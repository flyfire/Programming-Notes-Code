package coroutines_principle

import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine

/*
打印顺序：

before coroutine
after coroutine
in coroutine. Before suspend.
in suspend block.
calc md5 for test.zip.
after resume.
in coroutine. After suspend. result = 1499929744853
resume: kotlin.Unit
 */


fun main(args: Array<String>) {
    println("before coroutine")
    asyncCalcMd5("test.zip") /*下面这个lambda已经在异步现成中执行了*/{
        println("in coroutine. Before suspend.")
        val result: String = suspendCoroutine { continuation ->
            println("in suspend block.")
            continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
            println("after resume.")
        }
        println("in coroutine. After suspend. result = $result")
    }
    println("after coroutine")

    CommonPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
}


private fun calcMd5(path: String): String {
    println("calc md5 for $path.")
    Thread.sleep(1000)
    return System.currentTimeMillis().toString()
}

private fun asyncCalcMd5(path: String, block: suspend () -> Unit) {

    val continuation = object : Continuation<Unit> {

        override val context: CoroutineContext
            get() = FilePath(path) + CommonPool

        override fun resume(value: Unit) {
            println("resume: $value")
        }

        override fun resumeWithException(exception: Throwable) {
            println(exception.toString())
        }
    }

    block.startCoroutine(continuation)
}

private fun launch(context: CoroutineContext, block: suspend () -> Unit) = block.startCoroutine(StandaloneCoroutine(context))

class StandaloneCoroutine(override val context: CoroutineContext) : Continuation<Unit> {
    override fun resume(value: Unit) {}
    override fun resumeWithException(exception: Throwable) {
        //处理异常
        val currentThread = Thread.currentThread()
        currentThread.uncaughtExceptionHandler.uncaughtException(currentThread, exception)
    }
}
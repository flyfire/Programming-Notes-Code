package understanding

import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine


fun main(args: Array<String>) {
    println("1 before coroutine")
    asyncCalcMd5("test.zip") /*下面这个lambda已经在异步现成中执行了*/{
        println("3 in coroutine. Before suspend.")
        val result: String = suspendCoroutine { continuation ->
            println("4 in suspend block.")
            continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
            println("5 after resume.")
        }
        println("6 in coroutine. After suspend. result = $result")
    }
    println("2 after coroutine")

    CommonPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
}


private fun calcMd5(path: String): String {
    println("               calc md5 for $path.")
    Thread.sleep(1000)
    return System.currentTimeMillis().toString()
}

/**以CommonPool调度black*/
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

/**函数用于在执行的协程上下文中调度block*/
private fun launch(context: CoroutineContext, block: suspend () -> Unit) =
        block.startCoroutine(StandaloneCoroutine(context))

/**独立的协程*/
class StandaloneCoroutine(override val context: CoroutineContext) : Continuation<Unit> {
    override fun resume(value: Unit) {}
    override fun resumeWithException(exception: Throwable) {
        //处理异常
        val currentThread = Thread.currentThread()
        currentThread.uncaughtExceptionHandler.uncaughtException(currentThread, exception)
    }
}
package sample

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinWorkerThread
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.*

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

object CommonPool : Pool(ForkJoinPool.commonPool())

fun main(args: Array<String>) {
    println("before coroutine")
    asyncCalcMd5("test.zip") /*下面这个lambda已经在异步现成中执行了*/{
        println("in coroutine. Before suspend.")
        val result: String = suspendCoroutine {
            continuation ->
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


open class Pool(val pool: ForkJoinPool) : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
            PoolContinuation(pool, //下面这段代码是要查找其他拦截器，并保证能调用它们的拦截方法
                    continuation.context.fold(continuation,
                            { cont, element ->
                                if (element != this@Pool && element is ContinuationInterceptor)
                                    element.interceptContinuation(cont) else cont
                            }))
}

class PoolContinuation<in T>(val pool: ForkJoinPool, val continuation: Continuation<T>) : Continuation<T> by continuation {

    override fun resume(value: T) {
        if (isPoolThread()) {
            continuation.resume(value)
        } else pool.execute {
            continuation.resume(value)
        }
    }

    override fun resumeWithException(exception: Throwable) {
        if (isPoolThread()) continuation.resumeWithException(exception)
        else pool.execute { continuation.resumeWithException(exception) }
    }

    fun isPoolThread(): Boolean = (Thread.currentThread() as? ForkJoinWorkerThread)?.pool == pool
}
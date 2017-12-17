package understanding

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

    //创建一个协程Continuation，Continuation代表一个协程节点
    val continuation = object : Continuation<Unit> {

        override val context: CoroutineContext
            get() = FilePath(path) + CommonPool

        override fun resume(value: Unit) {
            println("--------------inner continuation resume: $value")
        }

        override fun resumeWithException(exception: Throwable) {
            println(exception.toString())
        }
    }

    block.startCoroutine(continuation)
}


/*
一个CoroutineContext
一个Element代表一个协程上下文元素
一个拦截器
具有调用协程和拦截调度的功能
 */
open class Pool(val pool: ForkJoinPool) : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =

            PoolContinuation(pool, //下面这段代码是要查找其他拦截器，并保证能调用它们的拦截方法
                    //continuation 就是 cont
                    continuation.context.fold(continuation, { cont, element ->
                        if (element != this@Pool && element is ContinuationInterceptor)//如果element不是当前Pool并且是一个拦截器，那么就拦截
                            element.interceptContinuation(cont)
                        else//否则不拦截，返回原来的cont
                            cont
                    }))
}

class PoolContinuation<in T>(val pool: ForkJoinPool, val continuation: Continuation<T>) : Continuation<T> by continuation {

    override fun resume(value: T) {
        if (isPoolThread()) {
            continuation.resume(value)
        } else
            pool.execute {
                continuation.resume(value)
            }
    }

    override fun resumeWithException(exception: Throwable) {
        if (isPoolThread()) continuation.resumeWithException(exception)
        else pool.execute { continuation.resumeWithException(exception) }
    }

    fun isPoolThread(): Boolean = (Thread.currentThread() as? ForkJoinWorkerThread)?.pool == pool
}
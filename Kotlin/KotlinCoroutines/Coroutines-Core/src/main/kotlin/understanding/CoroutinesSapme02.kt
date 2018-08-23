package understanding

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinWorkerThread
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.*

/**
1 before coroutine Thread[main,5,main]
2 after coroutine Thread[main,5,main]
3 in coroutine. Before suspend. Thread[main,5,main]
4 in suspend block. Thread[main,5,main]
calc md5 for test.zip.
5 after resume. Thread[main,5,main]
6 in coroutine. After suspend. result = 1513872046932 Thread[main,5,main]
 */
fun main(args: Array<String>) {
    //1
    println("1 before coroutine " + Thread.currentThread())

    asyncCalcMd5("test.zip") {
        //asyncCalcMd5是用的是异步调度器
        // 3 comm pool
        println("3 in coroutine. Before suspend. " + Thread.currentThread())

        val result: String = suspendCoroutine { continuation ->
            //4
            println("4 in suspend block. " + Thread.currentThread())
            continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
            //5
            println("5 after resume. " + Thread.currentThread())
        }

        //6
        println("6 in coroutine. After suspend. result = $result " + Thread.currentThread())
    }

    //2
    println("2 after coroutine " + Thread.currentThread())
    CommonPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
}

//异步调度
object CommonPool : Pool(ForkJoinPool.commonPool())


/**耗时方法*/
private fun calcMd5(path: String): String {
    println("               calc md5 for $path.")
    Thread.sleep(1000)
    return System.currentTimeMillis().toString()
}

/**开启协程*/
private fun asyncCalcMd5(path: String, block: suspend () -> Unit) {

    //创建一个协程Continuation，Continuation代表一个协程节点
    val continuation = object : Continuation<Unit> {

        override val context: CoroutineContext
        //这里添加了CommonPool
        //协程上下文可以使用+运算符来组合。右侧的上下文替换左侧上下文的相关条目。比如调度器被替换
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


/**
一个CoroutineContext
一个Element代表一个协程上下文元素
一个拦截器具有调用协程和拦截调度的功能
 */
open class Pool(val pool: ForkJoinPool) : AbstractCoroutineContextElement(ContinuationInterceptor/*这里其实引用的是ContinuationInterceptor.Key*/), ContinuationInterceptor {

    //该方法需要返回用于包装原始Continuation的新的Continuation
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
    //下面这段代码是要查找其他拦截器，并保证能调用它们的拦截方法
    //这里返回了一个PoolContinuation，PoolContinuation使用CommPool进行协程调度
            PoolContinuation(pool, continuation.context.fold(continuation,
                    { cont, element ->
                        //如果element不是当前Pool并且是一个拦截器，那么就拦截
                        if (element != this@Pool && element is ContinuationInterceptor)
                            element.interceptContinuation(cont)
                        else//否则不拦截，返回原来的Continuation
                            cont
                    }))
}

/**  代表一个协程执行段 */
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
        if (isPoolThread()) {
            continuation.resumeWithException(exception)
        } else {
            pool.execute { continuation.resumeWithException(exception) }
        }
    }

    private fun isPoolThread(): Boolean = (Thread.currentThread() as? ForkJoinWorkerThread)?.pool == pool
}
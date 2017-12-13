package coroutines_principle

import java.util.concurrent.Executors
import kotlin.coroutines.experimental.*

/**
 * 深入理解 Kotlin Coroutine：https://blog.kotliner.cn/2017/01/30/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3%20Kotlin%20Coroutine/
 */

private val executor = Executors.newSingleThreadScheduledExecutor { Thread(it, "coroutine-scheduler") }

fun main(args: Array<String>) {

    println("before coroutine")

    //启动我们的协程
    asyncCalcMd5("test.zip") {

        println("in coroutine. Before suspend.")

        //暂停我们的线程，并开始执行一段耗时操作
        //suspendCoroutine 这个方法将外部的代码执行权拿走，并转入传入的 Lambda 表达式中，而这个表达式当中的操作就对应异步的耗时操作了
        val result: String = suspendCoroutine { continuation ->
            println("in suspend block.")
            executor.submit {
                //将结果传了出去，传给suspendCoroutine 的返回值也即 result，这时候协程继续执行，打印 result 结束。
                continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
                println("after resume.")
            }
        }
        println("in coroutine. After suspend. result = $result")
    }

    println("after coroutine")
}

/**
 * 上下文，用来存放我们需要的信息，可以灵活的自定义
 */
class FilePath(val path: String) : AbstractCoroutineContextElement(FilePath) {
    companion object Key : CoroutineContext.Key<FilePath>
}

//模拟计算mdl
private fun calcMd5(path: String): String {
    println("calc md5 for $path.")
    //暂时用这个模拟耗时
    Thread.sleep(1000)
    //假设这就是我们计算得到的 MD5 值
    return System.currentTimeMillis().toString()
}

//异步计算
private fun asyncCalcMd5(path: String, block: suspend () -> Unit) {
    //定义Continuation，Continuation用于执行协程的启动，暂停，恢复
    val continuation = object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = FilePath(path)

        override fun resume(value: Unit) {
            println("resume: $value")
        }

        override fun resumeWithException(exception: Throwable) {
            println(exception.toString())
        }
    }

    block.startCoroutine(continuation)
}
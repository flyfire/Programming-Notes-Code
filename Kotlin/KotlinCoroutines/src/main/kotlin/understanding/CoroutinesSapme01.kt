package understanding

import java.util.concurrent.Executors
import kotlin.coroutines.experimental.*

//====================================================================
//深入理解 Kotlin Coroutine 1 ：https://blog.kotliner.cn/2017/01/30/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3%20Kotlin%20Coroutine/
//====================================================================

//一个线程池
private val executor = Executors.newSingleThreadScheduledExecutor { Thread(it, "coroutine-scheduler") }

/**
执行结果：
   1 before coroutine
   2 in coroutine. Before suspend.
   3 in suspend block.
   4 after coroutine
   5 calc md5 for test.zip.----------------------------------

   6 in coroutine. After suspend. result = 1513701367927  Thread[coroutine-scheduler,5,main]
   7 resume: kotlin.Unit Thread[coroutine-scheduler,5,main]
   8 after resume.

 */
fun main(args: Array<String>) {

    //1
    println("before coroutine")

    //2 启动我们的协程
    asyncCalcMd5("test.zip") {

        //3：3和6在同一个代码块中，但是这个是一个可挂起的代码库，等待挂起恢复后，代码块的执行线程将发生变化。
        println("in coroutine. Before suspend.")

        //暂停我们的线程，并开始执行一段耗时操作，由于asyncCalcMd5中没有指定特定的协程上下文，则该协程在主线程执行

        //suspendCoroutine 这个方法将外部的协程代码执行权拿走，并把执行权转入传入的 Lambda 表达式中
        val result: String = suspendCoroutine { continuation ->
            //4
            println("in suspend block.")
            //这个表达式当中的操作就对应异步的耗时操作了
            executor.submit {
                //执行耗时任务
                val filePath = continuation.context[FilePath.MyKey]!!
                val path = filePath.path//test.zip
                val md5 = calcMd5(path)
                //将结果传了出去，传给suspendCoroutine 的返回值也即 result，这时候协程继续执行，打印 result 结束。
                continuation.resume(md5)

                //7 等待外部协程执行完毕，接着执行后面的 println("after resume.")
                println("after resume.")
            }

            //suspendCoroutine的block执行完毕，则主线程继续执行外面的代码
        }

        //6
        println("in coroutine. After suspend. result = $result  ${Thread.currentThread()}")
    }

    //5
    println("after coroutine")
}

/**
 *协程上下文，用来存放我们需要的信息，可以灵活的自定义，
 *这里AbstractCoroutineContextElement的参数为FilePath，作为覆盖父类中的key属性
 */
class FilePath(val path: String) : AbstractCoroutineContextElement(MyKey) {
    //这里创建了一个Key类型的伴生对象,Key上定义的实际泛型类型为FilePath，则通过该Key获取的Element就是FilePath类型
    companion object MyKey : CoroutineContext.Key<FilePath>
}


//模拟计算mdl，这是一个耗时操作
private fun calcMd5(path: String): String {
    println("calc md5 for $path.----------------------------------")
    println()
    println()
    //暂时用这个模拟耗时
    Thread.sleep(1000)
    //假设这就是我们计算得到的 MD5 值
    return System.currentTimeMillis().toString()
}

/**
 * 异步计算，block是一个挂起函数：
 *
 * 1. 在函数内部创建Continuation对象
 * 2. 使用block启动协程
 */
private fun asyncCalcMd5(path: String, block: suspend () -> Unit) {
    //创建一个Continuation，Continuation用于执行挂起协程的恢复操作
    val continuation = object : Continuation<Unit> {

        //Continuation提供的关于该协程的上下文
        override val context: CoroutineContext
            get() = FilePath(path)

        override fun resume(value: Unit) {
            println("resume: $value ${Thread.currentThread()}")
        }

        override fun resumeWithException(exception: Throwable) {
            println(exception.toString())
        }
    }

    //调用挂起函数的startCoroutine方法
    //startCoroutine方法用于开启一个没有接收器和返回值的携程
    //当该协程执行完成后，continuation的resume或resumeWithException(发生异常情况下)方法将会被调用
    block.startCoroutine(continuation)
}
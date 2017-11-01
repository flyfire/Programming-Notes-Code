package core.canceltimeouts

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *协程代码必须合作才能取消
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 14:39
 */
/*
协程代码必须合作才能取消

1. kotlinx.coroutines中的所有暂停功能都可以取消。他们检查取消状态，并在取消时抛出CancellationException
2. 如果协同工作在计算中，并且不检查取消，则不能被取消，

 */
fun main(args: Array<String>) = runBlocking {

    //启动一个协程
    val job = launch(CommonPool) {
        var nextPrintTime = 0L
        var i = 0
        while (i < 10) { // computation loop
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime = currentTime + 500L
            }
        }
    }

    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    val cancel = job.cancel() // cancels the job
    println("cancel:$cancel")//即使取消后。协程还在运行，因为协程没有在暂停状态
    delay(1300L) // delay a bit to see if it was cancelled....
    println("main: Now I can quit.")
}
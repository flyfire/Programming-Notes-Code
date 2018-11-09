package core.cancel_timeouts

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *构造出可以被取消的协程——提高协程之间的协作能力
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 14:47
 */
/*
有两种方法可以让计算代码变得可以被取消。
1. 定期调用一个挂定函数，yield是一个不错的选择
2. 在协程内明确地检查自己的取消状态
 */

fun main(args: Array<String>) = runBlocking {

    //启动一个协程，在内部加上一个状态判断
    val job = launch(CommonPool) {
        var nextPrintTime = 0L
        var i = 0
        //isActive 是协程的内部状态
        while (isActive) { // cancellable computation loop
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime = currentTime + 500L
            }
        }
    }

    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    delay(1300L) // delay a bit to see if it was cancelled....
    println("main: Now I can quit.")
}
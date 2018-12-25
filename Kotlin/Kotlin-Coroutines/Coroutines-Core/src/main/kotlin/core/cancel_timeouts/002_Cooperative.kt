package core.cancel_timeouts

import kotlinx.coroutines.*


/*
协程代码检查自己是否被取消

1. kotlinx.coroutines中的所有suspend函数都可以取消。
2. 如果协程正在运行中，并且不检查取消状态，则它不能被取消(即使调用了cancel)，
 */
fun main() = runBlocking {
    //sampleStart
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion，即使取消后。协程还在运行，因为协程没有在暂停状态
    println("main: Now I can quit.")
//sampleEnd
}
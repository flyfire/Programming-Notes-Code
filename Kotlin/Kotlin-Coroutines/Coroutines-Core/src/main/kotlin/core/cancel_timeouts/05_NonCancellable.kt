package core.cancel_timeouts

import kotlinx.coroutines.*

fun main() = runBlocking {
    //sampleStart
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            //在罕见的情况下，需要在取消的协程中挂起，可以使用run(NonCancellable) {...}，
            //run作用：Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns the result.

            withContext(NonCancellable) {
                println(Thread.currentThread())
                println("I'm running finally")
                //如果不使用 withContext(NonCancellable) {}，则下面两行代码不会执行
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
//sampleEnd
}
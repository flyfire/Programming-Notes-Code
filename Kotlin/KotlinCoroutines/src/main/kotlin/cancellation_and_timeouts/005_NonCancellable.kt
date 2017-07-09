package cancellation_and_timeouts

import kotlinx.coroutines.experimental.*

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 14:58
 */
fun main(args: Array<String>) = runBlocking {
    val job = launch(CommonPool) {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            //在罕见的情况下，需要在取消的协议中挂起，可以使用run(NonCancellable) {...}，
            //run作用：Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns the result.
            run(NonCancellable) {
                println("I'm running finally")
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    delay(1300L) // delay a bit to ensure it was cancelled indeed
    println("main: Now I can quit.")
}
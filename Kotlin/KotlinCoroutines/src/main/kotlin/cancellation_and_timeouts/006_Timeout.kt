package cancellation_and_timeouts

import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 15:00
 */
fun main(args: Array<String>) = runBlocking {
    //withTimeout作用：Runs a given suspending block of code inside a coroutine with a specified timeout and throws
    // CancellationException if timeout was exceeded.
    //给定时间被协程没有执行完毕，将会抛出异常
   /* withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }*/

    try {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    } catch (e: CancellationException) {
        e.printStackTrace(System.err)
    }

}
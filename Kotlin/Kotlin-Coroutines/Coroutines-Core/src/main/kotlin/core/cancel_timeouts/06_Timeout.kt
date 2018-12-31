package core.cancel_timeouts


import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

//协程是可以设置超时的
fun main() = runBlocking {
    //withTimeout作用：Runs a given suspending block of code inside a coroutine with a specified timeout and throws
    // CancellationException if timeout was exceeded.
    //给定时间被协程没有执行完毕，将会抛出异常
    //sampleStart
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

//sampleEnd
}
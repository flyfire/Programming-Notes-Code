package coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.3 23:59
 */
/*
runBlocking:作为一个适配器，用于启动顶级主协程
 */
fun main(args: Array<String>) = runBlocking {
    // start main coroutine
    launch(CommonPool) {
        // create new coroutine in common thread pool
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues while child is delayed 当子协程延迟时主协程继续
    delay(2000L) // non-blocking delay for 2 seconds to keep JVM alive
}


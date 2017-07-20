package coroutines_06_concurrency

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 22:17
 */

// 互斥
val mutex = Mutex()
var counter3 = 0

fun main(args: Array<String>) = runBlocking {
    massiveRun(CommonPool) {
        //关键的区别是, Mutex是一个暂停的功能，不阻塞一个线程
        mutex.lock()
        try { counter3++ }
        finally { mutex.unlock() }
    }
    println("Counter = $counter3")
    /*
    Completed 1000000 actions in 1493 ms
    Counter = 1000000
     */
}
package core.concurrency

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.run
import kotlinx.coroutines.experimental.runBlocking

/**
 *并发控制——细粒度
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 22:07
 */

//线程控制——细粒度
val counterContext = newSingleThreadContext("CounterContext")
var counter1 = 0

fun main(args: Array<String>) = runBlocking {
    massiveRun(CommonPool) {
        //在CommonPool运行每一个协程
        run(counterContext) {
            // 但是限制每一个add操作在单一的线程
            counter1++
        }
    }
    println("Counter1 = $counter1")
    /*
    Completed 1000000 actions in 2397 ms
    Counter1 = 1000000
     */
}
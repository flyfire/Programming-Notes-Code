package core.concurrency

import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

/**
 *线程控制——粗粒度
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 22:07
 */
//实践中把大的状态更新业务逻辑限于单线程，有时候使用单线程效率更高
val counterContext2 = newSingleThreadContext("CounterContext")
var counter2 = 0

fun main(args: Array<String>) = runBlocking {
    massiveRun(counterContext2) { //在单线程运行每一个协程
            counter2++
    }
    println("Counter1 = $counter2")
    /*
    Completed 1000000 actions in 57 ms
    Counter1 = 1000000
        从打印机结果来看，这个方案的效率更高
     */
}
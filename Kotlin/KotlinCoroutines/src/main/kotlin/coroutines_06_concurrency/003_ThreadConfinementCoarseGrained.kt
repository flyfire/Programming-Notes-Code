package coroutines_06_concurrency

import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 22:07
 */
//线程控制——粗粒度
//实际上，线程限制在大块中执行，把大的状态更新业务逻辑限于单线程
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
     */
}
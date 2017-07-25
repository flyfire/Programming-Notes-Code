package coroutines_06_concurrency

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.system.measureTimeMillis

/**
 *通道也存在并发问题
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 21:56
 */
//注意，如果系统比较老，少于或等于两个cpu核心时，将不会看到并发的问题，因为在这种情况下，CommonPool只在一个线程中运行


//启动100个协程，并且每个协程执行一千次相同的动作
suspend fun massiveRun(context: CoroutineContext, action: suspend () -> Unit) {
    val n = 1000 // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        val jobs = List(n) {
            launch(context) {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")
}

var counter = 0

fun main(args: Array<String>) = runBlocking<Unit> {
    massiveRun(CommonPool) {
        counter++
    }
    println("Counter = $counter")
    /*
    打印结果为：
    Completed 1000000 actions in 54 ms
    Counter = 904759
    由此可以看出，kotlin中协程实现并不是线程安全的，其底层还是使用的多线程实现的协程，而且这种情况，volatile关键字并不适用
    而使用原子(Atomic)类可以避免并发问题
     */
}
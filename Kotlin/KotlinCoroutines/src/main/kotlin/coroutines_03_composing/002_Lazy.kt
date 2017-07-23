package coroutines_03_composing

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

/**
 *协程的懒启动
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 15:12
 */
/**
 * 并发的调用两个函数
 */
fun main(args: Array<String>) = runBlocking {

    val time = measureTimeMillis {
        //懒执行
        //async作用：Creates new coroutine and returns its future result as an implementation of [Deferred].
        val one = async(CommonPool, CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(CommonPool, CoroutineStart.LAZY) { doSomethingUsefulTwo() }

        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")//4074 ms 时间是两者相加，因为是懒执行
}

private suspend fun doSomethingUsefulOne(): Int {
    delay(3000L) // pretend we are doing something useful here
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}
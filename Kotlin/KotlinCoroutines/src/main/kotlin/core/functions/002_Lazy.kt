package core.functions

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 *协程的懒启动
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 15:12
 */

/**并发的调用两个函数*/
fun main(args: Array<String>) = runBlocking {

    val time = measureTimeMillis {
        //CoroutineStart.LAZY表示懒启动协程，在需要的时候才会启动，比如调用了await函数或start函数。
        //async作用：Creates new coroutine and returns its future result as an implementation of Deferred
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")// 时间是两者相加，因为是懒执行
}

private suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}
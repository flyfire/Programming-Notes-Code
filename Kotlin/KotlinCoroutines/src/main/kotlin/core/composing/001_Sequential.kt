package core.composing

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 *顺序执行两个协程
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.3 23:41
 */
//在协程中，顺序的调用两个函数
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")//4039
}


private suspend fun doSomethingUsefulOne(): Int {
    delay(3000L) // pretend we are doing something useful here
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

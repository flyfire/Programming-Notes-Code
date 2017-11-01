package core.composing

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 *编写异步风格的协程代码
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 16:34
 */
// note, that we don't have `runBlocking` to the right of `main` in this example
fun main(args: Array<String>) {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = asyncSomethingUsefulOne()
        val two = asyncSomethingUsefulTwo()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("runBlocking")
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")//3112
}


// The result type of asyncSomethingUsefulOne is Deferred<Int>
private fun asyncSomethingUsefulOne() = async(CommonPool) {
    doSomethingUsefulOne()
}

// The result type of asyncSomethingUsefulTwo is Deferred<Int>
private fun asyncSomethingUsefulTwo() = async(CommonPool) {
    doSomethingUsefulTwo()
}


private suspend fun doSomethingUsefulOne(): Int {
    println("doSomethingUsefulOne")
    delay(3000L) // pretend we are doing something useful here
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}
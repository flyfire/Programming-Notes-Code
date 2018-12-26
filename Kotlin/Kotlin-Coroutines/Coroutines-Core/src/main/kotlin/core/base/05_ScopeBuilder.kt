package core.base

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
Task from coroutine scope
Task from runBlocking
Task from nested launch
Coroutine scope is over
*/
fun main() = runBlocking {
    // this: CoroutineScope

    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    //使用 coroutineScope 创建一个新的 CoroutineScope
    coroutineScope {
        // Creates a new coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before nested launch
    }

    println("Coroutine scope is over") // This line is not printed until nested launch completes
}
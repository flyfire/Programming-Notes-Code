package core.base

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    launchDoWorld()
    println("Hello,")
}

// this is your first suspending function
suspend fun launchDoWorld() = coroutineScope {
    launch {
        println("World!")
    }
}
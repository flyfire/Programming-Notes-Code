package core.contextdispatchers

import kotlinx.coroutines.experimental.*

/**
 *Job
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 17:09
 */
/*
1. job是其context的一部分。协同程序可以使用context[Job]表达式获取job

2. When context of a coroutine is used to launch another coroutine,
the Job of the new coroutine becomes a child of the parent coroutine's job.
When the parent coroutine is cancelled, all its children are recursively cancelled, too.
 */

fun main(args: Array<String>) = runBlocking {
    // start a coroutine to process some kind of incoming request
    val request = launch(CommonPool) {
        // it spawns two other jobs, one with its separate context
        println(context[Job])

        val job1 = launch(CommonPool) {//由于1使用了新的Context，所以不会被取消
            println("job1: I have my own context and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }

        // and the other inherits the parent context
        val job2 = launch(context) {
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
        // request completes when both its sub-jobs complete:
        job1.join()
        job2.join()
    }

    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")
}
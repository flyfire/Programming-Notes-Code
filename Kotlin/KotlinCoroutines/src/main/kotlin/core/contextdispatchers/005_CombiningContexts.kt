package core.contextdispatchers

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *协程上下文组合
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 17:14
 */
/*
使用+运算符可以组合上下文，右侧的上下文替换左侧上下文的相关条目
例如，父协程工作可以被继承，而其调度员被替换
 */
fun main(args: Array<String>) = runBlocking {

    // start a coroutine to process some kind of incoming request
    val request = launch(context) {
        // use the context of `runBlocking`

        println(Thread.currentThread().name)//main
        println("context——" + context)
        // spawns CPU-intensive child job in CommonPool !!!，产生CPU密集型的子程序在共同工作池! ! !
        val job = launch(context + CommonPool) {
            println("context——" + context)
            println(Thread.currentThread().name)//commonPool
            println("job: I am a child of the request coroutine, but with a different dispatcher")
            delay(1000)
            println("job: I will not execute this line if my parent request is cancelled")

        }
        job.join() // request completes when its sub-job completes
    }

    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")
}



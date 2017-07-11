package coroutines_04_context_and_dispatchers

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 17:22
 */
/*
孩子和工作在一起,假设我们的应用程序有一个具有生命周期的对象，该对象不是协同程序。例如，我们正在编写一个Android应用程序，
并在Android活动的上下文中启动各种协同程序，以执行异步操作来获取和更新数据，进行动画等。为了避免内存泄漏，当Activity被销毁时所有这些协同程序必须被取消。
 */

fun main(args: Array<String>) = runBlocking {

    val job = Job() // create a job object to manage our lifecycle

    // now launch ten coroutines_01_base for a demo, each working for a different time
    val coroutines = List(10) { i ->
        // they are all children of our job object
        launch(context + job) { // we use the context of main runBlocking thread, but with our own job object
            delay(i * 200L) // variable delay 0ms, 200ms, 400ms, ... etc
            println("Coroutine $i is done")
        }
    }

    println("Launched ${coroutines.size} coroutines_01_base")
    delay(500L) // delay for half a second
    println("Cancelling job!")
    job.cancel() // cancel our job.. !!!
    delay(1000L) // delay for more to see if our coroutines_01_base are still working
}
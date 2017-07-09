package context_and_dispatchers

import kotlinx.coroutines.experimental.*

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 16:53
 */
//无限制的协同调度程序在调用者线程中启动协同程序，但直到第一个暂停点，暂停后，它将恢复在被调用的挂起函数完全确定的线程中。
// 无协调的调度程序适用于协调程序不消耗CPU时间，也不更新任何局限于特定线程的共享数据（如UI）。

//通过CoroutineScope接口在任何协同程序块中可用的context属性,是对这个特定协程的上下文的引用，runBlocking的默认上下文
//

fun main(args: Array<String>) = runBlocking {
    val jobs = arrayListOf<Job>()

    jobs += launch(Unconfined) { // not confined -- will work with main thread
        println(" 'Unconfined': I'm working in thread ${Thread.currentThread().name}")//main
        delay(500)
        println(" 'Unconfined': After delay in thread ${Thread.currentThread().name}")// kotlinx.coroutines.ScheduledExecutor
    }

    jobs += launch(context) { // context of the parent, runBlocking coroutine
        println("    'context': I'm working in thread ${Thread.currentThread().name}")//main
        delay(1000)
        println("    'context': After delay in thread ${Thread.currentThread().name}")//main
    }

    jobs.forEach { it.join() }
}
package core.context_dispatchers

import kotlinx.coroutines.experimental.*

/**
 *Unconfined和context协程调度器
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 16:53
 */

//Unconfined协程调度在调用者线程中启动协程，但直到第一个暂停点，暂停后，它运行在恢复它的线程中。
//Unconfined调度程序适用于不消耗CPU时间，也不更新任何局限于特定线程的共享数据（如UI）。
//通过CoroutineScope接口在任何协程块中可以访问coroutineContext属性，coroutineContext是对这个特定协程的上下文的引用，比如此处是runBlocking的默认上下文
//父上下文是可以被继承
fun main(args: Array<String>) = runBlocking {

    val jobs = arrayListOf<Job>()

    //对于Unconfined，当暂停恢复后，它的调度线程将发生改变，具体在哪个线程由
    jobs += launch(Unconfined) { // not confined -- will work with main thread
        println(" 'Unconfined': I'm working in thread ${Thread.currentThread().name}")//main
        delay(500)
        println(" 'Unconfined': After delay in thread ${Thread.currentThread().name}")// DefaultExecutor，DefaultExecutor监视delay的时间，然后在规定的时间后恢复协程。
    }

    jobs += launch(coroutineContext) { // context of the parent, runBlocking coroutine
        println("    'context': I'm working in thread ${Thread.currentThread().name}")//main
        delay(1000)
        println("    'context': After delay in thread ${Thread.currentThread().name}")//main
    }

    jobs.forEach { it.join() }
}
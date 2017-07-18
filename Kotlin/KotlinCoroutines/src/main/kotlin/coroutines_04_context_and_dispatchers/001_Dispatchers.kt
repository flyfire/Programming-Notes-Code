package coroutines_04_context_and_dispatchers

import kotlinx.coroutines.experimental.*

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 16:45
 */
/*
Dispatchers(调度器) and threads：
Coroutine context 包含一个Dispatchers，它确定对应协同程序用于执行的线程或线程池。
Dispatchers可以将协程执行限制在一个特定的线程上，将其调度到线程池，或者让其无限制地运行。
 */

fun main(args: Array<String>) = runBlocking {

    val jobs = arrayListOf<Job>()
    jobs += launch(Unconfined) {
        // not confined -- will work with main thread
        println(" 'Unconfined': I'm working in thread ${Thread.currentThread().name}")
    }

    jobs += launch(context) {
        // context of the parent, runBlocking coroutine ???
        println("    'context': I'm working in thread ${Thread.currentThread().name}")
    }

    jobs += launch(CommonPool) {
        // will get dispatched to ForkJoinPool.commonPool (or equivalent)
        println(" 'CommonPool': I'm working in thread ${Thread.currentThread().name}")
    }

    jobs += launch(newSingleThreadContext("MyOwnThread")) {
        // will get its own new thread
        println("     'newSTC': I'm working in thread ${Thread.currentThread().name}")
    }

    jobs.forEach { it.join() }
}
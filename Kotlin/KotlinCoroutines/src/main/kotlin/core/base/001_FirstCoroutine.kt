package core.base

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 *第一个协程程序
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.4 23:49
 */


//通过Builders中的launch启动协程
fun main(args: Array<String>) {
    //launch作用：启动一个新的协程并返回一个代表协程引用的Job对象，当前线程不会被阻塞
    //CommonPool作用：它是一个协程上下文，同时也是一个协程调度器，用于协程调度程序的计算密集型任务。此时协程将会在CommonPoll提供的线程池中运行
    launch(CommonPool) {
        // create new coroutine in common thread pool：在一个线程池中启动一个协程
        //delay作用：在给定的时间内延迟协程，此时该协程将让出执行权，仅用于协程中使用
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        println("CommonPool thread:" + Thread.currentThread())//Thread[ForkJoinPool.commonPool-worker-1,5,main]
    }
    println("Hello,") // main function continues while coroutine is delayed
    Thread.sleep(3000L) // block main thread for 2 seconds to keep JVM alive，因为协程类似守护线程，主线程退出协程就自动退出了
}

package coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.4 23:49
 */
/*
基本上，协同程序是轻量级的线程，其通过Builder中的launch启动
 */
fun main(args: Array<String>) {
    //launch作用：Launches new coroutine without blocking current thread and returns a reference to the coroutine as a [Job].
    //CommonPool作用：代表CommonPool线程协程调度程序的计算密集型任务。
    launch(CommonPool) {
        // create new coroutine in common thread pool：在一个线程池中启动一个协程
        //delay作用：在给定的时间内延迟协程，仅用于协程
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        println("CommonPool thread:" + Thread.currentThread())//Thread[ForkJoinPool.commonPool-worker-1,5,main]
    }
    println("Hello,") // main function continues while coroutine is delayed
    Thread.sleep(3000L) // block main thread for 2 seconds to keep JVM alive，因为协程类似守护线程，主线程退出协程就自动退出了
}
/*
打印结果为：
Hello,
World!
 */
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

    launch(CommonPool) { // create new coroutine in common thread pool：在一个线程池中启动一个协程
        //delay作用：在给定的时间内延迟协程，仅用于协程
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main function continues while coroutine is delayed
    Thread.sleep(3000L) // block main thread for 2 seconds to keep JVM alive
}
/*
打印结果为：
Hello,
World!
 */
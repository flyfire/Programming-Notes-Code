package core.base

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *协程是轻量级的
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.5 0:16
 */
//协程是轻量级的，它拥有自己的运行状态，但它对资源的消耗却非常的小
fun main(args: Array<String>) = runBlocking {
    //List作用：创建一个规定size的只读列表, 每一个函数调用指定的函数
    //这里创建了100_000协程
    val jobs = List(100_000) {
        // create a lot of core.base and list their jobs
        launch(CommonPool) {
            delay(2000L)//每一个协程都停一秒，但是相互之间并不阻塞
            print(".")
        }
    }
    println(jobs.size)
    //在主线程调用每一个协程的join方法，主线程会等待所有协程执行完毕后，再执行
    jobs.forEach { it.join() } // wait for all jobs to complete
    println(" end ....")
}

//结果是：很快的打印了100000个点，所有的协程都在两秒后瞬间执行完毕，如果是换成线程的话，那很可能造成内存溢出。

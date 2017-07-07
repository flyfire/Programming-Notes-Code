package coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.5 0:16
 */

fun main(args: Array<String>) = runBlocking {
    val jobs = List(100_000) {
        // create a lot of coroutines and list their jobs
        launch(CommonPool) {
            delay(2000L)//每一个协程都停一秒，但是相互之间并不阻塞
            print(".")
        }
    }
    println(jobs.size)
    jobs.forEach { it.join() } // wait for all jobs to complete
}
/*
结果是：很快的打印了100000个点，所有的协程都在两秒后瞬间执行完毕，如果是换成线程的话，那很可能造成内存溢出。
 */
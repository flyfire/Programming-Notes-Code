package coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.5 0:02
 */

//与002的结果是一样的，但是主协作的代码不以任何方式与后台作业的持续时间相关
fun main(args: Array<String>) = runBlocking {
    val job = launch(CommonPool) {
        doWorld()
    }
    println("Hello,")
    //join作用：Suspends coroutine until this job is complete.
    job.join() // wait until child coroutine completes
}

private suspend fun doWorld() {
    // create new coroutine and keep a reference to its Job
    delay(1000L)
    println("World!")
}
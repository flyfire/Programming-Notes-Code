package core.canceltimeouts

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *取消协程
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 14:35
 */

/*
在小应用程序中，从“main”方法返回可能听起来像是一个好主意，可以让所有协程程序隐式终止。
但是一个长时间运行的程序，需要细粒度的控制协程，launch函数返回一个可用于取消正在运行的协同程序的作业
 */
fun main(args: Array<String>) = runBlocking {

    val job = launch(CommonPool) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!") //不想等了
    job.cancel() // cancels the job 就取消
    delay(1300L) // delay a bit to ensure it was cancelled indeed
    println("main: Now I can quit.")
}
package coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.5 0:24
 */
/*
Coroutines are like daemon threads，当主线程退出时，协程也会退出，Active coroutines do not keep the process alive. They are like daemon threads.
 */
fun main(args: Array<String>) = runBlocking {

    launch(CommonPool) {
        //repeat作用：对给定的函数执行给定的次数
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

    delay(1300L) // just quit after delay
}
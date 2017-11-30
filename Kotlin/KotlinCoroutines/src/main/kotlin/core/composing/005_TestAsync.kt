package core.composing

import kotlinx.coroutines.experimental.*

/**
 *测试async方法
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.17 22:44
 */
fun main(args: Array<String>) = runBlocking {

    println("start")
    val result1 = async(CommonPool) {
        delay(3000)
        1
    }
    val result2 = async(CommonPool) {
        delay(3000)
        2
    }

    //await表示，当Deferred代表的协程调度完毕，会在指定的协程剩下文中调度await所在的代码块。
    val launch = launch(coroutineContext) {
        println(result1.await() + result2.await())
    }

    println("ending")
    launch.join()
}

/*
打印结果为：
start
ending
3
 */
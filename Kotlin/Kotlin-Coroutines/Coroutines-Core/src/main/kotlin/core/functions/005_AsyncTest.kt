package core.functions

import kotlinx.coroutines.experimental.*

/**
 *测试async方法
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.17 22:44
 */
fun main(args: Array<String>) = runBlocking {

    println("1 start")
    val result1 = async(CommonPool) {
        delay(3000)
        throw IllegalStateException("--------")
        1
    }
    val result2 = async(CommonPool) {
        delay(3000)
        2
    }

    //await表示，当Deferred代表的协程调度完毕，会在指定的协程上下文中调度await所在的代码块。
    val launch = launch(coroutineContext) {
        try {
            println("3 ${result1.await() + result2.await()}")
        } catch (e: Exception) {
            println("发生错误了")
        }
    }

    println("2 ending")
    launch.join()
}

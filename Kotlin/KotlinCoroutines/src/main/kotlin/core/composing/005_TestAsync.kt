package core.composing

import kotlinx.coroutines.experimental.*

/**
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
    val launch = launch(CommonPool) {
        println(result1.await() + result2.await())
    }
    println("ending")
    launch.join()
}
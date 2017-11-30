package core.contextdispatchers

import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.run
import kotlinx.coroutines.experimental.runBlocking

/**
 *调试协程
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 17:01
 */
/*
使用线程调试应用程序的常见方法是在每个日志语句的日志文件中打印线程名称。日志记录框架通常支持此功能。
JVM启动参数：-Dkotlinx.core.base.debug
 */
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

/*

fun main(args: Array<String>) = runBlocking {
    val a = async(context) {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async(context) {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
}
*/


fun main(args: Array<String>) {
    val ctx1 = newSingleThreadContext("Ctx1")
    val ctx2 = newSingleThreadContext("Ctx2")
    runBlocking(ctx1) {
        log("Started in ctx1")
        run(ctx2) {
            log("Working in ctx2")
        }
        log("Back to ctx1")
    }
}
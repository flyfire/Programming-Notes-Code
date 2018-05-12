package core.selectexpression

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import kotlin.coroutines.experimental.CoroutineContext

/**
 *选择表达式
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 23:30
 */

//选择表达式可以同时等待多个暂停功能，并选择可用的第一个。

//一个生产者协程，返回一个ProducerJob
private fun fizz(context: CoroutineContext) = produce(context) {
    while (true) { // sends "Fizz" every 300 ms
        delay(300)
        send("Fizz")
    }
}

//一个生产者协程，返回一个ProducerJob
private fun buzz(context: CoroutineContext) = produce(context) {
    while (true) { // sends "Buzz!" every 500 ms
        delay(500)
        send("Buzz!")
    }
}

private suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
    //条款(clauses)：onReceive即一种条款，表示暂停函数ReceiveChannel.receive从频道接受到了一个元素
    //select：同时等待多个中断函数的结果，在select内指定使用条款(builder)范围的选择调用的调用者将被暂停,直到其中一个条款被选择或失败
    select<Unit> {
        // <Unit> means that this select expression does not produce any result

        fizz.onReceive { value ->
            // this is the first select clause，这是第一选择条款
            println("fizz -> '$value'")
        }
        buzz.onReceive { value ->
            // this is the second select clause，这是第二选择条款
            println("buzz -> '$value'")
        }
    }
}

fun main(args: Array<String>) = runBlocking {
    val fizz = fizz(context)
    val buzz = buzz(context)
    repeat(7) {
        selectFizzBuzz(fizz, buzz)
    }
}
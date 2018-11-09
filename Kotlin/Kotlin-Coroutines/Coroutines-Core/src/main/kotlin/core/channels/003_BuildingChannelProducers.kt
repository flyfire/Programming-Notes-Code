package core.channels

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking

/**
 *produce——(生产者 - 消费者)
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.19 23:42
 */

//使用Product中的produce方便的实现(生产者与消费者模型)
//produce的作用：Launches new coroutine to produce a stream of values by sending them to a channel and returns a reference to the coroutine as a ProducerJob
//This resulting object can be used to receive  elements produced by this coroutine.
private fun produceSquares() = produce(CommonPool) {
    //produce的特点在于，当send完毕，其会自动关闭通道
    for (x in 1..5) send(x * x)
}

fun main(args: Array<String>) = runBlocking {

    val squares: ReceiveChannel<Int> = produceSquares()

    squares.consumeEach { println(it) }

    //这里使用为什么不会等待Channel关闭呢？
    for (a in squares) {
        println(a)
    }

    repeat(6) {
        //第六次的时候会抛出异常：.ClosedReceiveChannelException: Channel was closed
        //println(squares.receive())
    }

    println("Done!")
}
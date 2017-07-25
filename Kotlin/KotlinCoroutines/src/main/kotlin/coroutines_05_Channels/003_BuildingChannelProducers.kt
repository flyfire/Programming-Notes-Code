package coroutines_05_Channels

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ProducerJob
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking

/**
 *produce——(生产者 - 消费者)
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.19 23:42
 */
/*
协程生成一系列元素的模式是很常见的。这是在并发代码中经常出现的(生产者 - 消费者)模式的一部分。
可以将这样的生产者抽象成一个以通道为参数的功能，但这与常识相反，结果必须从函数返回。
有一个方便的构造器函数produce，使其易于在生产者方面做到这一点，和一个扩展函数consumeEach，可以替换消费者端的for循环
 */

//produce的作用：Launches new coroutine to produce a stream of values by sending them to a channel and returns a reference to the coroutine as a ProducerJob
//This resulting object can be used to receive  elements produced by this coroutine.
private fun produceSquares() = produce(CommonPool) {
    //produce的特点在于，当send完毕，其会自动关闭通道
    for (x in 1..5) send(x * x)
}

fun main(args: Array<String>) = runBlocking {
    val squares: ProducerJob<Int> = produceSquares()

//    squares.consumeEach { println(it) }
    //这里使用为什么不会等待Channel关闭呢？
/*    for (a in squares.channel) {
        println(a)
    }*/
/*    for (a in squares) {
        println(a)
    }*/

    repeat(6){
        //第六次的时候会抛出异常：.ClosedReceiveChannelException: Channel was closed
        println(squares.receive())
    }

    println("Done!")
}
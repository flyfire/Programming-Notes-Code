package coroutines_05_Channels

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.19 23:54
 */
//Pipelines(管道)是一个协程生产的模式，可能是无限的流
fun produceNumbers() = produce(CommonPool) {
    var x = 1
    while (true) {
        println("produceNumbers sending $x")
        send(x++)
    } // infinite stream of integers starting from 1
}

fun square(numbers: ReceiveChannel<Int>) = produce(CommonPool) {
    for (x in numbers) {
        println("square sending $x")
        send(x * x)
    }
}

fun main(args: Array<String>) = runBlocking<Unit> {
    val numbers = produceNumbers() // produces integers from 1 and on
    val squares = square(numbers) // squares integers

    for (i in 1..5) println(squares.receive()) // print first five
    println("Done!") // we are done
    //在大的应用程序中，如果我们不再需要它，我们将需要停止管道。
    squares.cancel() // need to cancel these coroutines in a larger app
    numbers.cancel()
}
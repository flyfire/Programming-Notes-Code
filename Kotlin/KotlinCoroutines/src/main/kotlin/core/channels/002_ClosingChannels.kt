package core.channels

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *关闭通道
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.19 23:36
 */

// Channel可以被关闭，那样接收方可以方便的使用正规的for循环来接收数据
fun main(args: Array<String>) = runBlocking {
    val channel = Channel<Int>()

    launch(CommonPool) {
        for (x in 1..5) channel.send(x * x)
        channel.close() // we're done sending，如果不关闭，下面for循环将会一直挂起。
    }

    // here we print received values using `for` loop (until the channel is closed)
    //使用for循环打印 channel发送的数据，直到channel关闭
    for (y in channel) println(y)
    println("Done!")
}
package coroutines_05_Channels

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *多个coroutines可能会发送到同一个channel。
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 0:35
 */
private suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}

//多个coroutines可能会发送到同一个channel。
fun main(args: Array<String>) = runBlocking {
    val channel = Channel<String>()//创建通道

    launch(context) { sendString(channel, "foo", 200L) }//发送
    launch(context) { sendString(channel, "BAR!", 500L) }//发送

    repeat(6) { // receive first six
        println(channel.receive())
    }
}
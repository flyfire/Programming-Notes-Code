package coroutines_05_Channels

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *通道容器缓存
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 0:36
 */
//Channel够建时可以指定容器的大小，用于缓存发射的数据
fun main(args: Array<String>) = runBlocking {
    val channel = Channel<Int>(4) // create buffered channel
    launch(context) {
        // launch sender coroutine
        repeat(10) {
            println("Sending $it") // print before sending each element
            channel.send(it) // will suspend when buffer is full
        }
    }
    // don't receive anything... just wait....
    delay(1000)
}
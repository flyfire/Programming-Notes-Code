package coroutines_05_Channels

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ProducerJob
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 0:35
 */

//创建一个协程ProducerJob
private fun produceNumbers() = produce(CommonPool) {
    var x = 1 // start from 1
    while (true) {
        send(x++) // produce next
        delay(100) // wait 0.1s
    }
}

private fun launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch(CommonPool) {
    //从频道获取数据
    channel.consumeEach {
        println("Processor #$id received $it")
    }
}

/*
多个coroutines可以从同一个channel接收，在他们之间分配工作。
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    //ProducerJob: Job, ReceiveChannel
    val producer: ProducerJob<Int> = produceNumbers()
    repeat(5) { launchProcessor(it, producer) }
    delay(1000)
    producer.cancel() // cancel producer coroutine and thus kill them all
}
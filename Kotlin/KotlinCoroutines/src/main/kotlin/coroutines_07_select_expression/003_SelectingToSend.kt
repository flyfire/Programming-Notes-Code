package coroutines_07_select_expression

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 23:52
 */
/*
选择表达式具有onSend子句，可以与一个很好的选择结合使用偏好的属性。
 */
private fun produceNumbers(side: SendChannel<Int>) = produce(CommonPool) {
    for (num in 1..10) { // produce 10 numbers from 1 to 10
        delay(100) // every 100 ms
        select<Unit> {
            onSend(num) {
                println("----on send $num")
            } // Send to the primary channel，发送到主频道
            side.onSend(num) {} // or to the side channel，调用side发送
        }
    }
}

fun main(args: Array<String>) = runBlocking {
    val side = Channel<Int>() // allocate side channel

    launch(context) { // this is a very fast consumer for the side channel
        side.consumeEach { println("Side channel has $it") }
    }

    produceNumbers(side).consumeEach {
        println("Consuming $it")
        delay(250) // let us digest the consumed number properly, do not hurry
    }
    println("Done consuming")
}

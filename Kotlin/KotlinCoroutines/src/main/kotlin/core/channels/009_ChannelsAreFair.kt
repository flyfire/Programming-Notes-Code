package core.channels

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *通道是公平的
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 0:36
 */


private data class Ball(var hits: Int)

private suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) { // receive the ball in a loop
        ball.hits++
        println("$name $ball")
        delay(300) // wait a bit
        table.send(ball) // send the ball back
    }
}

//发送和接收频道的操作对于多个协同程序的调用顺序是公平的。他们以先到先得的顺序送达，调用接收的第一个协同程序获取元素。，
fun main(args: Array<String>) = runBlocking<Unit> {
    val table = Channel<Ball>() // a shared table

    launch(context) { player("ping", table) }
    launch(context) { player("pong", table) }

    table.send(Ball(0)) // serve the ball
    delay(1000) // delay 1 second
    table.receive() // game over, grab the ball
}

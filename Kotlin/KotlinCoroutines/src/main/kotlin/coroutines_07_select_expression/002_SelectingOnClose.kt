package coroutines_07_select_expression

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select

/**
 *选择表达式规则
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 23:30
 */


/*
首先，select偏向于第一个clause(条款)，当几个条款可以同时选择时，其中第一个被选中
下面两个渠道都在不断地生产String，所以第一个渠道，作为选择中的第一个条款，赢了
然而因为我们正在使用无缓冲的频道，a在发送调用时不时的会暂停，并给b发送的机会。
 */
fun main(args: Array<String>) = runBlocking {
    // we are using the context of the main thread in this example for predictability ...
    val a = produce(context) {
        repeat(4) { send("Hello $it") }
    }
    val b = produce(context) {
        repeat(4) { send("World $it") }
    }

    repeat(8) {
        // print first eight results
        println(selectAorB(a, b))
    }
}


suspend fun selectAorB(a: ReceiveChannel<String>, b: ReceiveChannel<String>): String =
        select {

            a.onReceiveOrNull { value ->
                if (value == null)
                    "Channel 'a' is closed"
                else
                    "a -> '$value'"
            }

            b.onReceiveOrNull { value ->
                if (value == null)
                    "Channel 'b' is closed"
                else
                    "b -> '$value'"
            }
        }
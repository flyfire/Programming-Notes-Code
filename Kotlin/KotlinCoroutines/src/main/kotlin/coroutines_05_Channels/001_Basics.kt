package coroutines_05_Channels

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.17 23:22
 */
//Deferred 提供了在协同程序之间传输单个值的便利方式。Channels 提供了一种传输流的方式。
//Channels的概念非常类似 BlockingQueue，一个关键的不同是：挂起的send操作代替了阻塞的put 操作；挂起的 receive.操作代替了阻塞的take  操作

fun main(args: Array<String>) = runBlocking {
    //通道是一个非阻塞的原始发送者,用于在发送通道和接收者之间的通信。
    val channel = Channel<Int>(1/*容量*/)
    launch(CommonPool) {
        //这可能是重CPU耗费的计算或异步逻辑，我们将发送五个方块
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        //send作用：将(元素)添加到这个频道,当Channel完全填满时暂停调用者
        for (x in 1..5) channel.send(x * x)
    }
    // here we print five received integers:
    //receive作用：将(元素)从这个频道移除并获取整个元素,当Channel内没有元素时暂停调用者
    repeat(5) { println(channel.receive()) }
    println("Done!")
}

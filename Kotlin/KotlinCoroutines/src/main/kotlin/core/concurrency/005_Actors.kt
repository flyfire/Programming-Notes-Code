package core.concurrency

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.runBlocking

/**
 *Actor解决并发问题
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 22:21
 */
//Actor是一个Coroutine的组合，状态被限制并被封装到这个Coroutine中，Actor有一个通道可以与其他协程通信
//一个简单的Actor可以写成一个Function，但是一个复杂状态的Actor更适合一个class。

// Message types for counterActor
sealed class CounterMsg

object IncCounter : CounterMsg() // one-way message to increment counter
class GetCounter(val response: SendChannel<Int>) : CounterMsg() // a request with reply

// This function launches a new counter actor
//actor作用：Launches new coroutine that is receiving messages from its mailbox channel and returns a reference to the coroutine as an [ActorJob].
//The resulting object can be used to send messages to this coroutine.

fun counterActor() = actor<CounterMsg>(CommonPool) {
    var counter = 0 // actor state
    //内置channel
    for (msg in channel) { // iterate over incoming messages
        when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.send(counter)
        }
    }
}

//Actor是一个协程，并且它顺序执行，所以这解决共享可变状态的问题
//Actor比加载下的锁定更有效率，因为在这种情况下，它总是有工作要做，而且根本不需要切换到不同的上下文。
fun main(args: Array<String>) = runBlocking<Unit> {
//todo
//    val counter: ActorJob<CounterMsg> = counterActor() // create the actor

//    massiveRun(CommonPool) {
//        counter.send(IncCounter)
//    }

//    val response = Channel<Int>()//创建一个通道
//    counter.send(GetCounter(response))//send会暂停，因为counter已经满了
//    println("Counter = ${response.receive()}")//从通道获取值
//    counter.close() // shutdown the actor
}
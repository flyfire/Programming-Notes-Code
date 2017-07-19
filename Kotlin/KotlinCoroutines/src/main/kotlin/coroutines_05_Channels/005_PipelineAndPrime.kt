package coroutines_05_Channels

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.CoroutineContext

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.20 0:10
 */
//通道和质数

fun numbersFrom(context: CoroutineContext, start: Int) = produce(context) {
    var x = start
    while (true) {
        println("numbersFrom $x")
        send(x++)
    } // infinite stream of integers from start
}

fun main(args: Array<String>) = runBlocking {
    //通过从2开始一个数字流建立我们的管道，从当前频道中选出一个素数，并为每个素数找到新的流水线阶段
    //numbersFrom(2) -> filter(2) -> filter(3) -> filter(5) -> filter(7)

    var cur = numbersFrom(context, 2)

    for (i in 1..5) {
        val prime = cur.receive()//2
        println(prime)
        cur = filter(context, cur, prime)
    }
// 2          2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
// 3             3    5    7    9      11     13      15
// 5                   5    7             11     13
// 7                         7             11     13

}

//过滤输入的数字流,删除所有可被整数给定素数的数字
fun filter(context: CoroutineContext, numbers: ReceiveChannel<Int>, prime: Int) = produce(context) {
    println("------------------prime=$prime")
    for (x in numbers) {
        println("filter $x")
        if (x % prime != 0)
            send(x)
    }
    println("------------------")
}

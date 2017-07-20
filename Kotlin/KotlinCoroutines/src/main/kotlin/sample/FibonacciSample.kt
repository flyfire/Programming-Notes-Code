package sample

import kotlin.coroutines.experimental.buildSequence

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.17 23:03
 */
interface Sequence<out T> {
    operator fun iterator(): Iterator<T>
}

/*
延迟计算:斐波那契数列

斐波那契数列公式：
F0 =0
F1=1
Fn=F(n-1)+F(n-2);(n>=2)

 */
fun main(args: Array<String>) {

    //Builds a [Sequence] lazily yielding values one by one.
    //使用协程，可以实现懒计算和缓存之前的计算结果
    val fibonacci = buildSequence {
        println("------1")
        yield(1) // first Fibonacci number，转让执行权
        println("------2")
        var cur = 1
        var next = 1
        while (true) {
            yield(next) // next Fibonacci number，转让执行权
            println("------3")
            val tmp = cur + next
            cur = next
            next = tmp
        }
    }


    for (i in fibonacci) {
        println(i)
        if (i > 100) break //大于100就停止循环
    }
}
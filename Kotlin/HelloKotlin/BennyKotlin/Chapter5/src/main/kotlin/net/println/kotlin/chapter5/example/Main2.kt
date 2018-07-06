package net.println.kotlin.chapter5.example

/**
 * Created by benny on 4/23/17.
 */
fun main(args: Array<String>) {
    val list = listOf(1,3,4,5,6,7,9)
    list.takeWhile { it <= 3 }.forEach(::println)
    list.forEach {
        if(it % 2 == 0){
            println(it)
        }
    }
    list.filter(Int::isEvent).forEach(::println)
    println("Hello")


}

fun Int.isEvent() = this % 2 == 0
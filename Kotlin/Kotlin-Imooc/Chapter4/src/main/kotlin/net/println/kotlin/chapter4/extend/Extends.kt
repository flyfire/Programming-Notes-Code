package net.println.kotlin.chapter4.extend

/**
 * Created by benny on 4/4/17.
 */
fun main(args: Array<String>) {
    println("abc" * 16)
    "abc".b = 5
    println("abc".b)
}

operator fun String.times(int: Int): String{
    val stringBuilder = StringBuilder()
    for(i in 0 until int){
        stringBuilder.append(this)
    }
    return stringBuilder.toString()
}

val String.a: String
    get() = "abc"

var String.b: Int
    set(value) {

    }
    get() = 5

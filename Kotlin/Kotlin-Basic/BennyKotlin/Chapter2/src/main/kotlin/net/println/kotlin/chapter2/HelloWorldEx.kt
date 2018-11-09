package net.println.kotlin.chapter2

import net.println.kotlin.chapter2.helloworld.HelloWorld

/**
 * Created by benny on 2/26/17.
 */
val FINAL_HELLO_WORLD: String = "Hello World"
var helloWorld: String = FINAL_HELLO_WORLD
var nullableHelloWorld: String? = helloWorld
val helloWorldArray: Array<Char> = arrayOf('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd')
val helloWorldCharArray: CharArray = charArrayOf('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd')
val helloWorldLength: Int = helloWorld.length
val helloWorldLengthLong: Long = helloWorldLength.toLong()

fun main(args: Array<String>) {
    println("final hello world: " + FINAL_HELLO_WORLD)
    println("assignable hello world: " + helloWorld)
    println("hello world from nullable type: " + nullableHelloWorld)
    println("hello world from Array: " + helloWorldArray.joinToString(""))
    println("hello world from CharArray: " + String(helloWorldCharArray))
    println("class name hello world: " + HelloWorld::class.java.simpleName)
    println("class name hello world: " + HelloWorld::class.java.name)
    println("part of the class name of HelloWorld: "
            + HelloWorld::class.java.simpleName.slice(0 until helloWorldLength)) // [0, 11)
    println("the length of hello world is : " + helloWorldLength)
    println("the length of hello world is (long): " + helloWorldLengthLong)
}

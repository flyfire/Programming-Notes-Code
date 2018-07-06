package net.println.kotlin.chapter3

/**
 * Created by benny on 3/9/17.
 */

class X

class A{
    var b = 0
    lateinit var c: String
    lateinit var d: X
    val e: X by lazy {
        println("init X")
        X()
    }

    var cc: String? = null
}

fun main(args: Array<String>) {

    println("start")
    val a = A()
    println("init a")
    println(a.b)
    println(a.e)

    a.d = X()
    println(a.d)

    println(a.c)

    println(a.cc?.length)
}
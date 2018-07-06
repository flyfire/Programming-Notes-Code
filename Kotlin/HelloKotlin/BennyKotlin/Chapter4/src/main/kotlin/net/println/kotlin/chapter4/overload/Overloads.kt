package net.println.kotlin.chapter4.overload

import java.util.*

/**
 * Created by benny on 4/4/17.
 */
class Overloads{
    @JvmOverloads
    fun a(int: Int = 0): Int{
        return int
    }
}

fun main(args: Array<String>) {
    val overloads = Overloads()
    overloads.a(3)

    val integerList = ArrayList<Int>()
    integerList.add(13)
    integerList.add(2)
    integerList.add(3)
    integerList.add(23)
    integerList.add(5)
    integerList.add(15)
    integerList.add(50)
    integerList.add(500)
    println(integerList)

    integerList.removeAt(1)
    integerList.remove(5)
    println(integerList)
}
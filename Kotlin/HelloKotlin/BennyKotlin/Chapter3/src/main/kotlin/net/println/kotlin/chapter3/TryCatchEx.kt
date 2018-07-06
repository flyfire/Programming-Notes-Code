package net.println.kotlin.chapter3

/**
 * Created by benny on 3/19/17.
 */
fun main(args: Array<String>) {
    val result = try{
        args[0].toInt() / args[1].toInt()
    }catch (e: Exception){
        e.printStackTrace()
        0
    }
    println(result)
}
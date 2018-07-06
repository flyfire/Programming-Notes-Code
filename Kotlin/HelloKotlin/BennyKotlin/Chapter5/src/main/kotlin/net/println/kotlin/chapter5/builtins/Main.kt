package net.println.kotlin.chapter5.builtins

import java.io.BufferedReader
import java.io.FileReader

/**
 * Created by benny on 4/15/17.
 */

data class Person(val name: String, val age: Int){
    fun work(){
        println("$name is working!!!")
    }
}

fun main(args: Array<String>) {
    BufferedReader(FileReader("hello.txt")).use {
        var line: String?
        while (true){
            line = it.readLine()?: break
            println(line)
        }
    }
}

fun findPerson(): Person?{
    return null
}
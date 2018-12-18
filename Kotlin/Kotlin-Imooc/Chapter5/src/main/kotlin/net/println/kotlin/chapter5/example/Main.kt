package net.println.kotlin.chapter5.example

import java.io.File

/**
 * Created by benny on 4/15/17.
 */
fun main(args: Array<String>) {
    val map = HashMap<Char, Int>()
    File("build.gradle").readText().toCharArray().filterNot(Char::isWhitespace)
            .groupBy { it }.map {
        it.key to it.value.size
    }.forEach(::println)

}
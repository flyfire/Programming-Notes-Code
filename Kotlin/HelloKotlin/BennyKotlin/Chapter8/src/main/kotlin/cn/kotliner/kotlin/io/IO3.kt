package cn.kotliner.kotlin.io

import java.io.*

/**
 * Created by benny on 5/28/17.
 */
fun main(args: Array<String>) {
    File("build.gradle").readLines().forEach(::println)
}
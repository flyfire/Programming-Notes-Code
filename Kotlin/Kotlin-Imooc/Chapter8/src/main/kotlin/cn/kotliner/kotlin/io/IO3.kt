package cn.kotliner.kotlin.io

import java.io.File

/**
 * Created by benny on 5/28/17.
 */
fun main(args: Array<String>) {
    //小文件一次性读取
    File("build.gradle").readLines().forEach(::println)
}
package _java.io

import rxkotlin.sample2
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    sample1()
    sample2()
    //小文件一次性读取
    sample3()
}

fun sample3() {
    File("build.gradle").readLines().forEach(::println)
}

private fun sample2() {
    val file = File("build.gradle")
    //use自关闭资源
    BufferedReader(FileReader(file)).use {
        var line: String
        while (true) {
            line = it.readLine() ?: break
            println(line)
        }
    }
}

private fun sample1() {
    val file = File("build.gradle")
    val bufferedReader = BufferedReader(FileReader(file))
    var line: String

    while (true) {
        line = bufferedReader.readLine() ?: break
        println(line)
    }
    bufferedReader.close()
}
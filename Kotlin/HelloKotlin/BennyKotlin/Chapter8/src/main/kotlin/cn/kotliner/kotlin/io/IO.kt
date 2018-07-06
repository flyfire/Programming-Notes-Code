package cn.kotliner.kotlin.io

import java.io.*

/**
 * Created by benny on 5/28/17.
 */
fun main(args: Array<String>) {
    val file = File("build.gradle")
    val bufferedReader = BufferedReader(FileReader(file))
    var line: String

    while(true){
        line = bufferedReader.readLine()?:break
        println(line)
    }
    bufferedReader.close()
}
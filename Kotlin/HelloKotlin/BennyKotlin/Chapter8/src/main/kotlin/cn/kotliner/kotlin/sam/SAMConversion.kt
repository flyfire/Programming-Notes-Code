package cn.kotliner.kotlin.sam

import cn.kotliner.java.sam.SAMInJava
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    val samInJava = SAMInJava()
    val lambda = {
        println("Hello")
    }

    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)

    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)

    val samInKotlin = SAMInKotlin()
    samInKotlin.addTask {

    }
}
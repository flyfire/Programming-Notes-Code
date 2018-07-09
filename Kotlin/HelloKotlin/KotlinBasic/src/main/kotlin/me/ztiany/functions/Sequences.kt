package me.ztiany.functions

import java.io.File

fun File.isInsideHiddenDirectory() = generateSequence(this) {
    println("generate：$it")
    it.parentFile
}.any {
    println("any：$it")
    it.isHidden
}

fun main(args: Array<String>) {
    val file = File(".").absoluteFile
    println(file.isInsideHiddenDirectory())
}
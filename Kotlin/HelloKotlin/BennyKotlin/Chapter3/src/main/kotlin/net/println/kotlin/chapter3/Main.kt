package net.println.kotlin.chapter3

/**
 * Created by benny on 3/5/17.
 */
const val FINAL_HELLO_WORLD: String = "HelloWorld"
var helloWorld: String = FINAL_HELLO_WORLD

val FINAL_HELLO_CHINA = "HelloChina"

fun main(args: Array<String>) { // (Array<String>) -> Unit
//    checkArgs(args)
//    val arg1 = args[0].toInt()
//    val arg2 = args[1].toInt()
//    println("$arg1 + $arg2 = ${sum(arg1, arg2)}")
//    println(int2Long(3))
//    println(sum(1, 3))
//    println(sum.invoke(1, 3))

//    args.forEach ForEach@{
//        if(it == "q") return@ForEach
//        println(it)
//    }
//
//    println("The End")

    println(sum)
    println(int2Long)
    println(::printUsage is ()-> Unit)
}

fun checkArgs(args: Array<String>) {
    if (args.size != 2) {
        printUsage()
        System.exit(-1)
    }
}

fun printUsage() {
    println("请传入两个整型参数，例如 1 2") // (Any?) -> Unit
} // ()->Unit

val sum = { arg1: Int, arg2: Int ->
    println("$arg1 + $arg2 = ${arg1 + arg2}")
    arg1 + arg2
}
// (Int, Int) -> Int

val printlnHello = {
    println("Hello")
}
// ()-> Unit

val int2Long = fun(x: Int): Long {
    return x.toLong()
}

//Int Long String  ABC

// (Int) -> Long



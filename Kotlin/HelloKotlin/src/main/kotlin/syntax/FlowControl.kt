package syntax

/**
 *流程控制
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.23 22:57
 */

fun testFor() {

    val list = listOf("A", "B", "C", "D")
    for (item in list) {
        print(item)
    }
    println()
    for (index in list.indices) {
        println(list[index])
    }
}

fun testWhile() {
    val list = listOf("A", "B", "C", "D")
    var index = 0
    while (index < list.size) {
        println(list[index])
        index++
    }
}


fun testWhen(obj: Any): String =
        when (obj) {//类似switch语句
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

fun main(args: Array<String>) {
    testFor()
    testWhile()
    println(testWhen(21))
}


package _java.regex

import java.util.regex.Pattern

fun main(args: Array<String>) {
    val source = "Hello, This my phone number: 010-12345678. "
    //raw String中不用转义
    val pattern = """.*(\d{3}-\d{8}).*"""
    val matcher = Pattern.compile(pattern).matcher(source)

    while (matcher.find()) {
        println(matcher.group())
        println(matcher.group(1))
    }
}

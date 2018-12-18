package cn.kotliner.kotlin.regex

import java.util.regex.Pattern

/**
 * Created by benny on 5/28/17.
 */
fun main(args: Array<String>) {
    val source = "Hello, This my phone number: 010-12345678. "
    val pattern = """.*(\d{3}-\d{8}).*"""

    Regex(pattern).findAll(source).toList().flatMap(MatchResult::groupValues).forEach(::println)

}
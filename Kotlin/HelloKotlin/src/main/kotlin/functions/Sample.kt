package functions

import java.io.File

/**
 *
 * @author ztiany
 *          Email: ztiany3@gmail.com
 */
fun main(args: Array<String>) {
    //统计一个文件中所有字符串的个数
    File("build.gradle")
            .readText()
            .toCharArray()
            .filterNot(Char::isWhitespace)
            .groupBy { it }
            .map {
                it.key to it.value.size
            }
            .forEach(::println)
}
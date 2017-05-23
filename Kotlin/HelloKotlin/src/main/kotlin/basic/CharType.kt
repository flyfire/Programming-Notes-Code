package basic

/**
 *字符类型
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.24 0:08
 */

/**
 * 字符类型：字符用 Char 类型表示。它们不能直接当作数字，字符字面值用单引号括起来: '1'，特殊字符可以用反斜杠转义，
 *  编码其他字符要用 Unicode 转义序列语法：'\uFF00'。
 *  当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性
 */

fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt() - '0'.toInt() // 显式转换为数字
}


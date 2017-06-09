package syntax

/**
 *空检查
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.22 22:56
 */

/**
当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
 */
fun parseInt(str: String): Int? {
    //如果 str 的内容不是数字返回 null：
    return str.toIntOrNull()
}

fun main(args: Array<String>) {
    println(parseInt("123"))
    println(parseInt("abc"))
}
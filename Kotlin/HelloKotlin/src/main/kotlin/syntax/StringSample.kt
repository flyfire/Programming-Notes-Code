package syntax

/**
 *字符串
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.22 22:47
 */


//字符串模板
fun main(args: Array<String>) {
    //sampleStart
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    //sampleEnd
    println(s2)
}


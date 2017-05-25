package syntax

/**
 *定义局部变量
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.22 22:47
 */

//定义局部变量
fun main(args: Array<String>) {
    //一次赋值（只读）的局部变量
    val a: Int = 3
    val b = 4
    val c: Int
    c = 5
    println("a = $a, b = $b, c = $c")
    //可变变量
    var x = 5 // 自动推断出 `Int` 类型
    x += 1
    println("x = $x")
}


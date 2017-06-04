package syntax

/**
 *条件表达式
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.22 22:47
 */


//返回最大值
fun maxOf_1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

//返回最大值
fun maxOf_2(a: Int, b: Int): Int = if (a > b) a else b


fun main(args: Array<String>) {
    println("max of 433 and 42 is ${maxOf_1(433, 42)}")
    println("max of 0 and 42 is ${maxOf_2(0, 42)}")
}
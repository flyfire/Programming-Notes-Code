package syntax


/**
 *定义函数
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.22 22:47
 */

/*
 定义函数，返回Int类型
 */
fun sum(a: Int, b: Int): Int {
    return a + b
}


/*
将表达式作为函数体、返回值类型自动推断的函数：
 */
fun sum_1(a: Int, b: Int): Int = a + b

/*
无返回值
 */
fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

/*
Main函数
 */
fun main(args: Array<String>) {
    println("sum(3, 5): result = ${sum(3, 5)}")
    println("sum_1(3, 5): result = ${sum_1(3, 5)}")
    printSum(4, 5)
}
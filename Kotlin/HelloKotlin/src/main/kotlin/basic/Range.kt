package basic

/**
 *使用区间
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.23 23:06
 */


/**
 * 使用 in 运算符来检测某个数字是否在指定区间内
 */
private fun range() {
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }
}

/**
 * 区间迭代
 */
private fun rangeIteration() {
    for (x in 1..5) {
        print(x)
        print(',')
    }
    println()
    for (x in 1..10 step 2) {
        print(x)
        print(',')
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
        print(',')
    }
    for (x in 1 until 10) {//不包含10
        print(x)
        print(',')
    }
}

fun main(args: Array<String>) {
    basic.range()
    basic.rangeIteration()
}


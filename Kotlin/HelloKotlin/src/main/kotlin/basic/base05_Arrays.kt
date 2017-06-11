package basic

import java.util.*

/**
 *数组类型
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.24 23:16
 */

private fun createArray() {

    // 使用库函数 arrayOf() 来创建一个数组并传递元素值给它
    val intArray = arrayOf(1, 2, 3, 4, 5)
    println(Arrays.toString(intArray))
    println(intArray[2])
    println(intArray.set(1, 2))
    println(intArray.size)

    // 库函数 arrayOfNulls() 可以用于创建一个指定大小、元素都为空的数组。
    val arrayNulls = arrayOfNulls<String>(size = 4)
    println(Arrays.toString(arrayNulls))

    //创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
    val asc = Array(5, { i -> (i * i).toString() })
    println(Arrays.toString(asc))
}


fun main(args: Array<String>) {
    createArray()
}
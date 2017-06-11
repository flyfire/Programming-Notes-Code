package advance

/**
 *集合
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.6.5 22:25
 */


/**
 * Kotlin中集合：
 *  1，Kotlin 区分可变集合和不可变集合（lists、sets、maps 等）。精确控制什么时候集合可编辑有助于消除 bug 和设计良好的 API。
 *  2，Kotlin 没有专门的语法结构创建 list 或 set。 要用标准库的方法，如 listOf()、 mutableListOf()、 setOf()、 mutableSetOf()
 *  3，在非性能关键代码中创建 map 可以用一个简单的惯用法来完成：mapOf(a to b, c to d)
 */

open private class Shape

private class Square : Shape()

private fun useCollections() {
    //可变集合
    val mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    mutableList.clear()
    //listOf创建的是只读集合
    val readOnlyList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    //创建set
    val strSet = hashSetOf("a", "b", "c", "c")
    //在非性能关键代码中创建 map 可以用一个简单的惯用法来完成
    val map = mapOf("A" to 1, "B" to 2)

    //协变性，不可变集合具有协变性
    var listShape: List<Shape> = listOf()
    val listSquare: List<Square> = listOf()
    listShape = listSquare

    //对于可变集合，使用toList()可以返回一个快照
    val intList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val readOnlyIntList = intList.toList()

    //集合还有一些列优雅的API

}

fun main(args: Array<String>) {
    useCollections()
}

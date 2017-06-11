package oop

/**
 *泛型
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.30 17:16
 */

/**
 * in和out：out，输出，表示消费者；in，进入，表示生产者
 */
private abstract class Source<out T> {
    abstract fun nextT(): T
}

private abstract class TComparable<in T> {
    abstract fun compareTo(other: T): Int
}

private fun testIn(source: Source<String>) {
    val sourceAny: Source<Any> = source
}

private fun testIn(x: TComparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: TComparable<Double> = x // OK！
}

/**
 * 类型投影
 */
private fun copy(from: Array<out Any>, to: Array<Any>) {

}

/**
 * 星投影：
 *
 *          Function<*, String> 表示 Function<in Nothing, String>；
 *          Function<Int, *> 表示 Function<Int, out Any?>；
 *          Function<*, *> 表示 Function<in Nothing, out Any?>。
 *
 */


/**
 * 上界：冒号之后指定的类型是上界：只有 Comparable<T> 的子类型可以替代 T
 *
 * 1，默认的上界（如果没有声明）是 Any?。在尖括号中只能指定一个上界。
 */
private fun <T : Comparable<T>> sort(list: List<T>) {

}

private fun testSort() {
    sort(listOf(1, 2, 3)) // OK。Int 是 Comparable<Int> 的子类型
}


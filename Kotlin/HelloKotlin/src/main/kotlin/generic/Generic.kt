package generic

//todo 学习Kotlin泛型

/**
 *Kotlin泛型
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.30 17:16
 */

//https://www.kotlincn.net/docs/reference/generics.html
//https://blog.kotliner.cn/2017/06/26/kotlin-generics/
//https://kymjs.com/code/2017/06/06/01/


/**
 * 泛型：与Java 类似，Kotlin 中的类也可以有类型参数
 */
private class Box<T>(t: T) {
    var value = t
}

private val box1: Box<Int> = Box<Int>(1)
// 1 具有类型 Int，所以编译器知道我们说的是 Box<Int>。则可以省略掉返现说明
private val box2 = Box(1)


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


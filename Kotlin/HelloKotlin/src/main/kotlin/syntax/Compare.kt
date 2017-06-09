package syntax

/**
 *比较
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.6.4 15:14
 */

/**
 * Kotlin中的比较：
 *
 *      ==比较：
 *          如果作用于基本数据类型的变量，则直接比较其存储的 “值”是否相等
 *          对于引用类型： a==b等同于a?.equals(b) ?: (b === null)
 *      equals
 *          equals方法不能作用于基本数据类型的变量
 *          如果没有对equals方法进行重写，则比较的是引用类型的变量所指向的对象的地址；
 *          诸如String、Date等类对equals方法进行了重写，比较的是所指向的对象的内容。
 *       === 和 !==
 *           a===b当且仅当a和b执行同一个对象时返回true
 */
fun main(args: Array<String>) {
    var a: Int = 3
    var b: Int? = 3
    println(a==b)
}
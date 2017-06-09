package feature

import kotlin.reflect.KClass
import kotlin.reflect.jvm.javaGetter

/**
 *反射
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.6.8 23:34
 */

/**
 *反射：反射是这样的一组语言和库功能，它允许在运行时自省你的程序的结构。
 *           Kotlin 让语言中的函数和属性做为一等公民、并对其自省（即在运行时获悉一个名称或者一个属性或函数的类型）
 *          与简单地使用函数式或响应式风格紧密相关。
 *
 *
 * 类引用：val c = MyClass::class
 * 1，该引用是 KClass 类型的值。
 * 2，Kotlin 类引用与 Java 类引用不同。要获得 Java 类引用， 请在 KClass 实例上使用 .java 属性
 *
 * */

private val number = 233

private class PropertyA(val p: Int)

private val String.lastChar: Char
    get() = this[length - 1]

fun main(args: Array<String>) {

    //类引用
    val strClazz = String::class
    println(strClazz)

    //绑定的类引用
    val str = "abc"
    println(str::class)
    println(str::class.java)

    //函数引用
    fun isOdd(x: Int) = x % 2 != 0

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))//::isOdd 是函数类型 (Int) -> Boolean 的一个值

    //当上下文中已知函数期望的类型时，:: 可以用于重载函数
    fun isOdd(s: String) = s == "brillig" || s == "slithy" || s == "tove"
    println(numbers.filter(::isOdd))//// 引用到 isOdd(x: Int)

    //可以通过将方法引用存储在具有显式指定类型的变量中来提供必要的上下文
    val predicate: (String) -> Boolean = ::isOdd

    //如果我们需要使用类的成员函数或扩展函数，它需要是限定的
    //String::toCharArray 为类型 String 提供了一个扩展函数：String.() -> CharArray。
    fun toCharArray(lambda: (String) -> CharArray) {
        lambda("abc")
    }
    toCharArray(String::toCharArray)

    //函数组合
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    fun length(s: String) = s.length
    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")
    println(strings.filter(oddLength)) // 输出 "[a, abc]"

    //要把属性作为 Kotlin中 的一等对象来访问，我们也可以使用 :: 运算符
    //表达式 ::x 求值为 KProperty<Int> 类型的属性对象
    println(::number.get())


    //属性引用可以用在不需要参数的函数处
    val strList = listOf("a", "bc", "def")
    println(strList.map(String::length))

    //访问属于类的成员的属性
    val prop = PropertyA::p
    println(prop.get(PropertyA(1))) // 输出 "1"

    //对于扩展属性
    println(String::lastChar.get("abc")) // 输出 "c"


    //与 Java 反射的互操作性
    //在Java平台上，标准库包含反射类的扩展，它提供了与 Java 反射对象之间映射(kotlin.reflect.jvm)

    //查找一个用作 Kotlin 属性 getter 的 幕后字段或 Java方法
    println(PropertyA::p.javaGetter)
    //获得对应于 Java 类的 Kotlin 类
    fun getKClass(o: Any): KClass<Any> = o.javaClass.kotlin
    println(getKClass("abc"))


    //构造函数引用
    //构造函数可以像方法和属性那样引用。他们可以用于期待这样的函数类型对象的任何地方
    //：它与该构造函数接受相同参数并且返回相应类型的对象。 通过使用 :: 操作符并添加类名来引用构造函数。
    class Foo

    fun function(factory: () -> Foo) {
        val x: Foo = factory()
    }
    println(function(::Foo))
    Foo::class.constructors.forEach {
        println(it)
    }

    //绑定的函数与属性引用

    //可以引用特定对象的实例方法
    val numberRegex = "\\d+".toRegex()
    println(numberRegex.matches("29")) // 输出“true”
    val isNumber: (String) -> Boolean = numberRegex::matches
    println(isNumber)
    println(isNumber("29"))


    //属性引用也可以绑定
    val lengthProp = "abc"::length
    println(lengthProp.get())   // 输出“3”

}


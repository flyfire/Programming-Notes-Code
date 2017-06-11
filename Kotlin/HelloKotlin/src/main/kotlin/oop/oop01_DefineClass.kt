package oop

import java.awt.Frame
import java.awt.Window
import java.awt.event.ActionListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/**
 *定义类
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.26 22:51
 */


/**
 * 定义类：Kotlin 中使用关键字 class 声明类。
 *
 * 1，类声明由类名、类头（指定其类型参数、主 构造函数等）和由大括号包围的类体构成。
 * 2，类头和类体都是可选的； 如果一个类没有类体，可以省略花括号。
 */
private class Person constructor(name: String, var age: Int)

private fun testPerson() {
    val person = Person("abc", 21)
    person.age = 12//构造函数参数加上var或者val，则自动为类上的属性
}


/**
 * 构造函数：
 *
 * 1，在 Kotlin 中的一个类可以有一个主构造函数和一个或多个次构造函数。主构造函数是类头的一部分：它跟在类名（和可选的类型参数）后
 * 2，如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
 * 3，如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面
 * 4，主构造函数不能包含任何的代码。初始化的代码可以放 到以 init 关键字作为前缀的初始化块（initializer blocks）中
 * 5，主构造的参数可以在初始化块中使用。它们也可以在类体内声明的属性初始化器中使用
 */
private class Dog(name: String) {

    //属性初始化
    val tag = name.hashCode()

    //初始化块
    init {
        println(name)
    }

}

/**
 *次构造函数：类也可以声明前缀有 constructor 的次构造函数：
 *
 * 1，如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，可以直接委托或者通过别的次构造函数间接委托。
 *      委托到同一个类的另一个构造函数 用 this 关键字即可
 * 2，如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的 不带参数的主构造函数。构造函数的可见性是 public。
 *      如果你不希望你的类 有一个公有构造函数，你需要声明一个带有非默认可见性的空的主构造函数
 * 3，在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值。
 */
private class Woman(val name: String) {
    constructor(name: String, parent: Person) : this(name)
}

/**
 *创建类的实例：要创建一个类的实例，就像普通函数一样调用构造函数， Kotlin 并没有 new 关键字。
 *
 *类可以包含：
 *  1，构造函数和初始化块
 *  2，函数
 *  3，属性
 *  4，嵌套类和内部类
 *  5，对象声明
 */
private var person = Person("Ztiany", 21)

/**
 * 嵌套类：类可以嵌套在其他类中
 *
 *  1，类可以标记为 inner 以便能够访问外部类的成员。内部类会带有一个对外部类的对象的引用
 *
 * */

private class Outer {

    private val bar: Int = 1

    inner class Nested1 {
        fun foo() = 2
    }

    class Nested2 {
        fun foo() = 2
    }
}

//访问方式不一样的
private val nested1 = Outer().Nested1().foo() // == 2
private val nested2 = Outer.Nested2().foo() // == 2

/**
 * 匿名内部类：使用对象表达式创建匿名内部类实例
 *
 * 1，如果对象是函数式Java接口（即具有单个抽象方法的 Java 接口）的实例， 可以使用带接口类型前缀的lambda表达式创建它
 *
 * */
private fun testAnonymity() {

    val window: Window = Window(Frame())
    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            // ……
        }
        override fun mouseEntered(e: MouseEvent) {
            // ……
        }
    })

    //如果对象是函数式Java接口（即具有单个抽象方法的 Java 接口）的实例， 可以使用带接口类型前缀的lambda表达式创建它
    val listener = ActionListener { println("clicked") }

}
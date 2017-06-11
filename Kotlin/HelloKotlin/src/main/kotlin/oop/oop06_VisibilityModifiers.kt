package oop

/**
 *可见性修饰符
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.30 14:36
 */

/**
 * 可见性修饰符：类、对象、接口、构造函数、方法、属性和它们的 setter 都可以有可见性修饰符。 （getter 总是与属性有着相同的可见性。）
 * 在 Kotlin 中有这四个可见性修饰符：private、 protected、 internal 和 public。 如果没有显式指定修饰符的话，默认可见性是 public
 */


/**
 *包名内：
 *
 *  1，函数、属性和类、对象和接口可以在顶层声明，即直接在包内
 *  2，如果声明为 private，只会在声明它的文件内可见
 *  3，如果声明为 internal，在相同模块内随处可
 *  4，protected 不适用于顶层声明
 *
 *类和接口：
 *
 *  1，private 意味着只在这个类内部（包含其所有成员）可见
 *  2，protected—— 和 private一样 + 在子类中可见
 *  3，internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员
 *  4，public —— 能见到类声明的任何客户端都可见其 public 成员。
 *
 * 构造函数：
 *
 *  1，class C private constructor(a: Int) { …… }这里的构造函数是私有的。
 *  2， 默认情况下，所有构造函数都是 public，这实际上 等于类可见的地方它就可见（即 一个 internal 类的构造函数只能 在相同模块内可见).
 *
 *
 * 模块：可见性修饰符 internal 意味着该成员只在相同模块内可见。更具体地说， 一个模块是编译在一起的一套 Kotlin 文件：
 *      一个 IntelliJ IDEA 模块；
 *      一个 Maven 或者 Gradle 项目；
 */

open class VisibilityA {
    open var a = 3
}

class VisibilityB : VisibilityA() {
    override var a = 3
}

fun main(args: Array<String>) {

}
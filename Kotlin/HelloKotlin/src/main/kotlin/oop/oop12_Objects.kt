package oop

import java.awt.Frame
import java.awt.Window
import java.awt.event.ActionListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.Closeable
import java.util.*

/**
 *对象
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.30 17:45
 */

/**
 * 对象： 有时候，我们需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类。
 *          Java 用匿名内部类处理这种情况。 Kotlin 用对象表达式和对象声明对这个概念稍微概括了下
 */

/**
 * 对象表达式：要创建一个继承自某个（或某些）类型的匿名类的对象
 *
 * 1，如果超类型有一个构造函数，则必须传递适当的构造函数参数给它。 多个超类型可以由跟在冒号后面的逗号分隔的列表指定
 * 2，匿名对象可以用作只在本地和私有作用域中声明的类型。如果你使用匿名对象作为公有函数的 返回类型或者用作公有属性的类型
 *      那么该函数或属性的实际类型 会是匿名对象声明的超类型，如果你没有声明任何超类型，就会是 Any。
 * 3，在匿名对象中添加的成员将无法访问。
 */

private fun testObject() {

    val window: Window = Window(Frame())

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
        }

        override fun mouseEntered(e: MouseEvent) {
        }
    })

    //如果对象是函数式 Java 接口（即具有单个抽象方法的 Java 接口）的实例， 你可以使用带接口类型前缀的lambda表达式创建它：
    val listener = ActionListener { println("clicked") }

    val com = object : Closeable {
        override fun close() {
        }
    }


    //任何时候，如果我们只需要“一个对象而已”，并不需要特殊超类型
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

/**
 * 对象声明：下面程序则为对象声明，可作为对单例模式应用。
 *
 * 对象声明不能在局部作用域（即直接嵌套在函数内部），但是它们可以嵌套到其他对象声明或非内部类中。
 *
 * */
private interface DataProvider

private object DataProviderManager {

    fun registerDataProvider(provider: DataProvider) {

    }

    val allDataProviders: Collection<DataProvider>
        get() = Collections.emptyList()
}

/**
 *伴生对象：类内部的对象声明可以用 companion 关键字标记
 *
 * 1，伴生对象的成员可通过只使用类名作为限定符来调用
 * 2，可以省略伴生对象的名称，在这种情况下将使用名称 Companion
 * 3，即使伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然是真实对象的实例成员，而且还可以实现接口
 * 4，在 JVM 平台，如果使用 @JvmStatic 注解，你可以将伴生对象的成员生成为真正的 静态方法和字段
 * 5，每个类，只能有一个伴生对象
 */

private class CompanionClass1 {
    companion object {
        val x: Int = 100
    }
}

private class CompanionClass2 {
    companion object Factory {
        fun create(): CompanionClass2 = CompanionClass2()
    }
}


private fun testCompanion() {
    CompanionClass1.x
    CompanionClass2.create()
}

/**
 *对象表达式和对象声明之间的语义差异：
 *
 *          对象表达式是在使用他们的地方立即执行（及初始化）的
 *          对象声明是在第一次被访问到时延迟初始化的
 *          伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配
 */
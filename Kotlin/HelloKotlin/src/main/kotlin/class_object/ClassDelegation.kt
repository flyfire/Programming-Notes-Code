package class_object

import kotlin.reflect.KProperty

/**
 *类的委托
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.30 23:26
 */

/**
 *类的委托：委托模式已经证明是实现继承的一个很好的替代方式， 而 Kotlin 可以零样板代码地原生支持它。
 */
private interface DelegationBase {
    fun print()
}

private class BaseImpl(val x: Int) : DelegationBase {
    override fun print() {
        println(x)
    }
}

private class ObjectA(b: DelegationBase) : DelegationBase by b


fun testClassDelegation() {
    val b = BaseImpl(10)
    ObjectA(b).print() // 输出 10
}


/**
 * 委托属性：有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好。例如包括：
 *
 *          延迟属性（lazy properties）: 其值只在首次访问时计算，
 *          可观察属性（observable properties）: 监听器会收到有关此属性变更的通知，
 *          把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。
 *
 *Kotlin 支持 委托属性：语法是： val/var <属性名>: <类型> by <表达式>
 *     在 by 后面的表达式是该 委托， 因为属性对应的 get()（和 set()）会被委托给它的 getValue() 和 setValue() 方法。
 *     属性的委托不必实现任何的接口，但是需要提供一个 getValue() 函数（和 setValue()——对于 var 属性）
 *
 * */
private class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}

private class DelegationExample {
    var p: String by Delegate()
}

fun testPropertyDelegation() {
    val delegationExample = DelegationExample()
    delegationExample.p = "Like"
    println(delegationExample.p)
}

/**
 * 标准委托：Kotlin 标准库为几种有用的委托提供了工厂方法
 *
 *1，延迟属性 Lazy
 *
 * lazy() 是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，返回的实例可以作为实现延迟属性的委托：
 * 第一次调用 get() 会执行已传递给 lazy() 的 lamda 表达式并记录结果， 后续调用 get() 只是返回记录的结果。
 *
 * 默认情况下，对于 lazy 属性的求值是同步锁的（synchronized）：该值只在一个线程中计算，并且所有线程 会看到相同的值。
 * 如果初始化委托的同步锁不是必需的，这样多个线程 可以同时执行，那么将 LazyThreadSafetyMode.PUBLICATION 作为参数传递给 lazy() 函数。
 * 而如果你确定初始化将总是发生在单个线程，那么你可以使用 LazyThreadSafetyMode.NONE 模式， 它不会有任何线程安全的保证和相关的开销。
 *
 *2，可观察属性 Observable：
 *
 * Delegates.observable() 接受两个参数：初始值和修改时处理程序（handler）。 每当我们给属性赋值时会调用该处理程序（在赋值后执行）。
 * 它有三个 参数：被赋值的属性、旧值和新值
 *
 * 如果你想能够截获一个赋值并“否决”它，就使用 vetoable() 取代 observable()。 在属性被赋新值生效之前会调用传递给 vetoable 的处理程序。
 *
 * 3，把属性储存在映射中：
 *
 * 一个常见的用例是在一个映射（map）里存储属性的值。 这经常出现在像解析 JSON 或者做其他“动态”事情的应用中。
 * 在这种情况下，你可以使用映射实例自身作为委托来实现委托属性。
 *
 * 4，局部委托属性（自 1.1 起）：
 *
 * 可以将局部变量声明为委托属性。 例如可以使一个局部变量惰性初始化
 *
 *
 * 属性委托要求：
 *
 *
 */


/**
 * 翻译规则
 */

/**
 * 提供委托
 */

/**
 * test
 */
fun main(args: Array<String>) {
    testClassDelegation()
    testPropertyDelegation()
}
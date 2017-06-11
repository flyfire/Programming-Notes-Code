package functions

import java.util.concurrent.locks.Lock
import javax.swing.tree.TreeNode

/**
 *内联函数
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.6.3 11:21
 */

/**
 * 使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。
 * 即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。
 * 但是在许多情况下通过内联化 lambda 表达式可以消除这类的开销。
 *
 *1，inline 修饰符影响函数本身和传给它的 lambda 表达式：所有这些都将内联 到调用处。
 *2，内联可能导致生成的代码增加，但是如果我们使用得当（不内联大函数），它将在 性能上有所提升，尤其是在循环中的“超多态（megamorphic）”调用处。
 *3，如果只想被（作为参数）传给一个内联函数的 lamda 表达式中只有一些被内联，你可以用 noinline 修饰符标记 一些函数参数
 *4，可以内联的 lambda 表达式只能在内联函数内部调用或者作为可内联的参数传递， 但是 noinline 的可以以任何我们喜欢的方式操作：存储在字段中、传送它等等。
 */

private inline fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body.invoke()
    } finally {
        lock.unlock()
    }
}

private inline fun noinlineSample(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    inlined.invoke()
    notInlined.invoke()
}

/**
 * 非局部返回：在 Kotlin 中，我们可以只使用一个正常的、非限定的 return 来退出一个命名或匿名函数。 这意味着要退出一个 lambda 表达式，我们必须使用一个标签，
 *                    并且 在 lambda 表达式内部禁止使用裸 return，因为 lambda 表达式不能使包含它的函数返回
 * */

private fun inlineReturn() {

    //但是如果 lambda 表达式传给的函数是内联的，该 return 也可以内联，所以它是允许的
    //这种返回（位于 lambda 表达式中，但退出包含它的函数）称为非局部返回
    fun hasZeros(ints: List<Int>): Boolean {
        ints.forEach {
            if (it == 0) return true // 从 hasZeros 返回
        }
        return false
    }
}

/**
 *一些内联函数可能调用传给它们的不是直接来自函数体、而是来自另一个执行 上下文的 lambda 表达式参数，
 * 例如来自局部对象或嵌套函数。在这种情况下，该 lambda 表达式中 也不允许非局部控制流。
 * 为了标识这种情况，该 lambda 表达式参数需要 用 crossinline 修饰符标记
 */
private inline fun crossinlineSample(crossinline body: () -> Unit) {
    val f = object : Runnable {
        override fun run() = body()
    }
}

/**
 * 具体化的类型参数：使用 reified 修饰符来限定类型参数，
 */
private fun <T> TreeNode.findParentOfType1(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}

private inline fun <reified T> TreeNode.findParentOfType2(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}

fun testfindParentOfType() {
    var treeNode: TreeNode? = null
    treeNode?.findParentOfType2<TreeNode>()
}

/**
 * 内联属性（自 1.1 起）：
 * inline 修饰符可用于没有幕后字段的属性的访问器。 你可以标注独立的属性访问器
 */

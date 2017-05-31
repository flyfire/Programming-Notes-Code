package class_object

/**
 *抽象类
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.26 23:57
 */

/**
 * 抽象类：
 * 类和其中的某些成员可以声明为 abstract。 抽象成员在本类中可以不用实现。 需要注意的是，我们并不需要用 open 标注一个抽象类或者函数——因为这不言而喻
 * */

open class AbstractA {
    open fun f() {}
}

abstract class AbstractB : AbstractA() {
    override abstract fun f()
}

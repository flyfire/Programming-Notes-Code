package feature

/**
 *类型别名
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.6.10 10:47
 */

/***
 * 类型别名：类型别名为现有类型提供替代名称。 如果类型名称太长，你可以另外引入较短的名称，并使用新的名称替代原类型名。
 *  1，typealias NodeSet = Set<Network.Node>
 *  2，类型别名不会引入新类型。 它们等效于相应的底层类型。 当你在代码中添加 typealias Predicate<T> 并使用 Predicate<Int> 时，Kotlin 编译器总是把它扩展为 (Int) -> Boolean。 因此，当你需要泛型函数类型时，你可以传递该类型的变量，反之亦然
 */

typealias StringSet = Set<String>

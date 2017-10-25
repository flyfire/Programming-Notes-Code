# Unit和Nothing

## Unit

Kotlin 里没有 void，所有函数都有返回类型：

```kotlin
fun doSomething() {
  Log.d("Hello", "World")
}
```
这个函数的返回类型是 Unit，而且所有不显式声明返回类型的函数都会返回 Unit 类型，所以写不写这个 Unit 并没有什么区别。
```kotlin
/**
 * The type with only one value: the Unit object. This type corresponds to the `void` type in Java.
 * 只有一个实例，这个实例相当于Java中的void
 */
public object Unit {
    override fun toString() = "kotlin.Unit"
}
```
从Unit的定义可以看出，Unit 是一个真正的类，继承自 Any 类，只有一个值，也就是所谓的“单例”，
因为 Unit 是一个真正的返回类型，所以我们可以把 Unit 类型的值赋给一个变量，
但是这样并没有什么作用。Unit的头String方法返回的是"Kotlin.Unit"。
```kotlin
val doSomethingResult = doSomething()
```

## Nothing

Nothing的定义为
```kotlin
/**
 * Nothing has no instances. You can use Nothing to represent "a value that never exists": for example,
 * if a function has the return type of Nothing, it means that it never returns (always throws an exception).
 * Nothing没有实例对象，可以用Nothing表示一个用于不存在的值，比如：如果一个函数返回类型的Nothing，意思就是这个函数永远
 * 不会返回(这个函数总是抛出一个异常)
 */
public class Nothing private constructor()
```

```kotlin
fun fail() {
    throw RuntimeException("Something went wrong")
}
```

这个函数的返回类型好像和上面那个一样，但是却不一样，一个只抛出了异常的函数真的有返回类型吗？
它应该什么都不返回，事实上，这个函数的返回类型就是 Nothing。

所以上面的函数可以写成：
```kotlin
fun fail(): Nothing {
    throw RuntimeException("Something went wrong")
}
```
我们不需要在代码用 Nothing 类型。Nothing 只是一个编译期的抽象概念（所以没有实例）。
只有需要将一个函数显式标记为无法完成时，才有用 Nothing 类型的必要。


## 内容引自

[文章翻译：Kotlin 中的 Nothing 和 Unit](https://zhuanlan.zhihu.com/p/26890263)
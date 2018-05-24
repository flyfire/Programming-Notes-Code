#  1 Kotlin基础

---
## 1.1 语法

### 定义变量

   - 可变使用`var`
   - 常量使用`val`
   - 成员变量必须显式指定初始值，否则需要使用`lateinit`修饰变量，lateinit不能用于基本类型和可空类型
   - const：在companion对象或顶级作用域中可以使用 `const val` 定义字面常量，支持基本类型和String
    
### 条件表达式

   - 简单的函数声明可以省略大括号，直接使用表达式
    
### 流程控制

   - `if`：if表达式取代了三元运算符，kotlin中没有三元运算符
   - `for`
   - `while`
   - `when`：kotlin没有`switch`
   - 定义标签`loop1@`，标签返回`returnloop1@`
   - `Elvis操作符?:`：示例`obj?.getSome() ?: doSomething()`，`?:`符号会在符号左边为空的情况才会进行下面的处理，不为空则不会有任何操作。

### 类型检查

   - 类型检查用`is`，比如`obj is String`
    
### 比较
    
       ==比较：
           如果作用于基本数据类型的变量，则直接比较其存储的 “值”是否相等
           对于引用类型： a==b等同于a?.equals(b) ?: (b === null)
       equals
           equals方法不能作用于基本数据类型的变量
           如果没有对equals方法进行重写，则比较的是引用类型的变量所指向的对象的地址；
           诸如String、Date等类对equals方法进行了重写，比较的是所指向的对象的内容。
        === 和 !==
            a===b当且仅当a和b指向同一个对象时返回true

### 空检查

   - 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
   
---
## 1.2 数据类型

### 数字类型

   - 支持的数字数据类型
   - 不同数字类型不能隐式转换，使用`toXXX`方法进行转换，比如`toInt()`
   - 字面量支持二进制、十进制、十六进制，不支持八进制
   - 2进制以 `0b `开头：`0b00001011`
   - 数字类型位操作：`shl、shr、ushr、and、or、xor、inv`
   - 当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性
    
###  字符类型

   - **它们不能直接当作数字**，字符字面值用单引号括起来: '1'，特殊字符可以用反斜杠转义，
   - 编码其他字符要用 Unicode 转义序列语法：'\uFF00'。
    
### 字符串类型

   - `"""`括起来的字符串会保留原来的换行格式
   - 字符串模板的使用方法：使用`${xxx}`引用其他变量
    
### 数组类型

   - 数组在 Kotlin 中使用 Array 类来表示， 它定义了 get （按照运算符重载约定这会转变为 []）和 set 函数 和 size 属性，以及一些其他有用的成员函数
   - 与 Java 不同的是，Kotlin 中数组是**不型变**的（invariant）。这意味着 Kotlin 不让我们把 `Array<String>` 赋值给 `Array<Any>`
   - Kotlin 也有无装箱开销的专门的类来表示原生类型数组: `ByteArray、 ShortArray、IntArray` 等等
   
### 使用集合

   - 有很多关于集合的方法可以直接使用，比如`setOf()、listOf()、mutableListOf<>()、listOf<>()`等
   - 使用集合的`StreamAPI`可以很方便的操作集合数据

### 使用范围
    
   - Range是一种数据结构，表示一个范围：比如1到5用`1..5`表示
   - 使用 `in` 运算符来检测某个数字是否在指定区间内
        
`..`操作符表示区间表达式，重载的是`rangeTo`函数

```kotlin
fun range(){
    for(i in 1..10 step 2){
        println(i)
    }
     for(i in 1.rangeTo(10) step 2){
            println(i)
     }
}
```

---
## 3 Kotlin默认导入的包

有多个包会默认导入到每个 Kotlin 文件中：

```
kotlin.*
kotlin.annotation.*
kotlin.collections.*
kotlin.comparisons.*
kotlin.io.*
kotlin.ranges.*
kotlin.sequences.*
kotlin.text.*
```

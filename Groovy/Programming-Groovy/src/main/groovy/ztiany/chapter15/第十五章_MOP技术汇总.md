
# 第十五章：MOP技术汇总

## 1 使用Expando创建动态的类

Groovy完全可以在运行时创建一个类，假设需要构建一个用于配置设备的应用，而对于设备一无所知，只知道它们的某些属性和配置脚本，
那就无法在编写代码时奢侈的为每个设备创建一个类，因此需要在运行时合成与这些设备打交道并完成配置的类。


Groovy的Expando类提供了动态合成类的能力。示例:`001ExpandoCreateDynamicClass.groovy`

## 2 方法委托：汇总练习

继承用来扩展一个类的行为，而委托依赖包含或者聚合的对象可以提供一个类的行为，**如果希望一个类替换另一个类，应该是用继承**，如果只是想简单的使用
一个对象，则应该选择委托，请将基础保留给is-a或者kind-of关系，大多数情况下应该使用委托。

java中的继承非常简单，使用extends关键字即可继承一个类，而实现委托则需要很多的路由方法。而Groovy可以简单的实现委托。

示例`002MethodDelegate.groovy`


## 3 MOP技术回顾

### 3.1 用于方法拦截的选项

- 如果有权修改原代码，则可以使用GroovyInterceptable，只需实现invokeMethod方法
- 如果无法修改代码，或者是Java类，则可以使用ExpandoCMetaClass或者分类，使用分类必须使用use代码块

### 3.2 用于方法注入的选项

使用分类或者ExpandoMetaClass来实现

- 分类必须使用use代码块，有所限制
- 想注入静态方法和构造器，ExpandoMetaClass是更好的选择，注意ExpandoMetaClass不是默认的metaClass的实现

### 3.3 用于方法合成的选项

- 如果有权修改代码，在原代码上使用methodMissing方法通过第一次调用时注入方法来改进性能。
- 如果无法修改代码，可以将methodMissing添加到ExpandoMetaClass中








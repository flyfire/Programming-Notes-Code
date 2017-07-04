# 第十七章：Groovy生成器

**生成器是内部的DSL**，为处理某些特定类型的问题提供了方便，如果需要使用嵌入的、层次式结构的(树、xml、json、html)等表示形式，生成器将会非常有用


# 17.1 处理xml

使用groovy.xml.MarkupBuilder生成器来创建xml文档，在生成器的上調用任意的方法或屬性時,它会根据的调用的上下文，体贴的假设我们引用的是所生成文档中的元素名：

示例`UseXmlBuilder.groovy`

# 17.2 處理Json

使用JsonBuilder可以很方便的构建json，而使用JsonSlurpe可以快速的解析json。

# 17.3 构建Swing应用

略

# 17.4 使用元编程定制生成器

对于使用嵌入的、层次式结构的(树、xml、json、html)的专门化任务，生成器提供了一个创建内部DSL的方式，当在应用中处理专门化任务时，可以检查一下是否有生成器
可以解决这个问题，如果没有则可以执行创建。

定制生成器的方式有两种：
- 利用Groovy的元编程能力，一切自己来，
- 利用Groovy提供的BuilderSupport或FactoryBuilderSupport

关于利用Groovy的元编程能力定制生成器，示例为：`UsingTodoBuilder.groovy`

# 17.5 使用BuilderSupport

如果要创建的生成器不止一个，可能要将某些方法识别代码重构到一个公共基类中，而Groovy已经提供了BuilderSupport类用于识别
节点节点结构的便捷方法，就像解析xml的sax一样，我们只需要监听节点和属性事件，示例`UseBuilderSupport.groovy`

# 17.6 使用FactoryBuilderSupport

BuilderSupport适合处理层次式结构，然而对于处理不同类型的节点并不方便，假设需要处理20种节点类型，createNode方法将会变得很复杂，
基于节点名称创建不同类型的节点会导致大量的麻烦，所以可以使用抽象工程的方法来解决问题。

FactoryBuilderSupport就是这么做到，示例`UseFactoryBuilderSupport.groovy`

FactoryBuilderSupport还有很多方便的方法，具体可以查看API文档

>本章学习了如果使用Groovy生成器，对于枯燥的处理生成XML，JSON生成器提供了执行它们的DSL语法，可以使用Groovy提供的生成器， 也可以亲自创建生成器。


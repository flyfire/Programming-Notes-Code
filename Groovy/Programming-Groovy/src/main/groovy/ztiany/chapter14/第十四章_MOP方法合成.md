

# 第十四章：MOP方法合成

添加方法的行为可以分为两类：注入和合成

**方法注入**(method inject)表示：编写代码时知道要添加到一个类或者多个类中的方法的名字，利用方法注入可以动态的向类中添加行为。
**方法合成**(method synthesis)表示：想在调用的时候动态的确认方法的行为。Groovy的invokeMethod、methodMissing、GroovyInterceptable对于方法的合成很有帮助。

合成的方法直到调用时才会作为独立的方法存在。

实现：

 - 使用methodMissing合成方法,,示例`001MethodMissingSynthesis.groovy`,需要注意的是，只有对象将控制转移给其MateClass的invokeMethod时，才会调用methodMissing
 - 使用GroovyInterceptable合成方法,示例`002GroovyInterceptableSynthesis.groovy`
 - 使用ExpandoMetaClass合成方法,示例`003ExpandoMetaClassSynthesis.groovy`,适用于已有的类
 
 
 
 
 ## 为具体的示例合成方法
 
 前面学习了如果为具体的示例注入方法，通过想具体的实例提供的metaClass也可以将方法合成到这些实例中去。示例`004SysthesizeInstance.groovy`
 
 能够基于实例的当前状态或者实例所接受到的输入创建动态的方法或者行为，为创建和实现高度动态的DSL铺平了道路。
 
 
 
 
 
 
 
 
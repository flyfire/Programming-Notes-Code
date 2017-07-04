# 第十二章：使用MOP拦截方法

在Groovy中可以轻松的实现AOP编程(aspect-oriented-programing)，比如有三种类型的建议：

- 前置建议：某个操作之前调用
- 后置建议：某个操作之后调用
- 环绕建议：代替某个操作

使用MOP可以轻松的实现这些建议的拦截器

## 1 使用GroovyInterceptable拦截方法

GroovyInterceptable拦截方法的规则上一章已经学习过了。


**使用GroovyInterceptable拦截方法适用于拦截自己编写的类**

示例`001GroovyInterceptableInterceptMethod.groovy`

## 2 使用MetaClass拦截方法

**注意：对于没有实现GroovyInterceptable的类，如果它的MetaClass上实现了invokeMethod();不管方法是否存在，都会调用到该方法**

示例`002MetaClassInterceptMethod..groovy`

 

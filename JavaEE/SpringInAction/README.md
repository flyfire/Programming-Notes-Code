# Spring In Action  学习代码

## Chapter02：Bean 的装配

- 在 XML 中进行显式配置。
- 在 Java 中进行显式配置。
- 隐式的 bean 发现机制和自动装配。

## Chapter03：高级装配

- 环境与profile
- 条件化的 bean
- 处理自动装配的歧义性
- bean的作用域
    - 用会话和请求作用域
- 运行时值注入
    - 占位符
    - SpEL运算符

## Chapter04：AOP

- 注解方式
- xml配置方式
- 为Aspect进行注入(异常)

## Chapter05：构建 Spring Web 应用

Spittr应用简介：
>为了实现在线社交的功能，我们将要构建一个简单的微博（microblogging）应用。在很多方面，我们所构建的应用与最早的微博应用Twitter很类似。
在这个过程中，我们会添加一些小的变化。当然，我们要使用Spring技术来构建这个应用。因为从Twitter借鉴了灵感并且通过Spring来进行实现，
所以它就有了一个名字：Spitter。再进一步，应用网站命名中流行的模式，如Flickr，我们去掉字母e，这样的话，我们就将这个应用称为Spittr。
这个名称也有助于区分应用名称和领域类型，因为我们将会创建一个名为Spitter的领域类。

>Spittr应用有两个基本的领域概念：Spitter（应用的用户）和Spittle（用户发布的简短状态更新）。
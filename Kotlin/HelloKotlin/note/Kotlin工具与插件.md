##  全开放编译器插件

Kotlin 有类及其默认为 final 的成员，这使得像 Spring AOP 这样需要类为 open 的框架和库用起来很不方便，all-open 编译器插件会适配 Kotlin 以满足那些框架的需求，
并使用指定的注解标注类而其成员无需显式使用 open 关键字打开。当使用 Spring 时，不需要打开所有的类，而只需要使用特定的注解标注，如 `@Configuration` 或 `@Service`。
All-open 允许指定这些注解。

使用方式：
```groovy
buildscript {
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-allopen:$kotlin_version"
    }
}

apply plugin: "kotlin-allopen"

//在脚本中指定那个包下的类应用all open
allOpen {
    annotation("com.my.Annotation")
}
```
Kotlin Android Sample

# 1 Kotlin

todo study

# 2 Anko库

## 2.1 Common库

- 快速的构建Dialog
- 使用intentFor方面的打开Activity
- 更加方便的Log库
- `applyRecursively()`方法遍历View树
- `0xff0000.opaque`颜色值方法
- 各种Dimensions的转换

todo study

## 2.2 协程

- `asReference()`用于避免内存泄漏
- `bg{}`用于在异步线程执行任务

todo study

## 2.3 Anko Layouts

Anko Layouts允许使用DSL的方式来创建布局，切使用起来非常方便.

### 基本功能

1. Layouts可扩展，只需要在ViewManager上添加对应View的扩展
2. 可以使用Gradle继承Anko所有的库，也可以单独继承Anko的Layouts库
3. 使用AnkoComponent可以支持布局的预览，当然这需要Anko的插件支持，且插件只支持AndroidStudio2.4以上
4. Layouts的Listeners支持使用协程
5. Listeners支持自定义coroutine context
6. 在内部类中，使用' ctx'就可以引用外部类的this引用(Activity中)
7. Anko Layouts的DSL支持include xml布局
8. Anko的插件有两个功能
    - DSL布局预览
    - xml布局转换为DSL布局
9. 在Activity中使用DSL布局，不需要再调用setContentView方法，框架自动完成

### DSL的原理：

1. Anko库给Activity添加了很多的扩展方法，比如`verticalLayout`
2. 在Fragment中需要先调用`UI`方法获取AnkoContext才能进行DSL布局编程

todo study


## 2.4 Anko SQLite

todo study
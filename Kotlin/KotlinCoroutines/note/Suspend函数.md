## Suspend函数

在Kotlin中，Suspend函数表示一个可以被暂停的函数，运行在协程中，Suspend函数是协程的核心概念之一。
使用协程可以像写单线程方法一样来编写各种异步操作，这些都归功于Suspend函数。

### 异步回调

在异步回调的模型中，当开启一个异步任务去获取数据时，有两种方式去获取结果：

- 使用异步回调(Callback)
- 使用阻塞获取(Future.get())

### Suspend函数的暂停与恢复

在协程中，可以实现异步返回，由于Suspend函数是可以被暂停的。被暂停的函数在恢复执行后，回回到之前的暂停点继续执行。
而且，任何一个恢复都可以切换调度线程。看下面代码：

```kotlin
private fun asyncReturnSample2() = runBlocking {
    //1 在主线程启动协程
    println("1 主线程开启协程")
    println("2 在主线程协程中开启新的协程异步查询数据")
    //2 开启异步协程去查询数据
    val deferred = async(CommonPool) { queryDatabase() }
    println("3 主携程继续执行.....1.")
    //3 获取数据
    launch(coroutineContext) {
        val data = deferred.await()
        println("5 得到数据回到主线程协程$data")
    }
    println("4 主携程继续执行.....2.")
}

/**执行耗时操作*/
private fun queryDatabase(): String {
    Thread.sleep(2000)
    return "Data"
}
```

打印结果为：

        1 主线程开启协程
        2 在主线程协程中开启新的协程异步查询数据
        3 主携程继续执行.....1.
        4 主携程继续执行.....2.
        5 得到数据回到主线程协程Data


## 理解暂停点与Continuation 

一个协程的执行经常是断断续续的: 执行一段, 挂起来, 再执行一段, 再挂起来, ...
每个挂起的地方是一个suspension point, 每一小段执行是一个Continuation.
协程的执行流被它的 "suspension point" 分割成了很多个 "Continuation" .

Continuation：
```kotlin
public interface Continuation<in T> {
    //调度当前挂起段的协程上下文
    public val context: CoroutineContext

    //挂起段执行完毕，把数据传递到下一段
    public fun resume(value: T)
    //挂起段执行完毕，把异常传递到下一段
    public fun resumeWithException(exception: Throwable)
}
```
协程之间通过Continuation协作运行，挂起段由Continuation中的CoroutineContext调度。


## CoroutinesLibrary函数说明

### suspendCoroutine函数

```kotlin
public inline suspend fun <T> suspendCoroutine(crossinline block: (Continuation<T>) -> Unit): T =
        suspendCoroutineOrReturn { c: Continuation<T> ->
            val safe = SafeContinuation(c)
            block(safe)
            safe.getResult()
        }
```
suspendCoroutine是一个suspend函数，所以它只能在协程中调用，
suspendCoroutine的作用是将当前执行流挂起, 在适合的时机再将协程恢复执行。
suspendCoroutine方法接受一个lambda，这个lambda的返回值就是该函数的返回值，
block有一个参数continuation，当block执行完毕后，调用continuation的resume方法则返回结果，
这个结果就是suspendCoroutine的返回值，然后Kotlin回切换回之前的挂起点继续调度。


## 协程API

Kotlin官方对协程提供的三种级别的能力支持, 分别是: 

- 最底层的语言层
- 中间层标准库(kotlin-stdlib)
- 最上层应用层(kotlinx.coroutines).

### 应用层

它的实现在kotlinx.coroutines里面，比如常用的launch方法, async方法等

### 标准库

标准库仅仅提供了少量创建协程的方法：

- createCoroutine()
- startCoroutine()
- suspendCoroutine()

### 语言层

语言本身主要提供了对suspend关键字的支持, Kotlin编译器会对suspend修饰的方法或lambda特殊处理, 生成一些中间类和逻辑代码。
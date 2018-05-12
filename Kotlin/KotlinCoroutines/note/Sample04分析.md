# 流程分析

##  FilePath

是一个CoroutinesContext，继承自AbstractCoroutineContextElement

## 启动协程

```kotlin
private fun <T> launch(receiver: T, context: CoroutineContext, block: suspend T.() -> Unit) = block.startCoroutine(receiver, StandaloneCoroutine(context))

private fun launchWithContext(context: CoroutineContext, block: suspend CoroutineContext.() -> Unit) = launch(context, context, block)
```
这两个方法用于启动协程，launchWithContext是作为更加方便的实现，把CoroutineContext通过作为receiver

## 全部流程

suspend函数的扩展方法startCoroutine用于启动协程，其接受的参数有：

1：StandaloneCoroutine是一个Coroutine的简单默认的实现

2：receiver，其在方法中的定义为

```
public fun <R, T> (suspend R.() -> T).startCoroutine(
        receiver: R,
        completion: Continuation<T>
){
    createCoroutineUnchecked(receiver, completion).resume(Unit)
}
```

createCoroutineUnchecked方法分析
```kotlin
public fun <R, T> (suspend R.() -> T).createCoroutineUnchecked(
        receiver: R,
        completion: Continuation<T>
): Continuation<Unit> =
        //这里this引用的上面的block，即调用startCoroutine方法的block
        //经过debug发现，block指向CoroutineImpl
        if (this !is kotlin.coroutines.experimental.jvm.internal.CoroutineImpl)
            buildContinuationByInvokeCall(completion) {
                @Suppress("UNCHECKED_CAST")
                (this as Function2<R, Continuation<T>, Any?>).invoke(receiver, completion)
            }
        else
            //所以方法会走这里
            (this.create(receiver, completion) as kotlin.coroutines.experimental.jvm.internal.CoroutineImpl).facade
```
上面block是main函数中调用launchWithContext中传入的代码块，默认为CoroutineImpl子类，create方法返回的类型转换为facade，
facade的类型是Continuation<Any?，createCoroutineUnchecked方法返回facade后，会调用其resume方法，传入的值是Unit。

需要注意的是facade定义了自己的get方法：
```kotlin
    val facade: Continuation<Any?> get() {
         //注意，这里的get方法传入的this也就是把本身CoroutineImpl
        if (_facade == null) _facade = interceptContinuationIfNeeded(_context!!, this)
        return _facade!!
    }
```
默认_facade是为null的，所以为调用` interceptContinuationIfNeeded(_context!!, this)`方法

```kotlin
//context是在main方法中传入的协程上下文，而此处的continuation是上面分析的CoroutineImpl，即main方法中传入的block
internal fun <T> interceptContinuationIfNeeded(context: CoroutineContext, continuation: Continuation<T>) 
        = context[ContinuationInterceptor]?.interceptContinuation(continuation) ?: continuation
```
这里通过ContinuationInterceptor获取拦截器，如果存在拦截器则调用拦截器的interceptContinuation方法，由于我们在main方法中传入的上下文是
FilePath+CommonPoll的组合上下文，CommonPoll是Poll的子类，而Poll实现了拦截器，所以这里能获取到拦截器PoolContinuation
```kotlin
open class Pool(val pool: ForkJoinPool) : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    //interceptContinuation方法返回的是PoolContinuation调度
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =

            PoolContinuation(pool, //下面这段代码是要查找其他拦截器，并保证能调用它们的拦截方法
                    //continuation 就是 cont
                    continuation.context.fold(continuation, { cont, element ->
                        if (element != this@Pool && element is ContinuationInterceptor)//如果element不是当前Pool并且是一个拦截器，那么就拦截
                            element.interceptContinuation(cont)
                        else//否则不拦截，返回原来的cont
                            cont
                    }))
}
```

所以，createCoroutineUnchecked最终调用的resume方法调用的是PoolContinuation的resume方法

PoolContinuation的resume方法的实现如下：

```kotlin
    override fun resume(value: T) {
         //判断执行的线程是否在PoolContinuation的调度池中
        if (isPoolThread()) {
            continuation.resume(value)
        } else
            //这里不是在调度池中，那认为转换到调度池中执行
            pool.execute {
                continuation.resume(value)
            }
    }
```
需要注意的是上面的continuation实际类型就是main方法中传入的block，而block的实际类型是CoroutineImpl，而当调用了resume方法后，此时执行线程以及是在线程池中了。
然后来看一下CoroutineImpl的resume方法
```kotlin

    override fun resume(value: Any?) {
        processBareContinuationResume(completion!!) {
            doResume(value, null)
        }
    }
 
 internal inline fun processBareContinuationResume(completion: Continuation<*>, block: () -> Any?) {
     try {
         val result = block()//调用doResume方法
         if (result !== COROUTINE_SUSPENDED) {//result是Unit，不是COROUTINE_SUSPENDED
             @Suppress("UNCHECKED_CAST")
             (completion as Continuation<Any?>).resume(result)//调用CoroutineImpl内部completion的resume方法
         }
     } catch (t: Throwable) {
         completion.resumeWithException(t)
     }
 }
```
根据debug发现，CoroutineImpl的doResume方法将会调用main方法中block块
```kotlin
        println("in coroutine. Before suspend.")
        val result: String = calcMd5(this[FilePath]!!.path).await()
        println("in coroutine. After suspend. result = $result")
        
  //calcMd5方法返回的是一个CompletableFuture，注意CompletableFuture表示一个异步任务，而这个异步任务由CompletableFuture.supplyAsync提交的的任务生成
 private fun calcMd5(path: String): CompletableFuture<String> = CompletableFuture.supplyAsync {
     println("calc md5 for $path.")
     //暂时用这个模拟耗时
     Thread.sleep(1000)
     //假设这就是我们计算得到的 MD5 值
     System.currentTimeMillis().toString()
 }
 
 //await方法是给CompletableFuture添加的扩展方法，
 //内部逻辑调用suspendCoroutine函数的返回值
 private suspend fun <T> CompletableFuture<T>.await(): T {
     return suspendCoroutine {
         continuation ->
         println(Thread.currentThread())
         whenComplete { result, e ->
             if (e == null) continuation.resume(result)
             else continuation.resumeWithException(e)
         }
     }
 }
 //suspendCoroutine源码
 public inline suspend fun <T> suspendCoroutine(crossinline block: (Continuation<T>) -> Unit): T =
         suspendCoroutineOrReturn { c: Continuation<T> ->
             val safe = SafeContinuation(c)//创建一个SafeContinuation
             block(safe)//调用await函数中从suspendCoroutine传入的代码块
             safe.getResult()//返回值
         }

```


# 总结

block值main中通过launchWithContext提交的block

- FilePath+CommonPool组合上下文
- 启动任务block,block的真实类型被是CoroutineImpl
- 由于CommonPool是拦截器，所以block被拦截然后在CommonPool的内部Poll中执行
- 在block中把计算任务交给了CompletableFuture，然后返回一个CompletableFuture示例
- CompletableFuture的扩展方法await等待任务执行完毕，调用continuation的resume方法传会执行结果
- **最后我发现我总结不对......,线程调度完全无规律可循**。其实这才是正确的，因为**协程不是线程**，
不要去猜测协程的运行线程，这是不确定的，每一次运行都不确定，协程只会最大化的去利用线程。


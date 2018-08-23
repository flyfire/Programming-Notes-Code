package understanding

import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine


fun main(args: Array<String>) {
    sample()
}

private fun sample() {
    println("1 before coroutine")

    launchWithContext(FilePath("test.zip") + CommonPool) {
        //this在代码块中指向最左边的接收者，这里就是协程上下文
        println("3 in coroutine. Before suspend.")
        println("4 main block -- " + Thread.currentThread())
        val result: String = calcMd5(this[FilePath]!!.path).await()
        println("5 in coroutine. After suspend. result = $result")
    }

    println("2 after coroutine")
    CommonPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
}

private fun calcMd5(path: String): CompletableFuture<String> = CompletableFuture.supplyAsync {
    println("           calc md5 for $path.")
    //暂时用这个模拟耗时
    Thread.sleep(1000)
    //假设这就是我们计算得到的 MD5 值
    System.currentTimeMillis().toString()
}

private suspend fun <T> CompletableFuture<T>.await(): T {
    return suspendCoroutine { continuation ->
        println("           suspendCoroutine-- ")
        println(continuation)//SafeContinuation
        //实现whenComplete方法，whenComplete方法表示当CompletableFuture被执行完后调用
        whenComplete { result, e ->
            println("           whenComplete -- " + Thread.currentThread())
            if (e == null) {
                continuation.resume(result)
            } else {
                continuation.resumeWithException(e)
            }
        }
    }
}


/** 带有 Receiver 的协程：不仅可以传入一个独立的函数作为协程的代码块，还可以将一个对象的方法传入，也就是说，我们完全可以在启动协程的时候为它指定一个 receiver*/
private fun <T> launch(receiver: T, context: CoroutineContext, block: suspend T.() -> Unit) =
        block.startCoroutine(receiver, StandaloneCoroutine(context))

/** 函数用于在执行的协程上下文中调度block，suspend CoroutineContext.() -> Unit表示带接收者的函数字面值，即该函数由字面值对象调用，在函数体内访问接收者对象的成员*/
private fun launchWithContext(context: CoroutineContext, block: suspend CoroutineContext.() -> Unit) {
    launch(context, context, block)
}

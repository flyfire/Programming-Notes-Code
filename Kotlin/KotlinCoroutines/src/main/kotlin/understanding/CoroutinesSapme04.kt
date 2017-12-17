package understanding

import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine

/*
打印顺序：

before coroutine
after coroutine
in coroutine. Before suspend.
in suspend block.
calc md5 for test.zip.
after resume.
in coroutine. After suspend. result = 1499929744853
resume: kotlin.Unit
 */

fun main(args: Array<String>) {
    println("before coroutine")

    launchWithContext(FilePath("test.zip") + CommonPool) {
        //this在代码块中指向最左边的接收者，这里就是协程上下文
        println("in coroutine. Before suspend.")
        println("main block -- " + Thread.currentThread())
        val result: String = calcMd5(this[FilePath]!!.path).await()
        println("in coroutine. After suspend. result = $result")
    }

    println("after coroutine")
    CommonPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
}

private fun calcMd5(path: String): CompletableFuture<String> = CompletableFuture.supplyAsync {
    println("calc md5 for $path.")
    println("calcMd5 -- "+Thread.currentThread())
    //暂时用这个模拟耗时
    Thread.sleep(1000)
    //假设这就是我们计算得到的 MD5 值
    System.currentTimeMillis().toString()
}

private suspend fun <T> CompletableFuture<T>.await(): T {
    return suspendCoroutine {
        continuation ->
        println("suspendCoroutine-- "+Thread.currentThread())
        println(continuation)//SafeContinuation
        //实现whenComplete方法，whenComplete方法表示当CompletableFuture被执行完后调用
        whenComplete { result, e ->
            println("whenComplete -- "+Thread.currentThread())
            if (e == null) continuation.resume(result)
            else continuation.resumeWithException(e)
        }
    }
}


private fun <T> launch(receiver: T, context: CoroutineContext, block: suspend T.() -> Unit) =
    block.startCoroutine(receiver, StandaloneCoroutine(context))


private fun launchWithContext(context: CoroutineContext, block: suspend CoroutineContext.() -> Unit) =
        launch(context, context, block)


//test

private fun (Int.() -> String).mt() = {
    //this 指向 (Int.() -> String)
    this.invoke(3)
}

private fun abc(a: Int.() -> String) {
    //于是函数a可以调用函数mt
    a.mt()
//    a.invoke()，此时调用invoke需要一个Int参数
}

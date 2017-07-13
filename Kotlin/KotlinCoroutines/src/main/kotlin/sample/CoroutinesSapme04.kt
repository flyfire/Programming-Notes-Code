package sample

import java.util.concurrent.CompletableFuture
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinWorkerThread
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.*

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
        val result: String = calcMd5(this[FilePath]!!.path).await()
        println("in coroutine. After suspend. result = $result")
    }

    println("after coroutine")
    CommonPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
}

private fun calcMd5(path: String): CompletableFuture<String> = CompletableFuture.supplyAsync {
    println("calc md5 for $path.")
    //暂时用这个模拟耗时
    Thread.sleep(1000)
    //假设这就是我们计算得到的 MD5 值
    System.currentTimeMillis().toString()
}

suspend fun <T> CompletableFuture<T>.await(): T {
    return suspendCoroutine {
        continuation ->
        whenComplete { result, e ->
            if (e == null) continuation.resume(result)
            else continuation.resumeWithException(e)
        }
    }
}


private fun <T> launch(receiver: T, context: CoroutineContext, block: suspend T.() -> Unit) = block.startCoroutine(receiver, StandaloneCoroutine(context))

private fun launchWithContext(context: CoroutineContext, block: suspend CoroutineContext.() -> Unit) = launch(context, context, block)
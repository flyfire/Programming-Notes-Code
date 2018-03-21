package sample

import java.util.concurrent.Executors

private val pool by lazy {
    Executors.newCachedThreadPool()
}

/**
 * 用于异步执行block
 */
class AsyncTask(val block: () -> Unit) {
    fun execute() = pool.execute(block)
}
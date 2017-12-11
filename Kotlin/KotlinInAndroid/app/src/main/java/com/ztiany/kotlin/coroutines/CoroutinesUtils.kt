package com.ztiany.kotlin.coroutines

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 * http://www.jianshu.com/p/d4a8358e843e
 *<pre>
 *     DeferredCoroutine任务创建后会立即启动
 *     LazyDeferredCoroutine任务创建后new的状态，要等用户调用 start() or join() or await()去启动他
 *</pre>
 *@author Ztiany
 *      Email: ztiany3@gmail.com
 *      Date : 2017-07-21 00:37
 */
fun <T> startCoroutine(block: suspend CoroutineScope.() -> T, uiBlock: suspend (T) -> Unit): Deferred<T> {
    val deferred = async(CommonPool, false, block)
    launch(UI) {
        uiBlock(deferred.await())
    }
    return deferred
}

package com.ztiany.kotlin.coroutines

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 *@author Ztiany
 *      Email: ztiany3@gmail.com
 *      Date : 2017-07-21 00:37
 */

fun <T> startCoroutine(block: suspend CoroutineScope.() -> T, uiBlock: suspend (T) -> Unit): Deferred<T> {
    val deferred = async(CommonPool,false,block)
    launch(UI){
        uiBlock(deferred.await())
    }
    return deferred
}

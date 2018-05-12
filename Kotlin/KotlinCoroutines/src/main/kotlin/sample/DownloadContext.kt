package sample

import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.CoroutineContext

/**
 * 具体的协程上下文DownloadContext，其携带一个URL，用于指定下载源，
 * 携带资源正是协程的一个功能，其携带的资源由其定义的key获取
 */
class DownloadContext(val url: String): AbstractCoroutineContextElement(Key){
    companion object Key: CoroutineContext.Key<DownloadContext>
}
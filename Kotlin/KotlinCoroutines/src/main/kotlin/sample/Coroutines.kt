package sample

import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine


fun 我要开始协程啦(context: CoroutineContext = EmptyCoroutineContext, block: suspend () -> Unit) {
    val c = ContextContinuation(context + AsyncContext())
    block.startCoroutine(c)
}

suspend fun <T> 我要开始耗时操作了(block: CoroutineContext.() -> T) =
//暂停当前协程， 转而去调用black
        suspendCoroutine<T> { continuation ->

            log("异步任务开始前")

            AsyncTask {
                try {
                    //调用resume会把结果传回到上面挂起的协程中
                    continuation.resume(block(continuation.context))
                } catch (e: Exception) {
                    continuation.resumeWithException(e)
                }
            }.execute()

        }

fun 我要开始加载图片啦(url: String): ByteArray {
    log("异步任务开始前")
    log("耗时操作，下载图片")
    val responseBody = HttpService.service.getLogo(url).execute()
    if (responseBody.isSuccessful) {
        responseBody.body()?.byteStream()?.readBytes()?.let {
            return it
        }
        throw HttpException(HttpError.HTTP_ERROR_NO_DATA)
    } else {
        throw HttpException(responseBody.code())
    }
}
package sample

import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.ContinuationInterceptor

/**
 * 异步协程上下文：协程上下文可以实现ContinuationInterceptor接口，从而获取协程调度拦截的功能。
 */
class AsyncContext : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        /*步骤分解：
         *  1 异步协程需要在UI线程返回结果，所以用UiCotinuationWrapper包装传入的Continuation
         *  2 但是不能就这么简单的返回包装的UiCotinuationWrapper，每一个continuation都持有一个
         *  协程上下文，而协程上下文还可以是多个上下文的组合，应该给予每一个上下文拦截continuation的机会
         *  所以调用continuation.context.fold方法遍历，在传入的闭包中让每一个ContinuationInterceptor对continuation进行拦截
         */
        return UiCotinuationWrapper(continuation.context.fold(continuation) { continuation, element ->
            if (element != this && element is ContinuationInterceptor) {
                element.interceptContinuation(continuation)
            } else continuation
        })
    }

}
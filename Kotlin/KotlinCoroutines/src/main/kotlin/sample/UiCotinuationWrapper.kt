package sample

import javax.swing.SwingUtilities
import kotlin.coroutines.experimental.Continuation

/**
 * UiCotinuationWrapper用于包装已有原有的Continuation，为其添加在UI线程返回结果的功能
 */
class UiCotinuationWrapper<T>(val continuation: Continuation<T>) : Continuation<T> {

    override val context = continuation.context

    override fun resume(value: T) {
        SwingUtilities.invokeLater { continuation.resume(value) }
    }

    override fun resumeWithException(exception: Throwable) {
        SwingUtilities.invokeLater { continuation.resumeWithException(exception) }
    }

}

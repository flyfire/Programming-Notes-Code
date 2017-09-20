package com.ztiany.progress.manager;

import android.os.Handler;
import android.os.Looper;

import com.mondial.base.data.net.progress.ProgressInterceptor.InterceptorProgressListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.OkHttpClient;

/**
 * https://github.com/JessYanCoding/ProgressManager
 */
public final class ProgressManager {

    ///////////////////////////////////////////////////////////////////////////
    // default
    ///////////////////////////////////////////////////////////////////////////

    private static volatile ProgressManager mProgressManager;

    public static ProgressManager getDefault() {
        if (mProgressManager == null) {
            synchronized (ProgressManager.class) {
                if (mProgressManager == null) {
                    mProgressManager = new ProgressManager();
                }
            }
        }
        return mProgressManager;
    }

    ///////////////////////////////////////////////////////////////////////////
    //instance
    ///////////////////////////////////////////////////////////////////////////

    private final Map<String, ProgressListener> mRequestListeners = new WeakHashMap<>();
    private final Map<String, ProgressListener> mResponseListeners = new WeakHashMap<>();

    private final Map<String, List<ProgressListener>> mMultiRequestListeners = new WeakHashMap<>();
    private final Map<String, List<ProgressListener>> mMultiResponseListeners = new WeakHashMap<>();

    private final ProgressInterceptor mInterceptor;
    private final Handler mHandler;

    public ProgressManager() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mInterceptor = new ProgressInterceptor(mInterceptorProgressListener);
    }

    public OkHttpClient.Builder withProgress(OkHttpClient.Builder builder) {
        return builder.addNetworkInterceptor(mInterceptor);
    }

    /**
     * 设置每次被调用的间隔时间,单位毫秒
     */
    public void setRefreshTime(int refreshTime) {
        mInterceptor.setRefreshTime(refreshTime);
    }

    @SuppressWarnings("all")
    private InterceptorProgressListener mInterceptorProgressListener = new InterceptorProgressListener() {

        @Override
        public void onRequestProgress(String url, ProgressInfo progressInfo) {
            notifyProgress(mRequestListeners, url, progressInfo);
            notifyMultiProgress(mMultiRequestListeners, url, progressInfo);
        }

        @Override
        public void onRequestError(String url, long id, Exception e) {
            notifyError(mRequestListeners, url, id, e);
            notifyMultiError(mMultiRequestListeners, url, id, e);
        }

        @Override
        public void onResponseProgress(String url, ProgressInfo progressInfo) {
            notifyProgress(mResponseListeners, url, progressInfo);
            notifyMultiProgress(mMultiResponseListeners, url, progressInfo);
        }

        @Override
        public void onResponseError(String url, long id, Exception e) {
            notifyError(mResponseListeners, url, id, e);
            notifyMultiError(mMultiResponseListeners, url, id, e);
        }
    };

    ///////////////////////////////////////////////////////////////////////////
    // notify
    ///////////////////////////////////////////////////////////////////////////

    private void notifyError(Map<String, ProgressListener> listenerMap, String url, final long id, final Exception e) {
        ProgressListener progressListener = listenerMap.get(url);
        if (progressListener != null) {
            mHandler.post(() -> progressListener.onError(id, e));
            listenerMap.remove(url);
        }
    }

    private void notifyMultiError(Map<String, List<ProgressListener>> listenerMap, String url, final long id, final Exception e) {
        List<ProgressListener> progressListenerList = listenerMap.get(url);
        if (progressListenerList != null) {
            mHandler.post(() -> {
                ProgressListener[] progressListeners = progressListenerList.toArray(new ProgressListener[progressListenerList.size()]);
                for (ProgressListener progressListener : progressListeners) {
                    progressListener.onError(id, e);
                }
            });
        }
    }

    private void notifyProgress(Map<String, ProgressListener> listenerMap, String url, ProgressInfo progressInfo) {
        ProgressListener progressListener = listenerMap.get(url);
        if (progressListener != null) {
            mHandler.post(() -> progressListener.onProgress(progressInfo));
            listenerMap.remove(url);
        }
    }

    private void notifyMultiProgress(Map<String, List<ProgressListener>> listenerMap, String url, ProgressInfo progressInfo) {
        List<ProgressListener> progressListenerList = listenerMap.get(url);
        if (progressListenerList != null) {
            mHandler.post(() -> {
                ProgressListener[] progressListeners = progressListenerList.toArray(new ProgressListener[progressListenerList.size()]);
                for (ProgressListener progressListener : progressListeners) {
                    progressListener.onProgress(progressInfo);
                }
            });
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // add listener
    ///////////////////////////////////////////////////////////////////////////

    public void setRequestListener(String url, ProgressListener listener) {
        synchronized (ProgressManager.class) {
            mRequestListeners.put(url, listener);
        }
    }

    public void setResponseListener(String url, ProgressListener listener) {
        synchronized (ProgressManager.class) {
            mResponseListeners.put(url, listener);
        }
    }

    public void addRequestListener(String url, ProgressListener listener) {
        List<ProgressListener> progressListeners;
        synchronized (ProgressManager.class) {
            progressListeners = mMultiRequestListeners.get(url);
            if (progressListeners == null) {
                progressListeners = new LinkedList<>();
                mMultiRequestListeners.put(url, progressListeners);
            }
        }
        progressListeners.add(listener);
    }

    public void addResponseListener(String url, ProgressListener listener) {
        List<ProgressListener> progressListeners;
        synchronized (ProgressManager.class) {
            progressListeners = mMultiResponseListeners.get(url);
            if (progressListeners == null) {
                progressListeners = new LinkedList<>();
                mMultiResponseListeners.put(url, progressListeners);
            }
        }
        progressListeners.add(listener);
    }
}

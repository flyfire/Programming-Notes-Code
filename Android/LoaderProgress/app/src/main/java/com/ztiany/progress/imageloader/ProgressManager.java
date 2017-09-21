package com.ztiany.progress.imageloader;

import android.os.Handler;
import android.os.Looper;

import com.ztiany.progress.manager.ProgressInfo;
import com.ztiany.progress.manager.ProgressListener;
import com.ztiany.progress.manager.ResponseProgressInterceptor;
import com.ztiany.progress.manager.UrlProgressListener;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.OkHttpClient;

public final class ProgressManager {

    ///////////////////////////////////////////////////////////////////////////
    // default
    ///////////////////////////////////////////////////////////////////////////

    private static volatile ProgressManager mProgressManager;

    public static ProgressManager getInstance() {
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

    private final Map<String, ProgressListener> mResponseListeners;
    private final ResponseProgressInterceptor mInterceptor;
    private final Handler mHandler;

    private ProgressManager() {
        this.mHandler = new Handler(Looper.getMainLooper());
        //https://stackoverflow.com/questions/2255950/is-there-java-util-concurrent-equivalent-for-weakhashmap
        //A synchronized WeakHashMap may be constructed using the Collections.synchronizedMap method.
        mResponseListeners = Collections.synchronizedMap(new WeakHashMap<String, ProgressListener>());
        this.mInterceptor = new ResponseProgressInterceptor(mInterceptorProgressListener);
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
    private UrlProgressListener mInterceptorProgressListener = new UrlProgressListener() {
        @Override
        public void onProgress(String url, ProgressInfo progressInfo) {
            notifyProgress(mResponseListeners, url, progressInfo);
        }

        @Override
        public void onError(String url, Exception e) {
            notifyError(mResponseListeners, url, e);
        }
    };

    ///////////////////////////////////////////////////////////////////////////
    // notify
    ///////////////////////////////////////////////////////////////////////////
    private void notifyError(Map<String, ProgressListener> listenerMap, String url, final Exception e) {
        final ProgressListener progressListener = listenerMap.get(url);
        if (progressListener != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressListener.onError(e);
                }
            });
            listenerMap.remove(url);
        }
    }

    private void notifyProgress(Map<String, ProgressListener> listenerMap, String url, final ProgressInfo progressInfo) {
        final ProgressListener progressListener = listenerMap.get(url);
        if (progressListener != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressListener.onProgress(progressInfo);
                }
            });
            listenerMap.remove(url);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // add listener
    ///////////////////////////////////////////////////////////////////////////
    public void setLoadListener(String url, ProgressListener listener) {
        synchronized (ProgressManager.class) {
            mResponseListeners.put(url, listener);
        }
    }
}

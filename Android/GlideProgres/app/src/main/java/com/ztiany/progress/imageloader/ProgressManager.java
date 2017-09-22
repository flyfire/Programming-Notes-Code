package com.ztiany.progress.imageloader;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

final class ProgressManager {

    private static volatile ProgressManager mProgressManager;
    private static final int DEFAULT_REFRESH_TIME = 200;

    private int mRefreshTime = DEFAULT_REFRESH_TIME;//进度刷新时间(单位ms),避免高频率调用
    private final Map<String, LoadListener> mResponseListeners;
    private final ResponseProgressInterceptor mInterceptor;
    private final Handler mHandler;

    private void notifyProgress(String url, final long contentLength, final long currentBytes, final float percent, final boolean isFinish) {
        final LoadListener loadListener = mResponseListeners.get(url);
        if (loadListener != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    loadListener.onProgress(contentLength, currentBytes, percent, isFinish);
                }
            });
        }
    }

    static ProgressManager getInstance() {
        if (mProgressManager == null) {
            synchronized (ProgressManager.class) {
                if (mProgressManager == null) {
                    mProgressManager = new ProgressManager();
                }
            }
        }
        return mProgressManager;
    }

    private ProgressManager() {
        this.mHandler = new Handler(Looper.getMainLooper());
        //https://stackoverflow.com/questions/2255950/is-there-java-util-concurrent-equivalent-for-weakhashmap
        //A synchronized WeakHashMap may be constructed using the Collections.synchronizedMap method.
        mResponseListeners = Collections.synchronizedMap(new WeakHashMap<String, LoadListener>());
        this.mInterceptor = new ResponseProgressInterceptor();
    }

    OkHttpClient.Builder withProgress(OkHttpClient.Builder builder) {
        return builder.addNetworkInterceptor(mInterceptor);
    }

    /**
     * 设置每次被调用的间隔时间,单位毫秒
     */
    void setRefreshTime(int refreshTime) {
        mRefreshTime = refreshTime;
    }

    ///////////////////////////////////////////////////////////////////////////
    //listener
    ///////////////////////////////////////////////////////////////////////////
    void setLoadListener(String url, LoadListener listener) {
        mResponseListeners.put(url, listener);
    }

    void removeListener(String url) {
        mResponseListeners.remove(url);
    }

    ///////////////////////////////////////////////////////////////////////////
    //ResponseProgressInterceptor
    ///////////////////////////////////////////////////////////////////////////
    private class ResponseProgressInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            return wrapResponseBody(chain.proceed(chain.request()));
        }

        private Response wrapResponseBody(Response response) {
            if (response == null || response.body() == null) {
                return response;
            }
            final String key = response.request().url().toString();
            ProgressResponseBody progressResponseBody = new ProgressResponseBody(response.body(), mRefreshTime) {
                @Override
                void onProgress(long contentLength, long currentBytes, float percent, boolean isFinish) {
                    notifyProgress(key, contentLength, currentBytes, percent, isFinish);
                }
            };
            return response.newBuilder()
                    .body(progressResponseBody).build();
        }
    }

}

package com.ztiany.progress.manager;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-20 15:39
 */
public class RequestProgressInterceptor implements Interceptor {

    private static final int DEFAULT_REFRESH_TIME = 150;
    private final UrlProgressListener mInterceptorProgressListener;
    private int mRefreshTime = DEFAULT_REFRESH_TIME;//进度刷新时间(单位ms),避免高频率调用

    public RequestProgressInterceptor(UrlProgressListener interceptorProgressListener) {
        mInterceptorProgressListener = interceptorProgressListener;
        if (mInterceptorProgressListener == null) {
            throw new NullPointerException();
        }
    }

    public void setRefreshTime(int refreshTime) {
        mRefreshTime = refreshTime;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(wrapRequestBody(chain.request()));
    }

    private Request wrapRequestBody(Request request) {
        if (request == null || request.body() == null) {
            return request;
        }
        final String key = request.url().toString();
        return request.newBuilder()
                .method(request.method(), new ProgressRequestBody(request.body(), mRefreshTime, new ProgressListener() {
                    @Override
                    public void onProgress(ProgressInfo progressInfo) {
                        mInterceptorProgressListener.onProgress(key, progressInfo);
                    }

                    @Override
                    public void onError(Exception e) {
                        mInterceptorProgressListener.onError(key, e);
                    }
                }))
                .build();
    }

}

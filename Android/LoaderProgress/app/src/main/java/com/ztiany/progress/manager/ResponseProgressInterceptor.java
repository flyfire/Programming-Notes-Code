package com.ztiany.progress.manager;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-20 15:39
 */
@SuppressWarnings("all")
public class ResponseProgressInterceptor implements Interceptor {

    private static final int DEFAULT_REFRESH_TIME = 150;
    private final UrlProgressListener mInterceptorProgressListener;
    private int mRefreshTime = DEFAULT_REFRESH_TIME;//进度刷新时间(单位ms),避免高频率调用

    public ResponseProgressInterceptor(UrlProgressListener interceptorProgressListener) {
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
        return wrapResponseBody(chain.proceed(chain.request()));
    }

    private Response wrapResponseBody(Response response) {
        if (response == null || response.body() == null) {
            return response;
        }

        final String key = response.request().url().toString();
        return response.newBuilder()
                .body(new ProgressResponseBody(response.body(), mRefreshTime, new ProgressListener() {
                    @Override
                    public void onProgress(ProgressInfo progressInfo) {
                        mInterceptorProgressListener.onProgress(key, progressInfo);
                    }

                    @Override
                    public void onError(Exception e) {
                        mInterceptorProgressListener.onError(key, e);
                    }
                })).build();
    }

}

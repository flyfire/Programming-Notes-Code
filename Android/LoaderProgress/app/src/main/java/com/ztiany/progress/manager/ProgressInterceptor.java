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
@SuppressWarnings("all")
public class ProgressInterceptor implements Interceptor {

    private static final int DEFAULT_REFRESH_TIME = 150;
    private final InterceptorProgressListener mInterceptorProgressListener;
    private int mRefreshTime = DEFAULT_REFRESH_TIME;//进度刷新时间(单位ms),避免高频率调用

    public ProgressInterceptor(InterceptorProgressListener interceptorProgressListener) {
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
        return wrapResponseBody(chain.proceed(wrapRequestBody(chain.request())));
    }

    private Request wrapRequestBody(Request request) {
        if (request == null || request.body() == null) {
            return request;
        }
        String key = request.url().toString();
        return request.newBuilder()
                .method(request.method(), new ProgressRequestBody(request.body(), mRefreshTime, new ProgressListener() {
                    @Override
                    public void onProgress(ProgressInfo progressInfo) {
                        mInterceptorProgressListener.onRequestProgress(key, progressInfo);
                    }

                    @Override
                    public void onError(long id, Exception e) {
                        mInterceptorProgressListener.onRequestError(key, id, e);
                    }
                }))
                .build();
    }

    private Response wrapResponseBody(Response response) {
        if (response == null || response.body() == null) {
            return response;
        }

        String key = response.request().url().toString();
        return response.newBuilder()
                .body(new ProgressResponseBody(response.body(), mRefreshTime, new ProgressListener() {
                    @Override
                    public void onProgress(ProgressInfo progressInfo) {
                        mInterceptorProgressListener.onResponseProgress(key, progressInfo);
                    }

                    @Override
                    public void onError(long id, Exception e) {
                        mInterceptorProgressListener.onResponseError(key, id, e);
                    }
                }))
                .build();
    }


    public static abstract class InterceptorProgressListener {

        public void onRequestProgress(String url, ProgressInfo progressInfo) {

        }

        public void onRequestError(String url, long id, Exception e) {

        }

        public void onResponseProgress(String url, ProgressInfo progressInfo) {

        }

        public void onResponseError(String url, long id, Exception e) {

        }

    }
}

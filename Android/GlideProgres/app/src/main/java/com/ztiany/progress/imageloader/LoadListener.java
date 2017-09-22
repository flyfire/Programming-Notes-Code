package com.ztiany.progress.imageloader;

public interface LoadListener<T> {

    void onLoadStart();

    void onLoadSuccess(T bitmap);

    void onProgress(long contentLength, long currentBytes, float percent, boolean isFinish);

    void onLoadFail();

}

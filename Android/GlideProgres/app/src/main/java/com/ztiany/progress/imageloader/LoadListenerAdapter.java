package com.ztiany.progress.imageloader;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-22 13:14
 */
public abstract class LoadListenerAdapter<T> implements LoadListener<T> {

    @Override
    public void onLoadSuccess(T bitmap) {
    }

    @Override
    public void onProgress(long contentLength, long currentBytes, float percent, boolean isFinish) {
    }

    @Override
    public void onLoadFail() {
    }

    @Override
    public void onLoadStart() {
    }
}

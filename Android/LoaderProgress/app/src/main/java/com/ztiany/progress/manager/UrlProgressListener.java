package com.ztiany.progress.manager;

/**
 * Created by jess on 02/06/2017 18:23
 * Contact with jess.yan.effort@gmail.com
 */
public interface UrlProgressListener {

    /**
     * 进度监听
     *
     * @param progressInfo
     */
    void onProgress(String url, ProgressInfo progressInfo);

    void onError(String url, Exception e);
}

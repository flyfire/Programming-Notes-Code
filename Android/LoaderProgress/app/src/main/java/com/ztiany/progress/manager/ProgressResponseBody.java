package com.ztiany.progress.manager;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

class ProgressResponseBody extends ResponseBody {

    private final int mRefreshTime;
    private final ResponseBody mDelegate;
    private final ProgressInfo mProgressInfo;
    private final ProgressListener mProgressListener;
    private BufferedSource mBufferedSource;

    ProgressResponseBody(ResponseBody responseBody, int refreshTime, ProgressListener progressListener) {
        this.mDelegate = responseBody;
        mProgressListener = progressListener;
        this.mRefreshTime = refreshTime;
        this.mProgressInfo = new ProgressInfo();
    }

    @Override
    public MediaType contentType() {
        return mDelegate.contentType();
    }

    @Override
    public long contentLength() {
        return mDelegate.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (mBufferedSource == null) {
            mBufferedSource = Okio.buffer(source(mDelegate.source()));
        }
        return mBufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            private long totalBytesRead = 0L;
            private long lastRefreshTime = 0L;  //最后一次刷新的时间
            private long tempSize = 0L;

            @Override
            public long read(@NonNull Buffer sink, long byteCount) throws IOException {
                long bytesRead;
                try {
                    bytesRead = super.read(sink, byteCount);
                } catch (IOException e) {
                    e.printStackTrace();
                    mProgressListener.onError(e);
                    throw e;
                }
                if (mProgressInfo.getContentLength() == 0) { //避免重复调用 contentLength()
                    mProgressInfo.setContentLength(contentLength());
                }
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                tempSize += bytesRead != -1 ? bytesRead : 0;

                long curTime = SystemClock.elapsedRealtime();
                if (curTime - lastRefreshTime >= mRefreshTime || bytesRead == -1 || totalBytesRead == mProgressInfo.getContentLength()) {
                    final long finalTempSize = tempSize;
                    final long finalTotalBytesRead = totalBytesRead;
                    final long finalIntervalTime = curTime - lastRefreshTime;
                    // Runnable 里的代码是通过 Handler 执行在主线程的,外面代码可能执行在其他线程
                    // 所以我必须使用 final ,保证在 Runnable 执行前使用到的变量,在执行时不会被修改
                    mProgressInfo.setEachBytes(bytesRead != -1 ? finalTempSize : -1);
                    mProgressInfo.setCurrentBytes(finalTotalBytesRead);
                    mProgressInfo.setIntervalTime(finalIntervalTime);
                    mProgressInfo.setFinish(bytesRead == -1 && finalTotalBytesRead == mProgressInfo.getContentLength());
                    mProgressListener.onProgress(mProgressInfo);
                    lastRefreshTime = curTime;
                    tempSize = 0;
                }
                return bytesRead;
            }
        };
    }
}

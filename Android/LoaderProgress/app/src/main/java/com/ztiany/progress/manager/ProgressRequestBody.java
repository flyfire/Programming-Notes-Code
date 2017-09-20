package com.ztiany.progress.manager;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

class ProgressRequestBody extends RequestBody {

    private final ProgressListener mProgressListener;
    private int mRefreshTime;
    private final RequestBody mDelegate;
    private final ProgressInfo mProgressInfo;
    private BufferedSink mBufferedSink;

    ProgressRequestBody(RequestBody delegate, int refreshTime, ProgressListener progressListener) {
        this.mDelegate = delegate;
        mProgressListener = progressListener;
        this.mRefreshTime = refreshTime;
        this.mProgressInfo = new ProgressInfo(System.currentTimeMillis());
    }

    @Override
    public MediaType contentType() {
        return mDelegate.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return mDelegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        if (mBufferedSink == null) {
            mBufferedSink = Okio.buffer(new CountingSink(sink));
        }
        try {
            mDelegate.writeTo(mBufferedSink);
            mBufferedSink.flush();
        } catch (IOException e) {
            e.printStackTrace();
            mProgressListener.onError(mProgressInfo.getId(), e);
            throw e;
        }
    }

    private final class CountingSink extends ForwardingSink {

        private long totalBytesRead = 0L;
        private long lastRefreshTime = 0L;  //最后一次刷新的时间
        private long tempSize = 0L;

        CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(@NonNull Buffer source, long byteCount) throws IOException {
            try {
                super.write(source, byteCount);
            } catch (IOException e) {
                e.printStackTrace();
                mProgressListener.onError(mProgressInfo.getId(), e);
                throw e;
            }
            if (mProgressInfo.getContentLength() == 0) { //避免重复调用 contentLength()
                mProgressInfo.setContentLength(contentLength());
            }
            totalBytesRead += byteCount;
            tempSize += byteCount;

            long curTime = SystemClock.elapsedRealtime();
            if (curTime - lastRefreshTime >= mRefreshTime || totalBytesRead == mProgressInfo.getContentLength()) {
                final long finalTempSize = tempSize;
                final long finalTotalBytesRead = totalBytesRead;
                final long finalIntervalTime = curTime - lastRefreshTime;
                mProgressInfo.setEachBytes(finalTempSize);
                mProgressInfo.setCurrentbytes(finalTotalBytesRead);
                mProgressInfo.setIntervalTime(finalIntervalTime);
                mProgressInfo.setFinish(finalTotalBytesRead == mProgressInfo.getContentLength());
                mProgressListener.onProgress(mProgressInfo);
                lastRefreshTime = curTime;
                tempSize = 0;
            }
        }
    }
}

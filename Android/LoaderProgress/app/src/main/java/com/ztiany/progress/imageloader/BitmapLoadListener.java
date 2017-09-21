package com.ztiany.progress.imageloader;

import android.graphics.Bitmap;

public interface BitmapLoadListener {

    void onLoadSuccess(Bitmap bitmap);

    void onLoadFail(Exception e);

}

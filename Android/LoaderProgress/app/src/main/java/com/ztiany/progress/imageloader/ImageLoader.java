package com.ztiany.progress.imageloader;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;


public interface ImageLoader {

    void display(Fragment fragment, ImageView imageView, String url);

    void display(Fragment fragment, ImageView imageView, String url, DisplayConfig displayConfig);

    void display(Fragment fragment, ImageView imageView, Source source);

    void display(Fragment fragment, ImageView imageView, Source source, DisplayConfig displayConfig);


    void display(ImageView imageView, String url);

    void display(ImageView imageView, String url, DisplayConfig config);

    void display(ImageView imageView, Source source);

    void display(ImageView imageView, Source source, DisplayConfig config);

    ///////////////////////////////////////////////////////////////////////////
    // pause and resume
    ///////////////////////////////////////////////////////////////////////////
    void pause(Fragment fragment);

    void resume(Fragment fragment);

    void pause(Context context);

    void resume(Context context);

    ///////////////////////////////////////////////////////////////////////////
    // preload
    ///////////////////////////////////////////////////////////////////////////
    void preload(Context context, Source source);

    void preload(Context context, Source source, int width, int height);

    ///////////////////////////////////////////////////////////////////////////
    // LoadBitmap
    ///////////////////////////////////////////////////////////////////////////
    void loadBitmap(Context context, Source source, boolean cache, BitmapLoadListener bitmapLoadListener);

    void loadBitmap(Context context, Source source, boolean cache, BitmapLoadListener bitmapLoadListener, int width, int height);

}

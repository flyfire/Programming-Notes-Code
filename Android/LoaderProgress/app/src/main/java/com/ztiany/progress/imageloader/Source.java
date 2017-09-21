package com.ztiany.progress.imageloader;

import android.net.Uri;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.RequestManager;

import java.io.File;


public class Source {

    private String mUrl;
    private File mFile;
    private int mResource;
    private Uri mUri;

    public static Source create(String url) {
        Source source = new Source();
        source.mUrl = url;
        return source;
    }

    public static Source createWithPath(String path) {
        return create(new File(path));
    }


    public static Source createWithUri(Uri uri) {
        Source source = new Source();
        source.mUri = uri;
        return source;
    }

    public static Source create(File file) {
        Source source = new Source();
        source.mFile = file;
        return source;
    }

    public static Source create(int resource) {
        Source source = new Source();
        source.mResource = resource;
        return source;
    }


    DrawableTypeRequest<?> setToRequest(RequestManager requestManager) {
        if (mFile != null) {
            return requestManager.load(mFile);
        } else if (mUrl != null) {
            return requestManager.load(mUrl);
        } else if (mResource != 0) {
            return requestManager.load(mResource);
        } else if (mUri != null) {
            return requestManager.load(mUri);
        } else {
            throw new IllegalArgumentException("UnSupport source");
        }
    }


}

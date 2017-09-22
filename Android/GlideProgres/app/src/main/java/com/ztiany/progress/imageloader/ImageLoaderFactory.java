package com.ztiany.progress.imageloader;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-03-27 18:09
 */

public class ImageLoaderFactory {

    private static final GlideImageLoader IMAGE_LOADER = new GlideImageLoader();

    private ImageLoaderFactory() {

    }

    public static ImageLoader getImageLoader() {
        return IMAGE_LOADER;
    }
}

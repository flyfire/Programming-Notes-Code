package com.ztiany.progress.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

class GlideImageLoader implements ImageLoader {

    private static final String TAG = GlideImageLoader.class.getSimpleName();

    private boolean canFragmentLoadImage(Fragment fragment) {
        return fragment.isResumed() || fragment.isAdded() || fragment.isVisible();
    }

    @Override
    public void display(Fragment fragment, ImageView imageView, final String url, final LoadListener<Drawable> loadListener) {
        if (canFragmentLoadImage(fragment)) {
            Glide.with(fragment).load(url).into(new InnerImageTarget(imageView, url, loadListener));
        }
    }

    @Override
    public void display(Fragment fragment, ImageView imageView, String url, DisplayConfig displayConfig, LoadListener<Drawable> loadListener) {
        if (!canFragmentLoadImage(fragment)) {
            return;
        }
        RequestBuilder<Drawable> requestBuilder = Glide.with(fragment).load(url);
        if (displayConfig != null) {
            RequestOptions requestOptions = buildRequestOptions(displayConfig);
            requestBuilder.apply(requestOptions);
        }
        requestBuilder.into(new InnerImageTarget(imageView, url, loadListener));
    }

    @Override
    public void display(ImageView imageView, String url, LoadListener<Drawable> loadListener) {
        Glide.with(imageView.getContext()).load(url).into(new InnerImageTarget(imageView, url, loadListener));
    }

    @Override
    public void display(ImageView imageView, String url, DisplayConfig config, LoadListener<Drawable> loadListener) {
        RequestBuilder<Drawable> requestBuilder = Glide.with(imageView.getContext()).load(url);
        if (config != null) {
            RequestOptions requestOptions = buildRequestOptions(config);
            requestBuilder.apply(requestOptions);
        }
        requestBuilder.into(new InnerImageTarget(imageView, url, loadListener));
    }

    @Override
    public void removeListener(String url) {
        ProgressManager.getInstance().removeListener(url);
    }

    private class InnerImageTarget extends ImageViewTarget<Drawable> {

        private final LoadListener<Drawable> mLoadListener;

        InnerImageTarget(ImageView view, String url, LoadListener<Drawable> loadListener) {
            super(view);
            ProgressManager.getInstance().setLoadListener(url, loadListener);
            mLoadListener = loadListener;
        }

        @Override
        protected void setResource(@Nullable Drawable resource) {
            getView().setImageDrawable(resource);
        }

        @Override
        public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
            super.onResourceReady(resource, transition);
            mLoadListener.onLoadSuccess(resource);
        }

        @Override
        public void onLoadStarted(@Nullable Drawable placeholder) {
            mLoadListener.onLoadStart();
            super.onLoadStarted(placeholder);
        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {
            super.onLoadFailed(errorDrawable);
            mLoadListener.onLoadFail();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Progress end
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void display(Fragment fragment, ImageView imageView, String url) {
        if (canFragmentLoadImage(fragment)) {
            Glide.with(fragment).load(url).into(imageView);
        }
    }

    @Override
    public void display(Fragment fragment, ImageView imageView, String url, DisplayConfig displayConfig) {
        display(imageView, Glide.with(fragment).load(url), displayConfig);
    }

    @Override
    public void display(Fragment fragment, ImageView imageView, Source source) {
        display(fragment, imageView, source, null);
    }

    @Override
    public void display(Fragment fragment, ImageView imageView, Source source, DisplayConfig displayConfig) {
        if (!canFragmentLoadImage(fragment)) {
            return;
        }
        RequestManager requestManager = Glide.with(fragment);
        RequestBuilder<?> drawableTypeRequest = setToRequest(requestManager, source);
        display(imageView, drawableTypeRequest, displayConfig);
    }

    @Override
    public void display(ImageView imageView, String url) {
        display(imageView, Glide.with(imageView.getContext()).load(url), null);
    }

    @Override
    public void display(ImageView imageView, String url, DisplayConfig config) {
        display(imageView, Glide.with(imageView.getContext()).load(url), config);
    }

    @Override
    public void display(ImageView imageView, Source source) {
        RequestBuilder<?> drawableTypeRequest = setToRequest(Glide.with(imageView.getContext()), source);
        display(imageView, drawableTypeRequest, null);
    }

    @Override
    public void display(ImageView imageView, Source source, DisplayConfig config) {
        RequestBuilder<?> drawableTypeRequest = setToRequest(Glide.with(imageView.getContext()), source);
        display(imageView, drawableTypeRequest, config);
    }

    private void display(ImageView imageView, RequestBuilder request, DisplayConfig displayConfig) {
        if (displayConfig != null) {
            RequestOptions requestOptions = buildRequestOptions(displayConfig);
            request.apply(requestOptions);
        }
        request.into(imageView);
    }

    ///////////////////////////////////////////////////////////////////////////
    // pause and resume
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void pause(Fragment fragment) {
        Glide.with(fragment).pauseRequests();
    }

    @Override
    public void resume(Fragment fragment) {
        Glide.with(fragment).resumeRequests();
    }

    @Override
    public void pause(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void resume(Context context) {
        Glide.with(context).resumeRequests();
    }


    ///////////////////////////////////////////////////////////////////////////
    //Load Bitmap
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void preload(Context context, Source source) {
        RequestManager requestManager = Glide.with(context);
        setToRequest(requestManager, source).preload();
    }

    @Override
    public void preload(Context context, Source source, int width, int height) {
        RequestManager requestManager = Glide.with(context);
        setToRequest(requestManager, source).preload(width, height);
    }

    @Override
    public void loadBitmap(Context context, Source source, boolean cache, final LoadListener<Bitmap> bitmapLoadListener) {
        String url = source.mUrl;
        if (url != null) {
            ProgressManager.getInstance().setLoadListener(url, bitmapLoadListener);
        }

        RequestManager requestManager = Glide.with(context);

        RequestOptions requestOptions = new RequestOptions();
        if (cache) {
            requestOptions.skipMemoryCache(false);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        RequestBuilder<Bitmap> requestBuilder = requestManager.asBitmap().apply(requestOptions);
        requestBuilder = setToRequest(requestBuilder, source);
        requestBuilder.into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                bitmapLoadListener.onLoadSuccess(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                bitmapLoadListener.onLoadFail();
            }

            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                bitmapLoadListener.onLoadStart();
            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////
    // Build request
    ///////////////////////////////////////////////////////////////////////////

    private RequestOptions buildRequestOptions(DisplayConfig displayConfig) {
        RequestOptions requestOptions = new RequestOptions();
        /*DiskCache*/
        if (displayConfig.isCacheDisk()) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        }

        /*MemoryCache*/
        if (displayConfig.isCacheMemory()) {
            requestOptions.skipMemoryCache(false);
        } else {
            requestOptions.skipMemoryCache(true);
        }

        /*SCALE_TYPE*/
        if (displayConfig.getScaleType() == DisplayConfig.SCALE_CENTER_CROP) {
            requestOptions.centerCrop();
        } else if (displayConfig.getScaleType() == DisplayConfig.SCALE_FIT_CENTER) {
            requestOptions.fitCenter();
        }

        /*transform*/
        if (displayConfig.getTransform() == DisplayConfig.TRANSFORM_CIRCLE) {
            requestOptions.circleCrop();
        } else if (displayConfig.getTransform() == DisplayConfig.TRANSFORM_ROUNDED_CORNERS) {
            requestOptions.transform(new RoundedCorners(displayConfig.getRoundedCornersRadius()));
        } else if (displayConfig.getTransform() == DisplayConfig.TRANSFORM_NONE) {
            requestOptions.dontTransform();//不做渐入渐出的转换
        }

        /*Placeholder*/
        if (displayConfig.getErrorPlaceholder() != DisplayConfig.NO_PLACE_HOLDER) {
            requestOptions.error(displayConfig.getErrorPlaceholder());
        }
        if (displayConfig.getErrorDrawable() != null) {
            Log.d(TAG, "buildRequestOptions() called with: displayConfig = [" + displayConfig + "]");
            requestOptions.error(displayConfig.getErrorDrawable());
        }

        if (displayConfig.getLoadingPlaceholder() != DisplayConfig.NO_PLACE_HOLDER) {
            requestOptions.placeholder(displayConfig.getLoadingPlaceholder());
        }

        if (displayConfig.getLoadingDrawable() != null) {
            Log.d(TAG, "buildRequestOptions() called with: displayConfig = [" + displayConfig + "]");
            requestOptions.placeholder(displayConfig.getLoadingDrawable());
        }
        return requestOptions;
    }

    private RequestBuilder<?> setToRequest(RequestManager requestManager, Source source) {
        if (source.mFile != null) {
            return requestManager.load(source.mFile);
        } else if (source.mUrl != null) {
            return requestManager.load(source.mUrl);
        } else if (source.mResource != 0) {
            return requestManager.load(source.mResource);
        } else if (source.mUri != null) {
            return requestManager.load(source.mUri);
        } else {
            throw new IllegalArgumentException("UnSupport source");
        }
    }

    private <T> RequestBuilder<T> setToRequest(RequestBuilder<T> requestBuilder, Source source) {
        if (source.mFile != null) {
            return requestBuilder.load(source.mFile);
        } else if (source.mUrl != null) {
            return requestBuilder.load(source.mUrl);
        } else if (source.mResource != 0) {
            return requestBuilder.load(source.mResource);
        } else if (source.mUri != null) {
            return requestBuilder.load(source.mUri);
        } else {
            throw new IllegalArgumentException("UnSupport source");
        }
    }

}

package com.ztiany.progress.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.android.base.imageloader.transform.GlideCircleTransform;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


class GlideImageLoader implements ImageLoader {

    ///////////////////////////////////////////////////////////////////////////
    // Fragment
    ///////////////////////////////////////////////////////////////////////////
    private boolean canFragmentLoadImage(Fragment fragment) {
        return fragment.isResumed() || fragment.isAdded() || fragment.isVisible();
    }

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
        RequestManager with = Glide.with(fragment);
        DrawableTypeRequest<?> drawableTypeRequest = source.setToRequest(with);
        display(imageView, drawableTypeRequest, displayConfig);
    }


    private void display(ImageView imageView, DrawableTypeRequest request, DisplayConfig displayConfig) {
        if (displayConfig != null) {

            if (displayConfig.isCacheDisk()) {
                request.diskCacheStrategy(DiskCacheStrategy.ALL);
            } else {
                request.diskCacheStrategy(DiskCacheStrategy.NONE);
            }
            if (displayConfig.isCacheMemory()) {
                request.skipMemoryCache(false);
            } else {
                request.skipMemoryCache(true);
            }
            if (displayConfig.getScaleType() == DisplayConfig.SCALE_CENTER_CROP) {
                request.centerCrop();
            } else if (displayConfig.getScaleType() == DisplayConfig.SCALE_FIT_CENTER) {
                request.fitCenter();
            }

            if (displayConfig.getTransform() == DisplayConfig.TRANSFORM_CIRCLE) {
                request.transform(new GlideCircleTransform(imageView.getContext()));
            } else if (displayConfig.getTransform() == DisplayConfig.TRANSFORM_NONE) {
                request.dontTransform();
            }

            if (displayConfig.getAnim() == DisplayConfig.ANIM_NONE) {
                request.dontAnimate();
            } else {
                int anim = displayConfig.getAnim();
                if (anim > 0) {
                    request.animate(anim);
                }
            }

            if (displayConfig.getErrorPlaceholder() != DisplayConfig.NO_PLACE_HOLDER) {
                request.error(displayConfig.getErrorPlaceholder());
            }
            if (displayConfig.getLoadingPlaceholder() != DisplayConfig.NO_PLACE_HOLDER) {
                request.placeholder(displayConfig.getLoadingPlaceholder());
            }

        }
        request.into(imageView);

    }


    ///////////////////////////////////////////////////////////////////////////
    // Context
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public void display(ImageView imageView, String url) {
        display(
                imageView, Glide.with(imageView.getContext()).load(url), null
        );
    }

    @Override
    public void display(ImageView imageView, String url, DisplayConfig config) {
        display(
                imageView, Glide.with(imageView.getContext()).load(url), config
        );
    }


    @Override
    public void display(ImageView imageView, Source source) {
        DrawableTypeRequest<?> drawableTypeRequest = source.setToRequest(Glide.with(imageView.getContext()));

        display(
                imageView, drawableTypeRequest, null
        );
    }

    @Override
    public void display(ImageView imageView, Source source, DisplayConfig config) {
        DrawableTypeRequest<?> drawableTypeRequest = source.setToRequest(Glide.with(imageView.getContext()));
        display(
                imageView, drawableTypeRequest, config
        );
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
        source.setToRequest(requestManager).preload();
    }

    @Override
    public void preload(Context context, Source source, int width, int height) {
        RequestManager requestManager = Glide.with(context);
        source.setToRequest(requestManager).preload(width, height);
    }

    @Override
    public void loadBitmap(Context context, Source source, boolean cache, final BitmapLoadListener bitmapLoadListener) {
        loadBitmap(context, source, cache, bitmapLoadListener, 0, 0);
    }

    @Override
    public void loadBitmap(Context context, Source source, boolean cache, final BitmapLoadListener bitmapLoadListener, int width, int height) {
        DrawableTypeRequest<?> drawableTypeRequest = source.setToRequest(Glide.with(context));
        loadBitmap(drawableTypeRequest, cache, bitmapLoadListener, width, height);
    }


    private void loadBitmap(DrawableTypeRequest<?> drawableTypeRequest, boolean cache, final BitmapLoadListener bitmapLoadListener, int width, int height) {
        if (cache) {
            drawableTypeRequest.skipMemoryCache(false);
            drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            drawableTypeRequest.skipMemoryCache(true);
            drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
        }

        if (width > 0 && height > 0) {
            drawableTypeRequest.asBitmap()
                    .override(width, height)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            bitmapLoadListener.onLoadSuccess(resource);
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            bitmapLoadListener.onLoadFail(e);
                        }
                    });
        } else {
            drawableTypeRequest.asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            bitmapLoadListener.onLoadSuccess(resource);
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            bitmapLoadListener.onLoadFail(e);
                        }
                    });
        }
    }

}

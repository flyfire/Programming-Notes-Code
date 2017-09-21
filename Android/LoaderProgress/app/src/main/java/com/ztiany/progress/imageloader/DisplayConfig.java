package com.ztiany.progress.imageloader;



public class DisplayConfig {

    private boolean mCacheMemory = true;
    private boolean mCacheDisk = true;
    private int mTransform;
    private int mAnimId;
    private int mScaleType;

    /*placeholder*/
    static final int NO_PLACE_HOLDER = -1;
    private int mErrorPlaceholder = NO_PLACE_HOLDER;
    private int mLoadingPlaceholder = NO_PLACE_HOLDER;
    /*scale type*/
    public static final int SCALE_NONE = 0;
    public static final int SCALE_CENTER_CROP = 1;
    public static final int SCALE_FIT_CENTER = 2;
    /*transform*/
    public static final int TRANSFORM_NONE = 1;
    public static final int TRANSFORM_CIRCLE = 2;
    /*animation*/
    public static final int ANIM_NONE = 1;


    private DisplayConfig() {

    }

    public static DisplayConfig create() {
        return new DisplayConfig();
    }

    public DisplayConfig setErrorPlaceholder(int errorPlaceholder) {
        mErrorPlaceholder = errorPlaceholder;
        return this;
    }

    public DisplayConfig setLoadingPlaceholder(int loadingPlaceholder) {
        mLoadingPlaceholder = loadingPlaceholder;
        return this;
    }

    /**
     * @param scaleType {@link #SCALE_CENTER_CROP} or{@link #SCALE_FIT_CENTER}
     * @return DisplayConfig
     */
    public DisplayConfig scaleType(int scaleType) {
        mScaleType = scaleType;
        return this;
    }

    public DisplayConfig cacheMemory(boolean cacheMemory) {
        mCacheMemory = cacheMemory;
        return this;
    }

    public DisplayConfig setCacheDisk(boolean cacheDisk) {
        mCacheDisk = cacheDisk;
        return this;
    }


    /**
     * @param transform {@link #TRANSFORM_CIRCLE}
     * @return
     */
    public DisplayConfig setTransform(int transform) {
        mTransform = transform;
        return this;
    }


    /**
     * @param animId {@link #ANIM_NONE} or animator resource id
     * @return
     */
    public DisplayConfig setAnim(int animId) {
        mAnimId = animId;
        return this;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getter
    ///////////////////////////////////////////////////////////////////////////

    int getScaleType() {
        return mScaleType;
    }

    boolean isCacheMemory() {
        return mCacheMemory;
    }

    boolean isCacheDisk() {
        return mCacheDisk;
    }

    int getTransform() {
        return mTransform;
    }

    int getErrorPlaceholder() {
        return mErrorPlaceholder;
    }

    int getLoadingPlaceholder() {
        return mLoadingPlaceholder;
    }

    int getAnim() {
        return mAnimId;
    }
}

package com.ztiany.androidarchitecturecomponent.main;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * LifecycleObserver中虽然可以监听到Activity的生命周期，但是方法无法获取Activity生命周期方法的参数，如Bundle
 */
public class LifecycleObserverMain implements DefaultLifecycleObserver {

    private static final String TAG = LifecycleObserverMain.class.getSimpleName();

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onCreate() called with: owner = [" + owner + "]");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onDestroy() called with: owner = [" + owner + "]");
    }

}
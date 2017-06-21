package com.ztiany.androidarchitecturecomponent.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * LifecycleObserver中虽然可以监听到Activity的生命周期，但是方法无法获取Activity生命周期方法的参数，如Bundle
 */
public class LifecycleObserverMain implements LifecycleObserver {


    private static final String TAG = LifecycleObserverMain.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        Log.d(TAG, "onAny() called with: owner = [" + owner + "], event = [" + event + "]");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner owner, Lifecycle.Event event) {
        Log.d(TAG, "onCreate() called with: instance = [" + owner + "]");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.d(TAG, "onDestroy() called");
    }
}
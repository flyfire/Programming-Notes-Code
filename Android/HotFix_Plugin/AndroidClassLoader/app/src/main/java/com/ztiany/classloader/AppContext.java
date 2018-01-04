package com.ztiany.classloader;

import android.app.Application;
import android.util.Log;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2018-01-04 19:22
 */
public class AppContext extends Application {

    private static final String TAG = AppContext.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "ClassLoader.getSystemClassLoader(): " + ClassLoader.getSystemClassLoader());

        Log.d(TAG, "getClassLoader():                               " + getClassLoader());

        Log.d(TAG, "MainActivity.class.getClassLoader():    " + MainActivity.class.getClassLoader());
    }

}

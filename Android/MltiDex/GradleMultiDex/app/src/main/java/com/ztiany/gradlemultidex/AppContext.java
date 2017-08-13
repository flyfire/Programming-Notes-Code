package com.ztiany.gradlemultidex;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-08-13 21:53
 */
public class AppContext extends Application {

    private static final String TAG = AppContext.class.getSimpleName();
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        /*
        ClassLoader.getSystemClassLoader():
        dalvik.system.PathClassLoader[
                DexPathList[[directory "."],
                nativeLibraryDirectories=[/vendor/lib64, /system/lib64]]]

        getClassLoader():dalvik.system.PathClassLoader[
                DexPathList[[zip file "/data/app/com.ztiany.gradlemultidex-2/base.apk"],
                nativeLibraryDirectories=[/data/app/com.ztiany.gradlemultidex-2/lib/arm64, /vendor/lib64, /system/lib64]]]
         */

        Log.d(TAG, "ClassLoader.getSystemClassLoader():" + ClassLoader.getSystemClassLoader());
        Log.d(TAG, "getClassLoader():" + getClassLoader());
        Log.d(TAG, "MainActivity.class.getClassLoader():" + MainActivity.class.getClassLoader());
    }
}

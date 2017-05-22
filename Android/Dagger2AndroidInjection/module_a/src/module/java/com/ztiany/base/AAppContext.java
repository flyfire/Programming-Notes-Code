package com.ztiany.base;

import android.app.Activity;

import dagger.android.AndroidInjector;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:04
 */
public class AAppContext extends AppContext {

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return null;
    }

}

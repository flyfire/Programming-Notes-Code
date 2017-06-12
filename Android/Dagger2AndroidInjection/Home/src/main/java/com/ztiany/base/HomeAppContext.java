package com.ztiany.base;

import android.app.Activity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-23 10:54
 */
public class HomeAppContext extends AppContext {


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.create()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }


}

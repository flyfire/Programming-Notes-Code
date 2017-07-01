package com.ztiany.main;

import android.app.Application;
import android.view.View;

import dagger.android.AndroidInjector;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-06-21 14:55
 */
public class ViewInjection {

    public static void inject(View view) {
        Application application = (Application) view.getContext().getApplicationContext();
        AndroidInjector<View> activityInjector = ((HasViewInjector) application).viewInjector();
        activityInjector.inject(view);
    }

}

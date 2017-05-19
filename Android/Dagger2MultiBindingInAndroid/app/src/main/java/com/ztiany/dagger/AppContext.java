package com.ztiany.dagger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ztiany.dagger.multibinding.ActivityComponentBuilder;
import com.ztiany.dagger.multibinding.HasActivitySubcomponentBuilders;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


public class AppContext extends Application implements HasActivitySubcomponentBuilders {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
        appComponent.inject(this);
    }


    //方式1，使用MultiBinding Map 解耦
    @Inject
    Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> activityComponentBuilders;


    public static HasActivitySubcomponentBuilders get(Context context) {
        return ((HasActivitySubcomponentBuilders) context.getApplicationContext());
    }

    @Override
    public ActivityComponentBuilder getActivityComponentBuilder(Class<? extends Activity> activityClass) {
        return activityComponentBuilders.get(activityClass).get();
    }

}
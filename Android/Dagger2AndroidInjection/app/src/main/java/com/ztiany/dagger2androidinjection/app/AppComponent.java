package com.ztiany.dagger2androidinjection.app;


import com.ztiany.base.BaseModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                BaseModule.class,
                AndroidInjectionModule.class,
                AndroidSupportInjectionModule.class,
                BuildersModule.class
        }
)
public interface AppComponent {

    HomeAppContext inject(HomeAppContext application);

}
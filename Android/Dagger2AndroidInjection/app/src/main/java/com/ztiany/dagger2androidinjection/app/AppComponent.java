package com.ztiany.dagger2androidinjection.app;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                AndroidInjectionModule.class,
                AndroidSupportInjectionModule.class,
                BuildersModule.class
        }
)
public interface AppComponent {

    AppContext inject(AppContext application);

}
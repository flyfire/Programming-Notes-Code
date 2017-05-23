package com.ztiany.base;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-23 10:55
 */
@Component(
        modules = {
                BaseModule.class,
                AndroidInjectionModule.class,
                AndroidSupportInjectionModule.class
        }
)
@Singleton
public interface AppComponent {
    void inject(HomeAppContext homeAppContext);
}

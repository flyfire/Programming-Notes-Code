package com.ztiany.dagger2androidinjection.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-17 17:59
 */
@Module
class AppModule {

    @Provides
    @Singleton
    public String name() {
        return "dagger 2 android injection sample";
    }

}

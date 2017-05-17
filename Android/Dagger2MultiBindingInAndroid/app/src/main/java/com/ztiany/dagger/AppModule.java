package com.ztiany.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    public AppModule() {

    }

    @Provides
    @Singleton
    Utils provideUtils() {
        return new Utils();
    }

} 
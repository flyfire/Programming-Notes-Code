package com.ztiany.main.splash;

import dagger.Binds;
import dagger.Module;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-17 18:42
 */
@Module
public abstract class SplashModule {

    @Binds
    abstract SplashView splashView(SplashActivity splashActivity);

}

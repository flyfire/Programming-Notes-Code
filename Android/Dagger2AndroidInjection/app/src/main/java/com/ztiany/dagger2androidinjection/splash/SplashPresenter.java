package com.ztiany.dagger2androidinjection.splash;

import javax.inject.Inject;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-17 18:39
 */
public class SplashPresenter {

    SplashView mSplashView;

    @Inject
    SplashPresenter(SplashView splashView) {
        mSplashView = splashView;
    }

    void start() {
        mSplashView.openMainPage();
    }
}

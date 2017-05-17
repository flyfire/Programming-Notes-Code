package com.ztiany.dagger2androidinjection.main;

import javax.inject.Inject;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-17 17:45
 */
public class MainPresenter {

    private MainView mMainView;

    @Inject
    MainPresenter(MainActivity mainView) {
        mMainView = mainView;
    }

    void start() {
        mMainView.showMessage("MainPresenter installed");
    }

}

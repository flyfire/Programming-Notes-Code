package com.ztiany.dagger2androidinjection.main;

import dagger.Binds;
import dagger.Module;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-17 17:38
 */
@Module
abstract class MainModule {

    @Binds
    abstract MainView mainView(MainActivity act);

}

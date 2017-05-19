package com.ztiany.dagger2androidinjection.app;

import android.app.Activity;

import com.ztiany.dagger2androidinjection.main.MainActivity;
import com.ztiany.dagger2androidinjection.main.MainActivitySubcomponent;
import com.ztiany.dagger2androidinjection.splash.SplashActivity;
import com.ztiany.dagger2androidinjection.splash.SplashModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivitySubcomponent.class
})
public abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivityFactory(MainActivitySubcomponent.Builder builder);

    /**
     * ContributesAndroidInjector用在方法中，此方法必须是无参数的，且返回值为Android中具体的组件，然后ContributesAndroidInjector
     * 根据方法的返回类型为其生成一个SubComponent，然后我们就不需要在写Component了
     */
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity bindSplashActivity();


}
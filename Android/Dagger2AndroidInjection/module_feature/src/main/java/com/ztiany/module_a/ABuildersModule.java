package com.ztiany.module_a;

import android.app.Activity;

import com.ztiany.base.di.ActivityScope;
import com.ztiany.module_a.feature1.ModuleA_1Activity;
import com.ztiany.module_a.feature1.ModuleA_1Component;
import com.ztiany.module_a.feature2.ModuleA_2Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        ModuleA_1Component.class
})
public abstract class ABuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(ModuleA_1Activity.class)
    public abstract AndroidInjector.Factory<? extends Activity> bindModuleA1Factory(ModuleA_1Component.Builder builder);

    /**
     * ContributesAndroidInjector用在方法中，此方法必须是无参数的，且返回值为Android中具体的组件，然后ContributesAndroidInjector
     * 根据方法的返回类型为其生成一个SubComponent，然后我们就不需要在写Component了
     */
    @ContributesAndroidInjector()
    @ActivityScope
    abstract ModuleA_2Activity bindModuleA2();

}
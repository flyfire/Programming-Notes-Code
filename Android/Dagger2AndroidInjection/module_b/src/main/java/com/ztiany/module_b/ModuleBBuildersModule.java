package com.ztiany.module_b;

import android.view.View;

import com.ztiany.base.di.ActivityScope;
import com.ztiany.base.di.ViewKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-19 15:36
 */
@Module(
        subcomponents = {
                ItemTextView.CustomTextViewComponent.class
        }
)
public abstract class ModuleBBuildersModule {

    /**
     * ContributesAndroidInjector中可以再嵌套带有ContributesAndroidInjector的Module，这样一般情况下都不需要再手动写SubComponent了
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = ModuleBModule.class)
    abstract ModuleBActivity bindModuleBActivity();

    @Binds
    @IntoMap
    @ViewKey(ItemTextView.class)
    public abstract AndroidInjector.Factory<? extends View> bindCustomTextView(ItemTextView.CustomTextViewComponent.Builder builder);
}

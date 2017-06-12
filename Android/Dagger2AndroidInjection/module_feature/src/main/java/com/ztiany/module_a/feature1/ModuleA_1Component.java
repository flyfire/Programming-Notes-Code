package com.ztiany.module_a.feature1;

import com.ztiany.base.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:23
 */
@Subcomponent(modules = ModuleA_1Module.class)
@ActivityScope
public interface ModuleA_1Component extends AndroidInjector<ModuleA_1Activity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ModuleA_1Activity> {

    }

}

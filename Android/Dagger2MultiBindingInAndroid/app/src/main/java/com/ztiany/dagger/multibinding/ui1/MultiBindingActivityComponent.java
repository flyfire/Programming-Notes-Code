package com.ztiany.dagger.multibinding.ui1;

import com.ztiany.dagger.multibinding.ActivityComponent;
import com.ztiany.dagger.multibinding.ActivityComponentBuilder;
import com.ztiany.dagger.multibinding.ActivityModule;
import com.ztiany.dagger.multibinding.ActivityScope;

import dagger.Module;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = MultiBindingActivityComponent.MultiBindingActivityModule.class
)
public interface MultiBindingActivityComponent extends ActivityComponent<MultiBindingActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MultiBindingActivityModule, MultiBindingActivityComponent> {

    }

    @Module
    class MultiBindingActivityModule extends ActivityModule<MultiBindingActivity> {

        MultiBindingActivityModule(MultiBindingActivity activity) {
            super(activity);
        }

    }
}
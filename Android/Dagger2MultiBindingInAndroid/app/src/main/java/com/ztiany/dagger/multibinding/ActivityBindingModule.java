package com.ztiany.dagger.multibinding;

import com.ztiany.dagger.multibinding.ui1.MultiBindingActivity;
import com.ztiany.dagger.multibinding.ui1.MultiBindingActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(
        subcomponents = {
                MultiBindingActivityComponent.class,
        })
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MultiBindingActivity.class)
    public abstract ActivityComponentBuilder mainActivityComponentBuilder(MultiBindingActivityComponent.Builder impl);

}
package com.ztiany.dagger2androidinjection.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(
        modules = MainModule.class
)
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }

}
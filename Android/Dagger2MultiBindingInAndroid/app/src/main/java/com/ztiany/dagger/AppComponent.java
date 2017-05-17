package com.ztiany.dagger;

import com.ztiany.dagger.multibinding.ActivityBindingModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                //使用MultiBinding解耦
                ActivityBindingModule.class,
        }
)
public interface AppComponent {

    AppContext inject(AppContext application);

}
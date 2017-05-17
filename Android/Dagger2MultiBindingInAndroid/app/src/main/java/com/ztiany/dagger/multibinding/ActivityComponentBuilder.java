package com.ztiany.dagger.multibinding;

public interface ActivityComponentBuilder<M extends ActivityModule, C extends ActivityComponent> {

    ActivityComponentBuilder<M, C> activityModule(M activityModule);

    C build();

}
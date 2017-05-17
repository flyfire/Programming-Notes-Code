package com.ztiany.dagger.multibinding;

import android.app.Activity;

import dagger.MapKey;

@MapKey
public @interface ActivityKey {

    Class<? extends Activity> value();

}
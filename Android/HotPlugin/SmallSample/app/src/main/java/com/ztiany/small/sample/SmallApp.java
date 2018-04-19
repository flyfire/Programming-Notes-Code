package com.ztiany.small.sample;

import android.app.Application;

import net.wequick.small.Small;

public class SmallApp extends Application {

    public SmallApp() {
        Small.preSetUp(this);
    }

}
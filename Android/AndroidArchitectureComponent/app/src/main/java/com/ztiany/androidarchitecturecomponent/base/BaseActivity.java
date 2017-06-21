package com.ztiany.androidarchitecturecomponent.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-06-12 21:30
 */
public abstract class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    public LifecycleRegistry getLifecycle() {
        return this.mRegistry;
    }
}

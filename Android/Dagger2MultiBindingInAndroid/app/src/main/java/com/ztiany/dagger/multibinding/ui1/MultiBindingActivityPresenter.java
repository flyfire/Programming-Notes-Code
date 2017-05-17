package com.ztiany.dagger.multibinding.ui1;

import com.ztiany.dagger.Utils;
import com.ztiany.dagger.multibinding.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class MultiBindingActivityPresenter {

    private final MultiBindingActivity activity;

    @Inject
    Utils mUtils;

    @Inject
    public MultiBindingActivityPresenter(MultiBindingActivity activity) {
        this.activity = activity;
    }

    void init() {
        activity.updateText(mUtils.getMessage(this));
    }
} 
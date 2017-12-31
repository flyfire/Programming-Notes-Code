package com.ztiany.androidarchitecturecomponent.main;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-06-12 21:54
 */
public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    public void start() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared() called");
    }
}

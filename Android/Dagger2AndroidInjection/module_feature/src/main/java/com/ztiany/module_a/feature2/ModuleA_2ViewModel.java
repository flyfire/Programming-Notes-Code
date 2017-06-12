package com.ztiany.module_a.feature2;

import android.databinding.ObservableField;

import com.ztiany.base.presention.ErrorHandler;

import javax.inject.Inject;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:55
 */
public class ModuleA_2ViewModel {

    @Inject
    ErrorHandler mErrorHandler;

    @Inject
    ModuleA_2ViewModel() {

    }

    public final ObservableField<String> message = new ObservableField<>();//下拉刷新indicator

    void start() {
        message.set("ModuleA_2ViewModel started");
    }

    public void getData() {
        mErrorHandler.handleError(new NullPointerException("ModuleA_2ViewModel mock NullPointerException"));
    }

}

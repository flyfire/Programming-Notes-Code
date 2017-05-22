package com.ztiany.base;

import android.widget.Toast;

import com.ztiany.base.presention.ErrorHandler;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 11:20
 */
public class AppErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(AppContext.getContext(), "发生错误了 ： " + throwable, Toast.LENGTH_SHORT).show();
    }
}

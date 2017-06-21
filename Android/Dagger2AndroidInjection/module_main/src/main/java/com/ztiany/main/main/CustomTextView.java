package com.ztiany.main.main;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.ztiany.base.presentation.ErrorHandler;
import com.ztiany.main.ViewInjection;

import javax.inject.Inject;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-06-21 14:42
 */
public class CustomTextView extends AppCompatTextView {

    @Inject
    ErrorHandler mErrorHandler;

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewInjection.inject(this);
        Log.d("CustomTextView", "mErrorHandler:" + mErrorHandler);
    }
}

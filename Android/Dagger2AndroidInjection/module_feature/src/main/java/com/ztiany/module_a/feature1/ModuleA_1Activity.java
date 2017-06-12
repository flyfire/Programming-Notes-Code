package com.ztiany.module_a.feature1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ztiany.base.presention.ErrorHandler;
import com.ztiany.module_a.R;
import com.ztiany.module_a.feature2.ModuleA_2Activity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:20
 */
public class ModuleA_1Activity extends AppCompatActivity implements ModuleA_1View {

    @Inject
    ModuleA_1Presenter mModuleA_1Presenter;
    @Inject
    ErrorHandler mErrorHandler;

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_a_1_activity);
        mTextView = (TextView) findViewById(R.id.module_a_1_tv_message);
        mModuleA_1Presenter.start();
    }

    @Override
    public void showMessage(String message) {
        mTextView.setText(message);
    }

    public void open2(View view) {
        startActivity(new Intent(this, ModuleA_2Activity.class));
    }

    public void throwError(View view) {
        mErrorHandler.handleError(new IllegalArgumentException("ModuleA_1Activity mock IllegalArgumentException"));
    }
}

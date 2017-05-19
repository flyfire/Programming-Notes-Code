package com.ztiany.dagger2androidinjection.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ztiany.dagger2androidinjection.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter mMainPresenter;


    private TextView mMessageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageTv = (TextView) findViewById(R.id.main_tv);
        mMainPresenter.start();
    }


    @Override
    public void showMessage(CharSequence message) {
        mMessageTv.setText(message);
    }
}

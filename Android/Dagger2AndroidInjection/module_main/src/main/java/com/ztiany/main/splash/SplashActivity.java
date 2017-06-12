package com.ztiany.main.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ztiany.main.R;
import com.ztiany.main.main.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    SplashPresenter mSplashPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_main_activity_splash);
        mSplashPresenter.start();
        setupView();
    }

    private void setupView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.module_main_toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.module_main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSplashPresenter.open();
            }
        });
    }

    @Override
    public void openMainPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showMessage(String message) {
        TextView textView = (TextView) findViewById(R.id.module_main_splash_tv);
        textView.setText(message);
    }

}

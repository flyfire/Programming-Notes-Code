package com.ztiany.dagger2androidinjection.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ztiany.dagger2androidinjection.R;
import com.ztiany.dagger2androidinjection.main.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    SplashPresenter mSplashPresenter;

    //从AppComponent注入
    @Inject
    String mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        TextView textView = (TextView) findViewById(R.id.splash_tv);
        textView.setText(mTag);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSplashPresenter.start();
            }
        });
    }

    @Override
    public void openMainPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}

package com.ztiany.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ztiany.main.splash.SplashActivity;
import com.ztiany.module_a.feature1.ModuleA_1Activity;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-23 10:58
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void openModuleMainSplash(View view) {
        startActivity(new Intent(this, SplashActivity.class));
    }

    public void openModuleA1(View view) {
        startActivity(new Intent(this, ModuleA_1Activity.class));
    }
}

package com.ztiany.dagger.multibinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ztiany.dagger.AppContext;

public abstract class BaseActivity extends AppCompatActivity {
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(); 
    } 
 
    protected void setupActivityComponent() { 
        injectMembers(AppContext.get(this));
    } 
 
    protected abstract void injectMembers(HasActivitySubcomponentBuilders hasActivitySubcomponentBuilders);
} 
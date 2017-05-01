package com.ztiany.wrapcontent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ztiany.recyclerview.R;


public class WrapContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WithScrollVIewFragment.newInstance(), WithScrollVIewFragment.class.getName())
                    .commit();

        }
    }

}

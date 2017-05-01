package com.ztiany.diffutils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ztiany.recyclerview.R;


/**
 * 原文地址：http://blog.csdn.net/zxt0601/article/details/52562770
 *
 * @author Ztiany
 *         Date : 2016-10-25 23:09
 */
public class DiffUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, DiffUtilsOneFragment.newInstance(), DiffUtilsOneFragment.class.getName())
                    .commit();
        }
    }

}

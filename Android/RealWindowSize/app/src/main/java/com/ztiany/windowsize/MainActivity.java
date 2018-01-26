package com.ztiany.windowsize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                printWindowSize();
                printAllPadding();
            }
        });
    }

    private void printAllPadding() {
        View button = findViewById(R.id.btn_show);
        ViewParent parent = button.getParent();
        while (parent != null) {
            Log.d(TAG, "parent:" + parent);
            if (parent instanceof View) {
                Log.d(TAG, "((View) parent).getPaddingTop():" + ((View) parent).getPaddingTop());
            }
            parent = parent.getParent();
        }
    }

    private void printWindowSize() {
        View decorView = getWindow().getDecorView();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d(TAG, "displayMetrics.widthPixels:" + displayMetrics.widthPixels);
        Log.d(TAG, "displayMetrics.heightPixels:" + displayMetrics.heightPixels);
        Log.d(TAG, "decorView.getMeasuredWidth():" + decorView.getMeasuredWidth());
        Log.d(TAG, "decorView.getMeasuredHeight():" + decorView.getMeasuredHeight());
    }

    public void showDialog(View view) {
        new SizeDialog(this).show();
    }
}

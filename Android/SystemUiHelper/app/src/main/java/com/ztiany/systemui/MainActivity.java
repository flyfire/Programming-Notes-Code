package com.ztiany.systemui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ztiany.systemui.uisapmle.SystemUIActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "getWindow().getDecorView().getMeasuredHeight():" + getWindow().getDecorView().getMeasuredHeight());
                Log.d(TAG, "getWindow().getDecorView().getMeasuredWidth():" + getWindow().getDecorView().getMeasuredWidth());
            }
        });
    }

    public void openSystemUI(View view) {
        startActivity(new Intent(this, SystemUIActivity.class));
    }
}

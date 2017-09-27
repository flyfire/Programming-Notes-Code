package com.ztiany.androidaspectj03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListener();
    }

    private void setListener() {
        findViewById(R.id.btn_target_1).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_target_2).setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_target_1:
                    new AspectTarget1().runAll();
                    break;
                case R.id.btn_target_2:
                    AspectTarget2 aspectTarget2 = new AspectTarget2();
                    aspectTarget2.print1();
                    aspectTarget2.print2();
                    break;
            }
        }
    };
}

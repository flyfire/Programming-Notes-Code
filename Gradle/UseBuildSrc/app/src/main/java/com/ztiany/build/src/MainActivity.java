package com.ztiany.build.src;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztiany.liba.ModuleA;
import com.ztiany.libb.ModuleB;

public class MainActivity extends AppCompatActivity {

    @Override
    @DebugTools
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ModuleA.print();
        ModuleB.print();
    }

}

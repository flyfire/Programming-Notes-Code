package com.ztiany.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ztiany.webview.basic.BasicUseActivity;
import com.ztiany.webview.https.HttpsWebViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBasic(View view) {
        startActivity(new Intent(this, BasicUseActivity.class));
    }

    public void openHttps(View view) {
        startActivity(new Intent(this, HttpsWebViewActivity.class));
    }
}

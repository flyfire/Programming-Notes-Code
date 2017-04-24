package com.ztiany;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ztiany.diffutils.DiffUtilsActivity;
import com.ztiany.itemtouch.ItemTouchActivity;
import com.ztiany.itemtouchhelperextension.ItemTouchHelperExtensionActivity;
import com.ztiany.recyclerview.R;
import com.ztiany.snap.SnapHelperActivity;
import com.ztiany.wrapcontent.WrapContentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void wrapContent(View view) {
        startActivity(new Intent(this, WrapContentActivity.class));
    }

    public void snapHelper(View view) {
        startActivity(new Intent(this, SnapHelperActivity.class));
    }

    public void itemTouch(View view) {
        startActivity(new Intent(this, ItemTouchActivity.class));
    }

    public void itemTouchExtension(View view) {
        startActivity(new Intent(this, ItemTouchHelperExtensionActivity.class));
    }

    public void diffUtils(View view) {
        startActivity(new Intent(this, DiffUtilsActivity.class));

    }


}

package com.ztiany.dialogs.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ztiany.dialogs.R;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-07-24 23:28
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(attributes);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                View parent = findViewById(R.id.dialog_view);
                while (parent != null) {
                    Drawable background = parent.getBackground();
                    Log.d("DialogActivity", parent + "---" + background);
                    if (background instanceof ColorDrawable) {
                        Log.d("DialogActivity", parent + "---" + Integer.toHexString(((ColorDrawable) background).getColor()));
                    }
                    if (parent.getParent() != null && parent.getParent() instanceof ViewGroup) {
                        parent = (ViewGroup) parent.getParent();
                    } else {
                        parent = null;
                    }
                }
            }
        }, 2000);
    }


}

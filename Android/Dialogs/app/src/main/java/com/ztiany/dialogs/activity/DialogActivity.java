package com.ztiany.dialogs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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
    }


}

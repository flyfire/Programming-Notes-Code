package com.ztiany.itemtouchhelperextension;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ztiany.recyclerview.R;


/**
 * see：https://github.com/loopeer/itemtouchhelper-extension
 *
 * @author Ztiany
 *         <p>
 *         Date : 2016-09-07 23:50
 */
public class ItemTouchHelperExtensionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, ItemTouchExtensionFragment.newInstance(), ItemTouchExtensionFragment.class.getName())
                    .commit();
        }

    }
}

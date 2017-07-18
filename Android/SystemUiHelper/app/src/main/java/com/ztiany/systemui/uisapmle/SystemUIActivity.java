package com.ztiany.systemui.uisapmle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ztiany.systemui.R;
import com.ztiany.systemui.uisapmle.utils.SystemUiHelper;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-07-18 10:54
 */
public class SystemUIActivity extends AppCompatActivity {

    private static final String TAG = SystemUIActivity.class.getSimpleName();
    private SystemUiHelper mSystemUiHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_system_ui);
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "getWindow().getDecorView().getMeasuredHeight():" + getWindow().getDecorView().getMeasuredHeight());
                Log.d(TAG, "getWindow().getDecorView().getMeasuredWidth():" + getWindow().getDecorView().getMeasuredWidth());
            }
        });

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mSystemUiHelper = new SystemUiHelper(this,
                SystemUiHelper.LEVEL_HIDE_STATUS_BAR,
                SystemUiHelper.FLAG_IMMERSIVE_STICKY,
                new SystemUiHelper.OnVisibilityChangeListener() {
                    @Override
                    public void onVisibilityChange(boolean visible) {
                        Log.d(TAG, "visible:" + visible);
                    }
                });
    }


    public void toggle(View view) {
        if (mSystemUiHelper.isShowing()) {
            mSystemUiHelper.hide();
        } else {
            mSystemUiHelper.show();
        }
    }
}

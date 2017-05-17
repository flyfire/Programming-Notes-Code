package com.ztiany.test.activity_animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ztiany.test.R;

/**
 * <br/>   Description：
 *
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2016-12-23 12:52
 */

public class AnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_main);
    }


    public void explode(View view) {
        Intent intent = new Intent(this, AnimTwoActivity.class);
        intent.putExtra("transition", "explode");
        //将原先的跳转改成如下方式
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    public void slide(View view) {
        Intent intent = new Intent(this, AnimTwoActivity.class);

        intent.putExtra("transition", "slide");
        //将原先的跳转改成如下方式
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    public void fade(View view) {
        Intent intent = new Intent(this, AnimTwoActivity.class);

        intent.putExtra("transition", "fade");
        //将原先的跳转改成如下方式
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    public void share(View view) {
        Intent intent = new Intent(this, AnimTwoActivity.class);
        //共享元素
        ImageView share = (ImageView) findViewById(R.id.iv_share);
        intent.putExtra("transition", "share");
        //将原先的跳转改成如下方式，注意这里面的第三个参数决定了ActivityTwo 布局中的android:transitionName的值，它们要保持一致
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, share, "image").toBundle());

    }

}

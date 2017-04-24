package com.ztiany.view.draw.path;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-26 11:37                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class ABezierActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFrameLayout = new FrameLayout(ABezierActivity.this);
        mFrameLayout.addView(new BezierView(ABezierActivity.this));
        setContentView(mFrameLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuItem = menu.add("贝塞尔曲线");
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new BezierView(ABezierActivity.this));
                return true;
            }
        });

        MenuItem waterItem = menu.add("模拟水波纹");
        waterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new WaterView(ABezierActivity.this));
                return true;
            }
        });

        MenuItem twoBezier = menu.add("二阶贝塞尔曲线");
        twoBezier.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new TwoBezierView(ABezierActivity.this));
                return true;
            }
        });

        MenuItem thirdBezier = menu.add("三阶贝塞尔曲线");
        thirdBezier.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new ThirdBezierView(ABezierActivity.this));
                return true;
            }
        });

        MenuItem circleBezier = menu.add("圆圈贝塞尔曲线");
        circleBezier.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new BezierCircle(ABezierActivity.this));
                return true;
            }
        });

        MenuItem circleBezierTranslate = menu.add("圆圈贝塞尔曲线Translate");
        circleBezierTranslate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new BezierCircleTranslate(ABezierActivity.this));
                return true;
            }
        });

        MenuItem addPath = menu.add("AddPath");
        addPath.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new AddPathView(ABezierActivity.this));
                return true;
            }
        });


        MenuItem pathEffectView = menu.add("PathEffective");
        pathEffectView.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new PathEffectView(ABezierActivity.this));
                return true;
            }
        });

        return true;

    }

}

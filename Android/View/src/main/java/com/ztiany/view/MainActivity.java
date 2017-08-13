package com.ztiany.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ztiany.view.custom.CustomViewFragment;
import com.ztiany.view.draw.PathFragment;
import com.ztiany.view.draw.CanvasFragment;
import com.ztiany.view.draw.MatrixFragment;
import com.ztiany.view.draw.camera.Camera3DFragment;
import com.ztiany.view.draw.camera.Camera3DTheoryFragment;
import com.ztiany.view.draw.camera.CameraDemoViewFragment;
import com.ztiany.view.draw.color.ColorMatrixFilterFragment;
import com.ztiany.view.draw.color.MaskFilterFragment;
import com.ztiany.view.scroll.ScrollFragment;
import com.ztiany.view.scroll.sticky.StickyNavigationFragment;
import com.ztiany.view.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final List<Item> LIST = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseUtils.init(getApplication());
        setContentView(R.layout.common_activity_main);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setContentInsetStartWithNavigation(0);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    supportFinishAfterTransition();
                }
            });
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        setAdapter();
    }

    private void setAdapter() {
        mRecyclerView.setAdapter(new ItemAdapter(this, LIST));
    }

    static {
        LIST.add(new Item("事件&滑动", ScrollFragment.class));
        LIST.add(new Item("Sticky Navigation", StickyNavigationFragment.class));
        LIST.add(new Item("Canvas绘制", CanvasFragment.class));
        LIST.add(new Item("矩阵", MatrixFragment.class));
        LIST.add(new Item("ColorMatrixFilter", ColorMatrixFilterFragment.class));
        LIST.add(new Item("MaskFilter", MaskFilterFragment.class));
        LIST.add(new Item("Path&贝塞尔曲线", PathFragment.class));
        LIST.add(new Item("Camera变换", CameraDemoViewFragment.class));
        LIST.add(new Item("Camera3D View", Camera3DFragment.class));
        LIST.add(new Item("Camera3D 原理", Camera3DTheoryFragment.class));
        LIST.add(new Item("自定义View", CustomViewFragment.class));
    }
}

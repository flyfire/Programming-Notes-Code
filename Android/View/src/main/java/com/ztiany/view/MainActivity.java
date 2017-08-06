package com.ztiany.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztiany.view.custom.CustomViewFragment;
import com.ztiany.view.draw.BezierFragment;
import com.ztiany.view.draw.CanvasFragment;
import com.ztiany.view.draw.color.ColorMatrixFilterFragment;
import com.ztiany.view.draw.color.MaskFilterFragment;
import com.ztiany.view.scroll.ScrollFragment;
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
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        setAdapter();
    }

    private void setAdapter() {
        mRecyclerView.setAdapter(new ItemAdapter(this, LIST));
    }

    static {
        LIST.add(new Item("事件&滑动", ScrollFragment.class));
        LIST.add(new Item("绘制", CanvasFragment.class));
        LIST.add(new Item("ColorMatrixFilter", ColorMatrixFilterFragment.class));
        LIST.add(new Item("MaskFilter", MaskFilterFragment.class));
        LIST.add(new Item("Path&贝塞尔曲线", BezierFragment.class));
        LIST.add(new Item("自定义View", CustomViewFragment.class));
    }
}

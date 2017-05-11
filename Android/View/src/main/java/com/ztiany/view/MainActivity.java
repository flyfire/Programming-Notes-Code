package com.ztiany.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztiany.view.coordinator.sample.CoordinatorSampleActivity;
import com.ztiany.view.custom.activity.CustomViewActivity;
import com.ztiany.view.draw.canvas.ADrawingActivity;
import com.ztiany.view.draw.color.ColorMatrixFilterActivity;
import com.ztiany.view.draw.color.MaskFilterActivity;
import com.ztiany.view.draw.path.ABezierActivity;
import com.ztiany.view.draw.pathmeasure.PathMeasureActivity;
import com.ztiany.view.nestedscrolling.NestedScrollingActivity;
import com.ztiany.view.scroll.ScrollActivity;
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
        LIST.add(new Item("Scroll", ScrollActivity.class));
        LIST.add(new Item("Color", ColorMatrixFilterActivity.class));
        LIST.add(new Item("Draw", ADrawingActivity.class));
        LIST.add(new Item("MaskFilter", MaskFilterActivity.class));
        LIST.add(new Item("Bezier", ABezierActivity.class));
        LIST.add(new Item("PathMeasure", PathMeasureActivity.class));
        LIST.add(new Item("自定义View", CustomViewActivity.class));
        LIST.add(new Item("NestedScrolling", NestedScrollingActivity.class));
        LIST.add(new Item("CoordinatorSample", CoordinatorSampleActivity.class));
    }
}

package com.ztiany.animator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ztiany.animator.spring.SpringScrollViewFragment;
import com.ztiany.animator.utils.BaseUtils;

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
        LIST.add(new Item("Spring动画", SpringScrollViewFragment.class));
    }
}

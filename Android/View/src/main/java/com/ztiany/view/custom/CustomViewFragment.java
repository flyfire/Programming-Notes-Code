package com.ztiany.view.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.view.BaseViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-08-05 15:44
 */
public class CustomViewFragment extends BaseViewPagerFragment {

    private List<View> mViewList = new ArrayList<>();

    private String[] titles = {
            "水平滑动",
            "BitmapDecor显式大图",
            "Surface Loading",
            "宫格锁",
            "圆环",
            "尺子",
            "正方形布局",
            "可缩放的ImageView"
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewList.add(HScrollLayoutBuilder.buildHScrollLayout(getContext()));
        mViewList.add(new LargeImageView(getContext()));
        mViewList.add(new LoadingView(getContext()));
        mViewList.add(new LockPatternView(getContext()));
        mViewList.add(new Ring(getContext(), null));
        mViewList.add(new RulerView(getContext()));
        mViewList.add(new SquareEnhanceLayout(getContext()));
        mViewList.add(new ZoomImageView(getContext()));
    }

    @Override
    protected PagerAdapter getAdapter() {
        return new CustomViewBasePagerAdapter(titles);
    }

    private class CustomViewBasePagerAdapter extends BasePagerAdapter {

        CustomViewBasePagerAdapter(String[] titles) {
            super(titles);
        }

        @Override
        protected View getItemView(ViewGroup container, int position) {
            return mViewList.get(position);
        }
    }


}

package com.ztiany.view.scroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.view.BaseViewPagerFragment;
import com.ztiany.view.R;
import com.ztiany.view.scroll.sticky.StickyNavBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-08-05 16:09
 */
public class ScrollFragment extends BaseViewPagerFragment {

    private List<View> mViewList = new ArrayList<>();

    String[] mTitles = {
            "多指处理",
            "Scroller",
            "Over Scroller",
            "嵌套滑动",
            "Sticky Navigation"
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewList.add(View.inflate(getContext(), R.layout.scroll_multi_drag, null));
        mViewList.add(View.inflate(getContext(), R.layout.scroll_scroller, null));
        mViewList.add(View.inflate(getContext(), R.layout.scroll_over_scroller, null));
        mViewList.add(View.inflate(getContext(), R.layout.scroll_nested_sample, null));
        mViewList.add(new StickyNavBuilder(getContext()).getView());
    }

    @Override
    protected PagerAdapter getAdapter() {
        return new ScrollAdapter(mTitles);
    }

    private class ScrollAdapter extends BasePagerAdapter {

        ScrollAdapter(String[] titles) {
            super(titles);
        }

        @Override
        protected View getItemView(ViewGroup container, int position) {
            return mViewList.get(position);
        }
    }

}

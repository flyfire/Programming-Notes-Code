package com.ztiany.view.scroll.sticky;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ztiany.view.R;

import static com.ztiany.view.utils.BaseUtils.getResources;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-10 23:41
 */
public class StickyNavBuilder {

    private String[] mTitles = new String[]{"简介", "评价", "相关"};

    private SimpleViewPagerIndicator mIndicator;

    private View view;

    public View getView() {
        return view;
    }

    public StickyNavBuilder(Context context) {
        view = View.inflate(context, R.layout.scroll_nested_sticky, null);
        onViewCreated();
    }

    private void onViewCreated() {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.nested_scroll_viewpager);
        mIndicator = (SimpleViewPagerIndicator) view.findViewById(R.id.nested_scroll_indicator);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mIndicator.setTitles(mTitles);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View item = createItem(container);
                container.addView(item);
                return item;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        viewPager.setCurrentItem(0);
    }

    private View createItem(ViewGroup container) {
        final Context context = container.getContext();
        RecyclerView mRecyclerView = new RecyclerView(context);
        mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView textView = new AppCompatTextView(context);
                textView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                textView.setPadding(padding, padding, padding, padding);
                textView.setGravity(Gravity.CENTER);
                return new RecyclerView.ViewHolder(textView) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(String.valueOf(position));
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
        return mRecyclerView;
    }


}

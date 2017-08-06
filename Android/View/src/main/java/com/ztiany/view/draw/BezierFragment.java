package com.ztiany.view.draw;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.view.BaseViewPagerFragment;
import com.ztiany.view.draw.path.AddPathView;
import com.ztiany.view.draw.path.BezierCircle;
import com.ztiany.view.draw.path.BezierCircleTranslate;
import com.ztiany.view.draw.path.BezierView;
import com.ztiany.view.draw.path.PathEffectView;
import com.ztiany.view.draw.path.PathMeasureCircleSampleBuilder;
import com.ztiany.view.draw.path.ThirdBezierView;
import com.ztiany.view.draw.path.TwoBezierView;
import com.ztiany.view.draw.path.WaterView;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-26 11:37                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class BezierFragment extends BaseViewPagerFragment {

    private String[] titles = {
            "贝塞尔曲线",
            "模拟水波纹",
            "二阶贝塞尔曲线",
            "三阶贝塞尔曲线",
            "圆圈贝塞尔曲线",
            "圆圈贝塞尔曲线Translate",
            "AddPath",
            "PathEffective",
            "PathMeasure Circle"
    };

    @Override
    protected PagerAdapter getAdapter() {
        return new BezierAdapter(titles);
    }

    private class BezierAdapter extends BasePagerAdapter {

        BezierAdapter(String[] titles) {
            super(titles);
        }

        @Override
        protected View getItemView(ViewGroup container, int position) {
            View view = null;
            switch (position) {
                case 0:
                    view = new BezierView(getContext());
                    break;
                case 1:
                    view = new WaterView(getContext());
                    break;
                case 2:
                    view = new TwoBezierView(getContext());
                    break;
                case 3:
                    view = new ThirdBezierView(getContext());
                    break;
                case 4:
                    view = new BezierCircle(getContext());
                    break;
                case 5:
                    view = new BezierCircleTranslate(getContext());
                    break;
                case 6:
                    view = new AddPathView(getContext());
                    break;
                case 7:
                    view = new PathEffectView(getContext());
                    break;
                case 8:
                    view = PathMeasureCircleSampleBuilder.create(getContext());
            }
            return view;
        }
    }


}

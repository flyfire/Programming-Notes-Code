package com.ztiany.view.custom.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ztiany.view.custom.HScrollLayout;


public class HScrollLayoutFragment extends Fragment {

    private HScrollLayout mHScrollLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHScrollLayout = new HScrollLayout(getContext());
        initListView();
        return mHScrollLayout;
    }

    private void initListView() {
        LinearLayout.LayoutParams lp;
        ListView listView;
        for (int i = 0; i < 3; i++) {
            listView = new ListView(HScrollLayoutFragment.this.getContext());
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            listView.setLayoutParams(lp);
            listView.setAdapter(new Adapter(i));
            mHScrollLayout.addView(listView);
        }
    }

    private class Adapter extends BaseAdapter {

        private final int mType;

        Adapter(int type) {
            mType = type;
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                TextView textView = new AppCompatTextView(HScrollLayoutFragment.this.getContext());
                textView.setPadding(40, 40, 40, 40);
                textView.setGravity(Gravity.CENTER);
                convertView = textView;
                if (mType == 0) {
                    textView.setTextColor(Color.BLUE);
                } else if (mType == 1) {
                    textView.setTextColor(Color.RED);

                } else {
                    textView.setTextColor(Color.GREEN);
                }
            }
            TextView textView = (TextView) convertView;
            textView.setText("position = " + position);
            return convertView;
        }
    }


}
    


package com.ztiany.diff_util;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztiany.recyclerview.R;

import java.util.List;

/**
 * @author Ztiany
 * Email: ztiany3@gmail.com
 * Date : 2018-04-25 16:46
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {

    private List<TestBean> mTestBeans;

    public static final String KEY_IMAGE = "key_image";
    public static final String KEY_DES = "key_des";

    DataAdapter(List<TestBean> testBeans) {
        mTestBeans = testBeans;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diff_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        TestBean testBean = mTestBeans.get(position);

        holder.mDes.setText(testBean.getDes());
        holder.mId.setText(String.valueOf(testBean.getId()));
        int drawableId = testBean.getDrawableId();
        if (drawableId == 0) {
            holder.mImage.setImageDrawable(null);
        } else {
            holder.mImage.setImageResource(drawableId);
        }
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position, List<Object> payloads) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle payload = (Bundle) payloads.get(0);
            for (String key : payload.keySet()) {
                switch (key) {
                    case KEY_DES:
                        //这里可以用payload里的数据，不过data也是新的 也可以用
                        holder.mDes.setText(payload.getString(key));
                        break;
                    case KEY_IMAGE:
                        int drawableId = payload.getInt(key);
                        if (drawableId == 0) {
                            holder.mImage.setImageDrawable(null);
                        } else {
                            holder.mImage.setImageResource(drawableId);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mTestBeans == null ? 0 : mTestBeans.size();
    }

    public void setNewData(List<TestBean> newData) {
        mTestBeans = newData;
    }

    static class DataHolder extends RecyclerView.ViewHolder {

        private TextView mDes;
        private TextView mId;
        private ImageView mImage;

        DataHolder(View itemView) {
            super(itemView);
            mDes = (TextView) itemView.findViewById(R.id.tv_des);
            mId = (TextView) itemView.findViewById(R.id.tv_id);
            mImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

    public List<TestBean> getTestBeans() {
        return mTestBeans;
    }

}

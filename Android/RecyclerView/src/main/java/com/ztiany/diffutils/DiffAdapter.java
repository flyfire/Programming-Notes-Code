package com.ztiany.diffutils;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztiany.recyclerview.R;

import java.util.List;

class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.DiffVH> {

    private List<DiffBean> mData;

    DiffAdapter(List<DiffBean> data) {
        this.mData = data;
    }

    void setData(List<DiffBean> data) {
        this.mData = data;
    }

    @Override
    public DiffVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiffVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diff, parent, false));
    }

    @Override
    public void onBindViewHolder(final DiffVH holder, final int position) {
        DiffBean bean = mData.get(position);
        holder.mTitleTv.setText(bean.getName());
        holder.mDescTv.setText(bean.getDesc());
        holder.mImageIV.setImageResource(bean.getPic());
    }

    @Override
    public void onBindViewHolder(DiffVH holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else { //部分改变

            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
            DiffBean bean = mData.get(position);//取出新数据源，（可以不用）

            for (String key : payload.keySet()) {
                switch (key) {
                    case "KEY_DESC":
                        //这里可以用payload里的数据，不过data也是新的 也可以用 
                        holder.mDescTv.setText(bean.getDesc());
                        break;
                    case "KEY_PIC":
                        holder.mImageIV.setImageResource(payload.getInt(key));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class DiffVH extends RecyclerView.ViewHolder {
        TextView mTitleTv, mDescTv;
        ImageView mImageIV;

        DiffVH(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.item_diff_title_tv);
            mDescTv = (TextView) itemView.findViewById(R.id.item_diff_desc_tv);
            mImageIV = (ImageView) itemView.findViewById(R.id.item_diff_image_iv);
        }
    }
} 

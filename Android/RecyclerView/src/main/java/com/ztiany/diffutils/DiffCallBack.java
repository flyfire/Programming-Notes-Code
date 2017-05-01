package com.ztiany.diffutils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class DiffCallBack extends DiffUtil.Callback {

    private List<TestBean> mOldData, mNewData;//老的数据、新的数据

    public DiffCallBack(List<TestBean> oldData, List<TestBean> newData) {
        this.mOldData = oldData;
        this.mNewData = newData;
    }

    //老数据集size 
    @Override
    public int getOldListSize() {
        return mOldData != null ? mOldData.size() : 0;
    }

    //新数据集size 
    @Override
    public int getNewListSize() {
        return mNewData != null ? mNewData.size() : 0;
    }

    /**
     * 被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等。
     * 本例判断name字段是否一致
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldData.get(oldItemPosition).getName().equals(mNewData.get(newItemPosition).getName());
    }

    /**
     * 被DiffUtil调用，用来检查 两个item是否含有相同的数据
     * DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化
     * DiffUtil 用这个方法替代equals方法去检查是否相等。
     * 所以你可以根据你的UI去改变它的返回值
     * 例如，如果你用RecyclerView.Adapter 配合DiffUtil使用，你需要返回Item的视觉表现是否相同。
     * 这个方法仅仅在areItemsTheSame()返回true时，才调用。
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     *                        oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        TestBean beanOld = mOldData.get(oldItemPosition);
        TestBean beanNew = mNewData.get(newItemPosition);
        if (!beanOld.getDesc().equals(beanNew.getDesc())) {
            return false;//如果有内容不同，就返回false 
        }
        if (beanOld.getPic() != beanNew.getPic()) {
            return false;//如果有内容不同，就返回false 
        }
        return true; //默认两个data内容是相同的 
    }

    /**
     * 当areItemsTheSame返回true，且areContentsTheSame 返回false时，DiffUtils会回调此方法，
     * 去得到这个Item（有哪些）改变的payload。
     * 例如，如果你用RecyclerView配合DiffUtils，你可以返回  这个Item改变的那些字段，
     * ItemAnimator}可以用那些信息去执行正确的动画
     * 默认的实现是返回null
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return A payload object that represents the change between the two items.
     * 返回 一个 代表着新老item的改变内容的 payload对象，
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //实现这个方法 就能成为文艺青年中的文艺青年 
        // 定向刷新中的部分更新 
        // 效率最高 
        //只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要） 
        TestBean oldBean = mOldData.get(oldItemPosition);
        TestBean newBean = mNewData.get(newItemPosition);

        //这里就不用比较核心字段了,一定相等 
        Bundle payload = new Bundle();
        if (!oldBean.getDesc().equals(newBean.getDesc())) {
            payload.putString("KEY_DESC", newBean.getDesc());
        }
        if (oldBean.getPic() != newBean.getPic()) {
            payload.putInt("KEY_PIC", newBean.getPic());
        }

        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;//
    }
} 
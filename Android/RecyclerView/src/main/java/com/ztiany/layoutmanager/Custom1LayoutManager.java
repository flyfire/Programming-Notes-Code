package com.ztiany.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

/**
 * http://blog.csdn.net/huachao1001/article/details/51594004
 *
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-29 15:44
 */
class Custom1LayoutManager extends LayoutManager {

    //用于滑动
    private int verticalScrollOffset;//总的滑动量
    private int totalHeight;//总的高度

    //用于回收利用
    //保存所有的Item的上下左右的偏移量信息
    private SparseArray<Rect> allItemFrames = new SparseArray<>();
    //记录Item是否出现过屏幕且还没有回收。true表示出现过屏幕上，并且还没被回收
    private SparseBooleanArray hasAttachedItems = new SparseBooleanArray();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
    }

    //在初始化布局以及adapter数据发生改变（或更换adapter）的时候调用。所以我们在这个方法中对我们的item进行测量以及初始化。
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        //step1 判断状态
        //如果没有item，直接返回
        if (getItemCount() <= 0) return;
        // 跳过preLayout，preLayout主要用于支持动画
        if (state.isPreLayout()) {
            return;
        }

        //step 2 回收view
        //在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        //为什么要这样做呢？主要是考虑到，屏幕上可能还有一些ItemView是继续要留在屏幕上的，我们不直接Remove，而是选择Detach。
        detachAndScrapAttachedViews(recycler);

        //step 3 计算和布局
        int childCount = getItemCount();
        totalHeight = 0;
        //定义竖直方向的偏移量
        int offsetY = 0;

        for (int i = 0; i < childCount; i++) {

            //这里就是从缓存里面取出
            View view = recycler.getViewForPosition(i);
            //将View加入到RecyclerView中
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            totalHeight += height;

            //记录所有Item的位置
            recordItemPosition(offsetY, i, width, height);

            //将竖直方向偏移量增大height
            offsetY += height;
        }

        //如果所有子View的高度和没有填满RecyclerView的高度，
        // 则将高度设置为RecyclerView的高度
        totalHeight = Math.max(totalHeight, getVerticalSpace());

        //step 4
        //上面的for循环里面并没有再调用layoutDecorated()函数，而是在最后调用了recycleAndFillItems()函数，
        // 这个函数是先将不需要的Item进行回收，然后在从缓存中取出需要的Item
        recycleAndFillItems(recycler, state);
    }

    private void recordItemPosition(int offsetY, int i, int width, int height) {
        //将所有的item的上下左右的偏移量记录下来，并且要记录哪些Item需要被回收
        Rect frame = allItemFrames.get(i);
        if (frame == null) {
            frame = new Rect();
        }
        frame.set(0, offsetY, width, offsetY + height);
        // 将当前的Item的Rect边界数据保存
        allItemFrames.put(i, frame);
        // 由于已经调用了detachAndScrapAttachedViews，因此需要将当前的Item设置为未出现过
        hasAttachedItems.put(i, false);
    }


    /**
     * 回收不需要的Item，并且将需要显示的Item从缓存中取出
     */
    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 跳过preLayout，preLayout主要用于支持动画
        if (state.isPreLayout()) {
            return;
        }

        //step 1  获取当前scroll offset状态下的显示区域
        Rect displayFrame = new Rect(0, verticalScrollOffset, getHorizontalSpace(), verticalScrollOffset + getVerticalSpace());

        //将滑出屏幕的Items回收到Recycle缓存中
        Rect childFrame = new Rect();

        //先移除所有不可见的的Item
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childFrame.left = getDecoratedLeft(child);//将给定子视图的左边缘返回它的父视图
            childFrame.top = getDecoratedTop(child);
            childFrame.right = getDecoratedRight(child);
            childFrame.bottom = getDecoratedBottom(child);

            //如果Item没有在显示区域，就说明需要回收
            if (!Rect.intersects(displayFrame, childFrame)) {
                //回收掉滑出屏幕的View
                removeAndRecycleView(child, recycler);
            }
        }

        //重新显示需要出现在屏幕的子View
        for (int i = 0; i < getItemCount(); i++) {
            if (Rect.intersects(displayFrame, allItemFrames.get(i))) {
                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap);
                Rect frame = allItemFrames.get(i);
                //将这个item布局出来
                layoutDecorated(scrap,
                        frame.left,
                        frame.top - verticalScrollOffset,
                        frame.right,
                        frame.bottom - verticalScrollOffset);
            }
        }
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    ///////////////////////////////////////////////////////////////////////////
    // 滑动逻辑
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    //获取垂直方向上的高度
    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //因为在滑动过程中，需要重新对Item进行布局，即从缓存中取出Item进行数据绑定后放在新出现的Item的位置上。
        // 并且，还需要在scrollVerticallyBy最开始调用detachAndScrapAttachedViews(recycler);
        //step 1-1 先detach掉所有的子View
        detachAndScrapAttachedViews(recycler);


        //step 2 处理滑动
        //往下拉dy<0，往上拉dy>0，verticalScrollOffset的范围在 0 到 totalHeight之间
        //实际要滑动的距离
        int travel = dy;
        //如果滑动到最顶部
        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset;//直接顶部
        } else if (verticalScrollOffset + dy > totalHeight - getVerticalSpace()) {//如果滑动到最底部
            travel = totalHeight - getVerticalSpace() - verticalScrollOffset;//直接滑动到最底部
        }
        //将竖直方向的偏移量+travel
        verticalScrollOffset += travel;
        // 平移容器内的item
        offsetChildrenVertical(-travel);//负数往上移，正数往下移动

        //step 1-2
        recycleAndFillItems(recycler, state);
        return travel;
    }


}

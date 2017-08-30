package com.ztiany.itemtouch_helper_extension;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ztiany.itemtouch_helper_extension.helper.Extension;
import com.ztiany.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_RECYCLER_WIDTH = 1000;
    private static final int ITEM_TYPE_ACTION_WIDTH = 1001;
    private static final int ITEM_TYPE_ACTION_WIDTH_NO_SPRING = 1002;//不回弹

    private List<TestModel> mData;
    private Context mContext;

    MainRecyclerAdapter(Context context) {
        mData = new ArrayList<>();
        mContext = context;
    }

    public void setData(List<TestModel> data) {
        mData.clear();
        mData.addAll(data);
    }

    void updateData(List<TestModel> datas) {
        setData(datas);
        notifyDataSetChanged();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_RECYCLER_WIDTH) {//unDo类型
            View view = getLayoutInflater().inflate(R.layout.item_touch_extension_delete, parent, false);//action view_list_repo_action_container容器id
            return new ItemViewHolderWithRecyclerWidth(view);
        }

        View view = getLayoutInflater().inflate(R.layout.item_touch_extension_main, parent, false);//action view_list_repo_action_container 容器id
        if (viewType == ITEM_TYPE_ACTION_WIDTH) {
            return new ItemSwipeWithActionWidthViewHolder(view);
        }

        return new ItemSwipeWithActionWidthNoSpringViewHolder(view);//不回弹
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ItemBaseViewHolder baseViewHolder = (ItemBaseViewHolder) holder;
        baseViewHolder.bind(mData.get(position));
        baseViewHolder.mViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Item Content click: #" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        if (holder instanceof ItemViewHolderWithRecyclerWidth) {//unDo类型
            ItemViewHolderWithRecyclerWidth viewHolder = (ItemViewHolderWithRecyclerWidth) holder;
            viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doDelete(holder.getAdapterPosition());//删除监听
                }
            });
        } else if (holder instanceof ItemSwipeWithActionWidthViewHolder) {//其余两种类型
            ItemSwipeWithActionWidthViewHolder viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;
            viewHolder.mActionViewRefresh.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "Refresh Click" + holder.getAdapterPosition()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }

            );
            viewHolder.mActionViewDelete.setOnClickListener(//删除监听
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            doDelete(holder.getAdapterPosition());
                        }
                    }
            );
        }
    }

    private void doDelete(int adapterPosition) {
        mData.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).position == 1) {
            return ITEM_TYPE_ACTION_WIDTH_NO_SPRING;
        }
        if (mData.get(position).position == 2) {
            return ITEM_TYPE_RECYCLER_WIDTH;
        }
        return ITEM_TYPE_ACTION_WIDTH;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ItemBaseViewHolder extends RecyclerView.ViewHolder {
        TextView mTextTitle;
        TextView mTextIndex;
        View mViewContent;//滑动的内容容器，
        View mActionContainer;//Action容器

        ItemBaseViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_list_main_title);
            mTextIndex = (TextView) itemView.findViewById(R.id.text_list_main_index);
            mViewContent = itemView.findViewById(R.id.view_list_main_content);
            mActionContainer = itemView.findViewById(R.id.view_list_repo_action_container);
        }

        @SuppressLint("SetTextI18n")
        void bind(TestModel testModel) {
            mTextTitle.setText(testModel.title);
            mTextIndex.setText("#" + testModel.position);
        }
    }


    @SuppressWarnings("all")
    class ItemViewHolderWithRecyclerWidth extends ItemBaseViewHolder {

        View mActionViewDelete;

        public ItemViewHolderWithRecyclerWidth(View itemView) {
            super(itemView);
            mActionViewDelete = itemView.findViewById(R.id.view_list_repo_action_delete);
        }

    }

    @SuppressWarnings("all")
    class ItemSwipeWithActionWidthViewHolder extends ItemBaseViewHolder implements Extension {//没有限制宽度的滑动item

        View mActionViewDelete;
        View mActionViewRefresh;

        public ItemSwipeWithActionWidthViewHolder(View itemView) {
            super(itemView);
            mActionViewDelete = itemView.findViewById(R.id.view_list_repo_action_delete);
            mActionViewRefresh = itemView.findViewById(R.id.view_list_repo_action_update);
        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }

    @SuppressWarnings("all")
    class ItemSwipeWithActionWidthNoSpringViewHolder extends ItemSwipeWithActionWidthViewHolder implements Extension {//限制了滑动距离的item

        public ItemSwipeWithActionWidthNoSpringViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();//返回action容器的宽度
        }
    }

}
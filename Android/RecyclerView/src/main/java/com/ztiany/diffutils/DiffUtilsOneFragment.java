package com.ztiany.diffutils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.recyclerview.R;

import java.util.ArrayList;
import java.util.List;


/*
使用步骤：
1：加入我们的列表已经显示了数据集
2：现在从网络加载了新的数据
3：备份现有的数据集，然后创建一个新的数据集合，这个新的数据集合应该中用来添加或者替换原有的数据集合(刷新则是替换，加载更多则是添加)
4：使用DiffUtil来计算两个数据集的不同(diffResult)，并且得出结果(在子线程中进行)
5：得出结果后，用新的数据集合替换原来的数据集，然后调用diffResult.dispatchUpdatesTo(mDiffAdapter);刷新列表
 */
public class DiffUtilsOneFragment extends Fragment {

    private List<DiffBean> mData;
    private DiffAdapter mDiffAdapter;
    private static final int H_CODE_UPDATE = 1;
    private List<DiffBean> mNewData;//增加一个变量暂存newList

    public static DiffUtilsOneFragment newInstance() {
        return new DiffUtilsOneFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem refresh = menu.add(Menu.NONE, 1, 1, "refresh").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onRefresh();
                return true;
            }
        });
        MenuItemCompat.setShowAsAction(refresh, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        mDiffAdapter = new DiffAdapter(mData);
        recyclerView.setAdapter(mDiffAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new DiffBean("Ztiany", "Android", R.drawable.head_portrait1));
        mData.add(new DiffBean("ChenLong", "Java", R.drawable.head_portrait2));
        mData.add(new DiffBean("WangHan", "Python", R.drawable.head_portrait3));
        mData.add(new DiffBean("LiDaJun", "Groovy", R.drawable.head_portrait4));
        mData.add(new DiffBean("MaiMingLiang", "Ruby", R.drawable.head_portrait5));
        mData.add(new DiffBean("ZhangCheng", "C++", R.drawable.head_portrait6));
        mData.add(new DiffBean("DaiMaJia", "C", R.drawable.head_portrait7));
        mData.add(new DiffBean("DaTouGui", "RxJava", R.drawable.head_portrait8));
    }


    /**
     * 模拟刷新操作
     */
    public void onRefresh() {
        mNewData = new ArrayList<>();
        for (DiffBean bean : mData) {
            mNewData.add(bean.clone());//clone一遍旧数据 ，模拟刷新操作
        }
        mNewData.add(new DiffBean("ZhangHongYang", "Android", R.drawable.head_portrait9));//模拟新增数据

        DiffBean diffBean0 = mNewData.get(0);
        diffBean0.setDesc(diffBean0.getDesc().concat("+"));
        diffBean0.setPic(R.drawable.head_portrait15);//模拟修改数据

        DiffBean diffBean = mNewData.get(1);//模拟数据位移
        mNewData.remove(diffBean);
        mNewData.add(diffBean);

        //利用DiffUtil.calculateDiff()方法，传入一个规则DiffUtil.Callback对象，和是否检测移动item的 boolean变量，得到DiffUtil.DiffResult 的对象
        //mAdapter.notifyDataSetChanged();//以前普通青年的我们只能这样，现在我们是文艺青年了，有新宠了
        new Thread(new Runnable() {
            @Override
            public void run() {
                //放在子线程中计算DiffResult
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mData, mNewData), true);
                Message message = mHandler.obtainMessage(H_CODE_UPDATE);
                message.obj = diffResult;//obj存放DiffResult
                message.sendToTarget();
            }
        }).start();
    }

    //demo 就不考虑泄漏了
    @SuppressWarnings("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case H_CODE_UPDATE:
                    //取出Result
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                    //别忘了将新数据给Adapter
                    mData = mNewData;
                    mDiffAdapter.setData(mData);
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter
                    diffResult.dispatchUpdatesTo(mDiffAdapter);
                    break;
            }
        }
    };

}

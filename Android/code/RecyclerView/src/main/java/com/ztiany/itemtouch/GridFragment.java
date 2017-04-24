package com.ztiany.itemtouch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.itemtouch.help.OnStartDragListener;
import com.ztiany.recyclerview.R;

public class GridFragment extends Fragment {


    private ItemTouchHelper mTouchHelper;

    static GridFragment newInstance() {


        return new GridFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(LayoutInflater.from(getContext()), new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mTouchHelper.startDrag(viewHolder);
            }
        });

        recyclerView.setAdapter(recyclerAdapter);

        mTouchHelper = new ItemTouchHelper(new HelperCallBack(recyclerAdapter));
        mTouchHelper.attachToRecyclerView(recyclerView);


    }


}

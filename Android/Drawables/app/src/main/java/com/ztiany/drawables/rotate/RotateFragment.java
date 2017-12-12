package com.ztiany.drawables.rotate;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.drawables.R;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-12-10 23:02
 */
public class RotateFragment extends Fragment {

    public static Fragment newInstance() {
        return new RotateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rotate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View view1 = view.findViewById(R.id.rotate_view_1);
        View view2 = view.findViewById(R.id.rotate_view_2);
        Animatable rotateDrawable = (Animatable) view2.getBackground();
        rotateDrawable.start();
    }
}

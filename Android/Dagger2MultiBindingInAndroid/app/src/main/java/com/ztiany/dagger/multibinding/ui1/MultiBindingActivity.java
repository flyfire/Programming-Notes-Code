package com.ztiany.dagger.multibinding.ui1;

import android.os.Bundle;
import android.widget.TextView;

import com.ztiany.dagger.multibinding.BaseActivity;
import com.ztiany.dagger.R;
import com.ztiany.dagger.multibinding.ActivityComponentBuilder;
import com.ztiany.dagger.multibinding.HasActivitySubcomponentBuilders;

import javax.inject.Inject;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-17 16:35
 */
public class MultiBindingActivity extends BaseActivity {


    private TextView mTextView;

    @Inject
    MultiBindingActivityPresenter mMultiBindingActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mTextView = (TextView) findViewById(R.id.tv_message);
        mMultiBindingActivityPresenter.init();
    }

    @Override
    protected void injectMembers(HasActivitySubcomponentBuilders hasActivitySubcomponentBuilders) {

        ActivityComponentBuilder activityComponentBuilder = hasActivitySubcomponentBuilders.getActivityComponentBuilder(MultiBindingActivity.class);

        ((MultiBindingActivityComponent.Builder) activityComponentBuilder)
                .activityModule(new MultiBindingActivityComponent.MultiBindingActivityModule(this))
                .build()
                .injectMembers(this);
    }

    public void updateText(String text) {
        mTextView.setText(text);
    }
}

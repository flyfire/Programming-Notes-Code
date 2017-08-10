package com.ztiany.mediaselector;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * 包含了Activity和Fragment的相关操作
 */
public class ContextWrapper {

    private Fragment mFragment;
    private Activity mActivity;

    public static ContextWrapper create(Activity activity) {
        ContextWrapper context = new ContextWrapper();
        context.mActivity = activity;
        return context;
    }

    public Context getContext() {
        if (mActivity != null) {
            return mActivity;
        } else {
            return mFragment.getActivity();
        }
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (mActivity != null) {
            mActivity.startActivityForResult(intent, requestCode, options);
        } else {
            mFragment.startActivityForResult(intent, requestCode, options);
        }
    }

    public static ContextWrapper create(Fragment fragment) {
        ContextWrapper context = new ContextWrapper();
        context.mFragment = fragment;
        return context;
    }

    public void startService(Intent intent) {
        if (mActivity != null) {
            mActivity.startService(intent);
        } else {
            FragmentActivity activity = mFragment.getActivity();
            activity.startService(intent);
        }
    }

    public void stopService(Class<? extends Service> payPalServiceClass) {
        if (mActivity != null) {
            mActivity.stopService(new Intent(mActivity, payPalServiceClass));
        } else {
            mFragment.getActivity().stopService(new Intent(mFragment.getActivity(), payPalServiceClass));
        }
    }
}
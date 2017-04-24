package com.ztiany.diffutils;

import android.support.annotation.DrawableRes;

/**
 * <br/>    Description  :
 * <br/>    Email    : ztiany3@gmail.com
 *
 * @author Ztiany
 *         <p>
 *         Date : 2016-10-25 23:03
 */

class TestBean implements Cloneable {




    private String mName;
    private String mDesc;
    @DrawableRes
    private int mPic;

    TestBean(String name, String desc, int pic) {
        mName = name;
        mDesc = desc;
        mPic = pic;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public int getPic() {
        return mPic;
    }

    public void setPic(int pic) {
        mPic = pic;
    }


    @Override
    protected TestBean clone() {
        try {
            return (TestBean) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

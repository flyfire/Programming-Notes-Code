package com.ztiany.diffutils;

import android.support.annotation.DrawableRes;


class DiffBean implements Cloneable {

    private String mName;
    private String mDesc;
    @DrawableRes
    private int mPic;

    DiffBean(String name, String desc, int pic) {
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
    protected DiffBean clone() {
        try {
            return (DiffBean) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

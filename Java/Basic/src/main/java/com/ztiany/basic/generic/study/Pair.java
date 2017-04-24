package com.ztiany.basic.generic.study;

/**
 * <br/>   Description：
 * <br/>    Email: ztiany3@gmail.com
 *
 * @author Ztiany
 *         Date : 2016-12-11 13:47
 */
public class Pair<T> {
    private T mFirst;
    private T mSecond;

    void setFirst(T t) {
        mFirst = t;
    }
    public T getFirst() {
        return mFirst;
    }

    public void setSecond(T second) {
        mSecond = second;
    }

    public T getSecond() {
        return mSecond;
    }
}

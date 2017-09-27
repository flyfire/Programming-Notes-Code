package com.ztiany.androidaspectj03;

import android.util.Log;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-27 16:12
 */
class AspectTarget1 {

    private static final String TAG = AspectTarget1.class.getSimpleName();

    void runAll() {
        String ztiany = login("ztiany", "123456");
        saveUserInfo(ztiany);
        showUserInfo(ztiany);
    }


    public String login(String name, String password) {
        Log.d(TAG, "login() called with: name = [" + name + "], password = [" + password + "]");
        return name;
    }

    public void saveUserInfo(String info) {
        Log.d(TAG, "saveUserInfo() called with: info = [" + info + "]");
    }

    private void showUserInfo(String ztiany) {
        Log.d(TAG, "showUserInfo() called with: ztiany = [" + ztiany + "]");
    }

}

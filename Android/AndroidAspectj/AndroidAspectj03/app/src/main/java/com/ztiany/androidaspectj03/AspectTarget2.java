package com.ztiany.androidaspectj03;

import android.util.Log;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-27 16:12
 */
class AspectTarget2 {

    private static final String TAG = AspectTarget2.class.getSimpleName();

    private void throwError() {
        int a = 0;
        int b = 3;
        int result = b / a;
    }

    void print1() {
        doPrint("print 1 called");
    }

    void print2() {
        doPrint("print 2 called");
    }

    private void doPrint(String abc) {
        Log.d(TAG, "doPrint() called with: abc = [" + abc + "]");
    }


}

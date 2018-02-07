package com.ztiany.jni.sample;

import java.util.Arrays;

/**
 * @author Ztiany
 *         Email ztiany3@gmail.com
 *         Date 2017/4/5 22:12
 */
public class JNIMain {

    private JniBridge mJniBridge = new JniBridge();

    static {
        System.loadLibrary("jni/native");
    }

    //generate windows dll：
    // gcc -Wl,--add-stdcall-alias -I "E:\DevTools\Java\JDK1.8\include" -I "E:\DevTools\Java\JDK1.8\include\win32" -shared -o native.dll native-lib.c Utils.c
    public static void main(String... args) {
        JNIMain jniMain = new JNIMain();
        jniMain.encryption();
    }


    //返回字符串
    private void stringFromC() {
        String stringFromC = JniBridge.stringFromC();
        System.out.println(stringFromC);
    }

    //模拟登录
    private void intFromC() {
        int a = 10;
        int b = 20;
        System.out.println("intFromC: " + (mJniBridge.intFromC(a, b)));
    }

    //修改每个元素后返回
    private void addArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        mJniBridge.addArray(arr, 100);
        System.out.println("addArray: " + Arrays.toString(arr));
    }

    //c语言的冒泡排序
    private void bubbleSort() {
        int[] originArr = initIntArr();
        System.out.println("originArr: " + Arrays.toString(originArr));
        long start = System.currentTimeMillis();
        mJniBridge.bubbleSort(originArr);
        System.out.println("originArr: " + Arrays.toString(originArr));
        System.out.println("bubbleSort use time: " + (System.currentTimeMillis() - start));
    }

    //加密
    private void encryption() {
        String password = "Java 哈哈->";
        String encryption = mJniBridge.encryption(password);
        System.out.println("加密 " + encryption);
    }

    //让C调用Java
    private void callJava() {
        mJniBridge.callJava("Java message");
    }

    //让C抛出异常
    private void throwError() {
        try {
            mJniBridge.throwError("抛出一个异常吧");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //调用C动态方法注册
    private void dynamicRegisterFromJni() {
        String registerFromJni = mJniBridge.dynamicRegisterFromJni();
        System.out.println(registerFromJni);
    }

    private int[] initIntArr() {
        int[] arr = new int[10000];
        for (int x = 0; x < arr.length; x++) {
            arr[x] = (int) (Math.random() * 10000 + 1);
        }
        return arr;
    }


}

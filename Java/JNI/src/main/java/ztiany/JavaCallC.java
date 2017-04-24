package ztiany;

import java.util.Arrays;

/**
 * @author Ztiany
 *         Email ztiany3@gmail.com
 *         Date 2017/4/8 7:31
 */
public class JavaCallC {

    private void initIntArr(int[] arr) {
        for (int x = 0; x < arr.length; x++) {
            arr[x] = (int) (Math.random() * 10000 + 1);
        }
    }

    void run() {
        //简单调用
        helloC();
        //返回字符串
        stringFromC();
        //传参
        System.out.println(login(2017, 1234));
        //改变数组
        int[] addArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] added = add(addArr, 30);
        System.out.println("added = " + Arrays.toString(added));
        //排序
        int[] ints = new int[10000];
        initIntArr(ints);
        cSort(ints);
        System.out.println(Arrays.toString(ints));
        //传递字符串
        System.out.println(encryption("Java "));
    }


    //调用C方法
    public static native void helloC();

    //返回字符串
    public static native String stringFromC();

    //模拟登录
    public native int login(int card, int password);

    //修改每个元素后返回
    public native int[] add(int origin[], int add);

    //c语言的冒泡排序
    public native void cSort(int[] arr);

    //加密
    public native String encryption(String password);

    //java排序
    private static void javaSort(int[] arr) {
        //冒泡排序
        for (int x = 0; x < arr.length - 1; x++) {
            for (int y = 0; y < arr.length - x - 1; y++) {
                if (arr[y] > arr[y + 1]) {
                    int temp = arr[y];
                    arr[y] = arr[y + 1];
                    arr[y + 1] = temp;
                }
            }
        }
    }
}

package com.ztiany.basic.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ztiany
 * Email: ztiany3@gmail.com
 */
public class ReferenceQueueMain {

    private static ReferenceQueue<byte[]> rq = new ReferenceQueue<>();
    private static int _1M = 1024 * 1024;

    public static void main(String... args) {
        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();

        startMonitor();

        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[_1M];
            //在对象被GC的同时，会把该对象的包装类即weakReference放入到ReferenceQueue里面
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes, rq);
            map.put(weakReference, value);
        }

        System.out.println("map.size->" + map.size());
    }

    @SuppressWarnings("unchecked")
    private static void startMonitor() {
        Thread thread = new Thread(() -> {

            try {
                int cnt = 0;
                WeakReference<byte[]> k;

                Reference<? extends byte[]> remove = rq.remove();
                while ((k = (WeakReference) remove) != null) {
                    System.out.println((cnt++) + "回收了:" + k + " and it get = " + remove.get());//null
                }

            } catch (InterruptedException e) {
                //结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


}

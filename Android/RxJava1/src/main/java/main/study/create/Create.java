package main.study.create;

import main.study.filter.FilterMain;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * Date:2016-03-26.0:02
 * 描述：
 */
public class Create {

    private static final Random RANDOM = new Random();


    public static void main(String[] args) {

        testRepeatWhen();

    }

    private static void testRepeatWhen() {


        Observable.just(null)
                .flatMap(new Func1<Object, Observable<String>>() {
                    @Override
                    public Observable<String> call(Object o) {
                        //模拟获取数据
                        return Observable.just(getNumber());
                    }
                }).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                //没两秒订阅
                return observable.delay(2, TimeUnit.SECONDS);
            }

        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });

        waitMain();//防止主线程退出，导致线程池shutdown

    }


    private static void waitMain() {
        synchronized (FilterMain.class) {
            try {
                FilterMain.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getNumber() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(RANDOM.nextInt());
    }
}

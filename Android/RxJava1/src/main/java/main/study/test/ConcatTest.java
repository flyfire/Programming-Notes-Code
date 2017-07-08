package main.study.test;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * @author Administrator
 *         Date:2016-06-07-0007.22:35
 *         描述：
 */
public class ConcatTest {
    private static BehaviorSubject<String> mBehaviorSubject = BehaviorSubject.create();

    private static View sView = new View();

    public static void main(String[] args)   {

        start();

//        new Thread(ConcatTest::destroy).start();

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        destroy();


        synchronized (ConcatTest.class) {
            try {
                ConcatTest.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void start() {
        Observable.just(getDataFormRemote())
                .subscribeOn(Schedulers.io())
                .compose(getTransformer())
                .observeOn(Schedulers.computation())
                .doOnUnsubscribe(() -> System.out.println("doOnUnsubscribe"))
                .subscribe(s -> {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sView.println(s);
                }, System.err::println);
    }

    private static void destroy() {
        mBehaviorSubject.subscribe(s -> {
            System.out.println("mBehaviorSubject subscribe ");
            sView = null;
        });

        mBehaviorSubject.onNext("DD");
//        sView = null;
    }

    private static Observable.Transformer<String, String> getTransformer() {
        return stringObservable -> stringObservable.takeUntil(mBehaviorSubject);
    }

    private static String getDataFormRemote() {
        try {
            Thread.sleep(110);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "远程数据";
    }


    static class View {
        void println(String s) {
            System.out.println("println run");

            System.out.println(s);
        }
    }


}

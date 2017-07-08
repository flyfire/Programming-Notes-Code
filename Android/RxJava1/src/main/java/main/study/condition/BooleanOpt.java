package main.study.condition;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * @author Administrator
 *         Date:2016-04-01.0:57
 *         描述：
 */
public class BooleanOpt {

    public static void main(String[] args) {
//        testAll();
//        testAmb();
//        testContains();
        testInEmpty();
//        testExists();
//        testDefaultIfEmpty();
//        testSequenceEqual();
//        testSkipUntil();
//        testSkipWhile();
            testTakeUntil();//当第二个Observable发射了一项数据或者终止时，丢弃原始Observable发射的任何数据
            testTakeWhile();//发射Observable发射的数据，直到一个指定的条件不成立
    }

    private static void testTakeWhile() {

    }

    private static void testTakeUntil() {

    }

    private static void testSkipWhile() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);


        interval.skipWhile(new Func1<Long, Boolean>() {
            @Override
            public Boolean call(Long aLong) {
                return aLong < 10;//aLong在前十秒小于10 都是成立的，所以在第十一秒开始才发射数据
            }
        }).subscribe(System.out::println);


        mainWait();

    }

    private static void testSkipUntil() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);

        Observable<Integer> delay = Observable.just(1).delay(4, TimeUnit.SECONDS);

        interval.skipUntil(delay)//直到delay发射了一个数据，interval才开始发生数据
                .subscribe(System.out::println);

        mainWait();

    }

    private static void mainWait() {
        synchronized (Object.class) {
            try {
                Object.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testSequenceEqual() {
        Observable<Integer> range = Observable.range(1, 4);

        Observable<Integer> just = Observable.just(1, 2, 3, 4);

        Observable.sequenceEqual(range, just, new Func2<Integer, Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer, Integer integer2) {
                return integer.equals(integer2);
            }
        })
                .subscribe(System.out::println);

    }

    private static void testDefaultIfEmpty() {
        Observable.empty()
                .defaultIfEmpty("S")
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println(o);
                    }
                });
    }

    private static void testExists() {
        Observable.range(1, 100)
                .exists(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer == 20;
                    }
                }).subscribe(System.out::println);
    }

    private static void testInEmpty() {
        Observable.just(1)
                .isEmpty()
                .subscribe(System.out::println);
    }

    private static void testContains() {
        Observable.range(1, 100)
                .contains(3)//这里调用的equals
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println(aBoolean);
                    }
                });
    }

    private static void testAmb() {
        Observable<Integer> delay = Observable.range(1, 20)
                .delay(10, TimeUnit.SECONDS);

        Observable<Integer> just = Observable.just(12);

        Observable.amb(delay, just)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    private static void testAll() {
        Subscription subscribe = Observable.range(1, 10)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 20;
                    }
                }).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println(aBoolean);
                    }
                });
    }

}

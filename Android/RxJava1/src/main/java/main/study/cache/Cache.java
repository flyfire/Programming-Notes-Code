package main.study.cache;

import main.study.filter.FilterMain;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 缓存
 */
public class Cache {

    public static void main(String[] args) {
        //testRetryWhen();
        //testRetry();
        //testOnErrorReturn();
        //testOnErrorResumeNext();
        testOnExceptionResumeNext();
    }

    private static void testOnExceptionResumeNext() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        subscriber.onError(new AssertionError("dd"));
                    }
                    subscriber.onNext(i + "--->string");
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .onExceptionResumeNext(Observable.just("妈蛋 一次了"))
                .subscribe(
                        s -> {
                            System.out.println(s);
                        }
                );

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testOnErrorResumeNext() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        subscriber.onError(new IllegalArgumentException("dd"));
                        break;
                    }
                    subscriber.onNext(i + "--->string");
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        //发生了两个数据
                        return Observable.just("妈蛋，异常了", "妈蛋，又异常了");
                    }
                }).subscribe(
                s -> {
                    System.out.println(s);
                }
        );

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testOnErrorReturn() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        subscriber.onError(new IllegalArgumentException("dd"));
                        break;
                    }
                    subscriber.onNext(i + "--->string");
                }
            }
        }).subscribeOn(Schedulers.newThread()).onErrorReturn(new Func1<Throwable, String>() {
            @Override
            public String call(Throwable throwable) {
                return "妈蛋  异常了";
            }
        }).subscribe(
                s -> {
                    System.out.println(s);
                }
        );

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testRetry() {
        Observable.just(null)
                .flatMap(new Func1<Object, Observable<String>>() {
                    @Override
                    public Observable<String> call(Object s) {
                        return Observable.just("aa");
                    }
                }).retry(10)//响应的是onError
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });
    }



}

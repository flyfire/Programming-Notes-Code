package main.study.helper;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;
import rx.schedulers.Timestamped;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *         Date:2016-03-31.20:35
 *         描述：
 */
public class HelperOpt {

    public static void main(String[] args) {
//        testDelay();//计算
//        testDelay2();
//        testDelay3();

//        testOnEach();
//        testOnNext();
//        testOnCompleted();
//        testOnError();
//        testOnTerminate();
//        testFinallyDo();
//        testDoAfterTerminate();//终止
//        testOnU();
//        testOnRequest();//for test

//        testTimeInterval();//immediate
//        testTimeTamp();

//        testUsing();





//        testOnSubscribe();

//        testMaterialize();
//        testDematerialize();

//        mainWait();

    }

    private static void testUsing() {
        Observable
                .using(new Func0<String>() {
                    @Override
                    public String call() {
                        System.out.println("Func0 run");
                        return "A";
                    }
                }, new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {

                        System.out.println("Func1 run");

                        return Observable.just(s);
                    }
                }, new Action1<String>() {
                    @Override
                    public void call(String s) {

                        System.out.println("recycler " + s);
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
    }

    private static void testTimeTamp() {
        Observable.range(1, 100)
                .timestamp()
                .subscribe(new Action1<Timestamped<Integer>>() {
                    @Override
                    public void call(Timestamped<Integer> integerTimestamped) {
                        System.out.println(integerTimestamped);
                    }
                });
    }

    private static void testTimeInterval() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(i * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    }
                    subscriber.onNext(String.valueOf(i));
                }
                subscriber.onCompleted();
            }
        }).timeInterval()
                .subscribe(new Subscriber<TimeInterval<String>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TimeInterval<String> stringTimeInterval) {
                        System.out.println(stringTimeInterval);
                    }
                });
    }

    private static void testMaterialize() {
        Observable.just(1)
                .materialize()
                .subscribe(new Action1<Notification<Integer>>() {
                    @Override
                    public void call(Notification<Integer> integerNotification) {
                        System.out.println(integerNotification.getKind());
                        System.out.println(integerNotification.getValue());
                    }
                });
    }

    private static void testOnRequest() {
        Subscription subscribe = Observable.just(1)
                .delay(3, TimeUnit.SECONDS)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer);
                    }
                }).doOnRequest(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    private static void testOnU() {
        Subscription subscribe = Observable.just(1)
                .delay(3, TimeUnit.SECONDS)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer);
                    }
                })
                .delay(2, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())//指定下面运行的线程。
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnUnsubscribe" + Thread.currentThread());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
        try {
            Thread.sleep(3500);
            subscribe.unsubscribe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testDoAfterTerminate() {
        Subscription subscribe = Observable.just(1)
                .delay(3, TimeUnit.SECONDS)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        int i = 1 / 0;
                        return String.valueOf(integer);
                    }
                })
                .observeOn(Schedulers.io())//指定下面运行的线程。
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnTerminate" + Thread.currentThread());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    private static void testFinallyDo() {
        Observable.just(1)
                .delay(3, TimeUnit.SECONDS)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer);
                    }
                })
                .observeOn(Schedulers.io())//指定下面运行的线程。
                .finallyDo(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnTerminate" + Thread.currentThread());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    private static void testOnTerminate() {

        Observable.just(1)
                .delay(5, TimeUnit.SECONDS)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer);
                    }
                })
                .observeOn(Schedulers.io())//指定下面运行的线程。
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnTerminate" + Thread.currentThread());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });


    }

    private static void testOnError() {
        Observable.error(new RuntimeException())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("doOnError " + throwable);
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }

    private static void testOnCompleted() {
        Observable.just(1)
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnCompleted");
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    private static void testOnSubscribe() {
        Observable.just(1)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnSubscribe1");
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return String.valueOf(integer);
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnSubscribe2");
                    }
                })
                .map(new Func1<String, Integer>() {

                    @Override
                    public Integer call(String s) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return Integer.parseInt(s);
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnSubscribe3");
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });


    }

    private static void testOnNext() {
        Observable.range(1, 100)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                }).subscribe(System.out::println);
    }

    private static void testOnEach() {
        Observable.range(1, 100)
                .doOnEach(new Action1<Notification<? super Integer>>() {
                    @Override
                    public void call(Notification<? super Integer> notification) {
                        Object value = notification.getValue();
                        System.out.println("value = " + value);
                    }
                }).subscribe(System.out::println);
    }

    private static void testDelay3() {
        Observable.just(123)
                .delay(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return Observable.range(1, 100);//此Observable正常结束时，原始的发射数据。
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    private static void testDelay2() {
        Observable.just(123)
                .delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(System.out::print);
    }

    private static void testDelay() {
        Observable.just(123)
                .delay(3, TimeUnit.SECONDS)
                .subscribe(System.out::print);

    }


    private static void mainWait() {
        synchronized (HelperOpt.class) {
            try {
                HelperOpt.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

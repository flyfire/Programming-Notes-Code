package main.study.cache;

import main.study.filter.FilterMain;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @Author Administrator
 * Date:2016-03-25.23:40
 * 描述：
 */
public class Cache {

    public static void main(String[] args) {
//        testRetryWhen();
//        testRetry();
//        testOnErrorReturn();
//        testOnErrorResumeNext();

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


    private static boolean tokenOk = false;

    private static void testRetryWhen() {
        Observable.just(null)
                .flatMap(new Func1<Object, Observable<String>>() {
                    @Override
                    public Observable<String> call(Object o) {
                        System.out.println("tokenOk = "+tokenOk);
                        if (tokenOk) {
                            return Observable.just("获取数据--->你好");
                        }
                        throw new TokenInvalidateException();
                    }
                }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (throwable instanceof TokenInvalidateException) {
                            return Observable.just("get Token").doOnNext(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    tokenOk = true;
                                }
                            });
                        } else {
                            return Observable.error(throwable);
                        }
                    }
                });

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });


    }


    private static class TokenInvalidateException extends RuntimeException {

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


}

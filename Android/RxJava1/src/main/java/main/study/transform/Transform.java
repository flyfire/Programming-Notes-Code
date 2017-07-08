package main.study.transform;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * Date:2016-03-14.21:23
 * 描述：
 */
public class Transform {

    public static void main(String[] args) throws InterruptedException {
//        testMap();
//        testMapIterable();
//        testBuffer();
        testScan();
//        testGroupBy();
//        testWindow();
//        testFlatMap1();
//        testFlatMap2();
    }

    private static void testFlatMap2() {
        File file = new File(".");
        Observable.from(file.listFiles())
                .flatMap(new Func1<File, Observable<String>>() {
                    @Override
                    public Observable<String> call(File file) {
                        return getFileDetail(file);//这里返回的是一个异步操作的Observable，如果这里不是异步的，将会串行执行
                    }
                })
                .subscribe(new Subscriber<String>() {
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


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //比如为我有一个异步操作，要去获取一个File里所有的文件与文件夹名
    private static Observable<String > getFileDetail(File file) {
        return Observable.just(file)
                .map(new Func1<File, String>() {
                    @Override
                    public String call(File file) {
                        return Arrays.toString(file.list());
                    }
                }).subscribeOn(Schedulers.newThread())//这里应用了异步操作
                .delay(2, TimeUnit.SECONDS);

    }

    private static void testFlatMap1() {

        File file = new File(".");

        Observable.just(file)//file是单个对象
                .flatMap(new Func1<File, Observable<?>>() {
                    @Override
                    public Observable<String> call(File file) {
                        return Observable.from(file.list());//使用flatMap的from从原来的file变换出string[],并且单个发射这些string
                    }
                }).subscribe(new Subscriber<Object>() {
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

    private static void testWindow() {
        //定义邮件内容
        final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!",};
        //创建Observable
        Observable.create(subscriber -> {
            if (subscriber.isUnsubscribed()) {
                return;
            }
            try {
                Random random = new Random();
                for (; ; ) {
                    //随机参数一份邮件
                    String contentMail = mails[random.nextInt(mails.length)];
                    //发布邮件
                    subscriber.onNext(contentMail);
                    Thread.sleep(1000);
                }


            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).window(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Observable<Object>>() {
                    @Override
                    public void call(Observable<Object> objectObservable) {
                        System.out.println(objectObservable);
                        objectObservable.subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                System.out.println(o);
                            }
                        });
                    }
                });
    }

    private static void testGroupBy() throws InterruptedException {
        Observable.just(1, 4, 5, 8, 1, 4)
                .take(10)
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer aLong) {
                        return aLong % 3;
                    }
                }).subscribe(new Subscriber<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GroupedObservable<Integer, Integer> longLongGroupedObservable) {
                System.out.println("getKey" + longLongGroupedObservable.getKey());
                longLongGroupedObservable.subscribe(
                        System.out::println
                );
            }
        });

        Thread.sleep(100000);

    }

    private static void testScan() {
        Observable.just("A", "", "B", "C", "D", "E")
                .scan( new Func2<String, String, String>() {
                    @Override
                    public String call(String integer, String integer2) {
                        System.out.println(integer+"----------"+integer2);
                       return integer+integer2;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String integer) {
                        System.out.println("++" + integer);
                    }
                });
    }

    private static void testBuffer() {

        //定义邮件内容
        final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!",};
        //创建Observable
        Observable.create(subscriber -> {
            if (subscriber.isUnsubscribed()) {
                return;
            }
            try {
                Random random = new Random();
                for (; ; ) {
                    //随机参数一份邮件
                    String contentMail = mails[random.nextInt(mails.length)];
                    //发布邮件
                    subscriber.onNext(contentMail);
                    Thread.sleep(1000);
                }


            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).buffer(3)
                .subscribe(new Action1<List<Object>>() {
                    @Override
                    public void call(List<Object> objects) {
                        System.out.println(objects);
                        System.out.println(Thread.currentThread());
                    }
                });
    }

    private static void testMapIterable() {
        File directory = new File(".");//当前文件夹
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }


        Observable.just(directory)
                .flatMapIterable(new Func1<File, Iterable<File>>() {
                    @Override
                    public Iterable<File> call(File file) {
                        return Arrays.asList(file.listFiles());
                    }
                }).subscribe(new Subscriber<File>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);

            }

            @Override
            public void onNext(File file) {
                System.out.println(file);

            }
        });
    }


    private static void testMap() {

        Observable.range(1, 10)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return transform(integer);
                    }
                }).observeOn(Schedulers.computation())
               . subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });



        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static Observable<File> listFile(File file) {

        if (file.isDirectory()) {
            return Observable.from(file.listFiles()).flatMap(new Func1<File, Observable<File>>() {
                @Override
                public Observable<File> call(File file) {
                    return listFile(file);
                }
            });
        } else {
            return Observable.just(file);
        }
    }

    private static String transform(int i) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(i);
    }

}

package main.study.cutomer;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 *         Date:2016-04-02.12:41
 *         描述：
 */
public class TestCustomOpt {


    private static Executor sExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {

       Observable.just(32)
                .lift(new Test1Opt<>(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer);
                    }
                })).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });


        Observable.range(1, 30)
                .compose(new Test1Transformer())
                .compose(ApplyComputer.applySchedulers())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                        System.out.println(Thread.currentThread());

                    }
                });

    }


    public static class Test1Opt<T, R> implements Observable.Operator<T, R> {

        private Func1<R, T> mTest1Transform;

        public Test1Opt(Func1<R, T> test1Transform) {
            mTest1Transform = test1Transform;
        }


        @Override
        public Subscriber<? super R> call(Subscriber<? super T> subscriber) {
            return new Subscriber<R>() {
                @Override
                public void onCompleted() {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                        subscriber.setProducer(new Producer() {
                            @Override
                            public void request(long n) {

                            }
                        });
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(e);
                    }
                }

                @Override
                public void onNext(R r) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(mTest1Transform.call(r));

                    }
                }
            };
        }
    }


    public static class Test1Transformer implements Observable.Transformer<Integer, String> {

        @Override
        public Observable<String> call(Observable<Integer> integerObservable) {
            return integerObservable.lift(new Test1Opt<String, Integer>(new Func1<Integer, String>() {
                @Override
                public String call(Integer integer) {
                    return integer + "dddd";
                }
            }));
        }
    }



    public static class ComputerSchedulerTrans implements Observable.Transformer{

        @Override
        public Object call(Object o) {
            return ((Observable)o).subscribeOn(Schedulers.computation()).observeOn(Schedulers.from(sExecutor));
        }
    }

    public static class ApplyComputer{

        private static ComputerSchedulerTrans mComputerSchedulerTrans = new ComputerSchedulerTrans();

      static    <T> Observable.Transformer<T, T> applySchedulers() {
            return (Observable.Transformer<T, T>) mComputerSchedulerTrans;
        }
    }

}

package diff;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * RxJava2中的概念
 *
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class RxJava2 {

    public static void main(String... args) {
        flowableWithObservable();
        createSource();
        disposableObserver();
        operator();
    }

    private static void operator() {
        //        操作符 (operator) 可以完成三种操作：
        //              以某种方式操作或组合数据
        //              以某种方式操作线程
        //              以某种方式操作发射对象
        String first = Flowable.fromCallable(() -> "你好啊").blockingFirst();
        System.out.println(first);
    }


    private static void createSource() {
        //从Callable创建Flowable
        Flowable.fromCallable(() -> "abc");
        //从Callable创建Observable
        Observable.fromCallable(() -> "Hello");
        //从Callable创建Maybe，Maybe标识可能不发生数据，也可能发送数据
        Maybe.fromCallable(() -> "Hello");
        //Single只发射一个数据
        Single.fromCallable(() -> "Hello");
        //Completable不发射数据，只有完成或者失败
        Completable.fromCallable(() -> "Ignored!");
        Completable.fromAction(() -> System.out.println("Hello"));
        Completable.fromRunnable(() -> System.out.println("Hello"));
    }

    private static void flowableWithObservable() {
        //        Flowable 对比 Observable，可观测的流，RxJava 2 当中表示这种过程的类型有两种：分别是 Flowable 和 Observable。
        //         Flowable增加了对背压(BackPressure)的处理

        //对数据进行处理

        //RxJava1中的概念，现在Subscriber在订阅的时候回调给订阅者
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        // RxJava2的方式，Disposable在订阅的时候回调给订阅者,Disposable的dispose分发用于
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Processor processor = new Processor() {
            @Override
            public void subscribe(Subscriber s) {

            }

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


    private static void disposableObserver() {
        //名为 DisposableObserver 的类型，它将自动处理第四个方法，并允许只用关心来自 Observable 本身的通知
        //它实现了 Disposable，因此可以调用 dispose 方法，它会将其转发到过程链当中。
        DisposableObserver disposableObserver = new DisposableObserver() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        //在 RxJava 2 当中有一个名为 subscribeWith 的新方法。
        Observable<String> o = Observable.just("Hello");
        o.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

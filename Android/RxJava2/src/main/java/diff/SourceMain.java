package diff;

import io.reactivex.*;
import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import rx.*;
import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Rx2中的各种数据源
 *
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class SourceMain {

    public static void main(String... args) {
        testFlowable();
        test1Observable();
        test2Observable();
        testSingle();
        testCompletable();
        Utils.blockMain();
    }

    private static void test1Observable() {

        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            for (int i = 0; i < 1000; i++) {
                subscriber.onNext(String.valueOf(i));
            }
            subscriber.onCompleted();
        })
                .subscribeOn(rx.schedulers.Schedulers.newThread())
                .observeOn(rx.schedulers.Schedulers.newThread())
                .subscribe(new rx.Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("SourceMain.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("SourceMain.onError");
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(String s) {
                        Utils.sleep(10);
                    }
                });
    }

    private static void testCompletable() {

        Completable.complete()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("SourceMain.onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    private static void testSingle() {

        Single.just(100)
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        System.out.println("SourceMain.onSuccess");
                        System.out.println("integer = [" + integer + "]");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private static void testFlowable() {
  /*
        Subscription用于连接RxJava2中的Flowable和Subscriber
        在使用Flowable的create是，必须调用Subscription的request方法指定请求的数据，请求的个数代表自己能处理数据的能力

        Flowable处理了Backpressure，默认的缓存池大小为128

        BackpressureStrategy的几种模式说明：

            DROP 丢掉下游来不及处理的数据，如果不是使用create创建的Flowable，则对应方法为onBackpressureDrop()
            ERROR 抛出MissingBackpressureException    默认行为
            LATEST 只保留最新的事件，总是能获得最后一个事件，如果不是使用create创建的Flowable，则对应方法为onBackpressureLatest()
            BUFFER 缓存所有数据，如果不是使用create创建的Flowable，则对应方法为onBackpressureBuffer()
         */
        Flowable.create((FlowableOnSubscribe<String>) e -> {
            for (int i = 0; i < 1000; i++) {
                Utils.sleep(10);
                e.onNext("send : " + i);
            }
            e.onComplete();
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {
                        Utils.sleep(100);
                        System.out.println("s = [" + s + "]");
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("t = [" + t + "]");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("SourceMain.onComplete");
                    }
                });

    }


    private static void test2Observable() {
        /* Disposable用于连接RxJava2中的Observable和Observer   */
        io.reactivex.Observable.create((ObservableOnSubscribe<String>) e -> {

        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

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

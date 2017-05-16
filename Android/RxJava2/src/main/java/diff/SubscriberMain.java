package diff;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class SubscriberMain {

    /*
    在新版里Subscriber被赋予了更多的作用,有几个实现类可以供我们使用

        ResourceSubscriber


     */
    public static void main(String... args){
        Flowable.just(1, 3, 6, 8, 0, 1)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return String.valueOf(integer);
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("s = [" + s + "]");
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

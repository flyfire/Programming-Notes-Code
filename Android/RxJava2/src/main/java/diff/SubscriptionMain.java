package diff;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.SerialDisposable;

/**
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class SubscriptionMain {

    /*

    在RxJava1里，Subscription起到的是订阅桥梁的作用。
    在2中，由于Subscription本身和Reactive-Streams里的另外一个同名概念冲突。
    因此把原本的Subscription改名成了Disposable:

            CompositeSubscription 改名成了 CompositeDisposable
            SerialSubscription 和 MultipleAssignmentSubscription被合并到了SerialDisposable里.
            set() 方法会处理掉旧的值，而replace()方法不会。

     */
    public static void main(String... args) {
        Disposable disposable = new Disposable() {
            @Override
            public void dispose() {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        };
    }

    SerialDisposable serialDisposable = new SerialDisposable(new Disposable() {
        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    });
}

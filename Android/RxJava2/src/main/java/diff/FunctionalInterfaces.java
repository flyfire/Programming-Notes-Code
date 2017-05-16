package diff;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class FunctionalInterfaces {

    /*
    1   现在RxJava2的接口方法里加上了throws Exception:
    2   另外大部分接口方法都按照Java8的接口方法名进行了相应的修改，比如上面那个Consumer<T>接口原来叫Action1<T>，而Action2<T>改名成了BiConsumer
         Action3-Action9被删掉了
    3   Functions基本就是名字的修改和不常用类的删除

     */
    @SuppressWarnings("all")
    public static void main(String... args) {

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
                .subscribe();
    }


}

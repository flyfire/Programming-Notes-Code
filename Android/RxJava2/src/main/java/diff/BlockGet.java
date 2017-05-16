package diff;

import io.reactivex.Flowable;


/**
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class BlockGet {

    public static void main(String... args) {
        Integer integer = Flowable.fromArray(new Integer[]{1, 3, 4, 5, 56})
                .last(1)
                .blockingGet();
        System.out.println(integer);
    }
}

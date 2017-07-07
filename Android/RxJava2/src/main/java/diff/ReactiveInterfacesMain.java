package diff;


/**
 * 以Reactive-Streams为基础
 *
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class ReactiveInterfacesMain {

    /*
    和Flowable的接口Publisher类似，Observable、Single、Completable也有类似的基类,
    因此许多操作符接受的参数从以前的具体对象，变成了现在的接口由于接收的都是接口，
    在使用其他遵循Reactive-Streams设计的第三方库的时候，就不需要把他自定义的Flowable转换成标准Flowable了。
     */
    public static void main(String... args) {
        //  Flowable --> Publisher
        //  Observable --> ObservableSource
        //  Single --> SingleSource
        //  Completable --> CompletableSource
    }

}

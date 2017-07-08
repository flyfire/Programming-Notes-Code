package main.study.filter;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @Author Administrator
 * Date:2016-03-14.19:59
 * 描述：
 */
public class FilterMain {

    public static void main(String[] args) {
//        testRepeat();
        testFirst();
    }

    private static void testFirst() {
        Observable.empty().first().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
    }


    private static void testRepeat() {

        Observable.range(1,2)
                .single(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {

                        return integer ==22;
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }
                });

        waitMain();
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


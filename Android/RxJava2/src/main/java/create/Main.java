package create;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Main {

    public static void main(String... args) {
        just();
    }

    private static void just() {

        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        d.dispose();
                        System.out.println("d = [" + d + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("SourceMain.onComplete");
                    }
                });
    }
}

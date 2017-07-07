package sample;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author ztiany
 *         Email: ztiany3@gmail.com
 */
public class MultiFileUploadSample {

    private volatile static int taskCount = 0;
    private static Executor executor = Executors.newSingleThreadExecutor();

    private static class Task {
        private final String name;

        private Task(String name) {
            this.name = name;
        }
    }

    private static List<Task> taskList = new ArrayList<>();

    public static void main(String... args) {
        executor.execute(() -> {
            for (int i = 0; i < 3000; i++) {
                taskList.add(new Task(String.valueOf(i)));
            }
            uploadTask();
            System.out.println("executor  done...");
        });
    }

    //使用Subscriber进行订阅，是需要主动调用request方法的
    private static Subscriber subscriber = new Subscriber<Boolean>() {
        @Override
        public void onSubscribe(Subscription s) {
            s.request(300);
        }

        @Override
        public void onNext(Boolean aBoolean) {
            System.out.println("MultiFileUploadSample.onNext-------------->" + taskCount);
        }

        @Override
        public void onError(Throwable t) {
            System.out.println("MultiFileUploadSample.onError:" + t);
        }

        @Override
        public void onComplete() {
            System.out.println("MultiFileUploadSample.onComplete");
        }
    };

    /*使用consumer订阅，默认request Long.MAX_VALUE个*/
    private static Consumer consumer = new Consumer<Boolean>() {
        @Override
        public void accept(Boolean aBoolean) throws Exception {
            System.out.println("MultiFileUploadSample.onNext -------------->" + taskCount);
        }
    };

    //批量执行
    private static void uploadTask() {
        Flowable.fromIterable(taskList)
                .flatMap(MultiFileUploadSample::doUpload, true, 3)
                .doOnNext(aBoolean -> System.out.println(++taskCount))
                .observeOn(Schedulers.from(executor))
                .subscribe(subscriber);
    }

    //模拟上传文件
    private static Publisher<Boolean> doUpload(Task task) {
        return Flowable.just(task)
                .subscribeOn(Schedulers.io())
                .map(task1 -> {
                    Thread.sleep(300);
                    System.out.println("MultiFileUploadSample.apply-->Task =" + task1.name);
                    return true;
                });
    }
}

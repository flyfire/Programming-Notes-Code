package sample;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

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
    private volatile static int failTaskCount = 0;
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
            for (int i = 0; i < 100; i++) {
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
            s.request(100);
        }

        @Override
        public void onNext(Boolean aBoolean) {
            System.out.println("MultiFileUploadSample.onNext-------------->taskCount = " + taskCount + " failTaskCount = " + failTaskCount);
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

    /*使用consumer订阅，默认request Long.MAX_VALUE个*/
    private static Consumer errorConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            System.out.println("------------------throwable = [" + throwable + "]");
        }
    };

    //批量执行
    private static void uploadTask() {
         Flowable.fromIterable(taskList)
                .flatMap(MultiFileUploadSample::doUpload, true, 3)
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        System.out.println("success " + ++taskCount);
                    } else {
                        System.out.println("fail " + ++failTaskCount);
                    }
                })
                .observeOn(Schedulers.from(executor))
                .doOnCancel(() -> System.out.println("MultiFileUploadSample.doOnCancel run"))
                .subscribe(subscriber);
    }

    //模拟上传文件
    private static Publisher<Boolean> doUpload(Task task) {
        return Flowable.just(task)
                .subscribeOn(Schedulers.io())
                .flatMap(task1 -> {
                    Thread.sleep(300);
                    System.out.println("MultiFileUploadSample.apply-->Task =" + task1.name);
                    int i = Integer.parseInt(task1.name);
                    int result = i % 3;
                    if (result == 0) {
                        return Flowable.just(true);//上传成功
                    } else if (result == 1) {
                        return Flowable.just(false);//上传失败
                    } else {
                        return Flowable.error(new RuntimeException("网络错误"));//网络错误
                    }
                });
    }
}

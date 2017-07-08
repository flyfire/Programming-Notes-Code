package main.study.helper;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author Administrator
 *         Date:2016-03-31.22:34
 *         描述：
 */
public class TestHelperOpt_To {
    public static void main(String[] args) {
//        testGetIterator();
//        testToFuture();
//        testToIterable();

//        testToList();
//        testToMap();
//        testToMap2();
//        testToMap3();

//        testToSortList();
//        testToSortList2();


//        testToMultiMap();


        testNest();

    }

    private static void testNest() {
        Observable.range(1, 10)
                .nest()
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {

                    }
                });
    }


    private static void testToMultiMap() {
        Observable.just("A#", "C", "N$@")
                .toMultimap(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return String.valueOf(s.length());
                    }
                }, new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return null;
                    }
                }, new Func0<Map<String, Collection<Integer>>>() {
                    @Override
                    public Map<String, Collection<Integer>> call() {
                        return null;
                    }
                }, new Func1<String, Collection<Integer>>() {
                    @Override
                    public Collection<Integer> call(String s) {
                        return null;
                    }
                });

    }

    private static void testToSortList2() {
        Observable.just("A#", "C", "N$@")
                .toSortedList(new Func2<String, String, Integer>() {
                    @Override
                    public Integer call(String s, String s2) {
                        System.out.println(s + s2);
                        if (s.equals(s2)) {
                            return 0;
                        }
                        if (s2.length() > s.length()) {
                            return 1;
                        } else if (s2.length() < s.length()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                System.out.println(strings);
            }
        });
    }

    private static void testToSortList() {
        Observable.just(5, 2, 9, 6, 2, 1, 6)
                .toSortedList()
                .subscribe(new Subscriber<List<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        System.out.println(integers);
                    }
                });
    }

    private static void testToMap3() {
        Observable.range(1, 100)
                .toMap(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return null;
                    }
                }, new Func1<Integer, BigDecimal>() {
                    @Override
                    public BigDecimal call(Integer integer) {
                        return null;
                    }
                }, new Func0<Map<String, BigDecimal>>() {
                    @Override
                    public Map<String, BigDecimal> call() {
                        return null;
                    }
                }).subscribe(new Action1<Map<String, BigDecimal>>() {
            @Override
            public void call(Map<String, BigDecimal> stringBigDecimalMap) {

            }
        });
    }

    private static void testToMap2() {
        Observable.range(1, 100)
                .toMap(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return null;
                    }
                }, new Func1<Integer, BigDecimal>() {
                    @Override
                    public BigDecimal call(Integer integer) {
                        return null;
                    }
                }).subscribe(new Action1<Map<String, BigDecimal>>() {
            @Override
            public void call(Map<String, BigDecimal> stringBigDecimalMap) {

            }
        });
    }

    private static void testToMap() {
        Observable.range(1, 100)
                .toMap(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer * integer);
                    }
                }).subscribe(new Action1<Map<String, Integer>>() {
            @Override
            public void call(Map<String, Integer> stringIntegerMap) {

                System.out.println(stringIntegerMap);
            }
        });
    }

    private static void testToList() {
        Observable.range(1, 100)
                .toList()
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        System.out.println(integers);
                    }
                });
    }

    private static void testToIterable() {
        Iterable<Long> integers = Observable.interval(1, TimeUnit.SECONDS)
//                .delay(2,TimeUnit.SECONDS)
                .toBlocking()
                .toIterable();

        integers.forEach(new Consumer<Long>() {
            @Override
            public void accept(Long integer) {

                System.out.println(integer + "   " + Thread.currentThread());
            }
        });
    }

    private static void testToFuture() {
        Future<Integer> integerFuture = Observable.range(1, 100)
                .delay(2, TimeUnit.SECONDS)
                .toBlocking()
                .toFuture();

        try {
            System.out.println(integerFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void testGetIterator() {
        Iterator<Integer> iterator = Observable.range(1, 100)
                .toBlocking()
                .getIterator();
        iterator.forEachRemaining(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

    }
}

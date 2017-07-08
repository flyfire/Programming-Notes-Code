package main.advance.producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <br/>   Description：
 * <br/>    Email: ztiany3@gmail.com
 *
 * @author Ztiany
 *         Date : 2016-12-06 23:40
 */
public class RangeProducerTest {
    public static void main(String... args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AtomicLong atomicLong = new AtomicLong(0);
        for (int i = 0; i < 5; i++) {
//            executorService.submit(() ->
            System.out.println(getAndAddRequest(atomicLong, 100));
        }
    }

    public static long getAndAddRequest(AtomicLong requested, long n) {
        System.out.printf("AtomicLong requested, long n     %d , %d", requested.get(), n);
        System.out.println();
        while (true) {
            long current = requested.get();  // 0          100
            long next = addCap(current, n); // next = 100      200
            System.out.printf("current  next    %d , %d", current, next);
            System.out.println();
            if (requested.compareAndSet(current, next)) { // 0 , 100   100 200
                return current;
            }
        }
    }

    //add n to field but check for overflow
    private static long addCap(long a, long b) {
        long u = a + b;
        if (u < 0L) {
            u = Long.MAX_VALUE;
        }
        return u;
    }

}

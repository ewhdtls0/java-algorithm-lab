package lab;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentMapLab {
    private static final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private static final AtomicInteger createCount = new AtomicInteger();

    void main(String[] args) throws Exception {
        Thread[] threads = new Thread[20];

        for (int i=0; i<threads.length; i++) {
            threads[i] = new Thread(() -> {
                // ConcurrentHashMap을 사용하였지만 동시성 제어가 안됨.
                // 쓰레드가 관측하지않은 상태로 동작함. visibility가 필요
                if (!cache.containsKey("model")) {
                    sleep(100);
                    int n = createCount.incrementAndGet();

                    cache.put("model", "Model - " + n);
                }

                /**
                 *
                 * cache.computeIfAbsent("model", key -> {
                 *     sleep(100);
                 *
                 *     int n = createCount.incrementAndGet();
                 *
                 *     return "Model-" + n;
                 * });
                 */
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("cache = " + cache);
        System.out.println("생성 횟수 = " + createCount.get());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

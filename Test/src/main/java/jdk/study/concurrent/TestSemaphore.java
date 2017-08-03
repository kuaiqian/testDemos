package jdk.study.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {
    final static Semaphore SEMAPHORE = new Semaphore(1);

    final static int threadsNum = 5;

    public static void main(String[] args) {
        ExecutorService excute = Executors.newCachedThreadPool();
        for (int i = 0; i < threadsNum; i++) {
            // SEMAPHORE.tryAcquire(10L, TimeUnit.MILLISECONDS);
            // SEMAPHORE.acquire();
            SEMAPHORE.acquireUninterruptibly();
            new Thread(new Runnable(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":excute");
                    try {
                        TimeUnit.SECONDS.sleep(5L);
                        // SEMAPHORE.acquire();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SEMAPHORE.release();
                }
            }).start();
        }
        excute.shutdown();
    }
}

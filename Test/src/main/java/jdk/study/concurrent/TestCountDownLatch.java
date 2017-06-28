package jdk.study.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {
    final static int threadsNum = 5;

    final static CountDownLatch start = new CountDownLatch(threadsNum);

    final static CountDownLatch end = new CountDownLatch(threadsNum);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService excute = Executors.newCachedThreadPool();
        for (int i = 0; i < threadsNum; i++) {
            excute.execute(new Runnable(){
                @Override
                public void run() {
                    try {
                        start.await();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":excute");
                    end.countDown();
                }
            });
            start.countDown();
        }
        end.await();
        System.out.println("main thread is waiting for a event");
        excute.shutdown();
    }
}

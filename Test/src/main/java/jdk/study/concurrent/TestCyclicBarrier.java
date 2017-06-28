package jdk.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier {
    final static int threadsNum = 5;

    static final CyclicBarrier barrier = new CyclicBarrier(threadsNum, new Runnable(){
        @Override
        public void run() {
            System.out.println("da pai qu " + Thread.currentThread().getName());
        }
    });

    public static void main(String[] args) {
        ExecutorService excute = Executors.newCachedThreadPool();
        for (int i = 0; i < threadsNum; i++) {
            excute.execute(new Runnable(){
                @Override
                public void run() {
                    try {
                        barrier.await();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":excute");
                }
            });
        }
        System.out.println("wait for last partener!");
        excute.shutdown();
    }
}

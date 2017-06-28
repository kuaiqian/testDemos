package jdk.study;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestExecutor {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newCachedThreadPool();
        // for (int i = 0; i < 5; i++) {
        // executorService.execute(new Task());
        // }
        // executorService.shutdown();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 8; i++) {
            poolExecutor.execute(new Task());
        }
    }

    static class Task implements Runnable {
        private int a = 5;

        @Override
        public void run() {
            while (a-- > 0) {
                System.out.println(Thread.currentThread().getName() + " a=" + a);
            }
        }
    }
}

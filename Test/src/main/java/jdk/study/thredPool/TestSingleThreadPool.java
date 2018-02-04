package jdk.study.thredPool;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestSingleThreadPool {
    final static int size = 2;

    public static void main(String[] args) throws InterruptedException {
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // final CyclicBarrier barrier = new CyclicBarrier(size);
        // Runnable r1 = new MyTask(barrier);
        // Runnable r2 = new MyTask(barrier);
        // executorService.execute(r1);
        // executorService.execute(r2);
        // TimeUnit.SECONDS.sleep(11L);
        // System.out.println("main thread end.." + Thread.currentThread().getName());
        // executorService.execute(r1);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 5L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new RejectedExecutionHandler(){
                    @Override
                    public void rejectedExecution(Runnable paramRunnable, ThreadPoolExecutor paramThreadPoolExecutor) {
                        System.out.println("disacrd !");
                    }
                });
        poolExecutor.allowCoreThreadTimeOut(true);
        Runnable r1 = new MyTask(null);
        Runnable r2 = new MyTask(null);
        Runnable r3 = new MyTask(null);
        poolExecutor.execute(r1);
         poolExecutor.execute(r2);
         poolExecutor.execute(r3);
        TimeUnit.SECONDS.sleep(10L);
        System.out.println("main thread end.." + Thread.currentThread().getName());
        // poolExecutor.execute(r2);
        // poolExecutor.shutdown();
    }

    static class MyTask implements Runnable {
        final CyclicBarrier barrier;

        public MyTask(CyclicBarrier barrier) {
            super();
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // System.out.println("enter to wait barrier..");
                // barrier.await();
                System.out.println("beigin at the same time..");
                Thread.currentThread().sleep(10000L);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":excute");
        }
    }
}

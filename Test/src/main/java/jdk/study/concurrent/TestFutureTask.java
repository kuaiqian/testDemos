package jdk.study.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestFutureTask {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService=Executors.newCachedThreadPool();

        FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>(){
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5L);
                System.out.println("future task complete");
                return "success";
            }
        });
        executorService.submit(futureTask);
        System.out.println(futureTask.get(10L, TimeUnit.MILLISECONDS));
        System.out.println("main thread going...");
        executorService.shutdown();
    }
}

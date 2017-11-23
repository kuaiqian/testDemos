package junit;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestJunit {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
    }
    static class A implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            synchronized (dateFormat) {
                try {
                    dateFormat.wait(2000);
                    System.out.println("wait");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class B implements Runnable {
        @Override
        public void run() {
            synchronized (dateFormat) {
                dateFormat.notifyAll();
                System.out.println("notify");
            }
        }
    }

    @Test
    public void calc() {
        // System.out.println(998&1);
        // System.out.println(999%2);
        // System.out.println(999>>8);
        //
        // System.out.println(1|0);
        // System.out.println(1&0);
        // System.out.println(1^1);
        System.out.println(2 + 1 >> 1);
        System.out.println(12 >> 1 & 15);
    }
}

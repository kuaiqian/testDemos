package junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import junit.framework.TestCase;

public class TestJunit extends TestCase {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new A()).start();
        }
    }

    static class A implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(dateFormat.parse("2016-01-01 23:00:00"));
            }catch (ParseException e) {
                e.printStackTrace();
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

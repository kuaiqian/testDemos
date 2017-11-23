package importnew;

import java.util.concurrent.TimeUnit;

public class TestVolitle {
    protected static boolean is = true;

    public static void main(String[] args) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                int i = 0;
                while (TestVolitle.is) {
                    i++;
                    // synchronized (this) { } 会强制刷新主内存的变量值到线程栈?
                    // System.out.println("1"); println 是synchronized 的,会强制刷新主内存的变量值到线程栈?
                    // sleep 会从新load主内存的值?
                    // try {
                    // TimeUnit.MICROSECONDS.sleep(1);
                    // }catch (InterruptedException e) {
                    // e.printStackTrace();
                    // }
                }
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable(){
            @Override
            public void run() {
                is = false; // 设置is为false，使上面的线程结束while循环
            }
        }).start();
    }
}

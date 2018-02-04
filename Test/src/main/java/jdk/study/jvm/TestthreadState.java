package jdk.study.jvm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestthreadState {
	private static Lock a=new ReentrantLock();
	private static Condition c=a.newCondition();
	
	public static void main(String[] args) {
		A ar=new TestthreadState(). new A();
		new Thread(ar).start();
		new Thread(ar).start();
		new Thread(ar).start();
		new Thread(ar).start();
		new Thread(ar).start();
		new Thread(ar).start();
		new Thread(ar).start();
	}
    
	 class A implements Runnable{

		@Override
		public void run() {
				a.lock();
				try {
					Thread.currentThread().sleep(15000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("obtain lock");
				try {
					c.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				a.unlock();
			}
		
	}
}

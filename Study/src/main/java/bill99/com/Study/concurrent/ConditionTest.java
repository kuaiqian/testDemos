package bill99.com.Study.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	static ReentrantLock lock=new ReentrantLock();
	static Condition empty=lock.newCondition();
	public static void main(String[] args) throws InterruptedException {
		System.out.println("begin");
		lock.lock();
		empty.awaitNanos(1000L);
		System.out.println("end"+ThreadLocalRandom.current().nextInt());
//		System.out.println("end"+lock.getHoldCount());
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(lock.tryLock(1,TimeUnit.SECONDS));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				try {
//					empty.await(1, TimeUnit.SECONDS);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				System.out.println(Thread.currentThread().getName()+":count="+lock.getHoldCount());
			}
		}).start();
	}
	
}

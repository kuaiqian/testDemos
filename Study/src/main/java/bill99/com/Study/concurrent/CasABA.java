package bill99.com.Study.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasABA {
	static AtomicStampedReference<Integer> asr = new AtomicStampedReference<Integer>(100, 0);
	static AtomicInteger ai = new AtomicInteger(100);

	public static void main(String[] args) throws InterruptedException {
		Thread a1 = new Thread(new Runnable() {

			public void run() {
				ai.compareAndSet(100, 101);
				ai.compareAndSet(101, 100);
			}
		});

		Thread a2 = new Thread(new Runnable() {

			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(ai.compareAndSet(100, 101));
			}
		});
		a1.start();
		a2.start();
		a1.join();
		a2.join();
		System.out.println("===============");
		Thread asf1 = new Thread(new Runnable() {

			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				asr.compareAndSet(100, 101, asr.getStamp(), asr.getStamp() + 1);
				asr.compareAndSet(101, 100, asr.getStamp(), asr.getStamp() + 1);
			}
		});

		Thread asf2 = new Thread(new Runnable() {

			public void run() {
				int stamp = asr.getStamp();
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(asr.compareAndSet(100, 101, stamp, stamp + 1));
			}
		});
		asf1.start();
		asf2.start();

	}

}

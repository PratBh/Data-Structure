package CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;

public class Worker extends Thread {
	private int delay;
	private CountDownLatch latch;
	public Worker(int delay, CountDownLatch latch,
            String name)
	{
super(name);
this.delay = delay;
this.latch = latch;
}
	@Override
	public void run() {
		try {
			Thread.sleep(delay);
			latch.countDown();
			System.out.println(Thread.currentThread().getName()+" finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

}

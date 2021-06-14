package CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;

public class Demo {

	public static void main(String[] args) {
		CountDownLatch latch=new CountDownLatch(4);
		Worker w1=new Worker(100, latch, "W1");
		Worker w2=new Worker(101, latch, "W2");
		Worker w3=new Worker(102, latch, "W3");
		Worker w4=new Worker(103, latch, "W4");
		
		w1.start();
		w2.start();
		w3.start();
		w4.start();
		
		try {
			latch.await();
			System.out.println(Thread.currentThread().getName() +
                    " has finished");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package CyclicBarreierDemo;

import java.util.concurrent.BrokenBarrierException;

public class Computation1 implements Runnable {
	public static int product=0;

	@Override
	public void run() {
		product=2*3;
		try {
			Tester.newBarrier.await();
			System.out.println("Number of parties waiting at the barrier "+
            "at this point = " + Tester.newBarrier.getNumberWaiting());
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}
}

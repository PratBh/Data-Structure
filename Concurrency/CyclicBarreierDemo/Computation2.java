package CyclicBarreierDemo;

import java.util.concurrent.BrokenBarrierException;

public class Computation2 implements Runnable {
	public static int sum=0;
	@Override
	public void run() {
		sum=10*20;
		try {
			Tester.newBarrier.await();
			System.out.println("Number of parties waiting at the barrier "+
		            "at this point = " + Tester.newBarrier.getNumberWaiting());
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}

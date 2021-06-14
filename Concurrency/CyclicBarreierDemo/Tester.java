package CyclicBarreierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Tester implements Runnable {
	
	public static CyclicBarrier newBarrier=new CyclicBarrier(3);
	@Override
	public void run() {
		Thread mainThread=new Thread(new Tester());
		mainThread.start();
	}

	public static void main(String[] args) {
		System.out.println("Before Barrier "+(Computation1.product+Computation2.sum));
		System.out.println("Parties should wait at barrier "+newBarrier.getParties());
		Thread t1= new Thread(new Computation1());
		t1.start();
		Thread t2=new Thread(new Computation2());
		t2.start();
		try {
			Tester.newBarrier.await();
			System.out.println("Number of parties waiting at the barrier "+
            "at this point = " + Tester.newBarrier.getNumberWaiting());
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("After Barrier "+(Computation1.product+Computation2.sum));
		newBarrier.reset();
	}

}

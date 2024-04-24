package Producer_Consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
	boolean isAvailable = false;
	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void produce() {
		try {
			lock.lock();
			System.out.println("Produce locked by... " + Thread.currentThread().getName());
			while (isAvailable) {
				System.out.println("Produce is waiting..." + Thread.currentThread().getName());
				condition.await();
			}
			isAvailable = true;
			condition.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Produce lock release by..." + Thread.currentThread().getName());
			lock.unlock();
		}
	}

	public void consume() {
		try {
			lock.lock();
			System.out.println("Consume locked by... " + Thread.currentThread().getName());
			while (!isAvailable) {
				System.out.println("Consume is waiting..." + Thread.currentThread().getName());
				condition.await();
			}
			isAvailable = false;
			condition.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Consume lock release by..." + Thread.currentThread().getName());
			lock.unlock();
		}
	}
}

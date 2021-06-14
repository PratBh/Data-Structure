package Custom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Custom_BlockedQ {
	private CustomNode front;
	private CustomNode rear;
	private int length;
	private Lock lock;
	private Condition isFull;
	private Condition isEmpty;
	
	
	
	public Custom_BlockedQ() {
		super();
		this.length=0;
		this.lock = new ReentrantLock();
		this.isFull=lock.newCondition();
		this.isEmpty=lock.newCondition();
	}

	public void put(int data) throws InterruptedException {
		lock.lock();
		if(this.length>9) {
			System.out.println("queue is full,please wait....");
			isFull.await();
			
		}
		CustomNode node=new CustomNode(data, null);
		if(this.length==0) {
			front=node;
		}else {
			rear.setNext(node);
		}
		rear=node;
		this.length++;
		System.out.println("Data added.Notify all threads waiting to consume...");
		isEmpty.signalAll();
		lock.unlock();
	}
	
	public int get() throws InterruptedException {
		lock.lock();
		if(this.length==0) {
			System.out.println("Queue is empty,please wait....");
			isEmpty.await();
		}
		int data=front.getData();
		this.front=front.getNext();
		this.length--;
		if(this.length==8)
			isFull.signalAll();
		lock.unlock();
		return data;
	}
	
	public boolean isEmpty() {
		return this.length==0;
	}
}

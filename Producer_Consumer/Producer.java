package Producer_Consumer;

import java.util.Queue;

public class Producer {
	Queue<Integer> q;
	int size;
	
	public Producer(Queue<Integer> q,int size) {
		this.q=q;
		this.size=size;
	}
	
	public void produce(int i) {
		synchronized (q) {
			if(q.size()==size) {
				System.out.println("queue is full...");
				try {
					q.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			q.add(i);
			System.out.println("Produced "+i);
			q.notify();
		}
	}
}

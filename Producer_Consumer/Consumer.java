package Producer_Consumer;

import java.util.Queue;

public class Consumer {
	Queue<Integer> q;
	
	public Consumer(Queue<Integer> q) {
		this.q=q;
	}
	
	public int consume() {
		synchronized (q) {
			if(q.isEmpty()) {
				System.out.println("queue is empty...");
				try {
					q.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int a =q.poll();
			System.out.println("consumed "+a);
			q.notify();
			return a;
		}
	} 
}

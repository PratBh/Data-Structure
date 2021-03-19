
package Custom;

import java.util.LinkedList;
import java.util.List;

public class Custom_BlockedQ {
	private List q=new LinkedList();
	private int limit=10;
	public Custom_BlockedQ(int limit) {
		this.limit = limit;
	}
	
	public synchronized void enq(Object item) throws InterruptedException {
		while(this.q.size()==this.limit) {
			wait();
		}
		this.q.add(item);
		if(q.size()==1)
			notifyAll();
	}
	
	public synchronized Object deq() throws InterruptedException {
		while(this.q.size()==0)
			wait();
		if(this.q.size()==this.limit)
			notifyAll();
		return this.q.remove(0);
	}
}

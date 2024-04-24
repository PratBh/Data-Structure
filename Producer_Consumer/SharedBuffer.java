package Producer_Consumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
	Queue<Integer> buffer;
	int bufferSize;
	public SharedBuffer(int bufferSize) {
		this.bufferSize = bufferSize;
		this.buffer=new LinkedList<Integer>();
	}
	
	synchronized void addItem(int item) {
		System.out.println("Adding item..");
		while(buffer.size()==bufferSize) {
			System.out.println("Queue is full,wait....");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer.add(item);
			System.out.println("Item added");
			notifyAll();
		//}
	}	
	
	synchronized void removeItem() {
		System.out.println("Removing item...");
		while(buffer.size()==0) {
			System.out.println("Queue is empty....");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		buffer.remove();
		System.out.println("Item removed...");
		notifyAll();
	}
}

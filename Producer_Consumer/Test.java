package Producer_Consumer;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		//SharedBuffer sb=new SharedBuffer(4);
//		SharedResource sr = new SharedResource();
//		Thread producer = new Thread(()->{
//			for(int i=0;i<2;i++) {
//				sr.produce();
//			}
//		});
//		
//		Thread consumer = new Thread(()->{
//			for(int i=0;i<2;i++) {
//				sr.consume();
//			}
//		});
//		
//		producer.start();
//		consumer.start();
		Queue<Integer> q = new LinkedList<Integer>();
		Consumer cr = new Consumer(q);
		Producer pr = new Producer(q, 2);
		Thread t1 = new Thread(()->{
			for(int i=0;i<4;i++)
				pr.produce(i);
		});
		Thread t2 = new Thread(()->{
			for(int i=0;i<5;i++)
				cr.consume();
		});
		
		t1.start();
		t2.start();

	}

}

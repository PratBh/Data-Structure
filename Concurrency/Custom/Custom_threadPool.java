package Custom;

import java.util.concurrent.LinkedBlockingDeque;

public class Custom_threadPool {
	private int poolSize;
	private PoolWorker[] threads;
	private LinkedBlockingDeque queue;
	public Custom_threadPool(int poolSize) {
		super();
		this.poolSize = poolSize;
		this.threads = new PoolWorker[poolSize];
		this.queue = new LinkedBlockingDeque();
		
		for(int i=0;i<poolSize;i++) {
			threads[i]=new PoolWorker();
			threads[i].start();
		}
	}
	
	public void execute(Runnable task) {
		synchronized (queue) {
			queue.add(task);
			queue.notify();
		}
	}
	
	private class PoolWorker extends Thread{
		public void run() {
			Runnable task;
			while(true) {
				synchronized (queue) {
					if(queue.isEmpty())
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					task=(Runnable) queue.poll();
				}
				try {
				task.run();
				}catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class Task implements Runnable{
		private int num;
		public Task(int n) {
			this.num=n;
		}
		@Override
		public void run() {
			System.out.println("Task"+num+"running");
		}
		
	}
	
	public static void main(String[] args) {
		Custom_threadPool pool=new Custom_threadPool(3);
		for(int i=0;i<3;i++) {
			Task task=new Task(i);
			pool.execute(task);
		}
	}
}


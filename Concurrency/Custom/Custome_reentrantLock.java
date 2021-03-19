
package Custom;

public class Custom_reentrantLock {
	private boolean isLocked;
	Thread lockedBy;
	int lockCount;
	
	public Custom_reentrantLock() {
		this.isLocked=false;
		this.lockCount=0;
		this.lockedBy=Thread.currentThread();
	}
	
	public synchronized void lock() throws InterruptedException {
		Thread caller=Thread.currentThread();
		while(this.isLocked && this.lockedBy!=caller)
			wait();
		this.isLocked=true;
		this.lockCount++;
		this.lockedBy=caller;
	}
	
	public synchronized void unlock() {
		if(Thread.currentThread()==this.lockedBy)
			this.lockCount--;
		if(this.lockCount==0) {
			isLocked=false;
			notify();
		}
	}
}

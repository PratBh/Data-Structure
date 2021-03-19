package Custom;

public class Custom_lock {
	private boolean isLocked;
	public Custom_lock() {
		this.isLocked=false;
	}
	
	public synchronized void lock() throws InterruptedException {
		while(this.isLocked)
			wait();
		this.isLocked=true;
	}
	
	public synchronized void unlock() {
		this.isLocked=false;
		notify();
	}
}


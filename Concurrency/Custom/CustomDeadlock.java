package Custom;

public class CustomDeadlock {

	public static void main(String[] args) {
		CustomDeadlock dl=new CustomDeadlock();
		final A a=dl.new A();
		final B b=dl.new B();
		Runnable b1=()->{ {
				synchronized (a) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (b) {
						System.out.println("In block 1");
					}
				}
			}
		};
		
		Runnable b2=()->{
			synchronized (b) {
				synchronized (a) {
					System.out.println("In block 2");
				}
			}
		};
		
		new Thread(b1).start();
		new Thread(b2).start();
	}
	
	private class A {
        private int i = 10;
 
        public int getI() {
            return i;
        }
 
        public void setI(int i) {
            this.i = i;
        }
    }
 
    // Resource B
    private class B {
        private int i = 20;
 
        public int getI() {
            return i;
        }
 
        public void setI(int i) {
            this.i = i;
        }
    }

}

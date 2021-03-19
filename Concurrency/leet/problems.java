package leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class problems {
//	Suppose we have a class:
//
//		public class Foo {
//		  public void first() { print("first"); }
//		  public void second() { print("second"); }
//		  public void third() { print("third"); }
//		}
//		The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), 
//		and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(),
//		and third() is executed after second().
	
	//https://leetcode.com/problems/print-in-order/discuss/1020495/Java-Leverage-CountDownLatch
	//https://www.geeksforgeeks.org/countdownlatch-in-java/
	
	class Foo {
		
		private CountDownLatch latch2=new CountDownLatch(1);//through this second thread waits for first to end
		private CountDownLatch latch3=new CountDownLatch(1);//through this third thread waits for second to end

	    public Foo() {
	        
	    }

	    public void first(Runnable printFirst) throws InterruptedException {
	        printFirst.run();
	        latch2.countDown();
	    }

	    public void second(Runnable printSecond) throws InterruptedException {
	        latch2.await();
	        // printSecond.run() outputs "second". Do not change or remove this line.
	        printSecond.run();
	        latch3.countDown();
	    }

	    public void third(Runnable printThird) throws InterruptedException {
	        latch3.await();
	        // printThird.run() outputs "third". Do not change or remove this line.
	        printThird.run();
	    }
	}
	
//	Suppose you are given the following code:
//
//		class FooBar {
//		  public void foo() {
//		    for (int i = 0; i < n; i++) {
//		      print("foo");
//		    }
//		  }
//
//		  public void bar() {
//		    for (int i = 0; i < n; i++) {
//		      print("bar");
//		    }
//		  }
//		}
//		The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar().
//		Modify the given program to output "foobar" n times.
//
//		 
//
//		Example 1:
//
//		Input: n = 1
//		Output: "foobar"
//		Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
//		Example 2:
//
//		Input: n = 2
//		Output: "foobarfoobar"
//		Explanation: "foobar" is being output 2 times.
	
	//https://leetcode.com/problems/print-foobar-alternately/discuss/1029238/Java-or-Using-Two-CountDownLatch-or-18ms-or-Faster-than-77
	
	class FooBar {
	    private int n;
	    private List<CountDownLatch> latches1;
	    private List<CountDownLatch> latches2;

	    public FooBar(int n) {
	        this.n = n;
	        latches1=new ArrayList<CountDownLatch>(n);
	        latches2=new ArrayList<CountDownLatch>(n);
	        
	        for(int i=0;i<n;i++) {
	        	latches1.add(new CountDownLatch(1));
	        	latches2.add(new CountDownLatch(1));
	        }
	    }

	    public void foo(Runnable printFoo) throws InterruptedException {
	        
	        for (int i = 0; i < n; i++) {
	            if(i>0)
	            	latches2.get(i-1).await();//this is to make sure that we start next latch only when previous latch ended
	        	// printFoo.run() outputs "foo". Do not change or remove this line.
	        	printFoo.run();
	        	latches1.get(i).countDown();
	        }
	    }

	    public void bar(Runnable printBar) throws InterruptedException {
	        
	        for (int i = 0; i < n; i++) {
	            latches1.get(i).await();
	            // printBar.run() outputs "bar". Do not change or remove this line.
	        	printBar.run();
	        	latches2.get(i).countDown();
	        }
	    }
	}
	
	
	
	//https://leetcode.com/problems/print-foobar-alternately/discuss/1014473/Java-old-school-waitnotify-solution
	
	class FooBarAlt {
	    private int n;
	    private volatile int counter=1;

	    public FooBarAlt(int n) {
	        this.n = n;
	    }

	    public void foo(Runnable printFoo) throws InterruptedException {
	    	synchronized (this) {
	    		 for (int i = 0; i < n; i++) {
	 	        	
	 	            if(counter%2==0)
	 	            	wait();
	 	        	// printFoo.run() outputs "foo". Do not change or remove this line.
	 	        	printFoo.run();
	 	        	counter++;
	 	        	notify();
	 	        }
			}
	    }

	    public void bar(Runnable printBar) throws InterruptedException {
	        synchronized (this) {
	        	for (int i = 0; i < n; i++) {
		            if(counter%2!=0)
		            	wait();
		            // printBar.run() outputs "bar". Do not change or remove this line.
		        	printBar.run();
		        	counter++;
		        	notify();
		        }
			}
	        
	    }
	}
	
	//https://leetcode.com/problems/print-foobar-alternately/discuss/1041773/Java-99.8-Faster-or-Volatile-or
	
	class FooBarAlt2 {
	    private int n;
	    ReentrantLock lock=new ReentrantLock();
	    Condition fooCondition=lock.newCondition();
	    Condition barCondition=lock.newCondition();
	    private volatile boolean fooPrint=true;

	    public FooBarAlt2(int n) {
	        this.n = n;
	    }

	    public void foo(Runnable printFoo) throws InterruptedException {
	   
	    		 for (int i = 0; i < n; i++) {
	    			 	lock.lock();
	    		    	try {
	 	            while(!fooPrint)
	 	            	fooCondition.await();
	 	        	// printFoo.run() outputs "foo". Do not change or remove this line.
	 	        	printFoo.run();
	 	        	fooPrint=false;
	 	        	barCondition.signalAll();
	 	        }
	    		    	finally {
	    					lock.unlock();
	    				}
	    	}
	    	
	    }

	    public void bar(Runnable printBar) throws InterruptedException {
	    	
	        	for (int i = 0; i < n; i++) {
	        		lock.lock();
	    	        try {
		            while(fooPrint)
		            	barCondition.await();
		            // printBar.run() outputs "bar". Do not change or remove this line.
		        	printBar.run();
		        	fooCondition.signalAll();
		        }
	    	        finally {
	    				lock.unlock();
	    			}
			}
	        
	        
	    }
	}
	
//	Suppose you are given the following code:
//
//		class ZeroEvenOdd {
//		  public ZeroEvenOdd(int n) { ... }      // constructor
//		  public void zero(printNumber) { ... }  // only output 0's
//		  public void even(printNumber) { ... }  // only output even numbers
//		  public void odd(printNumber) { ... }   // only output odd numbers
//		}
//		The same instance of ZeroEvenOdd will be passed to three different threads:
//
//		Thread A will call zero() which should only output 0's.
//		Thread B will call even() which should only ouput even numbers.
//		Thread C will call odd() which should only output odd numbers.
//		Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506...
//		where the length of the series must be 2n.
//
//		 
//
//		Example 1:
//
//		Input: n = 2
//		Output: "0102"
//		Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd().
//		"0102" is the correct output.
//		Example 2:
//
//		Input: n = 5
//		Output: "0102030405"
	//https://leetcode.com/problems/print-zero-even-odd/discuss/1026121/Java-Basic-solution-using-synchronized
	
	class ZeroEvenOdd {
	    private int n;
	    private boolean zero;
	    private int current;
	    
	    public ZeroEvenOdd(int n) {
	        this.n = n;
	        this.current=1;
	        this.zero=true;
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    synchronized public void zero(IntConsumer printNumber) throws InterruptedException {
	        while(current<=n) {
	        	if(zero) {
	        		printNumber.accept(0);
	        		zero=false;
	        		notifyAll();
	        	}else
	        		wait();
	        }
	    }

	    synchronized public void even(IntConsumer printNumber) throws InterruptedException {
	        while(current<=n) {
	        	if(current%2==0 && !zero) {
	        		printNumber.accept(current);
	        		zero=true;
	        		current++;
	        		notifyAll();
	        	}else 
	        		wait();
	        }
	    }

	    synchronized public void odd(IntConsumer printNumber) throws InterruptedException {
	        while(current<=n) {
	        	if(current%2==1 && !zero) {
	        		printNumber.accept(current);
	        		zero=true;
	        		current++;
	        		notifyAll();
	        	}else
	        		wait();
	        }
	    }
	}
	
//	Write a program that outputs the string representation of numbers from 1 to n, however:
//
//		If the number is divisible by 3, output "fizz".
//		If the number is divisible by 5, output "buzz".
//		If the number is divisible by both 3 and 5, output "fizzbuzz".
//		For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
//
//		Suppose you are given the following code:
//
//		class FizzBuzz {
//		  public FizzBuzz(int n) { ... }               // constructor
//		  public void fizz(printFizz) { ... }          // only output "fizz"
//		  public void buzz(printBuzz) { ... }          // only output "buzz"
//		  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
//		  public void number(printNumber) { ... }      // only output the numbers
//		}
//		Implement a multithreaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed to four different threads:
//
//		Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
//		Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
//		Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
//		Thread D will call number() which should only output the numbers.
	//https://leetcode.com/problems/fizz-buzz-multithreaded/discuss/890801/Java-Solution-with-Locks
	class FizzBuzz {
	    private int n;
	    private int count;
	    private Object lock;

	    public FizzBuzz(int n) {
	        this.n = n;
	        this.count=1;
	        this.lock=new Object();
	    }

	    // printFizz.run() outputs "fizz".
	    public void fizz(Runnable printFizz) throws InterruptedException {
	        while(count<=n) {
	        	synchronized (lock) {
					if(count<=n && count%3==0 && count%5!=0) {
						printFizz.run();
						count++;
					}
				}
	        }
	    }

	    // printBuzz.run() outputs "buzz".
	    public void buzz(Runnable printBuzz) throws InterruptedException {
	    	while(count<=n) {
	        	synchronized (lock) {
					if(count<=n && count%3!=0 && count%5==0) {
						printBuzz.run();
						count++;
					}
				}
	        }
	    }

	    // printFizzBuzz.run() outputs "fizzbuzz".
	    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
	    	while(count<=n) {
	        	synchronized (lock) {
					if(count<=n && count%3==0 && count%5==0) {
						printFizzBuzz.run();
						count++;
					}
				}
	        }
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void number(IntConsumer printNumber) throws InterruptedException {
	    	while(count<=n) {
	        	synchronized (lock) {
					if(count<=n && count%3!=0 && count%5!=0) {
						printNumber.accept(count);
						count++;
					}
				}
	        }
	    }
	}
	
	class FizzBuzzAlt2 {
	    private int n;
	    private int i;

	    public FizzBuzzAlt2(int n) {
	        this.n = n;
	        i = 1;
	    }

	    // printFizz.run() outputs "fizz".
	    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
	        synchronized(this) {
	            while (i <= n) {
	                if (i % 3 != 0 || i % 5 == 0) {
	                    wait();
	                    continue;
	                }
	                printFizz.run();
	                ++i;
	                notifyAll();
	            }
	        }
	    }

	    // printBuzz.run() outputs "buzz".
	    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
	        synchronized(this) {
	            while (i <= n) {
	                if (i % 3 == 0 || i % 5 != 0) {
	                    wait();
	                    continue;
	                }
	                printBuzz.run();
	                ++i;
	                notifyAll();
	            }
	        }
	    }

	    // printFizzBuzz.run() outputs "fizzbuzz".
	    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
	        synchronized(this) {
	            while (i <= n) {
	                if (i % 15 != 0) {
	                    wait();
	                    continue;
	                }
	                printFizzBuzz.run();
	                ++i;
	                notifyAll();
	            }
	        }
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void number(IntConsumer printNumber) throws InterruptedException {
	        synchronized(this) {
	            while (i <= n) {
	                if (i % 3 == 0 || i % 5 == 0) {
	                    wait();
	                    continue;
	                }
	                printNumber.accept(i++);
	                notifyAll();
	            }
	        }
	    }
	}
	class FizzBuzzAlt {
	    private int n;
	    private AtomicInteger i=new AtomicInteger(0);
	    private AtomicInteger stage=new AtomicInteger(0);

	    public FizzBuzzAlt(int n) {
	        this.n = n;
	    }

	    // printFizz.run() outputs "fizz".
	    public void fizz(Runnable printFizz) throws InterruptedException {
	        while(i.get()<=n) {
	        	if(stage.get()==1) {
	        		printFizz.run();
	        		stage.set(0);
	        	}
	        }
	    }

	    // printBuzz.run() outputs "buzz".
	    public void buzz(Runnable printBuzz) throws InterruptedException {
	    	while(i.get()<=n) {
	        	if(stage.get()==2) {
	        		printBuzz.run();
	        		stage.set(0);
	        	}
	        }
	    }

	    // printFizzBuzz.run() outputs "fizzbuzz".
	    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
	    	while(i.get()<=n) {
	        	if(stage.get()==3) {
	        		printFizzBuzz.run();
	        		stage.set(0);
	        	}
	        }
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void number(IntConsumer printNumber) throws InterruptedException {
	        while(i.get()<n+1) {
	        	if(stage.get()==0) {
	        		i.getAndIncrement();
	        		if(i.get()%3==0 && i.get()%5!=0)
	        			stage.set(1);
	        		else if(i.get()%3!=0 && i.get()%5==0)
	        			stage.set(2);
	        		else if(i.get()%3==0 && i.get()%5==0)
	        			stage.set(3);
	        		else if(i.get()<=n)
	        			printNumber.accept(i.get());
	        	}
	        }
	    }
	}
	
//	There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules. There is a barrier 
//	where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and 
//	releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, 
//	and they must be able to immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule 
//	bond before any other threads from the next molecule do.
//
//	In other words:
//
//	If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
//	If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
//	We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are
//			paired up with. The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and
//			divide them into groups of three, each group should contain one oxygen and two hydrogen threads.
//
//	Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
//
//	 
//
//	Example 1:
//
//	Input: "HOH"
//	Output: "HHO"
//	Explanation: "HOH" and "OHH" are also valid answers.
//	Example 2:
//
//	Input: "OOHHHH"
//	Output: "HHOHHO"
//	Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.
	
	//https://leetcode.com/problems/building-h2o/discuss/1026286/Java-wait-and-notify-and-synchronize
	
	class H2O {
		
		int hCounts=0;
		int oCounts=0;
		
	    public H2O() {
	        
	    }

	    public  void  hydrogen(Runnable releaseHydrogen) throws InterruptedException {
	    	synchronized (this) {
	    		while(hCounts==2) {
					if(oCounts==1) {
						hCounts=0;
						oCounts=0;
						notifyAll();
					}else
						wait();
				}
		        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
		        releaseHydrogen.run();
		        hCounts++;	
			}
	    }

	    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
	    	synchronized (this) {
		        while(oCounts==1) {
		        	if(hCounts==2) {
		        		hCounts=0;
		        		oCounts=0;
		        		notifyAll();
		        	}else
		        		wait();
		        }
		        // releaseOxygen.run() outputs "O". Do not change or remove this line.
				releaseOxygen.run();
				oCounts++;
			}
	    }
	}
	
	class H2OAlt{
		private Queue<Runnable> hydrozens=new LinkedList<Runnable>();
		private Queue<Runnable> oxyzens=new LinkedList<Runnable>();
		
		public H2OAlt() {
	        
	    }
		
		public  void  hydrogen(Runnable releaseHydrogen) throws InterruptedException {
			hydrozens.add(releaseHydrogen);
			checkCanWater();
		}
		
		public  void  oxygen(Runnable releaseOxygen) throws InterruptedException {
			oxyzens.add(releaseOxygen);
			checkCanWater();
		}
		
		public void checkCanWater() {
			synchronized (this) {
				while(hydrozens.size()>=2 && oxyzens.size()>=1) {
					hydrozens.remove().run();
					hydrozens.remove().run();
					oxyzens.remove().run();
				}
			}
		}
		
	}
}


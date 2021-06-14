package Custom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

	public static void main(String[] args) {
		//Custom_BlockedQ bQ=new Custom_BlockedQ();
		
		
//		Thread t2=new Thread(()->{
//			for(int i=0;i<11;i++) {
//				try {
//					bQ.put(i);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		t2.start();
//		
//		Thread t1=new Thread(()->{
//			try {
//				System.out.println(bQ.get());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//		t1.start();
		
		Thread child=new Thread(()->{
			for(int i=1;i<10;i++) {
				System.out.println("child: "+i);
			}
		});
		
		child.start();
		try {
			child.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=0;i<5;i++) {
			System.out.println("main: "+i);
		}
		
		ExecutorService ex=Executors.newFixedThreadPool(3);
		ex.execute(()->{
			System.out.println("Async task");
		});
		
		Future<String> future=ex.submit(()->{
			System.out.println("Async callable...");
			return "Callable";
		});
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		callables.add(()->{
			return "Task 1";
		});
		callables.add(()->{
			return "Task 2";
		});
		callables.add(()->{
			return "Task 3";
		});
		
		try {
			List<Future<String>> futures=ex.invokeAll(callables);
			//futures.stream().map((f)->f.get()).forEach(System.out::println);;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

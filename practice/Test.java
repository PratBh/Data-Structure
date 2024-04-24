package practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
//		List<Integer> number = Arrays.asList(2,3,4,5);
//		List <Integer>mod = number.stream().filter(n->n%2==0).collect(Collectors.toList());
//		int a = number.stream().reduce((x,y)->x<y?x:y).orElse(0);
//				//number.stream().map(x->x*2).reduce(0,(ans,i)->ans+i);
//		System.out.println(a);
//		number.stream().map(x->x*x).forEach(y->System.out.println(y));
//		String[] array = { "Geeks", "for", "Geeks" }; 
//		String st = Arrays.stream(array).reduce((s1,s2)->s1+"_"+s2).orElse("_");
//		System.out.println(st);
//		//Stream<String> stream=
//				Stream.generate(()->"rose").limit(5).forEach(s->System.out.println(s));
//		List <Integer>l1 = Arrays.asList(13,12,90);
//		IntStream.of(l1.stream().mapToInt(Integer::intValue).toArray())
//        .boxed()
//        .forEach(s -> System.out.println(s));
		//List<Integer> listWithDuplicates = Arrays.asList(1, 2, 3, 4, 5, 2, 3, 4);
//		Set<Integer> set = listWithDuplicates.stream().collect(Collectors.toSet());
//		Map<Integer,Long> map = listWithDuplicates.stream().collect(Collectors.groupingBy(i->i,Collectors.counting()));
//		Function<Integer, Integer> f = i->i*i;
//		System.out.println(f.apply(4));
//		Predicate<Integer> p1 = i->i%2==0;
//		Predicate<Integer> p2 = i->i>10;
//		System.out.println(p1.and(p2).test(14));
//		System.out.println("Hello");
//		Inter1 i = n->System.out.println(n*2);
//		i.sum(5);
//		i.print(0);
//		System.out.println(Inter1.a);
//		Collections.sort(listWithDuplicates,(k,j)->k<j?1:-1);
//		List<Integer> l2 = listWithDuplicates.stream().map(n->n<=3?n+4:n).collect(Collectors.toList());
//		l2.stream().forEach(k->System.out.print(k+" "));
//		Thread t = new Thread(()->{
//			System.out.println("Child");
//			synchronized (args) {
//				for(int i=0;i<5;i++) {
//					
//				}
//			}
//			try {
//				Thread.sleep(2000);
//				//Thread.yield();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//		t.start();
//		try {
//			t.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Main");
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String str = "rose";
		Arrays.asList(str.toCharArray()).forEach(x->System.out.println(x));
		//System.out.println(Arrays.asList(str.toCharArray()));
		
		List<Integer> l1=Arrays.asList(1,3,7,4,6,8,10);
		long a=l1.stream().mapToInt(Integer :: intValue).sum();
		System.out.println(a);
		
		int min = Arrays.stream(new int[] {}).min().orElse(0);
		System.out.println(min);
		List<Integer> l2=Arrays.asList(3,3,4,7,4,2,3);
		Map<Integer,Long> mp = l2.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		int max = mp.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
		System.out.println(max);
		mp.entrySet().stream().forEach(e->System.out.println(e.getKey()+"-"+e.getValue()));
//		List<Double> l2=l1.stream().map(i->i*1.0/l1.stream().mapToInt(Integer::intValue).sum()).collect(Collectors.toList());
//		l2.forEach(System.out::println);
		
	}
	

}

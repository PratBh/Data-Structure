package lambda;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
//		ArrayList<DVDInfo> dvds=new ArrayList<DVDInfo>();
//		Collections.sort(dvds, (one,two)->one.getGenre().compareTo(two.getGenre()));
//		PriorityQueue<DVDInfo> pq=new PriorityQueue<DVDInfo>((one,two)->one.getGenre().compareTo(two.getGenre()));
//		Comparator<DVDInfo> genSort=new Comparator<DVDInfo>() {
//			
//			@Override
//			public int compare(DVDInfo o1, DVDInfo o2) {
//				return o1.getGenre().compareTo(o2.getGenre());
//			}
//		};
//		
//		Comparator<DVDInfo> genSort2=(one,two)->one.getGenre().length()>two.getGenre().length()?-1:1;
//		Runnable r=()->{
//			for(int i=0;i<5;i++) {
//				System.out.println("Child : "+i);
//			}
//		};
//		Thread t=new Thread(()->{
//			for(int i=0;i<5;i++) {
//				System.out.println("Child : "+i);
//			}
//		});
//		t.run();
//		for(int i=0;i<5;i++) {
//			System.out.println("Parent : "+i);
//		}
		
		
		Dog boi=new Dog("boi", 30, 6);
		Dog ciol=new Dog("ciol", 35, 12);
//		DogsPlay dp=new DogsPlay((d)->{
//			System.out.println("Testing: "+d.getName());
//			return d.getAge()>9;
//		});
//		
//		System.out.println(dp.doQuerry(ciol));
//		System.out.println(dp.doQuerry(boi));
		
//		DogQuerier cd=d->{
//			System.out.println("Testing: "+d.getName());
//			return d.getAge()>9;
//		};
//		System.out.println(cd.test(boi));
//		System.out.println(cd.test(ciol));
		
//		Supplier<String> intSup=()->{
//			Map<String,String> env=System.getenv();
//			return env.get("USER");
//		};
//		
//		System.out.println(intSup.get());
		
		Consumer<String> redBlue=pill->{
			if(pill.equals("red"))
				System.out.println("Red");
			else
				System.out.println("Blue");
		};
		
		redBlue.accept("green");
		
		List<String> dogs=Arrays.asList("boi","buddy","ciol");
		dogs.forEach(System.out::print);
		Predicate<Dog> pDog=d->d.getAge()>9;
		Predicate<Dog> nameP=d->d.getName().equals("boi");
		Predicate<Dog> nameAge=d->nameP.or(pDog).test(d);
		System.out.println(nameAge.test(boi));
		System.out.println(nameAge.test(ciol));
		
		Function<Integer,String> fun=a->{
			if(a==42)
				return "valid";
			else
				return "Invalid";
		};
		
		
		ArrayList<Dog> dogL=new ArrayList<Dog>();
		dogL.add(ciol);
		dogL.add(boi);
		
		ArrayList<Dog> dogN=dogL.stream().filter(d->d.getAge()>12).collect(Collectors.toCollection(()->new ArrayList<Dog>()));
		
		Map<Integer, List<Dog>> dogByAge=dogL.stream().collect(Collectors.groupingBy(Dog::getAge));
		System.out.println(dogByAge);
		
		Map<Integer,Long> ageNum=dogL.stream().collect(Collectors.groupingBy(Dog::getAge, Collectors.counting()));
		System.out.println(ageNum);
		
		Map<Integer, List<String>> nameAgeDog=dogL.stream().collect(Collectors.groupingBy(Dog::getAge, Collectors.mapping(Dog::getName, Collectors.toList())));
		System.out.println(nameAgeDog);
		
		fun.apply(34);
		
//		Field f1;
//		try {
//			f1 = Dog.class.getField("name");
//			System.out.println(f1.getType());
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}

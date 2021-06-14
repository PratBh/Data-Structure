package stream;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lambda.Dog;

public class test {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
//		Integer[] nums= {1,2,3};
//		long numCount=Arrays.stream(nums).filter(i->i>1).count();
//		System.out.println(numCount);
//		Map<String,Integer> myMap=new HashMap<String,Integer>();
//		myMap.put("boi",1);
//		myMap.put("ciol",2);
//		myMap.put("bud",3);
//		myMap.entrySet().stream().filter(d->d.getValue()<4).count();
//		List<String> names=Arrays.asList("boi","charlie","zooey","aiko","clover","bokeh");
//		names.stream().filter(s->s.startsWith("c")||s.startsWith("c")).filter(s->s.length()>3).forEach(System.out::println);
//		boolean cNames=myMap.entrySet().stream().filter(d->d.getValue()<4).anyMatch(d->d.getKey().startsWith("c"));
//		Optional<Entry<String, Integer>> c5=myMap.entrySet().stream().filter(d->d.getValue()>1).filter(d->d.getKey().startsWith("b")).findAny();
//		System.out.println("anymatch: "+cNames);
//		System.out.println(c5);
//		List<Integer> l1=Arrays.asList(1,2,3,4,5,6,7);
//		System.out.println(l1.stream().map(n->n*n).filter(n->n>20).count());
//		long result=l1.stream().peek(n->System.out.println(n)).map(n->n*n).peek(n->System.out.println(n)).count();
//		System.out.println(result);
//		Dog boi=new Dog("boi", 30, 6);
//		Optional<Dog> opDog=Optional.of(boi);
//		Dog aDog=opDog.orElse(new Dog("pit", 10, 2));
//		opDog.ifPresent(System.out::println);
//		
//		List<String> pronouns=Arrays.asList("me","you","he","she");
//		List<String> bigP=pronouns.stream().map(String::toUpperCase).collect(Collectors.toList());
//		pronouns.stream().mapToInt(String::length).filter(num->num>2).forEach(System.out::println);
//		//System.out.println(numS);
//		String name="Prat";
//		Optional<String> opS=Optional.of(name);
//		opS.ifPresent(n->System.out.println(n.length()));
//		
//		String str="AM";
//		String str1=Optional.ofNullable(str).orElse("PM");
//		System.out.println(str1);
//		
//		boolean test=Optional.ofNullable(str).map(s->s.length()).filter(p->p>1).filter(p->p<3).isPresent();
//		System.out.println(test);
//		
//		List<String> companyNames = Arrays.asList(
//			      "paypal", "oracle", "", "microsoft", "", "apple");
//		Optional<List<String>> opList=Optional.of(companyNames);
//		int size=opList.map(l->l.size()).orElse(0);
//		System.out.println(size);
//		
//		String password = " password/ ";
//		Optional<String> opPas=Optional.of(password);
//		System.out.println(opPas.map(s->s.trim()).filter(s->s.equals("password")).isPresent());
//		
//		int min1=Arrays.stream(new int[] {1,2,3,4}).min().orElse(0);
		
		//int []arr= {-1, 4, 9, 7, 7, 2, 7, 3, 0, 9, 6, 5, 7, 8, 9};
//		List<Integer> l1=Arrays.asList(1,3,7,4,6,8,10);
//		List<Double> l2=l1.stream().map(i->i*1.0/l1.stream().mapToInt(Integer::intValue).sum()).collect(Collectors.toList());
//		l2.forEach(System.out::println);
//		Map<Integer, Long> mp=l1.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//		int max=mp.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
//		mp.entrySet().stream().sorted(Map.Entry.comparingByValue());
//		System.out.println(mp);
//		System.out.println("Max: "+max);
		Praactise pr=new Praactise();
		System.out.println(pr.convert("I am good"));
	}

}

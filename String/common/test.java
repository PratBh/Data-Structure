import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Test {

	public static void main(String[] args) {
		Problems prb=new Problems();
		//prb.suggestedProducts(new String[] {"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
		//prb.reverseParentheses("(u(love)i)");
		//prb.removeDuplicates("azxxxzy");
//		int[] nums= {0,0,1,1,1,1,2,3,3};
//		//prb.reverseSentence("I am a girl");
//		//prb.remove("abccbcba");
//	//	prb.shiftingLetters("abc", num);
//		//prb.removeDuplicates(nums);
//		//prb.arrangeWords("Keep calm and code on");
//		String str="zzzzzbbbccccddehhhhiii";
//		Map<Character, Long> mp=str.chars().mapToObj(i->Character.valueOf((char)i)).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//		System.out.println(mp);
//		//mp.entrySet().stream().filter(e->e.getValue()==1L).map(e->e.getKey()).forEach(System.out::println);
//		Optional<Character> opC=mp.entrySet().stream().filter(e->e.getValue()==1L).map(e->e.getKey()).findFirst();
//		if(opC.isPresent()) {
//			Character c=opC.get();
//			System.out.println(c);
//		}
//		
//		String s1="aabbc";
//		Map<Character,Long> mp2=s1.chars().mapToObj(i->Character.valueOf((char)i)).peek(System.out::println).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//		System.out.println(mp2);
		
//		String str2="i am a girl";
//		Map<Character,Long> mp3=str2.chars().mapToObj(i->Character.valueOf((char)i)).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//				//.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
//		System.out.println(mp3);
//		TreeMap<Integer, Integer> tmp=new TreeMap<Integer, Integer>((a,b)->a-b);
//		int[]arr= {1,1,1,5,3,4,5,1,5};
//		for(int i:arr) {
//			tmp.put(i, tmp.getOrDefault(i, 0)+1);
//		}
//		tmp.entrySet().stream().forEach(System.out::println);
		//prb.findPermutations("xyz");
		System.out.println(prb.fractionToDecimal(50, 22));
		
	}

}


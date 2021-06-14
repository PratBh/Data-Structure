
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {

	public static void main(String[] args) {
//		int[] nums = {-1,0,3,5,9,12};
//		//Leet.threeSum(nums);
//		//Leet.powerset(nums);
//		//System.out.println(Leet.addBinary("11","1" ));
//		ArrayList<Integer> res = new ArrayList<Integer>();
//		Node n1 = new Node();
//		Node n2 = new Node();
//		Node n3 = new Node();
//		Node n4 = new Node();
//		Node n5 = new Node();
//		Node n6 = new Node();
//		Node n7 = new Node();
//		
//		n1.val=1;
//		n2.val=2;
//		n3.val=3;
//		n4.val=4;
//		n5.val=5;
//		n6.val=6;
//		n7.val=7;
//		
//		
//		
//		n1.next=n2;
//		n2.prev=n1;
//		n2.next=n3;
//		n3.prev=n2;
//		n2.child=n4;
//		n4.next=n5;
//		n5.prev = n4;
//		n5.next=n6;
//		n6.prev=n5;
//		n6.child=n7;
//	
//		
//		//n1.flatten(n1,res);
//		//Leet.addDigits(953);
//		String s = "catsanddog";
		ArrayList<String> wordDict = new ArrayList<String>();
		
		wordDict.add("cat");
		wordDict.add("cats");
		wordDict.add("and");
		wordDict.add("sand");
		wordDict.add("dog");
		wordDict.get(0);
//		//Leet.wordBreakTopDown(s, wordDict);
//		//Leet.arrangeCoins(7);
//		int[] cells= {4,3,2,1};
//		//Leet.prisonAfterNDays(cells, 5);
//		
//		int[] height = {2,3,4,5,18,17,6};
//		//Leet.twoSum(nms,6)
//		//Leet.letterCombinations("23");
//		//Leet.maxArea(height);
//		String[] arr = {"word","good","best","good"};
//		//Leet.removeElement(cells,1);
//		//Leet.findSubstring(s, arr);
//		//Leet.nextPermutation(cells);
//		//Leet.combinationSum2(nums, 8);
//		//Leet.multiply("123","456");
//		//Leet.firstMissingPositive(nums);
//		//Leet.reverseSentence("I am a girl");
//		char[] ch= {'1','2','3'};
//		//Leet.possibleSubsets(ch);
//		//Leet.findPairs(nums,0);
//		Leet.binarysearch(nums, 0);
		
		String str="226";
		for(int i=2;i<=str.length();i++) {
			System.out.println("firstcut: "+str.substring(i-1, i));
			System.out.println("secondcut: "+str.substring(i-2, i));
			
		}
	}

}

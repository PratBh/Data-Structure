package HashMap;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeetCode {
	
//	Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
//
//			Each letter in magazine can only be used once in ransomNote.
//
//			 
//
//			Example 1:
//
//			Input: ransomNote = "a", magazine = "b"
//			Output: false
//			Example 2:
//
//			Input: ransomNote = "aa", magazine = "ab"
//			Output: false
//			Example 3:
//
//			Input: ransomNote = "aa", magazine = "aab"
//			Output: true
	
	public boolean canConstruct(String ransomNote, String magazine) {
		if(magazine.isBlank()||magazine.isEmpty())
			return false;
		Map<Character,Integer> mp = new HashMap<>();
        for(char c:magazine.toCharArray())
        	mp.put(c,mp.getOrDefault(c, 0)+1);
		for(char c:ransomNote.toCharArray()) {
			if(!mp.containsKey(c))
				return false;
			else {
				mp.put(c, mp.get(c)-1);
				if(mp.get(c)==0)
					mp.remove(c);
			}
		}
		return true;
    }
	
//	We are given n different types of stickers. Each sticker has a lowercase English word on it.
//
//	You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them.
	//You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
//
//	Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.
//
//	Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.
//
//	 
//
//	Example 1:
//
//	Input: stickers = ["with","example","science"], target = "thehat"
//	Output: 3
//	Explanation:
//	We can use 2 "with" stickers, and 1 "example" sticker.
//	After cutting and rearrange the letters of those stickers, we can form the target "thehat".
//	Also, this is the minimum number of stickers necessary to form the target string.
//	Example 2:
//
//	Input: stickers = ["notice","possible"], target = "basicbasic"
//	Output: -1
//	Explanation:
//	We cannot form the target "basicbasic" from cutting letters from the given stickers.
	public int minStickers(String[] stickers, String target) {
		HashMap<Character,Integer> mp = new HashMap<>();
		HashMap<HashMap<Character,Integer>,Integer> dp = new HashMap<>();
        for(char c:target.toCharArray())
        	mp.put(c,mp.getOrDefault(c, 0)+1);
        int res= minStickersUtil(mp, 0, stickers,dp);
        return (res==1000)?-1:res;
    }
	
	int minStickersUtil(HashMap<Character, Integer> mp,int index,String[] stickers,HashMap<HashMap<Character,Integer>,Integer> dp) {
		if(mp.isEmpty())
			return 0;
		if(index == stickers.length)
			return 1000;
		if(dp.containsKey(mp))
			return dp.get(mp);
		HashMap<Character,Integer> curr_map = (HashMap<Character, Integer>) mp.clone();
		boolean usable = false;int res =-1;
		for(char c:stickers[index].toCharArray()) {
			if(curr_map.containsKey(c)) {
				usable=true;
				curr_map.put(c, curr_map.get(c)-1);
				if(curr_map.get(c)==0)
					curr_map.remove(c);
			}
		}
		if(usable) {
			res = Math.min(1+minStickersUtil(curr_map, index, stickers,dp), minStickersUtil(mp, index+1, stickers,dp));
		}else {
			res = minStickersUtil(mp, index+1, stickers,dp);
		}
		dp.put(mp, res);
		return res;
	}
	
	//below is another solution above one passed 99/101 test cases
	//https://leetcode.com/problems/stickers-to-spell-word/solutions/3941287/unbounded-knapsack-solution-backtracking-memoization-xor-java/
//	private final Map<String, Integer> memo = new HashMap<>();
//    public int minStickers(String[] stickers, String target) {
//        int minStickers = slv(stickers.length - 1, target, stickers);
//        return minStickers > 50 ? -1 : minStickers;
//    }
//
//    private int slv(int n, String target, String[] stickers) {
//        if (target.isEmpty()) return 0;
//        if(n < 0) return 10000;
//        String state = target + n;
//        if(memo.containsKey(state)) return memo.get(state);
//        Set<Integer> indices = getMatchingChar(stickers[n], target);
//        if (!indices.isEmpty()) {
//            memo.put(state, Math.min(
//                    1 + slv(n, getNewTarget(target, indices), stickers),
//                    slv(n - 1, target, stickers)
//            ));
//        } else {
//            memo.put(state, slv(n - 1, target, stickers));
//        }
//        return memo.get(state);
//    }
//
//    private String getNewTarget(String target, Set<Integer> indices) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < target.length(); i++)
//            if(!indices.contains(i)) sb.append(target.charAt(i));
//        return sb.toString();
//    }
//
//    private Set<Integer> getMatchingChar(String sticker, String target) {
//        Set<Integer> indices = new HashSet<>();
//        for (char s: sticker.toCharArray()) {
//            for (int i = 0; i < target.length(); i++) {
//                if(indices.contains(i)) continue;
//                if ((s ^ target.charAt(i)) == 0) {
//                    indices.add(i);
//                    break;
//                }
//            }
//        }
//        return indices;
//    }
	
//	Given two strings s and t, determine if they are isomorphic.
//
//	Two strings s and t are isomorphic if the characters in s can be replaced to get t.
//
//	All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
//
//	 
//
//	Example 1:
//
//	Input: s = "egg", t = "add"
//	Output: true
//	Example 2:
//
//	Input: s = "foo", t = "bar"
//	Output: false
//	Example 3:
//
//	Input: s = "paper", t = "title"
//	Output: true
	//https://leetcode.com/problems/isomorphic-strings/solutions/3200873/determining-if-two-strings-are-isomorphic-in-python/?envType=study-plan-v2&envId=top-interview-150
	public boolean isIsomorphic(String s, String t) {
       Map<Character,Character> sMap = new HashMap<Character, Character>();
       Map<Character,Character> tMap = new HashMap<Character, Character>();
       for(int i=0;i<s.length();i++) {
    	   char c= s.charAt(i);
    	   if(sMap.containsKey(c)) {
    		   if(sMap.get(c)!=t.charAt(i))
    			   return false;
    	   }else {
    		   if(tMap.containsKey(t.charAt(i)))
    			   if(tMap.get(t.charAt(i))!=c)
    				   return false;
    	   }
    	   sMap.put(c, t.charAt(i));
    	   tMap.put(t.charAt(i),c);
    	   
       }
       return true;
    }
	
//	Given a pattern and a string s, find if s follows the same pattern.
//
//	Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
//
//	 
//
//	Example 1:
//
//	Input: pattern = "abba", s = "dog cat cat dog"
//	Output: true
//	Example 2:
//
//	Input: pattern = "abba", s = "dog cat cat fish"
//	Output: false
//	Example 3:
//
//	Input: pattern = "aaaa", s = "dog cat cat dog"
//	Output: false
	
	public boolean wordPattern(String pattern, String s) {
		String[] arr = s.split(" ");
		if(pattern.length()!=arr.length)
			return false;
		Map<Character,String> patternMap = new HashMap<Character, String>();
	    Map<String,Character> sMap = new HashMap<String, Character>();
	    for(int i=0;i<pattern.length();i++) {
	    	char c= pattern.charAt(i);
	    	String st=arr[i];
	    	if(patternMap.containsKey(c)){
	    		if(!patternMap.get(c).equals(st))
	    			return false;
	    	}else {
	    		if(sMap.containsKey(st)) {
	    			if(sMap.get(st)!=c)
	    				return false;
	    		}
	    	}
	    	patternMap.put(c, st);
	    	sMap.put(st, c);
	    }
	    return true;
    }
	
//	Given two strings s and t, return true if t is an anagram of s, and false otherwise.
//
//			An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
//
//			 
//
//			Example 1:
//
//			Input: s = "anagram", t = "nagaram"
//			Output: true
//			Example 2:
//
//			Input: s = "rat", t = "car"
//			Output: false 
//		public boolean isAnagram(String s, String t) {
//			if(s.length()!=t.length())
//				return false;
//			Map<Character,Long> mp = IntStream.range(0, s.length()).mapToObj(i->s.toCharArray()[i]).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//			for (char c:t.toCharArray()) {
//				if(!mp.containsKey(c))
//					return false;
//				mp.put(c, mp.get(c)-1);
//				if(mp.get(c)==0)
//					mp.remove(c);
//			}
//			return mp.isEmpty();
//        
//		}
		//above one faced TLE.below did not
		public boolean isAnagram(String s, String t) {
	        int[] count = new int[26];
	        
	        // Count the frequency of characters in string s
	        for (char x : s.toCharArray()) {
	            count[x - 'a']++;
	        }
	        
	        // Decrement the frequency of characters in string t
	        for (char x : t.toCharArray()) {
	            count[x - 'a']--;
	        }
	        
	        // Check if any character has non-zero frequency
	        for (int val : count) {
	            if (val != 0) {
	                return false;
	            }
	        }
	        
	        return true;
	    }
		
//		Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//
//				An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
//
//				 
//
//				Example 1:
//
//				Input: strs = ["eat","tea","tan","ate","nat","bat"]
//				Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//				Example 2:
//
//				Input: strs = [""]
//				Output: [[""]]
//				Example 3:
//
//				Input: strs = ["a"]
//				Output: [["a"]]
		
		public List<List<String>> groupAnagrams(String[] strs) {
			Map<String, List<String>> map = new HashMap<>();
			List<List<String>> res = new ArrayList<List<String>>();
			for(String word:strs) {
				char[] chars = word.toCharArray();
				Arrays.sort(chars);
                String sortedWord = new String(chars);
				if(!map.containsKey(sortedWord)) {
					map.put(sortedWord, new ArrayList<>());
				}
				map.get(sortedWord).add(word);
			}
			return new ArrayList<>(map.values());
	    }
		
//		Write an algorithm to determine if a number n is happy.
//
//		A happy number is a number defined by the following process:
//
//		Starting with any positive integer, replace the number by the sum of the squares of its digits.
//		Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
//		Those numbers for which this process ends in 1 are happy.
//		Return true if n is a happy number, and false if not.
//
//		 
//
//		Example 1:
//
//		Input: n = 19
//		Output: true
//		Explanation:
//		12 + 92 = 82
//		82 + 22 = 68
//		62 + 82 = 100
//		12 + 02 + 02 = 1
//		Example 2:
//
//		Input: n = 2
//		Output: false
		//https://leetcode.com/problems/happy-number/solutions/3767573/easy-java-solution-two-pointers-floyd-s-tortoise-and-hare-detailed-explanation/?envType=study-plan-v2&envId=top-interview-150
		
		public boolean isHappy(int n) {
	        int slow = n,fast=n;
	        do {
	        	slow=sqare(slow);
	        	fast=sqare(sqare(fast));
	        }while(slow!=fast);
	        	
	        return slow == 1;
	    }
		int sqare(int n) {
			int ans =0;
			while(n>0) {
				int reminder = n%10;
				ans+=reminder*reminder;
				n=n/10;
			}
			return ans;
		}
		
//		Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
//
//				 
//
//				Example 1:
//
//				Input: nums = [1,2,3,1], k = 3
//				Output: true
//				Example 2:
//
//				Input: nums = [1,0,1,1], k = 1
//				Output: true
//				Example 3:
//
//				Input: nums = [1,2,3,1,2,3], k = 2
//				Output: false
		//https://leetcode.com/problems/contains-duplicate-ii/solutions/2727290/c-easy-detailed-explaination-optimized/?envType=study-plan-v2&envId=top-interview-150
			public boolean containsNearbyDuplicate(int[] nums, int k) {
//				//Map<Integer,Long> mp = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(i->i,Collectors.counting()));
//				for(int i=0;i<nums.length-1;i++) {
//					for(int j=i+1;j<nums.length;j++) {
//						if(nums[i]==nums[j] && (Math.abs(i-j)<=k))
//							return true;
//					}
////					if(mp.get(nums[i])==2) {
////						
////					}
//				}
//				return false;
				
				Map<Integer,Integer> mp = new HashMap<Integer, Integer>();
				for(int i=0;i<nums.length;i++) {
					if(mp.containsKey(nums[i])) {
						if(Math.abs(i-mp.get(nums[i]))<=k)
							return true;
					}
					
					mp.put(nums[i], i);
					
				}
				return false;
			}
			
//			Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
//
//					 
//
//					Example 1:
//
//					Input: nums = [1,2,3,1]
//					Output: true
//					Example 2:
//
//					Input: nums = [1,2,3,4]
//					Output: false
//					Example 3:
//
//					Input: nums = [1,1,1,3,3,4,3,2,4,2]
//					Output: true
			
			public boolean containsDuplicate(int[] nums) {
				Set<Integer> mp = new HashSet<Integer>();
				for(int i=0;i<nums.length;i++) {
					if(mp.contains(nums[i])) {
						//if(Math.abs(i-mp.get(nums[i]))<=k)
							return true;
					}
					else {
					mp.add(nums[i]);
					}
					
				}
				return false;
		    }
			
//			You are given an integer array nums and two integers indexDiff and valueDiff.
//
//			Find a pair of indices (i, j) such that:
//
//			i != j,
//			abs(i - j) <= indexDiff.
//			abs(nums[i] - nums[j]) <= valueDiff, and
//			Return true if such pair exists or false otherwise.
//
//			 
//
//			Example 1:
//
//			Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
//			Output: true
//			Explanation: We can choose (i, j) = (0, 3).
//			We satisfy the three conditions:
//			i != j --> 0 != 3
//			abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
//			abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
//			Example 2:
//
//			Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
//			Output: false
//			Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
			
			//https://leetcode.com/problems/contains-duplicate-iii/solutions/825726/contains-duplicate-iii-best-theoretical-time-complexity-java/
			public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
				int n=nums.length;
				TreeSet<Long> set = new TreeSet<Long>();
				for(int i=0;i<n;i++) {
					Long number = new Long(nums[i]);
					Long ceil = set.ceiling(number);
					Long floor = set.floor(number);
					if(ceil!= null && ceil-number <=valueDiff)
						return true;
					if(floor != null && number-floor <= valueDiff)
						return true;
					set.add(number);
					if(set.size()>indexDiff) {
						Long oldest = new Long(nums[i-indexDiff]);
						set.remove(oldest);
					}
				}
				return false;
				
		    }
			
//			You are given a non-negative integer array nums. In one operation, you must:
//
//				Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
//				Subtract x from every positive element in nums.
//				Return the minimum number of operations to make every element in nums equal to 0.
//
//				 
//
//				Example 1:
//
//				Input: nums = [1,5,0,3,5]
//				Output: 3
//				Explanation:
//				In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
//				In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
//				In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].
//				Example 2:
//
//				Input: nums = [0]
//				Output: 0
//				Explanation: Each element in nums is already 0 so no operations are needed.
//				 
//
//				Constraints:
//
//				1 <= nums.length <= 100
//				0 <= nums[i] <= 100	
			//https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/solutions/3317297/java-solution-using-priority-queue/
			/*
			 * In the below the logic is we are creating a min heap.putting all data there.Every time we poll we will get the minimum.So if minimum is 0 then we do
			 * not increase count or if we have duplicate value then also we do not increase.Else we are increasing count.*/
			public int minimumOperations(int[] nums) {
		        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // min heap
		        for(int i:nums)
		        	pq.offer(i);
		        int res=0,temp=-1;
		        while(!pq.isEmpty()) {
		        	int pulled = pq.poll();
		        	if(pulled==0 || pulled-temp==0)
		        		continue;
		        	temp=pulled;
		        	res++;
		        }
		        return res;
		    }
			
//			You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
//
//				difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
//				worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
//				Every worker can be assigned at most one job, but one job can be completed multiple times.
//
//				For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
//				Return the maximum profit we can achieve after assigning the workers to the jobs.
//
//				 
//
//				Example 1:
//
//				Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
//				Output: 100
//				Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
//				Example 2:
//
//				Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
//				Output: 0
	//https://leetcode.com/problems/most-profit-assigning-work/solutions/3918050/java-17ms-execution-time-beats-80-in-time-and-97-in-space-taken/
			static class Pair implements Comparable<Pair>{
				int diff;
				int pro;
				Pair(int diff,int pro){
					this.diff=diff;
					this.pro=pro;
				}
				@Override
				public int compareTo(Pair o) {
					return this.diff-o.diff;
				}
			}
			
			public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		        int n= difficulty.length;
		        Pair[] pairs = new Pair[n];
		        for(int i=0;i<n;i++)
		        	pairs[i]=new Pair(difficulty[i], profit[i]);
		        
		        Arrays.sort(pairs);
		        int[] maxSoFar = new int[n];
		        maxSoFar[0]=pairs[0].pro;
		        for(int i=1;i<n;i++) {
		        	 if (pairs[i].pro > maxSoFar[i-1]) {
		                 maxSoFar[i] = pairs[i].pro;
		             } else {
		                 maxSoFar[i] = maxSoFar[i-1];
		             }
		        }
		        int total=0;
		        for(int i=0;i<worker.length;i++) {
		        	int index = getIndex(worker[i], pairs, n);
		        	if(index!= -1)
		        		total+=maxSoFar[index];
		        }
		        return total;
		        
		    }
			
			int getIndex(int cap,Pair[] pairs,int n) {
				int low=0,high=n-1;
				while(low<=high) {
					int mid = low+(high-low)/2;
					if(pairs[mid].diff==cap) {
						while(mid<high && pairs[mid+1].diff==cap)
							mid++;
						return mid;
					}
					else if(pairs[mid].diff>cap)
						high=mid-1;
					else if (pairs[mid].diff<cap)
						low=mid+1;
				}
				return high;
			}
			
//			Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//					You must write an algorithm that runs in O(n) time.
//
//					 
//
//					Example 1:
//
//					Input: nums = [100,4,200,1,3,2]
//					Output: 4
//					Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//					Example 2:
//
//					Input: nums = [0,3,7,2,5,8,4,6,0,1]
//					Output: 9
//					 
//
//					Constraints:
//
//					0 <= nums.length <= 105
//					-109 <= nums[i] <= 109
					
			//https://leetcode.com/problems/longest-consecutive-sequence/solutions/3171985/best-c-4-solution-hash-table-sorting-brute-force-optimize-one-stop-solution/?envType=study-plan-v2&envId=top-interview-150
			public int longestConsecutive(int[] nums) {
				Set<Integer> set = Arrays.stream(nums).boxed().map(i->i).collect(Collectors.toSet());
				int len =0;
				for(int num:nums) {
					if(!set.contains(num-1)) {
						int curr = num;
						int currLen = 1;
						while(set.contains(curr+1)) {
							curr++;
							currLen++;
						}
						len=Math.max(currLen, len);
					}
				}
				return len;
		    }
			
//			You are given a sorted unique integer array nums.
//
//			A range [a,b] is the set of all integers from a to b (inclusive).
//
//			Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges,
			//and there is no integer x such that x is in one of the ranges but not in nums.
//
//			Each range [a,b] in the list should be output as:
//
//			"a->b" if a != b
//			"a" if a == b
//			 
//
//			Example 1:
//
//			Input: nums = [0,1,2,4,5,7]
//			Output: ["0->2","4->5","7"]
//			Explanation: The ranges are:
//			[0,2] --> "0->2"
//			[4,5] --> "4->5"
//			[7,7] --> "7"
//			Example 2:
//
//			Input: nums = [0,2,3,4,6,8,9]
//			Output: ["0","2->4","6","8->9"]
//			Explanation: The ranges are:
//			[0,0] --> "0"
//			[2,4] --> "2->4"
//			[6,6] --> "6"
//			[8,9] --> "8->9"
			
			public List<String> summaryRanges(int[] nums) {
		        int n=nums.length,i=0,prev=0,j=1;
		        List<String> res= new ArrayList<String>();
		        while(i<n&&prev<n) {
		        	if((j<n)&&(nums[j]==nums[prev]+1)) {
		        		while((j<n)&&(nums[j]==nums[prev]+1)) {
		        			prev++;
		        			j++;
		        		}
		        		res.add(Integer.toString(nums[i])+"->"+Integer.toString(nums[prev]));
		        		i=j;
		        		prev=j;
		        		j++;
		        	}else if(i==prev) {
		        		res.add(Integer.toString(nums[prev]));
		        		i++;
		        		prev++;
		        		j++;
		        	}else {
		        		i=j;
		        		prev=j;
		        		j++;
		        	}
		        }
		        return res;
		    }
			
//			Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
//
//					 
//
//					Example 1:
//
//					Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//					Output: [[1,6],[8,10],[15,18]]
//					Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//					Example 2:
//
//					Input: intervals = [[1,4],[2,3]]
//					Output: [[1,4]]
//					Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//					 
//
//					Constraints:
//
//					1 <= intervals.length <= 104
//					intervals[i].length == 2
//					0 <= starti <= endi <= 104
			
			public int[][] merge(int[][] intervals) {
		        Arrays.sort(intervals,new Comparator<int[]>() {

					@Override
					public int compare(int[] o1, int[] o2) {
						return Integer.compare(o1[0], o2[0]);
					}
				});
		        int n=intervals.length;
		        List<int[]> res = new ArrayList<int[]>();
		        int i=0,j=1;
		        
		        while(i<n) {
		        	int[] curr = intervals[i];
		        	
		        	if((j<n)&&(curr[1]>=intervals[j][0])&&((curr[1]>=intervals[j][1])||(curr[1]<=intervals[j][1]))) {
		        		int [] temp = new int[2];
		        		while((j<n)&&(curr[1]>=intervals[j][0])&&((curr[1]>=intervals[j][1])||(curr[1]<=intervals[j][1]))) {
		        			temp[0]=curr[0];
		        			temp[1]=(curr[1]>=intervals[j][1])?curr[1]:intervals[j][1];
		        			curr=temp;
		        			j++;
		        		}
		        		res.add(temp);
		        		i=j;
		        		j++;
		        	}else {
		        		res.add(curr);
		        		i=j;
		        		j++;
		        	}
		        }
		        return res.toArray(new int[0][]);
		    }
			
//			You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
//
//					Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
//
//					Return intervals after the insertion.
//
//					 
//
//					Example 1:
//
//					Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//					Output: [[1,5],[6,9]]
//					Example 2:
//
//					Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//					Output: [[1,2],[3,10],[12,16]]
//					Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
//					 
//
//					Constraints:
//
//					0 <= intervals.length <= 104
//					intervals[i].length == 2
//					0 <= starti <= endi <= 105
//					intervals is sorted by starti in ascending order.
//					newInterval.length == 2
//					0 <= start <= end <= 105
			
			public int[][] insert(int[][] intervals, int[] newInterval) {
		        int n=intervals.length;
		        int i=n-1,index=0;
		        while(i>=0) {
		        	if(newInterval[0]>intervals[i][0]) {
		        		index=i+1;
		        		break;
		        	}
		        	else
		        		i--;
		        }
		        int[][] newArr = new int[n+1][];
		        for(int j=0;j<n+1;j++) {
		        	if(j<index)
		        		newArr[j]=intervals[j];
		        	else if(j==index)
		        		newArr[j]=newInterval;
		        	else
		        		newArr[j]=intervals[j-1];
		        }
		        List<int[]> res = new ArrayList<int[]>();
		        int in=0,j=1;
		        
		        while(in<n+1) {
		        	int[] curr = newArr[in];
		        	
		        	if((j<n+1)&&(curr[1]>=newArr[j][0])&&((curr[1]>=newArr[j][1])||(curr[1]<=newArr[j][1]))) {
		        		int [] temp = new int[2];
		        		while((j<n+1)&&(curr[1]>=newArr[j][0])&&((curr[1]>=newArr[j][1])||(curr[1]<=newArr[j][1]))) {
		        			temp[0]=curr[0];
		        			temp[1]=(curr[1]>=newArr[j][1])?curr[1]:newArr[j][1];
		        			curr=temp;
		        			j++;
		        		}
		        		res.add(temp);
		        		in=j;
		        		j++;
		        	}else {
		        		res.add(curr);
		        		in=j;
		        		j++;
		        	}
		        }
		        return res.toArray(new int[0][]);
		        
		    }
			
//			There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
//
//					Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
//
//					Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
//
//					 
//
//					Example 1:
//
//					Input: points = [[10,16],[2,8],[1,6],[7,12]]
//					Output: 2
//					Explanation: The balloons can be burst by 2 arrows:
//					- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
//					- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
//					Example 2:
//
//					Input: points = [[1,2],[3,4],[5,6],[7,8]]
//					Output: 4
//					Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
//					Example 3:
//
//					Input: points = [[1,2],[2,3],[3,4],[4,5]]
//					Output: 2
//					Explanation: The balloons can be burst by 2 arrows:
//					- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
//					- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
//					 
//
//					Constraints:
//
//					1 <= points.length <= 105
//					points[i].length == 2
//					-231 <= xstart < xend <= 231 - 1
			//https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/3000383/c-easy-to-understand-fully-explained/?envType=study-plan-v2&envId=top-interview-150
			
			public int findMinArrowShots(int[][] points) {
		        Arrays.sort(points,new Comparator<int[]>() {

					@Override
					public int compare(int[] o1, int[] o2) {
						// TODO Auto-generated method stub
						return Integer.compare(o1[0], o2[0]);
					}
				});
		        
		        int ans=1,arrow = points[0][1],n=points.length;
		        for(int i=1;i<n;i++) {
		        	if(arrow<points[i][0]) {
		        		arrow=points[i][1];
		        		ans++;
		        	}
		        	else {
		        	arrow = Math.min(arrow, points[i][1]);
		        	}
		        }
		        return ans;
		    }
}

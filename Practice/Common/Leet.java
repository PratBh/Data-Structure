import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.print.DocFlavor.STRING;

public class Leet {
	public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                for (int k =j+1;k<nums.length;k++){
                    if((nums[i]+nums[j]+nums[k])==0){
                        List<Integer> tmpL = new ArrayList<>();
                        tmpL.add(nums[i]);
                        tmpL.add(nums[j]);
                        tmpL.add(nums[k]);
                        res.add(tmpL);
                    }
                }
            }
        }
       return res; 
    }
	
	public static List<List<Integer>> powerset(int[] nums){
		int setSize = (int) Math.pow(2, nums.length);
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<>());
		for(int counter =0;counter<setSize;counter++) {
			List<Integer> tmpL = new ArrayList<>();
			for(int j=0;j<nums.length;j++) {
				if((counter & (1 << j))> 0) {
					System.out.print(nums[j]);
					tmpL.add(nums[j]);
				}
			}
			res.add(tmpL);
			System.out.println();
		}
		return res;
	}
	
	static public int reverseBits(int n) {
		//n=5
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans = ans | (n & 1);
            n >>= 1;
        }
        return ans;
    }
	
	
	static String reverseSentence (String s) {
		String[] words = s.split("\\s");
		String reversedSentence = "";
		
		for (int i=0;i<words.length ; i++) {
			if(words[i].equals(""))
				continue;
			if(i==words.length-1) {
				reversedSentence = words[i] + reversedSentence;
			}
			else {
				reversedSentence = " " + words[i] + reversedSentence;
			}
		}
		
		return reversedSentence;
	}
	
	public static double myPow(double x, int n) {
		double res=0;
        if(n==0){
            return 1;
        }
        if(n==1)
        	return x;
        if(n==-1)
        	return (1/x);
        if(n>0) {
        	res=x;
        	for(int i=2;i<=n;i++) {
        		res=res*x;
        	}
        }
        
        if(n<0) {
        	res = (1/x);
        	for (int i=-2;i>=n;i--) {
        		res= res * (1/x);
        	}
        }
        
        return res;
        
    }
	
	public static int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> elementCountMap = new HashMap<Integer, Integer>();
		for(int i:nums) {
			if(elementCountMap.containsKey(i)) {
				elementCountMap.put(i, elementCountMap.get(i)+1);
			}else {
				elementCountMap.put(i, 1);
			}
		}
		int pos=0;
		int[] res = new int[nums.length];
		Iterator hmIterator = elementCountMap.entrySet().iterator();
		while(hmIterator.hasNext()) {
			Map.Entry<Integer,Integer> el = (Map.Entry)hmIterator.next();
			if(el.getValue()>=k)
				res[pos]=el.getKey();
			pos++;	
		}
		
		return res;
    }
	
	public static String addBinary(String a, String b) {
        String res="";
        int s=0;
        int i=a.length()-1,j=b.length()-1;
        while(i>=0 || j>=0 || s==1) {
        	s+=((i>=0)?a.charAt(i)-'0':0);
        	s+=((j>=0)?b.charAt(j)-'0':0);
        	
        	res = (char)(s % 2 + '0') + res; 
        	s /= 2;
        	i--;j--;
        }
        return res;
    }
	
	public ListNode removeElements(ListNode head,int key) {
		ListNode helper = new ListNode(0);
        helper.next=head;
        ListNode p=helper;
        while(p.next!=null){
            if(p.next.key==key){
                ListNode next = p.next;
                p.next=next.next;
                next.next=null;
            }else{
                p=p.next;
            }
        }
        
        return helper.next;
	}
	
	public boolean exist(char[][] board, String word) {
		if(board == null || word == null)
			return false;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				if(board[i][j]==word.charAt(0)&& findWord(board, word, i, j, 0))
					return true;
			}
		}
		return false;
	}
	
	public boolean findWord(char[][] board, String word,int i,int j,int count) {
		if(count == word.length())
			return true;
		if(i<0||i>=board.length||j<0||j>=board[i].length||board[i][j] != word.charAt(count))
			return false;
		
		char temp=board[i][j];
		board[i][j] = ' ';
		boolean found = findWord(board, word, i, j+1, count+1)||findWord(board, word, i, j-1, count+1)
							|| findWord(board, word, i+1, j, count+1)||findWord(board, word, i-1, j, count+1);
		board[i][j]=temp;
		return found;
	}
	
	public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> hm=new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++) {
        	if(hm.containsKey(nums[i]))
        		hm.put(nums[i], hm.get(nums[i])+1);
        	else
        		hm.put(nums[i], 1);
        }
        ArrayList<Integer> ar = new ArrayList<Integer>();
		Iterator hmIterator = hm.entrySet().iterator();
		while(hmIterator.hasNext()) {
			Map.Entry<Integer,Integer> el = (Map.Entry)hmIterator.next();
			if(el.getValue()==1)
				ar.add(el.getKey());	
		}
		
		int[] ret = new int[ar.size()];
	    Iterator<Integer> iterator = ar.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
        
    }
		public static int addDigits(int inp) {
			int sum=0;
			while(inp>0||sum>9) {
				if(inp==0) {
					inp=sum;
					sum=0;
				}
				sum += inp%10;
				inp=inp/10;
			}
			return sum;
		}
		
//		You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter represents a different task.
//		Tasks could be done without the original order of the array. Each task is done in one unit of time. 
//		For each unit of time, the CPU could complete either one task or just be idle.
//
//		However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), 
//		that is that there must be at least n units of time between any two same tasks.
//
//		You need to return the least number of units of times that the CPU will take to finish all the given tasks.
		
//		Input: tasks = ["A","A","A","B","B","B"], n = 2
//				Output: 8
//				Explanation: 
//				A -> B -> idle -> A -> B -> idle -> A -> B
//				There is at least 2 units of time between any two same tasks.
		//solution youtube nick white
		
		public int leastInterval(char[] tasks, int n) {
			int [] char_map=new int[26];//this array will hold all the frequence of each task represented by each aphabet
			
			for (char c: tasks) {
				char_map[c-'A']++;//c-'A' means if c is B then c-'A'=1,so 1th index in char_map will be incremented
			}
			
			Arrays.sort(char_map);//sorting ascending order,most frequent will be the last one
			
			int max_freq = char_map[25];
			int idle_slots = (max_freq-1)*n; // -1 is there because there will not be any idle slot after the last one
			
			for (int i=24;i>=0;i--) {
				idle_slots -=Math.min(char_map[i], max_freq-1);//filling the empty slots with other tasks.If all slots are filled then result is task length.
			}
			
			return idle_slots>0?idle_slots+tasks.length:tasks.length;
		}
		
		
//		Say you have an array for which the ith element is the price of a given stock on day i.
//
//		Design an algorithm to find the maximum profit. You may complete as many transactions as 
//		you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//		You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//		After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//		Example:
//
//		Input: [1,2,3,0,2]
//		Output: 3 
//		Explanation: transactions = [buy, sell, cooldown, buy, sell]
		//solution:https://www.youtube.com/watch?v=pkiJyNijgBw
		
		public int maxProfit(int[] prices) {
	        if(prices.length<=1)
	            return 0;
	        int A=0;
	        int B=(-1)*prices[0];
	        int C=0;
	        
	        for (int i=1;i<prices.length;i++){
	            int tmp=A;
	            A=Math.max(tmp,C);
	            C=B+prices[i];
	            B=Math.max(B,tmp-prices[i]);
	        }
	        
	        return Math.max(A,C);
	    }
		
//		Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence 
//		where each word is a valid dictionary word. Return all such possible sentences.
//
//		Note:
//
//		The same word in the dictionary may be reused multiple times in the segmentation.
//		You may assume the dictionary does not contain duplicate words.
//		Example 1:
//
//		Input:
//		s = "catsanddog"
//		wordDict = ["cat", "cats", "and", "sand", "dog"]
//		Output:
//		[
//		  "cats and dog",
//		  "cat sand dog"
//		]
		
//		solution : https://www.youtube.com/watch?v=WepWFGxiwRs,https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BreakMultipleWordsWithNoSpaceIntoSpace.java
		
		static public List<String> wordBreakTopDown(String s, List<String> wordDict) {
			Set<String> dict = new HashSet<String>();
	    	dict.addAll(wordDict);
	        Map<Integer, List<String>> dp = new HashMap<>();
	        int max = 0;
	        for (String s1 : wordDict) {
	            max = Math.max(max, s1.length());//finding the length of most lengthy word in the dictionary
	        }
	        return wordBreakUtil(s, dict, dp, 0, max);
	    }

	    static List<String> wordBreakUtil(String s, Set<String> dict, Map<Integer, List<String>> dp, int start, int max) {
	        if (start == s.length()) {
	            return Collections.singletonList("");
	        }

	        if (dp.containsKey(start)) {
	            return dp.get(start);
	        }

	        List<String> words = new ArrayList<>();
	        for (int i = start; i < start + max && i < s.length(); i++) {
	            String newWord = s.substring(start, i + 1);
	            if (!dict.contains(newWord)) {
	                continue;
	            }
	            List<String> result = wordBreakUtil(s, dict, dp, i + 1, max);
	            for (String word : result) {
	                String extraSpace = word.length() == 0 ? "" : " ";
	                words.add(newWord + extraSpace + word);
	            }
	        }
	        dp.put(start, words);
	        return words;
	    }
		/**
		 * Date 08/01/2014
		 * @author tusroy
		 * 
		 * Given a string and a dictionary, split this string into multiple words such that
		 * each word belongs in dictionary.
		 * 
		 * e.g peanutbutter -> pea nut butter
		 * e.g Iliketoplay -> I like to play
		 * 
		 * Solution 
		 * DP solution to this problem
		 * if( input[i...j] belongs in dictionary) T[i][j] = i
		 * else{
		 *     T[i][j] = k if T[i][k-1] != -1 && T[k][j] != -1
		 *     
		 * Test cases
		 * 1) Empty string
		 * 2) String where entire string is in dictionary
		 * 3) String which cannot be split into words which are in dictionary
		 * 3) String which can be split into words which are in dictionary    
		 *
		 */
		
		/**
	     * Dynamic programming version for breaking word problem.
	     * It returns null string if string cannot be broken into multipe words
	     * such that each word is in dictionary.
	     * Gives preference to longer words over splits
	     * e.g peanutbutter with dict{pea nut butter peanut} it would result in
	     * peanut butter instead of pea nut butter.
	     */
	    static public String breakWordDP(String word, List<String> wordDict){
	    	Set<String> dict = new HashSet<String>();
	    	dict.addAll(wordDict);
	    	
	    	int T[][]=new int [word.length()][word.length()];
	    	
	    	for (int i=0;i<word.length();i++) {
	    		for(int j=0;j<T[i].length;j++) {
	    			T[i][j]=-1;
	    		}
	    	}
	    	
	    	for (int l=1;l<=word.length();l++) {
	    		for (int i=0;i<word.length()-l+1;i++) {
	    			int j=i+l-1;
	    			String str = word.substring(i,j+1);
	    			if(dict.contains(str)) {
	    				T[i][j]=i;
	    				continue;
	    			}
	    			
	    			for (int k=i+1;k<=j;k++) {
	    				if(T[i][k-1]!=-1 && T[k][j]!=-1) {
	    					T[i][j]=k;
	    					break;
	    				}
	    			}
	    		}
	    	}
	    	
	    	if(T[0][word.length()-1]==-1)
	    		return null;
	    	
	    	StringBuffer sb=new StringBuffer();
	    	int i=0,j=word.length()-1;
	    	while(i<j) {
	    		int k=T[i][j];
	    		if(k==i) {
	    			sb.append(word.substring(i,j+1));
	    			break;
	    		}
	    		sb.append(word.substring(i,k)+" ");
	    		i=k;
	    	}
	    	
	    	return sb.toString();
	    }
	    
//	    You are climbing a stair case. It takes n steps to reach to the top.
//
//	    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//	    Example 1:
//
//	    Input: 2
//	    Output: 2
//	    Explanation: There are two ways to climb to the top.
//	    1. 1 step + 1 step
//	    2. 2 steps
//	    Example 2:
//
//	    Input: 3
//	    Output: 3
//	    Explanation: There are three ways to climb to the top.
//	    1. 1 step + 1 step + 1 step
//	    2. 1 step + 2 steps
//	    3. 2 steps + 1 step
	    
//	    Sol:
//	    	Algorithm
//
//	    	As we can see this problem can be broken into subproblems, and it contains the optimal substructure property i.e.
//	    its optimal solution can be constructed efficiently from optimal solutions of its subproblems, we can use dynamic programming to solve this problem.
//
//	    	One can reach i^{th}i 
//	    	th
//	    	  step in one of the two ways:
//
//	    	Taking a single step from (i-1)^{th}(i−1) 
//	    	th
//	    	  step.
//
//	    	Taking a step of 22 from (i-2)^{th}(i−2) 
//	    	th
//	    	  step.
//
//	    	So, the total number of ways to reach i^{th}i 
//	    	th
//	    	  is equal to sum of ways of reaching (i-1)^{th}(i−1) 
//	    	th
//	    	  step and ways of reaching (i-2)^{th}(i−2) 
//	    	th
//	    	  step.
//
//	    	Let dp[i]dp[i] denotes the number of ways to reach on i^{th}i 
//	    	th
//	    	  step:
//
//	    	dp[i]=dp[i-1]+dp[i-2]
//	    	dp[i]=dp[i−1]+dp[i−2]  Now if we look closer this is nothing but a fibonacci series
	    
	    static public int climbStairs(int n) {
	        if(n==0)
	        	return 0;
	        if(n==1)
	        	return 1;
	        int [] dp = new int[n+1];//in this array dp[i] denotes number of ways to reach i number of steps
	        dp[1]=1;//we can reach 1 step in 1 way only;
	        dp[2]=2;//reach 2 steps 2 ways
	        
	        for (int i=3;i<=n;i++) {
	        	dp[i]=dp[i-1]+dp[i-2];
	        }
	        
	        return dp[n];
	    }
	    
	    static public int climbStairsFibbo(int n) {
	    	if(n==0)
	        	return 0;
	        if(n==1)
	        	return 1;
	        int first=1,second=2,third;
	        for (int i=3;i<=n;i++) {
	        	third = first+second;
	        	first=second;
	        	second=third;
	        }
	        return second;
	    }
	    
//	    You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
//
//	    Given n, find the total number of full staircase rows that can be formed.
//
//	    n is a non-negative integer and fits within the range of a 32-bit signed integer.
//
//	    Example 1:
//
//	    n = 5
//
//	    The coins can form the following rows:
//	    ¤
//	    ¤ ¤
//	    ¤ ¤
//
//	    Because the 3rd row is incomplete, we return 2.
//	    Example 2:
//
//	    n = 8
//
//	    The coins can form the following rows:
//	    ¤
//	    ¤ ¤
//	    ¤ ¤ ¤
//	    ¤ ¤

	    //Because the 4th row is incomplete, we return 3.
	    
	    
//	    This question is easy in a sense that one could run an exhaustive iteration to obtain the result. 
//	    That could work, except that it would run out of time when the input becomes too large. 
//	    So let us take a step back to look at the problem, before rushing to the implementation.
//
//	    every kth row should have k coins i.e. 1st row 1 coin,2nd row 2 coins etc.
//	    So to form k complete rows we need at least 1+2+3+....+k=k(k+1)/2 coins
	    
//	    https://www.youtube.com/watch?v=dntz-wpip30
	    static public int arrangeCoins(int n) {
	    	long left = 0, right = n;
	        long k, curr;
	        while (left <= right) {
	          k = left + (right - left) / 2;
	          curr = k * (k + 1) / 2;

	          if (curr == n) return (int)k;

	          if (n < curr) {
	            right = k - 1;
	          } else {
	            left = k + 1;
	          }
	        }
	        return (int)right;
	    }
	    
	    static boolean isFibonacci(int n) 
	    {  
	        return isPerfectSquare(5*n*n + 4) || 
	               isPerfectSquare(5*n*n - 4); 
	    } 
	    
	    static  boolean isPerfectSquare(int x) 
	    { 
	        int s = (int) Math.sqrt(x); 
	        return (s*s == x); 
	    } 
	    
//	 	There are 8 prison cells in a row, and each cell is either occupied or vacant.
	    //
//	     	Each day, whether the cell is occupied or vacant changes according to the following rules:
	    //
//	     	If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
//	     	Otherwise, it becomes vacant.
//	     	(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
	    //
//	     	We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
	    //
//	     	Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
	    //
//	     	 
	    //
//	     	Example 1:
	    //
//	     	Input: cells = [0,1,0,1,1,0,0,1], N = 7
//	     	Output: [0,0,1,1,0,0,0,0]
//	     	Explanation: 
//	     	The following table summarizes the state of the prison on each day:
//	     	Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
//	     	Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
//	     	Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
//	     	Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
//	     	Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
//	     	Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
//	     	Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
//	     	Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
	    //
//	     	Example 2:
	    //
//	     	Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
//	     	Output: [0,0,1,1,1,1,1,0]
//	     	 
	    //
//	     	Note:
	    //
//	     	cells.length == 8
//	     	cells[i] is in {0, 1}
//	     	1 <= N <= 10^9
	     	
	     	static int[] prisonAfterNDays(int[] cells, int N) {
	            Map<String,Integer> hm = new HashMap<String, Integer>();
	            for (int i=0;i<N;i++) {
	            	String s = Arrays.toString(cells);
	            	if(hm.containsKey(s)) {
	            		int loop_length = i-hm.get(s);
	            		int remaing_days = (N-i)%loop_length;
	            		return prisonAfterNDays(cells, remaing_days);
	            	}else {
	            		hm.put(s, i);
	            		int prev = cells[0];
	            		for(int j=1;j<7;j++) {
	            			int next = cells[j+1];
	            			int curr = cells[j];
	            			if(prev==next)
	            				cells[j]=1;
	            			else cells[j]=0;
	            			prev=curr;
	            		}
	            	}
	            	cells[0]=cells[7]=0;
	            }
	            
	            return cells;
	        }
	     	
	     	
//	     	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//	     			You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//	     			Example:
//
//	     			Given nums = [2, 7, 11, 15], target = 9,
//
//	     			Because nums[0] + nums[1] = 2 + 7 = 9,
//	     			return [0, 1].
	     	
	     	public static int[] twoSum(int[] nums, int target) {
	            ArrayList<Integer> res = new ArrayList<Integer>();
	            for (int i=0;i<nums.length;i++) {
	            	for(int j=i+1;j<nums.length;j++) {
	            		if(nums[i]+nums[j]==target) {
	            			res.add(i);
	            			res.add(j);
	            		}
	            	}
	            }
	            int[] arr =new int[res.size()];
	            int pos=0;
	            for(int i:res) {
	            	arr[pos]=i;
	            	pos++;
	            }
	            
	            return arr;
	        }
	     	
	     	
//	     	You are given two non-empty linked lists representing two non-negative integers. 
//	     	The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
//	     			You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//	     			Example:
//
//	     			Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//	     			Output: 7 -> 0 -> 8
//	     			Explanation: 342 + 465 = 807.
	     	
	     	public Node addTwoNumbers(Node l1, Node l2) {
	            int carry = 0;
	            Node result = null;
	            Node tmp=null;
	            Node prev = null;
	            while(l1 != null || l2!=null) {  
	            	int sum = (l1 == null ?0:l1.val) + (l1 == null ?0:l2 .val)+carry;
	            	carry = (sum > 9)?1:0;
	            	sum = sum%10;
	            	tmp = new Node();
	            	tmp.val=sum;
	            	if(result==null)
	            		result=tmp;
	            	else {
	            		prev.next=tmp;
	            	}
	            	prev=tmp;
	            	if(l1!=null)
	            		l1=l1.next;
	            	if(l2!=null)
	            		l2=l2.next;
	            }
	            
	            if(carry == 1)
	            	tmp.next= new Node();
	            	tmp.next.val=carry;
	            
	            return result;
	        }
	     	
	     	public static Node reverseNode(Node root) {
	     		Node prev=null,next=null;
	     		Node current = root;
	     		while(current != null) {
	     			next = current.next;
	     			current.next=prev;
	     			prev=current;
	     			current = next;
	     		}
	     		return prev;
	     	}
	     	
//	     	Given a string, find the length of the longest substring without repeating characters.
//
//	     	Example 1:
//
//	     	Input: "abcabcbb"
//	     	Output: 3 
//	     	Explanation: The answer is "abc", with the length of 3. 
//	     	Example 2:
//
//	     	Input: "bbbbb"
//	     	Output: 1
//	     	Explanation: The answer is "b", with the length of 1.
//	     	Example 3:
//
//	     	Input: "pwwkew"
//	     	Output: 3
//	     	Explanation: The answer is "wke", with the length of 3. 
//	     	             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	     	//we solve this using sliding window approach.a is the start and b is the end of window.We keep on expanding the window i.e. b till we get 
	     	//a duplicate character.Once duplicate found we increment a and b both.
	     	
	     	public int lengthOfLongestSubstring(String s) {
	            int a_pointer = 0;
	            int b_pointer=0;
	            
	            int max = 0;
	            HashSet<Character> hs = new HashSet<Character>();
	            while(b_pointer<s.length()) {
	            	if(!hs.contains(s.charAt(b_pointer))) {
	            		hs.add(s.charAt(b_pointer));
	            		b_pointer++;
	            		max = Math.max(hs.size(), max);
	            	}else {
	            		hs.remove(s.charAt(a_pointer));
	            		a_pointer++;
	            	}
	            }
	            
	            return max;
	        }
	     	
	     	
	     	
//	     	Given two sorted arrays nums1 and nums2 of size m and n respectively.
//
//	     	Return the median of the two sorted arrays.
//
//	     	Follow up: The overall run time complexity should be O(log (m+n)).
//
//	     	 
//
//	     	Example 1:
//
//	     	Input: nums1 = [1,3], nums2 = [2]
//	     	Output: 2.00000
//	     	Explanation: merged array = [1,2,3] and median is 2.
//	     	Example 2:
//
//	     	Input: nums1 = [1,2], nums2 = [3,4]
//	     	Output: 2.50000
//	     	Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//	     	Example 3:
//
//	     	Input: nums1 = [0,0], nums2 = [0,0]
//	     	Output: 0.00000
//	     	Example 4:
//
//	     	Input: nums1 = [], nums2 = [1]
//	     	Output: 1.00000
//	     	Example 5:
//
//	     	Input: nums1 = [2], nums2 = []
//	     	Output: 2.00000
	     	//We are gonna try to solve it by doing binary search on smaller array.(Ref Tushar Roy video) https://www.youtube.com/watch?v=LPFhl65R7ww
	     	
	     	public int findMedianSortedArray(int [] arr1,int[] arr2) {
	     		if(arr1.length>arr2.length)
	     			return findMedianSortedArray(arr2, arr1);
	     		int x=arr1.length;
	     		int y=arr2.length;
	     		
	     		int start = 0;
	     		int end = x;
	     		while(end>=start) {
	     			int partionX = (start+end)/2;
	     			int partionY = (x+y+1)/2-partionX;
	     			
	     			int maxLeftX = (partionX == 0)?Integer.MIN_VALUE : arr1[partionX-1];
	     			int minRightX = (partionX == x)?Integer.MAX_VALUE : arr1[partionX];
	     			
	     			int maxLeftY = (partionY == 0)?Integer.MIN_VALUE : arr1[partionY-1];
	     			int minRightY = (partionY == x)?Integer.MAX_VALUE : arr1[partionY];
	     			
	     			if(maxLeftX <= minRightY && maxLeftY <= minRightX) {
	     				if(x+y % 2 == 0)
	     					return (Math.max(maxLeftX, maxLeftY)+Math.min(minRightX, minRightY))/2;
	     				else
	     					return Math.max(maxLeftY, maxLeftX);
	     			}else if (maxLeftX > minRightY)
	     				end = partionX-1;
	     			else {
	     				start = partionX+1;
	     			}
	     		}
	     		return 0;
	     		
	     	}
	     	
//	     	Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
//	     	Example 1:
//
//	     	Input: "babad"
//	     	Output: "bab"
//	     	Note: "aba" is also a valid answer.
//	     	Example 2:
//
//	     	Input: "cbbd"
//	     	Output: "bb"
	     	
	     	//geeksforgeeks dp,this return the length of longest palindrom
	     	
	     	static int longestPalindromeLength(String seq) {
	     		int n=seq.length();
	     		int i,j,cl;
	     		int L[][] = new int [n][n];
	     		for (i=0;i<n;i++) 
	     			L[i][i] = 1;
	     			
	     			for (cl=2;cl<=n;cl++) {//length of the substringn
	     				for(i=0;i<n-cl+1;i++) {
	     					j=i+cl-1;
	     					if(seq.charAt(i) == seq.charAt(j) && cl==2)
	     						L[i][j]=2;
	     					if(seq.charAt(i) ==  seq.charAt(j))
	     						L[i][j] = L[i+1][j-1]+2;
	     					else
	     						L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
	     				}
	     			}
	     			
	     		return L[0][n-1];
	     	}
	     	
	     	static int longestPalindromeLengthDP(String seq) {
	     		int n=seq.length();
	     		boolean table [][] =new boolean[n][n];
	     		for(int i=0;i<n;i++) {////checking palindrome for substring length 1
	     			table[i][i]=true;
	     		}
	     		int start=0,maxLength=1;
	     		
	     		for(int i=0;i<n-1;i++) {//checking palindrome for substring length 2
	     			if(seq.charAt(i)==seq.charAt(i+1)) {
	     				table[i][i+1]=true;
	     				maxLength=2;
	     				start=i;
	     			}
	     		}
	     		
	     		for(int k=3;k<=n;k++) {//this is length of substring
	     			for(int i=0;i<n-k+1;i++) {//checking palindrome for substring length more than 2
	     				int j=i+k-1;
	     				if(table[i+1][j-1]==true && seq.charAt(i)==seq.charAt(j)) {
	     					table[i][j]=true;
	     					if(k>maxLength) {
	     						maxLength=k;
	     						start=i;
	     					}
	     				}
	     			}
	     		}
	     		
	     		System.out.println(seq.substring(start, start+maxLength+2));
	     		return maxLength;
	     	}
	     
	     	
	     	static String longestPalSubstr(String str) 
	        { 
	            // get length of input string 
	            int n = str.length(); 
	      
	            // table[i][j] will be false if 
	            // substring str[i..j] is not palindrome. 
	            // Else table[i][j] will be true 
	            boolean table[][] = new boolean[n][n]; 
	      
	            // All substrings of length 1 are palindromes 
	            int maxLength = 1; 
	            for (int i = 0; i < n; ++i) 
	                table[i][i] = true; 
	      
	            // check for sub-string of length 2. 
	            int start = 0; 
	            for (int i = 0; i < n - 1; ++i) { 
	                if (str.charAt(i) == str.charAt(i + 1)) { 
	                    table[i][i + 1] = true; 
	                    start = i; 
	                    maxLength = 2; 
	                } 
	            } 
	      
	            // Check for lengths greater than 2. 
	            // k is length of substring 
	            for (int k = 3; k <= n; ++k) { 
	      
	                // Fix the starting index 
	                for (int i = 0; i < n - k + 1; ++i) { 
	                    // Get the ending index of substring from 
	                    // starting index i and length k 
	                    int j = i + k - 1; 
	      
	                    // checking for sub-string from ith index to 
	                    // jth index iff str.charAt(i+1) to 
	                    // str.charAt(j-1) is a palindrome 
	                    if (table[i + 1][j - 1] 
	                        && str.charAt(i) == str.charAt(j)) { 
	                        table[i][j] = true; 
	      
	                        if (k > maxLength) { 
	                            start = i; 
	                            maxLength = k; 
	                        } 
	                    } 
	                } 
	            } 
	            System.out.print("Longest palindrome substring is; "+str.substring(start,start+maxLength)); 
	           
	      
	            // return length of LPS 
	            return str.substring(start,start+maxLength); 
	        } 
	     	
	     	
//	     	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
//	     	(you may want to display this pattern in a fixed font for better legibility)
//
//	     	P   A   H   N
//	     	A P L S I I G
//	     	Y   I   R
//	     	And then read line by line: "PAHNAPLSIIGYIR"
//
//	     	Write the code that will take a string and make this conversion given a number of rows:
//
//	     	string convert(string s, int numRows);
//	     	Example 1:
//
//	     	Input: s = "PAYPALISHIRING", numRows = 3
//	     	Output: "PAHNAPLSIIGYIR"
//	     	Example 2:
//
//	     	Input: s = "PAYPALISHIRING", numRows = 4
//	     	Output: "PINALSIGYAHRPI"
//	     	Explanation:
//
//	     	P     I    N
//	     	A   L S  I G
//	     	Y A   H R
//	     	P     I
//	      youtube.com/watch?v=7UQ71uwQFx4
	     	
	     	
	     	public String convert(String s, int numRows) {
	            int length = s.length();
	            if(numRows > length || numRows<=1) {
	            	return s;
	            }
	            char[] zigZagChars = new char[length];
	            int count = 0;//how many characters we have already put in char array
	            int interval =  2*numRows -2;
	            for(int i=0;i<numRows;i++)//for each row 
	            	{
	            	int steps = interval -2*i;//calculate steps for each row with respect to interval
	            	for(int j = i;j<length;j+=interval) {//for each vertical column
	            			zigZagChars[count] = s.charAt(j);
	            			count++;
	            			if(steps > 0 && steps<interval && j+steps < length) {
	            				zigZagChars[count] = s.charAt(j+steps);
	            				count++;
	            			}
	            	}
	            }
	            
	            return new String(zigZagChars);
	        }
	     	
	     	
//	     	Given a 32-bit signed integer, reverse digits of an integer.
//
//	     	Example 1:
//
//	     	Input: 123
//	     	Output: 321
//	     	Example 2:
//
//	     	Input: -123
//	     	Output: -321
//	     	Example 3:
//
//	     	Input: 120
//	     	Output: 21
//	     	Note:
//	     	Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
	     	public int reverse(int x) {
	            int rev_num = 0,prev_rev_num=0;
	            boolean isNeg = false;
	            if(x<0) {
	            	isNeg = true;
	            	x=-x;
	            }
	            
	            while(x>0) {
	            	int curr_digit = x%10;
	            	rev_num = rev_num*10+curr_digit;
	            	if((rev_num - curr_digit)/10 != prev_rev_num) 
	                { 
	                    System.out.println("WARNING OVERFLOWED!!!"); 
	                    return 0; 
	                } 
	            	
	            	prev_rev_num = rev_num;
	            	x=x/10;
	            }
	            
	            return (isNeg)?-rev_num:rev_num;
	        }
	     	
	     	
//	     	Implement atoi which converts a string to an integer.
//
//	     	The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
//	     	Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
//	     	and interprets them as a numerical value.
//	     	The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
//
//	     	If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str 
//	     	is empty or it contains only whitespace characters, no conversion is performed.
//
//	     	If no valid conversion could be performed, a zero value is returned.
//
//	     	Note:
//
//	     	Only the space character ' ' is considered as whitespace character.
//	     	Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
//	     	Example 1:
//
//	     	Input: "42"
//	     	Output: 42
//	     	Example 2:
//
//	     	Input: "   -42"
//	     	Output: -42
//	     	Explanation: The first non-whitespace character is '-', which is the minus sign.
//	     	             Then take as many numerical digits as possible, which gets 42.
//	     	Example 3:
//
//	     	Input: "4193 with words"
//	     	Output: 4193
//	     	Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
//	     	Example 4:
//
//	     	Input: "words and 987"
//	     	Output: 0
//	     	Explanation: The first non-whitespace character is 'w', which is not a numerical 
//	     	             digit or a +/- sign. Therefore no valid conversion could be performed.
//	     	Example 5:
//
//	     	Input: "-91283472332"
//	     	Output: -2147483648
//	     	Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
//	     	             Thefore INT_MIN (−231) is returned.
	     		
	     	public int myAtoi(String str) {
	            str=str.trim();
	            if(str == null || str.length()==0)
	            	return 0;
	            
	            double result=0;
	            boolean isNeg = false;
	            int startIndex =0 ;
	            
	            if(str.charAt(0)=='+'||str.charAt(0)=='-')
	            	++startIndex;
	            
	            if(str.charAt(0)=='-')
	            	isNeg=true;
	            
	            for (int i =startIndex;i<str.length();i++) {
	            	if(str.charAt(i)<'0' || str.charAt(i)>'9')
	            		break;
	            	int digitV = (int)(str.charAt(i)-'0');
	            	result = result* 10+digitV;
	            }
	            
	            if(isNeg)
	            	result = -result;
	            
	            if(result < Integer.MIN_VALUE)
	            	return Integer.MIN_VALUE;
	            
	            if(result > Integer.MAX_VALUE)
	            	return Integer.MAX_VALUE;
	            
	            return (int)result;
	        }
	     	
	     	public static boolean isPalindrome(int x) {
	     		int rev_num=0;
	     		int org_num=x;
	            while(x>0) {
	            	rev_num = rev_num*10 + x%10;
	            	x=x/10;
	            }
	            
	            return (org_num==rev_num);
	        }
	     	
//	     	Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
//
//	     	'.' Matches any single character.
//	     	'*' Matches zero or more of the preceding element.
//	     	The matching should cover the entire input string (not partial).
//
//	     	Note:
//
//	     	s could be empty and contains only lowercase letters a-z.
//	     	p could be empty and contains only lowercase letters a-z, and characters like . or *.
//	     	Example 1:
//
//	     	Input:
//	     	s = "aa"
//	     	p = "a"
//	     	Output: false
//	     	Explanation: "a" does not match the entire string "aa".
//	     	Example 2:
//
//	     	Input:
//	     	s = "aa"
//	     	p = "a*"
//	     	Output: true
//	     	Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
//	     	Example 3:
//
//	     	Input:
//	     	s = "ab"
//	     	p = ".*"
//	     	Output: true
//	     	Explanation: ".*" means "zero or more (*) of any character (.)".
//	     	Example 4:
//
//	     	Input:
//	     	s = "aab"
//	     	p = "c*a*b"
//	     	Output: true
//	     	Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
//	     	Example 5:
//
//	     	Input:
//	     	s = "mississippi"
//	     	p = "mis*is*p*."
//	     	Output: false tushar roy dp programming https://www.youtube.com/watch?v=l3hda49XcDE
	     	
	     	
	     	public boolean isMatch(String s, String p) {
	           char[] text = s.toCharArray();
	           char[] pattern = p.toCharArray();
	           
	           boolean T[][] = new boolean[text.length+1][pattern.length+1];
	           T[0][0] = true;
	           
	           
	           //deals with the 0th row where we set values true if pattern is like a* or a*b* or a*b*c*
	           for (int i =1;i<T[0].length;i++) {
	        	   if(pattern[i-1] == '*')
	        		   T[0][i]=T[0][i-2];
	           }
	           
	           
	           for (int i=1;i<T.length;i++) {
	        	   for (int j=1;j<T[0].length;j++) {
	        		   if(pattern[j-1]=='.'|| pattern[j-1]==text[i-1])
	        			   T[i][j]=T[i-1][j-1] ;
	        		   else if (pattern[j-1]=='*') {
	        			   T[i][j]=T[i][j-2];
	        			   if(pattern[j-2] == '.' || pattern[j-2]==text[i-1])
	        				   T[i][j] = T[i][j] | T[i-1][j];
	        		   }else
	        			   T[i][j]=false;
	        	   }
	           }
	           
	           return T[text.length][pattern.length];
	        }
	     	
	     	/**
	     	 * Date 02/11/2016
	     	 * @author Tushar Roy
	     	 *
	     	 * Wild car matching with ? and *
	     	 *
	     	 * Reference
	     	 * https://leetcode.com/problems/wildcard-matching/
	     	 * 
	     	 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
	     	 */
	     	public class WildCardMatching {
	     	    public boolean isMatch(String s, String p) {
	     	        char[] str = s.toCharArray();
	     	        char[] pattern = p.toCharArray();

	     	        //replace multiple * with one *
	     	        //e.g a**b***c --> a*b*c
	     	        int writeIndex = 0;
	     	        boolean isFirst = true;
	     	        for ( int i = 0 ; i < pattern.length; i++) {
	     	            if (pattern[i] == '*') {
	     	                if (isFirst) {
	     	                    pattern[writeIndex++] = pattern[i];
	     	                    isFirst = false;
	     	                }
	     	            } else {
	     	                pattern[writeIndex++] = pattern[i];
	     	                isFirst = true;
	     	            }
	     	        }

	     	        boolean T[][] = new boolean[str.length + 1][writeIndex + 1];

	     	        if (writeIndex > 0 && pattern[0] == '*') {
	     	            T[0][1] = true;
	     	        }

	     	        T[0][0] = true;

	     	        for (int i = 1; i < T.length; i++) {
	     	            for (int j = 1; j < T[0].length; j++) {
	     	                if (pattern[j-1] == '?' || str[i-1] == pattern[j-1]) {
	     	                    T[i][j] = T[i-1][j-1];
	     	                } else if (pattern[j-1] == '*'){
	     	                    T[i][j] = T[i-1][j] || T[i][j-1];
	     	                }
	     	            }
	     	        }

	     	        return T[str.length][writeIndex];
	     	    }
	     	}

	     	
//	     	Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). 
//	     	    n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
//	     	    Find two lines, which together with x-axis forms a container, such that the container contains the most water.
//
//	     	Note: You may not slant the container and n is at least 2.
//
//	     	 
//
//
//
//	     	The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//
//	     	 
//
//	     	Example:
//
//	     	Input: [1,8,6,2,5,4,8,3,7]
//	     	Output: 49
	     	
	     	public static int maxArea(int[] height) {
	            int maxWidth = height.length-1;
//	            
	            int maxArea = (height[0]>height[maxWidth])?height[maxWidth]*maxWidth:height[0]*maxWidth;
	            
	            for (int i =1;i<maxWidth;i++) {
	            	if(height[0]<height[maxWidth] && height[i]>height[0]) {
	            		if(height[i]>height[maxWidth]) {
	            			int area = height[maxWidth]*(maxWidth-i);
	            			if(area>maxArea)
	            				maxArea=area;
	            		}
	            		if(height[i]<height[maxWidth]) {
	            			int area = height[i]*(maxWidth-i);
	            			if(area>maxArea)
	            				maxArea=area;
	            		}
	            	}
	            	
	            	else if (height[0]>height[maxWidth] && height[i]>height[maxWidth]) {
	            		if(height[i]>height[0]) {
	            			int area = height[0]*i;
	            			if(area>maxArea)
	            				maxArea=area;
	            		}
	            		if(height[i]<height[0]) {
	            			int area = height[i]*i;
	            			if(area>maxArea)
	            				maxArea=area;
	            		}
	            	}
	            }
	            
	            return maxArea;
	        }
	     	
	     	
//	     	Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
//	     	Symbol       Value
//	     	I             1
//	     	V             5
//	     	X             10
//	     	L             50
//	     	C             100
//	     	D             500
//	     	M             1000
//	     	For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
//
//	     	Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
//	     	I can be placed before V (5) and X (10) to make 4 and 9. 
//	     	X can be placed before L (50) and C (100) to make 40 and 90. 
//	     	C can be placed before D (500) and M (1000) to make 400 and 900.
//	     	Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
//
//	     	Example 1:
//
//	     	Input: 3
//	     	Output: "III"
//	     	Example 2:
//
//	     	Input: 4
//	     	Output: "IV"
//	     	Example 3:
//
//	     	Input: 9
//	     	Output: "IX"
//	     	Example 4:
//
//	     	Input: 58
//	     	Output: "LVIII"
//	     	Explanation: L = 50, V = 5, III = 3.
//	     	Example 5:
//
//	     	Input: 1994
//	     	Output: "MCMXCIV"
//	     	Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
	     	
	     	public static String intToRoman(int num) {
	     		String [] thousands = {"","M","MM","MMM"};
	     		String [] hundreds = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
	     		String [] tens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
	     		String [] units = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
	     		return thousands[num/1000]+hundreds[(num%1000)/100]+tens[(num%100)/10]+units[num%10];
	        }
	     	
	     	
//	     	Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
//	     	Symbol       Value
//	     	I             1
//	     	V             5
//	     	X             10
//	     	L             50
//	     	C             100
//	     	D             500
//	     	M             1000
//	     	For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
//
//	     	Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
//	     	I can be placed before V (5) and X (10) to make 4 and 9. 
//	     	X can be placed before L (50) and C (100) to make 40 and 90. 
//	     	C can be placed before D (500) and M (1000) to make 400 and 900.
//	     	Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
	     	
	     	public static int romanToInt(String s) {
	            HashMap<Character,Integer> hm = new HashMap<Character, Integer>();
	            hm.put('I', 1);
	            hm.put('V', 5);
	            hm.put('X', 10);
	            hm.put('L', 50);
	            hm.put('C', 100);
	            hm.put('D', 500);
	            hm.put('M', 1000);
	            
	            int result = 0;
	            
	            for(int i =0;i<s.length();i++) {
	            	if(i > 0 && hm.get(s.charAt(i))>hm.get(s.charAt(i-1))) {
	            		result = result+ hm.get(s.charAt(i))-2*hm.get(s.charAt(i-1));
	            	}
	            	else {
	            		result = result+hm.get(s.charAt(i));
	            	}
	            }
	            
	            return result;
	        }
	     	
	     	
//	     	Write a function to find the longest common prefix string amongst an array of strings.
//
//	     	If there is no common prefix, return an empty string "".
//
//	     	Example 1:
//
//	     	Input: ["flower","flow","flight"]
//	     	Output: "fl"
//	     	Example 2:
//
//	     	Input: ["dog","racecar","car"]
//	     	Output: ""
//	     	Explanation: There is no common prefix among the input strings.
//	     	Note:
//
//	     	All given inputs are in lowercase letters a-z.
	     	
	     	static String commonPrefixUtil(String str1, String str2) { 
	            String result = ""; 
	            int n1 = str1.length(), n2 = str2.length(); 
	      
	            // Compare str1 and str2  
	            for (int i = 0, j = 0; i <= n1 - 1 && j <= n2 - 1; i++, j++) { 
	                if (str1.charAt(i) != str2.charAt(j)) { 
	                    break; 
	                } 
	                result += str1.charAt(i); 
	            } 
	      
	            return (result); 
	        } 
	      
	    // A Function that returns the longest common prefix  
	    // from the array of strings  
	        static String commonPrefix(String arr[]) { 
	        	if (arr.length==0)
	        		return "";
	        	int n = arr.length;
	            String prefix = arr[0]; 
	      
	            for (int i = 1; i <= n - 1; i++) { 
	                prefix = commonPrefixUtil(prefix, arr[i]); 
	            } 
	      
	            return (prefix); 
	        } 
	        
	        
//	        Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
//	        Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
//	        
//
//	        Example 1:
//
//	        Input: nums = [-1,2,1,-4], target = 1
//	        Output: 2
//	        Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	        
	        
	        public static int threeSumClosest(int[] nums, int target) {
	        	int diff =Integer.MAX_VALUE;
	        	int res=0;
	            for(int i =0;i<nums.length;i++) {
	            	for (int j=i+1;j<nums.length;j++) {
	            		for(int k=j+1;k<nums.length;k++) {
	            			int resN=nums[i]+nums[j]+nums[k];
	            			int diffN=(target>resN)?(target-resN):(resN-target);
	            			if(diff>diffN) {
	            				diff=diffN;
	            				res=resN;
	            			}	
	            		}
	            	}
	            }
	            
	            return res;
	        }
	        
//	        Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
//
//	        		A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
//	        		Example:
//
//	        		Input: "23"
//	        		Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//	        		Note:
//
//	        		Although the above answer is in lexicographical order, your answer could be in any order you want.
	        
	        
	        
	        public static List<String> letterCombinations(String digits) {
	            LinkedList<String> q = new LinkedList<String>();
	            
	            if(digits.length()==0)
	            	return q;
	            q.add("");
	            String[] char_map = new String[] {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	            for(int i=0;i<digits.length();i++) {
	            	int index = Character.getNumericValue(digits.charAt(i));
	            	while(q.peek().length()==i) {
	            		String permutation = q.poll();
	            		for(char c:char_map[index].toCharArray()) {
	            			q.add(permutation+c);
	            		}
	            	}
	            }
	            
	            return q;
	            
	        }
	        
//	        Given a linked list, remove the n-th node from the end of list and return its head.
//
//	        		Example:
//
//	        		Given linked list: 1->2->3->4->5, and n = 2.
//
//	        		After removing the second node from the end, the linked list becomes 1->2->3->5.
//	        		Note:
//
//	        		Given n will always be valid
	        
	        public Node removeNthFromEnd(Node head, int n) {
	            Node first = head;
	            Node second = head;
	            
	            //at start second pointer needs to point to the nth node from begining
	            
	            for(int i=0;i<n;i++) {
	            	if(second.next==null) {
	            		if(i==n-1)//we have found the point so delete the node
	            			head = head.next;
	            	}
	            	second = second.next;	
	            }
	            
	            while(second.next != null) {
	            	first=first.next;
	            	second = second.next;
	            }
	            
	            //now we have reached the point,delete the node
	            first.next=first.next.next;
	            return head;
	            
	        }
	        
//	        Given a linked list, rotate the list to the right by k places, where k is non-negative.
//
//	        Example 1:
//
//	        Input: 1->2->3->4->5->NULL, k = 2
//	        Output: 4->5->1->2->3->NULL
//	        Explanation:
//	        rotate 1 steps to the right: 5->1->2->3->4->NULL
//	        rotate 2 steps to the right: 4->5->1->2->3->NULL
	        public Node rotateRight(Node head, int k) {
	        	Node first = head;
	        	Node second = head; 
	        	
	        	for(int i=0;i<k;i++) {
	        		second=second.next;
	        	}
	        	while(second.next!=null) {
	        		first=first.next;
	        		second=second.next;
	        	}
	        	
	        	second.next=head;
	        	
	        	Node temp=first.next;
	        	first.next=null;
	        	
	        	return temp; 
	        }
	        
	        
//	        Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//	        An input string is valid if:
//
//	        Open brackets must be closed by the same type of brackets.
//	        Open brackets must be closed in the correct order.
//	        Note that an empty string is also considered valid.
	        
	        public static boolean isValid(String s) {
	            if(s.isEmpty())
	                return true;
	            
	            ArrayDeque<Character> stack = new ArrayDeque<Character>();
	            for(int i =0;i<s.length();i++) {
	            	char expr = s.charAt(i);
	            	if(expr=='('||expr=='{'||expr=='[') {
	            		stack.push(expr);
	            		continue;
	            	}
	            	if(stack.isEmpty())
	            		return false;
	            	switch (expr) {
					case ')':
						char c=stack.pop();  
		                if (c == '{' || c == '[') 
		                    return false; 
		                break;

					case '}':
						char ct=stack.pop(); 
		                if (ct == '(' || ct == '[') 
		                    return false; 
		                break;
					case ']':
						char cf=stack.pop(); 
		                if (cf == '{' || cf == '(') 
		                    return false; 
		                break;
					}
	            	}
	            
	            return stack.isEmpty();
	            }
	        
	        
//	        Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
//
//	        		Example:
//
//	        		Input: 1->2->4, 1->3->4
//	        		Output: 1->1->2->3->4->4
	        
	        
	        public Node mergeTwoLists(Node l1, Node l2) {
	            Node dummy = new Node();
	            Node tail = dummy;
	            
	            while(true) {
	            	if(l1==null) {
	            		tail.next=l2;
	            		break;
	            	}
	            	if(l2==null) {
	            		tail.next=l1;
	            		break;
	            	}
	            	if(l1.val<=l2.val) {
	            		tail.next=l1;
	            		l1=l1.next;
	            	}
	            	else {
	            		tail.next=l2;
	            		l2=l2.next;
	            	}
	            	
	            	tail=tail.next;
	            }
	            return dummy.next;
	        	}
	        
	        
	        
//	        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//	        For example, given n = 3, a solution set is:
//
//	        [
//	          "((()))",
//	          "(()())",
//	          "(())()",
//	          "()(())",
//	          "()()()"
//	        ]
	        
	        public List<String> generateParenthesis(int n) {
	            
	          List<String> arr = new ArrayList<String>();
	          backtrack(arr,"",0,0,n);
	          return arr;
	        }
	        
	        public void backtrack(List<String> arr,String current,int open,int close,int max) {
	        	if(current.length()==max*2) {
	        		arr.add(current);
	        		return;
	        	}
	        	if(open<max) {
	        		backtrack(arr, current+"(", open+1, close, max);
	        	}
	        	
	        	if(close<open)
	        		backtrack(arr, current+")", open, close+1, max);
	        	
	        }
	        
	        
//	        Given an array of linked-lists lists, each linked list is sorted in ascending order.
//
//	        Merge all the linked-lists into one sort linked-list and return it.
//
//	         
//
//	        Example 1:
//
//	        Input: lists = [[1,4,5],[1,3,4],[2,6]]
//	        Output: [1,1,2,3,4,4,5,6]
//	        Explanation: The linked-lists are:
//	        [
//	          1->4->5,
//	          1->3->4,
//	          2->6
//	        ]
//	        merging them into one sorted list:
//	        1->1->2->3->4->4->5->6
//	        Example 2:
//
//	        Input: lists = []
//	        Output: []
//	        Example 3:
//
//	        Input: lists = [[]]
//	        Output: []
	        
	        
	        public Node mergeKLists(Node list[]) {
	        	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	        	for(Node head:list) {
	        		while(head!=null) {
	        		minHeap.add(head.val);
	        		head=head.next;
	        		}
	        	}
	        	
	        	Node dummy = new Node();
	        	Node head = dummy;
	        	
	        	while(!minHeap.isEmpty()) {
	        		head.next=new Node();
	        		head.next.val=minHeap.remove();
	        		head=head.next;
	        	}
	        	
	        	return dummy.next;
	        }
	        
	        
//	        Given a linked list, swap every two adjacent nodes and return its head.
//
//	        		You may not modify the values in the list's nodes, only nodes itself may be changed.
//
//	        		 
//
//	        		Example:
//
//	        		Given 1->2->3->4, you should return the list as 2->1->4->3.
	        
	        
	        public Node swapPairs(Node head) {
	            Node temp=head;
	            while(temp !=null && temp.next!=null) {
	            	int k=temp.val;
	            	temp.val=temp.next.val;
	            	temp.next.val=k;
	            	temp=temp.next.next;
	            }
	            
	            return head;
	        }
	        
	        
//	        Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//
//	        		k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
//
//	        		Example:
//
//	        		Given this linked list: 1->2->3->4->5
//
//	        		For k = 2, you should return: 2->1->4->3->5
//
//	        		For k = 3, you should return: 3->2->1->4->5
//
//	        		Note:
//
//	        		Only constant extra memory is allowed.
//	        		You may not alter the values in the list's nodes, only nodes itself may be changed.
	        
	        public Node reverseKGroup(Node head, int k) {
	            Node current=head;
	            Node prev =null;
	            Node next =null;
	            int count =0;
	            
	            while(count<k && current !=null) {
	            	next=current.next;
	            	current.next=prev;
	            	prev=current;
	            	current=next;
	            	count++;
	            }
	            
	            if(next !=null)
	            	head.next=reverseKGroup(next, k);
	            return prev;
	        }
	        
	        
//	        Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
//
//	        		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
//
//	        		Example 1:
//
//	        		Given nums = [1,1,2],
//
//	        		Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
//
//	        		It doesn't matter what you leave beyond the returned length.
//	        		Example 2:
//
//	        		Given nums = [0,0,1,1,1,2,2,3,3,4],
//
//	        		Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
//
//	        		It doesn't matter what values are set beyond the returned length.
	        
	        public static int removeDuplicates(int[] nums) {
	            //int[] temp = new int[nums.length];
	            int n=nums.length;
	            if (n == 0 || n == 1) 
	                return n; 
	            int j=0;
	            for(int i=0;i<n-1;i++) {
	            	if(nums[i]!=nums[i+1])
	            		nums[j++]=nums[i];
	            }
	            nums[j++] = nums[n-1];
	            	
//	            for (int i=0; i<j; i++) 
//	                nums[i] = temp[i];
	            
	            return j;
	        }
	        
	        
//	        Given an array nums and a value val, remove all instances of that value in-place and return the new length.
//
//	        		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
//
//	        		The order of elements can be changed. It doesn't matter what you leave beyond the new length.
//
//	        		Example 1:
//
//	        		Given nums = [3,2,2,3], val = 3,
//
//	        		Your function should return length = 2, with the first two elements of nums being 2.
//
//	        		It doesn't matter what you leave beyond the returned length.
	        
	        
	        public static int removeElement(int[] nums, int val) {
	        	int index = 0; 
	            for (int i=0; i<nums.length; i++) 
	               if (nums[i] != val) 
	            	   nums[index++] = nums[i]; 
	    
	           // Create a copy of arr[]  
	           return Arrays.copyOf(nums, index).length; 
	        }
	        
	        
	        /**
	         * Date 09/22/2014
	         * @author tusroy
	         * 
	         * Do pattern matching using KMP algorithm
	         * 
	         * Runtime complexity - O(m + n) where m is length of text and n is length of pattern
	         * Space complexity - O(n)
	         */
	        
	        /**
	         * Compute temporary array to maintain size of suffix which is same as prefix
	         * Time/space complexity is O(size of pattern)
	         */
	        private int[] computeTempArray(char pattern[]) {
	        	int [] lps = new int [pattern.length];
	        	int j = 0;
	        	for(int i=1;i<pattern.length;) {
	        		if(pattern[i]==pattern[j]) {
	        			lps[i]=j+1;
	        			j++;
	        			i++;
	        		}else {
	        			if(j !=0) {
	        				j=lps[j-1];
	        			}else {
	        				lps[i]=0;
	        				i++;
	        			}
	        		}
	        	}
	        	return lps;
	        }
	        
	        /**
	         * KMP algorithm of pattern matching.
	         */
	        public boolean KMP(char []text, char []pattern){
	        	int lps[] = computeTempArray(pattern);
	        	int i=0;
	        	int j=0;
	        	while(i<text.length && j<pattern.length) {
	        		if(text[i]==pattern[j]) {
	        			i++;
	        			j++;
	        		}else {
	        			if(j!=0) {
	        				j=lps[j-1];
	        			}else {
	        				i++;
	        			}
	        		}
	        	}
	        	
	        	if(j == pattern.length)
	        		return true;
	        	
	        	return false;
	        }
	        
//	        Implement strStr().
//
//	        Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
	        
//	        Example 1:
//
//	        	Input: haystack = "hello", needle = "ll"
//	        	Output: 2
	        
	        public int strStr(String haystack, String needle) {
	            if(needle.isEmpty())
	            	return 0;
	            int m = haystack.length();
	            int n=needle.length();
	            
	            if(m<n)
	            	return -1;
	            for(int i=0;i<=m-n;i++) {
	            	int j;
	            	for(j=0;j<n;j++) {
	            		if(haystack.charAt(i+j) != needle.charAt(j))
	            			break;
	            	}
	            	if(j==n)
	            		return i;
	            }
	            
	            return -1;
	        }
	        
//	        Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
//
//	        Return the quotient after dividing dividend by divisor.
//
//	        The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
//
//	        Example 1:
//
//	        Input: dividend = 10, divisor = 3
//	        Output: 3
//	        Explanation: 10/3 = truncate(3.33333..) = 3.
	        
	        public int divide(int a, int b) {
	        	long dividend = (long)a; 
	            long divisor = (long)b; 
	          
	            // Calculate sign of divisor i.e., 
	            // sign will be negative only if 
	            // either one of them is negative 
	            // otherwise it will be positive 
	          
	            long sign = (dividend < 0) ^  
	                        (divisor < 0) ? -1 : 1; 
	          
	            // Remove signs of  
	            // dividend and divisor 
	            dividend = Math.abs(dividend); 
	            divisor = Math.abs(divisor); 
	          
	            // Zero division Exception. 
	            if (divisor == 0) 
	            { 
	                System.out.println("Cannot Divide by 0"); 
	                return 0; 
	            } 
	          
	            if (dividend == 0)  
	            { 
	                System.out.println(a + " / " + b +  
	                                    " is equal to : " + 0); 
	                return 0; 
	            } 
	          
	            if (divisor == 1)  
	            { 
	                System.out.println(a + " / " + b +  
	                                   " is equal to : " +  
	                                     sign * dividend); 
	                return (int) (sign * dividend) ; 
	            } 
	          
	            // Using Formula 
	            // derived above. 
	            System.out.println(a + " / " + b + " is equal to : " +  
	                                                 Math.floor(sign *  
	                                    (Math.exp(Math.log(dividend) -  
	                                     Math.log(divisor))))); 
	            
	            return (int) Math.floor(sign *  
                        (Math.exp(Math.log(dividend) -  
                                Math.log(divisor))));
	        }
	        
//	        You are given a string s and an array of strings words of the same length. 
//	        Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
//
//	        You can return the answer in any order.
//
//	         
//
//	        Example 1:
//
//	        Input: s = "barfoothefoobarman", words = ["foo","bar"]
//	        Output: [0,9]
//	        Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
//	        The output order does not matter, returning [9,0] is fine too. sol:https://www.geeksforgeeks.org/find-starting-indices-substrings-string-s-made-concatenating-words-listl/
	        
	        public static List<Integer> findSubstring(String s, String[] words) {
	            List<String> wordList = Arrays.asList(words);
	            int size_word=wordList.get(0).length();
	            int word_count=wordList.size();
	            int size_l=size_word * word_count;
	            int n=s.length();
	            List<Integer> res = new ArrayList<Integer>();
	            if(size_l>n)
	            	return res;
	            
	            HashMap<String, Integer> hm = new HashMap<String, Integer>();
	            for(String word : wordList) {
	            	hm.put(word, hm.getOrDefault(word, 0)+1);
	            }
	            
	            for (int i=0;i<n-size_l;i++) {
	            	HashMap<String, Integer> temp_hm=(HashMap<String, Integer>) hm.clone();
	            	int j=i,count=word_count;
	            	while(j<i+size_l) {
	            		String word = s.substring(j, j+size_word);
	            		if(!hm.containsKey(word)||temp_hm.get(word)==0) {
	            			break;
	            		}else {
	            			temp_hm.put(word, temp_hm.get(word)-1);
	            			count--;
	            		}
	            		j=j+size_word;
	            	}
	            	if(count==0)
	            		res.add(i);
	            }
	            return res;
	        }
	        
	        
//	        Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//	        If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//	        The replacement must be in-place, do not allocate extra memory.
//	        Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//	        1,2,3 → 1,3,2
//	        3,2,1 → 1,2,3
//	        1,1,5 → 1,5,1
//
//	        Analysis:
//	        Well, this is more like a math problem, and I don't know how to solve it.
//	        From the wikipedia, one classic algorithm to generate next permutation is:
//	        Step 1: Find the largest index k, such that A[k]<A[k+1]. If not exist, this is the last permutation. (in this problem just sort 
	        //the vector in ascending order and return.)
//	        Step 2: Find the largest index l, such that A[l]>A[k].
//	        Step 3: Swap A[k] and A[l].
//	        Step 4: Reverse A[k+1] to the end.
	        
	        public static void nextPermutation(int[] nums) {
	            int n=nums.length;
	            int k=n-2;
	            while(k>=0 && nums[k]>=nums[k+1]) {
	            	k--;
	            }
	            if(k>=0) {
	            	int j=n-1;
	            	while(j>=0 && nums[j]<=nums[k]) {
	            		j--;
	            	}
	            	swap(nums,j,k);
	            }
	            reverse(nums,k+1,n-1);
	        }
	        
	        private static void swap(int[] nums,int lo,int hi) {
	        	int tmp=nums[lo];
	        	nums[lo]=nums[hi];
	        	nums[hi]=tmp;
	        }
	        
	        private static void reverse (int[] nums,int lo,int hi) {
	        	while(lo<hi) {
	        	swap(nums, lo++, hi--);
	        	}
	        }
	        
//	        Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
//	        1) Create an empty stack and push -1 to it. The first element
//	        of the stack is used to provide a base for the next valid string. 
//
//	     2) Initialize result as 0.
//
//	     3) If the character is '(' i.e. str[i] == '('), push index 
//	        'i' to the stack. 
//	        
//	     2) Else (if the character is ')')
//	        a) Pop an item from the stack (Most of the time an opening bracket)
//	        b) If the stack is not empty, then find the length of current valid
//	           substring by taking the difference between the current index and
//	           top of the stack. If current length is more than the result,
//	           then update the result.
//	        c) If the stack is empty, push the current index as a base for the next
//	           valid substring.
//
//	     3) Return result.
	        
	        public int longestValidParentheses(String s) {
	            Stack<Integer> st=new Stack<Integer>();
	            st.push(-1);
	            int n=s.length();
	            int res=0;
	            for(int i=0;i<n;i++) {
	            	if(s.charAt(i)=='(')
	            		st.push(i);
	            	else {
	            		st.pop();
	            		if(!st.isEmpty()) {
	            			res=Math.max(res, i-st.peek());
	            		}else
	            			st.push(i);
	            	}
	            }
	            return res;
	        }
	        
//	        You are given an integer array nums sorted in ascending order, and an integer target.
//
//	        Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
//
//	        If target is found in the array return its index, otherwise, return -1.
//	        		The idea is to find the pivot point, divide the array in two sub-arrays and perform binary search.
//	        		The main idea for finding pivot is – for a sorted (in increasing order) and pivoted array, pivot element is the only element 
	        //for which next element to it is smaller than it.
//	        		Using the above statement and binary search pivot can be found.
//	        		After the pivot is found out divide the array in two sub-arrays.
//	        		Now the individual sub – arrays are sorted so the element can be searched using Binary Search.
	        
	        public int search(int[] nums, int target) {
	        	int n=nums.length;
	        	int pivot=findPivot(nums,0,n-1);
	        	if(pivot==-1)
	        		return 	binarySearch(nums,0,n-1,target);
	        	if(nums[pivot]==target)
	        		return pivot;
	        	if(nums[0]<target)
	        		return binarySearch(nums, 0, pivot-1, target);
	        	return binarySearch(nums, pivot+1, n-1, target);
	        }
	        
	        private static int findPivot(int[] nums,int st,int end) {
	        	if(end<st)
	        		return -1;
	        	if(st==end)
	        		return st;
	        	int mid=(st+end)/2;
	        	if((mid<end)&& nums[mid]>nums[mid+1])
	        		return mid;
	        	if((mid>st)&&(nums[mid]<nums[mid-1]))
	        		return mid-1;
	        	if(nums[st]>=nums[mid])
	        		return findPivot(nums, st, mid-1);
	        	return findPivot(nums, mid+1, end);
	        }
	        
	        private static int binarySearch(int[] nums,int low,int high,int target) {
	        	if(high<low)
	        		return -1;
	        	
	        	int mid=(high+low)/2;
	        	if(nums[mid]==target)
	        		return mid;
	        	if(nums[mid]<target)
	        		return binarySearch(nums, mid+1, high, target);
	        	return binarySearch(nums, low, mid-1, target);
	        }
	        
//	        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//
//	        Your algorithm's runtime complexity must be in the order of O(log n).
//
//	        If the target is not found in the array, return [-1, -1].
//	        		Analysis,binary search from start give 1st occurance,from last give last occurance.
//	        Input : arr[] = {1, 3, 5, 5, 5, 5, 67, 123, 125}    
//	        x = 5
//	Output : First Occurrence = 2
//	         Last Occurrence = 5
	        
	        public  static int[] searchRange(int[] nums, int target) {
	            int[] res = {first(nums, target),last(nums,target)};
	            return res;
	        }
	        
	        private static int first(int []nums,int target) {
	        	int index = -1;
	        	int start=0;
	        	int end=nums.length-1;
	        	while(start <=end) {
		        	int mid=start+(end-start)/2;
	        		if(nums[mid]>=target)
	        			end=mid-1;
	        		else{
	        			start=mid+1;
	        		}
	        		if(nums[mid]==target)index=mid;
	        	}
	        	return index;
	        }
	        
	        private static int last(int []nums,int target) {
	        	int index = -1;
	        	int start=0;
	        	int end=nums.length-1;
	        	while(start <=end) {
		        	int mid=start+(end-start)/2;
	        		if(nums[mid]<=target)
	        			start=mid+1;
	        		else
	        			end=mid-1;
	        		if(nums[mid]==target)index=mid;
	        	}
	        	return index;
	        }
	        
//	        Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//	        		You may assume no duplicates in the array.
//
//	        		Example 1:
//
//	        		Input: [1,3,5,6], 5
//	        		Output: 2
	        
	        public static int searchInsert(int[] nums, int target) {
	            	int start=0,end=nums.length-1;
	            	while(start<=end) {
	            		int mid = start+(end-start)/2;
	            		if(nums[mid]==target) return mid;
	            		if(nums[mid]>target)
	            			end=mid-1;
	            		else
	            			start=mid+1;
	            	}
	            	return start;
	        }
	        
//	        Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
//
//	        	Each row must contain the digits 1-9 without repetition.
//	        	Each column must contain the digits 1-9 without repetition.
//	        	Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
	        //Add method in HashSet returns true if the element is added in it and false if element already present in hashSet.
	        
	        public boolean isValidSudoku(char[][] board) {
	        	HashSet<String> seen = new HashSet<String>();
	         for(int i=0;i<9;i++) {//for row
	        	 for(int j=0;j<9;j++) {//for column
	        		 char current_val= board[i][j];
	        		 if(current_val != '.') {
	        		 if(!seen.add(current_val + "in row"+i)||
	        			!seen.add(current_val + "in column"+j)||
	        			!seen.add(current_val + "in subboc"+i/3+" - "+j/3))
	        			 return false;
	        		 }
	        	 }
	         } 
	        	return true;
	        }
	        
//	    	Write a program to solve a Sudoku puzzle by filling the empty cells.
	        //
//	        	A sudoku solution must satisfy all of the following rules:
	        //
//	        	Each of the digits 1-9 must occur exactly once in each row.
//	        	Each of the digits 1-9 must occur exactly once in each column.
//	        	Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
	        	
	        	public static boolean isSafe(int [][] board,int row,int col,int num) {
	        		for(int d=0;d<9;d++) {
	        			if(board[row][d]==num) //checking for same number in row
	        				return false;
	        		}
	        		
	        		for(int r=0;r<9;r++) {
	        			if(board[r][col]==num) //checking for same number in column
	        				return false;
	        		}
	        		
	        		//checking duplicacy in subbox
	        		int sqrt = (int) Math.sqrt(board.length);
	        		int subBoxStartRow = row-row%sqrt;
	        		int subBoxStartCol = col-col%sqrt;
	        		
	        		for (int r=subBoxStartRow ; r<subBoxStartRow+sqrt;r++) {
	        			for (int d=subBoxStartCol;d<subBoxStartCol+sqrt;d++) {
	        				if(board[r][d]==num)
	        					return false;
	        			}
	        		}
	        		return true;
	        	}
	        	
	        	public static boolean solveSudoku(int board [][]) {
	        		boolean isEmpty = true;
	        		int row = -1; 
	                int col = -1;
	        		for (int i=0;i<9;i++) {
	        			for (int j=0;j<9;j++) {
	        				if(board[i][j]==0) {
	        					row=i;
	        					col=j;
	        					isEmpty = false;
	        					break;
	        				}
	        			}
	        			if(!isEmpty)
	        				break;
	        		}
	        		if(isEmpty)
	        			return true;
	        		for (int num=1;num<=9;num++) {
	        			if(isSafe(board, row, col, num)) {
	        				board[row][col]=num;
	        				if(solveSudoku(board))
	        					return true;
	        				else
	        					board[row][col]=0;
	        			}
	        		}
	        		return false;
	        		
	        	}
//	        	Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
//	        	The same repeated number may be chosen from candidates unlimited number of times.
//
//	        	Note:
//
//	        	All numbers (including target) will be positive integers.
//	        	The solution set must not contain duplicate combinations.
//	        	Example 1:
//
//	        	Input: candidates = [2,3,6,7], target = 7,
//	        	A solution set is:
//	        	[
//	        	  [7],
//	        	  [2,2,3]
//	        	]
	        	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
	                List<List<Integer>> results = new ArrayList<>();
	                
	                if (candidates == null || candidates.length == 0) {
	                    return results;
	                }
	                
	                Arrays.sort(candidates);
	                
	                List<Integer> combination = new ArrayList<>();
	                toFindCombinationsToTarget(results, combination, candidates, target, 0);
	                
	                return results;
	            }
	            
	            private static void  toFindCombinationsToTarget(List<List<Integer>> results, 
	            		List<Integer> combination, int[] candidates, int target, int startIndex) {
	                if (target == 0) {
	                    results.add(new ArrayList<>(combination));
	                    return;
	                }
	                
	                for (int i = startIndex; i < candidates.length; i++) {
	                    if (candidates[i] > target) {
	                        break;
	                    }         
	                    
	                    combination.add(candidates[i]);
	                    toFindCombinationsToTarget(results, combination, candidates, target - candidates[i], i);
	                    combination.remove(combination.size() - 1);
	                }        
	            }
	            
	            
//	            Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
//	            Each number in candidates may only be used once in the combination.
//
//	            Note:
//
//	            All numbers (including target) will be positive integers.
//	            The solution set must not contain duplicate combinations.
//	            Example 1:
//
//	            Input: candidates = [10,1,2,7,6,1,5], target = 8,
//	            A solution set is:
//	            [
//	              [1, 7],
//	              [1, 2, 5],
//	              [2, 6],
//	              [1, 1, 6]
//	            ]
	            
	            public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
	            	List<List<Integer>> results = new ArrayList<>();
	                
	                if (candidates == null || candidates.length == 0) {
	                    return results;
	                }
	                
	                Arrays.sort(candidates);
	                
	                List<Integer> combination = new ArrayList<>();
	                toFindCombinationsToTarget2(results, combination, candidates, target, 0);
	                
	                return results;
	            }
	            
	            private static void toFindCombinationsToTarget2(List<List<Integer>> results, 
	            		List<Integer> combination, int[] candidates, int target, int startIndex) {
	            		if(target==0) {
	            			results.add(new ArrayList<Integer>(combination));
	            			return;
	            		}
	            	for (int i=startIndex;i<candidates.length;i++) {
	            		 if (i != startIndex && candidates[i] == candidates[i - 1]) {
	                         continue;//this is for the situation when duplicate numbers are present in array.
	                     }
	            		if(candidates[i]>target)
	            			break;
	            		combination.add(candidates[i]);
	            		toFindCombinationsToTarget2(results, combination, candidates, target-candidates[i], i+1);
	            		combination.remove(combination.size()-1);
	            	}
	            }
	            
//	            Given an unsorted integer array, find the smallest missing positive integer.
//
//	            Example 1:
//
//	            Input: [1,2,0]
//	            Output: 3
//	            Analysis
//	            In this problem, we have created a list full of 0’s with size of the max() value of our given array. 
//	            Now, whenever we encounter any positive value in our original array, we change the index value of our list to 1. 
//	            So, after we are done, we simply iterate through our modified list, the first 0 we encounter, 
//	            its (index value + 1) should be our answer since index in python starts from 0.
	            
	            public static int firstMissingPositive(int[] nums) {
	            	
	            	boolean [] present = new boolean[nums.length+1];
	            	for(int i=0;i<nums.length;i++) {
	            		// Only mark the required elements 
	                    // All non-positive elements and 
	                    // the elements greater n + 1 will never 
	                    // be the answer 
	                    // For example, the array will be {1, 2, 3} 
	                    // in the worst case and the result 
	                    // will be 4 which is n + 1 
	            		if(nums[i]>0 && nums[i] <=nums.length)
	            			present[nums[i]]=true;
	            	}
	            	
	            	for (int i=1;i<=nums.length;i++) {
	            		if(!present[i])
	            			return i;
	            	}
	            	
	            	return nums.length+1;
	            }
	            
//	            Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
//
//	            The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. 
//	            Example:
//
//	            Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//	            Output: 6
	            
	            public int trap(int[] height) {
	                int n=height.length;
	                int[] left = new int[n];
	                int[] right = new int[n];
	                
	                int water = 0;
	                left[0]=height[0];
	                for(int i=1;i<n;i++) {
	                	left[i]=Math.max(left[i-1], height[i]);
	                }
	                right[n-1]=height[n-1];
	                for(int i=n-2;i>=0;i--) {
	                	right[i]=Math.max(right[i+1], height[i]);
	                }
	                
	                for(int i=0;i<n;i++) {
	                	water=water+Math.min(left[i], right[i])-height[i];
	                }
	                
	                return water;
	            }
	            
//	            Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
//
//	            		Example 1:
//
//	            		Input: num1 = "2", num2 = "3"
//	            		Output: "6"
	            
	            public static String multiply(String num1, String num2) {

	                int len1 = num1.length(); 
	                int len2 = num2.length(); 
	                if (len1 == 0 || len2 == 0) 
	                    return "0"; 
	              
	                // will keep the result number in vector 
	                // in reverse order 
	                int result[] = new int[len1 + len2]; 
	              
	                // Below two indexes are used to  
	                // find positions in result.  
	                int i_n1 = 0;  
	                int i_n2 = 0;  
	                  
	                // Go from right to left in num1 
	                for (int i = len1 - 1; i >= 0; i--) 
	                { 
	                    int carry = 0; 
	                    int n1 = num1.charAt(i) - '0'; 
	              
	                    // To shift position to left after every 
	                    // multipliccharAtion of a digit in num2 
	                    i_n2 = 0;  
	                      
	                    // Go from right to left in num2              
	                    for (int j = len2 - 1; j >= 0; j--) 
	                    { 
	                        // Take current digit of second number 
	                        int n2 = num2.charAt(j) - '0'; 
	              
	                        // Multiply with current digit of first number 
	                        // and add result to previously stored result 
	                        // charAt current position.  
	                        int sum = n1 * n2 + result[i_n1 + i_n2] + carry; 
	              
	                        // Carry for next itercharAtion 
	                        carry = sum / 10; 
	              
	                        // Store result 
	                        result[i_n1 + i_n2] = sum % 10; 
	              
	                        i_n2++; 
	                    } 
	              
	                    // store carry in next cell 
	                    if (carry > 0) 
	                        result[i_n1 + i_n2] += carry; 
	              
	                    // To shift position to left after every 
	                    // multipliccharAtion of a digit in num1. 
	                    i_n1++; 
	                } 
	              
	                // ignore '0's from the right 
	                int i = result.length - 1; 
	                while (i >= 0 && result[i] == 0) 
	                i--; 
	              
	                // If all were '0's - means either both  
	                // or one of num1 or num2 were '0' 
	                if (i == -1) 
	                return "0"; 
	              
	                // genercharAte the result String 
	                String s = ""; 
	                  
	                while (i >= 0) 
	                    s += (result[i--]); 
	              
	                return s; 
	            }
	            
	            //find all possible subsets from a set using bit manipulation
	            
	            public static void possibleSubsets(char A[]) {
	            	int N=A.length;
	            	for(int i=0;i<(1<<N);++i) {//no of subsets 2^N
	            		for(int j=0;j<N;++j) {//no of bits N
	            			 // (1<<j) is a number with jth bit 1 
	                        // so when we 'and' them with the 
	                        // subset number we get which numbers 
	                        // are present in the subset and which 
	                        // are not 
	            			if((i & (1<<j))>0)//chacking if jth bit is set in i:
	            				System.out.println(A[j]);
	            		}
	            	}
	            }
	            
//	            Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
//
//	            		A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
//
//	            		0 <= i, j < nums.length
//	            		i != j
//	            		a <= b
//	            		b - a == k
//	            		 
//
//	            		Example 1:
//
//	            		Input: nums = [3,1,4,1,5], k = 2
//	            		Output: 2
//	            		Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
//	            		Although we have two 1s in the input, we should only return the number of unique pairs.
	            //https://www.geeksforgeeks.org/count-pairs-difference-equal-k/
	            
	            public static int findPairs(int[] nums, int k) {
	                int count=0;
	                Arrays.sort(nums);
	                for(int i=0;i<nums.length-1;i++) {
	                	if(i !=0 && nums[i]==nums[i-1])
	                		continue;
	                	for(int j=i+1;j<nums.length;j++) {
	                		if(j!=nums.length-1 && nums[j]==nums[j+1])
		                		continue;
	                		if(nums[i]-nums[j]==k || nums[i]-nums[j]== (-1)*k)
	                			count++;
	                	}
	                }
	                return count;
	            }
	            
	            //optimized.O(nlogn)
	            	
	            public int BS(int[] nums,int start,int end,int x) {
	            	while(end>=start) {
	            		int mid=start+(end-start)/2;
	            		if(x==mid)
	            			return mid;
	            		if(x>mid)
	            			return BS(nums, mid+1, end, x);
	            		else
	            			return BS(nums, start, mid-1, x);
	            	}
	            	return -1;
	            }
	            
	            public int countDiffPairs(int[] nums,int k) {
	            	Arrays.sort(nums);
	            	int count =0 ;
	            	for(int i=0;i<nums.length-1;i++) {
	            		if(BS(nums, i+1, nums.length-1, nums[i]+k) != -1)
	            			count++;
	            	}
	            	
	            	return count;
	            }
	            
	            //knight's tour problem
	            //https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
	            //https://www.youtube.com/watch?v=pwlxQeHchFQ
	            
	            static boolean solveKT() {
	            	int sol[][]=new int [8][8];
	            	int [] xMove= {1,2,-1,-2,-2,-1,1,2};
	            	int [] yMove= {2,1,2,1,-1,-2,-2,-1};
	            	
	            	for(int i=0;i<8;i++) {
	            		for(int j=0;j<8;j++) {
	            			sol[i][j]=-1;
	            		}
	            	}
	            	
	            	sol[0][0]=0;
	            	if(!solveKTUtil(0,0,1,xMove,yMove,sol))
	            		return false;
	            	return true;
	            }
	            
	           static boolean solveKTUtil(int x,int y,int moveIndex,int[] xMove,int[]yMove,int [][] sol) {
	        	   int xNext,yNext;
	        	   if(moveIndex==64)
	        		   return true;
	        	   
	        	   for(int k=0;k<8;k++) {
	        		   xNext=xMove[k]+x;
	        		   yNext=yMove[k]+y;
	        		   
	        		   if(isSafe(xNext,yNext,sol)) {
	        			   sol[xNext][yNext]=moveIndex;
	        			   if(solveKTUtil(xNext, yNext, moveIndex+1, xMove, yMove, sol))
	        				   return true;
	        			   else
	        				   sol[xNext][yNext]=-1;
	        		   }
	        	   }
	        	   
	        	   return false;
	           }
	           
	           static boolean isSafe(int x,int y,int[][] sol) {
	        	   return (x>=0 && x<8 && y>=0 && y<8 && sol[x][y]==-1);
	           }
	           
	           /* This function solves the Maze problem using  
	           Backtracking. It mainly uses solveMazeUtil()  
	           to solve the problem. It returns false if no  
	           path is possible, otherwise return true and  
	           prints the path in the form of 1s. Please note  
	           that there may be more than one solutions, this  
	           function prints one of the feasible solutions.*/
	           
	           //https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
	           //https://www.youtube.com/watch?v=BNEVpkwYqpk
	           boolean solveMaze(int maze[][]) {
	        	   int N=maze.length;
	        	   
	        	   int[][] sol=new int[N][N];
	        	   if(!solveMazeUtil(maze,0,0,sol))
	        		   return false;
	        	   return true;
	           }
	           
	           boolean solveMazeUtil(int maze[][],int x,int y,int[][] sol) {
	        	   if(x==maze.length-1 && y==maze.length-1 && sol[x][y]==1)
	        		   return true;
	        	   
	        	   if(isSafeMaze(x,y,maze)) {
	        		   sol[x][y]=1;
	        		   if(solveMazeUtil(maze, x+1, y, sol))
	        			   return true;
	        		   if(solveMazeUtil(maze, x, y+1, sol))
	        			   return true;
	        		   sol[x][y]=0;
	        	   }
	        	   
	        	   return false;
	           }
	           
	           boolean isSafeMaze(int x,int y,int maze[][]) {
	        	   return (x>=0 && x<maze.length && y>=0 && y<maze.length && maze[x][y]==1);
	           }
	           
	           
//	           Given a list of intervals, remove all intervals that are covered by another interval in the list.
//
//	           Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
//
//	           After doing so, return the number of remaining intervals.//https://www.youtube.com/watch?v=vF8C-MaT2bI
	           
	           public int removeCoveredIntervals(int[][] intervals) {
	        	  Arrays.sort(intervals,(a,b)->a[0]-b[0]);//sorting the array on the basis of 1st int of every element
	        	  int[] curr = {-1,-1};
	        	  int result=0;
	        	  for(int[] in:intervals) {
	        		  if(curr[0]<in[0] && curr[1]<in[1]) {
	        			  curr[0]=in[0];
	        			  result++;
	        		  }
	        		  curr[1]=Math.max(curr[1], in[1]);
	        	  }
	        	  return result;
	           }
//	           Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary, 11 as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.
//
//	        		   The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.  For example, the complement of "101" in binary is "010" in binary.
//
//	        		   For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.
//	           
//	           https://medium.com/@poitevinpm/solution-to-leetcode-problem-1012-complement-of-base-10-integer-cf9421e3091b
	           
	           
	           public int bitwiseComplement(int N) {
	               int p=1;
	               while(p<N) {
	            	   p=p*2+1;//getting the nearest int of N where in binary form all bits are 1
	               }
	               return N^p;//xor operation
	           }
	           
           public static int binarysearch(int[] nums, int target) {
	           int start =0,end=nums.length-1,mid;
	           while(start<=end) {
	        	   mid=start+(end-start)/2;
	        	   if(nums[mid]==target)
	        		   return mid;
	        	   if(target<nums[mid])
	        		   end=mid-1;
	        	   else
	        		   start=mid+1;
	           }
	           
	           return -1;
	       }
           
//           Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
//
//           Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
//
//            
//
//           Example 1:
//
//           Input: [1,17,8]
//           Output: 2
//           Explanation: 
//           [1,8,17] and [17,8,1] are the valid permutations.
//           Example 2:
//
//           Input: [2,2,2]
//           Output: 1
           //https://www.programcreek.com/2014/02/leetcode-number-of-squareful-arrays-java/
           int squarefulCount=0;
           public int numSquarefulPerms(int[] A) {
               Arrays.sort(A);
               squarefulHelper(A,0);
               return squarefulCount;
           }
           
           public void squarefulHelper(int[] A,int start) {
        	   if(start>1 && !(isSquareful(A[start], A[start-1]) || isSquareful(A[start-1], A[start-2])))
        		   return;
        	   
        	   if(start==A.length-1 &&  !isSquareful(A[start], A[start-1]))
        		   return;
        	   
        	   if(start==A.length-1) {
        		   squarefulCount++;
        		   return;
        	   }
        	   
        	   HashSet<Integer> set =new HashSet<Integer>();
        	   for(int i=start;i<A.length;i++) {
        		   if(set.contains(A[i])) continue;
        		   set.add(A[i]);
        		   squareSwap(A, i, start);
        		   squarefulHelper(A, start+1);
        		   squareSwap(A, i, start);
        	   }
           }
           
           public void squareSwap(int[] A,int i,int j) {
        	   int temp=A[i];
        	   A[i]=A[j];
        	   A[j]=temp;
           }
           
           private boolean isSquareful(int a,int b) {
        	   double root = Math.sqrt(a+b);
               return (root-Math.floor(root))==0;
           }
	}


import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;

public class Greedy {
//	Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
//
//	Each element in the array represents your maximum jump length at that position.
//
//	Your goal is to reach the last index in the minimum number of jumps.
//
//	You can assume that you can always reach the last index.
//
//	 
//
//	Example 1:
//
//	Input: nums = [2,3,1,1,4]
//	Output: 2
//	Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
	
	//https://www.youtube.com/watch?v=PEgwWGoKUaY
	
	public int jump(int[] nums) {
        int des=0;
        int pos=0;
        int jump=0;
        for(int i=0;i<nums.length-1;i++) {
        	des=Math.max(des, i+nums[i]);
        	if(pos==i) {
        		pos=des;
        		jump++;
        	}
        }
        return jump;
    }
	
//	Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
//	Each element in the array represents your maximum jump length at that position.
//
//	Determine if you are able to reach the last index.
//
//	 
//
//	Example 1:
//
//	Input: nums = [2,3,1,1,4]
//	Output: true
//	Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//	https://www.youtube.com/watch?v=muDPTDrpS28
	
	public boolean canJump(int[] nums) {
	      int n=nums.length;
	      int reachable=0;
	      for(int i=0;i<n;++i) {
	    	  if(reachable<i)
	    		  return false;
	    	  reachable=Math.max(reachable, i+nums[i]);
	    	  }
	      
	     return true;
	      }
	
//	Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
//
//	Notice that you can not jump outside of the array at any time.
//
//	 
//
//	Example 1:
//
//	Input: arr = [4,2,3,0,3,1,2], start = 5
//	Output: true
//	Explanation: 
//	All possible ways to reach at index 3 with value 0 are: 
//	index 5 -> index 4 -> index 1 -> index 3 
//	index 5 -> index 6 -> index 4 -> index 1 -> index 3
//	https://www.youtube.com/watch?v=J-Io7YTiELg
	
	 public boolean canReach(int[] arr, int start) {
	        Stack<Integer> s=new Stack<Integer>();
	        s.add(start);
	        int[]seen=new int[arr.length];
	        while(!s.isEmpty()) {
	        	int root=s.pop();
	        	int left=root-arr[root];
	        	if(left>=0 && seen[left]==0 ) {
	        		if(arr[left]==0)
		        		return true;
	        		seen[left]=1;
	        		s.add(left);
	        	}
	        	int right=root+arr[root];
	        	if(right<arr.length&&seen[right]==0) {
	        		if(arr[right]==0)
		        		return true;
	        		seen[right]=1;
	        		s.add(right);
	        	}
	        }
	        
	        return false;
	    }
	 
//	 Say you have an array prices for which the ith element is the price of a given stock on day i.
//
//	 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
//
//	 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//
//	 Example 1:
//
//	 Input: [7,1,5,3,6,4]
//	 Output: 7
//	 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//	              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//	            		  https://www.youtube.com/watch?v=K8iHi8AW1ls
//	              
	 public int maxProfit(int[] prices) {
	       int n=prices.length;
	       int profit=0;
	       for(int i=1;i<n;i++) {
	    	   if(prices[i]>prices[i-1])
	    		   profit+=(prices[i]-prices[i-1]);
	       }
	       return profit;
	 }  
	 
//	 Say you have an array for which the ith element is the price of a given stock on day i.
//
//	 Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//	 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//
//	  
//
//	 Example 1:
//
//	 Input: prices = [3,3,5,0,0,3,1,4]
//	 Output: 6
//	 Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//	 Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
	 //https://www.youtube.com/watch?v=37s1_xBiqH0
	 public int maxProfit2(int[] prices) {
		 int n=prices.length;
		 if(n==0)
			 return 0;
		 int[] left=new int[n];
		 int[] right=new int[n];
		 int left_min=prices[0];
		 for(int i=1;i<n;i++) {
			 left[i]=Math.max(left[i-1], prices[i]-left_min);
			 left_min=Math.min(left_min, prices[i]);
		 }
		 
		 int right_max=prices[n-1];
		 for(int i=n-2;n>=0;n--) {
			 right[i]=Math.max(right[i+1], right_max-prices[i]);
			 right_max=Math.max(right_max, prices[i]);
		 }
		 
		 int profit=right[0];
		 for(int i=1;i<n;i++) {
			 profit=Math.max(profit, left[i-1]+right[i]);
		 }
		 
		 return profit;
	 }
	 
//	 There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
//	 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

//	 Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
//
//	 Note:
//
//	 If there exists a solution, it is guaranteed to be unique.
//	 Both input arrays are non-empty and have the same length.
//	 Each element in the input arrays is a non-negative integer.
//	 Example 1:
//
//	 Input: 
//	 gas  = [1,2,3,4,5]
//	 cost = [3,4,5,1,2]
//
//	 Output: 3
//
//	 Explanation:
//	 Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//	 Travel to station 4. Your tank = 4 - 1 + 5 = 8
//	 Travel to station 0. Your tank = 8 - 2 + 1 = 7
//	 Travel to station 1. Your tank = 7 - 3 + 2 = 6
//	 Travel to station 2. Your tank = 6 - 4 + 3 = 5
//	 Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
//	 Therefore, return 3 as the starting index.
//			https://www.youtube.com/watch?v=xWgbFI_rXJs
		public int canCompleteCircuit(int[] gas, int[] cost) {
		 int tank=0,total=0,index=0;
		 for(int i=0;i<gas.length;i++) {
			 int consume=gas[i]-cost[i];
			 tank +=consume;
			 if(tank<0) {
				 index=i+1;
				 tank=0;
			 }
			 total+=consume;
		 }
		 return total<0 ? -1:index;
	    }
	 
//	 There are N children standing in a line. Each child is assigned a rating value.
//
//	 You are giving candies to these children subjected to the following requirements:
//
//	 Each child must have at least one candy.
//	 Children with a higher rating get more candies than their neighbors.
//	 What is the minimum candies you must give?
//
//	 Example 1:
//
//	 Input: [1,0,2]
//	 Output: 5
//	 Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
	 //https://leetcode.com/problems/candy/solutions/4001238/java-easy-solution-with-proper-explanation-beats-100/?envType=study-plan-v2&envId=top-interview-150
	 public int candy(int[] ratings) {
		 int n=ratings.length;
	      int[]ltor=new int[n];
	      int[]rtol=new int[n];
	      
	      for(int i=0;i<n;i++) {
	    	  ltor[i]=1;
	    	  rtol[i]=1;
	      }
	      
	      for(int i=1;i<n;i++) {
	    	  if(ratings[i]>ratings[i-1])
	    		  ltor[i]=ltor[i-1]+1;
	      }
	      
	      for(int i=n-2;i>=0;i-- ) {
	    	  if(ratings[i]>ratings[i+1])
	    		  rtol[i]=rtol[i+1]+1;
	      }
	      
	      int total=0;
	      for(int i=0;i<n;i++) {
	    	  total += Math.max(ltor[i], rtol[i]);
	      }
	      return total;
	 }
	 
//	 Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
//
//	 Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
//
//	  
//
//	 Example 1:
//
//	 Input: s = "bcabc"
//	 Output: "abc"
	 //https://www.youtube.com/watch?v=muDlIlVE1q4
	 
	 public String removeDuplicateLetters(String s) {
		 int[]lastIndex=new int[26];
	       for(int i=0;i<s.length();i++) {
	    	   lastIndex[s.charAt(i)-'a']=i;
	       }
	       
	       boolean[]seen=new boolean[26];
	       Stack<Integer> st=new Stack<Integer>();
	       for(int i=0;i<s.length();i++) {
	    	   int c=s.charAt(i)-'a';
	    	   if(seen[c]) continue;
	    	   while(!st.isEmpty() && st.peek()>c && i<lastIndex[st.peek()])
                 seen[st.pop()]=false;
	    	    st.add(c);
	    	   seen[c]=true;
	       }
	       
	       StringBuilder sb=new StringBuilder();
	       while(!st.isEmpty()) {
	    	   sb.append((char)(st.pop()+'a'));
	       }
	       
	       return sb.reverse().toString();
	    }
}


package two_pointer;

import java.util.*;

public class LeetCode {
private int rest;

//	A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//	Given a string s, return true if it is a palindrome, or false otherwise.
//
//	 
//
//	Example 1:
//
//	Input: s = "A man, a plan, a canal: Panama"
//	Output: true
//	Explanation: "amanaplanacanalpanama" is a palindrome.
//	Example 2:
//
//	Input: s = "race a car"
//	Output: false
//	Explanation: "raceacar" is not a palindrome.
//	Example 3:
//
//	Input: s = " "
//	Output: true
//	Explanation: s is an empty string "" after removing non-alphanumeric characters.
//	Since an empty string reads the same forward and backward, it is a palindrome.
	
	public boolean isPalindrome(String s) {
        String st = s.replaceAll("[^\\p{Alpha}]+", "").toLowerCase();
        if(st.isBlank()||st.isEmpty()||(st.length()==1))
        	return true;
        int n=st.length();
        int i=0,j=n-1;
        while(i<j) {
        	if(st.charAt(i) == st.charAt(j)) {
        		i++;j--;
        	}else {
        		return false;
        	}
        }
        return true;
    }
	
	public int maxProfit(int[] prices) {
//		int i=0,max=0;
//		if(prices.length==1)
//			return max;
//		while(i<prices.length-1) {
//			int bp = prices[i];
//			for(int j=i+1;j<=(prices.length-1);j++) {
//				if(bp<prices[j]) {
//					int temp = prices[j]-bp;
//					max=Integer.max(max, temp);
//				}
//			}
//			i++;
//		}
//		return max;
		int minPrice = Integer.MAX_VALUE;
		int max =0;
		for (int i=0;i<prices.length;i++) {
			if(minPrice>prices[i])
				minPrice=prices[i];
			else if(minPrice-prices[i] > max)
				max= minPrice-prices[i];
		}
		return max;
	}
	
	public int maxProfitUsingDp(int[] prices) {
		int[] dp = new int[prices.length];
		Arrays.fill(dp, -1);
		int max=0;
		for(int i=0;i<prices.length-1;i++) {
			int k= getMax(prices,i+1,dp);
			if(prices[i]>k)
				continue;
			else {
				int temp = k-prices[i];
				max=Integer.max(max, temp);
			}
		}
		return max;
	}
	
	public int getMax(int[] prices,int i,int[] dp) {
		if(i==prices.length-2) {
			if(dp[i]==-1) {
			if(prices[i]<prices[i+1]) {
				dp[i]=prices[i+1];
			}else
				dp[i]=prices[i];
			}
			return dp[i];
		}
		if(i==prices.length-1) {
			dp[i]=prices[i];
			return dp[i];
		}
		if(dp[i]==-1) {
			rest = (dp[i+1]!=-1)?dp[i+1]:getMax(prices,i+1,dp);
		if(rest>prices[i]) {
				dp[i]=rest;
			}
		else
			dp[i]=prices[i];
		}
		return dp[i];
			
	}
	
//	You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
//
//	On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
//	However, you can buy it then immediately sell it on the same day.
//
//	Find and return the maximum profit you can achieve.
//
//	 
//
//	Example 1:
//
//	Input: prices = [7,1,5,3,6,4]
//	Output: 7
//	Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//	Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//	Total profit is 4 + 3 = 7.
	//https://www.youtube.com/watch?v=K8iHi8AW1ls
	
	public int maxProfitWithMultipleDP(int[] prices) {
        int n = prices.length;
        if(n==0||n==1)
        	return 0;
        if(n==2)
        	return Integer.max(0, prices[1]-prices[0]);
        int []dp_buy=new int[n];
        int []dp_sell=new int[n];
        Arrays.fill(dp_buy, 0);
        Arrays.fill(dp_sell, 0);
        return maxProfitWithMultipleDPUtil(true,prices,0,dp_buy,dp_sell,n);
    }
	
	public int maxProfitWithMultipleDPUtil(boolean buy,int []prices,int i,int[]dp_buy,int[]dp_sell,int n) {
		if((i>=n)|| (buy && i==n-1))
			return 0;
		else if (buy && dp_buy[i]>0)
			return dp_buy[i];
		else if (!buy && dp_sell[i]>0)
			return dp_sell[i];
		int profit = 0;
		int skip= maxProfitWithMultipleDPUtil(buy,prices,i,dp_buy,dp_sell,n);
		if(buy) {
			profit = -prices[i]+maxProfitWithMultipleDPUtil(false,prices,i+1,dp_buy,dp_sell,n);
			dp_buy[i]=Integer.max(profit, skip);
			return dp_buy[i];
		}else {
			profit = prices[i]+maxProfitWithMultipleDPUtil(true,prices,i+1,dp_buy,dp_sell,n);
			dp_sell[i]=Integer.max(profit, skip);
			return dp_sell[i];
		}
	}

	
	//same problem without dp as dp one gives TLE
//	this is a peak valley method.here what we do is if the cost of today is higher than yesterday then we just add the diff to profit.
//	e.g. at day i,if i-1 has less price than i the profit at day i would be p[i]-p[i-1].So we start from i=1 as we need cost of  day (i-1)
	public int maxProfitWithMultiple(int[] prices) {
		int profit = 0,n=prices.length;
		if(n==0||n==1)
			return profit;
		for (int i=1;i<n;i++) {
			if(prices[i]>prices[i-1])
				profit+=prices[i]-prices[i-1];
		}
		return profit;
		
	}
	
//	Given an array nums of size n, return the majority element.
//
//			The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
//
//			 
//
//			Example 1:
//
//			Input: nums = [3,2,3]
//			Output: 3
//			Example 2:
//
//			Input: nums = [2,2,1,1,1,2,2]
//			Output: 2
	
//	https://www.youtube.com/watch?v=n5QY3x_GNDg
//		moore's voting algorithm
//		
		public int majorityElement(int[] nums) {
	        int major = nums[0],count=0;
	        for(int i=0;i<nums.length;i++) {
	        	if(nums[i]==major)
	        		count++;
	        	else {
	        		count--;
	        		if(count==0) {
	        			major=nums[i];
	        			count=1;
	        		}
	        	}
	        }
	        int c=0;
	        for(int i:nums) {
	        	if(i==major)
	        		c++;
	        }
	        if(c>= Math.floor(nums.length/2))
	        	return major;
	        return 0;
	    }
		
//		Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
//
//		 
//
//		Example 1:
//
//		Input: nums = [3,2,3]
//		Output: [3]
//		Example 2:
//
//		Input: nums = [1]
//		Output: [1]
//		Example 3:
//
//		Input: nums = [1,2]
//		Output: [1,2]
		//Boyer-moore voting algo.Actually in a array of size n,there can be at most 2 elements whose frequency can be more than floor(n/3)
		public List<Integer> majorityElementTwo(int[] nums) {
	        int maj1=0,maj2=0,c1=0,c2=0;
	        for(int i:nums) {
	        	if(i==maj1)
	        		c1++;
	        	else if (i==maj2)
	        		c2++;
	        	else if (c1==0) {
	        		maj1=i;
	        		c1++;
	        	}else if (c2==0) {
	        		maj2=i;
	        		c2++;
	        	}else {
	        		c1--;
	        		c2--;
	        	}
	        }
	        
	        //validate the candidates
	        int valid_freq=Math.floorDiv(nums.length, 3),vc1=0,vc2=0;
	        for(int i : nums) {
	        	if(i==maj1)
	        		vc1++;
	        	else if(i==maj2)
	        		vc2++;
	        }
	        List<Integer> res=new ArrayList<Integer>();
	        if(vc1>valid_freq)
	        	res.add(maj1);
	        if(vc2>valid_freq)
	        	res.add(maj2);
	        return res;
	    }
		
//		Given an integer array nums, return the most frequent even element.
//
//				If there is a tie, return the smallest one. If there is no such element, return -1.
//
//				 
//
//				Example 1:
//
//				Input: nums = [0,1,2,2,4,4,1]
//				Output: 2
//				Explanation:
//				The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
//				We return the smallest one, which is 2.
//				Example 2:
//
//				Input: nums = [4,4,4,9,2,4]
//				Output: 4
//				Explanation: 4 is the even element appears the most.
//				Example 3:
//
//				Input: nums = [29,47,21,41,13,37,25,7]
//				Output: -1
//				Explanation: There is no even element.
		//https://leetcode.com/problems/most-frequent-even-element/solutions/3735920/simple-easy-to-understand-solution-using-hashmap-o-n/
		
		public int mostFrequentEven(int[] nums) {
	        Map<Integer,Integer> hm = new HashMap<>();
	        int maxFreq=0;
	        int ele = Integer.MAX_VALUE;
	        for(int i:nums) {
	        	if(i%2==0) {
	        		hm.put(i, hm.getOrDefault(i, 0)+1);
	        		int curr = hm.get(i);
	        		if(curr>maxFreq||curr==maxFreq && i<ele) {
	        			maxFreq=curr;
	        			ele=i;
	        		}
	        	}
	        }
	        return maxFreq!=0 ? ele: -1;
	        
	    }
//		You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your
//		maximum jump length at that position.
//
//		Return true if you can reach the last index, or false otherwise.
//
//		 
//
//		Example 1:
//
//		Input: nums = [2,3,1,1,4]
//		Output: true
//		Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//		Example 2:
//
//		Input: nums = [3,2,1,0,4]
//		Output: false
//		Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
		
		 public boolean canJump(int[] nums) {
			 if(nums.length==1)
	 				return true;
			 Map<Integer,Boolean> dp = new HashMap<>();
			 canJumpUtil(nums, 0, dp);
			 return dp.get(0);
		        
		 }
		 
		 boolean canJumpUtil(int[] nums,int i,Map<Integer,Boolean> dp) {
			 boolean res = false;
			 if(i==nums.length-1) {
				// dp[i]=true;
				 res= true;
			 }
			 else if(i>=nums.length) {
				 res = false;
			 }
			 else if(nums[i]==0) {
				 res= false;
				 dp.put(i, res);
			 }
			 else {
			 if(!dp.containsKey(i)) {
				 for(int k=1;k<=nums[i];k++) {
					 boolean temp = canJumpUtil(nums, i+k, dp);
					 res = res || temp;
				 }

				 dp.put(i, res);
			 	}
			 else
				 res=dp.get(i);
			 }
			 return res;
				 
		 }
		 public boolean canJumpTopDown(int[] nums) {
		 
		 int n=nums.length;
			boolean[] dp = new boolean[n];
			dp[n-1]=true;
			for(int i=n-2;i>=0;i--){
				boolean canReach = false;
				for(int k=1;k<=nums[i] && i+k < n;k++){
					canReach = canReach||dp[i+k];
				}
				dp[i]=canReach;
			}
			return dp[0];
		 }
		 
//		 You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
//
//		 Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i],
//		 you can jump to any nums[i + j] where:
//
//		 0 <= j <= nums[i] and
//		 i + j < n
//		 Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
//
//		  
//
//		 Example 1:
//
//		 Input: nums = [2,3,1,1,4]
//		 Output: 2
//		 Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//		 Example 2:
//
//		 Input: nums = [2,3,0,1,4]
//		 Output: 2
		 
		 public int jump(int[] nums) {
			 if(nums.length==1)
	 				return 0;
			 int[] dp = new int[nums.length];
			 Arrays.fill(dp, -1);
			 jumpUtil(nums,dp,0);
			 return dp[0];
		        
		 }
		 
		 public int jumpUtil(int[] nums,int[]dp,int i) {
		        if(i==nums.length-1)
		        	return 0;
		        if(i>=nums.length)
		        	return Integer.MAX_VALUE;
		        if(dp[i]!=-1)
		        	return dp[i];
		        int min=Integer.MAX_VALUE;
		        for(int k=1;k<=nums[i];k++) {
		        	min = Math.min(min,jumpUtil(nums, dp, i+k));
		        }
		        dp[i]= min != Integer.MAX_VALUE ? min+1 : min;
		        return dp[i];
		 }
		 
		 
		 
//		 Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
//		 return the researcher's h-index.
//
//				 According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has
//				 published at least h papers that have each been cited at least h times.
//
//				  
//
//				 Example 1:
//
//				 Input: citations = [3,0,6,1,5] -> 0 1 3 6 5
//				 Output: 3
//				 Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
//				 Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
//				 Example 2:
//
//				 Input: citations = [1,3,1] -> 1 1 3     6 7 10
//				 Output: 1
		 
		 		public int hIndex(int[] citations) {
		 			int n= citations.length;
		 			Arrays.sort(citations);
		 			//below commented part is inspired from the discussion 
//		 			https://leetcode.com/problems/h-index/solutions/3904184/5-lines-of-code-the-easiest-solution-with-o-n-log-and-o-1-for-space-complexity/
		 			
//		 			int h_candidate = 0;
//		 			for(int i=0;i<n;i++) {
//		 				int len = n-i;
//		 				int k = citations[i];
//		 				h_candidate = Math.max(h_candidate, Math.min(k,len));
//		 			}

//		 			return h_candidate;
		 			
		 			
		 			
		 			//this sol uses binary search where the min h can be 0 and max can be n i.e length of array.As the h will be in range from 0 to n
//		 			we use binary search to find the mid.Then check if the array has at least mid number of elements whose value is >=mid.If the count 
//		 					is less than mid the we decrease the mid taking end as mid-1 and else we increase start as mid.At the end the value at start is the h
//		 					https://leetcode.com/problems/h-index/solutions/3904184/5-lines-of-code-the-easiest-solution-with-o-n-log-and-o-1-for-space-complexity/
		 			int low =0,high = n;
		 			while(low<high) {
		 				int mid = (low+high+1)/2;
		 				int count = 0;
		 				for(int i=0;i<n;i++) {
							if (citations[i] >= mid)
								count++;
						}
		 				if(count>=mid)
		 					low=mid;
		 				else
		 					high=mid-1;
		 			}
		 			return low;
		        
		    }
		 		
//		 		Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
//
//		 				The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//
//		 				You must write an algorithm that runs in O(n) time and without using the division operation.
//
//		 				 
//
//		 				Example 1:
//
//		 				Input: nums = [1,2,3,4] -> 1 2 6 24      24 24 12 4
//		 				Output: [24,12,8,6]
//		 				Example 2:
//
//		 				Input: nums = [-1,1,0,-3,3]
//		 				Output: [0,0,9,0,0]
		 		//https://www.youtube.com/watch?v=gREVHiZjXeQ
		 		public int[] productExceptSelf(int[] nums) {
		 	        int n = nums.length;
		 	        int[] lft=new int[n];
//		 	        int[] right=new int[n];
		 	        lft[0]=nums[0];
//		 	        right[n-1]=nums[n-1];
		 	        for(int i=1;i<n;i++)
		 	        	lft[i]=lft[i-1]*nums[i]; //1 2 6 24
		 	        int product = 1;
		 	        for (int i=n-1;i>=0;i--) {
		 	        	if(i==0)
		 	        		lft[i]=product;
		 	        	else {
		 	        		lft[i]=lft[i-1]*product;
		 	        		product*=nums[i];
		 	        	}
		 	        }
		 	        return lft;
//		 	        
//		 	        for(int i=n-2;i>=0;i--)
//		 	        	right[i]=right[i+1]*nums[i];//24 24 12 4
//		 	        
//		 	        int[]res=new int[n];
//		 	        for(int i=0;i<n;i++) {
//		 	        	if(i==0)
//		 	        		res[i]=right[i+1];
//		 	        	else if(i==n-1)
//		 	        		res[i]=lft[i-1];
//		 	        	else
//		 	        		res[i]=lft[i-1]*right[i+1];
//		 	        }
//		 	        
//		 	        return res;
		 	    }
		 		
//		 		There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
//
//		 		You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
//		 		You begin the journey with an empty tank at one of the gas stations.
//
//		 		Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the
//		 		clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
//
//		 		 
//
//		 		Example 1:
//
//		 		Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//		 		Output: 3
//		 		Explanation:
//		 		Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//		 		Travel to station 4. Your tank = 4 - 1 + 5 = 8
//		 		Travel to station 0. Your tank = 8 - 2 + 1 = 7
//		 		Travel to station 1. Your tank = 7 - 3 + 2 = 6
//		 		Travel to station 2. Your tank = 6 - 4 + 3 = 5
//		 		Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
//		 		Therefore, return 3 as the starting index.
//		 		Example 2:
//
//		 		Input: gas = [2,3,4], cost = [3,4,3]
//		 		Output: -1
//		 		Explanation:
//		 		You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
//		 		Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//		 		Travel to station 0. Your tank = 4 - 3 + 2 = 3
//		 		Travel to station 1. Your tank = 3 - 3 + 3 = 3
//		 		You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
//		 		Therefore, you can't travel around the circuit once no matter where you start.
		 		//https://www.youtube.com/watch?v=xWgbFI_rXJs
		 		public int canCompleteCircuit(int[] gas, int[] cost) {
		 	        int n = gas.length;
//		 	        int res = -1;
//		 	        for(int i=0;i<n;i++) {
//		 	        	if(gas[i]<cost[i])
//		 	        		continue;
//		 	        	else {
//		 	        		if(canCompleteCircuitUtil(gas, cost, i, gas[i], true, n,i)) {
//		 	        			res=i;
//		 	        			break;
//		 	        		}
//		 	        	}
//		 	        }
//		 	        return res;
		 			int total = 0,tank=0,consume=0;
		 			int index=0;
		 			for(int i=0;i<n;i++) {
		 				consume = gas[i]-cost[i];
		 				tank+=consume;
		 				if(tank<0) {
		 					tank=0;
		 					index=i+1;
		 				}
		 				total+=consume;
		 			}
		 			return total>=0?index:-1;
		 	    }
		 		
//		 		boolean canCompleteCircuitUtil(int[] gas, int[] cost,int index,int balance,boolean isStarting,int n,int actualStart) {
//		 			
//		 			if(!isStarting && balance<cost[index])
//		 				return false;
//		 			if(!isStarting && (actualStart==index))
//		 				return true;
//		 			if(index==n-1) {
//		 				balance = balance-cost[index]+gas[0];
//		 				return canCompleteCircuitUtil(gas, cost, 0, balance, false, n,actualStart);
//		 			}
//		 			else {
//		 			balance = balance-cost[index]+gas[index+1];
//		 			return canCompleteCircuitUtil(gas, cost, index+1, balance, false, n,actualStart);
//		 			}
//		 			
//		 		}
//		 		There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
//
//		 		You are giving candies to these children subjected to the following requirements:
//
//		 		Each child must have at least one candy.
//		 		Children with a higher rating get more candies than their neighbors.
//		 		Return the minimum number of candies you need to have to distribute the candies to the children.
//
//		 		 
//
//		 		Example 1:
//
//		 		Input: ratings = [1,0,2] -> 1 1 1 -> 1 1 2 -> 2 1 2
//		 		Output: 5
//		 		Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
//		 		Example 2:
//
//		 		Input: ratings = [1,2,2]
//		 		Output: 4
//		 		Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
//		 		The third child gets 1 candy because it satisfies the above two conditions.
		 		//https://leetcode.com/problems/candy/solutions/3994447/fully-explained-c-solution-clean-code-beats-95-of-solutions-easy-to-understand/?envType=study-plan-v2&envId=top-interview-150
		 		public int candy(int[] ratings) {
		 	        int n= ratings.length;
		 	        int []ltor=new int [n];
		 	        Arrays.fill(ltor, 1);
		 	       for(int i=1;i<n;i++) {
		 	    	   if(ratings[i]>ratings[i-1])
		 	    		   ltor[i]=ltor[i-1]+1;
		 	       }
		 	      for(int i=n-2;i>=0;i--) {
		 	    	   if(ratings[i]>ratings[i+1] && ltor[i]<=ltor[i+1]) // the rating is higher than right neighbour and the candy is less or equal then only increase
		 	    		  ltor[i]=ltor[i+1]+1;
		 	       }
		 	      int min=0;
		 	      for(int i=0;i<n;i++)
		 	    	  min+=ltor[i];
		 	      return min;
		 	    }
		 		
//		 		Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
//
//		 		 
//
//		 		Example 1:
//
//
//		 		Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//		 		Output: 6
//		 		Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//		 		Example 2:
//
//		 		Input: height = [4,2,0,3,2,5]
//		 		Output: 9
//https://leetcode.com/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-interview-150
		 		// Solution taken from discussion and solution (1st) one
		 		
		 		public int trap(int[] height) {
		 	       int n=height.length;
		 	       int[] lMax = new int[n];
		 	       int[] rMax = new int[n];
		 	       int max=Integer.MIN_VALUE;
		 	       for(int i=0;i<n;i++) {
		 	    	   max=Math.max(max, height[i]);
		 	    	   lMax[i]=max;
		 	       }
		 	       max=Integer.MIN_VALUE;
		 	       for(int i=n-1;i>=0;i--) {
			 	    	max=Math.max(max, height[i]);
		 	    	   rMax[i]=max;
		 	       }
		 	       int total = 0;
		 	       for(int i=0;i<n;i++) {
		 	    	   total+=Math.min(lMax[i], rMax[i])-height[i];
		 	       }
		 	       return total;
		 	    }
		 		
		     	
//		     	Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
	//
//		     	Symbol       Value
//		     	I             1
//		     	V             5
//		     	X             10
//		     	L             50
//		     	C             100
//		     	D             500
//		     	M             1000
//		     	For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
	//
//		     	Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
	//
//		     	I can be placed before V (5) and X (10) to make 4 and 9. 
//		     	X can be placed before L (50) and C (100) to make 40 and 90. 
//		     	C can be placed before D (500) and M (1000) to make 400 and 900.
//		     	Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
		     	
		     	public int romanToInt(String s) {
		            HashMap<Character,Integer> hm = new HashMap<Character, Integer>();
		            hm.put('I', 1);
		            hm.put('V', 5);
		            hm.put('X', 10);
		            hm.put('L', 50);
		            hm.put('C', 100);
		            hm.put('D', 500);
		            hm.put('M', 1000);
		            
		            int result = 0;
		            for(int i=0;i<s.length();i++) {
		            	if(i>0 && hm.get(s.charAt(i))>hm.get(s.charAt(i-1))) {
		            		result+=hm.get(s.charAt(i))-(2*hm.get(s.charAt(i-1)));
		            	}else
		            		result+=hm.get(s.charAt(i));
		            }
		            return result;
		        }
		     	
		     	public String intToRoman(int num) {
		     		String[] thousands = {"","M","MM","MMM"};
		     		String[] hundreds = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
		     		String[] tens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
		     		String[] units = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
		     		
		     		return thousands[num/1000]+hundreds[(num%1000)/100]+tens[(num%100)/10]+units[num%10];
		     		
		     	}
		     	
//		     	Given a string s consisting of words and spaces, return the length of the last word in the string.
//
//		     			A word is a maximal 
//		     			substring
//		     			 consisting of non-space characters only.
//
//		     			 
//
//		     			Example 1:
//
//		     			Input: s = "Hello World"
//		     			Output: 5
//		     			Explanation: The last word is "World" with length 5.
//		     			Example 2:
//
//		     			Input: s = "   fly me   to   the moon  "
//		     			Output: 4
//		     			Explanation: The last word is "moon" with length 4.
//		     			Example 3:
//
//		     			Input: s = "luffy is still joyboy"
//		     			Output: 6
//		     			Explanation: The last word is "joyboy" with length 6.
		     	
		     	public int lengthOfLastWord(String s) {
				     int len =0,endSpace=0;
				     boolean foundFirstChar = false;
		            for(int i=s.length()-1;i>=0;i--) {
		            	
		            	if(Character.isWhitespace(s.charAt(i))) {
		            		if(!foundFirstChar) {
		            			endSpace++;
		            			continue;
		            		}

		            		len	= (s.length()-(i+1))-endSpace;
		            		break;
		            	}
		            	else if(i==0) {
		            		len= s.length()-endSpace;
		            	}
		            	else
		            		foundFirstChar=true;
		            }
		            return len;
		        }
		     	
//		     	Write a function to find the longest common prefix string amongst an array of strings.
//
//		     	If there is no common prefix, return an empty string "".
//
//		     	 
//
//		     	Example 1:
//
//		     	Input: strs = ["flower","flow","flight"]
//		     	Output: "fl"
//		     	Example 2:
//
//		     	Input: strs = ["dog","racecar","car"]
//		     	Output: ""
//		     	Explanation: There is no common prefix among the input strings.
		     	
		     	public String longestCommonPrefix(String[] strs) {
		     		if(strs.length==0)
		                return "";
		     		Arrays.sort(strs, Comparator.comparingInt(String::length));
		     		String prefix = strs[0];
		     		int n1 = prefix.length();
		     		for(int i=1;i<strs.length;i++) {
		     			prefix = longestCommonPrefixUtil(prefix, strs[i], n1);
		     		}
		     		return prefix;
		        }
		     	
		     	public String longestCommonPrefixUtil(String pref,String s,int n) {
		     		String res = "";
		     		int len=s.length();
		     		for(int  i=0,j=0;i<n && j<len;i++,j++) {
		     			if(pref.charAt(i) != s.charAt(j))
		     				break;
		     			res +=s.charAt(j);
		     		}
		     		return res;
		     	}
//		     	Given an input string s, reverse the order of the words.
//
//		     	A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
//
//		     	Return a string of the words in reverse order concatenated by a single space.
//
//		     	Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
//
//		     	 
//
//		     	Example 1:
//
//		     	Input: s = "the sky is blue"
//		     	Output: "blue is sky the"
//		     	Example 2:
//
//		     	Input: s = "  hello world  "
//		     	Output: "world hello"
//		     	Explanation: Your reversed string should not contain leading or trailing spaces.
//		     	Example 3:
//
//		     	Input: s = "a good   example"
//		     	Output: "example good a"
//		     	Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
		     	public String reverseWords(String s) {
		            String[] words = s.split("\\s");
		            String reversedSentence = "";
		            for(int i=0;i<words.length;i++) {
		            	if(words[i].equals(""))
		            		continue;
		            	if(i==words.length-1)
		            		reversedSentence = words[i]+reversedSentence;
		            	else
		            		reversedSentence = " "+words[i]+reversedSentence;
		            }
		            return reversedSentence;
		        }
		     	
//		     	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
//		     	P   A   H   N
//		     	A P L S I I G
//		     	Y   I   R
//		     	And then read line by line: "PAHNAPLSIIGYIR"
//
//		     	Write the code that will take a string and make this conversion given a number of rows:
//
//		     	string convert(string s, int numRows);
//		     	 
//
//		     	Example 1:
//
//		     	Input: s = "PAYPALISHIRING", numRows = 3
//		     	Output: "PAHNAPLSIIGYIR"
//		     	Example 2:
//
//		     	Input: s = "PAYPALISHIRING", numRows = 4
//		     	Output: "PINALSIGYAHRPI"
//		     	Explanation:
//		     	P     I    N
//		     	A   L S  I G
//		     	Y A   H R
//		     	P     I
//		     	Example 3:
//
//		     	Input: s = "A", numRows = 1
//		     	Output: "A"
		     	//https://www.youtube.com/watch?v=wMpYkNGh9_od
		     	public String convert(String s, int numRows) {
		            if(numRows==1)
		            	return s;
		            //boolean down=true;
		            String res="";
		            int step=2*(numRows-1);
		            for(int i=0;i<numRows;i++) {
		            	if(i==0 || i==numRows-1) {
		            		for(int j=i;j<s.length();j+=step)
		            			res+=s.charAt(j);
		            	}else {
		            		int j=i;
		            		boolean flag = true;
		            		int step1 = 2*(numRows-i-1);
		            		int step2 =step-step1;
		            		while(j<s.length()) {
		            			res+=s.charAt(j);
		            			if(flag)
		            				j+=step1;
		            			else
		            				j+=step2;
		            			flag=!flag;
		            		}
		            	}
		            }
		            return res;
		        }
		     	
//		     	Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
//
//		     	 
//
//		     	Example 1:
//
//		     	Input: s = "abab"
//		     	Output: true
//		     	Explanation: It is the substring "ab" twice.
//		     	Example 2:
//
//		     	Input: s = "aba"
//		     	Output: false
//		     	Example 3:
//     
//		     	Input: s = "abcabcabcabc"
//		     	Output: true
//		     	Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
		     	
		     	//brute force //https://www.youtube.com/watch?v=bClIZj66dVE
		     	public boolean repeatedSubstringPattern(String s) {
		            int len = s.length();
		            for(int i=len/2;i>=1;i--) {
		            	if(len%i == 0) {
		            		int repeat = len/i;
		            		String sub = s.substring(0,i);
		            		StringBuilder sb = new StringBuilder();
		            		for(int j=0;j<repeat;j++) {
		            			sb.append(sub);
		            		}
		            		if(sb.toString().equals(s))
		            			return true;
		            	}
		            }
		            return false;
		            
		        }
		     	
//		     	Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and
//		     	is fully (left and right) justified.
//
//		     	You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' '
//		     	when necessary so that each line has exactly maxWidth characters.
//
//		     	Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words,
//		     	the empty slots on the left will be assigned more spaces than the slots on the right.
//
//		     	For the last line of text, it should be left-justified, and no extra space is inserted between words.
//
//		     	Note:
//
//		     	A word is defined as a character sequence consisting of non-space characters only.
//		     	Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
//		     	The input array words contains at least one word.
//		     	 
//
//		     	Example 1:
//
//		     	Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
//		     	Output:
//		     	[
//		     	   "This    is    an",
//		     	   "example  of text",
//		     	   "justification.  "
//		     	]
//		     	Example 2:
//
//		     	Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
//		     	Output:
//		     	[
//		     	  "What   must   be",
//		     	  "acknowledgment  ",
//		     	  "shall be        "
//		     	]
//		     	Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
//		     	Note that the second line is also left-justified because it contains only one word.
//		     	Example 3:
//
//		     	Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
//		     	Output:
//		     	[
//		     	  "Science  is  what we",
//		     	  "understand      well",
//		     	  "enough to explain to",
//		     	  "a  computer.  Art is",
//		     	  "everything  else  we",
//		     	  "do                  "
//		     	]

		     	
//		 		You are given a 0-indexed integer array nums representing the contents of a pile, where nums[0] is the topmost element of the pile.
//
//		 		In one move, you can perform either of the following:
//
//		 		If the pile is not empty, remove the topmost element of the pile.
//		 		If there are one or more removed elements, add any one of them back onto the pile. This element becomes the new topmost element.
//		 		You are also given an integer k, which denotes the total number of moves to be made.
//
//		 		Return the maximum value of the topmost element of the pile possible after exactly k moves. In case it is not possible to obtain a non-empty pile after k moves, return -1.
//
		     	public List<String> fullJustify(String[] words, int maxWidth) {
		            List<String> res = new ArrayList<>();
		            int n = words.length;
		            int i = 0;
		            while (i < n) {
		                int j = i + 1;
		                int c = words[i].length();
		                int wc = words[i].length();
		                while (j < n && c + words[j].length() + 1 <= maxWidth) {
		                    c += words[j].length() + 1;
		                    wc += words[j].length();
		                    j++;
		                }
		                if (j == n || j - i == 1) {
		                    StringBuilder s = new StringBuilder();
		                    for (int p = i; p < j; p++) {
		                        s.append(words[p]);
		                        if (p != j - 1)
		                            s.append(' ');
		                    }
		                    int ts = maxWidth - s.length();
		                    for (int k = 0; k < ts; k++) {
		                        s.append(' ');
		                    }
		                    res.add(s.toString());
		                    i = j - 1;
		                } else {
		                    int tw = j - i;
		                    int ts = maxWidth - wc;
		                    int es = ts / (tw - 1);
		                    int extra = ts % (tw - 1);
		                    StringBuilder s = new StringBuilder();
		                    for (int p = i; p < j; p++) {
		                        s.append(words[p]);
		                        if (extra > 0) {
		                            s.append(' ');
		                            extra--;
		                        }
		                        if (p != j - 1) {
		                            for (int k = 0; k < es; k++) {
		                                s.append(' ');
		                            }
		                        }
		                    }
		                    res.add(s.toString());
		                    i = j - 1;
		                }
		                i++;
		            }
		            return res;
		        }
		     	
//		     	Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
//
//		     			A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters 
//		     			without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
//
//		     			 
//
//		     			Example 1:
//
//		     			Input: s = "abc", t = "ahbgdc"
//		     			Output: true
//		     			Example 2:
//
//		     			Input: s = "axc", t = "ahbgdc"
//		     			Output: false
		     	public boolean isSubsequence(String s, String t) {
		     		if(s.isBlank() && t.isBlank())
		     			return true;
		     		int i=0,j=0;
		            while(i<t.length() && j<s.length()) {
		            	if(s.charAt(j) == t.charAt(i)) {
		            		if(j == s.length()-1)
		            			return true;
		            		i++;j++;
		            	}else {
		            		if(i==t.length()-1)
		            			return false;
		            		i++;
		            	}
		            	
		            }
		            return false;
		        }
//
//		 		Example 1:
//
//		 		Input: nums = [5,2,2,4,0,6], k = 4
//		 		Output: 5
//		 		Explanation:
//		 		One of the ways we can end with 5 at the top of the pile after 4 moves is as follows:
//		 		- Step 1: Remove the topmost element = 5. The pile becomes [2,2,4,0,6].
//		 		- Step 2: Remove the topmost element = 2. The pile becomes [2,4,0,6].
//		 		- Step 3: Remove the topmost element = 2. The pile becomes [4,0,6].
//		 		- Step 4: Add 5 back onto the pile. The pile becomes [5,4,0,6].
//		 		Note that this is not the only way to end with 5 at the top of the pile. It can be shown that 5 is the largest answer possible after 4 moves.
//		 		Example 2:
//
//		 		Input: nums = [2], k = 1
//		 		Output: -1
//		 		Explanation: 
//		 		In the first move, our only option is to pop the topmost element of the pile.
//		 		Since it is not possible to obtain a non-empty pile after one move, we return -1.
		 		//https://leetcode.com/problems/maximize-the-topmost-element-after-k-moves/solutions/3101410/simple-and-easy-java-solution/
		 		public int maximumTop(int[] nums, int k) {
		 	        if(nums.length==0 && k==0)
		 	        	return -1;
		 	        if(nums.length == 1) {
		 	        	if(k%2==0)
		 	        		return nums[0];
		 	        	else
		 	        		return -1;
		 	        }
		 	        int n = nums.length;
		 	        int max = -1;
		 	        if(k>n) {
		 	        	for(int i=0;i<n;i++) {
		 	        		max=Math.max(max, nums[i]);
		 	        	}
		 	        	return max;
		 	        }
		 	        else if (k==n) {
		 	        	for(int i=0;i<k-1;i++) {
		 	        		max=Math.max(max, nums[i]);
		 	        	}
		 	        	return max;
		 	        }
		 	        else {
		 	        	if(k==0)
		 	        		return max;
		 	        	else {
		 	        		for(int i=0;i<k-1;i++) {
			 	        		max=Math.max(max, nums[i]);
			 	        	}
		 	               return Math.max(max,nums[k]);
		 	        	}
		 	        }
		 	    }
//		 		You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0)
//		 		and (i, height[i]).
//
//		 		Find two lines that together with the x-axis form a container, such that the container contains the most water.
//
//		 		Return the maximum amount of water a container can store.
//
//		 		Notice that you may not slant the container.
//
//		 		 
//
//		 		Example 1:
//
//
//		 		Input: height = [1,8,6,2,5,4,8,3,7]
//		 		Output: 49
//		 		Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section)
//		 		the container can contain is 49.
//		 		Example 2:
//
//		 		Input: height = [1,1]
//		 		Output: 1
		 		//https://leetcode.com/problems/container-with-most-water/solutions/3701708/best-method-c-java-python-beginner-friendly/?envType=study-plan-v2&envId=top-interview-150
		 		public int maxArea(int[] height) {
		 	        int n = height.length,left =0,right = n-1,resArea = 0;
		 	        while(left<right) {
		 	        	int currArea = Math.min(height[left], height[right])*(right-left);
		 	        	resArea = Math.max(resArea, currArea);
		 	        	if(height[left]<height[right])
		 	        		left++;
		 	        	else
		 	        		right--;
		 	        }
		 	        return resArea;
		 	    }
//		 		You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
//
//		 		The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any
//		 		two candies in the basket.
//
//		 		Return the maximum tastiness of a candy basket.
//
//		 		 
//
//		 		Example 1:
//
//		 		Input: price = [13,5,1,8,21,2], k = 3
//		 		Output: 8
//		 		Explanation: Choose the candies with the prices [13,5,21].
//		 		The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
//		 		It can be proven that 8 is the maximum tastiness that can be achieved.
//		 		Example 2:
//
//		 		Input: price = [1,3,1], k = 2
//		 		Output: 2
//		 		Explanation: Choose the candies with the prices [1,3].
//		 		The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
//		 		It can be proven that 2 is the maximum tastiness that can be achieved.
//		 		Example 3:
//
//		 		Input: price = [7,7,7,7], k = 2
//		 		Output: 0
//		 		Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
		 		//https://leetcode.com/problems/maximum-tastiness-of-candy-basket/solutions/2947983/c-java-python-binary-search-and-sorting/
		 		
		 		public int maximumTastiness(int[] price, int k) {
		 			Arrays.sort(price);
		 		    int lo = 0, hi = 1000_000_000;
		 		    while (lo < hi) {
		 		        int mid = lo + (hi - lo) / 2;
		 		        if (check(mid, price, k)) lo = mid + 1;
		 		        else hi = mid;
		 		    }
		 		    return lo - 1;
		 	    }
		 		
		 		boolean check(int taste,int [] price,int k) {
		 			int last = price[0],i=1,count=1;
		 			while(count< k && i<price.length) {
		 				if(Math.abs(last-price[i])>=taste){
		 					last=price[i];
		 					count++;
		 				}
		 				i++;
		 			}
		 			return count == k;
		 			
		 		}
	
//	Given start, end and an array arr of n numbers. At each step, start is multiplied with any number in the array and then mod operation with 100000 is done to get the new start.
//
//	Your task is to find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.
//
//	Example 1:
//
//	Input:
//	arr[] = {2, 5, 7}
//	start = 3, end = 30
//	Output:
//	2
//	Explanation:
//	Step 1: 3*2 = 6 % 100000 = 6 
//	Step 2: 6*5 = 30 % 100000 = 30
	
	int minimumMultiplications(int[] arr, int start, int end) {

		
    }
}

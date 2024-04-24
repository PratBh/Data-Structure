package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Kedane_Algo {
//	Given an integer array nums, find the 
//	subarray
//	 with the largest sum, and return its sum.
//
//	 
//
//	Example 1:
//
//	Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//	Output: 6
//	Explanation: The subarray [4,-1,2,1] has the largest sum 6.
//	Example 2:
//
//	Input: nums = [1]
//	Output: 1
//	Explanation: The subarray [1] has the largest sum 1.
//	Example 3:
//
//	Input: nums = [5,4,-1,7,8]
//	Output: 23
//	Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
//https://www.youtube.com/watch?v=HCL4_bOd3-4
	public int maxSubArray(int[] nums) {
		int n=nums.length,maxSum=Integer.MIN_VALUE,currSum=0;
		for(int i=0;i<n;i++) {
			currSum+=nums[i];
			maxSum=Math.max(maxSum, currSum);
			if(currSum<0)
				currSum=0;
		}
//		int n = nums.length, maxSum = nums[0], currSum = 0;
//		int[] dp = new int[n];
//		dp[0] = nums[0];
//		for (int i = 1; i < n; i++) {
//			currSum = Math.max(nums[i], dp[i - 1] + nums[i]);
//			dp[i] = currSum;
//			maxSum = Math.max(maxSum, currSum);
//		}
		return maxSum;
	}
//	You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//	You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
//
//	Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
//
//	 
//
//	Example 1:
//
//	Input: prices = [7,1,5,3,6,4]
//	Output: 5
//	Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//	Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
//	Example 2:
//
//	Input: prices = [7,6,4,3,1]
//	Output: 0
//	Explanation: In this case, no transactions are done and the max profit = 0.
	private int rest;
	public int maxProfit(int[] prices) {
		int n=prices.length,profit=Integer.MIN_VALUE;
		int []dp=new int[n];
		Arrays.fill(dp, -1);
		for(int i=0;i<n;i++) {
			int k=getMax(prices, dp, i);
			if(prices[i]>k)
				continue;
			else {
				int temp=k-prices[i];
				profit=Math.max(profit, temp);
			}
		}
		return profit;
	}
	
	private int getMax(int[] prices,int[] dp,int index) {
		if(index==prices.length-2) {
			if(dp[index]==-1)
				dp[index]=Math.max(prices[index], prices[index+1]);
		}
		else if(index==prices.length-1)
			dp[index]=prices[index];
		else {
			if(dp[index]==-1) {
				rest=(dp[index+1]!=-1)?dp[index+1]:getMax(prices, dp, index+1);
				dp[index]=Math.max(rest, prices[index]);
			}
		}
		return dp[index];
	}
	
//	Given an integer array nums, find a 
//	subarray
//	 that has the largest product, and return the product.
//
//	The test cases are generated so that the answer will fit in a 32-bit integer.
//
//	 
//
//	Example 1:
//
//	Input: nums = [2,3,-2,4]
//	Output: 6
//	Explanation: [2,3] has the largest product 6.
//	Example 2:
//
//	Input: nums = [-2,0,-1]
//	Output: 0
//	Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
	//https://www.youtube.com/watch?v=Y6B-7ZctiW8
	public int maxProduct(int[] nums) {
        int n=nums.length;
        int leftProd = 1,rightProd=1,max=nums[0];
        for(int i=0;i<n;i++) {
        	leftProd=(leftProd==0)?1:leftProd;
        	rightProd=(rightProd==0)?1:rightProd;
        	leftProd*=nums[i];
        	rightProd*=nums[n-1-i];
        	max=Math.max(max, Math.max(rightProd, leftProd));
        }
        return max;
    }
	
//	Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
//
//	Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,2,2,3,1]
//	Output: 2
//	Explanation: 
//	The input array has a degree of 2 because both elements 1 and 2 appear twice.
//	Of the subarrays that have the same degree:
//	[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
//	The shortest length is 2. So return 2.
//	Example 2:
//
//	Input: nums = [1,2,2,3,1,4,2]
//	Output: 6
//	Explanation: 
//	The degree is 3 because the element 2 is repeated 3 times.
//	So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
	
	public int findShortestSubArray(int[] nums) {
        int n=nums.length,degree=Integer.MIN_VALUE,res=Integer.MAX_VALUE;
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        for(int num:nums) {
        	mp.put(num, mp.getOrDefault(num, 0)+1);
        	degree=Math.max(degree, mp.get(num));
        }
        Set<Integer> degreeSet=new HashSet<Integer>();
        int i=0,j=0;
        while(j<n) {
        	if(mp.get(nums[j])==degree)
        		degreeSet.add(nums[j]);
        	mp.put(nums[j], mp.get(nums[j])-1);
        	if(mp.get(nums[j])==0 && degreeSet.contains(nums[j])) {
        		while(mp.get(nums[j])==0) {
        			int currLen = j-i+1;
            		res=Math.min(res, currLen);
        			mp.put(nums[i], mp.get(nums[i])+1);
        			i++;
        		}
        	}
        	j++;
        }
        return res;
        
    }
	
//	You are given two 0-indexed integer arrays nums1 and nums2, both of length n.
//
//	You can choose two integers left and right where 0 <= left <= right < n and swap the subarray nums1[left...right] with the
//	subarray nums2[left...right].
//
//	For example, if nums1 = [1,2,3,4,5] and nums2 = [11,12,13,14,15] and you choose left = 1 and right = 2, nums1 becomes [1,12,13,4,5] and
//	nums2 becomes [11,2,3,14,15].
//	You may choose to apply the mentioned operation once or not do anything.
//
//	The score of the arrays is the maximum of sum(nums1) and sum(nums2), where sum(arr) is the sum of all the elements in the array arr.
//
//	Return the maximum possible score.
//
//	A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between
//	indices left and right (inclusive).
//
//	 
//
//	Example 1:
//
//	Input: nums1 = [60,60,60], nums2 = [10,90,10]
//	Output: 210
//	Explanation: Choosing left = 1 and right = 1, we have nums1 = [60,90,60] and nums2 = [10,60,10].
//	The score is max(sum(nums1), sum(nums2)) = max(210, 80) = 210.
//	Example 2:
//
//	Input: nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
//	Output: 220
//	Explanation: Choosing left = 3, right = 4, we have nums1 = [20,40,20,40,20] and nums2 = [50,20,50,70,30].
//	The score is max(sum(nums1), sum(nums2)) = max(140, 220) = 220.
//	Example 3:
//
//	Input: nums1 = [7,11,13], nums2 = [1,1,1]
//	Output: 31
//	Explanation: We choose not to swap any subarray.
//	The score is max(sum(nums1), sum(nums2)) = max(31, 3) = 31.
	
	public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n=nums1.length,sum1=0,sum2=0;
        int [] first = new int[n];//nums2-nums1
        int [] second = new int[n];//nums1-nums2
        for(int i=0;i<n;i++) {
        	int a=nums1[i],b=nums2[i];
        	sum1+=a;
        	sum2+=b;
        	first[i]=b-a;
        	second[i]=a-b;
        }
        int res1=maxSubarrSum(first)+sum1;
        int res2=maxSubarrSum(second)+sum2;
        
        return Math.max(res1, res2);
    }
	
	private int maxSubarrSum(int[]arr) {
		int currSum=0,maxSum=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++) {
			currSum+=arr[i];
			maxSum=Math.max(maxSum, currSum);
			if(currSum<0)
				currSum=0;
		}
		return maxSum;
	}
	
//	You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is
//	abs(numsl + numsl+1 + ... + numsr-1 + numsr).
//
//	Return the maximum absolute sum of any (possibly empty) subarray of nums.
//
//	Note that abs(x) is defined as follows:
//
//	If x is a negative integer, then abs(x) = -x.
//	If x is a non-negative integer, then abs(x) = x.
//	 
//
//	Example 1:
//
//	Input: nums = [1,-3,2,3,-4]
//	Output: 5
//	Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
//	Example 2:
//
//	Input: nums = [2,-5,1,-4,3,-2]
//	Output: 8
//	Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.
	//https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/solutions/3310207/described-easily-with-example-and-solution-in-c-java-python3-javascript/
	public int maxAbsoluteSum(int[] nums) {
		int currSumMax=0,currSumMin=0,maxSum=Integer.MIN_VALUE,minSum=Integer.MAX_VALUE;
		for(int i=0;i<nums.length;i++) {
			currSumMax=Math.max(nums[i], currSumMax+nums[i]);
			currSumMin=Math.min(nums[i], currSumMin+nums[i]);
			maxSum=Math.max(maxSum, currSumMax);
			minSum=Math.min(minSum, currSumMin);
		}
		return Math.max(maxSum, Math.abs(minSum));
    }
//	There is a bag that consists of items, each item has a number 1, 0, or -1 written on it.
//
//	You are given four non-negative integers numOnes, numZeros, numNegOnes, and k.
//
//	The bag initially contains:
//
//	numOnes items with 1s written on them.
//	numZeroes items with 0s written on them.
//	numNegOnes items with -1s written on them.
//	We want to pick exactly k items among the available items. Return the maximum possible sum of numbers written on the items.
//
//	 
//
//	Example 1:
//
//	Input: numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
//	Output: 2
//	Explanation: We have a bag of items with numbers written on them {1, 1, 1, 0, 0}. We take 2 items with 1 written on them and get a sum in a total of 2.
//	It can be proven that 2 is the maximum possible sum.
//	Example 2:
//
//	Input: numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
//	Output: 3
//	Explanation: We have a bag of items with numbers written on them {1, 1, 1, 0, 0}. We take 3 items with 1 written on them, 
//	and 1 item with 0 written on it, and get a sum in a total of 3.
//	It can be proven that 3 is the maximum possible sum.
	
	public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
       int n=numOnes+numZeros+numNegOnes;
       if(n==0||k==0)
    	   return 0;
       int[] arr = new int[n];
       for(int i=0;i<n;i++) {
    	   if(numOnes>0) {
    		   arr[i]=1;
    		   numOnes--;
    		   continue;
    	   }
    	   if(numZeros>0) {
    		   arr[i]=0;
    		   numZeros--;
    		   continue;
    	   }
    	   if(numNegOnes>0) {
    		   arr[i]=-1;
    		   numNegOnes--;
    		   continue;
    	   }
       }
       int i=0,j=0,sum=0,max_sum=Integer.MIN_VALUE;
       while(j<n) {
    	   sum+=arr[j];
    	   if(j-i+1==k) {
    		   max_sum=Math.max(max_sum, sum);
    		   sum-=arr[i];
    		   i++;
    	   }
    	   j++;
       }
       return max_sum;
    }
	
//	You are given a string s, a string chars of distinct characters and an integer array vals of the same length as chars.
//
//	The cost of the substring is the sum of the values of each character in the substring. The cost of an empty string is considered 0.
//
//	The value of the character is defined in the following way:
//
//	If the character is not in the string chars, then its value is its corresponding position (1-indexed) in the alphabet.
//	For example, the value of 'a' is 1, the value of 'b' is 2, and so on. The value of 'z' is 26.
//	Otherwise, assuming i is the index where the character occurs in the string chars, then its value is vals[i].
//	Return the maximum cost among all substrings of the string s.
//
//	 
//
//	Example 1:
//
//	Input: s = "adaa", chars = "d", vals = [-1000]
//	Output: 2
//	Explanation: The value of the characters "a" and "d" is 1 and -1000 respectively.
//	The substring with the maximum cost is "aa" and its cost is 1 + 1 = 2.
//	It can be proven that 2 is the maximum cost.
//	Example 2:
//
//	Input: s = "abc", chars = "abc", vals = [-1,-1,-1]
//	Output: 0
//	Explanation: The value of the characters "a", "b" and "c" is -1, -1, and -1 respectively.
//	The substring with the maximum cost is the empty substring "" and its cost is 0.
//	It can be proven that 0 is the maximum cost.
	
	public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n=s.length(),max_sum=0,curr_sum=0;
        int[] arr=new int[n];
        for(int i=0;i<n;i++) {
        	if(chars.indexOf(s.charAt(i))!=-1) {
        		arr[i]=vals[chars.indexOf(s.charAt(i))];
        	}else
        		arr[i]=s.charAt(i)-'a'+1;
        }
        for(int i=0;i<n;i++) {
        	curr_sum+=arr[i];
        	max_sum=Math.max(max_sum, curr_sum);
        	if(curr_sum<0)
        		curr_sum=0;
        }
        return max_sum;
    }
	
//	The value of an alphanumeric string can be defined as:
//
//		The numeric representation of the string in base 10, if it comprises of digits only.
//		The length of the string, otherwise.
//		Given an array strs of alphanumeric strings, return the maximum value of any string in strs.
//
//		 
//
//		Example 1:
//
//		Input: strs = ["alic3","bob","3","4","00000"]
//		Output: 5
//		Explanation: 
//		- "alic3" consists of both letters and digits, so its value is its length, i.e. 5.
//		- "bob" consists only of letters, so its value is also its length, i.e. 3.
//		- "3" consists only of digits, so its value is its numeric equivalent, i.e. 3.
//		- "4" also consists only of digits, so its value is 4.
//		- "00000" consists only of digits, so its value is 0.
//		Hence, the maximum value is 5, of "alic3".
//		Example 2:
//
//		Input: strs = ["1","01","001","0001"]
//		Output: 1
//		Explanation: 
//		Each string in the array has value 1. Hence, we return 1.
	
	public int maximumValue(String[] strs) {
        int ans=Integer.MIN_VALUE;
        for(String st:strs) {
        	int curr;
        	if(st.matches("[0-9]+"))
        		curr=Integer.parseInt(st);
        	else
        		curr=st.length();
        	ans=Math.max(ans, curr);
        }
        return ans;
    }
//	The score of an array is defined as the product of its sum and its length.
//
//	For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
//	Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums whose score is strictly less than k.
//
//	A subarray is a contiguous sequence of elements within an array.
//
//	 
//
//	Example 1:
//
//	Input: nums = [2,1,4,3,5], k = 10
//	Output: 6
//	Explanation:
//	The 6 subarrays having scores less than 10 are:
//	- [2] with score 2 * 1 = 2.
//	- [1] with score 1 * 1 = 1.
//	- [4] with score 4 * 1 = 4.
//	- [3] with score 3 * 1 = 3. 
//	- [5] with score 5 * 1 = 5.
//	- [2,1] with score (2 + 1) * 2 = 6.
//	Note that subarrays such as [1,4] and [4,3,5] are not considered because their scores are 10 and 36 respectively, while we need scores strictly less than 10.
//	Example 2:
//
//	Input: nums = [1,1,1], k = 5
//	Output: 5
//	Explanation:
//	Every subarray except [1,1,1] has a score less than 5.
//	[1,1,1] has a score (1 + 1 + 1) * 3 = 9, which is greater than 5.
//	Thus, there are 5 subarrays having scores less than 5.
	//https://leetcode.com/problems/count-subarrays-with-score-less-than-k/solutions/2139590/sliding-window-first-principles-explaination-c/
	public long countSubarrays(int[] nums, long k) {
       int i=0,j=0,n=nums.length;
    		   long sum=0,count=0;
       while(j<n) {
    	   	sum+=nums[j];
    	   	while(sum*(j-i+1)>=k) {
    	   		sum-=nums[i];
    	   		i++;
    	   	}
    	   	count+=(j-i+1);
    	   	j++;
    	   }
       return count;
       }
	
//	The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string.
//	Note the two characters may or may not be the same.
//
//	Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
//
//	A substring is a contiguous sequence of characters within a string.
//
//	 
//
//	Example 1:
//
//	Input: s = "aababbb"
//	Output: 3
//	Explanation:
//	All possible variances along with their respective substrings are listed below:
//	- Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
//	- Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
//	- Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
//	- Variance 3 for substring "babbb".
//	Since the largest possible variance is 3, we return it.
//	Example 2:
//
//	Input: s = "abcde"
//	Output: 0
//	Explanation:
//	No letter occurs more than once in s, so the variance of every substring is 0.
	//https://www.youtube.com/watch?v=DbfHIdZL8rQ
	public int largestVariance(String s) {
        int ans=0;
//        int[] freq=new int[26];
//        for(char c:s.toCharArray())
//        	freq[c-'a']++;
        for(char ch1='a';ch1<='z';ch1++) {
        	for(char ch2='a';ch2<='z';ch2++) {
        		if(ch1==ch2 || s.indexOf(ch1)==-1|| s.indexOf(ch2)==-1)
        			continue;
        		else {
        			for(int rotation=0;rotation<2;rotation++) {
        				int f1=0,f2=0;
        				for(char c:s.toCharArray()) {
        					if(c==ch1)
        						f1++;
        					if(c==ch2)
        						f2++;
        					if(f1<f2)
        						f1=f2=0;
        					if(f1>0 && f2>0)
        						ans=Math.max(ans, f1-f2);
        				}
        				s=new StringBuilder(s).reverse().toString();
        			}
        		}
        	}
        }
        return ans;
    }
//	Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
//
//			A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] 
//					is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
//
//			A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i],
//			nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
//
//			 
//
//			Example 1:
//
//			Input: nums = [1,-2,3,-2]
//			Output: 3
//			Explanation: Subarray [3] has maximum sum 3.
//			Example 2:
//
//			Input: nums = [5,-3,5]
//			Output: 10
//			Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
//			Example 3:
//
//			Input: nums = [-3,-2,-3]
//			Output: -2
//			Explanation: Subarray [-2] has maximum sum -2.
	//https://www.youtube.com/watch?v=Q1TYVUEr-wY
	public int maxSubarraySumCircular(int[] nums) {
        int n=nums.length,curr_max_sum=0,curr_min_sum=0,min_sum=Integer.MAX_VALUE,max_sum=Integer.MIN_VALUE,arr_sum=0;
        for(int i=0;i<n;i++) {
        	arr_sum+=nums[i];
        	
        	curr_min_sum+=nums[i];
        	min_sum=Math.min(curr_min_sum, min_sum);
        	if(curr_min_sum>0)
        		curr_min_sum=0;
        	
        	curr_max_sum+=nums[i];
        	max_sum=Math.max(curr_max_sum, max_sum);
        	if(curr_max_sum<0)
        		curr_max_sum=0;
        }
        return (arr_sum==min_sum)?max_sum:Math.max(max_sum, arr_sum-min_sum);
    }
}

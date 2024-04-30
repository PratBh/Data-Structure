package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Problems {
//	Given a string s, find the length of the longest substring without repeating characters.
//
//	 
//
//	Example 1:
//
//	Input: s = "abcabcbb"
//	Output: 3
//	Explanation: The answer is "abc", with the length of 3.
	 public int lengthOfLongestSubstring(String s) {
		 if(s==null||s.length()==0)
			 return 0;
		 int i=0,j=0,max=Integer.MIN_VALUE;
		 Set<Character> hs=new HashSet<Character>();
		 while(j<s.length()) {
			 if(!hs.contains(s.charAt(j))) {
				 hs.add(s.charAt(j));
				 j++;
				 max=Math.max(max, hs.size());
			 }else {
				 hs.remove(s.charAt(i));
				 i++;
			 }
		 }
		 return max;
	 }
	 
//	 Given an array of integers nums and an integer target.
//
//	 Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target.
//	 Since the answer may be too large, return it modulo 109 + 7.
//
//	  
//
//	 Example 1:
//
//	 Input: nums = [3,5,6,7], target = 9
//	 Output: 4
//	 Explanation: There are 4 subsequences that satisfy the condition.
//	 [3] -> Min value + max value <= target (3 + 3 <= 9)
//	 [3,5] -> (3 + 5 <= 9)
//	 [3,5,6] -> (3 + 6 <= 9)
//	 [3,6] -> (3 + 6 <= 9)
	 //https://www.youtube.com/watch?v=xCsIkPLS4Ls
	 //https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/discuss/861343/C%2B%2B-10-Lines-O(NlogN)-Time-O(N)-Space
	 
	 public int numSubseq(int[] nums, int target) {
		 if(nums==null||nums.length==0||(nums.length==1 && 2*nums[0]>target))
	    	 return 0;
        if(nums.length==1) {
	    	 return 1;
	     }
	     Arrays.sort(nums);
	     int mod=(int) (Math.pow(10, 9)+7);
	     int res=0;
	     int right=nums.length-1;
	     for(int left=0;left<nums.length;left++) {
	    	 while(nums[left]+nums[right]>target && left<=right)
	    		 right--;
	    	 if(left<=right) {
	    		 res+=Math.pow(2, (right-left));
	    		 res %=mod;
	    	 }
	     }
	     return res;
	    }
//	 Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
//
//	 Return the length of the longest (contiguous) subarray that contains only 1s. 
//
//	  
//
//	 Example 1:
//
//	 Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//	 Output: 6
//	 Explanation: 
//	 [1,1,1,0,0,1,1,1,1,1,1]
//	 Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
//	 Example 2:
//
//	 Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//	 Output: 10
//	 Explanation: 
//	 [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//	 Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
	 
	 public int longestOnes(int[] A, int K) {
	    int n=A.length;
	    if(n==0)
	    	return 0;
	    if(n==1)
	    	return 1;
	    int i=0,j=0,len=Integer.MIN_VALUE,ones=0,zeros=0;
	    while(j<n) {
	    	if(A[j]==1)
	    		ones++;
	    	else
	    		zeros++;
	    	if(zeros<=K) {
	    		len=Math.max(len, zeros+ones);
	    		j++;
	    	}else if(zeros>K) {
	    		while(zeros>K) {
	    			if(A[i]==0)
	    				zeros--;
	    			else
	    				ones--;
	    			i++;
	    		}
	    		j++;
	    	}
	    }
	    return len;
	 }
	 
//	 Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and 
//	 all those customers leave after the end of that minute.
//
//	 On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0. 
//	 When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
//
//	 The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
//
//	 Return the maximum number of customers that can be satisfied throughout the day.
//
//	  
//
//	 Example 1:
//
//	 Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
//	 Output: 16
//	 Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
//	 The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
	 
//	 fails TLE public int maxSatisfied(int[] customers, int[] grumpy, int X) {
//	    int n=grumpy.length;
//	    int sum,maxSum=Integer.MIN_VALUE;
//	    for(int i=0;i<=n-X;i++) {
//	    	sum=0;
//	    	if(i!=0) {
//	    		for(int k=0;k<i;k++) {
//	    			if(grumpy[k]==0)
//	    				sum+=customers[k];
//	    		}
//	    	}
//	    	int j=i;
//	    	while(j<n) {
//	    		if(j-i+1<=X) {
//	    			sum+=customers[j];
//	    			j++;
//	    		}
//	    		else if(j-i+1>X) {
//	    			if(grumpy[j]==0)
//	    				sum+=customers[j];
//	    			j++;
//	    		}
//	    	}
//	    	maxSum=Math.max(maxSum, sum);
//	    }
//	    return maxSum;
//	 }
	 //https://www.youtube.com/watch?v=QF1CEMgdRG4
	 public int maxSatisfied(int[] customers, int[] grumpy, int X) {
	        int sum = 0; 
	        
	        for(int i = 0; i < customers.length; i++)
	        {
	            if(grumpy[i] == 0)
	            {
	                sum+=customers[i];
	            }
	        }
	        
	        int satisfaction = sum; 
	        int start = 0; 
	        int end = start + X - 1;
	        int tempSum = 0; 
	        
	        for(int i = start; i <= end;i++)
	        {
	            if(grumpy[i] == 1)
	            {
	                tempSum+= customers[i]; 
	            }
	        }
	        
	        int maxWindowSum = tempSum; 
	        start++;
	        end++;
	        while(end < customers.length)
	        {
	            if(grumpy[start-1] == 1)
	            {
	                tempSum = tempSum - customers[start-1]; 
	            }
	            if(grumpy[end] == 1)
	            {
	                tempSum = tempSum + customers[end];
	            }
	            
	            maxWindowSum = Math.max(tempSum, maxWindowSum);
	            start++; 
	            end++; 
	        }
	        
	        return (satisfaction + maxWindowSum); 
	        
	    }
	 
//	 Given a string s and an integer k.
//
//	 Return the maximum number of vowel letters in any substring of s with length k.
//
//	 Vowel letters in English are (a, e, i, o, u).
//
//	  
//
//	 Example 1:
//
//	 Input: s = "abciiidef", k = 3
//	 Output: 3
//	 Explanation: The substring "iii" contains 3 vowel letters.
//	 Example 2:
//
//	 Input: s = "aeiou", k = 2
//	 Output: 2
//	 Explanation: Any substring of length 2 contains 2 vowels.
//	 Example 3:
//
//	 Input: s = "leetcode", k = 3
//	 Output: 2
//	 Explanation: "lee", "eet" and "ode" contain 2 vowels.
//	 Example 4:
//
//	 Input: s = "rhythms", k = 4
//	 Output: 0
//	 Explanation: We can see that s doesn't have any vowel letters.
//	 Example 5:
//
//	 Input: s = "tryhard", k = 4
//	 Output: 1
	 
	 public int maxVowels(String s, int k) {
	     if(s.length()==0||k==0)
	    	 return 0;
	     Set<Character> vowels=new HashSet<Character>();
	     vowels.add('a');
	     vowels.add('e');
	     vowels.add('i');
	     vowels.add('o');
	     vowels.add('u');
	     int i=0,j=0,ans=0,maxLen=0;
	     while(j<s.length()) {
	    	 if(vowels.contains(s.charAt(j)))
	    		 ans++;
	    	 if(j-i+1<k)
	    		 j++;
	    	 else if(j-i+1==k) {
	    		 maxLen=Math.max(ans, maxLen);
	    		 if(vowels.contains(s.charAt(i)))
	    			 ans--;
	    		 i++;
	    		 j++;
	    	 }
	     }
	     return maxLen;
	 }
	 
//	 Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
//
//	 In one operation, you can choose any character of the string and change it to any other uppercase English character.
//
//	 Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
//
//	 Note:
//	 Both the string's length and k will not exceed 104.
//
//	 Example 1:
//
//	 Input:
//	 s = "ABAB", k = 2
//
//	 Output:
//	 4
//
//	 Explanation:
//	 Replace the two 'A's with two 'B's or vice versa.
	 //https://leetcode.com/problems/longest-repeating-character-replacement/discuss/971458/O(N)-%2B-Sliding-Window-%2B-Simple-with-explanation-%2B-Kotlin-solution
//	 The idea here is to find a window that satisfies the condition -
//	 count of most repeatable character + no. of allowed replacements <= length of the window
//	 Since the no. of allowed replacements is fixed, then the window size is directly proportional to the count of the most repeating character.
//	 Initially the window keeps growing from the end, until all the allowed replacements are added up in the window until it reaches the max size.
//	 The moment the condition is not satisfied (i.e., count of most repeatable character + no. of allowed replacements > size of the window), then we need to slide the window (not shrink) to the right and decrement the frequency of the character that is moved out of the window.
//	 If the next character coming in is the most repeating character, then the window grows or else it simply slides again.
	 public int characterReplacement(String s, int k) {
		if(s.length()==0)
		return 0;
	Map<Character,Integer> mp=new HashMap<Character,Integer>();
	int i=0,j=0,len=Integer.MIN_VALUE,mostFreqCharCount=0;
	while(j<s.length()) {
		mp.put(s.charAt(j),mp.getOrDefault(s.charAt(j),0)+1);
		mostFreqCharCount=Math.max(mostFreqCharCount, mp.get(s.charAt(j)));
		if(j-i+1<=mostFreqCharCount+k){
			len=Math.max(len, j-i+1);
			j++;
		}
		else if(j-i+1>mostFreqCharCount+k) {
			mp.put(s.charAt(i), mp.get(s.charAt(i))-1);
			if(mp.get(s.charAt(i))==0)
				mp.remove(s.charAt(i));
			i++;j++;
		}

	}
	return len;
	 }
	 
//	 Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
//
//			 A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
//
//			 More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
//
//			 For i <= k < j:
//			 arr[k] > arr[k + 1] when k is odd, and
//			 arr[k] < arr[k + 1] when k is even.
//			 Or, for i <= k < j:
//			 arr[k] > arr[k + 1] when k is even, and
//			 arr[k] < arr[k + 1] when k is odd.
//			  
//
//			 Example 1:
//
//			 Input: arr = [9,4,2,10,7,8,8,1,9]
//			 Output: 5
//			 Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
//			 Example 2:
//
//			 Input: arr = [4,8,12,16]
//			 Output: 2
//			 Example 3:
//
//			 Input: arr = [100]
//			 Output: 1
	//https://leetcode.com/problems/longest-turbulent-subarray/solution/ 
	 public int maxTurbulenceSize(int[] arr) {
	        int n=arr.length;
	        int i=0,j=1,ans=1;
	        while(j<n) {
	        	int c=Integer.compare(arr[j-1], arr[j]);
	        	if(c==0)
	        		i=j;
	        	else {
	        		if(j==n-1||c*Integer.compare(arr[j], arr[j+1])!=-1) {
	        			ans=Math.max(ans, j-i+1);
	        			i=j;
	        		}
	        	}
	        	j++;
	        }
	        return ans;
	  }
	 
//	 There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
//
//	 In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
//
//	 Your score is the sum of the points of the cards you have taken.
//
//	 Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
//
//	  
//
//	 Example 1:
//
//	 Input: cardPoints = [1,2,3,4,5,6,1], k = 3
//	 Output: 12
//	 Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. 
//	 The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
//	 Hints
//	 Let the sum of all points be total_pts. You need to remove a sub-array from cardPoints with length n - k.
//	 Keep a window of size n - k over the array. The answer is max(answer, total_pts - sumOfCurrentWindow)
	 
	 public int maxScore(int[] cardPoints, int k) {
	        int n=cardPoints.length,i=0,j=0,ans=Integer.MIN_VALUE,totalSum=0,currWindowSum=0;
	        for(int l=0;l<n;l++)
	        	totalSum+=cardPoints[l];
	        if(k==n)
	        	return totalSum;
	        while(j<n) {
	        	currWindowSum+=cardPoints[j];
	        	if(j-i+1<(n-k))
	        		j++;
	        	else if(j-i+1==(n-k)) {
	        		ans=Math.max(ans, totalSum-currWindowSum);
	        		currWindowSum-=cardPoints[i];
	        		i++;
	        		j++;
	        	}
	        }
	        return ans;
	 }
	 
//	 Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
//			 In other words, one of the first string's permutations is the substring of the second string.
//
//			 
//
//			 Example 1:
//
//			 Input: s1 = "ab" s2 = "eidbaooo"
//			 Output: True
//			 Explanation: s2 contains one permutation of s1 ("ba").
//			 Example 2:
//
//			 Input:s1= "ab" s2 = "eidboaoo"
//			 Output: False
	 
	 public boolean checkInclusion(String s1, String s2) {
	     int k=s1.length(),n=s2.length(),i=0,j=0;
	     Map<Character,Integer> mp=new HashMap<Character, Integer>();
	     for(char c:s1.toCharArray()){
			 mp.put(c,mp.getOrDefault(c,0)+1);
		 }
	     int count =mp.size();
	     while(j<n) {
	    	 if(mp.containsKey(s2.charAt(j))) {
	    		 mp.put(s2.charAt(j),mp.get(s2.charAt(j))-1);
	    		 if(mp.get(s2.charAt(j))==0)
	    			 count--;
	    		 if(count==0)
	    			 return true;
	    	 }
	    		 if(j-i+1<k)
	    			 j++;
	    		 else if(j-i+1==k) {
	    			 if(mp.containsKey(s2.charAt(i))) {
	    				 if(mp.get(s2.charAt(i))==0)
	    					 count++;
	    				 mp.put(s2.charAt(i),mp.get(s2.charAt(i))+1);
	    			 }
	    			 i++;
	    			 j++;
	    		 }
	    	 }
	     return false;
	 }
	 
//	 Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between
//			 any two elements of this subarray is less than or equal to limit.
//
//			 
//
//			 Example 1:
//
//			 Input: nums = [8,2,4,7], limit = 4
//			 Output: 2 
//			 Explanation: All subarrays are: 
//			 [8] with maximum absolute diff |8-8| = 0 <= 4.
//			 [8,2] with maximum absolute diff |8-2| = 6 > 4. 
//			 [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//			 [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//			 [2] with maximum absolute diff |2-2| = 0 <= 4.
//			 [2,4] with maximum absolute diff |2-4| = 2 <= 4.
//			 [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//			 [4] with maximum absolute diff |4-4| = 0 <= 4.
//			 [4,7] with maximum absolute diff |4-7| = 3 <= 4.
//			 [7] with maximum absolute diff |7-7| = 0 <= 4. 
//			 Therefore, the size of the longest subarray is 2.
			 
	 public int longestSubarray(int[] nums, int limit) {
	        ArrayDeque<Integer> max = new ArrayDeque<>(); // monotonic decrease
	        ArrayDeque<Integer> min = new ArrayDeque<>(); // monotonic increase
	        int n = nums.length;
	        max.offer(nums[0]);
	        min.offer(nums[0]);
	        int ans = 1;
	        int i = 0;
	        for (int j = 1; j < n; j++) {
	            while (!max.isEmpty() && max.peekLast() < nums[j]) {
	                max.pollLast();
	            }
	            max.offer(nums[j]);
	            while (!min.isEmpty() && min.peekLast() > nums[j]) {
	                min.pollLast();
	            }
	            min.offer(nums[j]);
	            while (max.peekFirst() - min.peekFirst() > limit) {
	                if (max.peekFirst() == nums[i]) {
	                    max.pollFirst();
	                }
	                if (min.peekFirst() == nums[i]) {
	                    min.pollFirst();
	                }
	                i++;
	            }
	            ans = Math.max(ans, j - i + 1);
	        }
	        return ans;
	    }
	 
//	 You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs 
//	 |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
//
//	 You are also given an integer maxCost.
//
//	 Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost less than or equal to
//	 maxCost.
//
//	 If there is no substring from s that can be changed to its corresponding substring from t, return 0.
//
//	  
//
//	 Example 1:
//
//	 Input: s = "abcd", t = "bcdf", maxCost = 3
//	 Output: 3
//	 Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
//	 Example 2:
//
//	 Input: s = "abcd", t = "cdef", maxCost = 3
//	 Output: 1
//	 Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.
	 //https://leetcode.com/problems/get-equal-substrings-within-budget/discuss/915412/C%2B%2B-Sliding-Window-O(n)-with-explanation
	 
	 public int equalSubstring(String s, String t, int maxCost) {
		 int n=t.length(),i=0,j=0,len=Integer.MIN_VALUE,sum=0;
		 int[]cost=new int[n];
		 for(int l=0;l<n;l++)
			 cost[l]=Math.abs(s.charAt(l)-t.charAt(l));
		 while(j<n) {
			 sum+=cost[j];
			 if(sum<=maxCost) {
				 len=Math.max(len, j-i+1);
				 j++;
			 }else {
				 sum-=cost[i];
				 i++;
				 j++;
			 }
		 }
		 return len;
	 }
	 
//	 Given a string s and an integer k, return the length of the longest substring of s such that the frequency
	 //of each character in this substring is greater than or equal to k.
//
//			 
//
//			 Example 1:
//
//			 Input: s = "aaabb", k = 3
//			 Output: 3
//			 Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
//			 Example 2:
//
//			 Input: s = "ababbc", k = 2
//			 Output: 5
//			 Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
	 //https://www.youtube.com/watch?v=YSQVDVRQyA4
	public int longestSubstring(String s, int k) {
		Map<Character,Integer> mp=new HashMap<Character, Integer>();
	for (char c:s.toCharArray())
		mp.put(c,mp.getOrDefault(c,0)+1);
	int start = 0;boolean valid = true; int maxLength = Integer.MIN_VALUE;
	for(int end=0;end<s.length();end++){
		if(mp.get(s.charAt(end))<k){
			maxLength = Math.max(maxLength,longestSubstring(s.substring(start,end),k));
			start = end+1;
			valid = false;
		}
	}
	if(valid)
		return s.length();
	else
		return Math.max(maxLength,longestSubstring(s.substring(start),k));
}
	 
//	 Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. 
//	 If there isn't one, return 0 instead.
//
//	 Example: 
//
//	 Input: s = 7, nums = [2,3,1,2,4,3]
//	 Output: 2
//	 Explanation: the subarray [4,3] has the minimal length under the problem constraint.
	 
	 public int minSubArrayLen(int s, int[] nums) {
	 int i=0,sum=0,minLen=Integer.MAX_VALUE,j=0;
	 while(j<nums.length) {
		 sum+=nums[j];
		 while(sum>=s) {
			 minLen=Math.min(minLen,j-i+1);
			 sum-=nums[i];
			 i++;
		 }
		 j++;
	 }
	 return minLen==Integer.MAX_VALUE?0:minLen;
	 }
	 
//	 Your are given an array of positive integers nums.
//
//	 Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
//
//	 Example 1:
//	 Input: nums = [10, 5, 2, 6], k = 100
//	 Output: 8
//	 Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
//	 Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
//	 Note:
//
//	 0 < nums.length <= 50000.
//	 0 < nums[i] < 1000.
//	 0 <= k < 10^6.
	 
	 public int numSubarrayProductLessThanK(int[] nums, int k) {
		 if(k<=1)
             return 0;
	  int i=0,j=0,prod=1,count=0;
	  while(j<nums.length) {
		  prod=prod*nums[j];
		  while(prod>=k) {
			  prod=prod/nums[i];
			  i++;
		  }
		  count+=j-i+1;
		  j++;
	  }
	  return count;
	 }
	//	You are given a string s and an array of strings words. All the strings of words are of the same length.
//
//	A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.
//
//	For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
//	Return the starting indices of all the concatenated substrings in s. You can return the answer in any order.
//
//
//
//			Example 1:
//
//	Input: s = "barfoothefoobarman", words = ["foo","bar"]
//	Output: [0,9]
//	Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
//	The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
//	The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
//	The output order does not matter. Returning [9,0] is fine too.

	public List<Integer> findSubstring(String s, String[] words) {
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
	 
}


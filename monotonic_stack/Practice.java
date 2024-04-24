package monotonic_stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Practice {
	
//	have to find out its next greater number which contains the same digit
//	e.g 
//	123 -> 132
//	4132->4213
//	21-> -1 as 12 is not greater than 21.
	//https://www.youtube.com/watch?v=yNMIyHZj3bk&t=576
	//https://leetcode.com/problems/next-greater-element-iii/description/
	//https://leetcode.com/problems/next-greater-element-iii/solutions/2597736/0ms-java-solution-100-with-explanation/
	int nextGreaterNuber(int numb) {
		String st = Integer.toString(numb) ;
		int n = st.length(),i=n-2;
		StringBuilder ans = new StringBuilder();
		while(i>=0 && ((st.charAt(i)-'0')>=(st.charAt(i+1)-'0')))
			i--;
		if(i==-1)
			return -1;
		int k=n-1;
		while((st.charAt(i)-'0')>=(st.charAt(k)-'0'))
			k--;
		char temp = st.charAt(k);
		char[] chars = st.toCharArray();
		chars[k]=st.charAt(i);
		chars[i]=temp;
		for(int j=0;j<=i;j++)
			ans.append(chars[j]);
		
		for(int j=n-1;j>i;j--)
			ans.append(chars[j]);
		
		long ans_ = Long.parseLong(ans.toString());
        
        return (ans_ > Integer.MAX_VALUE) ? -1 : (int)ans_;
	}
	
//	The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
//
//	You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
//
//	For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of 
//	nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
//
//	Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
	
//	Example 1:
//
//		Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
//		Output: [-1,3,-1]
//		Explanation: The next greater element for each value of nums1 is as follows:
//		- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
//		- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
//		- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
//		Example 2:
//
//		Input: nums1 = [2,4], nums2 = [1,2,3,4]
//		Output: [3,-1]
//		Explanation: The next greater element for each value of nums1 is as follows:
//		- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
//		- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
	//https://www.youtube.com/watch?v=68a1Dc_qVq4
	//this solution is not optimum.its order is O(nums1.length*nums2.length)
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer,Integer> mp = new HashMap<Integer, Integer>();
        int[] ans = new int[nums1.length];
        Arrays.fill(ans, -1);
        for(int i=0;i<nums1.length;i++) {
        	mp.put(nums1[i], i);// 4-0,1-1,2-2
      	}
        //this solution is of O(nums1.length+nums2.length)
        Stack<Integer> st = new Stack<Integer>();
        for(int i=0;i<nums2.length;i++) {
        	if(st.isEmpty()) {
        		if(mp.containsKey(nums2[i])) {
            		st.push(nums2[i]);
            	}
        	}
        	else {
        		while(!st.isEmpty()&&st.peek()<nums2[i]) {
        			int idx = mp.get(st.peek());
        			ans[idx]=nums2[i];
        			st.pop();
        		}
        		if(mp.containsKey(nums2[i]))
            		st.push(nums2[i]);
        	}

        }
//        
        
      //this solution is not optimum.its order is O(nums1.length*nums2.length)
//        for(int i=0;i<nums2.length;i++) {
//        	if(mp.containsKey(nums2[i])) {
//        		for(int j=i+1;j<nums2.length;j++) {
//        			if(nums2[i]<nums2[j]) {
//        				int idx = mp.get(nums2[i]);
//            			ans[idx]=nums2[j];
//            			break;
//        			}
//        		}
//        	}
//        }
        return ans;
    }
	
//	Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
//
//			The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search 
//			circularly to find its next greater number. If it doesn't exist, return -1 for this number.
//
//			 
//
//			Example 1:
//
//			Input: nums = [1,2,1]
//			Output: [2,-1,2]
//			Explanation: The first 1's next greater number is 2; 
//			The number 2 can't find next greater number. 
//			The second 1's next greater number needs to search circularly, which is also 2.
//			Example 2:
//
//			Input: nums = [1,2,3,4,3]
//			Output: [2,3,4,-1,4]
//			 
//
//			Constraints:
//
//			1 <= nums.length <= 104
//			-109 <= nums[i] <= 109
	//https://www.youtube.com/watch?v=ARkl69eBzhY
	 	public int[] nextGreaterElements(int[] nums) {
	 		int n= nums.length;
	        int[] ans = new int[n];
	        Arrays.fill(ans, -1);
	        Stack<Integer> st = new Stack<Integer>();
	        for(int i=0;i<n*2;i++) {
	        	while(!st.isEmpty() && nums[st.peek()]<nums[i%n]) {
	        		ans[st.pop()]=nums[i%n];
	        	}

	        	if(i<n)st.push(i);
	        }
	        return ans;
	    }
//	 	Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the 
//	 			number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible,
//	 			keep answer[i] == 0 instead.
//
//	 			 
//
//	 			Example 1:
//
//	 			Input: temperatures = [73,74,75,71,69,72,76,73]
//	 			Output: [1,1,4,2,1,1,0,0]
//	 			Example 2:
//
//	 			Input: temperatures = [30,40,50,60]
//	 			Output: [1,1,1,0]
//	 			Example 3:
//
//	 			Input: temperatures = [30,60,90]
//	 			Output: [1,1,0]
	 	
	 	public int[] dailyTemperatures(int[] temperatures) {
	 		int n= temperatures.length;
	        int[] ans = new int[n];
	        //Arrays.fill(ans, -1);
	        Stack<Integer> st = new Stack<Integer>();
	        for(int i=0;i<n;i++) {
	        	while(!st.isEmpty() && temperatures[st.peek()]<temperatures[i]) {
	        		int top = st.pop();
	        		ans[top]= i-top;
	        	}
	        	if(i<n)st.push(i);
	        }
	        return ans;
	    }
	 	
//	 	You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
//
//	 	Return the sum of all subarray ranges of nums.
//
//	 	A subarray is a contiguous non-empty sequence of elements within an array.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: nums = [1,2,3]
//	 	Output: 4
//	 	Explanation: The 6 subarrays of nums are the following:
//	 	[1], range = largest - smallest = 1 - 1 = 0 
//	 	[2], range = 2 - 2 = 0
//	 	[3], range = 3 - 3 = 0
//	 	[1,2], range = 2 - 1 = 1
//	 	[2,3], range = 3 - 2 = 1
//	 	[1,2,3], range = 3 - 1 = 2
//	 	So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
//	 	Example 2:
//
//	 	Input: nums = [1,3,3]
//	 	Output: 4
//	 	Explanation: The 6 subarrays of nums are the following:
//	 	[1], range = largest - smallest = 1 - 1 = 0
//	 	[3], range = 3 - 3 = 0
//	 	[3], range = 3 - 3 = 0
//	 	[1,3], range = 3 - 1 = 2
//	 	[3,3], range = 3 - 3 = 0
//	 	[1,3,3], range = 3 - 1 = 2
//	 	So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
//	 	Example 3:
//
//	 	Input: nums = [4,-2,-3,4,1]
//	 	Output: 59
//	 	Explanation: The sum of all subarray ranges of nums is 59.
//	 	 
//
//	 	Constraints:
//
//	 	1 <= nums.length <= 1000
//	 	-109 <= nums[i] <= 109
	 	
	 	//this is of o(n^2)
	 	public long subArrayRanges(int[] nums) {
	        int n=nums.length;
	        long sum =0;
	        if(n==1)
	        	return sum;
	        for(int start = 0;start<n-1;start++) {
	        	long max=nums[start],min=nums[start];
	        	for(int end = start+1;end<n;end++) {
	        		max = Math.max(max, nums[end]);
	        		min = Math.min(min, nums[end]);
	        		sum+=(max-min);
	        	}
	        }
	        return sum;
	    }
	 	
	 	//https://leetcode.com/problems/sum-of-subarray-ranges/solutions/2413551/2-monotonic-stack-easy-to-understand-plus-a-lot-of-explanations/
	 	public long subArrayRangesStack(int[] nums) {
	 		long result = 0;
	 		Stack<Integer> inc = new Stack<Integer>();//monotonic decreasing stack for next and prev greater amount
	 		Stack<Integer> dec = new Stack<Integer>();//monotonic increasing stack for next and prev lesser amount
	 		for(int i=0;i<=nums.length;i++) {
	 		            while(!inc.isEmpty() && ( i == nums.length || nums[inc.peek()] < nums[i])){
	 		                int currIndex = inc.pop();
	 		                int leftBound = inc.isEmpty() ? -1 : inc.peek(); 
	 		                int rightBound = i;
	 		                result += (currIndex - leftBound) * (rightBound - currIndex) * (long) nums[currIndex];                
	 		            }
	 		            while(!dec.isEmpty() && (i == nums.length || nums[dec.peek()] > nums[i])){
	 		                int currIndex = dec.pop();
	 		                int leftBound = dec.isEmpty()? -1 : dec.peek(); 
	 		                int rightBound = i;
	 		                result -= (currIndex - leftBound) * (rightBound - currIndex) * (long) nums[currIndex];
	 		            }
	 		            inc.push(i);
	 		            dec.push(i);
	 		}
	 		return result;
	 	}
	 	
//	 	Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, 
//	 	return the answer modulo 109 + 7.
//
//	 			 
//
//	 			Example 1:
//
//	 			Input: arr = [3,1,2,4]
//	 			Output: 17
//	 			Explanation: 
//	 			Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
//	 			Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//	 			Sum is 17.
//	 			Example 2:
//
//	 			Input: arr = [11,81,94,43,3]
//	 			Output: 444
//	 			 
//
//	 			Constraints:
//
//	 			1 <= arr.length <= 3 * 104
//	 			1 <= arr[i] <= 3 * 104
	 	//https://leetcode.com/problems/sum-of-subarray-minimums/solutions/2118729/very-detailed-stack-explanation-o-n-images-comments/
	 	public int sumSubarrayMins(int[] arr) {
	        long res =0;
	        Stack<Integer> st =new Stack<Integer>();
	        st.push(-1);
	        long M = (long)1e9+7;
	        for(int i2=0;i2<arr.length+1;i2++) {
	        	int currVal = (i2<arr.length)? arr[i2] : 0;
	        	while(st.peek()!= -1 && currVal<arr[st.peek()]) {
	        		int idx = st.pop();
	        		int i1 = st.peek();
	        		int left = idx-i1;
	        		int right = i2-idx;
	        		long add = (long)(left*right*(long)arr[idx]) % M;
	        		res+=add;
	        		res%=M;
	        	}
	        	st.push(i2);
	        }
	        return (int) res;
	    }
	 	
	 	public int sumSubarrayMax(int[] arr) {
	        long res =0;
	        Stack<Integer> st =new Stack<Integer>();
	        st.push(-1);
	        long M = (long)1e9+7;
	        for(int i2=0;i2<arr.length+1;i2++) {
	        	int currVal = (i2<arr.length)? arr[i2] : 0;
	        	while(st.peek()!= -1 && currVal>arr[st.peek()]) {
	        		int idx = st.pop();
	        		int i1 = st.peek();
	        		int left = idx-i1;
	        		int right = i2-idx;
	        		long add = (long)(left*right*(long)arr[idx]) % M;
	        		res+=add;
	        		res%=M;
	        	}
	        	st.push(i2);
	        }
	        return (int) res;
	    }
//	 	Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.
//
//	 			A string is homogenous if all the characters of the string are the same.
//
//	 			A substring is a contiguous sequence of characters within a string.
//
//	 			 
//
//	 			Example 1:
//
//	 			Input: s = "abbcccaa"
//	 			Output: 13
//	 			Explanation: The homogenous substrings are listed as below:
//	 			"a"   appears 3 times.
//	 			"aa"  appears 1 time.
//	 			"b"   appears 2 times.
//	 			"bb"  appears 1 time.
//	 			"c"   appears 3 times.
//	 			"cc"  appears 2 times.
//	 			"ccc" appears 1 time.
//	 			3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
//	 			Example 2:
//
//	 			Input: s = "xy"
//	 			Output: 2
//	 			Explanation: The homogenous substrings are "x" and "y".
//	 			Example 3:
//
//	 			Input: s = "zzzzz"
//	 			Output: 15
	 	
	 	 public int countHomogenous(String s) {
	 		 if(s.length()==1)
	 			 return 1;
	         long res =0,M = (long)1e9+7;
	         char[] chars = s.toCharArray();
	         long start=0,appear =0;
	         for(long i=1;i<chars.length+1;i++) {
	        	 if((i==chars.length)||(chars[(int) start]!=chars[(int) i])) {
	        		 appear = i-start;
	        		 long add = findSumOfNaturalNumber(appear) % M;
	        		 res+= add;
	        		 res%=M;
	        		 start=i;
	        	 }
	         }
	         return (int) res;
	     }
	 	 
	 	 long findSumOfNaturalNumber(long n) {
	 		 if(n==1)
	 			 return 1;
	 		 return (n*(n+1))/2;
	 	 }
	 	 
//	 	There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. You are given an array heights of 
//	 	distinct integers where heights[i] represents the height of the ith person.
//
//	 	A person can see another person to their right in the queue if everybody in between is shorter than both of them. More formally, 
//	 	the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
//
//	 	Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.
//
//	 	 
//
//	 	Example 1:
//
//
//
//	 	Input: heights = [10,6,8,5,11,9]
//	 	Output: [3,1,2,1,1,0]
//	 	Explanation:
//	 	Person 0 can see person 1, 2, and 4.
//	 	Person 1 can see person 2.
//	 	Person 2 can see person 3 and 4.
//	 	Person 3 can see person 4.
//	 	Person 4 can see person 5.
//	 	Person 5 can see no one since nobody is to the right of them.
//	 	Example 2:
//
//	 	Input: heights = [5,1,2,3,10]
//	 	Output: [4,1,1,1,0]
//	 	 
//
//	 	Constraints:
//
//	 	n == heights.length
//	 	1 <= n <= 105
//	 	1 <= heights[i] <= 105
//	 	All the values of heights are unique.
	 	 
	 	 //this is a brute force approach o(n^2)
	 	public int[] canSeePersonsCount(int[] heights) {
	        int n= heights.length;
	        int[] res = new int[n];
	        if(n==1)
	        	return res;
	        for(int i=0;i<n-1;i++) {
	        	int count =0,curr_next=0;
	        	for(int j=i+1;j<n;j++) {
	        		if(j==i+1) {
	        			count++;
	        			curr_next=heights[j];
	        		}else if(heights[i]<curr_next)
	        			break;
	        		else {
	        			if(curr_next<heights[j])
	        				count++;
	        			curr_next=Math.max(curr_next, heights[j]);
	        		}
	        	}
        		res[i]=count;
	        }
	        return res;
	    }
	 	
	 	//https://leetcode.com/problems/number-of-visible-people-in-a-queue/solutions/3424402/number-of-visible-people-in-a-queue-using-monotonic-stack-in-c-detailed-explanation/
	 	public int[] canSeePersonsCountStack(int[] heights) {
	        int n= heights.length;
	        int[] res = new int[n];
	        if(n==1)
	        	return res;
	        Stack<Integer> st = new Stack<Integer>();
	        st.push(heights[n-1]);
	        for(int i=n-2;i>=0;i--) {
	        	int num=0;
	        	while(!st.isEmpty() && heights[i]>st.peek()) {
	        		st.pop();
	        		num++;
	        	}
	        	if(!st.isEmpty())
	        		num++;
	        	st.push(heights[i]);
	        	res[i]=num;
	        }
	        return res;
	    }
	 	
}

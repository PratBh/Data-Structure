package LeetCode_contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code {
//	You are given a 0-indexed integer array batteryPercentages having length n, denoting the battery percentages of n 0-indexed devices.
//
//	Your task is to test each device i in order from 0 to n - 1, by performing the following test operations:
//
//	If batteryPercentages[i] is greater than 0:
//	Increment the count of tested devices.
//	Decrease the battery percentage of all devices with indices j in the range [i + 1, n - 1] by 1, ensuring their battery percentage never goes below 0, i.e, batteryPercentages[j] = max(0, batteryPercentages[j] - 1).
//	Move to the next device.
//	Otherwise, move to the next device without performing any test.
//	Return an integer denoting the number of devices that will be tested after performing the test operations in order.
//
//	 
//
//	Example 1:
//
//	Input: batteryPercentages = [1,1,2,1,3]
//	Output: 3
//	Explanation: Performing the test operations in order starting from device 0:
//	At device 0, batteryPercentages[0] > 0, so there is now 1 tested device, and batteryPercentages becomes [1,0,1,0,2].
//	At device 1, batteryPercentages[1] == 0, so we move to the next device without testing.
//	At device 2, batteryPercentages[2] > 0, so there are now 2 tested devices, and batteryPercentages becomes [1,0,1,0,1].
//	At device 3, batteryPercentages[3] == 0, so we move to the next device without testing.
//	At device 4, batteryPercentages[4] > 0, so there are now 3 tested devices, and batteryPercentages stays the same.
//	So, the answer is 3.
//	Example 2:
//
//	Input: batteryPercentages = [0,1,2]
//	Output: 2
//	Explanation: Performing the test operations in order starting from device 0:
//	At device 0, batteryPercentages[0] == 0, so we move to the next device without testing.
//	At device 1, batteryPercentages[1] > 0, so there is now 1 tested device, and batteryPercentages becomes [0,1,1].
//	At device 2, batteryPercentages[2] > 0, so there are now 2 tested devices, and batteryPercentages stays the same.
//	So, the answer is 2.
	public int countTestedDevices(int[] batteryPercentages) {
        int count =0;
        for(int i=0;i<batteryPercentages.length;i++) {
        	if(batteryPercentages[i]==0)
        		continue;
        	else {
        		batteryPercentages[i]=Math.max(0, batteryPercentages[i]-count);
        		if(batteryPercentages[i]!= 0)
        			count++;
        	}
        }
        return count;
    }
	
	//	You are given a 0-indexed 2D array variables where variables[i] = [ai, bi, ci, mi], and an integer target.
	//
	//			An index i is good if the following formula holds:
	//
	//			0 <= i < variables.length
	//			((aibi % 10)ci) % mi == target
	//			Return an array consisting of good indices in any order.
	//
	//			 
	//
	//			Example 1:
	//
	//			Input: variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
	//			Output: [0,2]
	//			Explanation: For each index i in the variables array:
	//			1) For the index 0, variables[0] = [2,3,3,10], (23 % 10)3 % 10 = 2.
	//			2) For the index 1, variables[1] = [3,3,3,1], (33 % 10)3 % 1 = 0.
	//			3) For the index 2, variables[2] = [6,1,1,4], (61 % 10)1 % 4 = 2.
	//			Therefore we return [0,2] as the answer.
	//			Example 2:
	//
	//			Input: variables = [[39,3,1000,1000]], target = 17
	//			Output: []
	//			Explanation: For each index i in the variables array:
	//			1) For the index 0, variables[0] = [39,3,1000,1000], (393 % 10)1000 % 1000 = 1.
	//			Therefore we return [] as the answer.
	
	public List<Integer> getGoodIndices(int[][] variables, int target) {
		List<Integer> ans = new ArrayList<>();
	    for (int i = 0; i < variables.length; ++i) {
	      final int a = variables[i][0];
	      final int b = variables[i][1];
	      final int c = variables[i][2];
	      final int m = variables[i][3];
	      if (modPow(modPow(a, b, 10), c, m) == target)
	        ans.add(i);
	    }
	    return ans;
    }
	
	private int modPow(int x,int n,int mod) {
		if(n==0)
			return 1;
		if(n%2==1)
			return x* modPow(x%mod, n-1, mod)%mod;
		else
			return modPow(x*x,n-2,mod)%mod;
	}
	
//	You are given an integer array nums and a positive integer k.
//
//	Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
//
//	A subarray is a contiguous sequence of elements within an array.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,3,2,3,3], k = 2
//	Output: 6
//	Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
//	Example 2:
//
//	Input: nums = [1,4,2,1], k = 3
//	Output: 0
//	Explanation: No subarray contains the element 4 at least 3 times.
//	 
//
//	Constraints:
//
//	1 <= nums.length <= 105
//	1 <= nums[i] <= 106
//	1 <= k <= 105
	//https://walkccc.me/LeetCode/problems/2962/#__tabbed_1_2
	public long countSubarrays(int[] nums, int k) {
        int maxElement = findMax(nums), i=0, j=0,maxCount=0;
        long count=0;
        while(j<nums.length) {
        	if(nums[j]==maxElement) {
        		maxCount++;
        	}
        	//if(maxCount==k) {
        		while(maxCount==k) {
        			if(nums[i]==maxElement)
        				maxCount--;
        			i++;
        		}
        		count+=i;
        	//}
        	j++;
        }
        return count;
        
    }
	
	private int findMax(int[]nums) {
		int max=Integer.MIN_VALUE;
		for(int i:nums)
			max=Math.max(max, i);
		return max;
	}
	
//	You are given a 0-indexed array nums consisting of positive integers.
//
//	A partition of an array into one or more contiguous subarrays is called good if no two subarrays contain the same number.
//
//	Return the total number of good partitions of nums.
//
//	Since the answer may be large, return it modulo 109 + 7.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,2,3,4]
//	Output: 8
//	Explanation: The 8 possible good partitions are: ([1], [2], [3], [4]), ([1], [2], [3,4]), ([1], [2,3], [4]), ([1], [2,3,4]), 
//	([1,2], [3], [4]), ([1,2], [3,4]), ([1,2,3], [4]), and ([1,2,3,4]).
//	Example 2:
//
//	Input: nums = [1,1,1,1]
//	Output: 1
//	Explanation: The only possible good partition is: ([1,1,1,1]).
//	Example 3:
//
//	Input: nums = [1,2,1,3]
//	Output: 2
//	Explanation: The 2 possible good partitions are: ([1,2,1], [3]) and ([1,2,1,3]).
	
	//https://www.youtube.com/watch?v=XfzjyBIBcTE
	public int numberOfGoodPartitions(int[] nums) {
        Map<Integer,Integer> last_index = new HashMap<Integer, Integer>();
        int mod = 100000007;
        for(int i=0;i<nums.length;i++)
        	last_index.put(nums[i], i);
        int p1=0,p2=0,partitions=0;
        while(p1<nums.length) {
        	p2=Math.max(p2, last_index.get(nums[p1]));
        	if(p1==p2)
        		partitions++;
        	p1++;
        }
        return pow(2, partitions-1, mod);
    }
	
	int pow(int x,int n,int mod) {
		long res = 1;
		while(n>0) {
			if((n&1)!=0) {
				res = (res*(long)x)%mod;
			}
			x=(int) (((long)x*x)%mod);
			n=n>>1;
		}
		return (int)res;
	}
	
	
	
	
	//modulo operator
	//https://www.youtube.com/watch?v=CnPreli2F-E
	
	int fastPower(int x,int n) {
		int res=1;
		while(n>0) {
			if((n &1) != 0) // and ing 1 with an even number will always give 0 because last bit of an evn number is always 0.So and ing 1 
				//with odd number will give 1 as last bit of an odd number is 1 
				res*=x;
			x=x*x;
			/*
			 * Shifting 1 bit right makes a number half.eg. 4>>1 is 2.(4)10 = 100
			 * Shifting 1bit of 100 to right makes it 010 which is actually 2.Same way
			 * Shifting a bit left doubles the number.eg. 5= 101. nod 5<<1 is 1010 which
			 * is 10
			 * */
			n=(n>>1); 
		}
		return res;
	}
}

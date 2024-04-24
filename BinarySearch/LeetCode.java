package BinarySearch;

public class LeetCode {
//	Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, 
//			return the index where it would be if it were inserted in order.
//
//			You must write an algorithm with O(log n) runtime complexity.
//
//			 
//
//			Example 1:
//
//			Input: nums = [1,3,5,6], target = 5
//			Output: 2
//			Example 2:
//
//			Input: nums = [1,3,5,6], target = 2
//			Output: 1
//			Example 3:
//
//			Input: nums = [1,3,5,6], target = 7
//			Output: 4
//			 
//
//			Constraints:
//
//			1 <= nums.length <= 104
//			-104 <= nums[i] <= 104
//			nums contains distinct values sorted in ascending order.
//			-104 <= target <= 104
	
	public int searchInsert(int[] nums, int target) {
        int n=nums.length,start=0,end=n-1;
        while(start<=end) {
        	int mid=start+(end-start)/2;
        	if(nums[mid]==target)
        		return mid;
        	if(nums[mid]>target)
        		end=mid-1;
        	else
        		start=mid+1;
        }
        return -1;
    }
	
//	You are given an m x n integer matrix matrix with the following two properties:
//
//		Each row is sorted in non-decreasing order.
//		The first integer of each row is greater than the last integer of the previous row.
//		Given an integer target, return true if target is in matrix or false otherwise.
//
//		You must write a solution in O(log(m * n)) time complexity.
//
//		 
//
//		Example 1:
//
//
//		Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//		Output: true
//		Example 2:
//
//
//		Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//		Output: false
	public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length,n=matrix[0].length,i=m-1,j=0;
        while(i>=0&&i<m&&j>=0&&j<n) {
        	if(matrix[i][j]==target)
        		return true;
        	if(matrix[i][j]>target)
        		i--;
        	else
        		j++;
        }
        return false;
    }
	
//	A peak element is an element that is strictly greater than its neighbors.
//
//	Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
//			return the index to any of the peaks.
//
//	You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater 
//	than a neighbor that is outside the array.
//
//	You must write an algorithm that runs in O(log n) time.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,2,3,1]
//	Output: 2
//	Explanation: 3 is a peak element and your function should return the index number 2.
//	Example 2:
//
//	Input: nums = [1,2,1,3,5,6,4]
//	Output: 5
//	Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
	
	public int findPeakElement(int[] nums) {
		if(nums.length==1)
			return 0;
        int n=nums.length,start=0,end=n-1;
        while(start<=end) {
        	int mid=start+(end-start)/2;
        	if(mid>0 && mid<n-1) {
        		if(nums[mid]>nums[mid-1]&&nums[mid]>nums[mid+1])
        			return mid;
        		if(nums[mid]>nums[mid-1])
        			start=mid+1;
        		else
        			end=mid-1;
        	}
        	if(mid==0) {
        		if(nums[mid]>nums[mid+1])
        			return mid;
        		else
        			start=mid+1;
        	}
        	if(mid==n-1) { 
        		if(nums[mid]>nums[mid-1])
            		return mid;
        		else
        			end=mid-1;
        	}
        }
        return -1;
    }
	
//	There is an integer array nums sorted in ascending order (with distinct values).
//
//	Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) 
//	such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
//	For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
//
//	Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
//
//	You must write an algorithm with O(log n) runtime complexity.
//
//	 
//
//	Example 1:
//
//	Input: nums = [4,5,6,7,0,1,2], target = 0
//	Output: 4
//	Example 2:
//
//	Input: nums = [4,5,6,7,0,1,2], target = 3
//	Output: -1
//	Example 3:
//
//	Input: nums = [1], target = 0
//	Output: -1
	//https://leetcode.com/problems/search-in-rotated-sorted-array/solutions/3879263/100-binary-search-easy-video-o-log-n-optimal-solution/?envType=study-plan-v2&envId=top-interview-150
	public int search(int[] nums, int target) {
        int n=nums.length,start=0,end=n-1;
        while(start<=end) {
        	int mid = start+(end-start)/2;
        	if(nums[mid]==target)
        		return mid;
        	else {
        		if(nums[start]<=nums[mid]) {
        			if(nums[start]<=target && nums[mid]>target)
        				end=mid-1;
        			else
        				start=mid+1;
        		}else {
        			if(nums[mid]<target && target<=nums[end])
        				start=mid+1;
        			else
        				end=mid-1;
        		}
        	}
        }
        return -1;
    }
//	Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
//
//	If target is not found in the array, return [-1, -1].
//
//	You must write an algorithm with O(log n) runtime complexity.
//
//	 
//
//	Example 1:
//
//	Input: nums = [5,7,7,8,8,10], target = 8
//	Output: [3,4]
//	Example 2:
//
//	Input: nums = [5,7,7,7,7,10,12], target = 6
//	Output: [-1,-1]
//	Example 3:
//
//	Input: nums = [], target = 0 
	// 2 3 4 4 4
//	Output: [-1,-1]
	
	public int[] searchRange(int[] nums, int target) {
		int[]ans= { findFirstApp(target, nums, 0, nums.length-1),findLastApp(target, nums, 0, nums.length-1)};
		return ans;
    }
	
	int findFirstApp(int target,int[]nums,int start,int end) {
		int ans=-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(nums[mid]==target) {
				ans=mid;
				end=mid-1;
			}else {
				if(nums[mid]>target)
					end=mid-1;
				else
					start=mid+1;
			}
		}
		return ans;
	}
	
	int findLastApp(int target,int[]nums,int start,int end) {
		int ans=-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(nums[mid]==target) {
				ans=mid;
				start=mid+1;
			}else {
				if(nums[mid]>target)
					end=mid-1;
				else
					start=mid+1;
			}
		}
		return ans;
	}
	
//	Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
//
//		[4,5,6,7,0,1,2] if it was rotated 4 times.
//		[0,1,2,4,5,6,7] if it was rotated 7 times.
//		Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
//
//		Given the sorted rotated array nums of unique elements, return the minimum element of this array.
//
//		You must write an algorithm that runs in O(log n) time.
//
//		 
//
//		Example 1:
//
//		Input: nums = [3,4,5,1,2]
//		Output: 1
//		Explanation: The original array was [1,2,3,4,5] rotated 3 times.
//		Example 2:
//
//		Input: nums = [4,5,6,7,0,1,2]
//		Output: 0
//		Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
//		Example 3:
//
//		Input: nums = [11,13,15,17]
//		Output: 11
//		Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
	
	public int findMin(int[] nums) {
		if(nums.length==1)
			return nums[0];
        int start=0,end=nums.length-1;
        if(nums[start]<nums[end])
        	return nums[start];
        while(start<=end) {
        	int mid=start+(end-start)/2;
        	if(nums[mid]>nums[mid+1])
        		return nums[mid+1];
        	if(nums[mid-1]>nums[mid])
        		return nums[mid];
        	if(nums[mid]>nums[0])
        		start=mid+1;
        	else
        		end=mid-1;
        }
        return -1;
    }
	
//	Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//			The overall run time complexity should be O(log (m+n)).
//
//			 
//
//			Example 1:
//
//			Input: nums1 = [1,3], nums2 = [2]
//			Output: 2.00000
//			Explanation: merged array = [1,2,3] and median is 2.
//			Example 2:
//
//			Input: nums1 = [1,2], nums2 = [3,4]
//			Output: 2.50000
//			Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
	//https://leetcode.com/problems/median-of-two-sorted-arrays/solutions/4070500/99-journey-from-brute-force-to-most-optimized-three-approaches-easy-to-understand/?envType=study-plan-v2&envId=top-interview-150
	//https://www.youtube.com/watch?v=q6IEA26hvXc
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length, n2 = nums2.length;
        
        // Ensure nums1 is the smaller array for simplicity
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        
        int n = n1 + n2;
        int left = (n1 + n2 + 1) / 2; // Calculate the left partition size
        int low = 0, high = n1;
        
        while (low <= high) {
            int mid1 = (low + high) >> 1; // Calculate mid index for nums1
            int mid2 = left - mid1; // Calculate mid index for nums2
            
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE, r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;
            
            // Determine values of l1, l2, r1, and r2
            if (mid1 < n1)
                r1 = nums1[mid1];
            if (mid2 < n2)
                r2 = nums2[mid2];
            if (mid1 - 1 >= 0)
                l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0)
                l2 = nums2[mid2 - 1];
            
            if (l1 <= r2 && l2 <= r1) {
                // The partition is correct, we found the median
                if (n % 2 == 1)
                    return Math.max(l1, l2);
                else
                    return ((double)(Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            }
            else if (l1 > r2) {
                // Move towards the left side of nums1
                high = mid1 - 1;
            }
            else {
                // Move towards the right side of nums1
                low = mid1 + 1;
            }
        }
        
        return 0; // If the code reaches here, the input arrays were not sorted.
    }	 


}

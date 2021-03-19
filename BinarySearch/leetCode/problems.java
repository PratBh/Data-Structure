
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Problems {
//	Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise. 
//
//	Return the number of negative numbers in grid.
//
//	 
//
//	Example 1:
//
//	Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
//	Output: 8
//	Explanation: There are 8 negatives number in the matrix.
//	Example 2:
//
//	Input: grid = [[3,2],[1,0]]
//	Output: 0
//	Example 3:
//
//	Input: grid = [[1,-1],[-1,-1]]
//	Output: 3
//	Example 4:
//
//	Input: grid = [[-1]]
//	Output: 1
	
	public int countNegatives(int[][] grid) {
		int m=grid.length,n=grid[0].length,count=0;
		for(int i=0;i<m;i++) {
			int [] arr=grid[i];
			int start=0,end=n-1;
			if(arr[start]<0) {
				count+=n;
				continue;
			}
			if(arr[end]>0)
				continue;
			while(start<=end) {
				int mid=start+(end-start)/2;
				if(arr[mid]>=0)
					start=mid+1;
				else {
					count+=end-mid+1;
					end=mid-1;
				}
			}
		}
		return count;
	}
//	Let's call an array arr a mountain if the following properties hold:
//
//	arr.length >= 3
//	There exists some i with 0 < i < arr.length - 1 such that:
//	arr[0] < arr[1] < ... arr[i-1] < arr[i]
//	arr[i] > arr[i+1] > ... > arr[arr.length - 1]
//	Given an integer array arr that is guaranteed to be a mountain, return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
//
//	 
//
//	Example 1:
//
//	Input: arr = [0,1,0]
//	Output: 1
//	Example 2:
//
//	Input: arr = [0,2,1,0]
//	Output: 1
//	Example 3:
//
//	Input: arr = [0,10,5,2]
//	Output: 1
	
	public int peakIndexInMountainArray(int[] arr) {
		if(arr.length==1)
			return 0;
        int start=0,end=arr.length-1;
        while(start<=end) {
        	int mid=start+(end-start)/2;
        	if(mid>0 && mid <arr.length-1) {
        		if(arr[mid]>arr[mid+1]&& arr[mid]>arr[mid-1])
        			return mid;
        		if(arr[mid]<arr[mid+1])
            		start=mid+1;
            	else if(arr[mid-1]>arr[mid])
            		end=mid-1;
        	}
        	if(mid==0) {
        		if(arr[mid]>arr[mid+1])
        			return mid;
        		else
        			start=mid+1;
        	}
        	else if(mid==arr.length-1) {
        		if(arr[mid]>arr[mid-1])
        			return mid;
        		else
        			end=mid-1;
        	}
        }
        return -1;
    }
//	Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians),
//	return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
//
//	A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, 
//	or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, 
//	always ones may appear first and then zeros.
//
//			 
//
//			Example 1:
//
//			Input: mat = 
//			[[1,1,0,0,0],
//			 [1,1,1,1,0],
//			 [1,0,0,0,0],
//			 [1,1,0,0,0],
//			 [1,1,1,1,1]], 
//			k = 3
//			Output: [2,0,3]
//			Explanation: 
//			The number of soldiers for each row is: 
//			row 0 -> 2 
//			row 1 -> 4 
//			row 2 -> 1 
//			row 3 -> 2 
//			row 4 -> 5 
//			Rows ordered from the weakest to the strongest are [2,0,3,1,4]
	
	public int[] kWeakestRows(int[][] mat, int k) {
        int [][] weeakest=new int [mat.length][];
        for(int i=0;i<mat.length;i++) {
        	int ones=countOneUtil(i, mat);
        	weeakest[i]=new int[]{ones,i};
        }
        Arrays.sort(weeakest,(a,b)->a[0]-b[0]);
        int res[]=new int[k];
        for(int i=0;i<k;i++) {
        	res[i]=weeakest[i][1];
        }
        return res;
    }
	
	int countOneUtil(int row,int[][] mat) {
		int start=0,end=mat[row].length-1,n=mat[row].length;
		int []arr=mat[row];
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]==1&&mid==n-1||arr[mid]==1 && arr[mid+1]==0)
				return mid+1;
			else if(arr[mid]==0)
				end=mid-1;
			else
				start=mid+1;
		}
		return 0;
	}
	
//	Given two arrays, write a function to compute their intersection.
//
//	Example 1:
//
//	Input: nums1 = [1,2,2,1], nums2 = [2,2]
//	Output: [2]
//	Example 2:
//
//	Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//	Output: [9,4]
	//https://www.youtube.com/watch?v=XxStWmfXJRs
	
	public int[] intersectionUsingSet(int[] nums1, int[] nums2) {
        Set<Integer> set1=new HashSet<Integer>();
        for(int i=0;i<nums1.length;i++) set1.add(nums1[i]);
        Set<Integer> intersect=new HashSet<Integer>();
        for(int i=0;i<nums2.length;i++) {
        	if(set1.contains(nums2[i]))
        		intersect.add(nums2[i]);
        }
        
        int []res=new int[intersect.size()];
        int i=0;
        for(int s: intersect) res[i++]=s;
        return res;
    }
	
	int[] intersectionBS(int[] nums1, int[] nums2) {
		if(nums1.length>nums2.length)
			return intersectionBS(nums2, nums1);
		Arrays.sort(nums2);
		Set<Integer> intersect=new HashSet<Integer>();
		for(int num:nums1) {
			if(find(nums2, num)!=-1)
				intersect.add(num);
		}
		int []res=new int[intersect.size()];
        int i=0;
        for(int s: intersect) res[i++]=s;
        return res;
	}
	
	int find(int [] arr,int num) {
		int start=0,end=arr.length-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]==num)
				return mid;
			if(arr[mid]>num)
				end=mid-1;
			else
				start=mid+1;
		}
		return -1;
	}
	
//	Given two arrays, write a function to compute their intersection.
//
//	Example 1:
//
//	Input: nums1 = [1,2,2,1], nums2 = [2,2]
//	Output: [2,2]
//	Example 2:
//
//	Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//	Output: [4,9]
//	Note:
//
//	Each element in the result should appear as many times as it shows in both arrays.
//	The result can be in any order.
//	Follow up:
//
//	What if the given array is already sorted? How would you optimize your algorithm?
//	What if nums1's size is small compared to nums2's size? Which algorithm is better?
//	What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
	//https://www.youtube.com/watch?v=lKuK69-hMcc
	
	public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        List<Integer> l1=new ArrayList<Integer>();
        while(i<nums1.length && j<nums2.length) {
        	if(nums1[i]==nums2[j]) {
        		l1.add(nums1[i]);
        		i++;
        		j++;
        	}
        	else if (nums1[i]>nums2[j])
        		j++;
        	else
        		i++;
        }
        
        int[]res=new int[l1.size()];
        i=0;
        for(int n:l1)
        	res[i++]=n;
        return res;
    }
	
	int[] intersectHashMap(int[] nums1, int[] nums2) {
		if(nums1.length>nums2.length)
			return intersectHashMap(nums2, nums1);
		HashMap<Integer, Integer> hm=new HashMap<Integer, Integer>();
		for(int n:nums1) 
			hm.put(n,hm.getOrDefault(n, 0)+1);
        List<Integer> l1=new ArrayList<Integer>();
		for(int num:nums2) {
			int count=hm.getOrDefault(num, 0);
			if(count>0) {
				l1.add(num);
				hm.put(num, hm.get(num)-1);
			}
		}
		int[]res=new int[l1.size()];
        int i=0;
        for(int n:l1)
        	res[i++]=n;
        return res;
	}
	
//	Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
//
//	The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
//
//	Note:
//
//	Your returned answers (both index1 and index2) are not zero-based.
//	You may assume that each input would have exactly one solution and you may not use the same element twice.
//	 
//
//	Example 1:
//
//	Input: numbers = [2,7,11,15], target = 9
//	Output: [1,2]
//	Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
//	Example 2:
//
//	Input: numbers = [2,3,4], target = 6
//	Output: [1,3]
	//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/980828/Java-0(n)-Solution-100-faster
	
	public int[] twoSum(int[] numbers, int target) {
       int start=0,end=numbers.length-1;
       boolean found=false;
       while(!found && start<=end) {
    	   if(numbers[start]+numbers[end]==target) {
    		   found=true;
    		   break;
    	   }
    	   else if(numbers[start]+numbers[end]>target)
    		   end--;
    	   else
    		   start++;
       }
       if(found) {
    	   int res[]= {start+1,end+1};
    	   return res;	
       }
       return new int[] {-1,-1};
    }
	
//	Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
//
//	Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
//
//	Examples:
//	Input:
//	letters = ["c", "f", "j"]
//	target = "a"
//	Output: "c"
//
//	Input:
//	letters = ["c", "f", "j"]
//	target = "c"
//	Output: "f"
	
	public char nextGreatestLetter(char[] letters, char target) {
        int start=0,end=letters.length-1,res=0;
        while(start<=end) {
        	int mid=start+(end-start)/2;
        	if(letters[mid]<=target)
        		start=mid+1;
        	else
        	{
        		res=mid;
        		end=mid-1;
        	}
        }
        return letters[res];
    }
	
//	Given a positive integer num, write a function which returns True if num is a perfect square else False.
//
//	Follow up: Do not use any built-in library function such as sqrt.
//
//	 
//
//	Example 1:
//
//	Input: num = 16
//	Output: true
//	Example 2:
//
//	Input: num = 14
//	Output: false
	//https://leetcode.com/problems/valid-perfect-square/discuss/990975/Binary-search-solution-wvideo-whiteboard-explanation
	
	public boolean isPerfectSquare(int num) {
		if(num<1)
			return false;
		if(num==1)
			return true;
        int start=1,end=num;
        while(start<=end) {
        	long mid=start+(end-start)/2;
        	if(mid*mid==num)
        		return true;
        	if(mid*mid>num)
        		end=(int) (mid-1);
        	else
        		start=(int) (mid+1);
        }
        return false;
    }
	
//	Given a non-negative integer x, compute and return the square root of x.
//
//			Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
//
//			 
//
//			Example 1:
//
//			Input: x = 4
//			Output: 2
//			Example 2:
//
//			Input: x = 8
//			Output: 2
//			Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
	//shttps://leetcode.com/problems/sqrtx/discuss/993234/Java-Binary-Search-(Steps-explained-with-comments-and-100-fast-)
	
	public int mySqrt(int x) {
		double upperBound = x;
        double lowerBound = 0;
        double possibleRoot = (upperBound + lowerBound) / 2 ;
        double precision = 0.01;  // nearest hundredth precision
        
        if (x == 1) {   // square root of 1 is 1
            return 1;
        }

        while (Math.abs(x - possibleRoot * possibleRoot) >= precision) {  // loop until the hundredth decimal place of the square root is accurate

            if (possibleRoot * possibleRoot > x) {  // if a guessed root is higher than the number, then the guessed number is the highest possible root
                upperBound = possibleRoot;
            }
            else {   // else the possible root is the lowest possible root
                lowerBound = possibleRoot;
            }

            possibleRoot = (upperBound + lowerBound) / 2;  // find the midpoint to approximate the root again
        }

        return (int) possibleRoot;
    }
	
//	Given a matrix mat where every row is sorted in increasing order, return the smallest common element in all rows.
//
//			If there is no common element, return -1.
//
//			 
//
//			Example 1:
//
//			Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
//			Output: 5
//			 
//
//			Constraints:
//
//			1 <= mat.length, mat[i].length <= 500
//			1 <= mat[i][j] <= 10^4
//			mat[i] is sorted in increasing order.
	//http://lixinchengdu.github.io/algorithmbook/leetcode/find-smallest-common-element-in-all-rows.html
	
	int smallestCommonElement(int [][] mat) {
		int m=mat.length;
		if(m==0) return -1;
		int n=mat[0].length;
		if(n==0) return -1;
		for(int num:mat[0]) {
			boolean found=true;
			for(int i=1;i<m;i++) {
				if(find(mat[i], num)==-1) {
					found=false;
					break;
				}
			}
			if(found)
				return num;
		}
		return -1;
	}
//	
//	Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
//	 
//
//	Example 1:
//
//	Input: root = [3,1,4,null,2], k = 1
//	   3
//	  / \
//	 1   4
//	  \
//	   2
//	Output: 1
//	Example 2:
//
//	Input: root = [5,3,6,2,4,null,null,1], k = 3
//	       5
//	      / \
//	     3   6
//	    / \
//	   2   4
//	  /
//	 1
//	Output: 3
	
	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode() {}
		      TreeNode(int val) { this.val = val; }
		      TreeNode(int val, TreeNode left, TreeNode right) {
		          this.val = val;
		          this.left = left;
		          this.right = right;
		      }
		 }
	public int kthSmallest(TreeNode root, int k) {
       List<Integer> in=new ArrayList<Integer>();
       inOrder(root, in);
       if(k<=in.size())
    	   return in.get(k-1);
       return -1;
    }
	
	void inOrder(TreeNode root,List<Integer>l1) {
		if(root.left!=null)
			inOrder(root.left, l1);
		l1.add(root.val);
		if(root.right!=null)
			inOrder(root.right, l1);
	}
	
//	A conveyor belt has packages that must be shipped from one port to another within D days.
//
//	The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt 
//	(in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
//
//	Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
//
//	 
//
//	Example 1:
//
//	Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//	Output: 15
//	Explanation: 
//	A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
//	1st day: 1, 2, 3, 4, 5
//	2nd day: 6, 7
//	3rd day: 8
//	4th day: 9
//	5th day: 10
//
//	Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages 
//	into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed. 
//	Example 2:
//
//	Input: weights = [3,2,2,4,1,4], D = 3
//	Output: 6
//	Explanation: 
//	A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
//	1st day: 3, 2
//	2nd day: 2, 4
//	3rd day: 1, 4
//	Example 3:
//
//	Input: weights = [1,2,3,1,1], D = 4
//	Output: 3
//	Explanation: 
//	1st day: 1
//	2nd day: 2
//	3rd day: 3
//	4th day: 1, 1
//	 
//
//	Constraints:
//
//	1 <= D <= weights.length <= 50000
//	1 <= weights[i] <= 500
	public int shipWithinDays(int[] weights, int D) {
        int max=0,sum=0,res=0;
        for(int i=0;i<weights.length;i++) {
        	max=Math.max(max, weights[i]);
        	sum+=weights[i];
        }
        int start=max,end=sum;
        while(start<=end) {
        	int mid=start+(end-start)/2;
        	if(isValidWeight(weights, D, mid)) {
        		res=mid;
        		end=mid-1;
        	}
        	else
        		start=mid+1;
        }
        return res;
    }
	
	boolean isValidWeight(int[] weights, int D,int maxWeight) {
		int days=1,total=0;
		for(int w:weights) {
			total+=w;
			if(total>maxWeight) {
				total=w;
				days++;
			}
			if(days>D)
				return false;
		}
		return true;
	}
	
//	You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.
//
//	Follow up: Your solution should run in O(log n) time and O(1) space.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,1,2,3,3,4,4,8,8]
//	Output: 2
//	Example 2:
//
//	Input: nums = [3,3,7,7,10,11,11]
//	Output: 10
	//https://www.youtube.com/watch?v=nMGL2vlyJk0
	 public int singleNonDuplicate(int[] nums) {
		 if(nums.length==1)
			 return nums[0];
		 int start=0,end=nums.length-1;
		 if(nums[0]<nums[1]) return nums[0];
		 if(nums[nums.length-1]>nums[nums.length-2]) return nums[nums.length-1];
		 while(start<=end) {
			 int mid=start+(end-start)/2;
				 if(nums[mid]!=nums[mid+1]&& nums[mid]!=nums[mid-1])
					 return nums[mid];
				 if(mid%2==0) {
					 if(nums[mid]<nums[mid+1])
						 end=mid-1;
					 else
						 start=mid+1;
				 }else {
					 if(nums[mid]<nums[mid+1])
						 start=mid+1;
					 else
						 end=mid-1;
				 }
		 }
		 return -1;
	 }
	 
//	 Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//
//	 There is only one duplicate number in nums, return this duplicate number.
//
//	 Follow-ups:
//
//	 How can we prove that at least one duplicate number must exist in nums? 
//	 Can you solve the problem without modifying the array nums?
//	 Can you solve the problem using only constant, O(1) extra space?
//	 Can you solve the problem with runtime complexity less than O(n2)?
//	  
//
//	 Example 1:
//
//	 Input: nums = [1,3,4,2,2]
//	 Output: 2
//	 Example 2:
//
//	 Input: nums = [3,1,3,4,2]
//	 Output: 3
//	 Example 3:
//
//	 Input: nums = [1,1]
//	 Output: 1
	 //https://leetcode.com/problems/find-the-duplicate-number/solution/  floyed's tortoise-hare method
	 
	 public int findDuplicate(int[] nums) {
	     int tortoise=nums[0],hare=nums[0];
	     do {
	    	 tortoise=nums[tortoise];
	    	 hare=nums[nums[hare]];
	     }while(tortoise!=hare);
	     tortoise=nums[0];
	     while(tortoise!=hare) {
	    	 tortoise=nums[tortoise];
	    	 hare=nums[hare];
	     }
	     return hare;
	 }
	 
//	 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
//
//	 Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//	 Example:
//
//	 matrix = [
//	    [ 1,  5,  9],
//	    [10, 11, 13],
//	    [12, 13, 15]
//	 ],
//	 k = 8,
//
//	 return 13.
	 //https://www.youtube.com/watch?v=oeQlc87HbbQ
	 
	 public int kthSmallest(int[][] matrix, int k) {
	     int n=matrix.length;
	     int start=matrix[0][0],end=matrix[n-1][n-1];
	     while(start<end) {
	    	 int mid=start+(end-start)/2;
	    	 if(countLesEq(matrix, mid)>=k) {
	    		 end=mid;
	    	 }
	    	 else {
	    		start=mid+1;
	    	 }
	     }
	     return end;//we can return end or start as at this stage both are same
	 }
	 
	 int countLesEq(int[][] matrix,int num) {
		 int n=matrix.length,i=0,j=n-1,count=0;
		 while(i<n && j>=0) {
			 if(matrix[i][j]<=num) {
				 count+=j+1;
				 i++;
			 }else
				 j--;
		 }
		 return count;
	 }
	 
//	 Given a list of n-1 integers and these integers are in the range of 1 to n. There are no duplicates in list. 
//	 One of the integers is missing in the list. Write an efficient code to find the missing integer.
//
//	 Examples:
//
//	 Input : arr[] = [1, 2, 3, 4, 6, 7, 8]
//	 Output : 5
//
//	 Input : arr[] = [1, 2, 3, 4, 5, 6, 8, 9]
//	 Output : 7
	 
	 int search(int arr[]) {
		 int start=0,end=arr.length-1,ans=0;
		 while(start<=end) {
			 int mid=start+(end-start)/2;
			 if(arr[mid]-mid==1)
				 start=mid+1;
			 else if(arr[mid]-mid==2) {
				 ans=arr[mid]-1;
				 end=mid-1;
			 }
		 }
		 return ans;
	 } 
	 
//	 Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
//
//	 To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
//
//	 Example:
//
//	 Input:
//	 A = [ 1, 2]
//	 B = [-2,-1]
//	 C = [-1, 2]
//	 D = [ 0, 2]
//
//	 Output:
//	 2
//
//	 Explanation:
//	 The two tuples are:
//	 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
//	 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
	 //https://www.youtube.com/watch?v=Lc54sgbyfYI
	 public int fourSumCountUsingHashmap(int[] A, int[] B, int[] C, int[] D) {
	      Map<Integer,Integer> sumToCount=new HashMap<Integer,Integer>();
	      for(int i=0;i<A.length;i++) {
	    	  for(int j=0;j<B.length;j++) {
	    		  int sum=A[i]+B[j];
	    		  sumToCount.put(sum, sumToCount.getOrDefault(sum, 0)+1);
	    	  }
	      }
	      int count=0;
	      for(int i=0;i<C.length;i++) {
	    	  for(int j=0;j<D.length;j++) {
	    		  int sum=C[i]+D[j];
	    		  if(sumToCount.containsKey(-1*sum))
	    			  count+=sumToCount.get(-1*sum);
	    	  }
	      }
	      return count;
	}
	 
//	 Convert problem to two sum problem by applying sum =a+b and diff = -(c+d)
//			 now we need to find how many times sum occurs in diff list.
//
//			 Algo:
//
//			 Compute Sum List-> A+B for all
//			 Compute diff List-> -(C+D) for all [ note here i used -(c+d) not (c+d) . a+b+c+d = a+b = -(c+d) ]
//			 For each element in sum list -> element
//			 Find how many times it occurred in Diff list and add that count
//			 To Find occurrence, we can use binary search
//
//			 Find the index of first occurrence of element
//			 find the index of last occurrence of element
//			 count = last-fist+1
	 public int fourSumCountUsingBinarySearch(int[] a, int[] b, int[] c, int[] d) {
	        return fourSumCount(a, b, c, d, 0);
	    }

	    public int fourSumCount(int[] a, int[] b, int[] c, int[] d, int target) {


	        ArrayList<Integer> sumPairs = new ArrayList<>();
	        ArrayList<Integer> diffPairs = new ArrayList<>();


	        int n = a.length;
	        for (int i = 0; i < n; i++) {

	            for (int j = 0; j < n; j++) {

	                /**
	                 * Convert those array in elements such a way that for elemnets sum become zero.
	                 * a + b + c + d = 0
	                 * a + b = - (c+d)
	                 */
	                sumPairs.add(a[i] + b[j]);

	                diffPairs.add(-(c[i] + d[j]));
	            }
	        }

	        Collections.sort(sumPairs);
	        Collections.sort(diffPairs);

	        int count = 0;
	        for (int i = 0; i < sumPairs.size(); i++) {

	            int element = sumPairs.get(i);

	            int countOfelement = binarySearch(diffPairs, element);
	            if (countOfelement != -1)
	                count += countOfelement;
	        }

	        return count;

	    }

	    private int binarySearch(ArrayList<Integer> diffPairs, int element) {
	        if (diffPairs == null || diffPairs.isEmpty())
	            return -1;

	        int low = 0, high = diffPairs.size();

	        int first = firstIndex(diffPairs, element);
	        if (first == -1)
	            return -1;

	        int last = lastIndex(diffPairs, element);

	        return last - first + 1;

	    }


	    private int firstIndex(ArrayList<Integer> diffPairs, int element) {
	        if (diffPairs == null || diffPairs.isEmpty())
	            return -1;

	        int low = 0, high = diffPairs.size() - 1;

	        while (low <= high) {
	            int mid = (low + high) >> 1;
	            int midV = diffPairs.get(mid);

	            if ((low == mid && midV == element) || (midV == element && diffPairs.get(mid - 1) < element))
	                return mid;
	            else if (midV < element)
	                low = mid + 1;
	            else
	                high = mid - 1; //mid already been tested
	        }

	        return -1;
	    }


	    private int lastIndex(ArrayList<Integer> diffPairs, int element) {
	        if (diffPairs == null || diffPairs.isEmpty())
	            return -1;

	        int low = 0, high = diffPairs.size() - 1;

	        while (low <= high) {
	            int mid = (low + high) >> 1;
	            int midV = diffPairs.get(mid);

	            if ((high == mid && midV == element) || (midV == element && diffPairs.get(mid + 1) > element))
	                return mid;
	            else if (midV > element)
	                high = mid - 1;
	            else
	                low = mid + 1; //mid already been tested
	        }

	        return -1;
	    }
	    
//	    You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.
//
//	    		Example 1:
//
//	    		Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
//	    		Output: [3,0,3]
//	    		Explanation: 
//	    		The nearest 3 from index 1 is at index 4 (3 steps away).
//	    		The nearest 2 from index 2 is at index 2 itself (0 steps away).
//	    		The nearest 1 from index 6 is at index 3 (3 steps away).
//	    		Example 2:
//
//	    		Input: colors = [1,2], queries = [[0,3]]
//	    		Output: [-1]
//	    		Explanation: There is no 3 in the array.
//	    		Constraints:
//
//	    		1 <= colors.length <= 5*10^4
//	    		1 <= colors[i] <= 3
//	    		1 <= queries.length <= 5*10^4
//	    		queries[i].length == 2
//	    		0 <= queries[i][0] < colors.length
//	    		1 <= queries[i][1] <= 3

	    
//	    int[] shortestDistanceColor(int []colors, int queries [][]) {
//	    	
//	    }
//	    
//	    int searchColor(int start,int target,int[]colors) {
//	    	if(colors[start]==target)
//	    		return start;
//	    	int end=colors.length;
//	    	while(start<=end) {
//	    		int mid=start+(end-start)/2;
//	    		
//	    	}
//	    }
	 
	    
//	    Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
//
//	    Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
//
//	    Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
//
//	    Return the minimum integer K such that she can eat all the bananas within H hours.
//
//	     
//
//	    Example 1:
//
//	    Input: piles = [3,6,7,11], H = 8
//	    Output: 4
//	    Example 2:
//
//	    Input: piles = [30,11,23,4,20], H = 5
//	    Output: 30
//	    Example 3:
//
//	    Input: piles = [30,11,23,4,20], H = 6
//	    Output: 23
//	     
//
//	    Constraints:
//
//	    1 <= piles.length <= 10^4
//	    piles.length <= H <= 10^9
//	    1 <= piles[i] <= 10^9
	    //https://leetcode.com/problems/koko-eating-bananas/discuss/978986/Java-oror-Binary-Search-oror-Easy-to-Understand
	    
	    public int minEatingSpeed(int[] piles, int H) {
	        Arrays.sort(piles);
	        int start=1,end=piles[piles.length-1],mid=0;
	        while(start<end) {
	        	mid=start+(end-start)/2;
	        	if(isValidNum(mid, piles, H))
	        		end=mid;
	        	else
	        		start=mid+1;
	        }
	        return end;
	    }
	    
	    boolean isValidNum(int num,int[] piles, int H) {
	    	int hour=0,carryOver=0;
	    	for(int banana:piles) {
	    		if(banana%num==0) hour+=banana/num;
	    		else hour+=banana/num+1;
	    	}
	    	if(hour<=H)
	    		return true;
	    	return false;
	    }
	    
//	    You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
//
//	    You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
//
//	    While moving from building i to building i+1 (0-indexed),
//
//	    If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
//	    If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
//	    Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
//	    Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
//	    Output: 4
//	    Explanation: Starting at building 0, you can follow these steps:
//	    - Go to building 1 without using ladders nor bricks since 4 >= 2.
//	    - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
//	    - Go to building 3 without using ladders nor bricks since 7 >= 6.
//	    - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
//	    It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
	    //https://leetcode.com/problems/furthest-building-you-can-reach/discuss/1000731/Java-Priority-Queue-solution
	    
	    public int furthestBuilding(int[] heights, int bricks, int ladders) {
	        PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
	        for(int i=0;i<heights.length-1;i++) {
	        	if(heights[i+1]<=heights[i])
	        		continue;
	        	int d=heights[i+1]-heights[i];
	        	pq.add(d);
	        	if(pq.size()>ladders) {
	        		bricks-=pq.poll();
	        		if(bricks<0)
	        			return i;
	        	}
	        }
	        return heights.length-1;
	    }
	    
//	    Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or 
//	    return 0 if there is no such square.
//
//	    		 
//
//	    		Example 1:
//
//
//	    		Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
//	    		Output: 2
//	    		Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
//	    		Example 2:
//
//	    		Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
//	    		Output: 0
//https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/discuss/966898/Java-Binary-Search-or-Easy-to-understand
	    
	    int rows, cols;
	    public int maxSideLength(int[][] mat, int threshold) {
	        rows = mat.length;
	        cols = mat[0].length;
	        int l = 1, r = Math.min(rows, cols) + 1;
	        while(l < r){
	            int m = l + (r - l) / 2;
	            if(!possible(mat, m, threshold)) r = m;
	            else l = m + 1;
	        }
	        return l - 1;
	    }

	    private boolean possible(int[][] mat, int size, int threshold){
		//check all possible start Point
	        for(int i = 0; i < rows - size + 1; i++){
	            for(int j = 0; j < cols - size + 1; j++){
	                if(checkThreshold(mat, i, j, size, threshold)) return true;
	            }
	        }
	        return false;
	    }

	    private boolean checkThreshold(int[][] mat, int x, int y, int size, int threshold){
		//for this point (x, y), check if this size value is possible
	        int sum = 0;
	        for(int i = x; i < x + size; i++){
	            for(int j = y; j < y + size; j++){
	                sum += mat[i][j];
	            }
	        }
	        return sum <= threshold;
	    }
	    
//	    Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
//
//	    		Example 1:
//
//	    		Input:
//	    		A: [1,2,3,2,1]
//	    		B: [3,2,1,4,7]
//	    		Output: 3
//	    		Explanation: 
//	    		The repeated subarray with maximum length is [3, 2, 1].
	    
	    public int findLength(int[] A, int[] B) {
	        if(A.length==0||B.length==0)
	        	return 0;
	        int [][]t=new int[A.length][B.length];
	        for(int i=0;i<t.length;i++)
	        	Arrays.fill(t[i], -1);
	        int ans=Integer.MIN_VALUE;
	        for(int i=0;i<A.length;i++) {
	        	for(int j=0;j<B.length;j++)
	        		ans=Math.max(lcSubstring(i, j, A, B, t), ans);
	        }
	        return ans;
	    }
	    
	    int lcSubstring(int i,int j,int[] A, int[] B,int [][]t) {
	    	if(i>=A.length || j>=B.length)
	    		return 0;
	    	if(t[i][j]!=-1)
	    		return t[i][j];
	    	if(A[i]==B[j])
	    		t[i][j]=1+lcSubstring(i+1, j+1, A, B, t);
	    	else
	    		t[i][j]=0;
	    	return t[i][j];
	    }
	    
//	    Given an integer array bloomDay, an integer m and an integer k.
//
//	    We need to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
//
//	    The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
//
//	    Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
//
//	     
//
//	    Example 1:
//
//	    Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
//	    Output: 3
//	    Explanation: Let's see what happened in the first three days. x means flower bloomed and _ means flower didn't bloom in the garden.
//	    We need 3 bouquets each should contain 1 flower.
//	    After day 1: [x, _, _, _, _]   // we can only make one bouquet.
//	    After day 2: [x, _, _, _, x]   // we can only make two bouquets.
//	    After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
//	    Example 2:
//
//	    Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
//	    Output: -1
//	    Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
//	    Example 3:
//
//	    Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
//	    Output: 12
//	    Explanation: We need 2 bouquets each should have 3 flowers.
//	    Here's the garden after the 7 and 12 days:
//	    After day 7: [x, x, x, x, _, x, x]
//	    We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
//	    After day 12: [x, x, x, x, x, x, x]
//	    It is obvious that we can make two bouquets in different ways.
//	    Example 4:
//
//	    Input: bloomDay = [1000000000,1000000000], m = 1, k = 1
//	    Output: 1000000000
//	    Explanation: You need to wait 1000000000 days to have a flower ready for a bouquet.
//	    Example 5:
//
//	    Input: bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
//	    Output: 9
	    //https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/discuss/990569/Java-Iterative-Binary-Search-or-O(m-*-log-n)-or-Easy-to-Understand
	    
	    public int minDays(int[] bloomDay, int m, int k) {
	       if(m*k>bloomDay.length)
	    	   return -1;
	       
	       int start=Integer.MAX_VALUE,end=Integer.MIN_VALUE,result=0;
	       for(int i=0;i<bloomDay.length;i++) {
	    	   start=Math.min(start, bloomDay[i]);
	    	   end=Math.max(end, bloomDay[i]);
	       }
	       
	       while(start<=end) {
	    	   int mid=start+(end-start)/2;
	    	   if(isSufficienDays(mid, bloomDay, m, k)) {
	    		   result=mid;
	    		   end=mid-1;
	    	   }else
	    		   start=mid+1;
	       }
	       return result;
	    }
	    
	    boolean isSufficienDays(int day,int[] bloomDay, int m, int k) {
	    	int counter=0,result=0;
	    	for(int i=0;i<bloomDay.length;i++) {
	    		if(bloomDay[i]<=day && counter<k)
	    			counter++;
	    		else if(bloomDay[i]>day && counter>0)
	    			counter=0;
	    		if(counter>=k) {
	    			result++;
	    			counter=0;
	    		}
	    	}
	    	return result>=m;
	    }
//	    Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide 
//all the array by it and sum the result of the division. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
//
//	    Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
//
//	    It is guaranteed that there will be an answer.
//
//	     
//
//	    Example 1:
//
//	    Input: nums = [1,2,5,9], threshold = 6
//	    Output: 5
//	    Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
//	    If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
//	    Example 2:
//
//	    Input: nums = [2,3,5,7,11], threshold = 11
//	    Output: 3
//	    Example 3:
//
//	    Input: nums = [19], threshold = 5
//	    Output: 4
//	     
//
//	    Constraints:
//
//	    1 <= nums.length <= 5 * 104
//	    1 <= nums[i] <= 106
//	    nums.length <= threshold <= 106
	    
	    public int smallestDivisor(int[] nums, int threshold) {
	        Arrays.sort(nums);
	        int start=1,end=nums[nums.length-1],res=0;
	        while(start<=end) {
	        	int mid=start+(end-start)/2;
	        	if(isValidSum(nums, mid, threshold)) {
	        		res=mid;
	        		end=mid-1;
	        	}
	        	else
	        		start=mid+1;
	        }
	        return res;
	    }
	    
	    boolean isValidSum(int[] nums,int n,int threshold) {
	    	int sum=0;
	    	for(int num:nums) {
	    		int div=num/n+(num%n==0?0:1);
	    		sum+=div;
	    	}
	    	return sum<=threshold;
	    }
	    
//	    In universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. 
//	    Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that 
//	    the minimum magnetic force between any two balls is maximum.
//
//	    Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
//
//	    Given the integer array position and the integer m. Return the required force.
//
//	     
//
//	    Example 1:
//
//
//	    Input: position = [1,2,3,4,7], m = 3
//	    Output: 3
//	    Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
//	    Example 2:
//
//	    Input: position = [5,4,3,2,1,1000000000], m = 2
//	    Output: 999999999
//	    Explanation: We can use baskets 1 and 1000000000.
	    //https://leetcode.com/problems/magnetic-force-between-two-balls/
	    //https://leetcode.com/problems/magnetic-force-between-two-balls/discuss/909322/Java-Binary-search-with-comments
	    
	    public int maxDistance(int[] position, int m) {
	        Arrays.sort(position);
	        int start=1;//min gap
	        int end=position[position.length-1]-position[0],max=0;
	        while(start<=end) {
	        	int mid=start+(end-start)/2;
	        	int force=calForce(mid, position, m);
	        	if(force==-1)
	        		end=mid-1;
	        	else {
	        		start=mid+1;
	        		max=Math.max(force, max);
	        	}
	        }
	        return max;
	    }
	    
	    int calForce(int gap,int[] position, int m) {
	    	int count=m,prev=position[0],force=-1;
	    	count--;
	    	for(int i=1;i<position.length;i++) {
	    		if(position[i]-prev >= gap) {
	    			count--;
	    			int val=position[i]-prev;
	    			force=(force==-1)?val:Math.min(force, val);
	    			prev=position[i];
	    		}
	    		if(count==0)
	    			break;
	    	}
	    	return (count==0)?force:-1;
	    }
	    
//	    You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
//
//	    		The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
//
//	    		Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
//
//	    		 
//
//	    		Example 1:
//
//	    		Input: intervals = [[1,2]]
//	    		Output: [-1]
//	    		Explanation: There is only one interval in the collection, so it outputs -1.
//	    		Example 2:
//
//	    		Input: intervals = [[3,4],[2,3],[1,2]]
//	    		Output: [-1,0,1]
//	    		Explanation: There is no right interval for [3,4].
//	    		The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
//	    		The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
//	    		Example 3:
//
//	    		Input: intervals = [[1,4],[2,3],[3,4]]
//	    		Output: [-1,2,-1]
//	    		Explanation: There is no right interval for [1,4] and [3,4].
//	    		The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
//	    		 
//
//	    		Constraints:
//
//	    		1 <= intervals.length <= 2 * 104
//	    		intervals[i].length == 2
//	    		-106 <= starti <= endi <= 106
//	    		The start point of each interval is unique.
	    //https://leetcode.com/problems/find-right-interval/discuss/990774/Java-Binary-Search-or-From-O(n2-*-log-n)-to-O(n-*-log-n)-or-Easy-to-Understand
	    
	    public int[] findRightInterval(int[][] intervals) {
	        HashMap<Integer,Integer> hm=new HashMap<Integer, Integer>();
	        int[] arr = new int[intervals.length];
	        int[] result = new int[intervals.length];
	        for(int i=0;i<intervals.length;i++) {
	        	result[i] = intervals[i][0];
	            arr[i] = intervals[i][1];
	        	hm.put(intervals[i][0], i);
	        }
	        
	        Arrays.sort(intervals,Comparator.comparingDouble(o->o[0]));
	        for(int i=0;i<intervals.length;i++) {
	        	int res=findGreater(intervals, arr[i]);
	        	result[i]=(res==-1)?-1:hm.get(intervals[res][0]);
	        }
	        return result;
	    }
	    
	    int findGreater(int[][] intervals,int element) {
	    	int start=0,end=intervals.length-1,result=-1;
	    	while(start<=end) {
	    		int mid=start+(end-start)/2;
	    		if(intervals[mid][0]==element)
	    			return mid;
	    		if(intervals[mid][0]<element)
	    			start=mid+1;
	    		else {
	    			result=mid;
	    			end=mid-1;
	    		}
	    	}
	    	return result;
	    }
//	    Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
//
//	    	[4,5,6,7,0,1,2] if it was rotated 4 times.
//	    	[0,1,2,4,5,6,7] if it was rotated 7 times.
//	    	Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
//
//	    	Given the sorted rotated array nums, return the minimum element of this array.
//
//	    	 
//
//	    	Example 1:
//
//	    	Input: nums = [3,4,5,1,2]
//	    	Output: 1
//	    	Explanation: The original array was [1,2,3,4,5] rotated 3 times.
//	    	Example 2:
//
//	    	Input: nums = [4,5,6,7,0,1,2]
//	    	Output: 0
//	    	Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
//	    	Example 3:
//
//	    	Input: nums = [11,13,15,17]
//	    	Output: 11
//	    	Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
//	    	 
//
//	    	Constraints:
//
//	    	n == nums.length
//	    	1 <= n <= 5000
//	    	-5000 <= nums[i] <= 5000
//	    	All the integers of nums are unique.
//	    	nums is sorted and rotated between 1 and n times.
	    
	    public int findMin(int[] nums) {
	            if(nums.length==1)
	            	return nums[0];
	            int start=0,end=nums.length-1;
	            if(nums[start]<nums[end])
	            	return nums[start];//here there is no rotation
	            
	            while(start<=end) {
	            	int mid=start+(end-start)/2;
	            	if(nums[mid]>nums[mid+1])//here the mid+1 is the inflation point
	            		return nums[mid+1];
	            	
	            	if(nums[mid-1]>nums[mid])// if the mid element is lesser than its previous element then mid element is the smallest
	            		return nums[mid];
	            	
	            	 // if the mid elements value is greater than the 0th element this means
	                // the least value is still somewhere to the right as we are still dealing with elements
	                // greater than nums[0]
	            	if(nums[mid]>nums[0])
	            		start=mid+1;
	            	else
	            		end=mid-1;
	            }
	            return -1;
	        }
	    
//	    Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
//
//	    	Integers in each row are sorted in ascending from left to right.
//	    	Integers in each column are sorted in ascending from top to bottom.
	   // https://leetcode.com/problems/search-a-2d-matrix-ii/
	    public boolean searchMatrix(int[][] matrix, int target) {
	       int rows=matrix.length,cols=matrix[0].length,i=0,j=cols-1; 
	       while(i>=0 && i<rows && j>=0 && j<cols ) {
	    	   if(matrix[i][j]==target)
	    		   return true;
	    	   if(matrix[i][j]>target)
	    		   j--;
	    	   else
	    		   i++;
	       }
	       return false;
	    }
	    
//	    Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value
//	    		in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.
//
//	    		In case of a tie, return the minimum such integer.
//
//	    		Notice that the answer is not neccesarilly a number from arr.
//
//	    		 
//
//	    		Example 1:
//
//	    		Input: arr = [4,9,3], target = 10
//	    		Output: 3
//	    		Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
//	    		Example 2:
//
//	    		Input: arr = [2,3,5], target = 10
//	    		Output: 5
//	    		Example 3:
//
//	    		Input: arr = [60864,25176,27249,21296,20204], target = 56803
//	    		Output: 11361
	    //https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/discuss/969320/Java-0ms-100-faster-No-Binary-Search
	    public int findBestValue(int[] arr, int target) {
	        int base=target/arr.length;
	        int diff=computeSum(arr, base)-target;
	        while(true) {
	        	int newBase=base;
	        	if(diff>=0)
	        		newBase--;
	        	else
	        		newBase++;
	        	int newDiff=computeSum(arr, newBase)-target;
	        	if(Math.abs(newDiff)<Math.abs(diff)) {
	        		base=newBase;
	        		diff=newDiff;
	        	}else
	        		break;
	        }
	        return base;
	    }
	    
	    int computeSum(int[] arr, int threshold) {
	    	int sum=0;
	    	for(int num:arr)
	    		sum+=Math.min(num, threshold);
	    	return sum;
	    }
	    
//	    You are given an integer array nums sorted in ascending order (not necessarily distinct values), and an integer target.
//
//	    Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,4,4,5,6,6,7] might become [4,5,6,6,7,0,1,2,4,4]).
//
//	    If target is found in the array return its index, otherwise, return -1.
//
//	     
//
//	    Example 1:
//
//	    Input: nums = [2,5,6,0,0,1,2], target = 0
//	    Output: true
//	    Example 2:
//
//	    Input: nums = [2,5,6,0,0,1,2], target = 3
//	    Output: false
	    //https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/1016983/Very-Simplified-Solution-(Binary-Search)
	    public boolean search(int[] nums, int target) {
	        if(nums.length==0)
	        	return false;
	        int start=0,end=nums.length-1;
	        while(start<=end) {
	        	int mid=start+(end-start)/2;
	        	if(nums[mid]==target)
	        		return true;
	        	if(nums[mid]==nums[start] && nums[end]==nums[mid]) {
	        		start++;
	        		end--;
	        		continue;
	        	}
	        	if(nums[start]<=nums[mid]) {//start to mid is sorted
	        		if(nums[start]<=target && target<nums[mid])
	        			end=mid-1;
	        		else
	        			start=mid+1;
	        	}else {//mid to end part is sorted
	        		if(nums[mid]<target && target<=nums[end])
	        			start=mid+1;
	        		else
	        			end=mid-1;
	        	}
	        }
	        return false;
	    }
	    
//	    Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
//
//	    
//
//	    Example 1:
//
//	    Input: x = 2.00000, n = 10
//	    Output: 1024.00000
//	    Example 2:
//
//	    Input: x = 2.10000, n = 3
//	    Output: 9.26100
//	    Example 3:
//
//	    Input: x = 2.00000, n = -2
//	    Output: 0.25000
//	    Explanation: 2-2 = 1/22 = 1/4 = 0.25
	   // https://leetcode.com/problems/powx-n/discuss/1021764/O(log-n)-Time-or-C%2B%2B-or-Simple-Solution
	    public double myPow(double x, int n) {
	    	if(n==0) return (double)1;
	    	double help=x;
	    	if(n<0)
	    		help=1/x;
	    	if(Math.abs(n)%2==0)
	    		return myPow(help*help, Math.abs(n)/2);
	    	return help*myPow(help*help, Math.abs(n)/2);
	    }
}

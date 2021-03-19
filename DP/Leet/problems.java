package Leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

public class Problems {
//	Say you have an array for which the ith element is the price of a given stock on day i.
//
//	If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
//
//	Note that you cannot sell a stock before you buy one.
//
//	Example 1:
//
//	Input: [7,1,5,3,6,4]
//	Output: 5
//	Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//	             Not 7-1 = 6, as selling price needs to be larger than buying price.
	
	public int maxProfit(int[] prices) {
        int[]t=new int[prices.length];
        Arrays.fill(t, -1);
        int max=0;
        for(int i=0;i<prices.length-1;i++) {
        	if(prices[i]>getMax(prices, i+1, prices.length-1, t))
        		continue;
        	int tmp=getMax(prices, i+1, prices.length-1, t)-prices[i];
        	max=Math.max(max, tmp);
        }
        System.out.println(max);
        return max;
    }
	
	int getMax(int[] arr,int i,int j,int[] t) {
		if(i==j) {
			if(t[i]==-1)
				t[i]=arr[i];
		}
		
		if(t[i]== -1) {
			t[i]=Math.max(arr[i], getMax(arr, i+1, j, t));
		}
		return t[i];
	}
	
	public int maxProfitPeakValey(int[] prices) {
		int minPrice=Integer.MAX_VALUE;
		int maxProfit=0;
		for(int i=0;i<prices.length;i++) {
			if(minPrice>prices[i])
				minPrice=prices[i];
			else
				maxProfit=Math.max(maxProfit, prices[i]-minPrice);
		}
		return maxProfit;
	}
	
//	On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
//
//	Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
//
//	Example 1:
//	Input: cost = [10, 15, 20]
//	Output: 15
//	Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
//	Example 2:
//	Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
//	Output: 6
//	Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
	
	public int minCostClimbingStairs(int[] cost) {
        int []t=new int[cost.length];
        Arrays.fill(t, -1);
        minCostClimbingStairsUtil(t,cost,0);
        return Math.min(t[0], t[1]);
        //t[cost.length-1]=0;
        //minCostClimbingStairsUtil(t,cost,i);
//        for(int i=0;i<cost.length-1;i++) {
//        	t[i]=Math.min(cost[i]+t[i+1], b);
//        }
    }
	
	int minCostClimbingStairsUtil(int[]t,int[]cost,int i){
		if(i>cost.length-1)
			return 0;
		if(i==cost.length-1)
			t[i]=cost[i];
		if(t[i]==-1) {
			t[i]=Math.min(cost[i]+minCostClimbingStairsUtil(t, cost,i+1),cost[i]+minCostClimbingStairsUtil(t, cost, i+2));
		}
		return t[i];
	}
	
//	Given a string s and a string t, check if s is subsequence of t.
//
//	A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
//
//	Follow up:
//	If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
//
//	Credits:
//	Special thanks to @pbrother for adding this problem and creating all test cases.
//
//	 
//
//	Example 1:
//
//	Input: s = "abc", t = "ahbgdc"
//	Output: true
//	Example 2:
//
//	Input: s = "axc", t = "ahbgdc"
//	Output: false
	 public boolean isSubsequence(String s, String t) {
	        int [][] dp=new int [s.length()][t.length()];
	        for(int i=0;i<s.length()+1;i++) {
	        	for(int j=0;j<t.length()+1;j++) {
	        		if(i==0||j==0)
	        			dp[i][j]=0;
	        	}
	        }
	        
	        for(int i=1;i<s.length()+1;i++) {
	        	for(int j=1;j<t.length()+1;j++) {
	        		if(s.charAt(i)==t.charAt(j))
	        			dp[i][j]=1+dp[i-1][j-1];
	        		else
	        			dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);
	        	}
	        }
	        if(dp[s.length()][t.length()]==s.length())
	        	return true;
	        return false;
	 }
	 
//	 You are climbing a staircase. It takes n steps to reach the top.
//
//	 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//	  
//
//	 Example 1:
//
//	 Input: n = 2
//	 Output: 2
//	 Explanation: There are two ways to climb to the top.
//	 1. 1 step + 1 step
//	 2. 2 steps
	 
	 public int climbStairs(int n) {
		 int[]t=new int[n+1];
		 Arrays.fill(t, -1);
		 return climbStairsUtit(n,0,t);
	 }
	 
	 int climbStairsUtit(int n,int i,int[]t) {
		 if(i>n)
			 return 0;
		 if(i==n)
			 t[i]=1;
		 if(t[i]==-1) {
			 t[i]=climbStairsUtit(n, i+1, t)+climbStairsUtit(n, i+2, t);
		 }
		 return t[i];
	 }
	 
//	 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
//
//			 Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
	 //https://www.youtube.com/watch?v=YxuK6A3SvTs
	 //Kadane's algorithm
	 
	 public int maxSubArray(int[] nums) {
		 int maxEndingHere=0,maxSoFar=Integer.MIN_VALUE;
		 for(int i=0;i<nums.length;i++) {
			 maxEndingHere=maxEndingHere+nums[i];
			 if(maxEndingHere<nums[i])
				 maxEndingHere=nums[i];
			 if(maxSoFar<maxEndingHere)
				 maxSoFar=maxEndingHere;
		 }
		 return maxSoFar;
	 }
	 
//	 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
//
//	 Implement the NumArray class:
//
//	 NumArray(int[] nums) Initializes the object with the integer array nums.
//	 int sumRange(int i, int j) Return the sum of the elements of the nums array in the range 
//	 [i, j] inclusive (i.e., sum(nums[i], nums[i + 1], ... , nums[j]))
//	  
//
//	 Example 1:
//
//	 Input
//	 ["NumArray", "sumRange", "sumRange", "sumRange"]
//	 [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//	 Output
//	 [null, 1, -1, -3]
//
//	 Explanation
//	 NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//	 numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
//	 numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
//	 numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
	 
	 class NumArray {
		    private int[] sum;

		    public NumArray(int[] nums) {
		         if(nums.length!=0){
			      sum=new int[nums.length];
			      sum[0]=nums[0];
			      for(int i=1;i<nums.length;i++)
			    	  sum[i]=nums[i]+sum[i-1];
		            }
			    }
			    
			    public int sumRange(int i, int j) {
			    	if(i==0)
			    		return sum[j];
			    	return sum[j]-sum[i-1];
			    }
	    }
	 
//	 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//	 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
//	  
//
//	 Example 1:
//
//	 Input: nums = [1,2,3,1]
//	 Output: 4
//	 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//	              Total amount you can rob = 1 + 3 = 4.
//	 Example 2:
//
//	 Input: nums = [2,7,9,3,1]
//	 Output: 12
//	 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//	              Total amount you can rob = 2 + 9 + 1 = 12.
	 
	 public int rob(int[] nums) {
	     int []t=new int[nums.length];
	     Arrays.fill(t, -1);
	     int max=0;
	     for(int i=0;i<nums.length;i++) {
	    	 max=Math.max(max, robUtil(t, nums, i));
	     }
	     return max;
	 }
	 
	 int robUtil(int[]t,int[]nums,int i) {
		 if(i>=nums.length)
			 return 0;
		 if(i==nums.length-1)
			 t[i]=nums[i];
		 else if(t[i]==-1)
			 t[i]=nums[i]+Math.max(robUtil(t, nums, i+2), robUtil(t, nums, i+3));
		 return t[i];
	 }
//	 Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
//
//			 A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
//
//			  
//
//			 Example 1:
//
//			 Input: n = 1
//			 Output: 5
//			 Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
	 //unbound knapsack
	 //https://leetcode.com/problems/count-sorted-vowel-strings/discuss/930886/Using-Unbounded-Knapsack.
	 public int countVowelStrings(int n) {
	        char []v= {'a','e','i','o','u'};
	        int m=v.length;
	        int[][]t=new int[m+1][n+1];
	        for(int i=0;i<=m;i++) {
	        	for(int j=0;j<=n;j++) {
	        		if(j==0)
	        			t[i][j]=0;
	        		if(j==0)
	        			t[i][j]=1;
	        	}
	        }
	        
	        for(int i=1;i<=m;i++) {
	        	for(int j=1;j<=n;j++) {
	        		t[i][j]=t[i][j-1]+t[i-1][j];  //t[i][j-1] is when we are including the char at i and t[i-1][j] is for when we are not including the character.
	        	}
	        }
	        return t[m][n];
	 }
	 
//	 Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
//			 
//
//			 Example 1:
//
//			 Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
//			 Output: [[12,21,16],[27,45,33],[24,39,28]]
//			 Example 2:
//
//			 Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
//			 Output: [[45,45,45],[45,45,45],[45,45,45]]
	 //answer is in the blue copy after MCM with explanation
	 
	 public int[][] matrixBlockSum(int[][] mat, int K) {
	      int t[][]=new int [mat.length][mat[0].length];
	      int ans[][]=new int [mat.length][mat[0].length];
	      for(int i=0;i<mat.length;i++) {
	    	  for(int j=0;j<mat[0].length;j++) {
	    		  if(j==0)
	    			  t[i][j]=mat[i][j];
	    		  else
	    			  t[i][j]=mat[i][j]+t[i][j-1];
	    			  
	    	  }
	      }
	      
	      for(int i=0;i<mat.length;i++) {
	    	  for(int j=0;j<mat[0].length;j++) {
	    		 int c1=Math.min(j+K, mat[0].length-1);
	    		 int c2=Math.max(0, j-K);
	    		 int r2=Math.max(0, i-K);
	    		 int r1=Math.min(i+K, mat.length-1);
	    		 ans[i][j]=rangeSum(c1, r1, c2, r2, t);
	    	  }
	      }
	      return ans;
	 }
	 
	 int rangeSum(int c1,int r1,int c2,int r2,int t[][]) {
		 int tmp1=0,tmp2=0;
		 for(int m=r2;m<=r1;m++) {
			 tmp1 += t[m][c1];
			 if(c2-1>=0)
			 tmp2 += t[m][c2-1];
		 }
		 
		 return tmp1-tmp2;
	 }
	 
//	 Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
//	 https://leetcode.com/problems/maximal-square/
	 
	 public int maximalSquare(char[][] matrix) {
	        int m=matrix.length,n=matrix[0].length;
	        int [][]t=new int [m][n];
	        for(int i=0;i<m;i++) {
	        	for(int j=0;j<n;j++) {
	        		t[i][j]=-1;
	        	}
	        }
	        int maxSqr=Integer.MIN_VALUE;
	        for(int i=0;i<m;i++) {
	        	for(int j=0;j<n;j++) {
	        		maxSqr=Math.max(maxSqr, maximalSquareUtil(t, i, j, matrix));
	        	}
	        }
	      return maxSqr*maxSqr;  
	 }
	 
	 int maximalSquareUtil(int [][]t,int i,int j,char [][]mat) {
		 if(i<0|| j<0||i>mat.length-1||j>mat[0].length-1)
			 return 0;
		 if(mat[i][j]!='1')
			 t[i][j]=0;
		 else if (t[i][j]==-1) {
			 t[i][j]=1+Math.min(Math.min(maximalSquareUtil(t, i+1, j, mat), maximalSquareUtil(t, i, j+1, mat)), maximalSquareUtil(t, i+1, j+1, mat));
		 	}
		 return t[i][j];
		 }
//	 In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0. What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.
//
//			 An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going up, down, left, and right, and made of 1s. This is demonstrated in the diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.
//
//			 Examples of Axis-Aligned Plus Signs of Order k:
//
//			 Order 1:
//			 000
//			 010
//			 000
//
//			 Order 2:
//			 00000
//			 00100
//			 01110
//			 00100
//			 00000
	 
	 public int orderOfLargestPlusSign(int N, int[][] mines) {
		 int [][]mat=new int[N][N];
		 for(int i=0;i<N;i++) {
			 for(int j=0;j<N;j++) {
				 mat[i][j]=1;
			 }
		 }
		 for(int[]mine:mines) {
			 mat[mine[0]][mine[1]]=0;
		 }
		 int maxPlus=0;
		 for(int i=0;i<N;i++) {
			 for(int j=0;j<N;j++) {
				 int k=0;
				 while(i-k>=0&& j-k>=0&&i+k<mat.length&&j+k<mat[0].length 
						 && mat[i-k][j]==1
						 && mat[i+k][j]==1
						 && mat[i][j-k]==1
						 && mat[i][j+k]==1
						 ) {
					 k++;
				 }
				 maxPlus=Math.max(maxPlus, k);
			 }
		 }
		 return maxPlus;
	 }
	 
//	 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
//
//	 
//
//	 Example 1:
//
//	 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//	 Output: True
//	 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.//this will be done by backtracking
	 //https://www.youtube.com/watch?v=y0mS_9VH5kU
	 
	 boolean canPartitionKSubsets(int[] nums, int k) {
		 int total=0;
		 for(int i=0;i<nums.length;i++)
			 total+=nums[i];
		 if(total%k !=0)
			 return false;
		 boolean[] used=new boolean[nums.length];
		 return canPartitionKSubsetsUtil(nums, k, used, 0, total/k, 0);
	 }
	 
	 boolean canPartitionKSubsetsUtil(int[] nums, int k,boolean[] used,int index,int subsetSum,int inProgressSum) {
		 if(k==1)
			 return true;
		 if(inProgressSum>subsetSum)
			 return false;
		 if(inProgressSum==subsetSum)
			 return canPartitionKSubsetsUtil(nums, k-1, used, 0, subsetSum, 0);
		 for(int i=index;i<nums.length;i++) {
			 if(!used[i] && inProgressSum+nums[i]<=subsetSum) {
				 used[i]=true;
				 if(canPartitionKSubsetsUtil(nums, k, used, i+1, subsetSum, inProgressSum+nums[i]))
					 return true;
				 used[i]=false;
			 }
		 }
		 return false;
	 }
	 
//	 Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
//
//			 
//
//			 Example 1:
//
//			 Input: matrix =
//			 [
//			   [0,1,1,1],
//			   [1,1,1,1],
//			   [0,1,1,1]
//			 ]
//			 Output: 15
//			 Explanation: 
//			 There are 10 squares of side 1.
//			 There are 4 squares of side 2.
//			 There is  1 square of side 3.
//			 Total number of squares = 10 + 4 + 1 = 15.
	 
	 public int countSquares(int[][] matrix) {
		 int m=matrix.length,n=matrix[0].length;
	        int [][]t=new int [m][n];
	        int sqrCount=0;
	        for(int i=0;i<m;i++) {
	        	for(int j=0;j<n;j++) {
	        		if(i==0||j==0)
	        			t[i][j]=matrix[i][j];
	        		else {
	        			if(matrix[i][j]==0)
		        			t[i][j]=0;
	        			else {
	        				t[i][j]=Math.min(t[i-1][j], Math.min(t[i][j-1], t[i-1][j-1]))+1;
	        			}
	        		}
	        		
	        		sqrCount += t[i][j];
	        	}
	        }
	       return sqrCount; 
	 }
	 
//	 Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
//
//			 
//
//			 Example 1:
//
//			 Input: mat = [[1,0,1],
//			               [1,1,0],
//			               [1,1,0]]
//			 Output: 13
//			 Explanation:
//			 There are 6 rectangles of side 1x1.
//			 There are 2 rectangles of side 1x2.
//			 There are 3 rectangles of side 2x1.
//			 There is 1 rectangle of side 2x2. 
//			 There is 1 rectangle of side 3x1.
//			 Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
	 //https://www.youtube.com/watch?v=8miqwSN3EFo
	 
	 public int numSubmat(int[][] mat) {
	      int m=mat.length,n=mat[0].length;
	      int [][] rightside_one_count=new int [m][n];
	      for(int i=0;i<m;i++) {
	    	  for(int j=n-1;j>=0;j--) {//starting from 1st row last column
	    		  if(mat[i][j]==1) {
	    			 rightside_one_count[i][j]+=1+(j<n-1?rightside_one_count[i][j+1]:0);//counting no. of ones present on the right side of the selected one
	    		  }
	    	  }
	      }
	      
	      int ans=0;
	      for(int i=0;i<m;i++) {
	    	  for(int j=0;j<n;j++) {
	    		  int min_width=Integer.MAX_VALUE;
	    		  for(int k=i;k<m;k++) {//this is to count the submatrix row wise
	    			  if(mat[k][j]!=1)//this portion makes sure that if one 0 is found in a row below i then we stop.
	    				  break;
	    			  {
	    				  min_width=Math.min(min_width, rightside_one_count[k][j]);
	    				  ans+=min_width;
	    			  }
	    		  }
	    	  }
	      }
	      
	      return ans;
	 }
	 
//	 Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
//
//			 You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
//
//			 Return the maximum profit you can make.
//
//			 Example 1:
//			 Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
//			 Output: 8
//			 Explanation: The maximum profit can be achieved by:
//			 Buying at prices[0] = 1
//			 Selling at prices[3] = 8
//			 Buying at prices[4] = 4
//			 Selling at prices[5] = 9
//			 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
	 
	 //peak valley greedy
	 public int maxProfit(int[] prices, int fee) {
	   int minPrice=Integer.MAX_VALUE;
	   int maxProfit=0;
	   for(int i=0;i<prices.length;i++) {
		   if(minPrice>prices[i])
			   minPrice=prices[i];
		   else if(prices[i]>minPrice+fee) {
			   maxProfit+=prices[i]-minPrice-fee;
			   minPrice=prices[i]-fee;
		   }
	   }
	   return maxProfit;
	 }
	 
//	 Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
//
//	 Example 1:
//
//	 Input: 2
//	 Output: [0,1,1]
//	 Example 2:
//
//	 Input: 5
//	 Output: [0,1,1,2,1,2]
	 //https://www.youtube.com/watch?v=awxaRgUB4Kw
	 public int[] countBits(int num) {
	      int []ans=new int[num+1];
	      ans[0]=0;
	      for(int i=1;i<num+1;i++) {
	    	  if(i%2==0)
	    		  ans[i]=ans[i/2];
	    	  else
	    		  ans[i]=ans[i/2]+1;
	      }
	      return ans;
	 }
	 
//	 Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.
//
//	 Return the largest sum of the given array after partitioning.
//
//	  
//
//	 Example 1:
//
//	 Input: arr = [1,15,7,9,2,5,10], k = 3
//	 Output: 84
//	 Explanation: arr becomes [15,15,15,9,10,10,10]
//	 Example 2:
//
//	 Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
//	 Output: 83
//	 Example 3:
//
//	 Input: arr = [1], k = 1
//	 Output: 1
	 //https://www.youtube.com/watch?v=RNGA4gHvC7E
	 
	 public int maxSumAfterPartitioning(int[] arr, int k) {
	     int n=arr.length;
	     int []t=new int[n];
	     int max=arr[0],subArrMax=0;
	     t[0]=arr[0];
	     //forming t for 0 to k-1
	     for(int i=1;i<k;i++) {
	    	 max=Math.max(max, arr[i]);
	    	 t[i]=max*(i+1);
	     }
	     
	     //forming t for k to n-1
	     for(int i=k;i<n;i++) {
	    	 subArrMax=arr[i];
	    	 //forming subarray 
	    	 for(int subArrSize=1;subArrSize<=k;subArrSize++) {
	    		 //t[i] represents largest sum till index i of arr
	    		//so largest sum at i =largest sum at (i-2)+max(arr[i],arr[i-1])*2 ....here 2 is k i.e. subarray size is 2
	    		 subArrMax=Math.max(subArrMax, arr[i-subArrSize+1]);
	    		 t[i]=Math.max(t[i], t[i-subArrSize]+subArrMax*subArrSize);
	    	 }
	     }
	     return t[n-1];
	 }
//	 Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
//
//	 The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
//
//	 Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
//
//	 Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
//
//	  
//
//	 Example 1:
//
//	 Input: piles = [5,3,4,5]
//	 Output: true
//	 Explanation: 
//	 Alex starts first, and can only take the first 5 or the last 5.
//	 Say he takes the first 5, so that the row becomes [3, 4, 5].
//	 If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
//	 If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
//	 This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
//	 
//	 https://leetcode.com/problems/stone-game/discuss/855702/Simple-or-Intuitive-For-Interviews-or-Top-Down-%2B-Memoisation
	 
	 public boolean stoneGame(int[] piles) {
	        int n=piles.length;
	        int dp[][]=new int [n][n];
	        for(int i=0;i<n;i++)
	        	Arrays.fill(dp[i], -1);
	        stoneGameUtil(0, n-1, 0, piles, dp);
	        return dp[0][n-1]>0;
	 }
	 
	 int stoneGameUtil(int start,int end,int player,int[] piles,int dp[][]) {
		 if(start>end)
			 return 0;
		 if(start==end)//only one element
			 return piles[start];
		 int stones;
		 if(dp[start][end]!=-1)
			 return dp[start][end];
		 if(player==0)// '0' is Alex
			 stones=Math.max(piles[start]+stoneGameUtil(start+1, end, 1, piles, dp), 
					 			piles[end]+stoneGameUtil(start, end-1, 1, piles, dp));
		 else// Lee plays and deducts our points, since its optimal play we take the most negative value
			 stones=Math.min(-piles[start]+stoneGameUtil(start+1, end, 0, piles, dp),
					 			-piles[end]+stoneGameUtil(start, end-1, 0, piles, dp));
		 dp[start][end]=stones;
		 return stones;
	 }
//	 Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones. 
//
//	 Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
//
//	 On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
//
//	 The game continues until all the stones have been taken.
//
//	 Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
//
//	  
//
//	 Example 1:
//
//	 Input: piles = [2,7,9,4,4]
//	 Output: 10
//	 Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total.
//	 If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. 
//	 So we return 10 since it's larger.
	 
//	 in the solution we are trying to find the min sum other player will have for X value which falls between 1 to 2M where current player taking ith piles 
//	 and other player will take i+X th piles.We are creating an array with same as size of piles which will have sum of element in piles from end i.e. 
//	 sum[n-1]=piles [n-1] whereas n is length of both array.So sum[i]=sum[i+1]+piles[i].we aill take a matrix of nXn for memoization where for ith piles we 
//	 can take up to 2j piles.So dp[i][j] represent the max value a player can have while taking piles from ith iex with M=j.So at the end we have to return
//	 dp[0][1] as Alex will start taking piles from 0th index and initially M=1.
	 //https://www.youtube.com/watch?v=6hu5G-abkdg
	 public int stoneGameII(int[] piles) {
		 int sum=0;
		 int [][]dp=new int [101][201];
		 for(int i=0;i<dp.length;i++)
			 Arrays.fill(dp[i], -1);
		 for(int i=0;i<piles.length;i++)
			 sum+=piles[i];
		 int diff=stoneGameIIUtil(0, 1, piles, dp);
		 return (diff+sum)/2;
	 }
	 
	 int stoneGameIIUtil(int start,int M,int []piles,int dp[][]) {
		if(start>=piles.length)
			return 0;
		if(dp[start][M]!=-1)
			return dp[start][M];
		else {
			int ans=Integer.MIN_VALUE,tot=0;
			for(int j=0;j<2*M;j++) {
				if(start+j<piles.length)
					tot+=piles[start+j];
				ans=Math.max(ans, tot-stoneGameIIUtil(start+j+1, Math.max(M, j+1), piles, dp));
			}
			return dp[start][M]=ans;
		}
	 }
	 
//	 Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which
//	 is an integer given in the array stoneValue.
//
//	 Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from 
//	 the first remaining stones in the row.
//
//	 The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
//
//	 The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie.
//	 The game continues until all the stones have been taken.
//
//	 Assume Alice and Bob play optimally.
//
//	 Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
	 //https://www.youtube.com/watch?v=HsY3jFyaePU
	 public String stoneGameIII(int[] stoneValue) {
	      int []dp=new int [50000];
	      Arrays.fill(dp, -1);
	      int alice=helper(stoneValue, 0, dp);
	      if(alice>0) return "Alice";
	      if(alice==0) return "Tie";
	      return "Bob";
	 }
	 
	 int helper(int[] stoneValue,int start,int[]dp) {
		 if(start>=stoneValue.length)
			 return 0;
		 if(dp[start]==-1) {
			 int ans=Integer.MIN_VALUE;
			 ans=Math.max(ans, stoneValue[start]-helper(stoneValue, start+1, dp));
			 if(start+1<stoneValue.length)
				 ans=Math.max(ans, stoneValue[start]+stoneValue[start+1]-helper(stoneValue, start+2, dp));
			 if(start+2<stoneValue.length)
				 ans=Math.max(ans, stoneValue[start]+stoneValue[start+1]+stoneValue[start+2]-helper(stoneValue, start+3, dp));
			 return dp[start]=ans;
		 }
		 return dp[start];
	 }
	 
//	 Given a square array of integers A, we want the minimum sum of a falling path through A.
//
//	 A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
//
//	  
//
//	 Example 1:
//
//	 Input: [[1,2,3],[4,5,6],[7,8,9]]
//	 Output: 12
//	 Explanation: 
//	 The possible falling paths are:
//	 [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
//	 [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
//	 [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
	 public int minFallingPathSum(int[][] A) {
		 int n=A.length;
	    int [][]t=new int [n][n]; 
	    for(int i=0;i<n;i++)
	    	Arrays.fill(t[i], -1);
	    int minSum=Integer.MAX_VALUE;
	    for(int j=0;j<n;j++)
	    	minSum=Math.min(minSum, minFallingPathSumUtil(t, A, 0, j));
	    return minSum;
	 }
	 
	 int minFallingPathSumUtil(int [][]t,int [][]A,int i,int j) {
		 if(i<0|| j<0||i>A.length-1||j>A[0].length-1)
			 return Integer.MAX_VALUE;
		 if(i==A.length-1)
			 return A[i][j];
		
		 if(t[i][j]==-1) {
		 int min=Integer.MAX_VALUE;
		 for(int m=j-1;m<=j+1;m++)
			 min=Math.min(min, minFallingPathSumUtil(t, A, i+1, m));
		 t[i][j]=min;
		 }
		 return t[i][j];
	 }
	 
//	 n a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
//
//	 Train tickets are sold in 3 different ways:
//
//	 a 1-day pass is sold for costs[0] dollars;
//	 a 7-day pass is sold for costs[1] dollars;
//	 a 30-day pass is sold for costs[2] dollars.
//	 The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
//
//	 Return the minimum number of dollars you need to travel every day in the given list of days.
//
//	  
//
//	 Example 1:
//
//	 Input: days = [1,4,6,7,8,20], costs = [2,7,15]
//	 Output: 11
//	 Explanation: 
//	 For example, here is one way to buy passes that lets you travel your travel plan:
//	 On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
//	 On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
//	 On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
//	 In total you spent $11 and covered all the days of your travel.
	 
//	 For each day, if you don't have to travel today, then it's strictly better to wait to buy a pass. If you have to travel today, you have up to 3 choices:
//		 you must buy either a 1-day, 7-day, or 30-day pass.
//
//	 We can express those choices as a recursion and use dynamic programming. Lets 
//	 say dp(i) is the cost to fulfill your travel plan from day i to the end of the plan. Then, if you have to travel today, your cost is:
//
//	 {dp}(i) = min(\text{dp}(i+1) + \text{costs}[0], \text{dp}(i+7) + \text{costs}[1], \text{dp}(i+30) + \text{costs}[2])dp(i)=min(dp(i+1)+costs[0],dp(i+7)+costs[1],dp(i+30)+costs[2])
//			 https://leetcode.com/problems/minimum-cost-for-tickets/solution/
	 
	 public int mincostTickets(int[] days, int[] costs) {
	        int [] dp=new int[366];
	        Set<Integer> daySet=new HashSet<Integer>();
	        for(int i:days)
	        	daySet.add(i);
	        return mincostTicketsUtil(1, days, costs, dp, daySet);
	 }
	 
	 int mincostTicketsUtil(int day,int[] days, int[] cost,int [] dp,Set<Integer> daySet) {
		 if(day>365)
			 return 0;
		 if(dp[day]!=0)
			 return dp[day];
		 int ans;
		 if(daySet.contains(day)) {
			 ans=Math.min(mincostTicketsUtil(day+1, days, cost, dp, daySet)+cost[0], 
					 mincostTicketsUtil(day+7, days, cost, dp, daySet)+cost[1]);
			 ans=Math.min(ans, mincostTicketsUtil(day+30, days, cost, dp, daySet)+cost[2]);
		 }else
			 ans=mincostTicketsUtil(day+1, days, cost, dp, daySet);
		 return ans;
		 
	 }
	 //accepted answer
	 public int mincostTicketsNew(int[] days, int[] costs) {
	        int[] dp = new int[365+30+1];
	        Set set = new HashSet<>();
	        for (int i:days) 
	            set.add(i);
	        for (int i = 365; i >=1; i--) {
	            if (!set.contains(i)){
	                dp[i] = dp[i+1];
	                continue;
	            }
	            dp[i] = Math.min(dp[i+1] + costs[0], dp[i+7] + costs[1]);
	            dp[i] = Math.min(dp[i], dp[i+30] + costs[2]);
	        }
	        return dp[1];
	    }
//	 We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
//
//	 We want to place these books in order onto bookcase shelves that have total width shelf_width.
//
//	 We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), 
//	 then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books 
//	 we just put down.  We repeat this process until there are no more books to place.
//
//	 Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  
//	 For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
//
//	 Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
//	 Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
//			 Output: 6
//			 Explanation:
//			 The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
//			 Notice that book number 2 does not have to be on the first shelf.
//	 https://leetcode.com/problems/filling-bookcase-shelves/
//		 MCM

	 
//	 public int minHeightShelves(int[][] books, int shelf_width) {
//		 	int [][]t=new int[20][20];
//		 	for(int i=0;i<t.length;i++)
//		 		Arrays.fill(t[i], -1);
//	       return minHeightShelvesUtil(books, 0, books.length-1, shelf_width,t);
//	 }
//	 
//	 int minHeightShelvesUtil(int[][] books,int i,int j,int shelf_width,int [][]t) {
//		 if(i>j)
//			 return 0;
//		 if(i==j) {
//			 if(books[i][0]<=shelf_width) {
//				 t[i][j]=books[i][1];
//				 
//			 }
//			 else {
//				 t[i][j]=0;
//			 }
//			 return t[i][j];
//		 }
//		 if(t[i][j]!=-1)
//			 return t[i][j];
//		 int totalWidth=0;
//		 int maxHeight=Integer.MIN_VALUE;
//		 for(int m=i;m<=j;m++) {
//			 totalWidth+=books[m][0];
//			 if(totalWidth>shelf_width)
//				 break;
//			 maxHeight=Math.max(maxHeight, books[m][1]);
//		 }
//		 if(totalWidth<=shelf_width) {
//			 t[i][j]=maxHeight;
//			 return maxHeight;
//		 }
//		 int ans=Integer.MAX_VALUE;
//		 for(int k=i;k<=j;k++) {
//			 int tmpAns=(minHeightShelvesUtil(books, i, k, shelf_width,t)+minHeightShelvesUtil(books, k+1, j, shelf_width,t));
//			 ans=Math.min(ans, tmpAns);
//		 }
//		 t[i][j]=ans;
//		 return ans;
//	 }
	 
	 //book shelves knapsack.Here we have to choose for a single book that whether we want to keep that book in the current shelf
	 //or create a new shelf for it and then have to take the min height resulting form the above two points.The first point will
	 //only be valid if the width of chosen book is less or equal to the remaining width left in the shelf.
	 //https://leetcode.com/problems/filling-bookcase-shelves/discuss/960543/Elegant-Java-Solution-(-Easy-Recursion-%2B-Memoization)
	 public int minHeightShelves(int[][] books, int shelf_width) {
		 int n=books.length;
		 int [][]t=new int[n+1][shelf_width+1];
		 return minHeightShelvesUtil(books, 0, 0, 0, shelf_width, t);
	 }
	 
	 int minHeightShelvesUtil(int[][] books,int currIndex,int lastHeight,int lastWidth,int maxWidth,int [][]t) {
		 if(currIndex==books.length)
			 return lastHeight;
		 if(t[currIndex][lastWidth]!=0)
			 return t[currIndex][lastWidth];
		 int op1=Integer.MAX_VALUE;
		 int op2=Integer.MAX_VALUE;
		 if(lastWidth+books[currIndex][0]<=maxWidth)//we are including the book in current shelf & increasing lastHeght and last width
			 op1=minHeightShelvesUtil(books, currIndex+1,Math.max(lastHeight, books[currIndex][1]), lastWidth+books[currIndex][0], maxWidth,t);
		 op2=lastHeight+minHeightShelvesUtil(books, currIndex+1, books[currIndex][1], books[currIndex][0], maxWidth,t);
		 //putting the book in new shelf & making books height and width as the current height and width
		 t[currIndex][lastWidth]=Math.min(op1, op2);
		 return t[currIndex][lastWidth];
	 }
//	 Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
//
//	 For example, given the following triangle
//
//	 [
//	      [2],
//	     [3,4],
//	    [6,5,7],
//	   [4,1,8,3]
//	 ]
//	 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//	 
	 public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
	        int n=triangle.get(triangle.size()-1).size();
	        int m=triangle.size();
	        int [][]t=new int[m][n];
	        int ans=Integer.MAX_VALUE;
	        for(int i=0;i<triangle.get(0).size();i++)
	        	ans=Math.min(ans, minimumTotalUtil(triangle, t, 0, i));
	        return ans;
	 }
	 
	 int minimumTotalUtil(ArrayList<ArrayList<Integer>> triangle,int [][]t,int i,int j) {
		 if(i==triangle.size()-1)
			 return triangle.get(i).get(j);
		 if(t[i][j]!=0)
			 return t[i][j];
		 int point=triangle.get(i).get(j);
		 int left=minimumTotalUtil(triangle, t, i+1, j);
		 int right=minimumTotalUtil(triangle, t, i+1, j+1);
		 t[i][j]=point+Math.min(left, right);
		 return t[i][j];
	 }
	 
//	 You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element. 
//	 Notice that the index of the elements may change after the removal.
//
//	 For example, if nums = [6,1,7,4,1]:
//
//	 Choosing to remove index 1 results in nums = [6,7,4,1].
//	 Choosing to remove index 2 results in nums = [6,1,4,1].
//	 Choosing to remove index 4 results in nums = [6,1,7,4].
//	 An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
//
//	 Return the number of indices that you could choose such that after the removal, nums is fair.
//
//	  
//
//	 Example 1:
//
//	 Input: nums = [2,1,6,4]
//	 Output: 1
//	 Explanation:
//	 Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
//	 Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
//	 Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
//	 Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
//	 There is 1 index that you can remove to make nums fair.
	 //https://www.youtube.com/watch?v=vy2RNQvayKw
	 
	 public int waysToMakeFair(int[] nums) {
		 int n=nums.length;
	     int[]even=new int[nums.length];   
	     int[]odd=new int[nums.length];
	     int t=0;
	     for(int i=0;i<nums.length;i++) {
	    	 if(i%2==0)t+=nums[i];
	    	 even[i]=t;//sum of all  numbers at even index  till i
	     }
	     
	     t=0;
	     for(int i=0;i<nums.length;i++) {
	    	 if(i%2!=0)t+=nums[i];
	    	 odd[i]=t;//sum of all  numbers at odd index  till i
	     }
	     int evenSum,oddSum,ans=0;
	     for(int i=0;i<nums.length;i++) {
	    	 evenSum=0;
	    	 oddSum=0;
	    	 if(i%2==0) {
	    		 evenSum=even[i]-nums[i];//evem sum for the left part of the deleted element
	    		 evenSum +=odd[n-1]-odd[i];//even sum for right part.here odd sum becomes even sum
	    		 
	    		 oddSum=odd[i]; //left part;
	    		 oddSum+=even[n-1]-even[i];//right part
	    	 }else
	    	 {
	    		 evenSum=even[i];//left
	    		 evenSum+=odd[n-1]-odd[i];//right
	    		 
	    		 oddSum=odd[i]-nums[i]; //left part;
	    		 oddSum+=even[n-1]-even[i];//right part
	    	 }
	    	 
	    	 if(oddSum==evenSum) ans++;
	     }
	     return ans; 
	 }
	 
//	 Given an array arr of positive integers, consider all binary trees such that:
//
//		 Each node has either 0 or 2 children;
//		 The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
//		 The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
//		 Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
//
//		  
//
//		 Example 1:
//
//		 Input: arr = [6,2,4]
//		 Output: 32
//		 Explanation:
//		 There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
//
//		     24            24
//		    /  \          /  \
//		   12   4        6    8
//		  /  \               / \
//		 6    2             2   4
	 //https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/951938/Don't-overthink-about-trees.-It's-a-DPGreedy-problem.
	 
	 public int mctFromLeafValues(int[] arr) {
	        int [][]dp=new int [arr.length][arr.length];
	        for(int i=0;i<arr.length;i++)
	        	Arrays.fill(dp[i], -1);
	        return mctFromLeafValueUtil(arr, 0, arr.length-1, dp);
	        
	 }
	 
	 public int mctFromLeafValueUtil(int[] arr,int i,int j,int [][]dp) {
//		 if(i==j-1)
//			 return arr[i]*arr[j];
		 if(i==j)
			 return 0;
		 if(dp[i][j]!=-1)
			 return dp[i][j];
		 int min=Integer.MAX_VALUE;
		 for(int k=i;k<j;k++) {
			 int tempAns=mctFromLeafValueUtil(arr, i, k, dp)+mctFromLeafValueUtil(arr, k+1, j, dp)+findMax(arr, i, k)*findMax(arr, k+1, j);
			 min=Math.min(tempAns, min);
		 }
		 dp[i][j]=min;
		 return min;
	 }
	 
	 int findMax(int[] arr,int i,int j) {
		 if(i>j)
			 return arr[j];
		 if(i==j)
			 return arr[i];
		 int max=Integer.MIN_VALUE;
		 for(int m=i;m<=j;m++)
			 max=Math.max(max, arr[m]);
		 return max;
	 }
	 
//	 Given a list of words, each word consists of English lowercase letters.
//
//	 Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
//
//	 A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
//
//	 Return the longest possible length of a word chain with words chosen from the given list of words.
//
//	  
//
//	 Example 1:
//
//	 Input: words = ["a","b","ba","bca","bda","bdca"]
//	 Output: 4
//	 Explanation: One of the longest word chain is "a","ba","bda","bdca".
//	 Example 2:
//
//	 Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
//	 Output: 5
	 //https://leetcode.com/problems/longest-string-chain/discuss/969065/LIS%2BLCS-cpp-solution
	 //https://leetcode.com/problems/longest-string-chain/discuss/756217/LCS%2BLIS%2Bsorting(Easiest-to-Understand-Among-All-Solutions)
	 //LCS
	 
	 public int longestStrChain(String[] words) {
		 	Map<String,String> mp=new HashMap<String, String>();
	        //int []dp=new int[words.length];
	        for(int i=0;i<words.length;i++) {
	        	for(int j=i+1;j<words.length;i++) {
	        		if(words[i].length()==words[j].length()||Math.abs(words[i].length()-words[j].length())!=1||mp.containsKey(words[i]))
	        			continue;
	        		if(words[i].length()<words[j].length()) {
	        			if(LCS(words[i],words[j])==words[i].length())
	        				mp.put(words[i],words[j]);
	        		}else if(words[i].length()>words[j].length()) {
	        			if(LCS(words[j],words[i])==words[j].length())
	        				mp.put(words[j],words[i]);
	        		}
	        	}
	        }
	     return 1;  
	 }
	 
	 int LCS(String x,String y) {
			int m=x.length(),n=y.length();
			int[][]t=new int[m+1][n+1];
			for(int i=0;i<=m;i++) {
				for(int j=0;j<=n;j++) {
					if(i==0||j==0)
						t[i][j]=0;
				}
			}
			
			for(int i=1;i<=m;i++) {
				for(int j=1;j<=n;j++) {
					if(x.charAt(i-1)==y.charAt(j-1))
						t[i][j]=1+t[i-1][j-1];
					else
						t[i][j]=Math.max(t[i-1][j], t[i][j-1]);
				}
			}
			return t[m][n];
		}
	 
//	 Given a string, your task is to count how many palindromic substrings in this string.
//
//	 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
//
//	 Example 1:
//
//	 Input: "abc"
//	 Output: 3
//	 Explanation: Three palindromic strings: "a", "b", "c".
	 
	 public int countSubstrings(String s) {
	    int n=s.length();
	    boolean [][]dp=new boolean[n][n];
	    
	    if(n<=0)
	    	return 0;
	    int ans=0;
	    
	    for(int i=0;i<n;i++) {
	    	dp[i][i]=true;
	    	ans++; //this is for all letters as single letter is always a palindrome
	    }
	    
	    for(int i=0;i<n-1;i++) {
	    	dp[i][i+1]=(s.charAt(i)==s.charAt(i+1));//this is for substring with two same letter like aa,tt ans these are also always palindrome
	    	ans+=(dp[i][i+1]?1:0);
	    }
	    
	    //for all other substring of length 3 or more than 3
	    for(int len=3;len<=n;len++) {
	    	for(int i=0,j=i+len-1;j<n;i++,j++) {
	    		dp[i][j]=dp[i+1][j-1] && (s.charAt(i)==s.charAt(j));//dp[i][j] will be true if s.substring(i,j)is palindrome.this will only be palindrome if s.substring(i+1,j-10 is a palindrome and char at i and j are same.	    	
	    		ans+=(dp[i][j]?1:0);
	    	}
	    }
	    return ans;
	 }
	 
//	 Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
//
//	 Example 1:
//	 Input: s1 = "sea", s2 = "eat"
//	 Output: 231
//	 Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
//	 Deleting "t" from "eat" adds 116 to the sum.
//	 At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
//	 Example 2:
//	 Input: s1 = "delete", s2 = "leet"
//	 Output: 403
//	 Explanation: Deleting "dee" from "delete" to turn the string into "let",
//	 adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
//	 At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
//	 If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
	 //https://www.youtube.com/watch?v=9zrNndIG_Ks
	 public int minimumDeleteSum(String s1, String s2) {
	        int m=s1.length(),n=s2.length();
	        int t[][]=new int [m+1][n+1];
	        for(int i=0;i<=m;i++) {
	        	Arrays.fill(t[i],-1);
	        }
	        return minimumDeleteSum(s1, s2, 0, 0, t);
	 }
	 
	 int minimumDeleteSum(String s1, String s2,int i,int j,int t[][]) {
		 if(t[i][j]!=-1)
			 return t[i][j];
		 int cost=0;
		 if(i==s1.length()&&j==s2.length())
			 cost=0;
		 else if(s1.charAt(i)==s2.charAt(j))
			 cost+=minimumDeleteSum(s1, s2, i+1, j+1, t);
		 else if(i==s1.length())
			 cost+=minimumDeleteSum(s1, s2, i, j+1, t)+s2.charAt(j);
		 else if(j==s2.length())
			 cost+=minimumDeleteSum(s1, s2, i+1, j, t)+s1.charAt(i);
		 else//when char at i and j are not equal
			 {
			 int first=minimumDeleteSum(s1, s2, i+1, j, t)+s1.charAt(i);
			 int second=minimumDeleteSum(s1, s2, i, j+1, t)+s2.charAt(j);
			 cost+=Math.min(first, second);//deleting the value with minimum ascii 
			 }
		 t[i][j]=cost;
		 return cost;
	 }
	 
//	 Given an integer array nums, return the length of the longest strictly increasing subsequence.
//
//			 A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
//
//			  
//
//			 Example 1:
//
//			 Input: nums = [10,9,2,5,3,7,101,18]
//			 Output: 4
//			 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
	 //https://www.youtube.com/watch?v=mouCn3CFpgg
	 
	 public int lengthOfLIS(int[] nums) {
		 if(nums.length==1)
			 return 1;
	   int LIS[]=new int[nums.length];
	   //Arrays.fill(LIS, 1);
	   LIS[0]=1;
	   int ans=Integer.MIN_VALUE;
	   for(int i=1;i<nums.length;i++) {
		   LIS[i]=1;
		   for(int j=0;j<i;j++) {
			   if(nums[i]>nums[j]&&LIS[i]<LIS[j]+1) {
				   LIS[i]=LIS[j]+1;
			   }
			   ans=Math.max(ans, LIS[i]);
		   }
	   }
	   return ans;
	 }
	 
//	 Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
//
//			 
//
//			 Example 1:
//
//			 Input: nums = [1,2,3,4,5]
//			 Output: true
//			 Explanation: Any triplet where i < j < k is valid.
//			 Example 2:
//
//			 Input: nums = [5,4,3,2,1]
//			 Output: false
//			 Explanation: No triplet exists.
//			 Example 3:
//
//			 Input: nums = [2,1,5,0,4,6]
//			 Output: true
//			 Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
	 public boolean increasingTriplet(int[] nums) {
	        if(nums.length<3)
				 return false;
		   int LIS[]=new int[nums.length];
		   LIS[0]=1;
		   for(int i=1;i<nums.length;i++) {
			   LIS[i]=1;
			   for(int j=0;j<i;j++) {
				   if(nums[i]>nums[j]&&LIS[i]<LIS[j]+1) {
					   LIS[i]=LIS[j]+1;
	                   if(LIS[i]==3)
	                       return true;
				   }
			   }
		   }
		   return false;
	    }
	 
//	 Given an integer array nums, return the number of longest increasing subsequences.
//
//			 Notice that the sequence has to be strictly increasing.
//
//			  
//
//			 Example 1:
//
//			 Input: nums = [1,3,5,4,7]
//			 Output: 2
//			 Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
//			 Example 2:
//
//			 Input: nums = [2,2,2,2,2]
//			 Output: 5
//			 Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
	 //https://www.youtube.com/watch?v=C1DTXdF3s14
	 //https://leetcode.com/problems/number-of-longest-increasing-subsequence/solution/
	 public int findNumberOfLIS(int[] nums) {
		 if(nums.length==1)
			 return 1;
	   int LIS[]=new int[nums.length];//LIS[i] = length of increasing subsequences ending in nums[i]
	   int count[]=new int[nums.length];//count[i] = number of increasing subsequences ending in nums[i]
	   Arrays.fill(LIS, 1);
	   Arrays.fill(count, 1);
	   int LISLen=Integer.MIN_VALUE;
	   for(int i=1;i<nums.length;i++) {
		   for(int j=0;j<i;j++) {
			   if(nums[i]>nums[j]) {
				   if(LIS[i]<LIS[j]+1) {
				   LIS[i]=LIS[j]+1;
				   count[i]=count[j];
				   }else if(LIS[i]==LIS[j]+1)
					   count[i]+=count[j];
			   }
		   }
		   LISLen=Math.max(LISLen, LIS[i]);
	   }
	   int numLis=0;
	   for(int i=0;i<nums.length;i++)
		   if(LIS[i]==LISLen)
			   numLis+=count[i];
	   return numLis;
	 }
	 
	 //find length of longest increasing subsequence
	 //https://www.youtube.com/watch?v=mouCn3CFpgg
	 int lisLength(int[] arr) {
		 if(arr.length==1)
			 return 1;
		 int[] LIS=new int[arr.length];
		 int max=1;
		 Arrays.fill(LIS, 1);
		 for(int i=1;i<arr.length;i++) {
			 for(int j=0;j<i;j++) {
				 if(arr[i]>=arr[j]) {
					 if(LIS[j]+1>LIS[i])
						 LIS[i]=LIS[j]+1;
				 }
			 }
			 
			 max=Math.max(max, LIS[i]);
		 }
		 return max;
	 }
	 
	 //print LIS
	 void printLIS(int[] arr) {
		 if(arr.length==1)
			 return ;
		 int[] LIS=new int[arr.length];
		 int max=0;
		 Arrays.fill(LIS, 1);
		 for(int i=1;i<arr.length;i++) {
			 for(int j=0;j<i;j++) {
				 if(arr[i]>=arr[j]) {
					 if(LIS[j]+1>LIS[i])
						 LIS[i]=LIS[j]+1;
				 }
			 }
			 
			 max=Math.max(max, LIS[i]);
		 }
		 List<Integer>res=new ArrayList<Integer>();
		 int i=LIS.length-1;
		 while(i>=1) {
			 if(LIS[i]!=max)
				 continue;
			 res.add(arr[i]);
			 for(int j=i-1;j>=0;j--) {
				 if(LIS[i]-1==LIS[j]) {
					 res.add(arr[j]);
					 i=j;
				 }
			 }
		 }
	 }
	 
//	 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
//
//	 
//
//	 Example 1:
//
//	 Input: [23, 2, 4, 6, 7],  k=6
//	 Output: True
//	 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
//	 Example 2:
//
//	 Input: [23, 2, 6, 4, 7],  k=6
//	 Output: True
//	 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
//	 https://www.youtube.com/watch?v=3kD0nuwyPj8
//		 https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/
	
	 public boolean checkSubarraySum(int[] nums, int k) {
		 Map<Integer,Integer> mp=new HashMap<Integer, Integer>();
		 mp.put(0,-1);//key is the reminder and value is the count of how many times the reminder has come.
		 int rem=0;//reminder
		 for(int i=0;i<nums.length;i++) {
			 rem+=nums[i];
			 if(k!=0)
				 rem%=k;
			 if(mp.containsKey(rem)) {
				 if(i-mp.get(rem)>1) return true;
			 }
			else
				mp.put(rem,i);
		 }
		 return false;
	 }
	 //dp for the same,but will not work if k-0
//	 public boolean checkSubarraySum(int[] nums, int k) {
//		 if(nums==null || nums.length==0||nums.length<2)
//			 return false;
//		 int n=nums.length;
//		 int tot=0;
//		 for(int i=0;i<n;i++)
//			 tot+=nums[i];
//        if(tot==0 && k==0)
//            return true;
//        if(k==0) return true;
//		 if(tot%k==0) 
//			 return true;
//		 Map<String, Boolean> mp=new HashMap<String, Boolean>();
//		 return checkSubarraySumUtil(0, n-1, mp, nums, k);
//	 }
//	 
//	 boolean checkSubarraySumUtil(int i,int j,Map<String, Boolean> mp,int[] nums,int k) {
//		 StringBuilder sb=new StringBuilder(String.valueOf(i)+"_"+String.valueOf(j));
//		 if(mp.containsKey(sb.toString()))
//			 return mp.get(sb.toString());
//		 if(i+1==j) {
//			 if((nums[i]+nums[j])%k==0)
//				 mp.put(sb.toString(), true);
//			 else
//				 mp.put(sb.toString(), false);
//			 return mp.get(sb.toString());
//		 }
//		 
//		 boolean ans=checkSubarraySumUtil(i+1, j, mp, nums, k)||checkSubarraySumUtil(i, j-1, mp, nums, k);
//		 mp.put(sb.toString(), ans);
//		 return mp.get(sb.toString());
//	 }
	 
//	 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
//
//	 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
//
//	 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
//
//	 Example 1:
//	 Input: [[1,2], [2,3], [3,4]]
//	 Output: 2
//	 Explanation: The longest chain is [1,2] -> [3,4]
	 //Same logic as longest increasing subsequence
	 
	 public int findLongestChain(int[][] pairs) {
		 Arrays.sort(pairs, Comparator.comparingDouble(o -> o[0]));
	      int n=pairs.length;
	      int pairChainLen[]=new int [n];
	      Arrays.fill(pairChainLen, 1);
	      int ans=1;
	      for(int i=1;i<n;i++) {
	    	  for(int j=0;j<i;j++) {
	    		  if(pairs[i][0]>pairs[j][1] && pairChainLen[i]<pairChainLen[j]+1) {
	    			  pairChainLen[i]=pairChainLen[j]+1;
	    			  ans=Math.max(ans, pairChainLen[i]);
	    		  }
	    	  }
	      }
	      return ans;
	 }
	 
//	 Given an integer array arr and an integer k, modify the array by repeating it k times.
//
//	 For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
//
//	 Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
//
//	 As the answer can be very large, return the answer modulo 10^9 + 7.
//
//	  
//
//	 Example 1:
//
//	 Input: arr = [1,2], k = 3
//	 Output: 9
//	 Example 2:
//
//	 Input: arr = [1,-2,1], k = 5
//	 Output: 2
//	 Example 3:
//
//	 Input: arr = [-1,-2], k = 7
//	 Output: 0
//	  
//
//	 Constraints:
//
//	 1 <= arr.length <= 10^5
//	 1 <= k <= 10^5
//	 -10^4 <= arr[i] <= 10^4
	 //https://leetcode.com/problems/k-concatenation-maximum-sum/discuss/932992/Java-Clean-Code-or-Kadane's-Algorithm
	 
	 int kadanes(int[] arr) {
		 int maxEndingHere=0,maxSoFar=0;
		 for(int i=0;i<arr.length;i++) {
			 maxEndingHere+=arr[i];
			 if(maxEndingHere>maxSoFar)
				 maxSoFar=maxEndingHere;
			 else if(maxEndingHere<0)
				 maxEndingHere=0;
		 }
		 return maxSoFar;
	 }
	 
	 int twoArrKadane(int[]arr) {
		 int []nArr=new int[arr.length*2];
		 for(int i=0;i<arr.length;i++)
			 nArr[i]=arr[i];
		 for(int i=0;i<arr.length;i++)
			 nArr[i+arr.length]=arr[i];
		 return kadanes(nArr);
	 }
	 
	 int getArrSum(int[]arr) {
		 int s=0;
		 for(int i=0;i<arr.length;i++)
			 s+=arr[i];
		 return s;
	 }
	 public int kConcatenationMaxSum(int[] arr, int k) {
	    long res=0,x=10000007,sum=getArrSum(arr); 
	    if(k==1)
	    	res=kadanes(arr);
	    else if(sum<0)
	    	res=twoArrKadane(arr);
	    else
	    	res=twoArrKadane(arr)+(k-2)*sum;
	    if(res<0) return 0;
	    else return (int) (res%x);
	 }
	 
//	 A message containing letters from A-Z can be encoded into numbers using the following mapping:
//
//		 'A' -> "1"
//		 'B' -> "2"
//		 ...
//		 'Z' -> "26"
//		 To decode an encoded message, all the digits must be mapped back into letters using the reverse of the mapping above (there may be multiple ways). 
//		 For example, "111" can have each of its "1"s be mapped into 'A's to make "AAA", or it could be mapped to "11" and "1" ('K' and 'A' respectively)
//		 to make "KA". Note that "06" cannot be mapped into 'F' since "6" is different from "06".
//
//		 Given a non-empty string num containing only digits, return the number of ways to decode it.
//
//		 The answer is guaranteed to fit in a 32-bit integer.
//
//		  
//
//		 Example 1:
//
//		 Input: s = "12"
//		 Output: 2
//		 Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
//		 Example 2:
//
//		 Input: s = "226"
//		 Output: 3
//		 Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
//		 Example 3:
//
//		 Input: s = "0"
//		 Output: 0
//		 Explanation: There is no character that is mapped to a number starting with 0. The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20".
//		 Since there is no character, there are no valid ways to decode this since all digits need to be mapped.
//		 Example 4:
//
//		 Input: s = "1"
//		 Output: 1
	 //https://www.youtube.com/watch?v=chW-M7oCAxc
	 
	 	public int numDecodings(String s) {
	 		 if(s.charAt(0)=='0')
	             return 0;
	      int[] dp=new int[s.length()+1];//one extra space to store value for empty string
	      dp[0]=1;
	      if(s.charAt(0)!=0)
	    	  dp[1]=1;
	      for(int i=2;i<=s.length();i++) {//starting from i=2 because we have already covered dp 0 and 1
	    	  int firstCut=Integer.valueOf(s.substring(i-1,i));
	    	  int secondCut=Integer.valueOf(s.substring(i-2,i));
	    	  
	    	  if(firstCut>0)
	    		  dp[i]=dp[i-1];
	    	  if(secondCut>=10 && secondCut<=26)
	    		  dp[i]=dp[i]+dp[i-2];
	      }
	      return dp[s.length()];
	    }
//	 	Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
//
//	 		'?' Matches any single character.
//	 		'*' Matches any sequence of characters (including the empty sequence).
//	 		The matching should cover the entire input string (not partial).
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: s = "aa", p = "a"
//	 		Output: false
//	 		Explanation: "a" does not match the entire string "aa".
//	 		Example 2:
//
//	 		Input: s = "aa", p = "*"
//	 		Output: true
//	 		Explanation: '*' matches any sequence.
//	 		Example 3:
//
//	 		Input: s = "cb", p = "?a"
//	 		Output: false
//	 		Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
//	 		Example 4:
//
//	 		Input: s = "adceb", p = "*a*b"
//	 		Output: true
//	 		Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
//	 		Example 5:
//
//	 		Input: s = "acdcb", p = "a*c?b"
//	 		Output: false
//	 		 
//
//	 		Constraints:
//
//	 		0 <= s.length, p.length <= 2000
//	 		s contains only lowercase English letters.
//	 		p contains only lowercase English letters, '?' or '*'.
	 	
	 	public boolean isMatch(String s, String p) {
	 		Boolean dp[][]=new Boolean[s.length()][p.length()];
	 		return  wm(s, p, 0, 0, dp);
	 	}
	 	
	 	boolean wm(String s, String p,int i,int j,Boolean [][]dp) {
	 		if(i==s.length() && j==p.length())
	 			return true;
	 		if(i==s.length()) {
	 			for(int index=j;index<p.length();index++) {
	 				if(p.charAt(index)!='*')
	 					return false;
	 			}
	 			return true;
	 		}
	 		if(j==p.length() && i!=s.length())
	 			return false;
	 		if(dp[i][j]!=null)
	 			return dp[i][j];
	 		boolean ans=false;
	 		if(p.charAt(j)=='?') {
	 			if(wm(s, p, i+1, j+1, dp))
	 				ans=true;
	 		}
	 		else if(p.charAt(j)=='*') {
	 			if(wm(s, p, i, j+1, dp))
	 				ans=true;
	 			if(wm(s,p,i+1,j+1,dp))
	 				ans=true;
	 			if(wm(s, p, i+1, j, dp))
	 				ans=true;
	 		}
	 		else {
	 			if(s.charAt(i)==p.charAt(j) && wm(s, p, i+1, j+1, dp))
	 				ans=true;
	 		}
	 		dp[i][j]=ans;
	 		return ans;
	 	}
	 	
//	 	Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 
//
//	 		'.' Matches any single character.​​​​
//	 		'*' Matches zero or more of the preceding element.
//	 		The matching should cover the entire input string (not partial).
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: s = "aa", p = "a"
//	 		Output: false
//	 		Explanation: "a" does not match the entire string "aa".
//	 		Example 2:
//
//	 		Input: s = "aa", p = "a*"
//	 		Output: true
//	 		Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
//	 		Example 3:
//
//	 		Input: s = "ab", p = ".*"
//	 		Output: true
//	 		Explanation: ".*" means "zero or more (*) of any character (.)".
//	 		Example 4:
//
//	 		Input: s = "aab", p = "c*a*b"
//	 		Output: true
//	 		Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
//	 		Example 5:
//
//	 		Input: s = "mississippi", p = "mis*is*p*."
//	 		Output: false
//	 		 
//
//	 		Constraints:
//
//	 		0 <= s.length <= 20
//	 		0 <= p.length <= 30
//	 		s contains only lowercase English letters.
//	 		p contains only lowercase English letters, '.', and '*'.
//	 		It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
	 	//https://leetcode.com/problems/regular-expression-matching/solution/
	 	
	 	public boolean isMatchRegex(String s, String p) {
	 		Boolean dp[][]=new Boolean[s.length()+1][p.length()+1];
	 		return rm(s, p, 0, 0, dp);
	    }
	 	
	 	boolean rm(String s, String p,int i,int j,Boolean [][]dp) {
	 		if (dp[i][j] != null) {
	            return dp[i][j] == true;
	        }
	        boolean ans;
	        if (j == p.length()){
	            ans = i == s.length();
	        } else{
	            boolean first_match = (i < s.length() &&
	                                   (p.charAt(j) == s.charAt(i) ||
	                                    p.charAt(j) == '.'));

	            if (j + 1 < p.length() && p.charAt(j+1) == '*'){
	                ans = (rm( s, p,i, j+2,dp) ||
	                       first_match && rm(s, p,i+1, j,dp));
	            } else {
	                ans = first_match && rm(s, p,i+1, j+1,dp);
	            }
	        }
	        dp[i][j] = ans ? true : false;
	        return ans;
	 	}
//	 	Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion.
//	 			In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element
//	 			left and the sum of the remaining elements is maximum possible.
//
//	 			Note that the subarray needs to be non-empty after deleting one element.
//
//	 			 
//
//	 			Example 1:
//
//	 			Input: arr = [1,-2,0,3]
//	 			Output: 4
//	 			Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
//	 			Example 2:
//
//	 			Input: arr = [1,-2,-2,3]
//	 			Output: 3
//	 			Explanation: We just choose [3] and it's the maximum sum.
//	 			Example 3:
//
//	 			Input: arr = [-1,-1,-1,-1]
//	 			Output: -1
//	 			Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty
//	 			subarray to make the sum equals to 0.
	 	//https://www.cnblogs.com/lz87/p/11525139.html
	 	
	 	public int maximumSum(int[] arr) {
	 		int n = arr.length, res = arr[0];
	        int[] maxEndAt = new int[n];
	        int[] maxStartAt = new int[n];
	        maxEndAt[0] = arr[0];
	        for(int i = 1; i < n; i++) {
	            maxEndAt[i] = Math.max(maxEndAt[i - 1] + arr[i], arr[i]);
	            res = Math.max(res, maxEndAt[i]);
	        }
	        maxStartAt[n - 1] = arr[n - 1];
	        for(int i = n - 2; i >= 0; i--) {
	            maxStartAt[i] = Math.max(arr[i] + maxStartAt[i + 1], arr[i]);
	        }
	        for(int i = 1; i < n - 1; i++) {
	            if(arr[i] < 0) {
	                res = Math.max(res, maxEndAt[i - 1] + maxStartAt[i + 1]);
	            }            
	        }
	        return res;
	 	}
	 	
//	 	A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between 
//	 	positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer 
//	 	than two elements is trivially a wiggle sequence.
//
//	 	For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. 
//	 	In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and 
//	 	the second because its last difference is zero.
//
//	 	Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence 
//	 			is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving 
//	 			the remaining elements in their original order.
//
//	 	Example 1:
//
//	 	Input: [1,7,4,9,2,5]
//	 	Output: 6
//	 	Explanation: The entire sequence is a wiggle sequence.
//	 	Example 2:
//
//	 	Input: [1,17,5,10,13,15,10,5,16,8]
//	 	Output: 7
//	 	Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
//	 	Example 3:
//
//	 	Input: [1,2,3,4,5,6,7,8,9]
//	 	Output: 2
	 	//https://leetcode.com/problems/wiggle-subsequence/solution/
	 	//greedy approach
	 	
	 	public int wiggleMaxLength(int[] nums) {
	        if(nums.length<2)
	        	return nums.length;
	        int prevdiff=nums[1]-nums[0];
	        int count=prevdiff!=0?2:1;
	        for(int i=1;i<nums.length;i++) {
	        	int diff=nums[i+1]-nums[i];
	        	if(diff>0 && prevdiff<=0 || diff<0 && prevdiff>=0) {
	        		count++;
	        		prevdiff=diff;
	        	}
	        }
	        return count;
	    }
//	 	You may recall that an array arr is a mountain array if and only if:
//
//	 		arr.length >= 3
//	 		There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
//	 		arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//	 		arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//	 		Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: arr = [2,1,4,7,3,2,5]
//	 		Output: 5
//	 		Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
//	 		Example 2:
//
//	 		Input: arr = [2,2,2]
//	 		Output: 0
//	 		Explanation: There is no mountain.
//	 		 
//
//	 		Constraints:
//
//	 		1 <= arr.length <= 104
//	 		0 <= arr[i] <= 104
	 	//https://leetcode.com/problems/longest-mountain-in-array/solution/
	 	
	 	public int longestMountain(int[] arr) {
	        if(arr.length<3)
	        	return 0;
	        int base=0,end,n=arr.length,ans=0;
	        while(base<n) {
	        	end=base;
	        	if(end+1<n && arr[end]<arr[end+1]) {
	        		while(end+1<n && arr[end]<arr[end+1])
	        			end++;
	        		if(end+1<n && arr[end]>arr[end+1]) {
		        		while(end+1<n && arr[end]>arr[end+1])
		        			end++;
		        		ans=Math.max(ans, end-base+1);
		        	}
	        	}
	        	base=Math.max(end, base+1);
	        	
	        }
	        return ans;
	    }
//	 	Given an array nums of size n, return the majority element.
//
//	 			The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element 
//	 			always exists in the array.
//
//	 			 
//
//	 			Example 1:
//
//	 			Input: nums = [3,2,3]
//	 			Output: 3
//	 			Example 2:
//
//	 			Input: nums = [2,2,1,1,1,2,2]
//	 			Output: 2
	 	//https://www.youtube.com/watch?v=n5QY3x_GNDg
	 	
	 	public int majorityElement(int[] nums) {
	        int maj_element=nums[0],count=1;
	        //finding the candidate for majority
	        for(int i=1;i<nums.length;i++) {
	        	if(maj_element==nums[i])
	        		count++;
	        	else {
	        		count--;
	        		if(count==0) {
	        			maj_element=nums[i];
	        			count=1;
	        		}
	        	}
	        }
	        int c=0;
	        //verifying whether the candidate is valid or not
	        for(int i=0;i<nums.length;i++) {
	        	if(nums[i]==maj_element)
	        		c++;
	        }
	        
	        return c>Math.floor(nums.length/2)?maj_element:0;
	    }
	 	
//	 	Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
//
//	 	Follow-up: Could you solve the problem in linear time and in O(1) space?
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: nums = [3,2,3]
//	 	Output: [3]
//	 	Example 2:
//
//	 	Input: nums = [1]
//	 	Output: [1]
//	 	Example 3:
//
//	 	Input: nums = [1,2]
//	 	Output: [1,2]
	 	//https://www.youtube.com/watch?v=Wjocm-7jU4Q
	 	//https://leetcode.com/problems/majority-element-ii/discuss/1011894/Generalised-Boyer-Moore-Voting.-Majority-Element-Problem.-Floor(Nk)-less-Frequency(Majority-Element)
	 	public List<Integer> majorityElementTwo(int[] nums) {
	        int maj1=0,maj2=0,count1=0,count2=0;
	        for(int num:nums) {
	        	if(maj1==num)
	        		count1++;
	        	else if(maj2==num)
	        		count2++;
	        	else if(count1==0) {
	        		maj1=num;
	        		count1++;
	        	}
	        	else if(count2==0) {
	        		maj2=num;
	        		count2++;
	        	}else {
	        		count1--;
	        		count2--;
	        	}
	        }
	        
	        int validFreq=(int) Math.floor(nums.length/3),vc1=0,vc2=0;
	        List<Integer> res=new ArrayList<Integer>();
	        for(int i=0;i<nums.length;i++) {
	        	if(maj1==nums[i])
	        		vc1++;
	        	else if(maj2==nums[i])
	        		vc2++;
	        }
	        
	        if(vc1>validFreq)
	        	res.add(maj1);
	        if(vc2>validFreq)
	        	res.add(maj2);
	        
	        return res;
	    }
	 	
//	 	Given a string s, remove duplicate letters so that every letter appears once and only once. 
//	 	You must make sure your result is the smallest in lexicographical order among all possible results.
//
//	 	Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: s = "bcabc"
//	 	Output: "abc"
//	 	Example 2:
//
//	 	Input: s = "cbacdcbc"
//	 	Output: "acdb"
	 	//https://www.youtube.com/watch?v=muDlIlVE1q4
	 	
	 	 public String removeDuplicateLetters(String s) {
	 		 int [] lastIndex=new int[26];
	 		 boolean []visited=new boolean[26];
	 		 Arrays.fill(visited, false);
	 		 for(int i=0;i<s.length();i++)
	 			 lastIndex[s.charAt(i)-'a']=i;
	 		 Stack<Character> st=new Stack<Character>();
	 		 for(int i=0;i<s.length();i++) {
	 			 char c=s.charAt(i);
	 			 if(visited[c-'a']==false) {
	 				 visited[c-'a']=true;
	 			 while(!st.empty() && st.peek()>c && lastIndex[st.peek()-'a']>i) {
	 				 visited[st.peek()-'a']=false;
	 				 st.pop();
	 			 }
	 			st.add(c);
	 			 }
	 		 }
	 		 
	 		 StringBuffer sb=new StringBuffer();
	 		 while(!st.empty())
	 			 sb.append(st.pop());
	 		 
	 		 return sb.reverse().toString();
	 	 }
	 	 
	 	 //remove duplicates from a string and still maintain the order of the resulting string.
	 	 //eg- abcbad --> abcd
	 	 
	 	 public String removeDuplicate(String s) {
	 		boolean []visited=new boolean[26];
	 		 Arrays.fill(visited, false);
	 		StringBuffer sb=new StringBuffer();
	 		for(char c:s.toCharArray()) {
	 			if(visited[c-'a']==false) {
	 				visited[c-'a']=true;
	 				sb.append(c);
	 			}	
	 		}
	 		return sb.toString();
	 	 }
	 	 
//	 	Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. 
//	 	Return the linked list sorted as well.
	 	//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
	 	 //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/solution/
	 	 
	 	 
	 	public class ListNode {
	 		      int val;
	 		      ListNode next;
	 		      ListNode() {}
	 		      ListNode(int val) { this.val = val; }
	 		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 		  }
	 		    public ListNode deleteDuplicates(ListNode head) {
	 		       ListNode sentinel=new ListNode(0, head);
	 		       ListNode pred=sentinel;
	 		       while(head!=null) {
	 		    	   if(head.next!=null && head.val==head.next.val) {
	 		    		   while(head.next!=null && head.val==head.next.val)
	 		    			   head.next=head.next.next;
		 		    	   pred.next=head.next;
	 		    	   }else
	 		    		   pred=pred.next;
	 		    	   head=head.next;
	 		       }
	 		       return sentinel.next;
	 		   }
	 
//	 		   We have two integer sequences A and B of the same non-zero length.
//
//	 		  We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.
//
//	 		  At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
//
//	 		  Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always
//	 				  makes it possible.
//
//	 		  Example:
//	 		  Input: A = [1,3,5,4], B = [1,2,3,7]
//	 		  Output: 1
//	 		  Explanation: 
//	 		  Swap A[3] and B[3].  Then the sequences are:
//	 		  A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
//	 		  which are both strictly increasing.
	 		    //https://www.youtube.com/watch?v=mLTF2UXkd2o
	 		    
	 		   public int minSwap(int[] A, int[] B) {
	 		        if(A.length==1)
	 		        	return 0;
	 		        int[]noSwp=new int[A.length];
	 		        int[]swp=new int[A.length];
	 		        noSwp[0]=0;swp[0]=1;
	 		        for(int i=1;i<A.length;i++) {
	 		        	noSwp[i]=Integer.MAX_VALUE;swp[i]=Integer.MAX_VALUE;
	 		        	if(A[i]>A[i-1]&& B[i]>B[i-1]) {
	 		        		noSwp[i]=noSwp[i-1];
	 		        		swp[i]=swp[i-1]+1;
	 		        	}
	 		        	if(A[i]>B[i-1]&& B[i]>A[i-1]) {
	 		        		noSwp[i]=Math.min(noSwp[i], swp[i-1]);
	 		        		swp[i]=Math.min(noSwp[i-1]+1, swp[i]);
	 		        	}
	 		        }
	 		        
	 		        return Math.min(noSwp[A.length-1],swp[A.length-1]);
	 		    }
	 		   
//	 		 In LeetCode Store, there are some kinds of items to sell. Each item has a price.
//
//	 		 However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
//
//	 		 You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the 
//	 		 lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.
//
//	 		 Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, 
//	 		 other numbers represents how many specific items you could get if you buy this offer.
//
//	 		 You could use any of special offers as many times as you want.
//
//	 		 Example 1:
//	 		 Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
//	 		 Output: 14
//	 		 Explanation: 
//	 		 There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
//	 		 In special offer 1, you can pay $5 for 3A and 0B
//	 		 In special offer 2, you can pay $10 for 1A and 2B. 
//	 		 You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
//	 		 Example 2:
//	 		 Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
//	 		 Output: 11
//	 		 Explanation: 
//	 		 The price of A is $2, and $3 for B, $4 for C. 
//	 		 You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
//	 		 You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
//	 		 You cannot add more items, though only $9 for 2A ,2B and 1C.
//	 		 Note:
//	 		 There are at most 6 kinds of items, 100 special offers.
//	 		 For each item, you need to buy at most 6 of them.
//	 		 You are not allowed to buy more items than you want, even if that would lower the overall price.
	 		   //https://leetcode.com/problems/shopping-offers/solution/
	 		   
	 		  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
	 		        HashMap<List<Integer>, Integer> needsToRes=new HashMap<List<Integer>, Integer>();
	 		        return shoppingUtil(price, special, needs, needsToRes);
	 		  }
	 		  
	 		  int shoppingUtil(List<Integer> price, List<List<Integer>> special, List<Integer> needs,HashMap<List<Integer>, Integer> needsToRes) {
	 			  if(needsToRes.containsKey(needs))
	 				  return needsToRes.get(needs);
	 			  
	 			  int j=0,res=originalPrice(price, needs);
	 			  for(List<Integer> s:special) {
	 				  ArrayList<Integer> cloneNeeds=new ArrayList<Integer>(needs);
	 				  for(j=0;j<needs.size();j++) {
	 					  int diff=cloneNeeds.get(j)-s.get(j);
	 					  if(diff<0)
	 						  break;
	 					  cloneNeeds.set(j, diff);
	 				  }if(j==needs.size()) {
	 					  res=Math.min(res, s.get(j)+shoppingUtil(price, special, cloneNeeds, needsToRes));
	 				  }
	 			  }
	 			  needsToRes.put(needs,res);
	 			  return res;
	 		  }
	 		  
	 		  int originalPrice(List<Integer> price,List<Integer> needs) {
	 			  int sum=0;
	 			  for(int i=0;i<needs.size();i++)
	 				  sum+=needs.get(i)*price.get(i);
	 			  return sum;
	 		  }
//	 		 Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
//
//	 		Follow up:
//
//	 		A straight forward solution using O(mn) space is probably a bad idea.
//	 		A simple improvement uses O(m + n) space, but still not the best solution.
//	 		Could you devise a constant space solution?
//	 		 
//
//	 		Example 1:
//
//
//	 		Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//	 		Output: [[1,0,1],[0,0,0],[1,0,1]]
//	 		Example 2:
//
//
//	 		Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//	 		Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
//	 		 
//
//	 		Constraints:
//
//	 		m == matrix.length
//	 		n == matrix[0].length
//	 		1 <= m, n <= 200
//	 		-231 <= matrix[i][j] <= 231 - 1
	 		  
	 		  //https://leetcode.com/problems/set-matrix-zeroes/solution/
	 		  
	 		 public void setZeroesWithSet(int[][] matrix) {
	 	        int rows=matrix.length;
	 	        int cols=matrix[0].length;
	 	        Set<Integer> rowSet=new HashSet<Integer>();
	 	        Set<Integer> colSet=new HashSet<Integer>();
	 	        
	 	        for(int i=0;i<rows;i++) {
	 	        	for(int j=0;j<cols;j++) {
	 	        		if(matrix[i][j]==0) {
	 	        			rowSet.add(i);
	 	        			colSet.add(j);
	 	        		}
	 	        	}
	 	        }
	 	        
	 	       for(int i=0;i<rows;i++) {
	 	        	for(int j=0;j<cols;j++) {
	 	        		if(rowSet.contains(i)||colSet.contains(j)) {
	 	        			matrix[i][j]=0;
	 	        		}
	 	        	}
	 	        }
	 	    }
	 		 
	 		 //with no extra space
	 		public void setZeroes(int[][] matrix) {
	 			int rows=matrix.length;
	 	        int cols=matrix[0].length;
	 	        boolean isCol=false;
	 	        for(int i=0;i<rows;i++) {
	 	        	if(matrix[i][0]==0)
	 	        		isCol=true;
	 	        	for(int j=1;j<cols;j++) {
	 	        		if(matrix[i][j]==0) {
	 	        			matrix[i][0]=0;
	 	        			matrix[0][j]=0;
	 	        		}
	 	        	}
	 	        }
	 	        
	 	    // Iterate over the array once again and using the first row and first column, update the elements.
	 	       for (int i = 1; i < rows; i++) {
	 	         for (int j = 1; j < cols; j++) {
	 	           if (matrix[i][0] == 0 || matrix[0][j] == 0) {
	 	             matrix[i][j] = 0;
	 	           }
	 	         }
	 	       }

	 	       // See if the first row needs to be set to zero as well
	 	       if (matrix[0][0] == 0) {
	 	         for (int j = 0; j < cols; j++) {
	 	           matrix[0][j] = 0;
	 	         }
	 	       }

	 	       // See if the first column needs to be set to zero as well
	 	       if (isCol) {
	 	         for (int i = 0; i < rows; i++) {
	 	           matrix[i][0] = 0;
	 	         }
	 	       }
	 	    }
//	 		According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician 
//	 		John Horton Conway in 1970."
//
//	 		The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). 
//	 		Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia 
//	 				article):
//
//	 		Any live cell with fewer than two live neighbors dies as if caused by under-population.
//	 		Any live cell with two or three live neighbors lives on to the next generation.
//	 		Any live cell with more than three live neighbors dies, as if by over-population.
//	 		Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
//	 		The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur
//	 		simultaneously. Given the current state of the m x n grid board, return the next state.
//	 				https://leetcode.com/problems/game-of-life/

	 		public void gameOfLife(int[][] board) {
	 			int rows=board.length;
	 	        int cols=board[0].length;
	 	        int[] dir= {0,-1,1};
	 	        for(int row=0;row<rows;row++) {
	 	        	for(int col=0;col<cols;col++) {
	 	        		int livingNei=0;
	 	        		for(int i=0;i<3;i++) {
	 	        			for(int j=0;j<3;j++) {
	 	        				if(!(dir[i]==0 && dir[j]==0)) {
	 	        					int r=row+dir[i];
	 	        					int c=col+dir[j];
	 	        					if(r<rows&&r>=0&&c<cols&&c>=0&&Math.abs(board[r][c])==1)
	 	        						livingNei++;
	 	        				}
	 	        			}
	 	        		}
	 	        		if(board[row][col]==1&&(livingNei>3||livingNei<2))
	 	        			board[row][col]=-1;
	 	        		if(board[row][col]==0&&livingNei==3)
	 	        			board[row][col]=2;
	 	        	}
	 	        }
	 	        
	 	        for(int row=0;row<rows;row++) {
	 	        	for(int col=0;col<cols;col++) {
	 	        		if(board[row][col]>0)
	 	        			board[row][col]=1;
	 	        		else
	 	        			board[row][col]=0;
	 	        	}
	 	        }
	 	    }
//	 		Given a list of intervals, remove all intervals that are covered by another interval in the list.
//
//	 		Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
//
//	 		After doing so, return the number of remaining intervals.
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: intervals = [[1,4],[3,6],[2,8]]
//	 		Output: 2
//	 		Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
//	 		Example 2:
//
//	 		Input: intervals = [[1,4],[2,3]]
//	 		Output: 1
//	 		Example 3:
//
//	 		Input: intervals = [[0,10],[5,12]]
//	 		Output: 2
//	 		Example 4:
//
//	 		Input: intervals = [[3,10],[4,10],[5,11]]
//	 		Output: 2
//	 		Example 5:
//
//	 		Input: intervals = [[1,2],[1,4],[3,4]]
//	 		Output: 1
//	 		 
//
//	 		Constraints:
//
//	 		1 <= intervals.length <= 1000
//	 		intervals[i].length == 2
//	 		0 <= intervals[i][0] < intervals[i][1] <= 10^5
//	 		All the intervals are unique.
	 		//https://leetcode.com/problems/remove-covered-intervals/discuss/975648/2-Solutions-or-Easy-to-Understand-or-Sort-and-Count-overlap-intervals
	 		public int removeCoveredIntervals(int[][] intervals) {
	 			Arrays.sort(intervals, (a,b)->a[0]-b[0]);
	 			int[] currInt= {-1,-1};
	 			int result=0;
	 			for(int[] interval:intervals) {
	 				if(currInt[0]<interval[0] && currInt[1]<interval[1]) {
	 					currInt[0]=interval[0];	
	 					result++;
	 				}
	 				currInt[1]=Math.max(currInt[1], interval[1]);
	 			}
	 			return result;
	 	    }
	 		
//	 		Given the following details of a matrix with n columns and 2 rows :
//
//	 			The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
//	 			The sum of elements of the 0-th(upper) row is given as upper.
//	 			The sum of elements of the 1-st(lower) row is given as lower.
//	 			The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.
//	 			Your task is to reconstruct the matrix with upper, lower and colsum.
//
//	 			Return it as a 2-D integer array.
//
//	 			If there are more than one valid solution, any of them will be accepted.
//
//	 			If no valid solution exists, return an empty 2-D array.
//	 		Example 1:
//
//	 			Input: upper = 2, lower = 1, colsum = [1,1,1]
//	 			Output: [[1,1,0],[0,0,1]]
//	 			Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
//	 			Example 2:
//
//	 			Input: upper = 2, lower = 3, colsum = [2,2,1,1]
//	 			Output: []
//	 			Example 3:
//
//	 			Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
//	 			Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]

	 		
	 		public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
	 	        int n = colsum.length;
	 	        int[][] mat = new int[2][n];
	 	        Set<Integer> onesIndices = new HashSet<>();
	 	        for (int i = 0; i < n; i++) {
	 	            if (colsum[i] == 2) {
	 	                if (upper < 1 || lower < 1) {
	 	                    return new ArrayList<>();
	 	                }
	 	                mat[0][i] = 1;
	 	                mat[1][i] = 1;
	 	                upper--;
	 	                lower--;
	 	            }
	 	            else if (colsum[i] == 1) {
	 	                onesIndices.add(i);
	 	            }
	 	        }
	 	        for (int i = 0; i < n; i++) {
	 	            if (onesIndices.contains(i)) {
	 	                if (upper == 0 && lower == 0) {
	 	                    return new ArrayList<>();
	 	                }
	 	                else if ((upper < lower && upper > 0) || lower == 0) {
	 	                    mat[0][i] = 1;
	 	                    upper--;
	 	                }
	 	                else if ((upper >= lower && lower > 0) || upper == 0) {
	 	                    mat[1][i] = 1;
	 	                    lower--;
	 	                }
	 	            }
	 	        }
	 	        if (upper > 0 || lower > 0) {
	 	            return new ArrayList<>();
	 	        }
	 	        List<List<Integer>> res = new ArrayList<>();
	 	        for (int i = 0; i < 2; i++) {
	 	            res.add(new ArrayList<>());
	 	            for (int j = 0; j < n; j++) {
	 	                res.get(i).add(mat[i][j]);
	 	            }
	 	        }
	 	        return res;
	 	    }
//	 		Given an integer n, return the least number of perfect square numbers that sum to n.
//
//	 				A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself.
//	 				For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
//
//	 				 
//
//	 				Example 1:
//
//	 				Input: n = 12
//	 				Output: 3
//	 				Explanation: 12 = 4 + 4 + 4.
//	 				Example 2:
//
//	 				Input: n = 13
//	 				Output: 2
//	 				Explanation: 13 = 4 + 9.
	 		//https://www.youtube.com/watch?v=1xfx6M_GqFk
	 		
	 		public int numSquare(int n) {
	 			int []dp=new int[n+1];
	 			Arrays.fill(dp, -1);
	 			return numSquaresUtil(n, dp);
	 		}
	 				
	 		public int numSquaresUtil(int n,int[]dp) {
	 			if(dp[n]!=-1)
	 				return dp[n];
	 			if(n==0)
	 				dp[n]= 0;
	 			else if(n<=3)
	 				dp[n]= n;
	 			else {
	 			int a=(int) Math.sqrt(n);
	 			if(a*a==n)
	 				dp[n]=1;
	 			else {
	 				int ans=Integer.MAX_VALUE;
	 			for(int i=1;i*i<=n;i++) {
	 				ans=Math.min(ans, 1+numSquaresUtil(n-i*i, dp));
	 				}
	 			dp[n]=ans;
	 			}
	 		}
	 			return dp[n];
	 	}
	 		
//	 		Say you have an array for which the ith element is the price of a given stock on day i.
//
//	 		Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share 
//	 				of the stock multiple times) with the following restrictions:
//
//	 		You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//	 		After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//	 		Example:
//
//	 		Input: [1,2,3,0,2]
//	 		Output: 3 
//	 		Explanation: transactions = [buy, sell, cooldown, buy, sell]
	 		//Explanation
//	 		- We have three possible states 'buy, sell, cooldown'
//	 		- When we are currently at 'buy' state
//	 			- We will have two choices
//	 				- We can buy the current stock or skip the current stock
//	 				- We will pick the choice that will give us a higher profit
//	 		- When we are currently at 'sell' state
//	 			- We will have two choices
//	 				- We can sell at the current stock or skip the current stock
//	 				- We will pick the choice that will give us a higher profit
//	 		- When we are current at 'cooldown' state
//	 			- We will need to skip the current stock and change our state to 'buy'
//	 	- We can indicate the three states using three integers
//	 		- 0, buy
//	 		- 1, sell
//	 		- 2, cooldown
	 		//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/1008431/Java-Recursive-greater-Memoization-greater-2D-Bottom-Up-greater-1D-Bottom-Up
	 		
	 		public int maxProfitWithCooldown(int[] prices) {
	 			int[][] dp=new int[prices.length][3];
	 			for(int i=0;i<prices.length;i++)
	 				Arrays.fill(dp[i], -1);
	 			return maxProfitUtil(prices, 0, dp, 0);
	 		}
	 		
	 		int maxProfitUtil(int[] prices,int state,int [][]dp,int start) {
	 			if(start>=prices.length)
	 				return 0;
	 			if(dp[start][state]!=-1)
	 				return dp[start][state];
	 			
	 			if(state==2)
	 				return maxProfitUtil(prices, 0, dp, start+1);
	 			return dp[start][state]=state==0?
	 					Math.max(maxProfitUtil(prices, state, dp, start+1), maxProfitUtil(prices, 1, dp, start+1)-prices[start]):
	 					Math.max(maxProfitUtil(prices, state, dp, start+1), maxProfitUtil(prices, 2, dp, start+1)+prices[start]);
	 		}
}



	      

	

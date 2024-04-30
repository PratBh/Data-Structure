package Knapsack;

import java.util.ArrayList;
import java.util.List;

public class Problems {
//0-1 knapsack
	//recursion
	int[][] dp=new int[20][20];
	
	int knapsackR(int[] wt,int[] val,int W,int n) {
		if(W==0||n==0)
			return 0;
		for(int i=0;i<n+1;i++) {
			for(int j=0;j<W+1;j++) {
				dp[i][j]=-1;
			}
		}
		return knapsackRUtil(wt, val, W, n);
	}
	
	int knapsackRUtil(int[] wt,int[] val,int W,int n) {
		if(W==0||n==0)
			return 0;
		if(dp[n][W]!=-1) {
			if(wt[n-1]<=W)
				dp[n][W]=Math.max(val[n-1]+knapsackRUtil(wt, val, W-wt[n-1], n-1),knapsackRUtil(wt, val, W, n-1));
			else
				dp[n][W]=knapsackRUtil(wt, val, W, n-1);
		}
		return dp[n][W];
	}
	
	//0-1 knapsack top-down approach
	
	int knapsack(int[] wt,int[] val,int W,int n) {
		int [][] t=new int[n+1][W+1];
		for(int i=0;i<n+1;i++) {
			for(int j=0;j<W+1;j++) {
				if(i==0||j==0)
				t[i][j]=0;
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=W;j++) {
				if(wt[i-1]<=j) {
					t[i][j]=Math.max(val[i-1]+t[i-1][j-wt[i-1]], t[i-1][j]);
				}else
					t[i][j]=t[i-1][j];
			}
		}
		return t[n][W];
	}
	
	//Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
//	Example:
//
//		Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
//		Output:  True  //There is a subset (4, 5) with sum 9.
	
	boolean isSubsetPresent(int[] arr,int sum) {
		int n=arr.length;
		boolean[][] t=new boolean[n+1][sum+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=sum;j++) {
				if(i==0)
					t[i][j]=false;
				if(j==0)
					t[i][j]=true;
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=sum;j++) {
				if(arr[i-1]<=j)
					t[i][j]=t[i-1][j-arr[i-1]]||t[i-1][j];
				else
					t[i][j]=t[i-1][j];
			}
		}
		return t[n][sum];
	}
	
//	Equal Sum Partition Problem
//	Partition problem is to determine whether a given set can be partitioned into two subsets such that the sum of elements in both subsets is same.
//	Examples:
//
//	arr[] = {1, 5, 11, 5}
//	Output: true 
//	The array can be partitioned as {1, 5, 5} and {11}
	
	boolean equalSumPartion(int [] arr) {
		int s=0;
		int n=arr.length;
		for(int i=0;i<n;i++)
			s+=arr[i];
		if(s%2 != 0)
			return false;
		int W=s/2;
		return isSubsetPresent(arr, W);
	}
	
//	Count of subsets sum with a Given sum
//	Given an array arr[] of length N and an integer X, the task is to find the number of subsets with sum equal to X.
//	Example:
//
//	Input: arr[] = {1, 2, 3, 3}, X = 6
//	Output: 3
//	All the possible subsets are {1, 2, 3},
//	{1, 2, 3} and {3, 3}
	
	int countSubset(int[] arr,int sum) {
		int n=arr.length;
		int [][]t=new int[n+1][sum+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=sum;j++) {
				if(i==0)
					t[i][j]=0;
				if(j==0)
					t[i][j]=1;
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=sum;j++) {
				if(arr[i-1]<j)
					t[i][j]=t[i-1][j-arr[i-1]]+t[i-1][j];
				else
					t[i][j]=t[i-1][j];
			}
		}
		return t[n][sum];
	}
	
//	Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.
//	If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and the value of 
	//abs(sum(Subset1) – sum(Subset2)) should be minimum.
//
//	Example:
//	Input:  arr[] = {1, 6, 11, 5} 
//	Output: 1
//	Explanation:
//	Subset1 = {1, 5, 6}, sum of Subset1 = 12 
//	Subset2 = {11}, sum of Subset2 = 11
	
	int findMinSubsetDiff(int[] arr) {
		int range=0;
		int n=arr.length;
		for(int i=0;i<n;i++)
			range+=arr[i];
		//code of subset sum
		boolean[][] t=new boolean[n+1][range+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=range;j++) {
				if(i==0)
					t[i][j]=false;
				if(j==0)
					t[i][j]=true;
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=range;j++) {
				if(arr[i-1]<=j)
					t[i][j]=t[i-1][j-arr[i-1]]||t[i-1][j];
				else
					t[i][j]=t[i-1][j];
			}
		}
		
		List<Integer> sums=new ArrayList();
		int ans=Integer.MAX_VALUE;
		for(int j=0;j<=range/2;j++) {
			if(t[n][j])
			sums.add(j);
		}
		for(int i:sums) {
			ans=Math.min(ans, range-2*i);
		}
		return ans;
	}
	
	//Count the number of subset with a given difference
	int countSubsetDiff(int[] arr,int diff) {
		int range=0;
		int n=arr.length;
		for(int i=0;i<n;i++)
			range+=arr[i];
		
		int s1=(range+diff)/2;
		return countSubset(arr, s1);
	}
	
//	Target Sum Problem
//	Given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, 
	//you should choose one from + and - as its new symbol.
//
//	Find out how many ways to assign symbols to make sum of integers equal to target S.
//
//	Example 1:
//	Input: nums is [1, 1, 1, 1, 1], S is 3. 
//	Output: 5
//	Explanation: 
//
//	-1+1+1+1+1 = 3
//	+1-1+1+1+1 = 3
//	+1+1-1+1+1 = 3
//	+1+1+1-1+1 = 3
//	+1+1+1+1-1 = 3
//
//	There are 5 ways to assign symbols to make the sum of nums be target 3.
	//this is same as subset sum diff problem
	
	
	//-----------------Unbounded Knapsack------------------------
//	Rod Cutting Problem
//	 Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. Determine the  maximum value obtainable by cutting up the rod and selling the pieces. 
//	Example: 
//	if length of the rod is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
//
//
//	length   | 1   2   3   4   5   6   7   8  
//	--------------------------------------------
//	price    | 1   5   8   9  10  17  17  20
	
	int [][] dp2=new int[20][20];
	
	int rodCuttingRecursion(int[] length,int[] prices,int N) {
		if(N==0||length.length==0)
			return 0;
		int l=length.length;
		return rodCuttingRecursionUtil(length, prices, N, l);
	}
	
	int rodCuttingRecursionUtil(int[] length,int[] prices,int N,int l) {
		if(N==0||length.length==0)
			return 0;
		if(length[l-1]<=N)
			dp2[l][N]=Math.max(prices[l-1]+rodCuttingRecursionUtil(length, prices, N-length[l-1], l), rodCuttingRecursionUtil(length, prices, N, l-1));
		else
			dp2[l][N]=rodCuttingRecursionUtil(length, prices, N, l-1);
		
		return dp2[l][N];
	}
	
	int rodCuttingTopdown(int[] length,int[] prices,int N) {
		int l=length.length;
		int[][]t=new int[l+1][N+1];
		for(int i=0;i<=l;i++) {
			for(int j=0;j<=N;j++) {
				if(i==0||j==0)
					t[i][j]=0;
			}
		}
		
		for(int i=1;i<=l;i++) {
			for(int j=1;j<=N;j++) {
				if(length[i-1]<=j)
					t[i][j]=Math.max(prices[i-1]+t[i][j-length[i-1]], t[i-1][j]);
				else
					t[i][j]=t[i-1][j];
			}
		}
		return t[l][N];
	}
//	Coin Change Problem Maximum Number of ways
//	Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm}
//	valued coins, how many ways can we make the change? The order of coins doesn’t matter.
//	Example:
//	for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.
	
	int coinChangeMaxNoOfWays(int[] coin,int sum) {
		int n=coin.length;
		int[][]t=new int[n+1][sum+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=sum;j++) {
				if(i==0)
					t[i][j]=0;
				if(j==0)
					t[i][j]=1;
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=sum;j++) {
				if(coin[i-1]<=j)
					t[i][j]=t[i][j-coin[i-1]]+t[i-1][j];
				else
					t[i][j]=t[i-1][j];
			}
		}
		return t[n][sum];
	}
	
//	Coin Change Problem Minimum Numbers of coins
//	Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins, 
	//what is the minimum number of coins to make the change?
//	Example:
//
//	Input: coins[] = {25, 10, 5}, V = 30
//	Output: Minimum 2 coins required
//	We can use one coin of 25 cents and one of 5 cents 
	
	int findMonNoOfCoins(int[] coin,int sum) {
		int n=coin.length;
		int[][]t=new int[n+1][sum+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=sum;j++) {
				if(i==0)
					t[i][j]=(int) Integer.MAX_VALUE-1;
				if(j==0)
					t[i][j]=0;
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=sum;j++) {
				if(coin[i-1]<=j)
					t[i][j]=Math.min(1+t[i][j-coin[i-1]], t[i-1][j]);
				else
					t[i][j]=t[i-1][j];
			}
		}
		return t[n][sum]==Integer.MAX_VALUE -1 ? -1:t[n][sum];
		
	}
	
}


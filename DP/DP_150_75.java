package DP;

import java.util.Arrays;

public class DP_150_75 {
//	Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//
//	An interleaving of two strings s and t is a configuration where s and t are divided into n and m 
//	substrings
//	 respectively, such that:
//
//	s = s1 + s2 + ... + sn
//	t = t1 + t2 + ... + tm
//	|n - m| <= 1
//	The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
//	Note: a + b is the concatenation of strings a and b.
//
//	 
//
//	Example 1:
//
//
//	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//	Output: true
//	Explanation: One way to obtain s3 is:
//	Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
//	Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
//	Since s3 can be obtained by interleaving s1 and s2, we return true.
//	Example 2:
//
//	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//	Output: false
//	Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
//	Example 3:
//
//	Input: s1 = "", s2 = "", s3 = ""
//	Output: true
//	This problem is quite challenging.
//	Here is a tip to overcome this challenge
//	This problem involves finding whether a target string s3 can be formed by interleaving two given strings s1 and s2.
//	An important observation is that the relative order of characters within each string needs to be maintained in the interleaved result.
//
//	Here's a high-level approach to consider:
//
//	Dynamic Programming: Consider using a dynamic programming approach. Create a 2D DP table where dp[i][j] represents
//	whether the first i characters of s1 and the first j characters of s2 can form the first i+j characters of s3.
//
//	Base Cases: Initialize the DP table with base cases. For example, dp[0][0] should be true, as both empty strings can form an empty string.
//
//	Recurrence Relation: Build the DP table bottom-up using a recurrence relation. You can consider the following cases:
//
//	If the current character in s3 matches the current character in s1, you can check if the previous characters in s1 and
//	s3 match and update dp[i][j] accordingly.
//	Similarly, if the current character in s3 matches the current character in s2, you can check if the previous characters 
//	in s2 and s3 match and update dp[i][j] accordingly.
//	Final Result: After filling the DP table, the value of dp[s1.length()][s2.length()] will indicate whether s3 can be
//	formed by interleaving s1 and s2.
//
//	Remember to handle the edge cases and index mapping properly while implementing the DP solution.
//
//	Good luck! Dynamic programming can be powerful for such sequence-related problems.
	
	//https://leetcode.com/problems/interleaving-string/?envType=study-plan-v2&envId=top-interview-150
	public boolean isInterleave(String s1, String s2, String s3) {
		if(s1.isEmpty() && s2.isEmpty())
        	return s3.isEmpty();
        int m=s1.length(),n=s2.length();
        if(m+n != s3.length())
            return false;
        boolean[][] dp =new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=1;i<=m;i++) {
        	if(s3.charAt(i-1)==s1.charAt(i-1)) {
        		dp[i][0]=dp[i-1][0];
        	}
        }
        
        for(int j=1;j<=n;j++) {
        	if(s3.charAt(j-1)==s2.charAt(j-1)) {
        		dp[0][j]=dp[0][j-1];
        	}
        }
        
        for(int i=1;i<=m;i++) {
        	for(int j=1;j<=n;j++) {
                    dp[i][j]=(dp[i-1][j]&&(s3.charAt(i+j-1)==s1.charAt(i-1)))||
                                (dp[i][j-1]&&(s3.charAt(i+j-1)==s2.charAt(j-1)));
        	}
        }
        return dp[m][n];
    }
	
//	Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
//
//			You have the following three operations permitted on a word:
//
//			Insert a character
//			Delete a character
//			Replace a character
//			 
//
//			Example 1:
//
//			Input: word1 = "horse", word2 = "ros"
//			Output: 3
//			Explanation: 
//			horse -> rorse (replace 'h' with 'r')
//			rorse -> rose (remove 'r')
//			rose -> ros (remove 'e')
//			Example 2:
//
//			Input: word1 = "intention", word2 = "execution"
//			Output: 5
//			Explanation: 
//			intention -> inention (remove 't')
//			inention -> enention (replace 'i' with 'e')
//			enention -> exention (replace 'n' with 'x')
//			exention -> exection (replace 'n' with 'c')
//			exection -> execution (insert 'u')
//			 
//
//			Constraints:
//
//			0 <= word1.length, word2.length <= 500
//			word1 and word2 consist of lowercase English letters.
	//https://www.youtube.com/watch?v=XYi2-LPrwm4
	public int minDistance(String word1, String word2) {
        int m=word1.length(),n=word2.length();
        int [][]dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++) {
        	for(int j=0;j<=n;j++) {
        		if(i==0 && j==0)
        			dp[i][j]=0;
        		else {
        			if(i==0)
        				dp[i][j]=j;
        			else if(j==0)
        				dp[i][j]=i;
        		}
        	}
        }
        
        for(int i=1;i<=m;i++) {
        	for(int j=1;j<=n;j++) {
        		if(word1.charAt(i-1)==word2.charAt(j-1))
        			dp[i][j]=dp[i-1][j-1];
        		else
        			dp[i][j]=1+Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1]);
        	}
        }
        return dp[m][n];
        
       
    }
	
//	You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//	Find the maximum profit you can achieve. You may complete at most two transactions.
//
//	Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//	 
//
//	Example 1:
//
//	Input: prices = [3,3,5,0,0,3,1,4]
//	Output: 6
//	Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//	Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
//	Example 2:
//
//	Input: prices = [1,2,3,4,5]
//	Output: 4
//	Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//	Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
//	Example 3:
//
//	Input: prices = [7,6,4,3,1]
//	Output: 0
//	Explanation: In this case, no transaction is done, i.e. max profit = 0.
	//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/solutions/4840610/best-time-to-buy-and-sell-stock-iii/?envType=study-plan-v2&envId=top-interview-150
	public int maxProfit(int[] prices) {
		int n=prices.length;
		int [][][]dp=new int[n][2][3]; //n is the length,2 is showing whether we will buy or not and 3 is for count of trxn 0 or 1 or 2
		for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++)
            Arrays.fill(dp[i][j],-1);
        }
        return maxProfitUtil(prices, dp, 0, 1, 2);
    }
	
	int maxProfitUtil(int[] prices,int [][][]dp,int i,int buy,int count) {
        if(count==0||i>=prices.length)
        	return 0;
        if(dp[i][buy][count]!=-1)
        	return dp[i][buy][count];
        
        int skip = maxProfitUtil(prices, dp, i+1, buy, count),profit=0 ;
        if(buy==1) {
        	profit=-prices[i]+maxProfitUtil(prices, dp, i+1, 0, count);
        	dp[i][buy][count]=Math.max(skip, profit);
        }else {
        	profit=prices[i]+maxProfitUtil(prices, dp, i+1, 1, count-1);
        	dp[i][buy][count]=Math.max(skip, profit);
        }
        return dp[i][buy][count];
    }
	
//	You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
//
//	Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
//
//	Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//	 
//
//	Example 1:
//
//	Input: k = 2, prices = [2,4,1]
//	Output: 2
//	Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
//	Example 2:
//
//	Input: k = 2, prices = [3,2,6,5,0,3]
//	Output: 7
//	Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//	 
//
//	Constraints:
//
//	1 <= k <= 100
//	1 <= prices.length <= 1000
//	0 <= prices[i] <= 1000
	//same as above problem.
	public int maxProfit(int k, int[] prices) {
		int n=prices.length;
		int [][][]dp=new int[n][2][k+1]; //n is the length,2 is showing whether we will buy or not and 3 is for count of trxn 0 or 1 or 2
		for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++)
            Arrays.fill(dp[i][j],-1);
        }
        return maxProfitUtil(prices, dp, 0, 1, k);
    }
	
//	Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
//
//	 
//
//	Example 1:
//
//
//	Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
	//https://leetcode.com/problems/maximal-square/description/?envType=study-plan-v2&envId=top-interview-150
	
	//https://www.youtube.com/watch?v=RElcqtFYTm0
	public int maximalSquare(char[][] matrix) {
		int m=matrix.length,n=matrix[0].length,maxSqrLen=0;
		int [][]dp=new int[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if((i==0||j==0) && matrix[i][j]=='1' ) {
					dp[i][j]=1;
					maxSqrLen=1;
				}
			}
		}
		
		for(int i=1;i<m;i++) {
			for(int j=1;j<n;j++) {
				if(matrix[i][j]=='1') {
					dp[i][j]=1+Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
					maxSqrLen=Math.max(maxSqrLen, dp[i][j]);
				}
			}
		}
		
		return maxSqrLen*maxSqrLen;
	}
}

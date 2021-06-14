package LCS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class problrms {
	
//	Longest Common Subsequence Problem solution using Memoization
//	Given two sequences, find the length of longest subsequence present in both of them.
//	A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. 
//
//	For example, “abc”,  “abg”, “bdf”, “aeg”,  ‘”acefg”, .. etc are subsequences of “abcdefg”.
	int LCSR(String x,String y) {
	 int m=x.length(),n=y.length();
	 int[][]t=new int[m+1][n+1];
	 Arrays.fill(t, -1);
	 return LCSUtil(x, y, m, n, t);
	}
	
	int LCSUtil(String x,String y,int m,int  n,int[][]t) {
		if(m==0||n==0)
			return 0;
		if(t[m][n]==-1) {
			if(x.charAt(m-1)==y.charAt(n-1))
				t[n][m]=1+LCSUtil(x, y, m-1, n-1, t);
			else
				t[n][m]=Math.max(LCSUtil(x, y, m-1, n, t), LCSUtil(x, y, m, n-1, t));
		}
		return t[m][n];
	}
	
	//LCS top down approach
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
	
//	Longest Common Substring(Dynamic Programming)
//	Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
//	Examples:
//
//	Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
//	Output : 5
//	The longest common substring is “Geeks” and is of length 5.
	
	int lcSUbstring(String x,String y) {
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
					t[i][j]=0;
			}
		}
		return t[m][n];
	}
//	
//	Printing Longest Common Subsequence
//	Given two sequences, print the longest subsequence present in both of them.
//	Example:
//	LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3. 
	
	void printLCS(String x,String y) {
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
		
		StringBuilder sb=new StringBuilder();
		int i=m,j=n;
		while(i>0 && j>0) {
			if(x.charAt(i-1)==y.charAt(j-1)) {
				sb.append(x.charAt(i-1));
				i--;
				j--;
			}else {
				if(t[i][j-1]>t[i-1][j])
					j--;
				else
					i--;
			}
		}
		System.out.println(sb.reverse());
	}
	
//	Shortest Common Supersequence
//	Given two strings str1 and str2, find the shortest string that has both str1 and str2 as subsequences.
//	Examples:
//
//	Input:   str1 = "geek",  str2 = "eke"
//	Output: "geeke" length
	
	int shortestComSubseqLen(String a,String b) {
		return (a.length()+b.length()-LCS(a, b));
	}
	
//	Minimum number of deletions and insertions to transform one string into another
//	Given two strings ‘str1’ and ‘str2’ of size m and n respectively. The task is to remove/delete and insert minimum number of characters from/in str1 so as to transform it into str2. It could be possible that the same character needs to be removed/deleted from one point of str1 and inserted to some another point.
//	Example:
//	Input : str1 = "geeksforgeeks", str2 = "geeks"
//	Output : Minimum Deletion = 8
//	         Minimum Insertion = 0 
	
	void minInsertDelete(String a,String b) {
		int l=LCS(a, b);
		System.out.println("Insertion:"+(b.length()-l));
		System.out.println("Deletion:"+(a.length()-l));
	} 
//	Given a sequence, find the length of the longest palindromic subsequence in it.
//	Example :
//	Input:"bbbab"
//	Output:4
	int LPS(String a) {
		StringBuilder sb=new StringBuilder(a);
		return LCS(a, sb.reverse().toString());
	}
	
//	Minimum number of deletions to make a string palindrome
//	Given a string of size ‘n’. The task is to remove or delete minimum number of characters from the string so that the resultant string is palindrome.
//	Examples :
//
//	Input : aebcbda
//	Output : 2
//	Remove characters 'e' and 'd'
//	Resultant string will be 'abcba'
//	which is a palindromic string
	
	int minDelToMakePalindrome(String a) {
		return a.length()-LPS(a);
	}
	
//	Given two strings X and Y, print the shortest string that has both X and Y as subsequences. If multiple shortest supersequence exists,
	//print any one of them.
//
//	Examples:
//
//	Input: X = "AGGTAB",  Y = "GXTXAYB"
//	Output: "AGXGTXAYB" OR "AGGXTXAYB" 
//	OR Any string that represents shortest
//	supersequence of X and Y
//
//	Input: X = "HELLO",  Y = "GEEK"
//	Output: "GEHEKLLO" OR "GHEEKLLO"
//	OR Any string that represents shortest 
//	supersequence of X and Y
	
	void printSCS(String a,String b) {
		int m=a.length(),n=b.length();
		int[][]t=new int[m+1][n+1];
		for(int i=0;i<=m;i++) {
			for(int j=0;j<=n;j++) {
				if(i==0||j==0)
					t[i][j]=0;
			}
		}
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				if(a.charAt(i-1)==b.charAt(j-1))
					t[i][j]=1+t[i-1][j-1];
				else
					t[i][j]=Math.max(t[i-1][j], t[i][j-1]);
			}
		}
		
		StringBuilder sb=new StringBuilder();
		int i=m,j=n;
		while(i>0 && j>0) {
			if(a.charAt(i-1)==b.charAt(j-1)) {
				sb.append(a.charAt(i-1));
				i--;
				j--;
			}else {
				if(t[i][j-1]>t[i-1][j]) {
					sb.append(b.charAt(j-1));
					j--;
				}
				else {
					sb.append(a.charAt(i-1));
					i--;
				}
			}
		}
		while(i>0) {
			sb.append(a.charAt(i-1));
			i--;
		}
		while(j>0) {
			sb.append(b.charAt(j-1));
			j--;
		}
		System.out.println(sb.reverse());
	}

//	Longest Repeating Subsequence
//	Given a string, print the longest repeating subsequence such that the two subsequence don’t have same string character at same position,
	//i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string.
//	Example:
//	Input: str = "aab"
//	Output: "a"
//	The two subsequence are 'a'(first) and 'a' 
//	(second). Note that 'b' cannot be considered 
//	as part of subsequence as it would be at same
//	index in both.
	
	int LRS(String a) {
		int m=a.length();
		int[][]t=new int[m+1][m+1];
		for(int i=0;i<=m;i++) {
			for(int j=0;j<=m;j++) {
				if(i==0||j==0)
					t[i][j]=0;
			}
		}
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=m;j++) {
				if((a.charAt(i-1)==a.charAt(j-1))&& i!=j)
					t[i][j]=1+t[i-1][j-1];
				else
					t[i][j]=Math.max(t[i-1][j], t[i][j-1]);
			}
		}
		return t[m][m];
	}
	
	//sequence pattern matching
	
	boolean patternMatches(String a,String b) {
		if(LCS(a, b)==a.length())
			return true;
		return false;
	}
	
//	Given a string, find the minimum number of characters to be inserted to form Palindrome string out of given string
//
//	Examples:
//	ab: Number of insertions required is 1. bab
//	aa: Number of insertions required is 0. aa
	
	int minInsertToMakePalindrome(String a) {
		return a.length()-LPS(a);
	}
//	You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this
//	place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
//	and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//	Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting 
//			the police.
//
//	 
//
//	Example 1:
//
//	Input: nums = [2,3,2]
//	Output: 3
//	Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
//	Example 2:
//
//	Input: nums = [1,2,3,1]
//	Output: 4
//	Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//	Total amount you can rob = 1 + 3 = 4.
//	Example 3:
//
//	Input: nums = [0]
//	Output: 0
	//https://leetcode.com/problems/house-robber-ii/discuss/1151554/Exactly-same-as-House-Robber
	public int rob(int[] nums) {
		if(nums.length==0)
			return 0;
		if(nums.length==1)
			return nums[0];
		if(nums.length==2)
			Math.max(nums[0], nums[1]);
        int n=nums.length;
        int []dp=new int[nums.length+1];
        Arrays.fill(dp, -1);
        //for the part 0 to n-1
        dp[n-1]=0;
        dp[n-2]=nums[n-2];
        for(int i=n-3;i>=0;i--) {
        	dp[i]=Math.max(nums[i]+dp[i+2], dp[i+1]);
        }
        int c1=dp[0];
        
        Arrays.fill(dp, -1);
        //for the part 1 to n
        dp[n]=0;
        dp[n-1]=nums[n-1];
        for(int i=n-2;i>=1;i--)
        	dp[i]=Math.max(nums[i]+dp[i+2], dp[i+1]);
        int c2=dp[1];
        return Math.max(c1, c2);
    }
//	The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
//
//	Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a 
//	binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
//	Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
//
//	 
//
//	Example 1:
//
//
//	Input: root = [3,2,3,null,3,null,1]
//	Output: 7
//	Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
//	Example 2:
//
//
//	Input: root = [3,4,5,1,3,null,1]
//	Output: 9
//	Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
//	 
//
//	Constraints:
//
//	The number of nodes in the tree is in the range [1, 104].
//	0 <= Node.val <= 104
//			https://leetcode.com/problems/house-robber-iii/
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
	public int rob(TreeNode root) {
		int []res=robUtil(root);
		return Math.max(res[0], res[1]);
	}
	
	public int[]robUtil(TreeNode node){
		if(node==null)
			return new int[2];
		int []left=robUtil(node.left);
		int []right=robUtil(node.right);
		
		int ans[]=new int[2];
		ans[0]=left[1]+node.val+right[1];
		ans[1]=Math.max(left[0], left[1])+Math.max(right[0], right[1]);
		return ans;
	}
		 
//		   public int rob(TreeNode root) {
//		       int c1=0,c2=0,parent=0;
//		       Queue<TreeNode> q=new LinkedList<problrms.TreeNode>();
//		       q.add(root);
//		       q.add(null);
//		       while(!q.isEmpty()) {
//		    	   TreeNode temp=q.poll();
//		    	   if(temp!=null) {
//		    		   if(parent==0)
//		    			   c1+=temp.val;
//		    		   else
//		    			   c2+=temp.val;
//		    		   if(temp.left!=null)
//		    			   q.add(temp.left);
//		    		   if(temp.right!=null)
//		    			   q.add(temp.right);
//		    	   }else {
//		    		   if(!q.isEmpty()) {
//		    			   parent=parent-1;
//		    			   q.add(null);
//		    		   }
//		    	   }
//		       }
//		       return Math.max(c1,c2);
//		   }
	
	//Count the number of prime numbers less than a non-negative number, n.
	
	public int countPrimes(int n) {
        if(n==0)
        	return 0;
        boolean prime[]=new boolean[n+1];
        Arrays.fill(prime, true);
        int count=0;
        for(int i=2;i<=n;i++) {
        	if(prime[i]) {
        		count++;
        		for(int j=i*i;j<=n;j+=i)
        			prime[j]=false;
        	}
        }
        for(int i=2;i<=n;i++) {
        	if(prime[i])
        		System.out.println(i);
        }
        return count;
    }
	
	//Find the highest occurring digit in prime numbers in a range
	//https://www.geeksforgeeks.org/find-highest-occurring-digit-prime-numbers-range/
	public int maxDigitInPrimes(int start, int end) {
		boolean[] prime=new boolean[end+1];
		Arrays.fill(prime, true);
		for(int i=2;i<=end;i++) {
        	if(prime[i]) {
        		for(int j=i*i;j<=end;j+=i)
        			prime[j]=false;
        	}
        }
		
		int[] freq=new int[10];
		for(int i=start;i<=end;i++) {
			if(prime[i]) {
				while(i>0) {
					freq[i%10]++;
					i=i/10;
				}
			}
		}
		
		int max=freq[0],ans=0;
		for(int j=1;j<10;j++) {
			if(freq[j]>max) {
				max=freq[j];
				ans=j;
			}
		}
		return ans;
	}
	
	//find lcm of two numbers
	int lcm(int a,int b) {
		int gcd=1,lcm;
		for(int i=1;i<=(a<b?a:b);i++) {
			if(a%i==0 && b%i==0)
				gcd=i;
		}
		lcm=(a*b)/gcd;
		return lcm;
	}
}

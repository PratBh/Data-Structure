package LCS;

import java.util.Arrays;

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

}


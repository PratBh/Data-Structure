package MCM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problems {
	//matrix chain multiplication recursion for finding min cost of multiplication
//	Input: p[] = {40, 20, 30, 10, 30}   
//	Output: 26000  
//	There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
//	Let the input 4 matrices be A, B, C and D.  The minimum number of 
//	multiplications are obtained by putting parenthesis in following way
//	(A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
	//dimention of Ai=arr[i-1]*arr[i]..that is why i started from 1
	
	int MCM(int[]arr) {
		int [][]t=new int[20][20];
		Arrays.fill(t, -1);
		return MCMUtil(arr, 1, arr.length-1, t);
	}
	
	int MCMUtil(int [] arr,int i,int j,int [][]t) {
		if(i>=j)
			return 0;
		if(t[i][j]!=-1)
			return t[i][j];
		int minCost=Integer.MAX_VALUE;
		for(int k=i;k<j-1;k++) {
			int temp=MCMUtil(arr, i, k, t)+MCMUtil(arr, k+1, j, t)+(arr[i-1]*arr[k]*arr[j]);
			minCost=Math.min(temp, minCost);
		}
		t[i][j]=minCost;
		return minCost;
	}
//	
//	Palindrome Partitioning Memoized Optimization
//	Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
//	Example:
//	  “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
	
	int palPartion(String s) {
		int [][]t=new int[20][20];
		Arrays.fill(t, -1);
		return palPartionUtil(s, 0, s.length()-1, t);
	}
	
	int palPartionUtil(String s,int i,int j,int [][]t) {
		if(i>j)
			return 0;
		if(isPalindrome(s, i, j))
			return 0;
		if(t[i][j]!=-1)
			return t[i][j];
		else {
			int ans=Integer.MAX_VALUE;
			int left,right;
			for(int k=i;i<j;k++) {
				int temp;
				if(t[i][k]!=-1) {
					 left=t[i][k];
					 temp=1+left+palPartionUtil(s, k+1, j, t);
				}
				if(t[k+1][j]!=-1) {
					right=t[k+1][j];
					temp=1+palPartionUtil(s, i, k, t)+right;
				}
				temp=1+palPartionUtil(s, i, k, t)+palPartionUtil(s, k+1, j, t);
				ans=Math.min(ans, temp);
			}
			t[i][j]=ans;
			return ans;
		}
	}
	
	boolean isPalindrome(String s,int i,int j) {
		if(i==j||i>j)
			return true;
		while(i<j) {
			if(s.charAt(i)!=s.charAt(j))
				return false;
			i++;j--;
		}
		return true;
	}
	
//	Evaluate Expression To True-Boolean Parenthesization Memoized
//	Given a boolean expression with following symbols.
//	Symbols
//	    'T' --- true 
//	    'F' --- false 
//	And following operators filled between symbols
//	Operators
//	    &   --- boolean AND
//	    |   --- boolean OR
//	    ^   --- boolean XOR 
//	Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
//	Example:
//	Input: symbol[]    = {T, F, T}
//	       operator[]  = {^, &}
//	Output: 2
//	The given expression is "T ^ F & T", it evaluates true
//	in two ways "((T ^ F) & T)" and "(T ^ (F & T))"
	 
	int evaluateExp(String s) {
		Map<String, Integer> dpMap=new HashMap();
		return evaluateExpUtil(0, s.length()-1, true, dpMap, s);
	}
	
	int evaluateExpUtil(int i,int j,boolean isTrue,Map<String, Integer> dpMap,String s) {
		if(i>j)
			return 0;
		if(i==j) {
			if(isTrue)
			{
				if(s.charAt(i)=='T')
					return 1;
				else 
					return 0;
			}
			else {
				if(s.charAt(i)=='F')
					return 1;
				else 
					return 0;
			}
		}
		StringBuilder sb=new StringBuilder();
		sb.append(String.valueOf(i)+" "+String.valueOf(j)+" "+String.valueOf(isTrue));
		if(dpMap.containsKey(sb.toString()))
			return dpMap.get(sb.toString());
		else {
			int ans=0 ;
			for(int k=i+1;k<=j-1;k++) {
				int leftTrue=evaluateExpUtil(i, k-1, true, dpMap, s);
				int leftFalse=evaluateExpUtil(i, k-1, false, dpMap, s);
				int rightTrue=evaluateExpUtil(k+1,j, true, dpMap, s);
				int rightFalse=evaluateExpUtil(k+1,j, false, dpMap, s);
				if(s.charAt(k)=='&') {
					if(isTrue)
						ans=ans+leftTrue*rightTrue;
					else
						ans=ans+leftFalse*rightFalse+leftTrue*rightFalse+leftFalse*rightTrue;
				}
				else if(s.charAt(k)=='|') {
					if(isTrue)
						ans=ans+leftTrue*rightTrue+leftTrue*rightFalse+leftFalse*rightTrue;
					else
						ans=ans+leftFalse*rightFalse;
				}
				else if(s.charAt(k)=='^') {
					if(isTrue)
						ans=ans+leftTrue*rightFalse+leftFalse*rightTrue;
					else
						ans=ans+leftFalse*rightFalse+leftTrue*rightTrue;
				}
			}
			dpMap.put(sb.toString(),ans);
			return ans;
		}
			
	}
	
//	Scramble String using Memoization(Dynamic Programming)
//	Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
//	Below is one possible representation of A = “great”:
//	 great
//	   /    \
//	  gr    eat
//	 / \    /  \
//	g   r  e   at
//	           / \
//	          a   t
//	To scramble the string, we may choose any non-leaf node and swap its two children.
//
//	For example, if we choose the node “gr” and swap its two children, it produces a scrambled string “rgeat”.
//
//	    rgeat
//	   /    \
//	  rg    eat
//	 / \    /  \
//	r   g  e   at
//	           / \
//	          a   t
//	We say that “rgeat” is a scrambled string of “great”.
//
//	Similarly, if we continue to swap the children of nodes “eat” and “at”, it produces a scrambled string “rgtae”.
//
//	    rgtae
//	   /    \
//	  rg    tae
//	 / \    /  \
//	r   g  ta  e
//	       / \
//	      t   a
//	We say that “rgtae” is a scrambled string of “great”.
	
	boolean isScrambledString(String a,String b) {
		if(a.length()!=b.length())
			return false;
		if(a==null&&b==null)
			return true;
		Map<String, Boolean> dp=new HashMap<String, Boolean>();
		return isScrambledStringUtil(a, b, dp);
	}
	
	boolean isScrambledStringUtil(String a,String b,Map<String, Boolean> dp) {
		if(a.equals(b))
			return true;
		if(a.length()<=1||b.length()<=1)
			return false;
		StringBuilder sb=new StringBuilder(a+"_"+b);
		if(dp.containsKey(sb.toString()))
			return dp.get(sb.toString());
		int n=a.length();
		boolean flag=false;
		for(int i=1;i<n;i++) {
			if((isScrambledStringUtil(a.substring(0, i), b.substring(0,i),dp)&&isScrambledStringUtil(a.substring(i, n-i+1), b.substring(i, n-i+1), dp))||
					isScrambledStringUtil(a.substring(0,i),b.substring(n-i,n), dp)&&isScrambledStringUtil(a.substring(i, n), b.substring(0, i+1), dp)) {
				flag=true;
				break;
			}
		}
		dp.put(sb.toString(), flag);
		return flag;
	}
	

//Egg Dropping using Memoization Optimization
//
//Problem statement: You are given N floor and K eggs. You have to minimize the number of times you have to drop the eggs to find the critical
	//floor where critical floor means the floor beyond which eggs start to break. Assumptions of the problem:
//
//If egg breaks at ith floor then it also breaks at all greater floors.
//If egg does not break at ith floor then it does not break at all lower floors.
//Unbroken egg can be used again.
//Note: You have to find minimum trials required to find the critical floor not the critical floor.
//
//Example:
//Input:
//    4
//    2
//    
//    Output:
//    Number of trials when number of eggs is 2 and number of floors is 4: 3
	
	int eggDrop(int floors,int eggs) {
		int[][]t=new int[eggs][floors];
		for(int i=0;i<t.length;i++)
			Arrays.fill(t[i], -1);
		return eggDropUtil(floors, eggs, t);
	}
	
	int eggDropUtil(int floors,int eggs,int[][]t) {
		if(eggs==0||floors==0||floors==1)
			return floors;
		if(t[eggs][floors]!=-1)
			return t[eggs][floors];
		int min=Integer.MAX_VALUE;
		for(int k=1;k<=floors;k++) {
			int op1,op2;
			if(t[eggs-1][k-1]!=-1)// if egg breaks
				op1=t[eggs-1][k-1];
			else {
				op1=eggDropUtil(k-1, eggs-1, t);
				t[eggs-1][k-1]=op1;
			}
			if(t[eggs][floors-k]!=-1)//if egg did not break
				op2=t[eggs][floors-k];
			else {
				op2=eggDropUtil(floors-k, eggs, t);
				t[eggs][floors-k]=op2;
			}
			int tempAns=1+Math.max(op1, op2);
			min=Math.min(tempAns, min);
		}
		t[eggs][floors]=min;
		return min;
	}
}


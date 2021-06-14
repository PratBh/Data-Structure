package adityaVerma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Problems {
	 
	//print 1 to n numbers using recursion
	
	void print(int n) {
		if(n==1) {
			System.out.println(n);
			return;
		}
		
		print(n-1);
		System.out.println(n);
	}
	
	//print n to 1 numbers using recursion
	void printRev(int n) {
		if(n==1) {
			System.out.print(n);
			return;
		}
		System.out.print(n);
		printRev(n-1);
	}
	
	//factorial of a number
	
	int fact(int n) {
		if(n==1)
			return 1;
		System.out.println(n*fact(n-1));
		return n*fact(n-1);
	}
	
	//find height of binary tree
	
	class TreeNode{
		TreeNode left;
		TreeNode right;
	}
	
	int binaryHeight(TreeNode root) {
		if(root==null)
			return 0;
		int left_h=binaryHeight(root.left);
		int right_h=binaryHeight(root.right);
		
		return 1+Math.max(left_h, right_h);
	}
	
	//sort a list using recursion
	
	List<Integer> sortList(List<Integer> arrLisrt) {
		//base condition
		if(arrLisrt.size()==1)
			return arrLisrt;
		//hypothesis
		int l=arrLisrt.get(arrLisrt.size()-1);
		arrLisrt.remove(arrLisrt.size()-1);
		sortList(arrLisrt);
		//induction
		insert(arrLisrt,l);
		return arrLisrt;
	}
	
	//function to insert an element at correct position in an sorted list
	void insert(List<Integer> arrLisrt,int k) {
		//base condition
		if(arrLisrt.size()==0||arrLisrt.get(arrLisrt.size()-1)<=k) {
			arrLisrt.add(k);
			return;
		}
		//hypothesis
		int l=arrLisrt.get(arrLisrt.size()-1);
		arrLisrt.remove(arrLisrt.size()-1);
		insert(arrLisrt, k);
		//induction
		arrLisrt.add(l);
	}
	
	//delete middle element of a stack by recursion
	
	void deleteMiddle (Stack<Integer> st) {
		if(st.size()==0||st==null)
			return ;
		int k=st.size()/2 +1;
		deleteMiddleUtil(st,k);
	}
	
	void deleteMiddleUtil(Stack<Integer> st,int k) {
		if(k==1) {
			st.pop();
			return;
		}
		int l=st.pop();
		deleteMiddleUtil(st, k-1);
		st.push(l);
	}
	
	//reverse a stack using recursion or without using extra space
	
	Stack<Integer> reverseSt(Stack<Integer> st){
		//base condition
		if(st.size()==1)
			return st;
		//hypothesis
		int l=st.pop();
		reverseSt(st);
		//induction
		insertSt(st,l);
		return st;
	}
	
	//insert an element at the end of an stack i.e. at the buttom
	
	void insertSt(Stack<Integer> st,int k) {
		//base condition
		if(st.size()==0) {
			st.push(k);
			return;
		}
		//hypothesis
		int m=st.pop();
		insertSt(st, k);
		//induction
		st.push(m);
	}
	
	//On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01,
	//and each occurrence of 1 with 10.
//
//Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
//
//Examples:
//Input: N = 1, K = 1
//Output: 0
//
//Input: N = 2, K = 1
//Output: 0
//
//Input: N = 2, K = 2
//Output: 1
//
//Input: N = 4, K = 5
//Output: 1
//
//Explanation:
//row 1: 0
//row 2: 01
//row 3: 0110
//row 4: 01101001
	//https://leetcode.com/problems/k-th-symbol-in-grammar/
	
	public int kthGrammar(int N, int K) {
       //Base condition
		if(N==1 && K==1)
			return 0;
		//hypothesis and induction
		int mid=(int) (Math.pow(2, N-1)/2);
		if(K<=mid)
			return kthGrammar(N-1, K);
		else{
			if(kthGrammar(N-1, K-mid)==1)
				return 0;
			return 1;
		}
    }
	
	
	//tower of hanoi
	void towerOfHanoi(int n,int source,int dest,int help) {
		//Base condition
		if(n==1) {
			System.out.println("moving plate "+n+" from "+source+" to "+dest);
			return;
		}
		//hypothesis
		towerOfHanoi(n-1, source, help, dest);//moving all plate except last one from s to h with help of d
		//induction
		System.out.println("moving plate "+n+" from "+source+" to "+dest);
		towerOfHanoi(n-1, help, dest, source);//moving all plate except last one from h to d with help of s;
	}
	
	//print all subsets from a string
	
	void printSubset(String ip,String op) {
		if(ip==null||ip.length()==0) {
			System.out.println(op);
			return;
		}
		String op1=op;
		String op2=op+ip.charAt(0);
		
		String ip1=ip.substring(1);
		printSubset(ip1, op1);
		printSubset(ip1, op2);
	}
//	Given a string you need to print all possible strings that can be made by placing spaces (zero or one) in between them. 
	//Output should be printed in sorted increasing order of strings.
//
//	Input:  str[] = "ABC"
//	Output: (A B C)(A BC)(AB C)(ABC)
//	Input:
//	First line contains the test cases T.  1<=T<=10
//	Each test case have one line string S of characters of length  N.  1<=N<=10
//
//	Output:
//	One line per test case, every string enclosed in ().(See example). Output should be printed in sorted order.
//
//	Example:
//	Input:
//	2
//	AB
//	ABC
//
//	Output:
//	(A B)(AB)
//	(A B C)(A BC)(AB C)(ABC)
	//https://practice.geeksforgeeks.org/problems/permutation-with-spaces/0
	
	void solvePerm(String ip) {
		StringBuilder op=new StringBuilder();
		op.append(ip.charAt(0));
		String input=ip.substring(1);
		solvePermUtil(input,op.toString());
	}
	
	void solvePermUtil (String ip,String op) {
		if(ip==null||ip.length()==0) {
			System.out.println(op);
			return;
		}
		String op1=op+ip.charAt(0);
		String op2=op+" "+ip.charAt(0);
		String ip1=ip.substring(1);
		solvePermUtil(ip1, op2);
		solvePermUtil(ip1, op1);
	}
	
//	Print all permutations of a string keeping the sequence but changing cases.
//
//	Examples:
//
//	Input : ab
//	Output : AB Ab ab aB
//
//	Input : ABC
//	Output : abc Abc aBc ABc abC AbC aBC ABC
	
	void permuteCase(String ip,String op) {
		if(ip==null||ip.length()==0) {
			System.out.println(op);
			return;
		}
		String op1=op+ip.charAt(0);
		String op2=op+Character.toUpperCase(ip.charAt(0));
		String ip1=ip.substring(1);
		permuteCase(ip1, op2);
		permuteCase(ip1, op1);
	}
	
//	Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
//
//	Return a list of all possible strings we could create. You can return the output in any order.
//
//	 
//
//	Example 1:
//
//	Input: S = "a1b2"
//	Output: ["a1b2","a1B2","A1b2","A1B2"]
//	Example 2:
//
//	Input: S = "3z4"
//	Output: ["3z4","3Z4"]
//	https://leetcode.com/problems/letter-case-permutation/
		public List<String> letterCasePermutation(String S) {
	       List<String> result=new ArrayList<String>();
	       String op="";
	       letterCasePermutationUtil(S, op, result);
	       return result;
	    }
		
		void letterCasePermutationUtil(String ip,String op,List<String> result) {
			if(ip==null||ip.length()==0) {
				result.add(op);
				return;
			}
			
			char c=ip.charAt(0);
			
			if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				String op1=op+Character.toUpperCase(c);
				String op2=op+Character.toLowerCase(c);
				String ip1=ip.substring(1);
				letterCasePermutationUtil(ip1, op1, result);
				letterCasePermutationUtil(ip1, op2, result);
			}else {
				String op1=op+ip.charAt(0);
				String ip1=ip.substring(1);
				letterCasePermutationUtil(ip1, op1, result);
			}
		}
		
//		Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses of length 2*n.
//
//		For example, given n = 3, a solution set is:
//
//		"((()))", "(()())", "(())()", "()(())", "()()()"
		
		public List<String> generatePar(int n){
			int open=n,close=n;
			List<String> res=new ArrayList<String>();
			String op="(";
			generateParUtil(open-1, close, res, op);
			return res;
		}
		
		void generateParUtil(int open,int close,List<String> res,String op) {
			if(open==0 && close==0) {
				res.add(op);
				return;
			}
			if(open !=0) {
				String op1=op+'(';
				generateParUtil(open-1, close, res, op1);
			}
			if(close>open) {
				String op2=op+')';
				generateParUtil(open, close-1, res, op2);
			}
		}
		
//		Given a positive integer N, the task is to find all the N bit binary numbers having more than or equal 1’s than 0’s for any prefix of the number.
//
//		Example 1:
//
//		Input:  N = 2
//		Output: 11 10
//		Explanation: 11 and 10 have more than 
//		or equal 1's than 0's
//		Example 2:
//
//		Input:  N = 3
//		Output: 111 110 101
//		Explanation: 111, 110 and 101 have more 
//		than or equal 1's than 0's
		
		List<String> generateBinary(int n){
			int ones=0,zeroes=0;
			String op="";
			List<String> res=new ArrayList<String>();
			generateBinaryUtil(n, ones, zeroes, res, op);
			return res;
		}
		
		void generateBinaryUtil(int n,int ones,int zeroes,List<String> res,String op) {
			if(n==0) {
				res.add(op);
				return;
			}
			String op1=op+"1";
			generateBinaryUtil(n-1, ones+1, zeroes, res, op1);
			if(ones>zeroes) {
				String op2=op+"0";
				generateBinaryUtil(n-1, ones, zeroes+1, res, op2);
			}
		}
		
//		There are n people standing in a circle (numbered clockwise 1 to n) waiting to be executed. The counting begins at point 1 in the circle and 
//		proceeds around the circle in a fixed direction (clockwise). In each step, a certain number of people are skipped and the next person is 
//		executed. The elimination proceeds around the circle (which is becoming smaller and smaller as the executed people are removed), 
//		until only the last person remains, who is given freedom.
//		Given the total number of persons n and a number k which indicates that k-1 persons are skipped and kth person is killed in circle.
//		The task is to choose the place in the initial circle so that you are the last one remaining and so survive.
//		Consider if n = 5 and k = 2, then the safe position is 3.
//		Firstly, the person at position 2 is killed, then person at position 4 is killed, then person at position 1 is killed. Finally, 
//		the person at position 5 is killed. So the person at position 3 survives.
//
//		Input:
//		The first line of input contains a single integer T denoting the number of test cases. Then T test cases follow.
//		The first and only line of each test case consists of two space separated positive integers denoting n and k.
//
//		Output:
//		Corresponding to each test case, in a new line, print the safe position.
//
//		Constraints:
//		1 ≤ T ≤ 200
//		1 ≤ n, k ≤ 200
//
//		Example:
//		Input:
//		3
//		2 1
//		4 2
//		50 10
//		Output:
//		2
//		1
//		36
//		Josephus Problem
		
		int solveJosephus(int n,int k) {
			List<Integer> persons=new ArrayList<Integer>();
			k=k-1;
			for(int i=0;i<n;i++)
				persons.add(i+1);
			solveJosephusUtil(persons, k, 0);
			return persons.get(0);
		}
		
		void solveJosephusUtil(List<Integer> persons,int k,int index) {
			if(persons.size()==1)
				return;
			int pos=(index+k)%persons.size();
			persons.remove(pos);
			solveJosephusUtil(persons, k, pos);
		}
}


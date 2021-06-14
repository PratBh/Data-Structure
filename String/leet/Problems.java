package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class Problems {
//	Given a valid (IPv4) IP address, return a defanged version of that IP address.
//
//			A defanged IP address replaces every period "." with "[.]".
//
//			 
//
//			Example 1:
//
//			Input: address = "1.1.1.1"
//			Output: "1[.]1[.]1[.]1"
//			Example 2:
//
//			Input: address = "255.100.50.0"
//			Output: "255[.]100[.]50[.]0"
	
	public String defangIPaddr(String address) {
        StringBuilder sb=new StringBuilder();
        char[] ad=address.toCharArray();
        for(int i=0;i<address.length();i++) {
        	if(ad[i]=='.') {
        		sb.append('[');
        		sb.append('.');
        		sb.append(']');
        	}else
        		sb.append(ad[i]);
        }
        return sb.toString();
    }
	
//	You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order. 
//	The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al". The interpreted strings 
	//are then concatenated 
//	in the original order.
//
//	Given the string command, return the Goal Parser's interpretation of command.
//
//	 
//
//	Example 1:
//
//	Input: command = "G()(al)"
//	Output: "Goal"
//	Explanation: The Goal Parser interprets the command as follows:
//	G -> G
//	() -> o
//	(al) -> al
//	The final concatenated result is "Goal".
//	Example 2:
//
//	Input: command = "G()()()()(al)"
//	Output: "Gooooal"
//	Example 3:
//
//	Input: command = "(al)G(al)()()G"
//	Output: "alGalooG"
//	 
//
//	Constraints:
//
//	1 <= command.length <= 100
//	command consists of "G", "()", and/or "(al)" in some order.
	
	public String interpret(String command) {
        StringBuilder sb=new StringBuilder();
        char[]com=command.toCharArray();
        Stack<Character> st=new Stack<Character>();
        for(int i=0;i<command.length();i++) {
        	if(com[i]=='G')
        		sb.append(com[i]);
        	else if(com[i]==')') {
        		char c=st.pop();
        		if(c=='(') {
        			sb.append('o');
        		}else if(c=='l') {
        			st.pop();
        			st.pop();
        			sb.append('a');
        			sb.append('l');
        		}
        	}
        	else
        		st.add(com[i]);
        }
        return sb.toString();
    }
	
//	Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
//
//	Given a balanced string s, split it in the maximum amount of balanced strings.
//
//	Return the maximum amount of split balanced strings.
//
//	 
//
//	Example 1:
//
//	Input: s = "RLRRLLRLRL"
//	Output: 4
//	Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
//	Example 2:
//
//	Input: s = "RLLLLRRRLR"
//	Output: 3
//	Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
//	Example 3:
//
//	Input: s = "LLLLRRRR"
//	Output: 1
//	Explanation: s can be split into "LLLLRRRR".
//	Example 4:
//
//	Input: s = "RLRRRLLRLL"
//	Output: 2
//	Explanation: s can be split into "RL", "RRRLLRLL", since each substring contains an equal number of 'L' and 'R'
//	 
//
//	Constraints:
//
//	1 <= s.length <= 1000
//	s[i] is either 'L' or 'R'.
//	s is a balanced string.
	
	public int balancedStringSplit(String s) {
        int count=0,i=0,rs=0,ls=0;
        for(i=0;i<s.length();i++) {
        	if(s.charAt(i)=='R')
        		rs++;
        	else
        		ls++;
        	if(rs==ls) {
        		count++;
        		ls=rs=0;
        	}
        }
        return count;
    }

//	A string is a valid parentheses string (denoted VPS) if it meets one of the following:
//
//		It is an empty string "", or a single character not equal to "(" or ")",
//		It can be written as AB (A concatenated with B), where A and B are VPS's, or
//		It can be written as (A), where A is a VPS.
//		We can similarly define the nesting depth depth(S) of any VPS S as follows:
//
//		depth("") = 0
//		depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
//		depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
//		depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
//		For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
//
//		Given a VPS represented as string s, return the nesting depth of s.
//
//		 
//
//		Example 1:
//
//		Input: s = "(1+(2*3)+((8)/4))+1"
//		Output: 3
//		Explanation: Digit 8 is inside of 3 nested parentheses in the string.
//		Example 2:
//
//		Input: s = "(1)+((2))+(((3)))"
//		Output: 3
//		Example 3:
//
//		Input: s = "1+(2*3)/(2-1)"
//		Output: 1
//		Example 4:
//
//		Input: s = "1"
//		Output: 0
//		 
//
//		Constraints:
//
//		1 <= s.length <= 100
//		s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
//		It is guaranteed that parentheses expression s is a VPS.
	
	public int maxDepth(String s) {
        int maxDeep=Integer.MIN_VALUE,deep=0;
        for(int i=0;i<s.length();i++) {
        	if(s.charAt(i)=='(')
        		deep++;
        	if(maxDeep<deep)
        		maxDeep=deep;
        	if(s.charAt(i)==')')
        		deep--;
        }
        return maxDeep;
    }
	
//	Given a string s. You should re-order the string using the following algorithm:
//
//		Pick the smallest character from s and append it to the result.
//		Pick the smallest character from s which is greater than the last appended character to the result and append it.
//		Repeat step 2 until you cannot pick more characters.
//		Pick the largest character from s and append it to the result.
//		Pick the largest character from s which is smaller than the last appended character to the result and append it.
//		Repeat step 5 until you cannot pick more characters.
//		Repeat the steps from 1 to 6 until you pick all characters from s.
//		In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.
//
//		Return the result string after sorting s with this algorithm.
//
//		 
//
//		Example 1:
//
//		Input: s = "aaaabbbbcccc"
//		Output: "abccbaabccba"
//		Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
//		After steps 4, 5 and 6 of the first iteration, result = "abccba"
//		First iteration is done. Now s = "aabbcc" and we go back to step 1
//		After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
//		After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
//		Example 2:
//
//		Input: s = "rat"
//		Output: "art"
//		Explanation: The word "rat" becomes "art" after re-ordering it with the mentioned algorithm.
//		Example 3:
//
//		Input: s = "leetcode"
//		Output: "cdelotee"
//		Example 4:
//
//		Input: s = "ggggggg"
//		Output: "ggggggg"
//		Example 5:
//
//		Input: s = "spo"
//		Output: "ops"
//		 
//
//		Constraints:
//
//		1 <= s.length <= 500
//		s contains only lower-case English letters.
	
	public String sortString(String s) {
        int[]map=new int[26];
        for(int i=0;i<s.length();i++) {
        	//char c=s.charAt(i);
        	map[s.charAt(i)-'a']++;
        }
        StringBuilder sb=new StringBuilder();
        int n=s.length(),len=0;
        while(len!=n) {
        	boolean used=false;
        	for(int i=0;i<26;i++) {
        		if(map[i]!=0) {
        			map[i]--;
        			sb.append(new Character((char) ((char)i+'a')));
        			used=true;
        		}
        	}
        	
        	for(int i=0;i<26;i++) {
         		if(map[25-i]!=0) {
        			map[25-i]--;
        			sb.append(new Character((char) ((char)25-i+'a')));
        			used=true;
        		}
        	}
        	
        	if(!used)
        		break;
        }
        return sb.toString();
    }
	
//	Given an array A consisting of N integers, returns the maximum sum of two numbers whose digits add up to an equal sum. if there are not two numbers 
//	whose digits have an equal sum, the function should return -1.
//
//			N is integer within the range [1, 200000]
//			each element of array A is an integer within the range [1, 1000000000]
//			Example
//			Example1:
//			Input:
//			A = [51, 71, 17, 42]
//			Output: 93
//			Explanation: There are two pairs of numbers whose digits add up to an equal sum: (51, 42) and (17, 71), The first pair sums up  to 93
//			Example2:
//			Input:
//			A = [42, 33, 60]
//			Output: 102
//			Explanation: The digits of all numbers in A add up the same sum, and choosng to add 42 and 60 gives the result 102
//			Example3:
//			Input:
//			A = [51, 32, 43]
//			Output: -1
//			Explanation: All numbers in A have digits that add up to different, unique sums
//	
	public int MaximumSum(int[] A) {
        int max=-1;
        Map<Integer,Integer> lookup=new HashMap<Integer,Integer>();
        for(int n:A) {
        	int sum=sumOfDigits(n);
        	if(lookup.containsKey(sum)) {
        		int cmax=n+lookup.get(sum);
        		if(max<cmax)
        			max=cmax;
        	}
        	else
        		lookup.put(sum, n);
        }
        return max;
    }
	
	int sumOfDigits(int n) {
		int sum=0;
		while(n>0) {
			sum+=n%10;
			n=n/10;
		}
		return sum;
	}
	
	//return a palindrome of N length and having k distinct characters
	
	public String generatePalindrome(int a,int b) {
		if(b<((a+1)/2)||b>26)
			return null;
		char[]chars="abcdefghijklmnopqrstuvwxyz".toCharArray();
		Random r=new Random();
		StringBuilder part1=new StringBuilder();
		int halflength=(a%2==0)?a/2:(a+1)/2;
		while(halflength-->0) {
			int ci=r.nextInt(b);
			part1.append(chars[ci]);
			chars[ci]=chars[--b];
		}
		return part1.append(new StringBuilder(part1).reverse().substring(a%2)).toString();
	}
	
}

package stack.leetcode;

import java.util.ArrayDeque;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

public class Stackpractice {

//	Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//	An input string is valid if:
//
//	Open brackets must be closed by the same type of brackets.
//	Open brackets must be closed in the correct order.
//	Every close bracket has a corresponding open bracket of the same type.
//	 
//
//	Example 1:
//
//	Input: s = "()"
//	Output: true
//	Example 2:
//
//	Input: s = "()[]{}"
//	Output: true
//	Example 3:
//
//	Input: s = "(]"
//	Output: false
//	 
//
//	Constraints:
//
//	1 <= s.length <= 104
//	s consists of parentheses only '()[]{}'.
	 public boolean isValid(String s) {
	        if(s.isEmpty())
	        	return true;
	        ArrayDeque<Character> q = new ArrayDeque<Character>();
	        for(int i=0;i<s.length();i++) {
	        	char c= s.charAt(i);
	        	if(c=='('||c=='{'||c=='[') {
	        		q.push(c);
	        		continue;
	        	}
	        	if(q.isEmpty())
	        		return false;
	        	switch (c) {
				case ')': {
					char ex=q.pop();
					if(ex == '{' || ex=='[')
						return false;
					break;
				}
				case '}': {
					char ex=q.pop();
					if(ex == '(' || ex=='[')
						return false;
					break;
				}
				case ']': {
					char ex=q.pop();
					if(ex == '{' || ex=='(')
						return false;
					break;
				}
	        }
	    }
	        return q.isEmpty();
	}
	 
//	 Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system,
//	 convert it to the simplified canonical path.
//
//	 In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level,
//	 and any multiple consecutive slashes
//	 (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
//
//	 The canonical path should have the following format:
//
//	 The path starts with a single slash '/'.
//	 Any two directories are separated by a single slash '/'.
//	 The path does not end with a trailing '/'.
//	 The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
//	 Return the simplified canonical path.
//
//	  
//
//	 Example 1:
//
//	 Input: path = "/home/"
//	 Output: "/home"
//	 Explanation: Note that there is no trailing slash after the last directory name.
//	 Example 2:
//
//	 Input: path = "/../"
//	 Output: "/"
//	 Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
//	 Example 3:
//
//	 Input: path = "/home//foo/"
//	 Output: "/home/foo"
//	 Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
//	  
//
//	 Constraints:
//
//	 1 <= path.length <= 3000
//	 path consists of English letters, digits, period '.', slash '/' or '_'.
//	 path is a valid absolute Unix path.
	 //https://leetcode.com/problems/simplify-path/solutions/1847526/best-explanation-ever-possible-not-a-clickbait/?envType=study-plan-v2&envId=top-interview-150
	 public String simplifyPath(String path) {
	        Stack<String> stack = new Stack<String>();
	        StringBuilder res = new StringBuilder();
	        String[] p =path.split("/");
	        for(int i=0;i<p.length;i++) {
	        	if(!stack.isEmpty() && p[i].equals(".."))
	        		stack.pop();
	        	else if(!p[i].equals("")&&!p[i].equals(".")&&!p[i].equals(".."))
	        		stack.push(p[i]);
	        }
	        if(stack.isEmpty())
	        	return "/";
	        while(!stack.isEmpty())
	        	res.insert(0, stack.pop()).insert(0, "/");
	        return res.toString();
	 }
	 
//	 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//	 Implement the MinStack class:
//
//	 MinStack() initializes the stack object.
//	 void push(int val) pushes the element val onto the stack.
//	 void pop() removes the element on the top of the stack.
//	 int top() gets the top element of the stack.
//	 int getMin() retrieves the minimum element in the stack.
//	 You must implement a solution with O(1) time complexity for each function.
//
//	  
//
//	 Example 1:
//
//	 Input
//	 ["MinStack","push","push","push","getMin","pop","top","getMin"]
//	 [[],[-2],[0],[-3],[],[],[],[]]
//
//	 Output
//	 [null,null,null,null,-3,null,0,-2]
//
//	 Explanation
//	 MinStack minStack = new MinStack();
//	 minStack.push(-2);
//	 minStack.push(0);
//	 minStack.push(-3);
//	 minStack.getMin(); // return -3
//	 minStack.pop();
//	 minStack.top();    // return 0
//	 minStack.getMin(); // return -2
//	 
	 // https://leetcode.com/problems/min-stack/solutions/49010/clean-6ms-java-solution/?envType=study-plan-v2&envId=top-interview-150
//	 class MinStack {
//
//	    	private Node head;
//		    public MinStack() {
//		        
//		    }
//		    
//		    public void push(int val) {
//		        if(head==null)
//		        	head = new Node(val, val, null);
//		        else
//		        	head = new Node(val, Math.min(val, head.min), head);
//		        	
//		    }
//		    
//		    public void pop() {
//		        head=head.next;
//		    }
//		    
//		    public int top() {
//		        return head.val;
//		    }
//		    
//		    public int getMin() {
//		        return head.min;
//		    }
//		}
//	 
//	 class Node{
//		 int val;
//		 int min;
//		 Node next;
//		 Node(int val,
//		 int min,
//		 Node next){
//			 this.min=min;
//			 this.val=val;
//			 this.next=next;
//		 }
//	 }
	 
	 
	 //https://leetcode.com/problems/min-stack/solutions/1209254/c-simple-code-with-one-stack/?envType=study-plan-v2&envId=top-interview-150
	 class MinStack {
		 private Stack<int[]> stack = new Stack<int[]>();

		    public MinStack() {
		        
		    }
		    
		    public void push(int val) {
		    	int[] arr = new int[2];
		    	arr[0]=val;
		        	if(stack.isEmpty()) {
		        		arr[1]=val;
		        	}else {
		        		arr[1]=Math.min(stack.peek()[1],val);
		        	}
		        	stack.push(arr);
		    }
		    
		    public void pop() {
		    	stack.pop();
		    }
		    
		    public int top() {
		    	return stack.peek()[0];
		    }
		    
		    public int getMin() {
		    	return stack.peek()[1];
		    }
		}
	 
//	 You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
//
//	 Evaluate the expression. Return an integer that represents the value of the expression.
//
//	 Note that:
//
//	 The valid operators are '+', '-', '*', and '/'.
//	 Each operand may be an integer or another expression.
//	 The division between two integers always truncates toward zero.
//	 There will not be any division by zero.
//	 The input represents a valid arithmetic expression in a reverse polish notation.
//	 The answer and all the intermediate calculations can be represented in a 32-bit integer.
//	  
//
//	 Example 1:
//
//	 Input: tokens = ["2","1","+","3","*"]
//	 Output: 9
//	 Explanation: ((2 + 1) * 3) = 9
//	 Example 2:
//
//	 Input: tokens = ["4","13","5","/","+"]
//	 Output: 6
//	 Explanation: (4 + (13 / 5)) = 6
//	 Example 3:
//
//	 Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//	 Output: 22
//	 Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//	 = ((10 * (6 / (12 * -11))) + 17) + 5
//	 = ((10 * (6 / -132)) + 17) + 5
//	 = ((10 * 0) + 17) + 5
//	 = (0 + 17) + 5
//	 = 17 + 5
//	 = 22
	 
	 	 
	 public int evalRPN(String[] tokens) {
		 Stack<Integer> st = new Stack<Integer>();
		 Set<String> ops = Set.of("+", "-", "*", "/");
		 int res=0;
		 for(int i=0;i<tokens.length;i++) {
			 String t=tokens[i];
			 if(!ops.contains(t)) {
				 st.push(Integer.parseInt(t));
			 }else {
				 if(!st.isEmpty()&& st.size()>=2) {
					 int op1 = st.pop();
					 int op2= st.pop();
					 switch (t) {
					case "*": {
						res = op1*op2;
						break;
					}case "/": {
						res = op2/op1;
						break;
					}case "+": {
						res = op1+op2;
						break;
					}case "-": {
						res = op2-op1;
						break;
					}
					 }
					 st.push(res);
				 }
		 }
		 }
		 return st.pop();
	 }
	 
//	 Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
//
//			 Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
//
//			  
//
//			 Example 1:
//
//			 Input: s = "1 + 1"
//			 Output: 2
//			 Example 2:
//
//			 Input: s = " 2-1 + 2 "
//			 Output: 3
//			 Example 3:
//
//			 Input: s = "(1+(4+5+2)-3)+(6+8)"
//			 Output: 23
//			  
//
//			 Constraints:
//
//			 1 <= s.length <= 3 * 105
//			 s consists of digits, '+', '-', '(', ')', and ' '.
//			 s represents a valid expression.
//			 '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
//			 '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
//			 There will be no two consecutive operators in the input.
//			 Every number and running calculation will fit in a signed 32-bit integer.
	 //https://leetcode.com/problems/basic-calculator/solutions/4011331/beats-75-easy-java-solution-using-stack-with-detailed-approach/?envType=study-plan-v2&envId=top-interview-150
	 	public int calculate(String s) {
	        Stack<Integer> st = new Stack<Integer>();
	        int ans =0,num=0,sign=1;
	        for(int i=0;i<s.length();i++) {
	        	char c = s.charAt(i);
	        	if(Character.isDigit(c))
	        		num=num*10 + (int)(c-'0');
	        	else if(c=='+') {
	        		ans+=sign*num;
	        		num=0;
	        		sign=1;
	        	}else if(c=='-') {
	        		ans+=sign*num;
	        		num=0;
	        		sign=-1;
	        	}else if(c=='(') {
	        		st.push(ans);
	        		st.push(sign);
	        		ans=0;
	        		sign=1;
	        	}else if(c==')') {
	        		ans+=sign*num;
	        		num=0;
	        		ans*=st.pop();
	        		ans+=st.pop();
	        	}
	        }
	        if(num!=0)
	        	ans+=sign*num;
	        return ans;
	    }
	 
}

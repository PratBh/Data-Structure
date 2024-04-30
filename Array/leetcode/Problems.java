package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problems {
//	Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//
//			Notice that the solution set must not contain duplicate triplets.
//
//			 
//
//			Example 1:
//
//			Input: nums = [-1,0,1,2,-1,-4]
//			Output: [[-1,-1,2],[-1,0,1]]
//			Example 2:
//
//			Input: nums = []
//			Output: []
//			Example 3:
//
//			Input: nums = [0]
//			Output: []
	
	public  List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		Set<List<Integer>> temp=new HashSet<List<Integer>>();
		int n=nums.length;
		Arrays.sort(nums);
		for(int i=0;i<n-2;i++) {
			int j=i+1;
			int k=n-1;
			while(j<k) {
				if(nums[i]+nums[j]+nums[k]==0) {
					List<Integer> l=new ArrayList<Integer>();
					l.add(nums[i]);
					l.add(nums[j]);
					l.add(nums[k]);
					temp.add(l);
					j++;
					k--;
				}else if (nums[i]+nums[j]+nums[k]<0)
							j++;
				else
					k--;
			}
		}
		for(List<Integer>x:temp)
			result.add(x);
		return result;
		
	}
	
//	Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//			You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//			You can return the answer in any order.
//
//			 
//
//			Example 1:
//
//			Input: nums = [2,7,11,15], target = 9
//			Output: [0,1]
//			Output: Because nums[0] + nums[1] == 9, we return [0, 1].
	
	public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        	hm.put(nums[i], i);
        
        for(int i=0;i<nums.length;i++) {
        	int diff=target-nums[i];
        	if(hm.containsKey(diff)&&hm.get(diff)!=i)
        		return new int[] {i,hm.get(diff)};
        }
        return null;
    }
	
//	Given an array of integers and a number x, find the smallest subarray with sum greater than the given value. 
//
//	Examples:
//	arr[] = {1, 4, 45, 6, 0, 19}
//	   x  =  51
//	Output: 3
//	Minimum length subarray is {4, 45, 6}
//
//	arr[] = {1, 10, 5, 2, 7}
//	   x  = 9
//	Output: 1
//	Minimum length subarray is {10}
//
//	arr[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}
//	    x = 280
//	Output: 4
//	Minimum length subarray is {100, 1, 0, 200}
//
//	arr[] = {1, 2, 4}
//	    x = 8
//	Output : Not Possible
//	Whole array sum is smaller than 8.
	
	public int findSmallestSubarr(int []arr,int target) {
		int len=Integer.MAX_VALUE,i=0,j=0,sum=0;
		while(j<arr.length) {
			sum+=arr[j];
			if(sum<=target) {
				j++;
			}else {
				len=Math.min(len, j-i+1);
				while(sum>target) {
					sum-=arr[i];
					i++;
					if(sum>target)
						len=Math.min(len, j-i+1);
				}
				j++;
			}
		}
		return len;
	}
//	Given an integer n, return true if it is a power of three. Otherwise, return false.
//
//			An integer n is a power of three, if there exists an integer x such that n == 3x.
//
//			 
//
//			Example 1:
//
//			Input: n = 27
//			Output: true
//			Example 2:
//
//			Input: n = 0
//			Output: false
//			Example 3:
//
//			Input: n = 9
//			Output: true
//			Example 4:
//
//			Input: n = 45
//			Output: false
	
	public boolean isPowerOfThree(int n) {
        if(n<1)
        	return false;
        while(n%3==0)
        	n/=3;
        return n==1;
    }
	
//	Alternate approach
//	An important piece of information can be deduced from the function signature
//
//
//	In particular, n is of type int. In Java, this means it is a 4 byte, signed integer [ref]. The maximum value of this data type is 2147483647. Three ways of calculating this value are
//
//	Google
//	System.out.println(Integer.MAX_VALUE);
//	MaxInt = \frac{ 2^{32} }{2} - 1 
//	2
//	2 
//	32
//	 
//	​
//	 −1 since we use 32 bits to represent the number, half of the range is used for negative numbers and 0 is part of the positive numbers
//	Knowing the limitation of n, we can now deduce that the maximum value of n that is also a power of three is 1162261467. We calculate this as:
//
//	3^{\lfloor{}\log_3{MaxInt}\rfloor{}} = 3^{\lfloor{}19.56\rfloor{}} = 3^{19} = 11622614673 
//	⌊log 
//	3
//	​
//	 MaxInt⌋
//	 =3 
//	⌊19.56⌋
//	 =3 
//	19
//	 =1162261467
//
//	Therefore, the possible values of n where we should return true are 3^03 
//	0
//	 , 3^13 
//	1
//	  ... 3^{19}3 
//	19
//	 . Since 3 is a prime number, the only divisors of 3^{19}3 
//	19
//	  are 3^03 
//	0
//	 , 3^13 
//	1
//	  ... 3^{19}3 
//	19
//	 , therefore all we need to do is divide 3^{19}3 
//	19
//	  by n. A remainder of 0 means n is a divisor of 3^{19}3 
//	19
//	  and therefore a power of three.
	
	public boolean isPowerOfThreeAlt(int n) {
		return n>0 && Math.pow(3, 19)%n==0;
	}
       
	
//	Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
//
//			An integer y is a power of three if there exists an integer x such that y == 3x.
//
//			 
//
//			Example 1:
//
//			Input: n = 12
//			Output: true
//			Explanation: 12 = 31 + 32
//			Example 2:
//
//			Input: n = 91
//			Output: true
//			Explanation: 91 = 30 + 32 + 34
//			Example 3:
//
//			Input: n = 21
//			Output: false
	
	public boolean checkPowersOfThree(int n) {
        int pow3=1;
        List<Integer> l=new ArrayList<Integer>();
        while(pow3<=n) {
        	l.add(pow3);
        	pow3*=3;
        }
        return dfs(n, 0, l);
    }
	
	boolean dfs(int tar,int start,List<Integer> l) {
		if(start==l.size())
			return tar==0;
		boolean a=dfs(tar-l.get(start), start+1, l);
		boolean b=dfs(tar, start+1, l);
		return a||b;
	}
//	Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
//
//	As a reminder, a binary search tree is a tree that satisfies these constraints:
//
//	The left subtree of a node contains only nodes with keys less than the node's key.
//	The right subtree of a node contains only nodes with keys greater than the node's key.
//	Both the left and right subtrees must also be binary search trees.
//	Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
//
//	 
//
//	Example 1:
//
//
//	Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//	Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
//	Example 2:
//
//	Input: root = [0,null,1]
//	Output: [1,null,1]
//	Example 3:
//
//	Input: root = [1,0,2]
//	Output: [3,3,2]
//	Example 4:
//
//	Input: root = [3,2,4,1]
//	Output: [7,9,4,10]
//	 
//
//	Constraints:
//
//	The number of nodes in the tree is in the range [0, 104].
//	-104 <= Node.val <= 104
//	All the values in the tree are unique.
//	root is guaranteed to be a valid binary search tree.
	
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
	public TreeNode convertBST(TreeNode root) {
        int sum=0;
        return convertBSTUtil(root, sum);
    }
	
	public TreeNode convertBSTUtil(TreeNode root,int sum) {
        if(root!=null) {
        	convertBSTUtil(root.right,sum);
        	sum+=root.val;
        	root.val=sum;
        	convertBSTUtil(root.left, sum);
        }
        return root;
    }
	
	//preOrder without recursion
	
	ArrayList<Integer> preOrder(TreeNode root){
		ArrayList<Integer> res=new ArrayList<Integer>();
		if(root==null)
			return res;
		Stack<TreeNode>st=new Stack<Problems.TreeNode>();
		st.push(root);
		while(!st.empty()) {
			TreeNode tmp=st.pop();
			res.add(tmp.val);
			if(tmp.right!=null)
				st.push(tmp.right);
			if(tmp.left!=null)
				st.push(tmp.left);
		}
		return res;
	}
	
	//inorder without recursion
	ArrayList<Integer> inorder(TreeNode root){
		ArrayList<Integer> res=new ArrayList<Integer>();
		if(root==null)
			return res;
		Stack<TreeNode>st=new Stack<Problems.TreeNode>();
		TreeNode curr=root;
		boolean done=false;
		while(!done) {
			if(curr!=null) {
				st.push(curr);
				curr=curr.left;
			}
			else {
				if(st.isEmpty())
					done=true;
				else {
					curr=st.pop();
					res.add(curr.val);
					curr=curr.right;
				}
			}
		}

		return res;
	}
	
	//postorder without recursion
	ArrayList<Integer> postorder(TreeNode root){
		ArrayList<Integer> res=new ArrayList<Integer>();
		if(root==null)
			return res;
		Stack<TreeNode>st=new Stack<Problems.TreeNode>();
		st.push(root);
		TreeNode prev=null;
		while(!st.isEmpty()) {
			TreeNode curr=st.peek();
			if(prev==null||prev.left==curr||prev.right==curr) {
				if(curr.left!=null)
					st.push(curr.left);
				else if(curr.right!=null)
					st.push(curr.right);
			}else if(curr.left==prev) {
				if(curr.right!=null)
					st.push(curr.right);
				else {
					res.add(curr.val);
					st.pop();
				}
			}
			prev=curr;
		}
		return res;
	}
	
	//find the most frequent number in an array
	
	void mostFreqNum(int[]arr) {
		Map<Integer, Long>mp=new HashMap<Integer, Long>();
		mp=Arrays.stream(arr).mapToObj(i->i).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//		for(int i:arr) 
//			mp.put(i, mp.getOrDefault(i, 0)+1);
//		Map<Integer,Integer> hm=mp.entrySet().stream().sorted((e1,e2)->e2.getValue()-e1.getValue()).
//				collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (newValue,oldValue)->newValue, LinkedHashMap::new));
		int max=Arrays.stream(arr).mapToObj(i->i).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
		System.out.println(max);
		for (Entry<Integer, Long> en :
            mp.entrySet()) {
           System.out.println("Key = " + en.getKey()
                              + ", Value = "
                              + en.getValue());
       }
	}
	
	Map<Integer,Integer> compareMap(Map<Integer,Integer>mp){
		List<Entry<Integer,Integer>> lm=new ArrayList<Map.Entry<Integer,Integer>>(mp.entrySet());
		Collections.sort(lm, (e1,e2)->e2.getValue()-e1.getValue());
		HashMap<Integer, Integer> temp=new HashMap<Integer, Integer>();
		for(Entry<Integer, Integer> en:lm)
			temp.put(en.getKey(),en.getValue());
		return temp;
	}
	
	//	Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
//
//	Each element in the array represents your maximum jump length at that position.
//
//	Determine if you are able to reach the last index.
//
//	 
//
//	Example 1:
//
//	Input: nums = [2,3,1,1,4]
//	Output: true
//	Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//	Example 2:
//
//	Input: nums = [3,2,1,0,4]
//	Output: false
//	Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
	//https://leetcode.com/problems/jump-game/discuss/1277838/All-Solutions-Java-or-4-Solutions
	public boolean canJump(int[] nums) {
		int n=nums.length;
		boolean[] dp = new boolean[n];
		dp[n-1]=true;
		for(int i=n-2;i>=0;i--){
			boolean canReach = false;
			for(int k=1;k<=nums[i] && i+k < n;k++){
				canReach = canReach||dp[i+k];
			}
			dp[i]=canReach;
		}
		return dp[0];
    } 
	
//	Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
//
//	Each element in the array represents your maximum jump length at that position.
//
//	Your goal is to reach the last index in the minimum number of jumps.
//
//	You can assume that you can always reach the last index.
//
//	 
//
//	Example 1:
//
//	Input: nums = [2,3,1,1,4]
//	Output: 2
//	Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//	Example 2:
//
//	Input: nums = [2,3,0,1,4]
//	Output: 2
	//https://leetcode.com/problems/jump-game-ii/discuss/1278393/Java-4-Solutions
	 public int jump(int[] nums) {
		 int n=nums.length;
	     int  dp[]=new int[n];
		 Arrays.fill(dp, Integer.MAX_VALUE);
		 dp[0]=0;
		 	for(int i=0;i<n;i++) {
		 		if(dp[i]==Integer.MAX_VALUE)
		 			continue;
		 		for(int j=1;j+i<n&& j<=nums[i];j++) {
		 			dp[i+j]=Math.min(dp[i+j], dp[i]+1);
		 		}
		 	}
		 	return dp[n-1];
	    }
	 
//	 Given an array arr[] of integers, find out the maximum difference between any two elements such that larger element appears after the smaller number. 
//
//	 Examples : 
//
//	 Input : arr = {2, 3, 10, 6, 4, 8, 1}
//	 Output : 8
//	 Explanation : The maximum difference is between 10 and 2.
//
//	 Input : arr = {7, 9, 5, 6, 3, 2}
//	 Output : 2
//	 Explanation : The maximum difference is between 9 and 7.
//	 https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
	 
	 int maxDiff(int [] arr) {
		 int n=arr.length;
		 if(n<2)
			 return 0;
		 int max_diff=arr[1]-arr[0];
		 int min_element=arr[0];
		 for(int i=1;i<n;i++) {
			 if(max_diff<arr[i]-min_element)
				 max_diff=arr[i]-min_element;
			 if(arr[i]<min_element)
				 min_element=arr[i];
		 }
		 return max_diff;
	 }
	 
//	 The cost of a stock on each day is given in an array, find the max profit that you can make by buying and selling in those days. 
//	 For example, if the given array is {100, 180, 260, 310, 40, 535, 695}, the maximum profit can earned by buying on day 0, selling on day 3.
//	 Again buy on day 4 and sell on day 6. If the given array of prices is sorted in decreasing order, then profit cannot be earned at all.
	 
	 int maxProfit(int []arr) {
		 int min_price=arr[0],max_profit=0,local_profit=0;
		 for(int i=1;i<arr.length;i++) {
			 if(min_price>arr[i]) {
				 min_price=arr[i];
				 max_profit+=local_profit;
				 local_profit=0;
			 }
			 if(local_profit<arr[i]-min_price)
				 local_profit=arr[i]-min_price;
		 }
		 System.out.println(max_profit+local_profit);
		 return max_profit+local_profit;
	 }
	 
//	 Given an array of n distinct elements, find the minimum number of swaps required to sort the array.
//
//	 Examples: 
//
//	 Input: {4, 3, 2, 1}
//	 Output: 2
//	 Explanation: Swap index 0 with 3 and 1 with 2 to 
//	               form the sorted array {1, 2, 3, 4}.
//
//	 Input: {1, 5, 4, 3, 2}
//	 Output: 2
	 
//	 https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
	 
	 int minSwaps(int[] arr) {
		 HashMap<Integer, Integer> map = new HashMap<>();
		 int n=arr.length;
		 for(int i=0;i<n;i++) {
			 map.put(arr[i], i);
		 }
		 boolean visited[]=new boolean[n];
		 Arrays.fill(visited, false);
		 int ans=0;
		 Arrays.sort(arr);
		 for(int i=0;i<n;i++) {
			if(visited[i]==true || map.get(arr[i])==i)
				continue;
			int j=i,cycle_len=0;
			while(!visited[j]) {
				visited[j]=true;
				j=map.get(arr[j]);
				cycle_len++;
			}
			if(cycle_len>0)
				ans+=cycle_len;
				
		 }
		 return ans;
	 }

}

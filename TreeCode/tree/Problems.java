package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problems {
	public void verticalOrder(treeNode root) {
		HashMap<Integer, List<Integer>> hm=new HashMap<Integer, List<Integer>>();
		vOrder(hm, root, 0);
		for(int k:hm.keySet()) {
			hm.get(k).forEach(System.out::print);
			System.out.println();
		}
	}
	
	private void vOrder(HashMap<Integer, List<Integer>> hm,treeNode root,int c) {
		if(root.leftNode!=null)
			vOrder(hm, root.leftNode, c-1);
		if(root.rightNode!=null)
			vOrder(hm, root.rightNode, c+1);
		if(hm.get(c)==null) {
			hm.put(c, new ArrayList<Integer>());
		}
		hm.get(c).add(root.data);
	}
	
	//Given the root of a binary tree, return the inorder traversal of its nodes' values.
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
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<Integer>();
        if(root==null)
        	return res;
        inorder(root, res);
        return res;
    }
	
	void inorder(TreeNode root,List<Integer> res) {
		if(root==null)
			return;
		inorder(root.left, res);
		res.add(root.val);
		inorder(root.right, res);
	}
	
	//Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
	//https://www.geeksforgeeks.org/number-of-unique-bst-with-a-given-key-dynamic-programming/
	public int numTrees(int n) {
		int [] dp=new int[n+1];
		Arrays.fill(dp, 0);
		dp[0]=dp[1]=1;
		for(int i=2;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				dp[i]+=dp[j-1]*dp[i-j];// n-i in right * i-1 in left
			}
		}
		return dp[n];
    }
	
//	Given an integer n, return all the structurally unique BST's (binary search trees), which has
//			exactly n nodes of unique values from 1 to n. Return the answer in any order.
//			https://leetcode.com/problems/unique-binary-search-trees-ii/
	
	//https://www.youtube.com/watch?v=SHp-uB4ngkU
	public List<TreeNode> generateTrees(int n) {
        return constructTrees(1, n);
    }
	
	public List<TreeNode> constructTrees(int start, int end){
		 List<TreeNode> list = new ArrayList<>();
		 if(start>end) {
			 list.add(null);
			 return list;
		 }
		 for(int i=start;i<=end;i++) {
			 List<TreeNode> leftSubtree=constructTrees(start, i-1);
			 List<TreeNode> rightSubtree=constructTrees(i+1, end);
			 for(int j=0;j<leftSubtree.size();j++) {
				 TreeNode left=leftSubtree.get(j);
				 for(int k=0;k<rightSubtree.size();k++) {
					 TreeNode right=rightSubtree.get(k);
					 TreeNode root=new TreeNode(i);
					 root.right=right;
					 root.left=left;
					 list.add(root);
				 }
			 }
		 }
		 return list;
	}
//	Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and 
//			operators. You may return the answer in any order.
//
//			 
//
//			Example 1:
//
//			Input: expression = "2-1-1"
//			Output: [0,2]
//			Explanation:
//			((2-1)-1) = 0 
//			(2-(1-1)) = 2
//			Example 2:
//
//			Input: expression = "2*3-4*5"
//			Output: [-34,-14,-10,-10,10]
//			Explanation:
//			(2*(3-(4*5))) = -34 
//			((2*3)-(4*5)) = -14 
//			((2*(3-4))*5) = -10 
//			(2*((3-4)*5)) = -10 
//			(((2*3)-4)*5) = 10
		//https://leetcode.com/problems/different-ways-to-add-parentheses/discuss/1184602/Java-easy-recursive-memorization-beats-100!
	public List<Integer> diffWaysToCompute(String expression) {
        List[][]memo=new List[expression.length()+1][expression.length()+1];
        return ways(expression, 0, expression.length(), memo);
    }
	
	private List<Integer> ways(String expression, int start, int end, List[][] memo){
		if(memo[start][end]!=null)
			return memo[start][end];
		List<Integer> res=new ArrayList<Integer>();
		for(int i=start;i<end;i++) {
			char curr=expression.charAt(i);
			if(curr !='+' && curr !='*' && curr !='-')
				continue;
			List<Integer> leftVals=ways(expression, start, i, memo);
			List<Integer> rightVals=ways(expression, i+1, end, memo);
			for(int left:leftVals) {
				for(int right:rightVals) {
					if(curr=='+')
						res.add(left+right);
					if(curr=='*')
						res.add(left*right);
					if(curr=='-')
						res.add(left-right);
				}
				
			}
		}
		if(res.size()==0)
			res.add(Integer.parseInt(expression.substring(start, end)));
		return res;
	}
	
//	Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//
//	A valid BST is defined as follows:
//
//	The left subtree of a node contains only nodes with keys less than the node's key.
//	The right subtree of a node contains only nodes with keys greater than the node's key.
//	Both the left and right subtrees must also be binary search trees.
//	 
//
//	Example 1:
//
//
//	Input: root = [2,1,3]
//	Output: true
//	Example 2:
//
//
//	Input: root = [5,1,4,null,null,3,6]
//	Output: false
//	Explanation: The root node's value is 5 but its right child's value is 4.
//	 
//
//	Constraints:
//
//	The number of nodes in the tree is in the range [1, 104].
//	-231 <= Node.val <= 231 - 1
	//from book
	//https://leetcode.com/problems/validate-binary-search-tree/discuss/1200382/Java-or-Recursion-Magic
	public boolean isValidBST(TreeNode root) {
        if(root==null)
        	return true;
        if(root.left!=null && getMax(root.left)>=root.val)
        	return false;
        if(root.right!=null && getMin(root.right)<=root.val)
        	return false;
        if(!isValidBST(root.left)||!isValidBST(root.right))
        	return false;
        return true;
    }
	
	private int getMax(TreeNode root) {
		if(root.right==null)
			return root.val;
		return getMax(root.right);
	}
	
	private int getMin(TreeNode root) {
		if(root.left==null)
			return root.val;
		return getMin(root.left);
	}
//	
//	Given the root of a binary tree, return its maximum depth.
//
//			A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
	
	public int maxDepth(TreeNode root) {
        if(root==null)
        	return 0;
        int count =1;
        Queue<TreeNode> q=new LinkedList<Problems.TreeNode>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty()) {
        	TreeNode nd=q.poll();
        	if(nd!=null) {
        		if(nd.left!=null)
        			q.offer(nd.left);
        		if(nd.right!=null)
        			q.offer(nd.right);
        	}else {
        		if(!q.isEmpty()) {
        			count++;
        			q.offer(null);
        		}
        	}
        }
        return count;
    }
	
//	Given a binary tree, find its minimum depth.
//
//	The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
//
//	Note: A leaf is a node with no children.
	
	public int minDepth(TreeNode root) {
	     if(root==null)
	        	return 0;
	        int count =1;
	        Queue<TreeNode> q=new LinkedList<TreeNode>();
	        q.offer(root);
	        q.offer(null);
	        while(!q.isEmpty()) {
	        	TreeNode nd=q.poll();
	        	if(nd!=null) {
	        		if(nd.left==null && nd.right==null)
	        			return count;
	        		if(nd.left!=null)
	        			q.offer(nd.left);
	        		if(nd.right!=null)
	        			q.offer(nd.right);
	        	}else {
	        		if(!q.isEmpty()) {
	        			count++;
	        			q.offer(null);
	        		}
	        	}
	        }
	        return count;   
	    }
	
//	Write a function to count number of smaller elements on right of each element in an array. Given an unsorted array arr[] of distinct integers,
//	construct another array countSmaller[] such that countSmaller[i] contains count of smaller elements on right side of each element arr[i] in array.
//
//	Examples: 
//
//	Input:   arr[] =  {12, 1, 2, 3, 0, 11, 4}
//	Output:  countSmaller[]  =  {6, 1, 1, 1, 0, 1, 0} 
//
//	(Corner Cases)
//	Input:   arr[] =  {5, 4, 3, 2, 1}
//	Output:  countSmaller[]  =  {4, 3, 2, 1, 0} 
//
//	Input:   arr[] =  {1, 2, 3, 4, 5}
//	Output:  countSmaller[]  =  {0, 0, 0, 0, 0}
	
	int[] findSmaller(int[]arr) {
		int[]res=new int[arr.length];
		BSTWithLessCount bs=new BSTWithLessCount(0,0);
		BSTWithLessCount root=null;
		for(int i=arr.length-1;i>=0;i--) {
			res[i]=bs.addNode(root, arr[i], 0);
		}
		return res;
	}
}

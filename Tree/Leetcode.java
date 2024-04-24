package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Leetcode {
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
//	Given the root of a binary tree, return its maximum depth.
//
//			A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
//
//			 
//
//			Example 1:
//
//
//			Input: root = [3,9,20,null,null,15,7]
//			Output: 3
//			Example 2:
//
//			Input: root = [1,null,2]
//			Output: 2
	public int maxDepth(TreeNode root) {
        if(root==null)
        	return 0;
        int count = 1;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty()) {
        	TreeNode nd =q.poll();
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
	
//	Given the roots of two binary trees p and q, write a function to check if they are the same or not.
//
//	Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
//
//	 
//
//	Example 1:
//
//
//	Input: p = [1,2,3], q = [1,2,3]
//	Output: true
//	Example 2:
//
//
//	Input: p = [1,2], q = [1,null,2]
//	Output: false
//	Example 3:
//
//
//	Input: p = [1,2,1], q = [1,1,2]
//	Output: false
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p==null && q==null)
			return true;
		else {
			if (p==null||q==null) 
				return false;
			else if(p.val==q.val) 
	        	return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
		}
	return false;
    }
	
//	Given the root of a binary tree, invert the tree, and return its root.
//
//			 
//
//			Example 1:
//
//
//			Input: root = [4,2,7,1,3,6,9]
//			Output: [4,7,2,9,6,3,1]
//			Example 2:
//
//
//			Input: root = [2,1,3]
//			Output: [2,3,1]
//			Example 3:
//
//			Input: root = []
//			Output: []
	 	public TreeNode invertTree(TreeNode root) {
	        if(root == null)
	        	return root;
	        TreeNode temp = invertTree(root.left);
	        root.left=invertTree(root.right);
	        root.right=temp;
	        return root;
	    }
	 	
//	 	Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: root = [1,2,2,3,4,4,3]
//	 	Output: true
//	 	Example 2:
//
//
//	 	Input: root = [1,2,2,null,3,null,3]
//	 	Output: false
	 	
	 	public boolean isSymmetric(TreeNode root) {
	        if(root==null)
	        	return true;
	        return isSameTree(root.left, invertTree(root.right));
	    }
	 	
//	 	Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree 
//	 	and inorder is the inorder traversal of the same tree, construct and return the binary tree.
//
//	 			 
//
//	 			Example 1:
//
//
//	 			Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//	 			Output: [3,9,20,null,null,15,7]
//	 			Example 2:
//
//	 			Input: preorder = [-1], inorder = [-1]
//	 			Output: [-1]
	 	public TreeNode buildTree(int[] preorder, int[] inorder) {
	        if(preorder.length==0||preorder.length!=inorder.length)
	        	return null;
	        return buildBT(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
	    }
	 	public TreeNode buildBT(int[] preorder, int[] inorder,int preStart,int preEnd,int inStart,int inEnd) {
	 		if(preStart>preEnd||inStart>inEnd)
	 			return null;
	 		int data = preorder[preStart];
	 		TreeNode curr = new TreeNode(data);
	 		int offSet = inStart;
	 		for(;offSet<inEnd;offSet++) {
	 			if(inorder[offSet]==data)
	 				break;
	 		}
	 		curr.left = buildBT(preorder, inorder, preStart+1, preStart+offSet-inStart, inStart, offSet-1);
	 		curr.right = buildBT(preorder, inorder, preStart+offSet-inStart+1, preEnd, offSet+1, inEnd);
	 		return curr;
	 	}
	 	
//	 	Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and 
//	 	postorder is the postorder traversal of the same tree, construct and return the binary tree.
//
//	 			 
//
//	 			Example 1:
//
//
//	 			Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//	 			Output: [3,9,20,null,null,15,7]
//	 			Example 2:
//
//	 			Input: inorder = [-1], postorder = [-1]
//	 			Output: [-1]
	 	public TreeNode buildTree(int[] inorder, int[] postorder) {
	        if(postorder.length==0||postorder.length!=inorder.length)
	        	return null;
	        return buildBT(postorder, inorder, 0, postorder.length-1, 0, inorder.length-1);
	    }
	 	
	 	public TreeNode buildBT(int[] postorder, int[] inorder,int postStart,int postEnd,int inStart,int inEnd) {
	 		if(postStart>postEnd||inStart>inEnd)
	 			return null;
	 		int data = postorder[postEnd];
	 		TreeNode curr = new TreeNode(data);
	 		int offSet = inStart;
	 		for(;offSet<inEnd;offSet++) {
	 			if(inorder[offSet]==data)
	 				break;
	 		}
	 		curr.left = buildBT(postorder, inorder, postStart, postStart+offSet-inStart-1, inStart, offSet-1);
	 		curr.right = buildBT(postorder, inorder, postStart+offSet-inStart, postEnd-1, offSet+1, inEnd);
	 		return curr;
	    }
	 	
//	 	Given a binary tree
//
//	 	struct Node {
//	 	  int val;
//	 	  Node *left;
//	 	  Node *right;
//	 	  Node *next;
//	 	}
//	 	Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
//	 	Initially, all next pointers are set to NULL.
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: root = [1,2,3,4,5,null,7]
//	 	Output: [1,#,2,3,#,4,5,7,#]
//	 	Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node,
//	 	just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
//	 	Example 2:
//
//	 	Input: root = []
//	 	Output: []
	 	class Node {
	 	    public int val;
	 	    public Node left;
	 	    public Node right;
	 	    public Node next;

	 	    public Node() {}
	 	    
	 	    public Node(int _val) {
	 	        val = _val;
	 	    }

	 	    public Node(int _val, Node _left, Node _right, Node _next) {
	 	        val = _val;
	 	        left = _left;
	 	        right = _right;
	 	        next = _next;
	 	    }
	 	};
	 	
	 	public Node connect(Node root) {
	        if(root==null)
	        	return root;
	        Queue<Node> q = new LinkedList<Node>();
	        q.offer(root);
	        q.offer(null);
	        while(!q.isEmpty()) {
	        	Node nd = q.poll();
	        	if(nd != null) {
	        		nd.next = q.peek();
	        		if(nd.left!=null)
	        			q.offer(nd.left);
	        		if(nd.right!=null)
	        			q.offer(nd.right);
	        	}else {
	        		if(!q.isEmpty())
	        			q.offer(null);
	        	}
	        }
	        return root;
	    }
	 	
//	 	Given the root of a binary tree, flatten the tree into a "linked list":
//
//	 		The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
//	 		The "linked list" should be in the same order as a pre-order traversal of the binary tree.
//	 		 
//
//	 		Example 1:
//
//
//	 		Input: root = [1,2,5,3,4,null,6]
//	 		Output: [1,null,2,null,3,null,4,null,5,null,6]
//	 		Example 2:
//
//	 		Input: root = []
//	 		Output: []
//	 		Example 3:
//
//	 		Input: root = [0]
//	 		Output: [0]
	 	
	 	public void flatten(TreeNode root) {
	        if (root==null)
	        	return;
	        TreeNode prev=null,curr=root;
	        Stack<TreeNode> st =new Stack<Leetcode.TreeNode>();
	        st.push(root);
	        while(!st.isEmpty()) {
	        	curr = st.pop();
	        	if(curr.right!=null)
	        		st.push(curr.right);
	        	if(curr.left!=null)
	        		st.push(curr.left);
	        	if(prev!=null) {
	        		prev.left=null;
	        		prev.right=curr;
	        	}
	        	prev=curr;
	        }
	  
	    }
	 	
//	 	Given the root of a binary tree and an integer targetSum, return true if the tree has a 
//	 			root-to-leaf path such that adding up all the values along the path equals targetSum.
//
//	 			A leaf is a node with no children.
//
//	 			 
//
//	 			Example 1:
//
//
//	 			Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//	 			Output: true
//	 			Explanation: The root-to-leaf path with the target sum is shown.
//	 			Example 2:
//
//
//	 			Input: root = [1,2,3], targetSum = 5
//	 			Output: false
//	 			Explanation: There two root-to-leaf paths in the tree:
//	 			(1 --> 2): The sum is 3.
//	 			(1 --> 3): The sum is 4.
//	 			There is no root-to-leaf path with sum = 5.
//	 			Example 3:
//
//	 			Input: root = [], targetSum = 0
//	 			Output: false
//	 			Explanation: Since the tree is empty, there are no root-to-leaf paths.
	 	
	 	public boolean hasPathSum(TreeNode root, int targetSum) {
            if(root==null)
                return false;
	        if(root.left==null&& root.right==null)
	        	return root.val==targetSum;
	        int sum = targetSum-root.val;
	        return hasPathSum(root.left, sum)|| hasPathSum(root.right, sum);
	    }
	 	
//	 	You are given the root of a binary tree containing digits from 0 to 9 only.
//
//	 	Each root-to-leaf path in the tree represents a number.
//
//	 	For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
//	 	Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
//
//	 	A leaf node is a node with no children.
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: root = [1,2,3]
//	 	Output: 25
//	 	Explanation:
//	 	The root-to-leaf path 1->2 represents the number 12.
//	 	The root-to-leaf path 1->3 represents the number 13.
//	 	Therefore, sum = 12 + 13 = 25.
//	 	Example 2:
//
//
//	 	Input: root = [4,9,0,5,1]
//	 	Output: 1026
//	 	Explanation:
//	 	The root-to-leaf path 4->9->5 represents the number 495.
//	 	The root-to-leaf path 4->9->1 represents the number 491.
//	 	The root-to-leaf path 4->0 represents the number 40.
//	 	Therefore, sum = 495 + 491 + 40 = 1026.
	 	
	 	public int sumNumbers(TreeNode root) {
	 		return sumNumbersUtil(root, 0);
	    }
	 	public int sumNumbersUtil(TreeNode root,int sum) {
	 		 if(root==null)
	 			 return 0; 
	 		 int currSum = sum*10+root.val;
	 		 if(root.left==null && root.right==null)
	 			 return currSum;
	 		return  sumNumbersUtil(root.left, currSum)+
	 			 sumNumbersUtil(root.right, currSum);
	    }
	 	
//	 	A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
//	 	A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
//
//	 	The path sum of a path is the sum of the node's values in the path.
//
//	 	Given the root of a binary tree, return the maximum path sum of any non-empty path.
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: root = [1,2,3]
//	 	Output: 6
//	 	Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//	 	Example 2:
//
//
//	 	Input: root = [-10,9,20,null,null,15,7]
//	 	Output: 42
//	 	Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
	 	static int max_sum;
	 	public int maxPathSum(TreeNode root) {
	        max_sum=Integer.MIN_VALUE;
	        maxPathSumUtil(root);
	        int temp=max_sum;
	        max_sum=Integer.MIN_VALUE;
	        return temp;
	    }
	 	public int maxPathSumUtil(TreeNode root) {
	        if(root==null)
	        	return 0;
	        int leftSum = maxPathSumUtil(root.left);
	        int rightSum = maxPathSumUtil(root.right);
	        int root_left= root.val+leftSum;
	        int root_right= root.val+rightSum;
	        int all = root.val+leftSum+rightSum;
	        max_sum=Math.max(max_sum,Math.max(Math.max(root_left,root_right),Math.max(all,root.val)));
	        //max_sum = Math.max(max_sum, totalSum);
	        return Math.max(Math.max(root.val+leftSum,root.val+rightSum),root.val);
	    }
	 	
//	 	Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
//
//	 		BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. 
//	 		The pointer should be initialized to a non-existent number smaller than any element in the BST.
//	 		boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
//	 		int next() Moves the pointer to the right, then returns the number at the pointer.
//	 		Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
//
//	 		You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
//
//	 		 
//
//	 		Example 1:
//
//
//	 		Input
//	 		["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
//	 		[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
//	 		Output
//	 		[null, 3, 7, true, 9, true, 15, true, 20, false]
//
//	 		Explanation
//	 		BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
//	 		bSTIterator.next();    // return 3
//	 		bSTIterator.next();    // return 7
//	 		bSTIterator.hasNext(); // return True
//	 		bSTIterator.next();    // return 9
//	 		bSTIterator.hasNext(); // return True
//	 		bSTIterator.next();    // return 15
//	 		bSTIterator.hasNext(); // return True
//	 		bSTIterator.next();    // return 20
//	 		bSTIterator.hasNext(); // return False
	 	
	 	//https://leetcode.com/problems/binary-search-tree-iterator/solutions/1430547/c-simple-solution-using-stack-o-h-time-complexity-with-diagrammatic-explanation/?envType=study-plan-v2&envId=top-interview-150
	 	class BSTIterator {
	 		Stack<TreeNode> st ;
	 	    public BSTIterator(TreeNode root) {
	 	        this.st= new Stack<Leetcode.TreeNode>();
	 	        push(root);
	 	    }
	 	    private void push(TreeNode root) {
	 	    	while(root!=null) {
	 	    		st.push(root);
	 	    		root=root.left;
	 	    	}
	 	    }
	 	    
	 	    public int next() {
	 	        TreeNode nd = st.pop();
	 	        push(nd.right);
	 	        return nd.val;
	 	    }
	 	    
	 	    public boolean hasNext() {
	 	        return !st.isEmpty();
	 	    }
	 	}
	 	
//	 	Given the root of a complete binary tree, return the number of the nodes in the tree.
//
//	 			According to Wikipedia, every level, except possibly the last, is completely filled in a 
//	 			complete binary tree, and all nodes in the last level are as far left as possible. 
//	 			It can have between 1 and 2h nodes inclusive at the last level h.
//
//	 			Design an algorithm that runs in less than O(n) time complexity.
//
//	 			 
//
//	 			Example 1:
//
//
//	 			Input: root = [1,2,3,4,5,6]
//	 			Output: 6
//	 			Example 2:
//
//	 			Input: root = []
//	 			Output: 0
//	 			Example 3:
//
//	 			Input: root = [1]
//	 			Output: 1
	 	
	 	public int countNodes(TreeNode root) {
	        if(root==null)
	        	return 0;
	        int count =1;
	        Stack<TreeNode> st= new Stack<Leetcode.TreeNode>();
	        st.add(root);
	        while(!st.isEmpty()) {
	        	TreeNode nd = st.pop();
	        	if(nd.left!=null) {
	        		st.add(nd.left);
	        		count++;
	        	}
	        	if(nd.right!=null) {
	        		st.add(nd.right);
	        		count++;
	        	}
	        }
	        return count;
	    }
	      
//	        Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
//
//	        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes 
//	        p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
//
//	         
//
//	        Example 1:
//
//
//	        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//	        Output: 3
//	        Explanation: The LCA of nodes 5 and 1 is 3.
//	        Example 2:
//
//
//	        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//	        Output: 5
//	        Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
//	        Example 3:
//
//	        Input: root = [1,2], p = 1, q = 2
//	        Output: 1
	        
//	        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//	            
//	        }
	 	//https://www.youtube.com/watch?v=KobQcxdaZKY
	        public TreeNode lowestCommonAncestorUtil(TreeNode root, TreeNode p, TreeNode q) {
	            if(root==null)
	            	return null;
	            else if(root== p || root==q)
	            	return root;
	            else {
	            	TreeNode leftR = lowestCommonAncestorUtil(root.left, p, q);
	            	TreeNode rightR = lowestCommonAncestorUtil(root.right, p, q);
	            	if(leftR!=null && rightR!=null)
	            		return root;
	            	else {
	            		if(leftR!=null)
	            			return leftR;
	            		else if(rightR!=null)
	            			return rightR;
	            		else 
	            			return null;
	            	}
	            }
	            
	        }
	        
//	        Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//
//	        		 
//
//	        		Example 1:
//
//
//	        		Input: root = [1,2,3,null,5,null,4]
//	        		Output: [1,3,4]
//	        		Example 2:
//
//	        		Input: root = [1,null,3]
//	        		Output: [1,3]
//	        		Example 3:
//
//	        		Input: root = []
//	        		Output: []
	        
	        public List<Integer> rightSideView(TreeNode root) {
	            if(root==null)
	            	return null;
	            TreeNode prev = null ;
	            Queue<TreeNode> q = new LinkedList<Leetcode.TreeNode>();
	            q.offer(root);
	            q.offer(null);
	            List<Integer> res =new ArrayList<Integer>();
	            while(!q.isEmpty()) {
	            	TreeNode nd = q.poll();
	            	if(nd!=null) {
	            		prev = nd;
	            		if(nd.left!=null)
	            			q.offer(nd.left);
	            		if(nd.right!=null)
	            			q.offer(nd.right);
	            	}else {
	            		if(prev!=null) {
	            			res.add(prev.val);
	            			prev=null;
	            		}
	            		if(!q.isEmpty())
	            			q.offer(null);
	            	}
	            }
	            return res;
	        }
	        
//	        Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
//	        		 
//
//	        		Example 1:
//
//
//	        		Input: root = [3,9,20,null,null,15,7]
//	        		Output: [3.00000,14.50000,11.00000]
//	        		Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
//	        		Hence return [3, 14.5, 11].
//	        		Example 2:
//
//
//	        		Input: root = [3,9,20,15,7]
//	        		Output: [3.00000,14.50000,11.00000]
	        
	        public List<Double> averageOfLevels(TreeNode root) {
	        	if(root==null)
	            	return new ArrayList<Double>();
	            double levelSum=0,nodeCount =0;
	            Queue<TreeNode> q = new LinkedList<Leetcode.TreeNode>();
	            q.offer(root);
	            q.offer(null);
	            List<Double> res =new ArrayList<Double>();
	            while(!q.isEmpty()) {
	            	TreeNode nd = q.poll();
	            	if(nd!=null) {
	            		levelSum+=nd.val;
	            		nodeCount++;
	            		if(nd.left!=null)
	            			q.offer(nd.left);
	            		if(nd.right!=null)
	            			q.offer(nd.right);
	            	}else {
	            		Double k = levelSum/nodeCount;
	            		res.add(k);
	            		levelSum=0;nodeCount =0;
	            		if(!q.isEmpty())
	            			q.offer(null);
	            	}
	            }
	            return res;
	        }
//	        Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
//
//	        		 
//
//	        		Example 1:
//
//
//	        		Input: root = [4,2,6,1,3]
//	        		Output: 1
//	        		Example 2:
//
//
//	        		Input: root = [1,0,48,null,null,12,49]
//	        		Output: 1
	        //https://leetcode.com/problems/minimum-absolute-difference-in-bst/solutions/3636691/java-solution-of-minimum-absolute-difference-in-bst/?envType=study-plan-v2&envId=top-interview-150
	        public int getMinimumDifference(TreeNode root) {
	        	if(root==null)
	            	return 0;
	        	int ans = Integer.MAX_VALUE;
	        	int prev = -1;
	        	Stack<TreeNode> st =new Stack<Leetcode.TreeNode>();
	        	while(root!=null||!st.isEmpty()) {
	        		while(root!=null) {
	        			st.push(root);
	        			root=root.left;
	        		}
	        		root = st.pop();
	        		if(prev>=0)
	        			ans=Math.min(ans,Math.abs(prev-root.val));
	        		prev=root.val;
	        		root=root.right;
	        	}
	        	return ans;
	        	
	        } 
//	        Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
//
//	        		 
//
//	        		Example 1:
//
//
//	        		Input: root = [3,1,4,null,2], k = 1
//	        		Output: 1
//	        		Example 2:
//
//
//	        		Input: root = [5,3,6,2,4,null,null,1], k = 3
//	        		Output: 3
	        //https://www.youtube.com/watch?v=9TJYWh0adfk
	        public int kthSmallest(TreeNode root, int k) {
	        	Stack<TreeNode> st =new Stack<Leetcode.TreeNode>();
	        	int result =-1,count=1;boolean done = false;
	        	while(!done) {
	        		if(root!=null) {
	        			st.push(root);
	        			root=root.left;
	        		}else {
	        			if(st.isEmpty())
	        				done =true;
	        			else {
	        				root = st.pop();
	        				if(count==k)
	        					return root.val;
	        				else
	        					count++;
	        				root=root.right;
	        			}
	        		}
	        	}
	        	return result;
	        }
	        
	        //find kth largest number in a BST
	        //just traverse in reverse of normal in order i.e. right-root-left
	        public int kthLargest(TreeNode root, int k) {
	        	Stack<TreeNode> st =new Stack<Leetcode.TreeNode>();
	        	int result =-1,count=1;boolean done = false;
	        	while(!done) {
	        		if(root!=null) {
	        			st.push(root);
	        			root=root.right;
	        		}else {
	        			if(st.isEmpty())
	        				done =true;
	        			else {
	        				root = st.pop();
	        				if(count==k)
	        					return root.val;
	        				else
	        					count++;
	        				root=root.left;
	        			}
	        		}
	        	}
	        	return result;
	        }

}

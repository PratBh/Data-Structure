package Tree;

import java.util.*;


public class Traversal {
	public static void main(String args[])
	{
	    TreeNode root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
	    root.left.left = new TreeNode(4);
	    root.left.right = new TreeNode(5);
	    postOrderRec(root);
	}
	
	static void inOrder(TreeNode root) {
		if(root==null)
			return;
		Stack<TreeNode> st = new Stack();
		TreeNode curr =root;
		while(curr!=null || !st.isEmpty()) {
			while(curr!=null) {
				st.add(curr);
				curr=curr.left;
			}
			curr = st.pop();
			System.out.println(curr.val);
			curr = curr.right;
		}
	}
	
	static void inOrderRec(TreeNode root) {
		if(root==null)
			return;
		inOrderRec(root.left);
		System.out.println(root.val);
		inOrderRec(root.right);
	}
	
	static void preOrder(TreeNode root) {
		if(root==null)
			return;
		Stack<TreeNode> st = new Stack();
		st.add(root);
		while(!st.isEmpty()) {
			TreeNode temp = st.pop();
			System.out.println(temp.val);
			if(temp.right!=null)
				st.add(temp.right);
			if(temp.left!=null)
				st.add(temp.left);
		}
		
	}
	
	static void preOrderRec(TreeNode root) {
		if(root==null)
			return;
		System.out.println(root.val);
		preOrderRec(root.left);
		preOrderRec(root.right);
	}
	
	static void postOrder(TreeNode root) {
		if(root==null)
			return;
		Stack<TreeNode> st = new Stack();
		st.add(root);
		List<Integer> al = new ArrayList();
		while(!st.isEmpty()) {
			TreeNode temp = st.pop();
			al.add(0,temp.val);
			if(temp.left!=null)
				st.add(temp.left);
			if(temp.right!=null)
				st.add(temp.right);
		}
		
		al.stream().forEach(t->System.out.println(t));
	}
	
	static void postOrderRec(TreeNode root) {
		if(root==null)
			return;
		postOrderRec(root.left);
		postOrderRec(root.right);
		System.out.println(root.val);
	}
	
}
class TreeNode{
	int val;
	TreeNode right;
	TreeNode left;
	
	public TreeNode(int val) {
		this.val=val;
		right=null;
		left=null;
	}
}

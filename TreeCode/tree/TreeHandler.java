+package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeHandler {
	
	static void preOrder(treeNode node) {
		if(node != null) {
			System.out.print(node.getData());
			preOrder(node.leftNode);
			preOrder(node.rightNode);
		}
	}
	
	static void inOrder(treeNode node) {
		if(node != null) {
			inOrder(node.leftNode);
			System.out.print(node.getData());
			inOrder(node.rightNode);
		}
	}
	
	static void postOrder(treeNode node) {
		if(node != null) {
			postOrder(node.leftNode);
			postOrder(node.rightNode);
			System.out.print(node.getData());
		}
	}
	
	static ArrayList<Integer> preOrderTraversal(treeNode root){
		ArrayList<Integer> res =new ArrayList<Integer>();
		if(root==null) 
			return null;
		Stack<treeNode>st=new Stack<treeNode>();
		st.push(root);
		while(!st.isEmpty()) {
			treeNode tmp =st.pop();
			res.add(tmp.data);
			if(tmp.rightNode!=null){
				st.add(tmp.rightNode);
			}
			if(tmp.leftNode!=null) {
				st.add(tmp.leftNode);
			}
		}
		return res;
	}
	
	static ArrayList<Integer> inOrderTraversal(treeNode root){
		ArrayList<Integer> res =new ArrayList<Integer>();
		if(root==null) 
			return null;
		Stack<treeNode>st=new Stack<treeNode>();
		treeNode currNode = root;
		boolean done=false;
		while(!done) {
			if(currNode != null ) {
				st.push(currNode);
				currNode=currNode.leftNode;
			}else {
				if(st.isEmpty())
					done=true;
				else {
					currNode=st.pop();
					res.add(currNode.data);
					currNode=currNode.rightNode;
				}
			}
		}
		return res;
	}
	
	static int findMax(treeNode node) {
		if (node == null) 
            return Integer.MIN_VALUE;
		int max = node.data;
		if(node != null) {
			int leftMax=findMax(node.getLeftNode());
			int rightMax=findMax(node.getRightNode());
			
			if(leftMax>max)
				max=leftMax;
			if(rightMax>max)
				max=rightMax;
		}
		
		return max;
	}
	
	
	static int findMaxWithoutRecursion(treeNode root) {
		if(root == null) 
			return Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			treeNode tmp = q.poll();
			if(tmp.data>max) {
				max=tmp.data;
			}
			if(tmp!=null) {
				if(tmp.leftNode!=null) {
					q.offer(tmp.leftNode);
				}
				if(tmp.rightNode!=null) {
					q.offer(tmp.rightNode);
				}
			}
		}
		
		return max;
	}
	
	static boolean findData(treeNode root,int data) {
		if(root == null)
			return false;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			treeNode tmp =q.poll();
			if(tmp.getData() == data)
				return true;
			if(tmp != null) {
				if(tmp.leftNode!=null) {
					q.offer(tmp.leftNode);
				}
				if(tmp.rightNode!=null) {
					q.offer(tmp.rightNode);
				}
			}
		}
		
		return false;
	}
	
	static treeNode insertDate(treeNode root, int data) {
		if (root == null)
			return null;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			treeNode tmp =q.poll();
			if(tmp != null) {
				if(tmp.leftNode!=null) {
					q.offer(tmp.leftNode);
				}else {
					tmp.setLeftNode(new treeNode(data));
					return root;
				}
				
				if(tmp.rightNode!=null) {
					q.offer(tmp.rightNode);
				}else {
					tmp.setRightNode(new treeNode(data));
					return root;
				}
			}
		}
		return root;
		
	}
	
	static int countElement(treeNode root) {
		int count = 0;
		if (root == null)
			return count;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			treeNode tmp =q.poll();
			count++;
			if(tmp != null) {
				if(tmp.leftNode!=null) 
					q.offer(tmp.leftNode);
				if(tmp.rightNode!=null) 
					q.offer(tmp.rightNode);
			}
		}
		return count;
	}
	
	static int maxTreeDepth(treeNode root) {
		if(root == null)
			return 0;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		q.offer(null);
		int count=1;
		while(!q.isEmpty()) {
			treeNode tmp =q.poll();
			if(tmp != null) {
				if(tmp.leftNode == null || tmp.rightNode == null)
					continue;
				if(tmp.leftNode!=null) 
					q.offer(tmp.leftNode);
				if(tmp.rightNode!=null) 
					q.offer(tmp.rightNode);
			}else {
				if(!q.isEmpty()) {
					count++;
					q.offer(null);
				}
			}
		}
		return count;
	}
	
	static int minTreeDepth(treeNode root) {
		if(root == null)
			return 0;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		q.offer(null);
		int count=1;
		while(!q.isEmpty()) {
			treeNode tmp =q.poll();
			if(tmp != null) {
				if(tmp.leftNode == null || tmp.rightNode == null)
					return count;
				if(tmp.leftNode!=null) 
					q.offer(tmp.leftNode);
				if(tmp.rightNode!=null) 
					q.offer(tmp.rightNode);
			}else {
				if(!q.isEmpty()) {
					count++;
					q.offer(null);
				}
			}
		}
		return count;
	}
	
	
	static treeNode deepestNode(treeNode root) {
		treeNode tmp =null;
		if (root == null)
			return null;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			tmp =q.poll();
			if(tmp != null) {
				if(tmp.leftNode!=null) 
					q.offer(tmp.leftNode);
				if(tmp.rightNode!=null) 
					q.offer(tmp.rightNode);
			}
		}
		return tmp;
	}
	
	static int maxSum(treeNode root) {
		if (root==null)
			return 0;
		int maxSum= 0;
		int currentSum=0;
		int level = 1;
		int taretLevel=1;
		Queue<treeNode> q=new LinkedList<treeNode>();
		q.offer(root);
		q.offer(null);
		while(!q.isEmpty()) {
			treeNode tmp = q.poll();
			if(tmp!=null) {
				currentSum=currentSum+tmp.data;
				if(tmp.leftNode!=null) 
					q.offer(tmp.leftNode);
				if(tmp.rightNode!=null) 
					q.offer(tmp.rightNode);
			}else {
				if(currentSum>maxSum) {
					maxSum=currentSum;
					taretLevel = level;
				}
				currentSum=0;
				if(!q.isEmpty()) {
					q.offer(null);
					level++;
				}
			}
		}
		System.out.println("Max: "+maxSum);
		return taretLevel;
	}
static void printPath(treeNode root) {
		int[]path = new int[256];
		printPaths(root,path,0);
		pathSum(root, path, 0);
	}
	
	static void printPaths(treeNode root,int[]path,int pathLen) {
		if(root==null)
			return;
		path[pathLen]=root.data;
		pathLen++;
		if(root.getLeftNode() == null && root.getRightNode() == null) {
			printArray(path,pathLen);
		}else {
			printPaths(root.getLeftNode(), path, pathLen);
			printPaths(root.getRightNode(), path, pathLen);
		}
	}
	
	static void printArray(int[] arr,int len) {
		for(int i=0;i<len;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	static void sumArray(int[] arr,int len) {
		int sum=0;
		for(int i=0;i<len;i++) {
			sum=sum+arr[i];
		}
		System.out.println(sum);
	}
	
	
	
	static void pathSum(treeNode root,int[]path,int pathLen) {
		if(root==null)
			return;
		path[pathLen]=root.data;
		pathLen++;
		if(root.getLeftNode() == null && root.getRightNode() == null) {
			sumArray(path,pathLen);
		}else {
			pathSum(root.getLeftNode(), path, pathLen);
			pathSum(root.getRightNode(), path, pathLen);
		}
		
	}
	
	
	static boolean hasPathSum(treeNode root,int sum) {
		if(root==null)
			return false;
		if(root.leftNode==null && root.rightNode==null && root.data==sum)
			return true;
		else
			return hasPathSum(root.leftNode, sum-root.leftNode.data)|| hasPathSum(root.rightNode, sum-root.rightNode.data);
	}
	
	static treeNode mirrorBinaryTree(treeNode root) {
		treeNode tmp=null;
		if(root != null) {
			mirrorBinaryTree(root.leftNode);
			mirrorBinaryTree(root.rightNode);
			tmp=root.leftNode;
			root.leftNode=root.rightNode;
			root.rightNode=tmp;
		}
		
		return root;
	}
	
	static treeNode btFromInorderPreorder(int[]pre,int[]in) {
		if(pre.length == 0 || in.length == 0 || in.length!=pre.length)
			return null;
		return buildBTFromInorderPreorder(pre,in,0,in.length-1);
	}
	
	static treeNode buildBTFromInorderPreorder(int[]pre,int[] in,int inStart,int inLend) {
		int preStart=0;
		if(inStart>inLend)
			return null;
		treeNode curr = new treeNode(pre[preStart]);
		preStart++;
		
		if(inStart==inLend)
			return curr;
		
		int inOrderIndex=0;
		
		for(int i=inStart;i<=inLend;i++) {
			if(in[i]==curr.data) {
				inOrderIndex=i;
			break;
			}
		}
		
		curr.leftNode=buildBTFromInorderPreorder(pre, in, inStart, inOrderIndex-1);
		curr.rightNode=buildBTFromInorderPreorder(pre, in, inOrderIndex+1, inLend);
		
		return curr;
	}
	
	 treeNode builtBT(int[]post,int[]in) {
		return buildBTFromInorderPostorder(post, in, 0, in.length-1, 0,post.length-1);
	}
	
	static treeNode buildBTFromInorderPostorder(int[] in, int[] post,int inStart,int inEnd,int pStart,int pEnd) {
		if(pStart>pEnd || inStart>inEnd)
	        return null;
			int val=post[pEnd];
	        treeNode curr=new treeNode(val);
	    
	        if(inStart==inEnd)
	            return curr;
	    
	        int offSet=getInorderIndex(in,inStart,inEnd,val);
			
			curr.leftNode=buildBTFromInorderPostorder(in,post, inStart, (offSet-1),pStart,pStart+offSet-inStart-1);
			curr.rightNode=buildBTFromInorderPostorder(in,post, (offSet+1), inEnd,pStart+offSet-inStart,pEnd-1);
			
			return curr;
		
	}
	
	public static int getInorderIndex(int[] inOrder, int start, int end, int data) {
		for (int i = start; i <= end; i++) {
			if (inOrder[i] == data) {
				return i;
			}
		}
		return -1;
    }
	
	static ArrayList<ArrayList<Integer>>zigZagTraversal(treeNode root){
		if(root==null)
			return null;
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		Queue<treeNode> q=new LinkedList<treeNode>();
		ArrayList<Integer> curr=new ArrayList<>();
		boolean leftToRight=true;
		q.offer(root);
		q.offer(null);
		while(!q.isEmpty()) {
			treeNode tmp=q.poll();
			if(tmp !=null) {
				curr.add(tmp.data);
				if(tmp.leftNode!=null)
					q.offer(tmp.leftNode);
				if(tmp.rightNode!=null)
					q.offer(tmp.rightNode);
			}else {
			if(leftToRight) {
				ArrayList<Integer> c_curr=new ArrayList<Integer>(curr);
				res.add(c_curr);
				curr.clear();
			}else {
				Stack<Integer> s=new Stack<>();
				s.addAll(curr);
				ArrayList<Integer> c_curr=new ArrayList<Integer>();
				while(!s.isEmpty()) {
					c_curr.add(s.pop());
				}
				res.add(c_curr);
				curr.clear();
				}
				if(!q.isEmpty()) {
					q.offer(null);
					leftToRight=!leftToRight;
					}
				}	
			}
		return res;
	}
	
	//here we are passing array like ILILL where I means internal node and L means leaf.Any node can have 0 or 2 children.In the
	//arguments 'i' is the starting index of the array.We can consider it as 1 may be.
	
	static treeNode buildTreeNodeFromPreOrder(char[] A,int i) {
		if(A == null || A.length == i)
			return null;
		treeNode root = new treeNode(A[i]);
		if(A[i] == 'L')
			return root;
		i++;
		root.setLeftNode(buildTreeNodeFromPreOrder(A, i));
		i++;
		root.setRightNode(buildTreeNodeFromPreOrder(A, i));
		return root;
	}
	
	static void dfs(treeNode root,int sum,ArrayList<Integer>pathSum) {
		if (root==null)
			return;
		sum=sum+root.data;
		if(root.leftNode == null && root.rightNode==null)
		{
			pathSum.add(sum);
			return;
		}
		
		dfs(root.leftNode,sum,pathSum);
		dfs(root.rightNode,sum,pathSum);
		
	}
	
	static void findPathSum(treeNode root) {
	 ArrayList<Integer> pathSum = new ArrayList<Integer>();
	 dfs(root,0,pathSum);
	 for(int i:pathSum) {
		 System.out.println(i);
	 }
 	}
 
 	static int maxAbsoluteDiffLevel(treeNode root) {
	 int maxDiff = Integer.MIN_VALUE;
	 int prevSum=0;
	 int currSum=0;
	 Queue<treeNode> q=new LinkedList<treeNode>();
	 q.offer(root);
	 q.offer(null);
	 while(!q.isEmpty()) {
		 treeNode tmp =q.poll();
		 if(tmp!=null) {
			 currSum=currSum+tmp.data;
			 if(tmp.leftNode != null)
				 q.offer(tmp.leftNode);
			 if(tmp.rightNode != null)
				 q.offer(tmp.rightNode);
		 }else {
			 if(maxDiff<Math.subtractExact(currSum, prevSum))
				 maxDiff=Math.subtractExact(currSum, prevSum);
			 if(!q.isEmpty()) {
				 prevSum=currSum;
				 currSum=0;
				 q.offer(null);
			 }
		 }
	 }
	 return maxDiff;
 }
 	
 	static ArrayList<Integer> eulerTour(treeNode root,ArrayList<Integer> euler) {
 		if(root==null)
 			return euler;
 		euler.add(root.data);
 		if(root.leftNode!=null) {
 			euler=eulerTour(root.leftNode, euler);
 			euler.add(root.data);
 		}
 		
 		if(root.rightNode != null) {
 			euler = eulerTour(root.rightNode, euler);
 			euler.add(root.data);
 		}
 		 return euler;
 		
 	}
 	
 	static int findLCA(treeNode root,treeNode n1,treeNode n2) {
 		if(root == null)
 			return 0;
 		List<Integer> path1=new ArrayList<Integer>();
 		List<Integer> path2=new ArrayList<Integer>();
 		if(findPathOfNode(root, n1, path1)&&findPathOfNode(root, n2, path2)) {
 			int i;
 			for (i=0;i<path1.size() && i<path2.size();i++) {
 				if(!path1.get(i).equals(path2.get(i)))
 					break;
 			}
 			return path1.get(i-1);
 		}
 		return 0;
 		
 		
 	}
 	
 	static boolean findPathOfNode(treeNode root,treeNode n1,List<Integer> path) {
 		if (root==null)
 			return false;
 		path.add(root.data);
 		if(root.data==n1.data)
 			return true;
 		if(root.leftNode!=null && findPathOfNode(root.leftNode, n1, path))
			return true;
		if(root.rightNode!=null && findPathOfNode(root.rightNode, n1, path))
			return true;
		path.remove(path.size()-1);
		return false;
 	}
 	
 	static int findMaxWidth(treeNode root) {
 		if(root == null)
 			return 0;
 		int currWidth = 0;
 		int width = 0;
 		Queue<treeNode> q = new LinkedList<treeNode>();
 		q.offer(root);
 		q.offer(null);
 		while(!q.isEmpty()) {
 			treeNode tmp = q.poll();
 			if(tmp !=null) {
 				currWidth++;
 				if(tmp.leftNode != null) {
 					q.offer(tmp.leftNode);
 				}if(tmp.rightNode != null) {
 					q.offer(tmp.rightNode);
 				}
 			}else {
 				if(width<currWidth) {
						width = currWidth;
						currWidth=0;
					}
 				if(!q.isEmpty()) {
 					q.offer(null);
 				}
 			}
 			
 		}
 		return width;
 	}
 	
 	public static boolean isSameTree(treeNode p, treeNode q) {
 		if((p == null) || (q==null))
 			return false;
        ArrayList<Integer> tree1=traverse(p);
        ArrayList<Integer> tree2=traverse(q);
        return tree1.equals(tree2);
    }
 	
 	static ArrayList<Integer> traverse(treeNode root){
 		ArrayList<Integer> res = new ArrayList<Integer>();
 		Stack<treeNode> st = new Stack<treeNode>();
 		st.add(root);
 		while(!st.isEmpty()) {
 			treeNode tmp = st.pop();
 			if(tmp == null)
 				res.add(null);
 			else {
 				res.add(tmp.data);
 				if(tmp.leftNode != null || tmp.rightNode != null) {
 				st.add(tmp.rightNode);
 				st.add(tmp.leftNode);
 				}
 			}
 		}
 		return res;
 	}
 	
// 	Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
//
// 			For example:
// 			Given binary tree [3,9,20,null,null,15,7],
// 			    3
// 			   / \
// 			  9  20
// 			    /  \
// 			   15   7
// 			return its bottom-up level order traversal as:
// 			[
// 			  [15,7],
// 			  [9,20],
// 			  [3]
// 			]
 	
 	public List<List<Integer>> levelOrderBottom(treeNode root) {
 		List<List<Integer>> res = new ArrayList<List<Integer>>();
 		Queue<treeNode> q = new LinkedList<treeNode>();
 		Stack<List<Integer>> st = new Stack<List<Integer>>();
 		q.offer(root);
 		while(!q.isEmpty()) {
 			int len = q.size();
 	 		List<Integer> level = new ArrayList<Integer>();
 	 		for (int i=0;i<len;i++) {
 	 			treeNode tmp=q.poll();
 	 				level.add(tmp.data);
 	 				if(tmp.leftNode != null)
 	 					q.offer(tmp.leftNode);
 	 				if(tmp.rightNode!=null)
 	 					q.offer(tmp.rightNode);
 			}
 	 		st.push(level);
 		}
 		while(!st.isEmpty())
 			res.add(st.pop());
 		return res;
    }
 	
 	public void levelOrderBottomUpStackQ(treeNode root) {
 		Queue<treeNode> q = new LinkedList<treeNode>();
 		Stack<Integer> st = new Stack<Integer>();
 		q.offer(root);
 		while(!q.isEmpty()) {
 			treeNode tmp=q.poll();
 			if(tmp!=null) {
 				st.add(tmp.data);
 				if(tmp.rightNode!=null)
 					q.offer(tmp.rightNode);
 				if(tmp.leftNode != null)
 					q.offer(tmp.leftNode);
 			}
 		}
 		while(!st.isEmpty()) {
 			System.out.print(st.pop()+" ");
 		}
    }
}



		





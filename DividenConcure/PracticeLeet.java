package DividenConcure;

import java.util.PriorityQueue;

public class PracticeLeet {
	
//	Given an integer array nums where the elements are sorted in ascending order, convert it to a 
//	height-balanced
//	 binary search tree.
//
//	 
//
//	Example 1:
//
//
//	Input: nums = [-10,-3,0,5,9]
//	Output: [0,-3,9,-10,null,5]
//	Explanation: [0,-10,5,null,-3,null,9] is also accepted:
//
//	Example 2:
//
//
//	Input: nums = [1,3]
//	Output: [3,1]
//	Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
	 
	/**
	 * Definition for a binary tree node.
	 * */
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
	 
	  public TreeNode sortedArrayToBST(int[] nums) {
	        return createBSTUtil(nums, 0, nums.length-1);
	  }

	  public TreeNode createBSTUtil(int[] nums,int start,int end) {
	        if(start>end)
	        	return null;
	        int mid = start+(end-start)/2;
	        TreeNode root = new TreeNode(nums[mid]);
	        root.left=createBSTUtil(nums, start, mid-1);
	        root.right=createBSTUtil(nums, mid+1, end);
	        return root;
	    }
	  
//	  Given the head of a linked list, return the list after sorting it in ascending order.
//
//			  
//
//			  Example 1:
//
//
//			  Input: head = [4,2,1,3]
//			  Output: [1,2,3,4]
//			  Example 2:
//
//
//			  Input: head = [-1,5,3,4,0]
//			  Output: [-1,0,3,4,5]
//			  Example 3:
//
//			  Input: head = []
//			  Output: []
	  
	  public class ListNode {
		       int val;
		       ListNode next;
		       ListNode() {}
		       ListNode(int val) { this.val = val; }
		       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		   }
	  
	  public ListNode sortList(ListNode head) {
	        if(head==null||head.next==null) {
	        	return head;
	        }
	        ListNode mid = findMid(head);
	        ListNode head2 = mid.next;
	        mid.next=null;
	        ListNode t1=sortList(head);
	        ListNode t2=sortList(head2);
	        return merge(t1, t2);
	    }
	  	
	  	public ListNode findMid(ListNode head) {
	        ListNode slow = head;
	        ListNode fast=head;
	        while(fast.next!=null && fast.next.next!=null) {
	        	slow=slow.next;
	        	fast=fast.next.next;
	        }
	        return slow;
	    }
	  	
	  	public ListNode merge(ListNode head1,ListNode head2) {
	        ListNode head;
	        if(head1==null)
	        	return head2;
	        if(head2==null)
	        	return head1;
	        if(head1.val<head2.val) {
	        	head=head1;
	        	head1=head1.next;
	        }else {
	        	head=head2;
	        	head2=head2.next;
	        }
	        ListNode t =head;
	        while(head1!=null && head2!=null) {
	        	if(head1.val>head2.val) {
	        		t.next=head2;
	        		head2=head2.next;
	        		t=t.next;
	        	}else {
	        		t.next=head1;
	        		head1=head1.next;
	        		t=t.next;
	        	}
	        }
	        if(head2==null)
	        	t.next=head1;
	        if(head1==null)
	        	t.next=head2;
	        return head;
	    }
	  	
//	  	Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
//
//	  	Return the root of the Quad-Tree representing grid.
//
//	  	A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
//
//	  	val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
//	  	isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
//	  	class Node {
//	  	    public boolean val;
//	  	    public boolean isLeaf;
//	  	    public Node topLeft;
//	  	    public Node topRight;
//	  	    public Node bottomLeft;
//	  	    public Node bottomRight;
//	  	}
//	  	We can construct a Quad-Tree from a two-dimensional area using the following steps:
//
//	  	If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
//	  	If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
//	  	Recurse for each of the children with the proper sub-grid.
//
//	  	If you want to know more about the Quad-Tree, you can refer to the wiki.
//
//	  	Quad-Tree format:
//
//	  	You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the 
//	  	serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
//
//	  	It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
//
//	  	If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
//
//	  	 
//
//	  	Example 1:
//
//
//	  	Input: grid = [[0,1],[1,0]]
//	  	Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
//	  	Explanation: The explanation of this example is shown below:
//	  	Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.
//
//	  	Example 2:
//
//
//
//	  	Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
//	  	Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
//	  	Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
//	  	The topLeft, bottomLeft and bottomRight each has the same value.
//	  	The topRight have different values so we divide it into 4 sub-grids where each has the same value.
//	  	Explanation is shown in the photo below:
	  	//https://leetcode.com/problems/construct-quad-tree/solutions/3235803/java-code-beats-100/?envType=study-plan-v2&envId=top-interview-150
	  	
	  	class Node {
	  	    public boolean val;
	  	    public boolean isLeaf;
	  	    public Node topLeft;
	  	    public Node topRight;
	  	    public Node bottomLeft;
	  	    public Node bottomRight;

	  	    
	  	    public Node() {
	  	        this.val = false;
	  	        this.isLeaf = false;
	  	        this.topLeft = null;
	  	        this.topRight = null;
	  	        this.bottomLeft = null;
	  	        this.bottomRight = null;
	  	    }
	  	    
	  	    public Node(boolean val, boolean isLeaf) {
	  	        this.val = val;
	  	        this.isLeaf = isLeaf;
	  	        this.topLeft = null;
	  	        this.topRight = null;
	  	        this.bottomLeft = null;
	  	        this.bottomRight = null;
	  	    }
	  	    
	  	    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
	  	        this.val = val;
	  	        this.isLeaf = isLeaf;
	  	        this.topLeft = topLeft;
	  	        this.topRight = topRight;
	  	        this.bottomLeft = bottomLeft;
	  	        this.bottomRight = bottomRight;
	  	    }
	  	};
	  	 
	  	public Node construct(int[][] grid) {
	        int m=grid.length,n=grid[0].length;
	        return check(grid, 0, m-1, 0, n-1);
	    }
	  	
	  	public Node check(int[][] grid,int rowStart,int rowEnd,int colStart,int colEnd) {
	        if(rowStart>rowEnd || colStart>colEnd)
	        	return null;
	        boolean isLeaf = true;
	        int val=grid[rowStart][colStart];
	        for(int i=rowStart;i<=rowEnd;i++) {
	        	for(int j=colStart;j<=colEnd;j++) {
	        		if(grid[i][j]!=val) {
	        			isLeaf=false;
	        			break;
	        		}
	        	}
	        	if(!isLeaf)
	        		break;
	        }
	        if(isLeaf) {
	        	if(val==1)
	        		return new Node(true, isLeaf);
	        	else
	        		return new Node(false, isLeaf);
	        }
	        int midRow = (rowEnd+rowStart)/2;
	        int midCol= (colEnd+colStart)/2;
	        Node topLeft = check(grid, rowStart, midRow, colStart, midCol);
	        Node topRight = check(grid,rowStart, midRow, midCol+1, colEnd);
	        Node bottomLeft = check(grid,  midRow+1, rowEnd, colStart, midCol);
	        Node bottomRight = check(grid, midRow+1, rowEnd, midCol+1, colEnd);
	        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
	    }
	  	
//	  	You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//	  	Merge all the linked-lists into one sorted linked-list and return it.
//
//	  	 
//
//	  	Example 1:
//
//	  	Input: lists = [[1,4,5],[1,3,4],[2,6]]
//	  	Output: [1,1,2,3,4,4,5,6]
//	  	Explanation: The linked-lists are:
//	  	[
//	  	  1->4->5,
//	  	  1->3->4,
//	  	  2->6
//	  	]
//	  	merging them into one sorted list:
//	  	1->1->2->3->4->4->5->6
//	  	Example 2:
//
//	  	Input: lists = []
//	  	Output: []
//	  	Example 3:
//
//	  	Input: lists = [[]]
//	  	Output: []
	  //	this code is giving TLE...
//	  	public ListNode mergeKLists(ListNode[] lists) {
//	        if(lists.length==0)
//	        	return null;
//	        ListNode pivot = lists[0];
//	        if(pivot==null)
//	        	return null;
//	        for(ListNode t1:lists) {
//	        	pivot=mergeListUtil(t1, pivot);
//	        }
//	        return pivot;
//	    }
//	  	
//	  	ListNode mergeListUtil(ListNode h1,ListNode h2) {
//	  		if(h1==null)
//	  			return h2;
//	  		if(h2==null)
//	  			return h1;
//	  		ListNode head;
//	  		if(h1.val<h2.val) {
//	  			head=h1;
//	  			h1=h1.next;
//	  		}else {
//	  			head=h2;
//	  			h2=h2.next;
//	  		}
//	  		ListNode t=head;
//	  		while(h1!=null &&h2!=null) {
//	  			if(h1.val<h2.val) {
//	  				t.next=h1;
//	  				h1=h1.next;
//	  				t=t.next;
//	  			}else {
//	  				t.next=h2;
//	  				h2=h2.next;
//	  				t=t.next;
//	  			}
//	  		}
//	  		if(h1!=null)
//	  			t.next=h1;
//	  		if(h2!=null)
//	  			t.next=h2;
//	  		return head;
//	  	}
	  	
	  	public ListNode mergeKLists(ListNode[] lists) {
	  		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	  		for(ListNode t1:lists) {
	  			while(t1!=null) {
	  				minHeap.add(t1.val);
	  				t1=t1.next;
	  			}
	  		}
	  		ListNode dummy=new ListNode(Integer.MIN_VALUE);
	  		ListNode head = dummy;
	  		while(!minHeap.isEmpty()) {
	  			head.next=new ListNode(minHeap.poll());
	  			head=head.next;
	  		}
	  		return dummy.next;
	  	}
}

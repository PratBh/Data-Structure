package linkedList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode {
	
	class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		     }
	}
//	Given head, the head of a linked list, determine if the linked list has a cycle in it.
//
//	There is a cycle in a linked list if there is some node in the list that can be reached again by
//	continuously following the next pointer. Internally, pos is used to denote the index of the node that 
//	tail's next pointer is connected to. Note that pos is not passed as a parameter.
//
//	Return true if there is a cycle in the linked list. Otherwise, return false.
//
//	 
//
//	Example 1:
//
//
//	Input: head = [3,2,0,-4], pos = 1
//	Output: true
//	Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
	
	public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)
        	return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null) {
        	slow=slow.next;
        	fast=fast.next.next;
        	if(slow==fast)
        		return true;
        }
        return false;
    }
	
//	Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
//
//			There is a cycle in a linked list if there is some node in the list that can be reached again by continuously 
//			following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer 
//			is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
//
//			Do not modify the linked list.
//
//			 
//
//			Example 1:
//
//
//			Input: head = [3,2,0,-4], pos = 1
//			Output: tail connects to node index 1
//			Explanation: There is a cycle in the linked list, where tail connects to the second node.
	
	public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean loopExist = false;
        while(fast!=null && fast.next!=null) {
        	slow=slow.next;
        	fast=fast.next.next;
        	if(slow==fast) {
        		loopExist=true;
        		break;
        	}
        }
        if(loopExist) {
        	slow=head;
        	while(slow!=fast) {
        		slow=slow.next;
        		fast=fast.next;
        	}
        	return fast;
        }else
        	return null;
    }
	
	
//	You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, 
//	and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//
//			You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//			 
//
//			Example 1:
//
//
//			Input: l1 = [2,4,3], l2 = [5,6,4]
//			Output: [7,0,8]
//			Explanation: 342 + 465 = 807.
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int advance = 0,sum=0;
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while(l1!=null && l2!=null) {
        	int res = l1.val+l2.val+advance;
        	advance = res/10;
        	sum=res%10;
        	ListNode temp = new ListNode(sum);
        	curr.next = temp;
        	curr = curr.next;
        	l1 = l1.next;
        	l2=l2.next;
        }
        if(l1!=null) {
        	if(advance!=0)
        		curr.next = addTwoNumbers(l1, new ListNode(advance));
        	else
        		curr.next = l1;
        }else if(l2!=null) {
        	if(advance!=0)
        		curr.next = addTwoNumbers(l2, new ListNode(advance));
        	else
        		curr.next = l2;
        }else if(advance!=0)
        	curr.next = new ListNode(advance);
        return head.next;
    }
	
//	
//	Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//
//	There is only one repeated number in nums, return this repeated number.
//
//	You must solve the problem without modifying the array nums and uses only constant extra space.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,3,4,2,2]
//	Output: 2
//	Example 2:
//
//	Input: nums = [3,1,3,4,2]
//	Output: 3
//	
	public int findDuplicate(int[] nums) {
        int slow =0,fast=0;
        
        do{
        	slow = nums[slow];
        	fast = nums[nums[fast]];
        }while(slow!=fast) ;
        
        slow = 0;
        while(slow!=fast) {
        	slow = nums[slow];
        	fast = nums[fast];
        }
        return slow;
    }
	
//	A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
//
//	Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value 
//	of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that 
//	the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
//
//	For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
//
//	Return the head of the copied linked list.
//
//	The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
//
//	val: an integer representing Node.val
//	random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
//	Your code will only be given the head of the original linked list.
	
	class RandomNode {
	    int val;
	    RandomNode next;
	    RandomNode random;

	    public RandomNode(int val) {
	        this.val = val;
	        this.next = null;
	        this.random = null;
	    }
	}
	
	 public RandomNode copyRandomList(RandomNode head) {
		 RandomNode x = head,y;
	        Map<RandomNode, RandomNode> mp = new HashMap<LeetCode.RandomNode, LeetCode.RandomNode>();
	        while(x!=null) {
	        	y= new RandomNode(x.val);
	        	y.next=null;
	        	y.random = null;
	        	mp.put(x,y);
	        	x=x.next;
	        }
	        x=head;
	        while(x!=null) {
	        	y=mp.get(x);
	        	y.next=mp.get(x.next);
	        	y.random=mp.get(x.random);
	        	x=x.next;
	        }
	        return mp.get(head);
	 }
	 
//	 Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position 
//			 left to position right, and return the reversed list.
//
//			 
//
//			 Example 1:
//
//
//			 Input: head = [1,2,3,4,5], left = 2, right = 4
//			 Output: [1,4,3,2,5]
//			 Example 2:
//
//			 Input: head = [5], left = 1, right = 1
//			 Output: [5]
//https://leetcode.com/problems/reverse-linked-list-ii/solutions/4012514/video-solution-explanation-with-drawings-in-depth-java-c/?envType=study-plan-v2&envId=top-interview-150
	 public ListNode reverseBetween(ListNode head, int left, int right) {
		 ListNode  dummyHead = new ListNode(0),  rangeHead = null, 
				 prevNode = dummyHead,  currNode = head,  rangeTail = null,  rangeHeadPrev = prevNode; 
		 dummyHead.next=head;
		 int nodeNum=1;
		 for(;nodeNum<left; nodeNum++) {
			 prevNode=currNode;
			 currNode=currNode.next;
		 }
		 rangeHeadPrev = prevNode;
		 for(;nodeNum<=right;nodeNum++) {
			 ListNode next = currNode.next;
			 if(nodeNum==left)
				 rangeHead = currNode;
			 if(nodeNum==right)
				 rangeTail = currNode;
			 currNode.next=(nodeNum!=left)?prevNode:null;
			 prevNode=currNode;
			 currNode = next;
		 }
		 rangeHead.next=currNode;
		 rangeHeadPrev.next=rangeTail;
		 return dummyHead.next;
	    }
	 
	 
//	 Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
//
//			 k is a positive integer and is less than or equal to the length of the linked list. 
//			 If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
//
//			 You may not alter the values in the list's nodes, only nodes themselves may be changed.
//
//			  
//
//			 Example 1:
//
//
//			 Input: head = [1,2,3,4,5], k = 2
//			 Output: [2,1,4,3,5]
//			 Example 2:
//
//
//			 Input: head = [1,2,3,4,5], k = 3
//			 Output: [3,2,1,4,5]
	 
	 public ListNode reverseKGroup(ListNode head, int k) {
	       ListNode curr =head;
	       int count =0;
	       while(curr!=null && count!=k) {
	    	   curr=curr.next;
	    	   count++;
	       }
	       if(count==k) {
	    	   curr = reverseKGroup(curr, k);
	    	   while(count-->0) {
	    		   ListNode tmp = head.next;
	    		   head.next=curr;
	    		   curr=head;
	    		   head=tmp;
	    	   }
	    	   head=curr;
	       }
	       return head;
	    }
	 
//	 Given the head of a linked list, remove the nth node from the end of the list and return its head.
//
//			 
//
//			 Example 1:
//
//
//			 Input: head = [1,2,3,4,5], n = 2
//			 Output: [1,2,3,5]
//			 Example 2:
//
//			 Input: head = [1], n = 1
//			 Output: []
//			 Example 3:
//
//			 Input: head = [1,2], n = 1
//			 Output: [1]
	 	//https://leetcode.com/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&envId=top-interview-150
	 public ListNode removeNthFromEnd(ListNode head, int n) {
	     int count =0;
	     ListNode slow = head,fast=head;
	     for(;count<n;count++) {
	    	 if(fast!=null)
	    		 fast=fast.next;
	     }
         if (fast == null) return head.next;
	     while(fast.next!=null) {
	    	 slow=slow.next;
	    	 fast=fast.next;
	     }
	    slow.next=slow.next.next;
	     return head;
	    }
	 
//	 Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
//
//	 
//
//	 Example 1:
//
//
//	 Input: head = [1,1,2]
//	 Output: [1,2]
//	 Example 2:
//
//
//	 Input: head = [1,1,1,2,3,3]
//	 Output: [1,2,3]
	 
	 public ListNode deleteDuplicates(ListNode head) {
		 if(head==null)
	            return head;
	    int k = head.val;
	    if(head.next==null)
	    	return head;
	    ListNode prev = head,curr=head.next;
	    while(curr!=null) {
	    	if(curr.val==k) {
	    		ListNode temp = curr.next;
	    		prev.next=curr.next;
	    		curr=temp;
	    		//curr=curr.next;
	    	}else {
	    		k=curr.val;
	    		prev=curr;
	    		curr=curr.next;
	    	}
	    }
	    return head;
	 }
	 
//	 Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers 
//	 from the original list. Return the linked list sorted as well.
//
//	 
//
//	 Example 1:
//
//
//	 Input: head = [1,2,3,3,4,4,5]
//	 Output: [1,2,5]
//	 Example 2:
//
//
//	 Input: head = [1,1,1,2,3]
//	 Output: [2,3]
	 //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/solutions/4005092/best-recursive-solution-for-java/?envType=study-plan-v2&envId=top-interview-150
	 ListNode duplicate =null;
	 public ListNode deleteDuplicatesTough(ListNode head) {
	       if(head==null)
	    	   return head;
	       if(duplicate!=null) {
	    	   if(duplicate.val==head.val)
	    		   return deleteDuplicates(head.next);
	    	   else {
	    		   duplicate=null;
	    		   head = deleteDuplicates(head);
	    	   }
	       }else if(head.next!=null && head.val==head.next.val) {
	    	   duplicate=head;
	    	   return deleteDuplicates(head.next.next);
	       }else {
	    	   duplicate=null;
    		   head.next = deleteDuplicates(head.next);
	       }
	       return head;
	 }
	 
//	 Given the head of a linked list, rotate the list to the right by k places.
//
//	 
//
//	 Example 1:
//
//
//	 Input: head = [1,2,3,4,5], k = 2
//	 Output: [4,5,1,2,3]
//	 Example 2:
//
//
//	 Input: head = [0,1,2], k = 4
//	 Output: [2,0,1]
	 public ListNode rotateRight(ListNode head, int k) {
	        if(head==null|| head.next==null)
	        	return head;
	        ListNode rotateStart = head,rotateEnd=head;
	        while(k>0) {
	        	rotateEnd = rotateEnd.next;
	        	if(rotateEnd==null)
	        		rotateEnd=head;
	        	k--;
	        }
	        if(rotateEnd==rotateStart)
	        	return head;
	        while(rotateEnd.next!=null) {
	        	rotateStart=rotateStart.next;
	        	rotateEnd=rotateEnd.next;
	        }
	        ListNode temp=rotateStart.next;
	        rotateEnd.next=head;
	        rotateStart.next=null;
	        return temp;
	   }
	 
//	 Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
//	 You should preserve the original relative order of the nodes in each of the two partitions.
//
//	  
//
//	 Example 1:
//
//
//	 Input: head = [1,4,3,2,5,2], x = 3
//	 Output: [1,2,2,4,3,5]
//	 Example 2:
//
//	 Input: head = [2,1], x = 2
//	 Output: [1,2]

	 public ListNode partition(ListNode head, int x) {
	        if(head==null||head.next==null)
	        	return head;
	        ListNode root=new ListNode(0),newHead = new ListNode(x),pivotNext=newHead,rootNext=root;
	        while(head!=null) {

        		ListNode next = head.next;
	        	if(head.val<x) {
	        		rootNext.next=head;
	        		rootNext=head;
	        	}else {
	        		pivotNext.next=head;
	        		pivotNext=head;
	        		pivotNext.next=null;
	        	}
	        	head=next;
	        }
	        rootNext.next=newHead.next;
	        return root.next;
	        
	    }
	 
//	 Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
//
//	 Implement the LRUCache class:
//
//	 LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//	 int get(int key) Return the value of the key if the key exists, otherwise return -1.
//	 void put(int key, int value) Update the value of the key if the key exists. Otherwise, 
//	 add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
//	 The functions get and put must each run in O(1) average time complexity.
//
//	  
//
//	 Example 1:
//
//	 Input
//	 ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//	 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//	 Output
//	 [null, null, null, 1, null, -1, null, -1, 3, 4]
//
//	 Explanation
//	 LRUCache lRUCache = new LRUCache(2);
//	 lRUCache.put(1, 1); // cache is {1=1}
//	 lRUCache.put(2, 2); // cache is {1=1, 2=2}
//	 lRUCache.get(1);    // return 1
//	 lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//	 lRUCache.get(2);    // returns -1 (not found)
//	 lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//	 lRUCache.get(1);    // return -1 (not found)
//	 lRUCache.get(3);    // return 3
//	 lRUCache.get(4);    // return 4
	 //https://leetcode.com/problems/lru-cache/solutions/3780778/100-fast-very-easily-line-by-line-explained-c-java-python/?envType=study-plan-v2&envId=top-interview-150
	 class Node {
		    public int key;
		    public int val;
		    public Node next;
		    public Node prev;

		    public Node(int key, int val) {
		        this.key = key;
		        this.val = val;
		        next = null;
		        prev = null;
		    }
		}

		class LRUCache {
		    private Map<Integer, Node> m;
		    private Node head;
		    private Node tail;
		    private int size;

		    public LRUCache(int capacity) {
		        size = capacity;
		        m = new HashMap<>();
		        head = new Node(-1, -1);
		        tail = new Node(-1, -1);
		        head.next = tail;
		        tail.prev = head;
		    }

		    private void deleteNode(Node p) {
		        Node pre = p.prev;
		        Node nex = p.next;
		        pre.next = nex;
		        nex.prev = pre;
		    }

		    private void addNode(Node newnode) {
		        Node temp = head.next;
		        head.next = newnode;
		        newnode.prev = head;
		        newnode.next = temp;
		        temp.prev = newnode;
		    }

		    public int get(int key) {
		        if (!m.containsKey(key))
		            return -1;

		        Node p = m.get(key);
		        deleteNode(p);
		        addNode(p);
		        m.put(key, head.next);
		        return head.next.val;
		    }

		    public void put(int key, int value) {
		        if (m.containsKey(key)) {
		            Node c = m.get(key);
		            deleteNode(c);
		            c.val = value;
		            addNode(c);
		            m.put(key, head.next);
		        } else {
		            if (m.size() == size) {
		                Node prev = tail.prev;
		                deleteNode(prev);
		                Node l = new Node(key, value);
		                addNode(l);
		                m.remove(prev.key);
		                m.put(key, head.next);
		            } else {
		                Node l = new Node(key, value);
		                addNode(l);
		                m.put(key, head.next);
		            }
		        }
		    }
		}

}

package Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LeetCode {
//	Given an integer array nums and an integer k, return the kth largest element in the array.
//
//			Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//			Can you solve it without sorting?
//
//			 
//
//			Example 1:
//
//			Input: nums = [3,2,1,5,6,4], k = 2
//			Output: 5
//			Example 2:
//
//			Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
//			Output: 4
//			 
//
//			Constraints:
//
//			1 <= k <= nums.length <= 105
//			-104 <= nums[i] <= 104
	
	public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq =new PriorityQueue<Integer>();
        for(int n:nums) {
        	pq.add(n);
        	if(pq.size()>k)
        		pq.poll();
        }
        return pq.peek();
    }
	
//	Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on 
//	some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before
//	the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
//
//	You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
//
//	Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
//
//	Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
//
//	The answer is guaranteed to fit in a 32-bit signed integer.
//
//	 
//
//	Example 1:
//
//	Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//	Output: 4
//	Explanation: Since your initial capital is 0, you can only start the project indexed 0.
//	After finishing it you will obtain profit 1 and your capital becomes 1.
//	With capital 1, you can either start the project indexed 1 or the project indexed 2.
//	Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
//	Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
//	Example 2:
//
//	Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//	Output: 6
	//https://leetcode.com/problems/ipo/solutions/3219987/day-54-c-priority-queue-easiest-beginner-friendly-sol/?envType=study-plan-v2&envId=top-interview-150
	public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n=profits.length;
        int[][]projects=new int[n][2];
        for(int i=0;i<n;i++) {
        	projects[i][0]=capital[i];
        	projects[i][1]=profits[i];
        }
        Arrays.sort(projects,(a, b) -> Integer.compare(a[0],b[0]));
        int i=0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        while(k>0) {
        	while(i<n && projects[i][0]<=w) {
        		pq.add(projects[i][1]);
        		i++;
        	}
        	if(pq.isEmpty())
        		break;
        	w+=pq.poll();
        	k--;
        }
        return w;
    }
//	You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
//
//	Define a pair (u, v) which consists of one element from the first array and one element from the second array.
//
//	Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
//
//	 
//
//	Example 1:
//
//	Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//	Output: [[1,2],[1,4],[1,6]]
//	Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//	Example 2:
//
//	Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//	Output: [[1,1],[1,1]]
//	Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//	Example 3:
//
//	Input: nums1 = [1,2], nums2 = [3], k = 3
//	Output: [[1,3],[2,3]]
//	Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
	
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
       List<List<Integer>> ans =new ArrayList<List<Integer>>();
       int l1=nums1.length,l2=nums2.length;
       PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.sum-b.sum);
       for(int i=0;i<l1;i++) {
    	 Node nd = new Node(nums1[i]+nums2[0],nums1[i],nums2[0],0);
    	 pq.add(nd);
       }
       while(!pq.isEmpty() && k>0) {
    	   List<Integer> list=new ArrayList<Integer>();
    	   Node one = pq.poll();
    	   list.add(one.a);
    	   list.add(one.b);
    	   ans.add(list);
    	   if(one.index+1<l2)
    		   pq.add(new Node(one.a+nums2[one.index+1], one.a, nums2[one.index+1],one.index+1 ));
    	   k--;
       }
       return ans;
    }
	class Node{
		int sum;
		int a;
		int b;
		int index;
		public Node(int sum, int a, int b, int index) {
			super();
			this.sum = sum;
			this.a = a;
			this.b = b;
			this.index = index;
		}
	}
//	The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, 
//	and the median is the mean of the two middle values.
//
//	For example, for arr = [2,3,4], the median is 3.
//	For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
//	Implement the MedianFinder class:
//
//	MedianFinder() initializes the MedianFinder object.
//	void addNum(int num) adds the integer num from the data stream to the data structure.
//	double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
//	 
//
//	Example 1:
//
//	Input
//	["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//	[[], [1], [2], [], [3], []]
//	Output
//	[null, null, null, 1.5, null, 2.0]
//
//	Explanation
//	MedianFinder medianFinder = new MedianFinder();
//	medianFinder.addNum(1);    // arr = [1]
//	medianFinder.addNum(2);    // arr = [1, 2]
//	medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
//	medianFinder.addNum(3);    // arr[1, 2, 3]
//	medianFinder.findMedian(); // return 2.0
	//https://leetcode.com/problems/find-median-from-data-stream/solutions/1330646/c-java-python-minheap-maxheap-solution-picture-explain-clean-concise/?envType=study-plan-v2&envId=top-interview-150 
	class MedianFinder {
		PriorityQueue<Integer>minheap;
		PriorityQueue<Integer>maxheap;

	    public MedianFinder() {
	        this.minheap=new PriorityQueue<Integer>();
	        this.maxheap=new PriorityQueue<Integer>(Collections.reverseOrder());
	    }
	    
	    public void addNum(int num) {
	        maxheap.add(num);
	        minheap.add(maxheap.poll());
	        if(minheap.size()>maxheap.size())
	        	maxheap.add(minheap.poll());
	    }
	    
	    public double findMedian() {
	        if(minheap.size()==maxheap.size())
	        	return (minheap.peek()+maxheap.peek())/2;
	        return maxheap.peek();
	    }
	}
}

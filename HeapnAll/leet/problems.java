package leet;

import java.util.Comparator;
import java.util.PriorityQueue;

public class problems {
	
//	Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
//	So the median is the mean of the two middle value.
//
//	For example,
//	[2,3,4], the median is 3
//
//	[2,3], the median is (2 + 3) / 2 = 2.5
//
//	Design a data structure that supports the following two operations:
//
//	void addNum(int num) - Add a integer number from the data stream to the data structure.
//	double findMedian() - Return the median of all elements so far.
//	 
//
//	Example:
//
//	addNum(1)
//	addNum(2)
//	findMedian() -> 1.5
//	addNum(3) 
//	findMedian() -> 2
	class MedianFinder {
		
		PriorityQueue<Integer> minHeap=null;
		PriorityQueue<Integer> maxHeap=null;

	    /** initialize your data structure here. */
	    public MedianFinder() {
	        minHeap=new PriorityQueue<Integer>();
	        maxHeap=new PriorityQueue<Integer>(Comparator.reverseOrder());
	    }
	    
	    public void addNum(int num) {
	        minHeap.add(num);
	        maxHeap.add(minHeap.poll());
	        
	        if(minHeap.size()<maxHeap.size())
	        	minHeap.add(maxHeap.poll());
	    }
	    
	    public double findMedian() {
	        if(minHeap.size()>maxHeap.size())
	        	return minHeap.peek();
	        return(minHeap.peek()+maxHeap.peek())/2.0;
	    }
	}
}


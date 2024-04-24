package stack.AdityaVerma;

import java.security.interfaces.DSAKeyPairGenerator;
import java.util.*;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StackPractice {
//	Given an array, print the Next Greater Element (NGE) for every element. The Next greater 
//	Element for an element x is the first greater element on the right side of x in array. 
//	Elements for which no greater element exist, consider next greater element as -1.
	
	public void nge(int[]arr) {
		Stack<Integer> st = new Stack<Integer>();
		int n=arr.length;
		int[]res = new int[n];
		for(int i=n-1;i>=0;i--) {
			if(st.isEmpty())
				res[i]=-1;
			else {
				while(!st.isEmpty() && st.peek()<arr[i])
					st.pop();
				if(!st.isEmpty())
					res[i]=st.peek();
				else
					res[i]=-1;
			}
			st.push(arr[i]);
		}
	} 
	
	// Given an array of integers, find the closest  greater on
	//left of every element. If an element has no greater on the left side, print -1 
	
	public void nearestGreaterToLeft(int[]arr) {
		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;
		 int[] res=new int[n];
		 for(int i=0;i<n;i++) {
			 if(st.isEmpty()) {
				 res[i]=-1;
				 }
			 else {
				 while(!st.isEmpty() && st.peek()<arr[i]) 
					 st.pop();
				 if(!st.isEmpty())
					 res[i]=st.peek();
				 else
					 res[i]=-1;
					 
			 }
			 st.push(arr[i]);
		 }
	}
	
//	Given an array of integers, find the closest smaller on 
//	left of every element. If an element has no smaller on the left side, print -1 . 
	
	public void nearestSmallerToLeft(int[]arr) {
		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;
		 int[] res=new int[n];
		 for(int i=0;i<n;i++) {
			 if(st.isEmpty()) {
				 res[i]=-1;
				 }
			 else {
				 while(!st.isEmpty() && st.peek()>arr[i]) 
					 st.pop();
				 if(!st.isEmpty())
					 res[i]=st.peek();
				 else
					 res[i]=-1;
					 
			 }
			 st.push(arr[i]);
		 }
	}
	
//	Given an array of integers, find the closest smaller on
//	rightof every element. If an element has no smaller on the right side, print -1. 
	
	public void nearestSmallerToRight(int[]arr) {
		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;
		 int[] res=new int[n];
		 for(int i=n-1;i>=0;i--) {
			 if(st.isEmpty()) {
				 res[i]=-1;
				 }
			 else {
				 while(!st.isEmpty() && st.peek()>arr[i]) 
					 st.pop();
				 if(!st.isEmpty())
					 res[i]=st.peek();
				 else
					 res[i]=-1;
					 
			 }
			 st.push(arr[i]);
		 }
	} 
	
//	The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate span of stock’s price for
//	all n days.
//	The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day,
//	for which the price of the stock on
//	the current day is less than or equal to its price on the given day.
//	For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
	
	public void stockSpan(int[]arr) {
		Stack<int[]> st = new Stack<>();
		int n = arr.length;
		 int[] res=new int[n];
		 for(int i=0;i<n;i++) {
			 if(st.isEmpty()) {
				 res[i]=-1;
				 }
			 else {
				 //finding nearest greatest element to left
				 while(!st.isEmpty() && st.peek()[0]<arr[i])
					 st.pop();
				 if(!st.isEmpty())
					 res[i]=st.peek()[1];
				 else
					 res[i]=-1;
					 
			 }
			 int[] temp =  {arr[i],i};
			 st.push(temp);
		 }

			for(int i=0;i<n;i++) {
				res[i] = i-res[i];
			}
			
	}
	
//	Find the largest rectangular area possible in a given histogram where the largest rectangle can be made of a number of contiguous bars. 
//	For simplicity, assume that all bars have same width and the width is 1 unit.
//	Refer : https://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-using-stack/
	
	
	public int maxAreaHistogram(int [] arr) {
		Stack<int[]> st = new Stack<>();
		int n = arr.length;
		 int[] nsrIndex=new int[n];
		 int[] nslIndex=new int[n];
		 int[] width=new int[n];
		 //code to find nearest smaller right index
		 for(int i=n-1;i>=0;i--) {
			 if(st.isEmpty()&& i==n-1) {
				 nsrIndex[i]=n;
				 }
			 else {
				 while(!st.isEmpty() && st.peek()[0]>=arr[i]) 
					 st.pop();
				 if(!st.isEmpty())
					 nsrIndex[i]=st.peek()[1];
				 else
					 nsrIndex[i]=n;
					 
			 }
			 int[] temp =  {arr[i],i};
			 st.push(temp);
		 }
		 st.removeAllElements();
		 
		//code to find nearest smaller left index
		 for(int i=0;i<n;i++) {
			 if(st.isEmpty()) {
				 nslIndex[i]=-1;
				 }
			 else {
				 while(!st.isEmpty() && st.peek()[0]>=arr[i]) 
					 st.pop();
				 if(!st.isEmpty())
					 nslIndex[i]=st.peek()[1];
				 else
					 nslIndex[i]=-1;
					 
			 }
			 int[] temp =  {arr[i],i};
			 st.push(temp);
		 }
		 
		 for(int i=0;i<n;i++) 
			 width[i]=nsrIndex[i]-nslIndex[i]-1;
		 int max = Integer.MIN_VALUE;
		 for(int i=0;i<n;i++) {
			 max=Math.max(width[i]*arr[i], max);
		 }
		 return max;
		 
	}
	
//	Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1’s.
//	Example:
//
//	Input :   0 1 1 0
//	          1 1 1 1
//	          1 1 1 1
//	          1 1 0 0
//
//	Output :  1 1 1 1
//	          1 1 1 1 . 
	
	public int maxAreaRectangleBinary(int[][] matrix) {
		int n=matrix[0].length;
		int[] h1 =matrix[0];
		int ans = Integer.MIN_VALUE;
		for(int i=0;i<matrix.length;i++) {
			if(i>0) {
				for(int j=0;j<n;j++) {
					if(matrix[i][j]==1)
						h1[j]=h1[j]+1;
					else 
						h1[j]=0;
				}
			}
			int temp = maxAreaHistogram(h1);
			ans=Math.max(ans, temp);
		}
		return ans;
	}
	
//	Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//	Input: arr[]   = {2, 0, 2}
//	Output: 2
//	Structure is like below
//	| |
//	|_|
//	We can trap 2 units of water in the middle gap.
//
//	Input: arr[]   = {3, 0, 0, 2, 0, 4}
//	Output: 10
//	Structure is like below
//	     |
//	|    |
//	|  | |
//	|__|_| 
//	We can trap "3*2 units" of water between 3 an 2,
//	"1 unit" on top of bar 2 and "3 units" between 2 
//	and 4.  See below diagram also.
	
	public int rainWater(int[] arr) {
		int n=arr.length ,water = 0;
		int[] lMax = new int[n];
		lMax[0]=arr[0];
		int[] rMax = new int[n];
		rMax[n-1]=arr[n-1];
		for(int i=1;i<n;i++) {
			lMax[i]=Math.max(lMax[i-1], arr[i]);
		}
		
		for(int i=n-2;i>=0;i--) {
			rMax[i]=Math.max(rMax[i+1], arr[i]);
		}
		for(int i =0;i<n;i++) {
			water+= Math.min(rMax[i], lMax[i])-arr[i];
		}
		
		return water;
	}
	
	
}

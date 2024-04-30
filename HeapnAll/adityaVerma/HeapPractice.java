package com.aditya.verma.heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HeapPractice {
	//kth smallest element in an array
	
	int kthSamllest(int k,int[] arr) {
		PriorityQueue<Integer> pMax=new PriorityQueue<Integer>(Collections.reverseOrder());//max heap
		for(int i=0;i<arr.length;i++) {
			pMax.add(arr[i]);
			if(pMax.size()>k)
				pMax.poll();
		}
		System.out.println(pMax.peek());
		return pMax.peek();
	}
	
	//return k largest elements in an array.Order of array does not matter
	
	int[] kLargestNumbers(int k,int[] arr) {
		int[] result=new int[k];
		PriorityQueue<Integer> pMin=new PriorityQueue<Integer>();//min heap
		for(int i=0;i<arr.length;i++) {
			pMin.add(arr[i]);
			if(pMin.size()>k)
				pMin.poll();
		}
		
		for(int i=0;i<k;i++)
			result[i]=pMin.poll();
		
		return result;
	}
	
	//sort k sorted (nearly sorted) array
	
	int[] sortKSorted(int k,int[] arr) {
		int[] result=new int[arr.length];
		PriorityQueue<Integer> pMin=new PriorityQueue<Integer>();//min heap
		int j=0;
		for(int i=0;i<arr.length;i++) {
			pMin.add(arr[i]);
			if(pMin.size()>k) {
				result[j]=pMin.poll();
				j++;
			}
		}
		while(!pMin.isEmpty()) {
			result[j]=pMin.poll();
			j++;
		}
		return result;
	}
	
	//k closest elements of a number x in an array
	
	class arr_comparator implements Comparator<int[]>{
		@Override
		public int compare(int[] o1, int[] o2) {
			if (o1[1] < o2[1]) return +1;
	        if (o1[1]==(o1[1])) return 0;
	        return -1;
		}
		
	}
	
	int[] kClosest(int k,int[] arr,int x) {
		int[] result=new int[k];
		PriorityQueue<int[]> pMax=new PriorityQueue<>((a, b) -> b[1] - a[1]);//max heap
		for(int i=0;i<arr.length;i++) {
			int[] temp= {arr[i],Math.abs(arr[i]-x)};
			pMax.add(temp);
			if(pMax.size()>k)
				pMax.poll();
		}
		for(int i=0;i<k;i++)
			result[i]=pMax.poll()[0];
		
		return result;
		
	}
	
	//top K frequent elements in array
	
	
	
	int[] kFrequent(int k,int[]arr) {
		int[] result=new int[k];
		HashMap<Integer, Integer> hm=new HashMap<Integer, Integer>();
		for(int i=0;i<arr.length;i++) {
			if(hm.containsKey(arr[i])) {
				hm.put(arr[i], hm.get(arr[i])+1);
			}else
				hm.put(arr[i], 1);
		}
		
		PriorityQueue<int[]> pMin=new PriorityQueue<int[]>((a,b)->(a[0]-b[0]));//min heap
		for(Map.Entry<Integer, Integer> entry:hm.entrySet()) {
			int[] temp= {entry.getValue(),entry.getKey()};
			pMin.add(temp);
			if(pMin.size()>k)
				pMin.poll();
		}
		
		for(int i=0;i<k;i++)
			result[i]=pMin.poll()[1];
		
		return result;
	}
	
	//sort by frequency
	int[] freqSort(int[] arr) {
		int[] result=new int[arr.length];
		HashMap<Integer, Integer> hm=new HashMap<Integer, Integer>();
		for(int i=0;i<arr.length;i++) {
			if(hm.containsKey(arr[i])) {
				hm.put(arr[i], hm.get(arr[i])+1);
			}else
				hm.put(arr[i], 1);
		}
		
		PriorityQueue<int[]> pMax=new PriorityQueue<>((a, b) -> b[1] - a[1]);//max heap
		for(Map.Entry<Integer, Integer> entry:hm.entrySet()) {
			int[] temp= {entry.getKey(),entry.getValue()};
			pMax.add(temp);
		}
		
		int j=0;
		
		while(!pMax.isEmpty()) {
			int freq=pMax.peek()[1];
			int el=pMax.poll()[0];
			for(int i=0;i<freq;i++) {
				result[j]=el;
				j++;
			}
		}
		
		return result;
	}
	
	//k closest points to origin (0,0)
	
	class arr2_comparator implements Comparator<int[]>{


		@Override
		public int compare(int[] o1, int[] o2) {
			if (o1[0] < o2[0]) return +1;
	        if (o1[0]==(o1[0])) return 0;
	        return -1;
		}
		
	}
	
	int[][] kClosest(int[][] points,int k) {
		PriorityQueue<int[]> pMax=new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for(int i=0;i<points.length;i++) {
			int [] dist= {(points[i][0]*points[i][0])+points[i][1]*points[i][1],points[i][0],points[i][1]};
			pMax.add(dist);
			if(pMax.size()>k)
				pMax.poll();
		}
		
		int[][] res=new int [k][2];
		int j=0;
		while(!pMax.isEmpty()) {
			res[j][0]=pMax.peek()[1];
			res[j][1]=pMax.poll()[2];
			j++;
		}
		return res;
	}
	
	//Connect ropes with minimum cost
	
	int minCost(int[] arr) {
		int sum=0;
		PriorityQueue<Integer> pMin=new PriorityQueue<Integer>();
		for(int i=0;i<arr.length;i++)
			pMin.add(arr[i]);
		
		while(pMin.size()>=2) {
			int a=pMin.poll();
			int b=pMin.poll();
			int tempSum=a+b;
			pMin.add(tempSum);
			sum+=tempSum;
			
		}
		
		return sum;
	}
	
	//get sum between k1th and k2th smallest number in an array
	
	int getSum(int k1,int k2,int[] arr) {
		int a=kthSamllest(k1, arr);
		int b=kthSamllest(k2, arr);
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>a && arr[i]<b)
				sum+=arr[i];
		}
		return sum;
	}
}


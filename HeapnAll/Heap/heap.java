package com.heap;

import java.util.List;

public class Heap {
	public int[] array;
	public int count;
	public int capacity;
	public int hepa_type;
	public Heap(int capacity, int hepa_type) {
		super();
		this.capacity = capacity;
		this.hepa_type = hepa_type;
		this.count=0;
		this.array=new int[capacity];
	}
	
	public int Parent(int i) {
		if(i<0||i>=this.count)
			return -1;
		return (i-1)/2;
	}
	
	public int LeftChild(int i) {
		int left=2*i+1;
		if(left>=this.count)
			return -1;
		return left;
	}
	
	public int RightChild(int i) {
		int right=2*i+2;
		if(right>=this.count)
			return -1;
		return right;
	}
	
	public int GetMax() {
		if(this.count==0)
			return -1;
		return this.array[0];//in case of max_heap
	}
	
	//heapifying the element at location
	
	public void PercolateDown(int i) {
		int l,r,max,temp;
		l=LeftChild(i);
		r=RightChild(i);
		if(l!=-1 && this.array[l]>this.array[i])
			max=l;
		else
			max=i;
		if(r!=-1 && this.array[r]>this.array[i])
			max=r;
		if(max !=i) {
			temp=this.array[i];
			this.array[i]=this.array[max];
			this.array[max]=temp;
			PercolateDown(max);
		}
	}
	
	public void PercolateUp(int i){
		int p=Parent(i);
		int max=-1;
		if(this.array[p]<this.array[i])
			max=i;
		if(max==i) {
			int temp=this.array[p];
			this.array[p]=this.array[i];
			this.array[max]=temp;
			PercolateUp(p);
		}
	}
	
	public int DeleteMax() {
		if(this.count==0)
			return -1;
		int data=this.array[0];
		this.array[0]=this.array[this.count-1];
		this.count--;
		PercolateDown(0);
		return data;
	}
	
	public void ResizeHeap() {
		int[] arr_old=new int[this.capacity];
		System.arraycopy(this.array, 0, arr_old, this.count-1, count);
		this.array=new int[this.capacity*2];
		for(int i=0;i<this.capacity;i++) {
			this.array[i]=arr_old[i];
		}
		this.capacity *= 2;
		arr_old=null;
	}
	
	public void insert(int data) {
		if(this.capacity==this.count)
			ResizeHeap();
		int i=this.count-1;
		this.array[i]=data;
		PercolateUp(i);
	}
	
	public void BuildHeap(Heap h,int[]A,int n) {
		if (h==null)
			return;
		while(n>this.capacity)
			h.ResizeHeap();
		for(int i=0;i<n;i++) {
			h.array[i]=A[i];
		}
		
		this.count=n;
		for(int i=(n-1)/2;i>=0;i--) {
			h.PercolateDown(i);
		}
	}
	
	public void HeapSort(int[]A,int n) {//the sorted one wiil be the array of heap..
		Heap h=new Heap(n, 0);
		int old_size=h.count;
		BuildHeap(h, A, n);
		for(int i=n-1;i>=0;i--) {
			int temp=h.array[0];
			h.array[0]=h.array[h.count-1];
			h.count--;
			h.PercolateDown(0);
		}
		h.count=old_size;
	}
	
	public int Delete(Heap h,int i) {
		if(h.count<i)
			return -1;
		int key=h.array[i];
		h.array[i]=h.array[h.count-1];
		h.count--;
		h.PercolateDown(i);
		return key;
	}
	
	//kth minimum in min heap
	public int FindKthMin(int k) {
		Heap hOrg = null,hAux = null;
		int heapElement;
		int count=1;
		hAux.insert(hOrg.DeleteMax());
		while(true) {
			heapElement=hAux.DeleteMax();
			if(++count==k)
				return heapElement;
			else {
				hAux.insert(hOrg.LeftChild(k));
				hAux.insert(hOrg.RightChild(k));
			}
		}
	}
	
	//merge two max_heap of different size
	public void mergeHeaps(Heap h1,Heap h2) {
		int n=h1.array.length+h2.array.length;
		int arr[]=new int[n];
		Heap h=new Heap(n, 0);
		BuildHeap(h, arr, n);
	}
	
	//Merge k sorted arrays
	//https://www.geeksforgeeks.org/merge-k-sorted-arrays/
	
	//Dyanamic median using heap
	//https://www.baeldung.com/java-stream-integers-median-using-heap
}


package book;

import java.util.Collections;
import java.util.Vector;

public class SortingAlgo {
	
	void countingSort(char[] arr) {
		int n=arr.length;
		char[] output=new char[n];
		int count[]=new int [48];//this size should be the max number of inputs possible for input array
		for(int i=0;i<48;i++)
			++count[arr[i]];
		
		for(int i=1;i<48;i++)
			count[i] +=count[i-1];
		
		for(int i=n-1;i>=0;i--) {
			output[count[arr[i]]-1]=arr[i];
			--count[count[i]];
		}
		
		for(int i=0;i<n;i++)
			arr[i]=output[i];
	}
	
	void bucketSort(float[]arr) {
		int n=arr.length;
		if(n<=0)
			return ;
		Vector<Float>[] buckets=new Vector[n];
		for(int i=0;i<n;i++)
			buckets[i]=new Vector<Float>();
		
		for(int i=0;i<n;i++) {
			float idx=arr[i]*n;
			buckets[(int) idx].add(arr[i]);
		}
		
		for(int i=0;i<n;i++)
			Collections.sort(buckets[i]);
		int index=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<buckets[i].size();j++)
				arr[index++]=buckets[i].get(j);
		}
	}
}


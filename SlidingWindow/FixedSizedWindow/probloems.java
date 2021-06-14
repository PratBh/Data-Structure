package fixedSizeWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problems {
	//Find  maximum sum of  subarray of size k from a given array
	int maxSumSubarray(int [] arr,int k) {
		int sum=0;
		int max_sum=Integer.MIN_VALUE;
		int i=0;//start of window
		int j=0;//end of window
		while(j<arr.length) {
			sum+=arr[j];
			if(j-i+1<k)
				j++;
			else if(j-i+1==k) {
				max_sum=Math.max(sum, max_sum);
				sum=sum-arr[i];
				i++;j++;
			}
		}
		return max_sum;
	}
	
	//Find first -ve integer in every subarray/window of size k
	
	int[] firstNegative(int[] arr,int k) {
		int n=arr.length;
		List<Integer> neg_list=new ArrayList<Integer>();
		int[] res=new int[n-k+1];
		int l=0,i=0,j=0;
		while(j<n) {
			if(arr[j]<0) neg_list.add(arr[j]);
			if(j-i+1<k) j++;
			else if(j-i+1==k) {
				if(!neg_list.isEmpty()) {
					res[l]=neg_list.get(0);
					if(arr[i]==neg_list.get(0))
						neg_list.remove(0);
				}else {
					res[l]=0;
				}
				l++;
				i++;j++;
			}
		}
		return res;
	}
	
	
	//count occurance of anagram
	
	int countAnagram(String s,String pattern) {
		int k=pattern.length();
		Map<Character, Integer> hm=new HashMap<Character, Integer>();
		for(char c:pattern.toCharArray()) {
			if(!hm.containsKey(c))
				hm.put(c, 1);
			else
				hm.put(c,hm.get(c)+1);
		}
		
		int count = hm.size();
		int i=0,j=0,ans=0;
		while(j<s.length()) {
			if(hm.containsKey(s.charAt(j))) {
				hm.put(s.charAt(j),hm.get(s.charAt(j))-1);
				if(hm.get(s.charAt(j))==0)
					count--;
			}
			if(j-i+1<k)
				j++;
			else if(j-i+1==k) {
				if(count==0)
					ans++;
				if(hm.containsKey(s.charAt(i))){
					if(hm.get(s.charAt(i))==0)
						count++;
					hm.put(s.charAt(i),hm.get(s.charAt(i))+1);
				}
				i++;
				j++;
			}
		}
		return ans;
	}
	
	//maximum of all subarray of size k
	int [] maxOfSubarray(int[] arr,int k) {
		int n=arr.length;
		Queue<Integer> q=new LinkedList<Integer>();
		int len=n-k+1;
		int[] res=new int[len];
		int i=0,j=0,l=0;
		while(j<n) {
			if(q.isEmpty())
				q.add(arr[j]);
			else {
				while(!q.isEmpty() && q.peek()<arr[j])
					q.poll();
				q.add(arr[j]);
			}
			if(j-i+1<k)
				j++;
			else if(j-i+1==k) {
				res[l]=q.peek();
				l++;
				if(arr[i]==q.peek()) {
					q.poll();
				}
				i++;
				j++;
			}
		}
		return res;
	}
}


package variableSizeWindow;

import java.util.HashMap;
import java.util.Map;

public class Problems {

//	Problem Description:
//
//		Given an array containing N positive integers and an integer K. Your task is to find the length of the longest Sub-Array with sum of the
//	elements equal to the given value K.
//
//		For Input:
//		1
//		7 5
//		4 1 1 1 2 3 5
//		your output is: 
//		4
	
	int subarraySumK(int k,int[] arr) {
		int i=0,j=0,sum=0,len=Integer.MIN_VALUE;
		while(j<arr.length) {
			sum+=arr[j];
			if(sum<k) {
				j++;
			}else {
				while(sum>k) {
					sum-=arr[i];
					i++;
				}
				if(sum==k) {
					len=Math.max(len, j-i+1);
					sum-=arr[i];
					i++;
				}
				j++;
			}
		}
		return len;
	}
	
	//largest subarray of sum k where negetave number can be present
	int subarraySumKNeg(int k,int[]arr) {
		int i=0,j=0,sum=0,len=Integer.MIN_VALUE;
		while(j<arr.length) {
			sum+=arr[j];
			if(sum==k) {
				len=Math.max(j-i+1, len);
				sum-=arr[i];
				i++;
			}
			j++;
		}
		return len;
	}
	
//	Given a string you need to print the size of the longest possible substring that has exactly k unique characters. If there is no possible substring print -1.
//	Example
//	For the string aabacbebebe and k = 3 the substring will be cbebebe with length 7.
	
	int substringKUnique(String s,int k) {
		int i=0,j=0,len=Integer.MIN_VALUE;
		Map<Character,Integer> mp=new HashMap<Character,Integer>();
		while(j<s.length()) {
			if(mp.containsKey(s.charAt(j)))
				mp.put(s.charAt(j), mp.get(s.charAt(j))+1);
			else
				mp.put(s.charAt(j), 1);
			if(mp.size()<k)
				j++;
			else if(mp.size()==k) {
				len=Math.max(len, j-i+1);
				j++;
			}
			else if(mp.size()>k) {
				while(mp.size()>k) {
					mp.put(s.charAt(i), mp.get(s.charAt(i))-1);
					if(mp.get(s.charAt(i))==0)
						mp.remove(s.charAt(i));
						
					i++;
				}
				j++;
			}
		}
		return len;
	}
}


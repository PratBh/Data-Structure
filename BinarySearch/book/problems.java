
package book;

import java.util.HashMap;
import java.util.Map;

public class Problems {
	//In an array of positive numbers find the first number which was repeated.
	//arr={3,2,1,2,2,3} answer should be 3(not 2)
	
	int findFirstNumRepeated(int[] arr) {
		Map<Integer,Integer> hm=new HashMap<Integer,Integer>();
		for(int i=0;i<arr.length;i++) {
			if(hm.containsKey(arr[i])) {
				if(hm.get(arr[i])<0)
					continue;
				else
					hm.put(arr[i], -1*hm.get(arr[i]));
			}else
				hm.put(arr[i],i+1);
		}
		
		int max=Integer.MIN_VALUE,ans=0;
		for(Map.Entry<Integer, Integer> entry:hm.entrySet()) {
			if(entry.getValue()<0) {
				if(entry.getValue()>max) {
					max=entry.getValue();
					ans=entry.getKey();
				}
			}
		}
		return ans;
	}
}

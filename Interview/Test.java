package Interview;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		
		
   }
	
	
	
//	Maximum consecutive ones in an array of 1 & 0
//	var nums = [1,0,0,1,1,1,0,0,1,0,1,1,0,0,1];
//	
//	
	static int findLongestOnes (int []nums) {
		int n=nums.length,max_len = 0,i=0,j=0,len=0;
		while(j<n) {
			if(nums[j]==1) {
				len = j-i+1;
				max_len=(Math.max(max_len, len));
				j++;
			}else {
				j++;
				i=j;
			}
		}
		return max_len;
	}
//
////	Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
////			Output: ["the","is","sunny","day"]
//	
//	
//	
	static List<String> mostFreq(List<String> words,int k){
		Map<String ,Integer> mp =new HashMap<String, Integer>();
		PriorityQueue<Custom> pq = new PriorityQueue<Custom>((a,b)->a.freq-b.freq);
		for(String word:words) {
			mp.put(word, mp.getOrDefault(word, 0)+1);
		}
		for(Map.Entry<String, Integer> entry:mp.entrySet()) {
			Custom temp = new Custom(entry.getKey(),entry.getValue());
			pq.add(temp);
			if(pq.size()>k)
				pq.poll();
		}
		List<String> res =new ArrayList<String>();
		while(!pq.isEmpty())
			res.add(pq.poll().val);
		Collections.reverse(res);
		return res;
	}
//	
//
//	
}
class Custom{
	String val;
	int freq;
	public Custom(String val, int freq) {
		super();
		this.val = val;
		this.freq = freq;
	}
//	
}

 
	

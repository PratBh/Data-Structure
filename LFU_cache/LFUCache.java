package LFU_cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class LFUCache {
	 	HashMap<Integer, Integer[]> keyToValuenFreqMap ;
	    HashMap<Integer, Set<Integer>> freqToListOfKeys;
	    TreeSet<Integer> frqSet;
	    int capacity;

	    public LFUCache(int size){
	        capacity=size;
	        keyToValuenFreqMap=new HashMap<>();
	        freqToListOfKeys=new HashMap<>();
	        frqSet=new TreeSet<>((a,b)->a-b);
	    }
	
	public int get(int key){
        if(keyToValuenFreqMap.size()==0||!keyToValuenFreqMap.containsKey(key))
            return -1;
        int res = keyToValuenFreqMap.get(key)[0],frq=keyToValuenFreqMap.get(key)[1];
        keyToValuenFreqMap.remove(key);
        Integer [] arr = {res,frq+1};
        keyToValuenFreqMap.put(key,arr);
        if(freqToListOfKeys.containsKey(frq)) {
            Set<Integer> keys = freqToListOfKeys.get(frq);
            keys.remove(key);
        }
         Set<Integer> set = freqToListOfKeys.getOrDefault(frq+1,new HashSet<>());
         set.add(key);
         freqToListOfKeys.put(frq+1,set);
         frqSet.add(frq+1);
         if(freqToListOfKeys.get(frq).size()==0)
             frqSet.remove(frq);
        return res;
    }
	
	public void put(int key, int value) {
		boolean oldKey=keyToValuenFreqMap.containsKey(key);
		int newFreq = (oldKey)? keyToValuenFreqMap.get(key)[1]+1:1;
        if(keyToValuenFreqMap.size()==capacity) {
            int minFrq = frqSet.first();
            int leastFrqKey = freqToListOfKeys.get(minFrq).iterator().next();
            keyToValuenFreqMap.remove(leastFrqKey);
            freqToListOfKeys.get(minFrq).remove(leastFrqKey);
            if (freqToListOfKeys.get(minFrq).size() == 0)
                frqSet.remove(minFrq);
            }
            Integer [] arr = {value,newFreq};
            keyToValuenFreqMap.put(key,arr);
            Set<Integer> set = freqToListOfKeys.getOrDefault(arr[1],new HashSet<>());
            set.add(key);
            freqToListOfKeys.put(arr[1],set);
            frqSet.add(arr[1]);
        }

}

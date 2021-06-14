package test;

import java.util.LinkedHashSet;
import java.util.Set;

public class LRU_WithLinkedhash {
	Set<Integer> cache;
	int capacity;
	public LRU_WithLinkedhash(int capacity) {
		this.capacity = capacity;
		this.cache=new LinkedHashSet<Integer>();
	}
	
	public boolean get(int page) {
		if(!cache.contains(page))
			return false;
		cache.remove(page);
		return true;
	}
	
	public void put(int page) {
		if(capacity==cache.size()) {
			int firstKey=cache.iterator().next();
			cache.remove(firstKey);
		}
		cache.add(page);
	}
	
	public void refer(int page) {
		if(get(page)==false)
			put(page);
	}
	
}

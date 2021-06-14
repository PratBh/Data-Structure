package test;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class LRU_withoutLinked {
	private int capacity;
	private Deque<Integer> dq;
	private HashSet<Integer> cache;
	
	public LRU_withoutLinked(int capacity) {
		super();
		this.capacity = capacity;
		this.dq = new LinkedList<Integer>();
		this.cache = new HashSet<Integer>();
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Deque<Integer> getDq() {
		return dq;
	}
	public void setDq(Deque<Integer> dq) {
		this.dq = dq;
	}
	public HashSet<Integer> getCache() {
		return cache;
	}
	public void setCache(HashSet<Integer> cache) {
		this.cache = cache;
	}
	
	public void refer(int page) {
		if(!cache.contains(page)) {
			if(dq.size()==this.capacity) {
				int last=dq.removeLast();
				cache.remove(last);
			}
		}else {
			dq.remove(page);
		}
		dq.push(page);
		cache.add(page);
	}
	
	public void display() {
		dq.stream().forEach(System.out::println);
	}
	
}

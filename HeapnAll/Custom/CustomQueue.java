package custom;

import java.util.ArrayList;

public class CustomQueue {
	private ArrayList<Integer> arr;

	public CustomQueue() {
		super();
		this.arr=new ArrayList<Integer>();
	}
	
	public void add(int i) {
		arr.add(i);
	}
	
	public int pop() {
		return arr.remove(0);
	}
	
	public int peek() {
		return arr.get(0);
	}	
}

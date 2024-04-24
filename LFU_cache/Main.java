package LFU_cache;

public class Main {

	public static void main(String[] args) {
		LFUCache lfu = new LFUCache(2);
		lfu.put(3, 1); 
		lfu.put(2, 1); 
		lfu.put(2, 2); 
		lfu.put(4, 4); 
		 System.out.println(lfu.get(2)); 

	}

}

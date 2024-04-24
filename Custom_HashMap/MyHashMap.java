package Custom_HashMap;

public class MyHashMap<K,V> {
	private int capacity = 16;
	private Entry<K, V>[] table;
	
	public MyHashMap() {
		table=new Entry[capacity];
	}
	
	public MyHashMap(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
    }
	
	private int index(K key) {
		if(key==null)
			return 0;
		return Math.abs(key.hashCode()%capacity);
	}
	public void put(K key,V value) {
		int idx = index(key);
		Entry newEntry = new Entry<K, V>(key, value);
		if(table[idx]==null)
			table[idx]=newEntry;
		else {
			Entry<K, V> prev = null;
			Entry<K, V> curr = table[idx];
			while(curr!=null) {
				if(curr.getK().equals(key)) {
					curr.setV(value);
					break;
				}
				prev =curr;
				curr=curr.getNext();
			}
			if(prev!= null)
				prev.setNext(newEntry);
		}
	}
	
	public V get(K key) {
		V value = null;
		int idx = index(key);
		Entry<K, V> entry = table[idx];
		while(entry!=null) {
			if(entry.getK().equals(key)) {
				value= entry.getV();
				break;
			}
			entry=entry.getNext();
		}
		return value;
	}
	
	public void remove(K key) {
		int idx = index(key);
		Entry<K, V> entry = table[idx];
		Entry<K, V> prev = null;
		while(entry!=null) {
			if(entry.getK().equals(key)) {
				if(prev==null) {
					entry=entry.getNext();
					table[idx]=entry;
					return;
				}else {
					prev.setNext(entry.getNext());
					return;
				}
			}
			prev=entry;
			entry=entry.getNext();
		}
	}
}

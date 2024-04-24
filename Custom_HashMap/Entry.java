package Custom_HashMap;

public class Entry<K,V> {
	private K k;
	private V v;
	private Entry<K,V> next;
	
	public Entry(K k,V v){
		this.k=k;
		this.v=v;
		this.next=null;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public Entry<K, V> getNext() {
		return next;
	}

	public void setNext(Entry<K, V> next) {
		this.next = next;
	}
	
	
}

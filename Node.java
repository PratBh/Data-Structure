
public class Node {
	int value;
	Node next;
	public Node(int value) {
		super();
		this.value = value;
		this.next=null;
	}
	
	public Node() {
		this.value = 0;
		this.next=null;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
	
}

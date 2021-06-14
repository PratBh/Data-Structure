package Custom;

public class CustomNode {
	private int data;
	private CustomNode next;
	public CustomNode(int data, CustomNode next) {
		super();
		this.data = data;
		this.next = next;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public CustomNode getNext() {
		return next;
	}
	public void setNext(CustomNode next) {
		this.next = next;
	}
	
}

package tree;

public class BinarySearchTreeNode {
	private int data;
	private BinarySearchTreeNode left;
	private BinarySearchTreeNode right;
	
	
	
	public BinarySearchTreeNode(int data) {
		super();
		this.data = data;
		this.left = null;
		this.right = null;
	}



	public BinarySearchTreeNode(int data, BinarySearchTreeNode left, BinarySearchTreeNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}



	public BinarySearchTreeNode() {
		super();
	}



	public int getData() {
		return data;
	}



	public void setData(int data) {
		this.data = data;
	}



	public BinarySearchTreeNode getLeft() {
		return left;
	}



	public void setLeft(BinarySearchTreeNode left) {
		this.left = left;
	}



	public BinarySearchTreeNode getRight() {
		return right;
	}



	public void setRight(BinarySearchTreeNode right) {
		this.right = right;
	}
	
	
}


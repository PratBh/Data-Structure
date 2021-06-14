package tree;

public class treeNode {
	public int data;
	public treeNode leftNode,rightNode;
	
	public treeNode(int data) {
		super();
		this.data = data;
		this.leftNode = null;
		this.rightNode = null;
	}
	
	public treeNode(int data, treeNode leftNode, treeNode rightNode) {
		super();
		this.data = data;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public treeNode getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(treeNode leftNode) {
		this.leftNode = leftNode;
	}
	public treeNode getRightNode() {
		return rightNode;
	}
	public void setRightNode(treeNode rightNode) {
		this.rightNode = rightNode;
	}
	
	
	
	
	
}


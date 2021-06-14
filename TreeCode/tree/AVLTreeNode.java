package tree;

public class AVLTreeNode {
	private int data,height;
	private AVLTreeNode left,right;
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public AVLTreeNode getLeft() {
		return left;
	}
	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}
	public AVLTreeNode getRight() {
		return right;
	}
	public void setRight(AVLTreeNode right) {
		this.right = right;
	}
	
	AVLTreeNode singleRotateLeft(AVLTreeNode x) {
		AVLTreeNode w=x.getLeft();
		x.setLeft(w.getRight());
		w.setRight(x);
		x.setHeight(Math.max(x.getRight().getHeight(), x.getLeft().getHeight())+1);
		w.setHeight(Math.max(w.getLeft().getHeight(), x.getHeight())+1);
		return w;//new root
	}
	
	AVLTreeNode singleRotateRight(AVLTreeNode w) {
		AVLTreeNode x=w.getRight();
		w.setRight(x.getLeft());
		x.setLeft(w);
		w.setHeight(Math.max(w.getLeft().getHeight(), w.getRight().getHeight())+1);
		x.setHeight(Math.max(x.getRight().getHeight(), w.getHeight())+1);
		return x;//new root
	}
	
	AVLTreeNode DoubleRotateWithLeft(AVLTreeNode z) {
		z.setLeft(singleRotateRight(z.getLeft()));
		return singleRotateLeft(z);
	}
	
	AVLTreeNode DoubleRotateWithRight(AVLTreeNode x) {
		x.setRight(singleRotateRight(x.getRight()));
		return singleRotateRight(x);
	}
	
	AVLTreeNode insert(AVLTreeNode root,AVLTreeNode parent,int data) {
		if(root==null) {
			root=new AVLTreeNode();
			root.setData(data);
			root.setHeight(0);
			root.setLeft(null);
			root.setRight(null);
		}
		else if(data<root.getData()){
			root.setLeft(insert(root.getLeft(), parent, data));
			if(root.getLeft().getHeight()-root.getRight().getHeight()==2) {
				if(data<root.getLeft().data)
					root=singleRotateLeft(root);
				else
					root=DoubleRotateWithLeft(root);
			}
		}
		else if(data>root.getData()) {
			root.setRight(insert(root.getRight(), parent, data));
			if(root.getRight().getHeight()-root.getLeft().getHeight()==2) {
				if(data>root.getRight().getData())
					root=singleRotateRight(root);
				else
					root=DoubleRotateWithRight(root);
			}
		}
		root.setData(Math.max(root.getLeft().getHeight(), root.getRight().getHeight())+1);
		return root;
	}
	
}

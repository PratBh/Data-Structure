package tree;

public class BSTWithLessCount {
	int val;
	int lessCount;
	BSTWithLessCount left;
	BSTWithLessCount right;
	public BSTWithLessCount(int val,int lessCount) {
		this.val=val;
		this.lessCount=lessCount;
		this.left=this.right=null;
	}
	
	int addNode(BSTWithLessCount root,int value,int countSmaller ) {
		if(root==null) {
			root=new BSTWithLessCount(value, countSmaller);
			return countSmaller;
		}else if(root.val<value) {
			return root.lessCount+addNode(root.right, value, countSmaller+1);
		}else {
			root.lessCount++;
			return addNode(root.left, value, countSmaller);
		}
	}
}

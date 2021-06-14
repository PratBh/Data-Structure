package tree;

public class Test {

	public static void main(String[] args) {
		treeNode root=new treeNode(1);
		root.leftNode=new treeNode(2);
		root.rightNode=new treeNode(3);
		root.leftNode.rightNode=new treeNode(5);
		Problems prb=new Problems();
		prb.verticalOrder(root);
	}

}


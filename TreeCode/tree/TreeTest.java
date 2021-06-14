package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeTest {

	public static void main(String[] args) {
			treeNode n1=new treeNode(1);
			treeNode n2=new treeNode(2);
			treeNode n3=new treeNode(3);
			treeNode n4=new treeNode(4);
			treeNode n5=new treeNode(5);
			treeNode n6=new treeNode(6);
			treeNode n7=new treeNode(7);
			
			n1.setLeftNode(n2);
			n1.setRightNode(n3);
			
			n2.setLeftNode(n4);
			n2.setRightNode(n5);
			
			n3.setLeftNode(n6);
			n3.setRightNode(n7);
			
			int[] post = {4,5,2,6,7,3,1};
			int[] in = {4,2,5,1,6,3,7};
			
//			TreeHandler.inOrder(n1);
//			System.out.println();
//			TreeHandler.preOrder(n1);
//			System.out.println();
//			TreeHandler.postOrder(n1);
//			System.out.println(TreeHandler.findMaxWithoutRecursion(n1));
//			System.out.println(TreeHandler.findData(n1, 6));
//			System.out.println(TreeHandler.maxTreeDepth(n1));
//			System.out.println(TreeHandler.maxSum(n1));
//			TreeHandler.printPath(n1);
		//TreeHandler.zigZagTraversal(n1);
			//TreeHandler.inOrderTraversal(n1);
			//TreeHandler.findPathSum(n1);
			//System.out.println(TreeHandler.maxAbsoluteDiffLevel(n1));
			//ArrayList<Integer> euler = new ArrayList<Integer>();
			//TreeHandler.eulerTour(n1, euler);
			//System.out.println(TreeHandler.findLCA(n1, n4, n2));
			//System.out.println(TreeHandler.findMaxWidth(n1));
			//TreeHandler.isSameTree(n1, n1);
			TreeHandler hnd=new TreeHandler();
			//hnd.builtBT(post, in);
			hnd.levelOrderBottom(n1);
	}

}

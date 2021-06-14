package tree;

public class BSTHandler {
	
	static BinarySearchTreeNode findElement(BinarySearchTreeNode root,int data) {
		BinarySearchTreeNode tmp=null;
		while(root!=null) {
			if(data==root.getData()) {
				tmp=root;
			break;
			}
			else if(data>root.getData())
				root=root.getRight();
			else root=root.getLeft();
		}
		
		return tmp;
	}
	
	static BinarySearchTreeNode findMinElement(BinarySearchTreeNode root) {
		//left most element is the minimum
		if(root==null)
			return null;
		while(root.getLeft()!=null)
			root=root.getLeft();
		return root;
	}
	
	static BinarySearchTreeNode findMaxElement(BinarySearchTreeNode root) {
		//right most element is the maximum
		if(root==null)
			return null;
		while(root.getRight()!=null)
			root=root.getRight();
		return root;
	}
	
	static BinarySearchTreeNode insertElement(BinarySearchTreeNode root,int data) {
		BinarySearchTreeNode newNode=new BinarySearchTreeNode(data);
		if(root==null)
            return newNode;
		BinarySearchTreeNode x=root;
		BinarySearchTreeNode p=x;
		BinarySearchTreeNode y=null;
        while(x!=null){
            y=x;
            if(data<x.getData())
                x=x.getLeft();
            else
                x=x.getRight();
        }
        
        if(y==null)
            y=newNode;
        
        else if(y.getData()>data)
            y.setLeft(newNode);
        else 
            y.setRight(newNode);
        
        return p;
	}
	
	static BinarySearchTreeNode deleteElement(BinarySearchTreeNode root,int data) {
		BinarySearchTreeNode tmp;
		if(root==null)
			return null;
		else if(data<root.getData())
			root.setLeft(deleteElement(root.getLeft(), data));
		else if(data>root.getData())
			root.setRight(deleteElement(root.getRight(), data));
		else {
			if(root.getLeft()!=null && root.getRight()!=null) {
				tmp=findMaxElement(root.getLeft());
				root.setData(tmp.getData());
				root.setLeft(deleteElement(root.getLeft(), root.getData()));
			}else {
				tmp=root;
				if(root.getLeft()==null)
					root=root.getRight();
				if(root.getRight()==null)
					root=root.getLeft();
			}
		}
		return root;
	}
	
	//lowest common ancestor(LCA) for two given nodes is same as finding shortest path between those two nodes.In BST
	//starting from root the first element we get which fall between the given two pointers in the LCA.
	
	static BinarySearchTreeNode findLCA(BinarySearchTreeNode root,BinarySearchTreeNode a,BinarySearchTreeNode b) {
		if(root == null)
			return root;
		if(root==a || root ==b)
			return root;
		if(Math.max(a.getData(), b.getData())<root.getData())
			return findLCA(root.getLeft(), a, b);
		else if(Math.min(a.getData(), b.getData())>root.getData())
			return findLCA(root.getRight(), a, b);
		else 
			return root;
	}
	
	static boolean isBST(BinarySearchTreeNode root) {
		if(root == null)
			return true;
		if(root.getLeft()!=null && findMaxElement(root.getLeft()).getData()>root.getData())
			return false;
		if (root.getRight()!=null && findMinElement(root.getRight()).getData()<root.getData())
			return false;
		if(!isBST(root.getLeft())|| !isBST(root.getRight()))
			return false;
		
		return true;
	}
	
	static int prev=Integer.MIN_VALUE;
	
	static boolean inBSTSimple(BinarySearchTreeNode root) {
		if(root==null)
			return true;
		if(!inBSTSimple(root.getLeft()))
			return false;
		if(root.getData() < prev)
			return false;
		prev=root.getData();
		return inBSTSimple(root.getRight());
	}
	
	//start and end at the start should be the starting point of array and ending point
	static BinarySearchTreeNode buildBSTFromSortedArray(int A[],int start,int end) {
		BinarySearchTreeNode tmp;
		if(start>end)
			return null;
		tmp=new BinarySearchTreeNode();
		if(start==end) {
			tmp.setData(start);
			tmp.setLeft(null);
			tmp.setRight(null);
		}else {
			int mid=start+(end-start)/2;
			tmp.setData(A[mid]);
			tmp.setLeft(buildBSTFromSortedArray(A, start, mid-1));
			tmp.setRight(buildBSTFromSortedArray(A, mid+1, end));
		}
		return tmp;
	}
	
	static int count =0;
	
	static BinarySearchTreeNode kthSmallestElement(BinarySearchTreeNode root,int k) {
		if(root==null)
			return null;
		BinarySearchTreeNode left = kthSmallestElement(root.getLeft(), k);
		if(left != null)
			return left;
		count++;
		if (count == k)
			return root;
		return kthSmallestElement(root.getRight(), k);
	}
	
	
	//floor -> largest number in BST which is less than or equal to the given number
	
	static BinarySearchTreeNode floor(BinarySearchTreeNode root,int data) {
		BinarySearchTreeNode prev = null;
		return floorUtil(root,prev,data);
	}
	
	static BinarySearchTreeNode floorUtil(BinarySearchTreeNode root,BinarySearchTreeNode prev,int data) {
		if (root==null)
			return null;
		if(floorUtil(root.getLeft(), prev, data)==null)
			return null;
		if(root.getData()>data)
			return prev;
		if(root.getData()==data)
			return root;
		prev=root;
		return floorUtil(root.getRight(), prev, data);
	}
	
	
	//ceil -> smallest number in BST which is greater than or equal to the given number
	
	static BinarySearchTreeNode ceil(BinarySearchTreeNode root,int data) {
		BinarySearchTreeNode prev = null;
		return ceilUtil(root,prev,data);
	}
	
	static BinarySearchTreeNode ceilUtil(BinarySearchTreeNode root,BinarySearchTreeNode prev,int data) {
		if (root==null)
			return null;
		if(ceilUtil(root.getLeft(), prev, data)==null)
			return null;
		if(root.getData()==data)
			return root;
		if(root.getData()<data)
			return prev;
		prev=root;
		return ceilUtil(root.getLeft(), prev, data);
	}
	
	
}


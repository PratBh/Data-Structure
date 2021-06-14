import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumHeightTree {
	public static void main(String[] args) {
		MinimumHeightTree mht=new MinimumHeightTree();
		mht.findMHT(4, new int[][] {{1,0},{1,2},{1,3}});
	}
	public List<Integer> findMHT(int n , int[][] edges){
		if(n==0)
			return Collections.singletonList(0);
		
		List<Set<Integer>> adj=new ArrayList<>();//making adjacency list from given edges
		for(int i=0;i<n;i++) {
			adj.add(new HashSet<Integer>());
		}
		
		for(int[] edge:edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		
		List<Integer> leaves=new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			if(adj.get(i).size()==1)
				leaves.add(i);
		}
		
		while(n>2) {
			n=n-leaves.size();
			List<Integer> newLeaves=new ArrayList<Integer>();
			for(int leave:leaves) {
				int node = adj.get(leave).iterator().next();
				adj.get(node).remove(leave);
				if(adj.get(node).size()==1)
					newLeaves.add(node);
			}
			leaves=newLeaves;
		}
		return leaves;
	}
}


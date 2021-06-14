import java.util.HashMap;
import java.util.Map;
/**
 * @author tusroy
 * Date 06/20/2015
 *  
 * Video link - https://youtu.be/ID00PMy0-vE
 *  
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet
 * 2) union
 * 3) findSet
 * 
 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is 
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.
 */
public class DisjointSet {
	Map<Long,Node> hm=new HashMap<Long,Node>();
	
	class Node{
		long data;
		Node parent;
		int rank;
	}
	/**
     * Create a set with only one element.
     */
	public void makeSet(long data) {
		Node node=new Node();
		node.data=data;
		node.parent=node;
		node.rank=0;
		hm.put(data, node);
	}
	 /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
	public boolean union(long data1, long data2) {
		Node node1=hm.get(data1);
		Node node2=hm.get(data2);
		
		Node parent1=findSet(node1);
		Node parent2=findSet(node2);
		//if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }
      //else whoever's rank is higher becomes parent of other
		if(parent1.rank >= parent2.rank) {

            //increment rank only if both sets have same rank
			parent1.rank = (parent1.rank == parent2.rank)?parent1.rank+1:parent1.rank;
			parent2.rank=parent1.rank;
		}else {
			parent1.rank=parent2.rank;
		}
		return true;
	}
	/**
     * Finds the representative of this set
     */
	public long findSet(long data) {
		return findSet(hm.get(data)).data;
	}
	/**
     * Find the representative recursively and does path
     * compression as well.
     */
	public Node findSet(Node node) {
		Node parent=node.parent;
		if(parent==node)
			return parent;
		node.parent=findSet(node.parent);
		return node.parent;
	}
	
	public boolean hasCycleDisjoint(Graph<Integer> graph) {
		DisjointSet ds=new DisjointSet();
		for(VertexNew vertex:graph.getAllVertexNew()) {
			ds.makeSet(vertex.getId());
		}
		
		for(Edge edge:graph.getAllEdges()) {
			long parent1=ds.findSet(edge.getVertexNew1().getId());
			long parent2=ds.findSet(edge.getVertexNew2().getId());
			
			if(parent1 == parent2)
				return true;
			ds.union(edge.getVertexNew1().getId(),edge.getVertexNew2().getId());
		}
		
		return false;
	}
}


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class TopologicalSort {
	public Deque<VertexNew<Integer>> topSort(Graph<Integer> graph) {
	Deque<VertexNew<Integer>> st=new ArrayDeque<>();
	Set<VertexNew<Integer>> visited = new HashSet<>();
	for(VertexNew<Integer> vertex:graph.getAllVertexNew()) {
		if(visited.contains(vertex))
			continue;
		topSortUtil(vertex, st, visited);	
		}
		return st;
	}
	
	private void topSortUtil(VertexNew vertex, Deque<VertexNew<Integer>> stack,
			Set<VertexNew<Integer>> visited) {
		visited.add(vertex);
		for(VertexNew<Integer> child : vertex.getAdjacentVertexNewes()) {
			if(visited.contains(child))
				continue;
			topSortUtil(child, stack, visited);
		}
		stack.offerFirst(vertex);
	}
}


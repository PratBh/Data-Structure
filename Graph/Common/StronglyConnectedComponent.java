

//interview/src/com/interview/graph/StronglyConnectedComponent.java
//
//Tushar Roy Cleaning up code for Strongly connected components



import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date 10/01/2014
 * @author Tushar Roy
 *
 * Given a directed graph, find all strongly connected components in this graph.
 * We are going to use Kosaraju's algorithm to find strongly connected component.
 *
 * Algorithm
 * Create a order of vertices by finish time in decreasing order.
 * Reverse the graph
 * Do a DFS on reverse graph by finish time of vertex and created strongly connected
 * components.
 *
 * Runtime complexity - O(V + E)
 * Space complexity - O(V)
 *
 * References
 * https://en.wikipedia.org/wiki/Strongly_connected_component
 * http://www.geeksforgeeks.org/strongly-connected-components/
 */
public class StronglyConnectedComponent {

    public List<Set<VertexNew<Integer>>> scc(Graph<Integer> graph) {

        //it holds vertices by finish time in reverse order.
        Deque<VertexNew<Integer>> stack = new ArrayDeque<>();
        //holds visited vertices for DFS.
        Set<VertexNew<Integer>> visited = new HashSet<>();

        //populate stack with vertices with vertex finishing last at the top.
        for (VertexNew<Integer> vertex : graph.getAllVertexNew()) {
            if (visited.contains(vertex)) {
                continue;
            }
            DFSUtil(vertex, visited, stack);
        }

        //reverse the graph.
        Graph<Integer> reverseGraph = reverseGraph(graph);

        //Do a DFS based off vertex finish time in decreasing order on reverse graph..
        visited.clear();
        List<Set<VertexNew<Integer>>> result = new ArrayList<>();
        while (!stack.isEmpty()) {
        	VertexNew<Integer> vertex = reverseGraph.getVertexNew(stack.poll().getId());
            if(visited.contains(vertex)){
                continue;
            }
            Set<VertexNew<Integer>> set = new HashSet<>();
            DFSUtilForReverseGraph(vertex, visited, set);
            result.add(set);
        }
        return result;
    }

    private Graph<Integer> reverseGraph(Graph<Integer> graph) {
        Graph<Integer> reverseGraph = new Graph<>(true);
        for (Edge<Integer> edge : graph.getAllEdges()) {
            reverseGraph.addEdge(edge.getVertexNew1().getId(), edge.getVertexNew2()
                    .getId(), edge.getWeight());
        }
        return reverseGraph;
    }

    private void DFSUtil(VertexNew<Integer> vertex,
            Set<VertexNew<Integer>> visited, Deque<VertexNew<Integer>> stack) {
        visited.add(vertex);
        for (VertexNew<Integer> v : vertex.getAdjacentVertexNewes()) {
            if (visited.contains(v)) {
                continue;
            }
            DFSUtil(v, visited, stack);
        }
        stack.offerFirst(vertex);
    }

    private void DFSUtilForReverseGraph(VertexNew<Integer> vertex,
                                        Set<VertexNew<Integer>> visited, Set<VertexNew<Integer>> set) {
        visited.add(vertex);
        set.add(vertex);
        for (VertexNew<Integer> v : vertex.getAdjacentVertexNewes()) {
            if (visited.contains(v)) {
                continue;
            }
            DFSUtilForReverseGraph(v, visited, set);
        }
    }

    public static void main(String args[]){
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(5, 6);

        StronglyConnectedComponent scc = new StronglyConnectedComponent();
        List<Set<VertexNew<Integer>>> result = scc.scc(graph);

        //print the result
        result.forEach(set -> {
            set.forEach(v -> System.out.print(v.getId() + " "));
            System.out.println();
        });
    }
}


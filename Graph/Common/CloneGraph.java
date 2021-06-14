
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

//Given a reference of a node in a connected undirected graph.
//
//Return a deep copy (clone) of the graph.
//
//Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
//
//class Node {
//    public int val;
//    public List<Node> neighbors;
//}
// 
//
//Test case format:
//
//For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
//
//Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
//
//The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
//


class GraphNode 
{ 
    int val; 
  
    // A neighbour Vector which contains references to 
    // all the neighbours of a GraphNode 
    Vector<GraphNode> neighbours; 
    public GraphNode(int val) 
    { 
        this.val = val; 
        neighbours = new Vector<GraphNode>(); 
    } 
} 
  
class GraphClone
{ 
    // A method which clones the graph and 
    // returns the reference of new cloned source node 
    public GraphNode cloneGraph(GraphNode source) 
    { 
        Queue<GraphNode> q = new LinkedList<GraphNode>(); 
        q.add(source); 
  
        // An HashMap to keep track of all the 
        // nodes which have already been created 
        HashMap<GraphNode,GraphNode> hm = 
                        new HashMap<GraphNode,GraphNode>(); 
  
        //Put the node into the HashMap 
        hm.put(source,new GraphNode(source.val)); 
  
        while (!q.isEmpty()) 
        { 
            // Get the front node from the queue 
            // and then visit all its neighbours 
            GraphNode u = q.poll(); 
  
            // Get corresponding Cloned Graph Node 
            GraphNode cloneNodeU = hm.get(u); 
            if (u.neighbours != null) 
            { 
                Vector<GraphNode> v = u.neighbours; 
                for (GraphNode graphNode : v) 
                { 
                    // Get the corresponding cloned node 
                    // If the node is not cloned then we will 
                    // simply get a null 
                    GraphNode cloneNodeG = hm.get(graphNode); 
  
                    // Check if this node has already been created 
                    if (cloneNodeG == null) 
                    { 
                        q.add(graphNode); 
  
                        // If not then create a new Node and 
                        // put into the HashMap 
                        cloneNodeG = new GraphNode(graphNode.val); 
                        hm.put(graphNode,cloneNodeG); 
                    } 
  
                    // add the 'cloneNodeG' to neighbour 
                    // vector of the cloneNodeG 
                    cloneNodeU.neighbours.add(cloneNodeG); 
                } 
            } 
        } 
  
        // Return the reference of cloned source Node 
        return hm.get(source); 
    
    } 
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Graph_AdjacencyList {
	private int vertices;
	private ArrayList<Integer> [] adj;//adjacency list i.e array of arrayList
	
	@SuppressWarnings("unchecked")
	public Graph_AdjacencyList(int vertices) {
		this.vertices=vertices;
		adj= new ArrayList [vertices];
		for(int i=0;i<vertices;i++) {
			adj[i]=new ArrayList<Integer>();//create one arraylist for each vertices
		}
	}
	
	public void addEdge(int u,int v) {
		adj[u].add(v);
		adj[v].add(u);
	}
	
	public void removeEdge(int u,int v) {
		adj[u].remove(v);
		adj[v].remove(u);
	}
	
	//Number of Connected Components in an Undirected Graph
	public void connectedComponent(Graph_AdjacencyList graph) {
		boolean[] visited = new boolean[vertices]; 
		for(int v=0;v<graph.vertices;v++) {
			if(!visited[v]) {
				dfsUtil(v,visited,graph);
			}
		}
	}
	
	public void dfsUtil(int v,boolean[]visited,Graph_AdjacencyList graph) {
		visited[v]=true;
		System.out.print(v+" ");
		for(int x:graph.adj[v]) {
			if(!visited[x]) dfsUtil(x,visited,graph);
		}
	}
	
	/* The main function that print Eulerian Trail.  
    It first finds an odd degree vertex (if there  
    is any) and then calls printEulerUtil() to 
    print the path */
	//https://www.geeksforgeeks.org/fleurys-algorithm-for-printing-eulerian-path/
	public void printEulerTour() {
		int u=0;
		for(int i=0;i<vertices;i++) {
			if(adj[i].size() % 2 ==1) { //find the vertex with odd number of edges
				u=i;
				break;
			}
		}
		// Print tour starting from oddv 
        printEulerUtil(u); 
	}
	
	private void printEulerUtil(int u) {
		for(int i=0;i<adj[u].size();i++) {
			int v=adj[u].get(i);
			if (isValidNextEdge(u, v))  
            { 
                System.out.print(u + "-" + v + " "); 
                  
                // This edge is used so remove it now 
                removeEdge(u, v);  
                printEulerUtil(v); 
            } 
		}
	}
	
	// The function to check if edge u-v can be 
    // considered as next edge in Euler Tout
	private boolean isValidNextEdge(int u, int v) {
		// The edge u-v is valid in one of the 
        // following two cases: 
  
        // 1) If v is the only adjacent vertex of u  
        // i.e size of adjacent vertex list is 1
		if(adj[u].size()==1)
			return true;
		
		// 2) If there are multiple adjacents, then 
        // u-v is not a bridge Do following steps  
        // to check if u-v is a bridge 
        // 2.a) count of vertices reachable from u
		boolean[] isVisited = new boolean[this.vertices];
		int count1=dfsCount(u,isVisited);
		removeEdge(u, v);
		int count2 = dfsCount(u, isVisited);
		// 2.c) Add the edge back to the graph 
        addEdge(u, v); 
        return (count1 > count2) ? false : true;
	}
	
	// A DFS based function to count reachable 
    // vertices from v 
	private int dfsCount(int v,boolean [] isVisited) {
		// Mark the current node as visited 
        isVisited[v] = true;
        int count = 1;
     // Recur for all vertices adjacent to this vertex 
        for(int adj : adj[v]) {
        	if(!isVisited[adj]) {
        		count = count + dfsCount(adj, isVisited);
        	}
        }
        
        return count;
	}
	
}


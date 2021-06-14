
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
	class Node implements Comparator<Node>{
		int node;
		int cost;
		public Node(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compare(Node o1, Node o2) {
			if(o1.cost<o2.cost)
				return -1;
			else
				return 1;
		}
	}
	
	public void spg(List<List<Node>> adjList,int src) {
		Set<Integer> visited=new HashSet<Integer>();
		Node source=new Node(src, 0);
		PriorityQueue<Node> pq=new PriorityQueue<Dijkstra.Node>();
		pq.add(source);
		int dist[]=new int[adjList.size()];
		for(int i=0;i<dist.length;i++)
			dist[i]=Integer.MAX_VALUE;
		dist[src]=0;
		while(visited.size()!=adjList.size()) {
			int u;
			if(!visited.contains(pq.peek().node)) {
				u=pq.remove().node;
				visited.add(u);
				for(int i=0;i<adjList.get(u).size();i++) {
					int edgeDist=-1,newDist=-1;
					Node v=adjList.get(u).get(i);
					if(!visited.contains(v.node)) {
						edgeDist=v.cost;
						newDist=edgeDist+dist[u];
						if(newDist<dist[v.node])
							dist[v.node]=newDist;
						pq.add(new Node(v.node, dist[v.node]));
					}
				}
			}
		}
	}
}

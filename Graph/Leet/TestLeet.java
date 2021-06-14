package leet;

import java.util.ArrayList;
import java.util.List;

public class TestLeet {

	public static void main(String[] args) {
		List<List<Integer>> cost=new ArrayList<List<Integer>>();
		cost.add(new ArrayList<Integer>());
		cost.add(new ArrayList<Integer>());
		cost.get(0).add(15);
		cost.get(0).add(96);
		cost.get(1).add(36);
		cost.get(1).add(2);
		//BFS.connectTwoGroups(cost);
		int [][] edges= {{1,3}, {0,2},{1,3}, {0,2}};
		//BFS.findRedundantConnection(edges);
		//BFS.isBipartite(edges);
		
		int [][] graph= {{1,2},{2,3},{5},{0},{5},{},{}};
		//BFS.eventualSafeNodes(graph);
		List<List<Integer>> l1= new ArrayList<List<Integer>>();
		List<Integer> ll=new ArrayList<Integer>();
		ll.add(1);
		ll.add(3);
		l1.add(ll);
		List<Integer> lm=new ArrayList<Integer>();
		lm.add(1);
		lm.add(3);
		lm.add(0);
		l1.add(lm);
		List<Integer> ln=new ArrayList<Integer>();
		ln.add(2);
		l1.add(ln);
		List<Integer> lk=new ArrayList<Integer>();
		lk.add(0);
		l1.add(lk);
		//BFS.canVisitAllRooms(l1);
		//BFS.kSimilarity("abc", "bca");
		
		String[] sArr= {"a==b","b!=c","c==a"};
		BFS bfs=new BFS();
		//bfs.equationsPossible(sArr);
		int [][] connections= {{0,1},{0,2},{1,2}};
		//bfs.makeConnected(4, connections);
		//bfs.countSubTrees(7, edges, "abaedcd");
		
		DFS dfs=new DFS();
		
		int [] nums= {1,1,2,2,2};
		dfs.makesquare(nums);
	}

}


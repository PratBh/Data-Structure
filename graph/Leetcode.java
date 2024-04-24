package graph;

import java.util.*;

public class Leetcode {
//	Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
//
//			An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume 
//			all four edges of the grid are all surrounded by water.
//
//			 
//
//			Example 1:
//
//			Input: grid = [
//			  ["1","1","1","1","0"],
//			  ["1","1","0","1","0"],
//			  ["1","1","0","0","0"],
//			  ["0","0","0","0","0"]
//			]
//			Output: 1
//			Example 2:
//
//			Input: grid = [
//			  ["1","1","0","0","0"],
//			  ["1","1","0","0","0"],
//			  ["0","0","1","0","0"],
//			  ["0","0","0","1","1"]
//			]
//			Output: 3
//			 
//
//			Constraints:
//
//			m == grid.length
//			n == grid[i].length
//			1 <= m, n <= 300
//			grid[i][j] is '0' or '1'.
	
public int numIslands(char[][] grid) {
       int m=grid.length,n=grid[0].length,count=0;
       boolean [][]visited = new boolean[m][n];
       Queue<int[]> q =new LinkedList<int[]>();
       for(int i=0;i<m;i++) {
    	   for(int j=0;j<n;j++) {
    		   if(!visited[i][j] && grid[i][j]=='1') {
    			   int[]arr = new int[2];
    			   arr[0]=i;arr[1]=j;
    			   visited[i][j]=true;
    			   q.add(arr);
    			   while(!q.isEmpty()) {
    				   int[]curr=q.poll();
    				   int row=curr[0],col=curr[1];
    				   if(row+1<m && row+1>=0 && !visited[row+1][col] && grid[row+1][col]=='1') {
    					   int []temp=new int[2];
    					   temp[0]=row+1;temp[1]=col;
    					   visited[row+1][col]=true;
    					   q.add(temp);
    				   }
    				   if(row-1<m && row-1>=0 && !visited[row-1][col] && grid[row-1][col]=='1') {
    					   int []temp=new int[2];
    					   temp[0]=row-1;temp[1]=col;
    					   visited[row-1][col]=true;
    					   q.add(temp);
    				   }
    				   if(col+1<n && col+1>=0 && !visited[row][col+1] && grid[row][col+1]=='1') {
    					   int []temp=new int[2];
    					   temp[0]=row;temp[1]=col+1;
    					   visited[row][col+1]=true;
    					   q.add(temp);
    				   }
    				   if(col-1<n && col-1>=0 && !visited[row][col-1] && grid[row][col-1]=='1') {
    					   int []temp=new int[2];
    					   temp[0]=row;temp[1]=col-1;
    					   visited[row][col-1]=true;
    					   q.add(temp);
    				   }
    
    			   }
    			   count++;
    		   }
    	   }
       }
       return count;
    }

//Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
//
//A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
// 
//
//Example 1:
//
//
//Input: board = [

//["X","X","X","X"],
//["X","O","O","X"],
//["X","X","O","X"],
//["X","O","X","X"]
//		]
//Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//Explanation: Notice that an 'O' should not be flipped if:
//- It is on the border, or
//- It is adjacent to an 'O' that should not be flipped.
//The bottom 'O' is on the border, so it is not flipped.
//The other three 'O' form a surrounded region, so they are flipped.
//Example 2:
//
//Input: board = [["X"]]
//Output: [["X"]]

	public void solve(char[][] board) {
		if(board.length==0||board[0].length==0)
			return;
		int m=board.length,n=board[0].length;
		for(int i=0;i<m;i++) {
			if(board[i][0]=='0')
				boundaryDFS(board, i, 0);
			if(board[i][n-1]=='0')
				boundaryDFS(board, i, n-1);
		}
		
		for(int j=0;j<n;j++) {
			if(board[0][j]=='0')
				boundaryDFS(board, 0, j);
			if(board[m-1][j]=='0')
				boundaryDFS(board, m-1, j);
		}
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(board[i][j]=='0')
					board[i][j]='X';
				if(board[i][j]=='*')
					board[i][j]='0';
			}
		}
	}
	
	public void boundaryDFS(char[][] board,int row,int col) {
		if(row>board.length-1||row<0||col>board[0].length||col<0)
			return;
		if(board[row][col]=='0')
			board[row][col]='*';
		
		if(row>0 && board[row-1][col]=='0')
			boundaryDFS(board, row-1, col);
		
		if(col>0 && board[row][col-1]=='0')
			boundaryDFS(board, row, col-1);
		
		if(row<board.length-1 && board[row+1][col]=='0')
			boundaryDFS(board, row+1, col);
		
		if(col<board[0].length && board[row][col+1]=='0')
			boundaryDFS(board, row, col+1);
	}
	
//	Given a reference of a node in a connected undirected graph.
//
//	Return a deep copy (clone) of the graph.
//
//	Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
//
//	class Node {
//	    public int val;
//	    public List<Node> neighbors;
//	}
//	 
//
//	Test case format:
//
//	For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on.
//	The graph is represented in the test case using an adjacency list.
//
//	An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
//
//	The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
//
//	 
//
//	Example 1:
//
//
//	Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//	Output: [[2,4],[1,3],[2,4],[1,3]]
//	Explanation: There are 4 nodes in the graph.
//	1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//	2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//	3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//	4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
	class Node {
	    public int val;
	    public List<Node> neighbors;
	    public Node() {
	        val = 0;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val) {
	        val = _val;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val, ArrayList<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	}
	//https://leetcode.com/problems/clone-graph/solutions/1792834/c-easy-explanation-dfs/?envType=study-plan-v2&envId=top-interview-150
	public Node cloneGraph(Node node) {
        return cloneGraphUtil(node, new HashMap<Node, Node>());
    }
	public Node cloneGraphUtil(Node node,Map<Node,Node> mp) {
		if(node == null)
			return null;
		if(mp.containsKey(node))
			return mp.get(node);
		Node root = new Node(node.val);
		mp.put(node, root);
		for(Node nd:node.neighbors)
			root.neighbors.add(cloneGraphUtil(nd, mp));
		return root;
	}
	
//	You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
//
//			You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
//
//			Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//			Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
//
//			Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
//
//			 
//
//			Example 1:
//
//			Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//			Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//			Explanation: 
//			Given: a / b = 2.0, b / c = 3.0
//			queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
//			return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//			note: x is undefined => -1.0
//			Example 2:
//
//			Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//			Output: [3.75000,0.40000,5.00000,0.20000]
//			Example 3:
//
//			Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//			Output: [0.50000,2.00000,-1.00000,-1.00000]
//			 
//
//			Constraints:
//
//			1 <= equations.length <= 20
//			equations[i].length == 2
//			1 <= Ai.length, Bi.length <= 5
//			values.length == equations.length
//			0.0 < values[i] <= 20.0
//			1 <= queries.length <= 20
//			queries[i].length == 2
//			1 <= Cj.length, Dj.length <= 5
//			Ai, Bi, Cj, Dj consist of lower case English letters and digits.
	
	//https://www.youtube.com/watch?v=UcDZM6Ap5P4
	class HelperNode{
		String key;
		double val;
		
		public HelperNode(String k,double v) {
			this.key=k;
			this.val=v;
		}
	}
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String,List<HelperNode>> g = buildGraph(equations, values);
		double[]res = new double[queries.size()];
		for(int i=0;i<queries.size();i++)
			res[i]=dfsDivision(queries.get(i).get(1), queries.get(i).get(0), g, new HashSet<String>());
		return res;
    }
	private Map<String,List<HelperNode>> buildGraph(List<List<String>> equations, double[] values){
		Map<String,List<HelperNode>> g =new HashMap<String, List<HelperNode>>();
		for(int i=0;i<values.length;i++) {
			String src = equations.get(i).get(0);
			String des = equations.get(i).get(1);
			g.putIfAbsent(src, new ArrayList<Leetcode.HelperNode>());
			g.putIfAbsent(des, new ArrayList<Leetcode.HelperNode>());
			g.get(src).add(new HelperNode(des, values[i]));
			g.get(des).add(new HelperNode(src,1/values[i]));
		}
		return g;
	}
	
	private double dfsDivision(String des,String src,Map<String,List<HelperNode>> g,HashSet<String> visited) {
		if(!g.containsKey(src)|| !g.containsKey(des))
			return -1.0;
		if(src.equals(des))
			return 1.0;
		visited.add(src);
		for(HelperNode ng:g.get(src)) {
			if(!visited.contains(ng.key)) {
				double ans = dfsDivision(des, ng.key, g, visited);
				if(ans!=-1.0)
					return ans*ng.val;
			}
		}
		return -1.0;
	}
	
//	There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an 
//	array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//			For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//			Return true if you can finish all courses. Otherwise, return false.
//
//			 
//
//			Example 1:
//
//			Input: numCourses = 2, prerequisites = [[1,0]]
//			Output: true
//			Explanation: There are a total of 2 courses to take. 
//			To take course 1 you should have finished course 0. So it is possible.
//			Example 2:
//
//			Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//			Output: false
//			Explanation: There are a total of 2 courses to take. 
//			To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
	
	//TLE
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer,List<Integer>> graph = buildAdjList(numCourses, prerequisites);
		boolean[] visited = new boolean[numCourses];
		Arrays.fill(visited, false);
		boolean flag = false;
		for (Integer key : graph.keySet()) {
			visited[key] = true;
			for(int i=0;i<graph.get(key).size();i++) {
				flag = isCycle(graph, graph.get(key).get(i), visited);
				if(flag==true)
					return false;
			}
			visited[key]=false;
		}
		return true;
    }
	
	Map<Integer,List<Integer>> buildAdjList(int numCourses, int[][] prerequisites){
		Map<Integer,List<Integer>> g = new HashMap<>();
		for(int i=0;i<prerequisites.length;i++) {
			if(!g.containsKey(prerequisites[i][0]))
				g.put(prerequisites[i][0], new ArrayList<>());
			g.get(prerequisites[i][0]).add(prerequisites[i][1]);
		}
		return g;
	}
	
	boolean isCycle(Map<Integer,List<Integer>> graph,int src,boolean []visited) {
		if(visited[src]==true)
			return true;
		visited[src]=true;
		boolean flag = false;
		if(graph.containsKey(src)) {
		for(int i=0;i<graph.get(src).size();i++) {
			flag = isCycle(graph, graph.get(src).get(i), visited);
			if(flag==true)
				return true;
			}
		}
		visited[src]=false;
		return false;
		
	}
	
	//TLE
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		ArrayList<Integer>[] adj = new ArrayList[numCourses];
		for(int i=0;i<numCourses;i++)
			adj[i]=new ArrayList<Integer>();
		for(int i=0;i<prerequisites.length;i++)
			adj[prerequisites[i][0]].add(prerequisites[i][1]);
		boolean[] visited = new boolean[numCourses];
		for(int i=0;i<numCourses;i++) {
			if(!visited[i]&&!courseDfs(adj, visited, i))
				return false;
		}
		return true;
    }
	boolean courseDfs(ArrayList<Integer>[] adj,boolean[] visited,int src) {
		if(visited[src]==true)
			return false;
		visited[src]=true;
		for(int v:adj[src]) {
			if(!courseDfs(adj, visited, v))
				return false;
		}
		visited[src]=false;
		return true;
	}
	
	//using topological sort(using inorder array and queue).No TLE
	public boolean canFinishTopologicalSort(int numCourses, int[][] prerequisites) {
		List<List<Integer>> g =new ArrayList<List<Integer>>();
		int[]inDegree=new int[numCourses];
		for(int i=0;i<numCourses;i++)
			g.add(new ArrayList<Integer>());
		for(int [] pre:prerequisites) {
			g.get(pre[0]).add(pre[1]);
			inDegree[pre[1]]++;
		}
		Queue<Integer>q=new LinkedList<Integer>();
		for(int i=0;i<numCourses;i++) {
			if(inDegree[i]==0)
				q.add(i);
		}
		List<Integer> res=new ArrayList<Integer>();
		while(!q.isEmpty()) {
			int curr=q.poll();
			res.add(curr);
			for(int i=0;i<g.get(curr).size();i++) {
				int temp = g.get(curr).get(i);
				inDegree[temp]--;
				if(inDegree[temp]==0)
					q.add(temp);
			}
		}
		return res.size()==numCourses;
    }
	
//	There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
//	prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//			For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//			Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of 
//					them. If it is impossible to finish all courses, return an empty array.
//
//			 
//
//			Example 1:
//
//			Input: numCourses = 2, prerequisites = [[1,0]]
//			Output: [0,1]
//			Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct
//			course order is [0,1].
//			Example 2:
//
//			Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//			Output: [0,2,1,3]
//			Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
//	Both courses 1 and 2 should be taken after you finished course 0.
//			So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
//			Example 3:
//
//			Input: numCourses = 1, prerequisites = []
//			Output: [0]
	
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<List<Integer>> adj = new ArrayList<List<Integer>>();
		for(int i=0;i<numCourses;i++)
			adj.add(new ArrayList<Integer>());
		int[] inOrder = new int[numCourses];
		for(int []pre:prerequisites) {
			adj.get(pre[0]).add(pre[1]);
			inOrder[pre[1]]++;
		}
		Queue<Integer>q=new LinkedList<Integer>();
		for(int i=0;i<numCourses;i++) {
			if(inOrder[i]==0)
				q.add(i);
		}
		List<Integer> topoOrder = new ArrayList<Integer>();
		while(!q.isEmpty()) {
			int curr = q.poll();
			topoOrder.add(curr);
			for(int i=0;i<adj.get(curr).size();i++) {
				inOrder[adj.get(curr).get(i)]--;
				if(inOrder[adj.get(curr).get(i)]==0)
					q.add(adj.get(curr).get(i));
			}
		}
		if(topoOrder.size()!=numCourses)
			return new ArrayList<Integer>().stream().mapToInt(Integer::intValue).toArray();
		Collections.reverse(topoOrder);
		int[]a= topoOrder.stream().mapToInt(Integer::intValue).toArray();
		return a;
    }
	
//	You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of 
//	the board (i.e. board[n - 1][0]) and alternating direction each row.
//
//	You start on square 1 of the board. In each move, starting from square curr, do the following:
//
//	Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
//	This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
//	If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
//	The game ends when you reach the square n2.
//	A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. 
//	Squares 1 and n2 do not have a snake or ladder.
//
//	Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the start of another snake or ladder, 
//	you do not follow the subsequent snake or ladder.
//
//	For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3,
//	but do not follow the subsequent ladder to 4.
//	Return the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.
//
//	 
//
//	Example 1:
//
//
//	Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
//	Output: 4
//	Explanation: 
//	In the beginning, you start at square 1 (at row 5, column 0).
//	You decide to move to square 2 and must take the ladder to square 15.
//	You then decide to move to square 17 and must take the snake to square 13.
//	You then decide to move to square 14 and must take the ladder to square 35.
//	You then decide to move to square 36, ending the game.
//	This is the lowest possible number of moves to reach the last square, so return 4.
//	Example 2:
//
//	Input: board = [[-1,-1],[-1,3]]
//	Output: 1
	
	public int snakesAndLadders(int[][] board) {
        int n=board.length*board.length,moves=0,c=0,k=0;
    int [] flatArr = new int[n];
    for(int i=board.length-1;i>=0;i--) {
 	   if(c%2!=0)
 		   reverse(board[i]);
 	   for(int j:board[i]) {
 		   flatArr[k]=j==-1?k:j-1;
 		   k++;
 	   }
 	   c++;
    }
    boolean[]visited =new boolean[n];
    Queue<Integer> q =new LinkedList<Integer>();
    q.add(0);
    visited[0]=true;
    while(!q.isEmpty()) {
 	   int len=q.size();
 	   moves++;
 	   while(len>0) {
 	   int curr = q.poll();
 	   for(int i=curr+1;i<=(Math.min(curr+6, n-1));i++) {
 		   if(!visited[i]) {
 			   visited[i]=true;
 			   q.add(flatArr[i]);  
 			   if(flatArr[i]==n-1)
 				   return moves; 
 		    }
 	   }
 	   len--;
 	}
   }
    return -1;
     
 }
	
	private void reverse(int []a) {
		int i=0,j=a.length-1;
		while(j>i) {
			int temp=a[i];
			a[i]=a[j];
			a[j]=temp;
			i++;j--;
		}
	}
	
//	A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
//
//	Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is 
//	defined as one single character changed in the gene string.
//
//	For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
//	There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
//
//	Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to 
//			mutate from startGene to endGene. If there is no such a mutation, return -1.
//
//	Note that the starting point is assumed to be valid, so it might not be included in the bank.
//
//	 
//
//	Example 1:
//
//	Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
//	Output: 1
//	Example 2:
//
//	Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
//	Output: 2
//	 
//
//	Constraints:
//
//	0 <= bank.length <= 10
//	startGene.length == endGene.length == bank[i].length == 8
//	startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
	
	//TLE
//	public int minMutation(String startGene, String endGene, String[] bank) {
//        HashSet< String> set = new HashSet<String>();
//        for(String st:bank)
//        	set.add(st);
//        int moves=0;
//        StringBuffer curr = new StringBuffer(startGene);
//        while(!curr.toString().equals(endGene)) {
//        for(int i=0;i<startGene.length();i++) {
//        	if(curr.charAt(i)!=endGene.charAt(i)) {
//        		StringBuffer sb = new StringBuffer(curr);
//        		char c=endGene.charAt(i);
//        		sb = new StringBuffer(curr.substring(0,i)).append(c).append(curr.substring(i+1));
//        		if(set.contains(sb.toString())) {
//        			moves++;
//        			curr=sb;
//        			break;
//        		}
//        	}
//        }
//        }
//        return moves;
//    }
	//https://leetcode.com/problems/minimum-genetic-mutation/solutions/2771303/java-graph-and-bfs/?envType=study-plan-v2&envId=top-interview-150
	 private Map<String, List<String>> graph = new HashMap<>();

	    public int minMutation(String start, String end, String[] bank) {
	        // Early exit condition
	        if (!Arrays.asList(bank).contains(end)) return -1;

	        addWordVerticesToGraph(start);
	        // Seed the graph
	        for (String word : bank) {
	            addWordVerticesToGraph(word);
	        }

	        Set<String> visited = new HashSet<>();
	        visited.add(start);
	        
	        Queue<QueueElement> queue = new LinkedList<>();
	        queue.offer(new QueueElement(start, 0));

	        while (!queue.isEmpty()) {
	            QueueElement element = queue.poll();

	            if (element.word.equals(end)) {
	                return element.distance;
	            }

	            for (String vertex : generateWordVertices(element.word)) {
	                for (String adjacentVertex : graph.get(vertex)) {
	                    if (!visited.contains(adjacentVertex)) {
	                        visited.add(adjacentVertex);
	                        queue.offer(new QueueElement(adjacentVertex, element.distance + 1));
	                    }
	                }
	            }
	        }

	        return -1;

	    }

	    private List<String> generateWordVertices(String word) {
	       List<String> res =new ArrayList<String>();
	       for(int i=0;i<word.length();i++) {
	    	   String vertex = word.substring(0,i)+"#"+word.substring(i+1);
	    	   res.add(vertex);
	       }
	       return res;
	    }

	    private void addWordVerticesToGraph(String word) {
	        for(String v:generateWordVertices(word)) {
	        	if(!graph.containsKey(v))
	        		graph.put(v, new ArrayList<String>());
	        	graph.get(v).add(word);
	        }
	    }


	    private static class QueueElement {
	        private String word;
	        private int distance;

	        public QueueElement(String word, int distance) {
	            this.word = word;
	            this.distance = distance;
	        }
	    }
	    
//	    A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
//
//	    	Every adjacent pair of words differs by a single letter.
//	    	Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//	    	sk == endWord
//	    	Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from 
//	    			beginWord to endWord, or 0 if no such sequence exists.
//
//	    	 
//
//	    	Example 1:
//
//	    	Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//	    	Output: 5
//	    	Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
//	    	Example 2:
//
//	    	Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//	    	Output: 0
//	    	Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
//	    	 
//
//	    	Constraints:
//
//	    	1 <= beginWord.length <= 10
//	    	endWord.length == beginWord.length
//	    	1 <= wordList.length <= 5000
//	    	wordList[i].length == beginWord.length
//	    	beginWord, endWord, and wordList[i] consist of lowercase English letters.
//	    	beginWord != endWord
//	    	All the words in wordList are unique.
	    	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	    		if(!wordList.contains(endWord))
	    			return 0;
	    		int count = Integer.MAX_VALUE;
	    		addWordVerticesToGraph(beginWord);
	    		for(String st:wordList)
	    			addWordVerticesToGraph(st);
	    		Set<String> visited = new HashSet<String>();
	    		Queue<QueueElement>q=new LinkedList<Leetcode.QueueElement>();
	    		QueueElement ele=new QueueElement(beginWord, 0);
	    		visited.add(beginWord);
	    		q.add(ele);
	    		while(!q.isEmpty()) {
	    			QueueElement temp = q.poll();
	    			if(temp.word.equals(endWord)) {
	    				count=Math.min(count, temp.distance+1);
	    				visited.remove(endWord);
	    				
	    			}else {
	    				for(String ver:generateWordVertices(temp.word)) {
	    					for(String st:graph.get(ver)) {
	    						if(!visited.contains(st)) {
	    							visited.add(st);
	    							q.add(new QueueElement(st, temp.distance+1));
	    						}
	    					}
	    				}
	    			}
	    		}
	    		return count;
	    	}
}

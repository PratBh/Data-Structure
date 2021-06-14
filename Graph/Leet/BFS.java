package leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BFS {
//	Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
//
//	Only one letter can be changed at a time.
//	Each transformed word must exist in the word list.
//	Note:
//
//	Return 0 if there is no such transformation sequence.
//	All words have the same length.
//	All words contain only lowercase alphabetic characters.
//	You may assume no duplicates in the word list.
//	You may assume beginWord and endWord are non-empty and are not the same.
//	Example 1:
//
//		Input:
//		beginWord = "hit",
//		endWord = "cog",
//		wordList = ["hot","dot","dog","lot","log","cog"]
//
//		Output: 5
//
//		Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//		return its length 5.
//	https://www.youtube.com/watch?v=M9cVl4d0v04
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set=new HashSet<String>();
        for(String word:wordList) {
        	set.add(word);
        }
        
        if(set.contains(beginWord))
            set.remove(beginWord);
        
        if(!set.contains(endWord))
        	return 0;
        
        Queue<String> q=new LinkedList<String>();
        q.offer(beginWord);
        int level=1;
        while(!q.isEmpty()) {
        	int size=q.size();
        	for(int i=0;i<size;i++) {
        		String curr_word=q.poll();
            	char[] word_char=curr_word.toCharArray();
            	for(int j=0;j<word_char.length;j++) {
            		char orr_char=word_char[j];
            		for(char c='a';c<'z';c++) {
            			word_char[j]=c;
            			String new_word=String.valueOf(word_char);
            			if(new_word.equalsIgnoreCase(endWord)) return level+1;
            			if(set.contains(new_word)) {
            				q.offer(new_word);
            				set.remove(new_word);
            			}
            		}
            		word_char[j]=orr_char;
        	}
        		
        	}
        	level++;
        }
        
        return 0;
    }
	
//	Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
//
//	Only one letter can be changed at a time
//	Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
//	Note:
//
//	Return an empty list if there is no such transformation sequence.
//	All words have the same length.
//	All words contain only lowercase alphabetic characters.
//	You may assume no duplicates in the word list.
//	You may assume beginWord and endWord are non-empty and are not the same.
//	Example 1:
//
//	Input:
//	beginWord = "hit",
//	endWord = "cog",
//	wordList = ["hot","dot","dog","lot","log","cog"]
//
//	Output:
//	[
//	  ["hit","hot","dot","dog","cog"],
//	  ["hit","hot","lot","log","cog"]
//	]
	//https://www.youtube.com/watch?v=mIZJIuMpI2M
	List<List<String>> result=new ArrayList<List<String>>();
	
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		 HashSet<String> dict=new HashSet<String>();
	        for(String word:wordList) {
	        	dict.add(word);
	        }
	        HashMap<String, Set<String>> adj=new HashMap<String, Set<String>>();
	        Queue<String> q=new LinkedList<String>();
	        q.offer(beginWord);
	        HashMap<String, Integer> visited=new HashMap<>();
	        visited.put(beginWord, 0);
	        while(!q.isEmpty()) {
	        	String curr=q.peek();
	        	q.poll();
	        	String temp=curr;
	        	char[] word_char=temp.toCharArray();
	        	for(int i=0;i<word_char.length;i++) {
	        		for(char c='a';c<'z';c++) {
	        			if(word_char[i]==c)continue;
	        			word_char[i]=c;
	        			if(dict.contains(word_char)) {
	        				if(visited.get(word_char.toString())==0) {
	        					visited.put(word_char.toString(), visited.get(curr)+1);
	        					q.offer(word_char.toString());
	        					//adj.put(curr, new HashSet<String>());
	        					adj.get(curr).add(word_char.toString());
	        				}
	        			}
	        		}
	        		word_char[i]=curr.charAt(i);
	        	}
	        }
	        
	        Stack<String> st=new Stack<String>();
	        DFS(beginWord, endWord, st, adj);
	        return result;
	        
    }
	
	void DFS(String start,String end,Stack<String> st,HashMap<String, Set<String>> adj) {
		st.push(start);
		if(start.equals(end)) {
			result.add(st);
			st.pop();
			return;
		}
		for(String word:adj.get(start)) {
			DFS(word, end, st, adj);
		}
		st.pop();
	}
	
//	Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
//
//	A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//	Example:
//
//	X X X X
//	X O O X
//	X X O X
//	X O X X
//	After running your function, the board should be:
//
//	X X X X
//	X X X X
//	X X X X
//	X O X X
//	Explanation:
//
//	Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that 
//	is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent 
//	cells connected horizontally or vertically.
//	https://www.youtube.com/watch?v=ztTLGMeleco
	
	public void solve(char[][] board) {
		if(board.length==0 || board[0].length==0)
			return;
		
		int rows=board.length;
		int colimns=board[0].length;
		
		for(int i=0;i<rows;i++) {
			if(board[i][0]=='O') boundaryDFS(board, i, 0);
			if(board[i][colimns-1]=='O') boundaryDFS(board, i, colimns-1);
		}
		
		for(int j=0;j<colimns;j++) {
			if(board[0][j]=='O') boundaryDFS(board, 0, j);
			if(board[rows-1][j]=='O') boundaryDFS(board, rows-1, j);
		}
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<colimns;j++) {
				if(board[i][j]=='O')
					board[i][j]='X';
				if(board[i][j]=='*')
					board[i][j]='O';
			}
		}
		
		
	}
	
	public void boundaryDFS(char[][] board,int row,int column) {
		if(row>board.length-1||row<0 || column>board[0].length-1||column<0) return;
		if(board[row][column]=='O') {
			board[row][column]='*';
		}
		
		if(row>0 && board[row-1][column]=='O')
			boundaryDFS(board, row-1, column);
		
		if(row<board.length-1 && board[row+1][column]=='O')
			boundaryDFS(board, row+1, column);
		
		if(column>0 && board[row][column-1]=='O')
			boundaryDFS(board, row, column-1);
		
		if(column<board[0].length-1 && board[row][column+1]=='O')
			boundaryDFS(board, row, column+1);
	}
	
//	There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
//
//	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//	Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
//	 
//
//	Example 1:
//
//	Input: numCourses = 2, prerequisites = [[1,0]]
//	Output: true
//	Explanation: There are a total of 2 courses to take. 
//	             To take course 1 you should have finished course 0. So it is possible.
//	Example 2:
//
//	Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//	Output: false
//	Explanation: There are a total of 2 courses to take. 
//	             To take course 1 you should have finished course 0, and to take course 0 you should
//	             also have finished course 1. So it is impossible.
	//https://www.youtube.com/watch?v=iaaObeAEgxI
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
        	adj[i]=new ArrayList<Integer>();
        
        for(int[] pre:prerequisites)
        	adj[pre[0]].add(pre[1]);
        
        int[] visited=new int[numCourses];
        for(int i=0;i<numCourses;i++) {
        	if(visited[i]==0 && !courseDFS(adj, visited, i))
        		return false;
        }
        
        return true;
    }	
	
	public boolean courseDFS(ArrayList<Integer>[] adj,int[] visited,int ad) {
		if(visited[ad]==1)
			return false;//cycle
		
		visited[ad]=1;
		for(int v:adj[ad]) {
			if(!courseDFS(adj, visited, v))
				return false;
		}
		
		visited[ad]=2;
		return true;
	}
	
//	There are a total of n courses you have to take labelled from 0 to n - 1.
//
//	Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
//
//	Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//	If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
//
//	 
//
//	Example 1:
//
//	Input: numCourses = 2, prerequisites = [[1,0]]
//	Output: [0,1]
//	Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
//	Example 2:
//
//	Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//	Output: [0,2,1,3]
//	Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//	So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
	
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	ArrayList<Integer>[] adj = new ArrayList[numCourses];
    	int[] result=new int[numCourses];
        for(int i=0;i<numCourses;i++)
        	adj[i]=new ArrayList<Integer>();
        
        for(int[] pre:prerequisites)
        	adj[pre[1]].add(pre[0]);
        
        int[] visited=new int[numCourses];
        List<Integer> s=new LinkedList();
        for(int i=0;i<numCourses;i++) {
        	if(visited[i]==0 && courseDFSTotpSOrt(adj, visited, i, s))
        		return new int[0];
        }
        
        Collections.reverse(s);
        int [] resultInt=s.stream().mapToInt(Integer :: intValue).toArray();
        return resultInt;
    }
    
    public boolean courseDFSTotpSOrt(ArrayList<Integer>[] adj,int[] visited,int ad,List<Integer>s) {
    	if(visited[ad]==1)
    		return false;
    	visited[ad]=1;
    	for(int v:adj[ad]) {
    		if(visited[v]==1) return true;
    		if(visited[v]==0 && courseDFSTotpSOrt(adj, visited, v, s))
    			return true;
    	}
    	
    	visited[ad]=2;
    	s.add(ad);
    	return false;
    }
    
//    Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
//
//    Example 1:
//
//    Input: n = 12
//    Output: 3 
//    Explanation: 12 = 4 + 4 + 4.
//    Example 2:
//
//    Input: n = 13
//    Output: 2
//    Explanation: 13 = 4 + 9.
    //
    public int numSquares(int n) {
        int [] dp=new int[n+1];
        for(int i=1;i<=n;++i){
        	int min_val=i; //taken all 1s;
        	int j=1,sq=1;
        	while(sq<=i) {
        		min_val=Math.min(min_val, dp[i-sq]+1);
        		j++;
        		sq=j*j;
        	}
        	dp[i]=min_val;
        }
        return dp[n];
    }
    
//    You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, 
//    where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel 
//    to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires 
  //  the minimum effort.
//
//    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

    //https://leetcode.com/problems/path-with-minimum-effort/
    //https://www.youtube.com/watch?v=CPSJ0oFBMHg
    //dfs+binary search //https://www.youtube.com/watch?v=xrozg2SQpVA
   
    
    public int minimumEffortPath(int[][] heights) {
       int start=0;
       int end=Integer.MAX_VALUE;
       int n=heights.length;
		int m=heights[0].length;
       int [][]vis=new int [105][105];
       int[] [] dir={{1,0},{-1,0},{0,-1},{0,1}};
       while(start<end) {
    	   int mid=start+(end-start)/2;
    	   for(int[] v:vis)
    		   Arrays.fill(v, 0);
    	   ok(0, 0, heights, dir, vis, mid);
    	   if(vis[n-1][m-1]==1)
    		   end=mid;
    	   else
    		   start=mid+1;
       }
       
       return start;
    }
    
    void ok(int i,int j,int[][] heights,int[][] dir,int[][] vis,int effort) {
    	if(vis[i][j]==0) {
    		vis[i][j]=1;
    		int n=heights.length;
    		int m=heights[0].length;
    		for(int k=0;k<4;k++) {
    			int X=i+dir[k][0];
    			int Y=j+dir[k][1];
    			if(X<0 || X>=n || Y<0 ||Y>=m)
    				continue;
    			if(Math.abs(heights[X][Y]-heights[i][j])<=effort)
    				ok(X, Y, heights, dir, vis, effort);
    				
    		}
    	}
    }
   
    
    //dijkstra approac
    
    class minEffortNode implements Comparable<minEffortNode>{
    	int row;
    	int col;
    	int prevr;
    	int prevc;
    	int effort;
		public minEffortNode(int row, int col, int prevr, int prevc, int effort) {
			super();
			this.row = row;
			this.col = col;
			this.prevr = prevr;
			this.prevc = prevc;
			this.effort = effort;
		}
		@Override
		public int compareTo(minEffortNode o) {
			return this.effort-o.effort;
		}
		
		public int minimumEffortPathDijkstra(int[][] heights) {
			int max=0;
			boolean[][] visited=new boolean[heights.length][heights[0].length];
			PriorityQueue<minEffortNode> q=new PriorityQueue<BFS.minEffortNode>();
			q.add(new minEffortNode(0, 0, -1, -1, 0));
			
			while(!q.isEmpty()) {
				minEffortNode node=q.poll();
				int r=node.row;
				int c=node.col;
				
				max=Math.max(max, node.effort);
				if(visited[r][c])
					continue;
				visited[r][c]=true;
				if(r==heights.length-1 && c==heights[0].length-1) break;
				if(r-1>=0)
					q.offer(new minEffortNode(r-1, c, r, c, Math.abs(heights[r][c]-heights[r-1][c])));
				if(r+1<heights.length)
					q.offer(new minEffortNode(r+1, c, r, c, Math.abs(heights[r][c]-heights[r+1][c])));
				if(c-1>=0)
					q.offer(new minEffortNode(r, c-1, r, c, Math.abs(heights[r][c]-heights[r][c-1])));
				if(c+1<heights[0].length)
					q.offer(new minEffortNode(r, c+1, r, c, Math.abs(heights[r][c]-heights[r][c+1])));
			}
			
			return max;
		}
    	
    	
    }
    
//    There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a 
//    bidirectional road between cities ai and bi.
//
//    		The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly 
//    connected to both cities, it is only counted once.
//
//    		The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
//
//    		Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.
//
//    		 
//
//    				https://leetcode.com/problems/maximal-network-rank/
//    		Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
//    		Output: 4
//    		Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. 
//    		The road between 0 and 1 is only counted once.
//    		
//    		https://www.youtube.com/watch?v=lXiv1sw58d0
    
    	public int maximalNetworkRank(int n, int[][] roads) {
    		int [] road_counts=new int [n];
    		int [][] direct =new int [n][n];
    		for(int [] a:roads) {
    			road_counts[a[0]]++;
    			road_counts[a[1]]++;
    			direct[a[0]][a[1]]=1;
    			direct[a[1]][a[0]]=1;
    		}
    		int rank=0;
    		for(int i=0;i<n;i++) {
    			for(int j=i+1;j<n;j++) {
    				rank=Math.max(rank, road_counts[i]+road_counts[j]-direct[i][j]);
    			}
    		}
    		
    		return rank;
    	}
    		
//    	You are given two groups of points where the first group has size1 points, the second group has size2 points, and size1 >= size2.
//
//    	The cost of the connection between any two points are given in an size1 x size2 matrix where cost[i][j] is the cost of connecting point 
//    	i of the first group and point j of the second group. The groups are connected if each point in both groups is connected to one or 
//    	more points in the opposite group. In other words, each point in the first group must be connected to at least one point in the second group,
//    	and each point in the second group must be connected to at least one point in the first group.
//
//    	Return the minimum cost it takes to connect the two groups.	
//    	https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/
    	//https://www.youtube.com/watch?v=hq0IVf1Falk
    	
    	public static int connectTwoGroups(List<List<Integer>> cost) {
            int size1=cost.size();
            int size2=cost.get(0).size();
            int []min_cost2 =new int[size2];
            for(int i=0;i<size2;i++)
            	min_cost2[i]=Integer.MAX_VALUE;
            for(int j=0;j<size2;j++) {
            	for(int i=0;i<size1;i++) {
            		min_cost2[j]=Math.min(min_cost2[j],cost.get(i).get(j));
            	}
            }
            return connectGroupdsDP(0, 0, min_cost2, size1, size2,cost);
        }
    	
    	static int connectGroupdsDP(int i,int mask,int []min_cost2,int size1,int size2,List<List<Integer>> cost){
    		if(i==size1-1) {
    			int ans2=0;
    			for(int j=0;j<size2;j++) {
    				if((mask&1<<j) == 0)
    					ans2+=min_cost2[j];
    			}
    			return ans2;
    		}
    		int ans=Integer.MAX_VALUE;
    		for(int j=0;j<size2;j++) {
    			ans=Math.min(ans, connectGroupdsDP(i+1, (mask|1<<j), min_cost2, size1, size2,cost)+cost.get(i).get(j));
    		}
    		return ans;
    	}
    	
//    	Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1, and return them in any order.
//
//    	The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to 
//    			node graph[i][j]).
//    	https://leetcode.com/problems/all-paths-from-source-to-target/
    		
    	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    		List<List<Integer>> result=new ArrayList<List<Integer>>();
    	    List<Integer> path = new ArrayList<Integer>();
    	    pathDFS(graph, result, 0, path);
    	    return result;
        }
    	
    	void pathDFS(int[][] graph,List<List<Integer>> result,int v,List<Integer> path) {
    		path.add(v);
    		if(v==graph.length-1) 
    			result.add(new ArrayList(path));
    		for(int u:graph[v]) {
    			pathDFS(graph, result, u , path);
    		}
    		
    		result.remove(result.size()-1);
    	}
    	
//    	LeetCode] 1059. All Paths from Source Lead to Destination
//    	Given the edges of a directed graph, and two nodes source and destination of this graph, determine whether or not all paths starting from source 
 //   	eventually end at destination, that is:
//
//    	At least one path exists from the source node to the destination node
//    	If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
//    	The number of possible paths from source to destination is a finite number.
//    	Return true if and only if all roads from source lead to destination.
//
//    	Example 1:
//    		https://www.fatalerrors.org/a/leetcode-1059-all-paths-from-source-lead-to-destination.html
    	
    	public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    		HashMap<Integer, List<Integer>> map=new HashMap<Integer, List<Integer>>();
    		for(int [] edge:edges) {
    			map.putIfAbsent(edge[0], new ArrayList<Integer>());
    			map.get(edge[0]).add(edge[1]);
    		}
			return leadsToDestUtil(source, destination, map, new HashSet<Integer>());
    	}
    	
    	boolean leadsToDestUtil(int curr, int end,HashMap<Integer, List<Integer>> map,HashSet<Integer> visited) {
    		if(!map.containsKey(curr))
    			return curr==end;
    		
    		visited.add(curr);
    		for(int neighbours:map.get(curr)) {
    			if(visited.contains(neighbours)||!leadsToDestUtil(neighbours, end, map, visited))
    				return false;
    		}
    		
    		visited.remove(curr);
    		return true;
    	}
    	
//    	Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, 
//    	which minimizes the sum of all numbers along its path.
//
//    	Note: You can only move either down or right at any point in time.
//    	https://leetcode.com/problems/minimum-path-sum/
//    		https://www.youtube.com/watch?v=t1shZ8_s6jc
    		
    	public int minPathSum(int[][] grid) {
            int rows=grid.length;
            if(rows==0)
            	return 0;
            int cols=grid[0].length;
            int [][] dp=new int[rows][cols];
            dp[0][0]=grid[0][0];
            for(int i=1;i<cols;i++) {
            	dp[0][i]=dp[0][i-1]+grid[0][i];
            }
            
            for(int j=1;j<rows;j++) {
            	dp[j][0]=dp[j-1][0]+grid[j][0];
            }
            
            for(int i=1;i<rows;i++) {
            	for(int j=1;j<cols;j++) {
            		dp[i][j]=grid[i][j]+Math.min(dp[i-1][j], dp[i][j-1]);
            	}
            }
            
            return dp[rows-1][cols-1];
        }
    	
//    	A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//    	The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid 
//    	(marked 'Finish' in the diagram below).
//
//    	How many possible unique paths are there?
//    			https://leetcode.com/problems/unique-paths/
//    				https://www.youtube.com/watch?v=rBAxUTqvlQA
    	public int uniquePaths(int m, int n) {
    		int [][] dp=new int[m][n];
    		for(int i=0;i<m;i++) {
    			for(int j=0;j<n;j++) {
    				if(i==0||j==0)
    					dp[i][j]=1;
    				else
    					dp[i][j]=dp[i-1][j]+dp[i][j-1];
    			}
    		}
    		
    		return dp[m-1][n-1];
    	}
    	
//    	A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//    	The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked '
    	//Finish' in the diagram below).
//
//    	Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
//    	An obstacle and space is marked as 1 and 0 respectively in the grid.
//    	https://leetcode.com/problems/unique-paths-ii/
//    		https://leetcode.com/problems/unique-paths-ii/solution/
    	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    		if(obstacleGrid[0][0]==1)
    			return 0;
            int rows=obstacleGrid.length;
            int cols=obstacleGrid[0].length;
            obstacleGrid[0][0]=1;
            
            for(int i=1;i<rows;i++){//filling up 1st column
            	obstacleGrid[i][0]=(obstacleGrid[i][0]==0 && obstacleGrid[i-1][0]==1)?1:0;
            }
            
            for(int j=1;j<cols;j++) {//filling 1st row
            	obstacleGrid[0][j]=(obstacleGrid[0][j]==0 && obstacleGrid[0][j-1]==1)?1:0;
            }
            
            for(int i=1;i<rows;i++) {
            	for(int j=1;j<cols;j++) {
            		if(obstacleGrid[i][j]==0) {
            			obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            		}else
            			obstacleGrid[i][j]=0;
            	}
            }
        return obstacleGrid[rows-1][cols-1];
        }
    	
//    	On a 2-dimensional grid, there are 4 types of squares:
//
//    		1 represents the starting square.  There is exactly one starting square.
//    		2 represents the ending square.  There is exactly one ending square.
//    		0 represents empty squares we can walk over.
//    		-1 represents obstacles that we cannot walk over.
//    		Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
//
//    		 
//
//    		Example 1:
//
//    		Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
//    		Output: 2
//    		Explanation: We have the following two paths: 
//    		1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
//    		2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
//    		https://leetcode.com/problems/unique-paths-iii/
//    		https://www.youtube.com/watch?v=XNKCkX_tHhM
    			
    		public int uniquePathsIII(int[][] grid) {
    	       int zero_count=0,sx=0,sy=0; 
    	       for(int i=0;i<grid.length;i++) {
    	    	   for(int j=0;j<grid[0].length;j++) {
    	    		   if(grid[i][j]==0) zero_count++;//making the variable to keep count of all empty paths
    	    		   else if(grid[i][j]==1) {
    	    			   sx=i;
    	    			   sy=j;
    	    		   }
    	    	   }
    	       }
    	       
    	       return uniquePathDFS(grid, sx, sy, zero_count);
    	    }
    		
    		private int uniquePathDFS(int[][] grid,int x,int y,int zero_count) {
    			if(x<0||x>=grid.length||y<0||y>=grid[0].length||grid[x][y]==-1)
    				return 0;
    			if(grid[x][y]==2)
    				return (zero_count==-1)?1:0;//when we will reach the dest and also zero count indicate whether we have travelled all 0 paths .
    			//if its -1 i.e all paths have been travelled
    			grid[x][y]=-1;//marking the point as obstacle as we have already visited this and can not visit again
    			zero_count--;//reducing zero count as we have visited an empty path
    			int totalPath=uniquePathDFS(grid, x+1, y, zero_count)+
    					uniquePathDFS(grid, x-1, y, zero_count)+uniquePathDFS(grid, x, y+1, zero_count)+
    					uniquePathDFS(grid, x, y-1, zero_count); //running dfs in all four direction
    			
    			grid[x][y]=0;
    			zero_count++;//backtrack for both lines.it is required because here we are doing DFS so as one path gets completed we need to go back to source and start 
    			//journey again using another path.for that need to set variable in initialized state.
    			
    			return totalPath;
    		}
    		
//    		In this problem, a tree is an undirected graph that is connected and has no cycles.
//
//    		The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
//    		The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
//
//    		The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge 
//    		connecting nodes u and v.
//
//    		Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer 
//   		that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
//
//    		Example 1:
//    		Input: [[1,2], [1,3], [2,3]]
//    		Output: [2,3]
//    		Explanation: The given undirected graph will be like this:
//    		  1
//    		 / \
//    		2 - 3
//    		https://leetcode.com/problems/redundant-connection/
    			
    		boolean cycleNondirectedDetectionDFS(int source,int target,HashSet<Integer> visited) {
    			if(visited.contains(source))
    				return  false;
    			if(source==target)
    				return true;
    			visited.add(source);
    			for(int i:gph.getOrDefault(source, new ArrayList<Integer>())) {
    				if(cycleNondirectedDetectionDFS(i, target, visited))
    					return true;
    			}
    			
    			return false;
    		}
    		
    		Map<Integer,List<Integer>> gph=new HashMap<Integer,List<Integer>>();
    		public int[] findRedundantConnectionUsingDFS(int[][] edges) {
    			for(int [] edge:edges) {
    				int u=edge[0];
    				int v=edge[1];
    				
    				if(cycleNondirectedDetectionDFS(u,v,new HashSet()))
    					return edge;
    				
    				List<Integer> l1=gph.getOrDefault(u, new ArrayList<Integer>());
    				l1.add(v);
    				gph.put(u, l1);
    				
    				List<Integer> l2=gph.getOrDefault(v, new ArrayList<Integer>());
    				l2.add(u);
    				gph.put(v, l2);
    			}
    			
    			return new int[0];
    		}
    		//detect cycle in undirected grapg.Solve using disjoinct set.
    		//https://www.youtube.com/watch?v=MdI6sXCAiso
    		public int[] findRedundantConnection(int[][] edges) {
    			int []parent=new int [edges.length+1];
    			
    			for(int [] edge:edges) {
    				int u=edge[0];
    				int v=edge[1];
    				
    				int pu=findParent(u, parent);
    				int pv=findParent(v, parent);
    				
    				if(pu==pv)
    					return edge;
    				parent[pv]=pu;
    			}
    			return new int[0];
    	    }
    		
    		int findParent(int v,int parent[]) {
    			if(parent[v]==0)
    				parent[v]=v;
    			if(parent[v]!=v)
    				parent[v]=findParent(parent[v], parent);
    			
    			return parent[v];
    		}
    		
//    		In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
//
//    		The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
//
//    		The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.
//
//    		Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
//
//    		Example 1:
//    		Input: [[1,2], [1,3], [2,3]]
//    		Output: [2,3]
//    		Explanation: The given directed graph will be like this:
//    		  1
//    		 / \
//    		v   v
//    		2-->3
    		//https://www.youtube.com/watch?v=U7_ynlauYh0&feature=youtu.be
    		
    		public int[] findRedundantDirectedConnection(int[][] edges) {
    			 // We need to ensure that each node has only one parent 
    	        // and there are no cycle in this directed graph
    	         
    	        int []map = new int[edges.length + 1] ;
    	         
    	         int ans1[] = null ; //first edge
    	         int ans2[] = null ; //current edge
    	         
    	        for(int[] edge : edges) {
    	            int u = edge[0] ;
    	            int v = edge[1] ;
    	            
    	            if(map[v] > 0) { //[2,3] -> [1,3] / [2,3]
    	                ans1 = new int[] {map[v], v} ;
    	                ans2 = edge ;
    	            }
    	            
    	            map[v] = u ;
    	        } 
    	         
    	         
    	         
    	        int[] parent = new int[edges.length + 1] ;
    	          
    	         for(int[] edge : edges) {
    	             
    	             if(edge == ans2) continue ;
    	             
    	             int u = edge[0] ;
    	             int v = edge[1] ;
    	             
    	             int pu = findParent(u, parent) ;
    	             int pv = findParent(v, parent) ;
    	             
    	             if(pu == pv) { // if cycle
    	                 return ans1 == null ? edge :ans1 ;
    	             }
    	             
    	             parent[pv] = u ; //union/collapse
    	        }
    	          
    	        return ans2 ;
    	        
    	    }
    		
    		//detect cycle in a directed graph
    		//https://gist.github.com/SuryaPratapK/e84cf8624da8a690f11d5ce31745b808  ,https://www.youtube.com/watch?v=0dJmTuMrUZM
    		
    		public boolean isCyclic(int V,int [][] adj) {
    			boolean[] visited=new boolean[V];
    			boolean Flag=false;
    			for(int i=0;i<V;i++)
    				visited[i]=false;
    			
    			for(int i=0;i<V;i++) {
    				
    				visited[i]=true;
    				for(int j=0;j<adj[i].length;j++) {
    					Flag=isCyclicUtil(visited, adj[i][j], adj);
    					if(Flag==true)
    						return true;
    				}
    				
    				visited[i]=false;
    			}
    			
    			return false;
    		}
    		
    		boolean isCyclicUtil(boolean[] visited,int curr,int [][] adj) {
    			if(visited[curr]==true)
    				return true;
    			visited[curr]=true;
    			boolean Flag=false;
    			for(int i=0;i<adj[curr].length;i++) {
    				Flag=isCyclicUtil(visited, adj[curr][i], adj);
    				if(Flag==true)
    					return true;
    			}
    			
    			return false;
    		}
    		
    		
    		//There are N network nodes, labelled 1 to N.

//Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, 
//and w is the time it takes for a signal to travel from source to target.
//
//Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
    		//https://leetcode.com/problems/network-delay-time/
    		//https://www.youtube.com/watch?v=YHx6r9pM5e0
    		
    		class networkNode{
    			int dst;
    			int wt;
    		}
    		
    		class qNode{
    			int vertex;
    			int dist;
    		}
    		
    		int networkDelayTime(int[][] times, int N, int K) {
    			HashMap<Integer, List<networkNode>> adj=new HashMap<Integer, List<networkNode>>();
    			for(int i=0;i<times.length;i++) {
    				networkNode node=new networkNode();
    				node.dst=times[i][1];
    				node.wt=times[i][2];
    				adj.put(times[i][0],new ArrayList<networkNode>());
    				adj.get(times[i][0]).add(node);
    				//adj.getOrDefault(times[i][0], (List<networkNode>) node);
    			}
    			
    			qNode qN=new qNode();
    			qN.vertex=K;
    			qN.dist=0;
    			Queue<qNode> q=new LinkedList<BFS.qNode>();
    			q.add(qN);
    			
    			int[] dist_arr=new int[N+1];
    			for(int i=0;i<N+1;i++)
    				dist_arr[i]=Integer.MAX_VALUE;
    			
    			dist_arr[K]=0;
    			int time=0;
    			
    			while(!q.isEmpty()) {//BFS
    				qNode curr=q.peek();
    				q.poll();
    				
    				for(networkNode nd:adj.get(curr)) {
    					qNode temp=new qNode();
    					temp.vertex=nd.dst;
    					temp.dist=nd.wt+curr.dist;
    					if(dist_arr[temp.vertex]>temp.dist) {
    						q.add(temp);
    						dist_arr[temp.vertex]=nd.wt+curr.dist;
    					}
    				}
    			}
    			
    			//STEP-2: Find the max distance node (If all the nodes are traversed)
    			for (int i=1;i<N+1;i++) {
    				if(dist_arr[i]==Integer.MAX_VALUE)
    					return -1;
    				time=Math.max(time, dist_arr[i]);
    			}
    			
    			return time;
    		}
    		
//    		N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.
//
//    		The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
//
//    		The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.
//
//    		Example 1:
//
//    		Input: row = [0, 2, 1, 3]
//    		Output: 1
//    		Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
//    		Example 2:
//
//    			Input: row = [3, 2, 0, 1]
//    			Output: 0
//    			Explanation: All couples are already seated side by side.
    		//https://www.youtube.com/watch?v=WY6qloZPZ00
    		
    		public int minSwapsCouples(int[] row) {
    	        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
    	        for(int i=0;i<row.length;i++) {
    	        	map.put(row[i],i);
    	        }
    	        int swaps=0;
    	        for(int i=0;i<row.length;i+=2) {
    	        	int first=row[i];
    	        	int second=first+(first%2==0?1:-1);
    	        	
    	        	if(row[i+1]!=second) {
    	        		coupleSwap(row, map, i+1, map.get(second));
    	        		swaps++;
    	        	}
    	        }
    	        
    	        return swaps;
    	    }
    		
    		void coupleSwap(int[] row,HashMap<Integer, Integer> map,int i,int j) {
    			int temp=row[i];
    			row[i]=row[j];
    			row[j]=temp;
    			
    			map.put(row[i], i);
    		}
    		
    		//union find solution https://leetcode.com/problems/couples-holding-hands/discuss/600607/Java-Union-Find-Solution-with-explanation-Time-O(N)-0ms-beats-100
    		//in a couple (2,3) even number is the min one and if we do <even>&1 we get 0.also diff in a couple is always 1.
    		//https://www.youtube.com/watch?v=mJVQL-deD7A
    		
    		
//    		Given an undirected graph, return true if and only if it is bipartite.
//
//    		Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B, such that every edge in 
//    		the graph has one node in A and another node in B.
//
//    		The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
//    		Each node is an integer between 0 and graph.length - 1. 
//    		There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
//    		https://leetcode.com/problems/is-graph-bipartite/
//    			
//    		https://www.youtube.com/watch?v=0ACfAqs8mm0
    		public static boolean isBipartite(int[][] graph) {
    			int N=graph.length;
    	         List<Integer>[] adj=new ArrayList [N+1];
    	        for(int i=0;i<N;i++) {
    	        	adj[graph[i][0]]=new ArrayList<Integer>();
    	        	adj[graph[i][1]]=new ArrayList<Integer>();
    	        }
    	        for(int i=0;i<N;i++) {
    	        	if(!adj[graph[i][0]].contains(graph[i][1]))
    	        		adj[graph[i][0]].add(graph[i][1]);
    	        	if(!adj[graph[i][1]].contains(graph[i][0]))
    	        	adj[graph[i][1]].add(graph[i][0]);
    	        }
    	        
    	        int [] color=new int[N];
    	        Arrays.fill(color, -1);

    	        
    	        for(int i=0;i<N;++i) {
    	        	if(color[i]==-1)
    	        		if(!isBiPartiteUtil(adj, N, i, color)){
    	        			return false;
    	        		}
    	        }
    	        
    	        return true;
    	    }
    		
    		static boolean isBiPartiteUtil(List<Integer>[] adj,int N,int node,int [] color) {
    			Queue<Integer> q=new LinkedList<Integer>();
    			q.add(node);
    			color[node]=1;
    			while(!q.isEmpty()) {
    				int curr=q.peek();
    				q.poll();
    				for(int ele:adj[curr]) {
    					if(color[curr]==color[ele])
    						return false;
    					if(color[ele]==-1) {
    						color[ele]=1-color[curr];
    						q.add(ele);
    					}
    				}
    			}
    			
    			return true;
    		}

//    		In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal 
 //   		(that is, it has no outgoing directed edges), we stop.
//
//    		Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a
//    		natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.
//
//    		Which nodes are eventually safe?  Return them as an array in sorted order.
//
//    		The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: 
//    		graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
//
//    		Example:
//    		Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//    		Output: [2,4,5,6]
//    		Here is a diagram of the above graph.
//    		https://leetcode.com/problems/find-eventual-safe-states/
    		//https://leetcode.com/problems/find-eventual-safe-states/solution/
    			
    			public static int[] eventualSafeNodes(int[][] graph) {
    	        int [] arr=new int[graph.length];
    	        for (int i=0;i<graph.length;i++){
    	            if(graph[i].length==0) {
    	            	arr[i]=i;
    	            	continue;
    	            }
    	            if(graph[i].length==1) {
    	            	if(graph[graph[i][0]].length==0) {
    	            		arr[i]=i;
        	            	continue;
    	            	}
    	            }
    	        }
    	        
    	        Arrays.sort(arr);
    	        return arr;
    	    }
    			
//    			Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
//
//    			For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
//
//    			Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
//
//    			We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
//
//    			 
//
//    			Example 1:
//
//    			Input: strs = ["tars","rats","arts","star"]
//    			Output: 2
//    			Example 2:
//
//    			Input: strs = ["omv","ovm"]
//    			Output: 1		
    			//https://www.youtube.com/watch?v=WuBTG71yOek
    	public int numSimilarGroups(String[] A) {
    		     int result =0;
    		     if(A==null || A.length==0)
    		    	 return result;
    		     
    		     HashSet<String> visited=new HashSet<String>();
    		     for(String st:A) {
    		    	 if(!visited.contains(st)) {
    		    		 strSimilarDFS(visited,A,st);
    		    		 result++;
    		    	 }
    		     }
    		     
    		     return result;
    	}
    	
    	void strSimilarDFS(HashSet<String> visited,String[] A,String st) {
    		if(visited.contains(st))
    			return;
    		visited.add(st);
    		for(int i=0;i<A.length;i++) {
    			if(isSimilar(st,A[i])) {
    			strSimilarDFS(visited, A, st);
    			}
    		}
    	}
    	
    	boolean isSimilar(String s1,String s2) {
    		int count =0;
    		for(int i=0;i<s1.length();i++) {
    			if(s1.charAt(i)!=s2.charAt(i))
    				count++;
    			if(count>2)
    				return false;
    		}
    		
    		return count==2 || count==0;
    	}
    	
//    	An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
//
//    	Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
//
//    	To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the 
//    	starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the
//    	color of all of the aforementioned pixels with the newColor.
//
//    	At the end, return the modified image.
//
//    	Example 1:
//    	Input: 
//    	image = [[1,1,1],[1,1,0],[1,0,1]]
//    	sr = 1, sc = 1, newColor = 2
//    	Output: [[2,2,2],[2,2,0],[2,0,1]]
//    	Explanation: 
//    	From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
//    	by a path of the same color as the starting pixel are colored with the new color.
//    	Note the bottom corner is not colored 2, because it is not 4-directionally connected
//    	to the starting pixel.
//    	https://leetcode.com/problems/flood-fill/
    	//https://www.youtube.com/watch?v=RwozX--B_Xs
    	//https://leetcode.com/problems/flood-fill/solution/
    		public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            if(newColor==image[sr][sc])
            	return image;
            int rows=image.length;
            int cols=image[0].length;
            int source=image[sr][sc];
            floodFillDFS(image,sr,sc,rows,cols,newColor,source);
            return image;
        }
    	
    	void floodFillDFS(int[][] image, int sr, int sc,int rows,int cols, int newColor,int source) {
    		if(sr<0||sr>=rows||sc<0||sc>=cols)
    			return;
    		if(image[sr][sc] != source)
    			return;
    		image[sr][sc]=source;
    		floodFillDFS(image, sr-1, sc, rows, cols, newColor, source);
    		floodFillDFS(image, sr+1, sc, rows, cols, newColor, source);
    		floodFillDFS(image, sr, sc-1, rows, cols, newColor, source);
    		floodFillDFS(image, sr, sc+1, rows, cols, newColor, source);
    	}
    	
//    	There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 
//
//    	Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
//
//    	Initially, all the rooms start locked (except for room 0). 
//
//    	You can walk back and forth between rooms freely.
//
//    	Return true if and only if you can enter every room.
//
//    	Example 1:
//
//    	Input: [[1],[2],[3],[]]
//    	Output: true
//    	Explanation:  
//    	We start in room 0, and pick up key 1.
//    	We then go to room 1, and pick up key 2.
//    	We then go to room 2, and pick up key 3.
//    	We then go to room 3.  Since we were able to go to every room, we return true.
//    	Example 2:
//
//    	Input: [[1,3],[3,0,1],[2],[0]]
//    	Output: false
//    	Explanation: We can't enter the room with number 2.
    	//https://leetcode.com/problems/keys-and-rooms/solution/
    	public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
    		boolean opened=true;
          boolean [] open=new boolean [rooms.size()];
          open[0]=true;
          for(int i=0;i<rooms.size();i++) {
        	  for(int j:rooms.get(i)) {
        			  if(j != i && open[j]==false) {
        				  open[j]=true; 
        			  }
        	  }
          }
          
          for(int i=0;i<rooms.size();i++) {
        	  if(open[i]==false) {
          		opened=false;
        		 break;
        	  }
        	}
          return opened;
          
        }
    	
//    	Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the
//    	resulting string equals B.
//
//    	Given two anagrams A and B, return the smallest K for which A and B are K-similar.
//
//    	Example 1:
//
//    	Input: A = "ab", B = "ba"
//    	Output: 1
//    	Example 2:
//
//    	Input: A = "abc", B = "bca"
//    	Output: 2
//    	Example 3:
//
//    	Input: A = "abac", B = "baca"
//    	Output: 2
//    	https://leetcode.com/problems/k-similar-strings/solution/
    	
    	public static int kSimilarity(String A, String B) {
            Queue<String> queue = new ArrayDeque();
            queue.offer(A);

            Map<String, Integer> dist = new HashMap();
            dist.put(A, 0);

            while (!queue.isEmpty()) {
                String S = queue.poll();
                if (S.equals(B)) return dist.get(S);
                for (String T: neighbors(S, B)) {
                    if (!dist.containsKey(T)) {
                        dist.put(T, dist.get(S) + 1);
                        queue.offer(T);
                    }
                }
            }

            throw null;
        }

        public static List<String> neighbors(String S, String target) {
            List<String> ans = new ArrayList();
            int i = 0;
            for (; i < S.length(); ++i) {
                if (S.charAt(i) != target.charAt(i)) break;
            }

            char[] T = S.toCharArray();
            for (int j = i+1; j < S.length(); ++j)
                if (S.charAt(j) == target.charAt(i)) {
                    swap(T, i, j);
                    ans.add(new String(T));
                    swap(T, i, j);
                }

            return ans;
        }

        public static void swap(char[] T, int i, int j) {
            char tmp = T[i];
            T[i] = T[j];
            T[j] = tmp;
        }
        
//        In a network of nodes, each node i is directly connected to another node j if and only if graph[i][j] = 1.
//
//        		Some nodes initial are initially infected by malware.  Whenever two nodes are directly connected and at least one of those two nodes is 
//        infected by malware, both nodes will be infected by malware.  This spread of malware will continue until no more nodes can be infected in this manner.
//
//        		Suppose M(initial) is the final number of nodes infected with malware in the entire network, after the spread of malware stops.
//
//        		We will remove one node from the initial list.  Return the node that if removed, would minimize M(initial).  If multiple nodes could be 
 //       removed to minimize M(initial), return such a node with the smallest index.
//
//        		Note that if a node was removed from the initial list of infected nodes, it may still be infected later as a result of the malware spread.
//
//        		 
//
//        		Example 1:
//
//        		Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
//        		Output: 0
//        		Example 2:
//
//        		Input: graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]
//        		Output: 0
        
        //https://leetcode.com/problems/minimize-malware-spread/
        
        class MalwareDSU{
        	int[] p, sz;

        	MalwareDSU(int N) {
                p = new int[N];
                for (int x = 0; x < N; ++x)
                    p[x] = x;

                sz = new int[N];
                Arrays.fill(sz, 1);
            }

            public int find(int x) {
                if (p[x] != x)
                    p[x] = find(p[x]);
                return p[x];
            }

            public void union(int x, int y) {
                int xr = find(x);
                int yr = find(y);
                p[xr] = yr;
                sz[yr] += sz[xr];
            }

            public int size(int x) {
                return sz[find(x)];
            }
        }
        
        public int minMalwareSpread(int[][] graph, int[] initial) {
            int n=graph.length;
            MalwareDSU dsu=new MalwareDSU(n*n);
            for(int i=0;i<n;i++) {
            	for(int j=0;j<n;j++) {
            		if(graph[i][j]==1)
            			dsu.union(i, j);
            	}
            }
            
            Arrays.sort(initial);
            int[] count = new int[n];
            for(int i:initial) {
            	int root=dsu.find(i);
            	count[root]++;
            }
            
            int ans=-1,ansSize=-1;
            for(int i:initial) {
            	int root=dsu.find(i);
            	if(count[root]==1) {
            		int rootSize=dsu.size(root);
            		if(rootSize>ansSize) {
            			ansSize=rootSize;
            			ans=i;
            		}else if(rootSize==ansSize && i<ans) {
            			ansSize=rootSize;
            			ans=i;
            		}
            	}
            }
            
            if(ans==-1) {
            	ans=Integer.MAX_VALUE;
            	for(int node:initial) {
            		ans=Math.min(ans, node);
            	}
            }
            return ans;
            
        }
        
        //
//        (This problem is the same as Minimize Malware Spread, with the differences bolded.)
//
//        In a network of nodes, each node i is directly connected to another node j if and only if graph[i][j] = 1.
//
//        Some nodes initial are initially infected by malware.  Whenever two nodes are directly connected and at least one of those two nodes is 
//        infected by malware, both nodes will be infected by malware.  This spread of malware will continue until no more nodes can be infected in this manner.
//
//        Suppose M(initial) is the final number of nodes infected with malware in the entire network, after the spread of malware stops.
//
//        We will remove one node from the initial list, completely removing it and any connections from this node to any other node.  
 //       Return the node that if removed, would minimize M(initial).  If multiple nodes could be removed to minimize M(initial), return such a node with the smallest index.
//
//         
//
//        Example 1:
//
//        Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
//        Output: 0
//        Example 2:
//
//        Input: graph = [[1,1,0],[1,1,1],[0,1,1]], initial = [0,1]
//        Output: 1
        //https://leetcode.com/problems/minimize-malware-spread-ii/solution/
        //using DFS
        
        public int minMalwareSpreadSecondByDFS(int[][] graph, int[] initial) {
            int n=graph.length;
            int [] clean=new int[n];
            Arrays.fill(clean, 1);
            for(int i:initial)
            	clean[i]=0;
            ArrayList<Integer>[] infectedBy = new ArrayList[n];
            for(int i=0;i<n;i++)
            	infectedBy[i]=new ArrayList<Integer>();
            HashSet<Integer> seen=new HashSet<Integer>();
            for(int u:initial) {
            	seen.add(u);
            	malwareDFS(seen,u,graph,clean);
            	for(int v:seen){
            		infectedBy[v].add(u);
            	}
            }
            
            int [] contribution = new int [n];
            for (int i=0;i<n;i++) {
            	if (infectedBy[i].size() == 1)
            	contribution[infectedBy[i].get(0)]++;
            }
            
            Arrays.sort(initial);
            int ans=initial[0],ansSize=-1;
            
            for(int u:initial) {
            	int score=contribution[u];
            	if(score>ansSize || score==ansSize && u<ans) {
            		ansSize=score;
            		ans=u;
            	}
            }
            
            return ans;
        }
        
        void malwareDFS(HashSet<Integer> seen,int u,int[][] graph, int[] clean) {
        	for(int v=0;v<graph.length;v++) {
        		if(graph[u][v]==1 && clean[v]==1 && !seen.contains(v)) {
        			seen.add(v);
        			malwareDFS(seen, v, graph, clean);
        		}
        	}
        }
        
        //using union find
        public int minMalwareSpreadSecondUnion(int[][] graph, int[] initial) {
        	int n=graph.length;
        	MalwareDSU dsu=new MalwareDSU(n);
            int [] clean=new int[n];
            Arrays.fill(clean, 1);
            for(int i:initial)
            	clean[i]=0;
            for(int i=0;i<n;i++) {
            	if(clean[i]==1) {
            		for(int j=0;j<n;j++) {
            			if(clean[j]==1)
            				if(graph[i][j]==1)
            					dsu.union(i, j);
            		}
            	}
            }
            
            int [] count=new int[n];
            Map<Integer,Set<Integer>> nodeToComp=new HashMap<Integer, Set<Integer>>();
            for(int u:initial) {
            	Set<Integer> components=new HashSet<Integer>();
            	for(int v=0;v<n;v++) {
            		if(clean[v]==1) {
            			if(graph[u][v]==1)
            				components.add(dsu.find(v));
            		}
            	}
            	
            	nodeToComp.put(u, components);
            	for(int c:components)
            		count[c]++;
            }
            
            int ans=-1,ansSize=-1;
            for(int u:initial) {
            	Set<Integer> components=nodeToComp.get(u);
            	int score=0;
            	for(int c:components) {
            		if(count[c]==1)
        			score +=dsu.size(c);
            	}
            	
            	if(score>ansSize || score==ansSize && u<ans) {
            		ansSize=score;
            		ans=u;
            	}
            }
            
            return ans;
        }
        
//        Given an integer matrix, find the length of the longest increasing path.
//
//        From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary
 //       (i.e. wrap-around is not allowed).
//
//        Example 1:
//
//        Input: nums = 
//        [
//          [9,9,4],
//          [6,6,8],
//          [2,1,1]
//        ] 
//        Output: 4 
//        Explanation: The longest increasing path is [1, 2, 6, 9].
//        Example 2:
//
//        Input: nums = 
//        [
//          [3,4,5],
//          [3,2,6],
//          [2,2,1]
//        ] 
//        Output: 4 
//        Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
        //https://leetcode.com/problems/longest-increasing-path-in-a-matrix/submissions/
        //https://www.youtube.com/watch?v=-UeIgUqAFbM
        
        
        public int longestIncreasingPath(int[][] matrix) {
            if(matrix.length==0)
            	return 0;
            
            int max=0;
            
            int [][] dp=new int [matrix.length][matrix[0].length];
            for(int i=0;i<matrix.length;i++) {
            	for(int j=0;j<matrix[0].length;j++) {
            		if(dp[i][j]==0) {
            			longestIncreasingPathDFS(matrix,i,j,Integer.MIN_VALUE,dp);
            		max=Math.max(max, dp[i][j]);
            		}
            	}
            }
            
            return max;
        }
        
        int longestIncreasingPathDFS(int[][] matrix,int i,int j,int prev,int[][] dp) {
        	
        	if(i<0||j<0||i>matrix.length-1||j>matrix[0].length-1)
        		return 0;
        	else if(prev >= matrix[i][j])
        		return 0;
        	else if(dp[i][j] !=0) return dp[i][j];
        	
        	int path1=longestIncreasingPathDFS(matrix, i+1, j, matrix[i][j],dp);
        	int path2=longestIncreasingPathDFS(matrix, i-1, j, matrix[i][j],dp);
        	int path3=longestIncreasingPathDFS(matrix, i, j+1, matrix[i][j],dp);
        	int path4=longestIncreasingPathDFS(matrix, i, j-1, matrix[i][j],dp);
        	
        	int max1=Math.max(path1, path2);
        	int max2=Math.max(path3, path4);
        	
        	dp[i][j] =1+Math.max(max1, max2);
        	return dp[i][j];
        }
        
//        Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
//
//        		An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of 
//        the grid are all surrounded by water.
//
//        		 
//
//        		Example 1:
//
//        		Input: grid = [
//        		  ["1","1","1","1","0"],
//        		  ["1","1","0","1","0"],
//        		  ["1","1","0","0","0"],
//        		  ["0","0","0","0","0"]
//        		]
//        		Output: 1
//        		Example 2:
//
//        		Input: grid = [
//        		  ["1","1","0","0","0"],
//        		  ["1","1","0","0","0"],
//        		  ["0","0","1","0","0"],
//        		  ["0","0","0","1","1"]
//        		]
//        		Output: 3
//        		https://leetcode.com/problems/number-of-islands/
        //https://www.youtube.com/watch?v=t4ZTkEjC3HQ
        			
        		public int numIslands(char[][] grid) {
        	    if(grid.length==0) return 0;
        	    int islands=0;
        	    
        	    for(int i=0;i<grid.length;i++) {
        	    	for(int j=0;j<grid[0].length;i++) {
        	    		if(grid[i][j]=='1') {
        	    			numIslandsDFS(grid,i,j);
        	    			islands++;
        	    		}
        	    	}
        	    }
        	    
        	    return islands;
        	    }
        		
        		void numIslandsDFS(char[][] grid,int i,int j) {
        			if(i<0||j<0||i>grid.length-1||j>grid[0].length-1)
                		return ;
        			if(grid[i][j]=='0')return;
        			grid[i][j]='0';
        			numIslandsDFS(grid, i+1, j);
        			numIslandsDFS(grid, i-1, j);
        			numIslandsDFS(grid, i, j+1);
        			numIslandsDFS(grid, i, j-1);
        		}
        		
//        		Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the 
 //       		left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
//        		Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
//        		Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
//        		Note:
//
//        		The order of returned grid coordinates does not matter.
//        		Both m and n are less than 150.
//        		 
//
//        		Example:
//
//        		Given the following 5x5 matrix:
//
//        		  Pacific ~   ~   ~   ~   ~ 
//        		       ~  1   2   2   3  (5) *
//        		       ~  3   2   3  (4) (4) *
//        		       ~  2   4  (5)  3   1  *
//        		       ~ (6) (7)  1   4   5  *
//        		       ~ (5)  1   1   2   4  *
//        		          *   *   *   *   * Atlantic
//
//        		Return:
//
//        		[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
//https://leetcode.com/problems/pacific-atlantic-water-flow/
        		//https://www.youtube.com/watch?v=VC9BYJvFeC8
        		public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        	        List<List<Integer>> cord=new ArrayList<List<Integer>>();
        	        
        	        if(matrix.length==0) return cord;
        	        int rows=matrix.length;
        	        int cols=matrix[0].length;
        	        int [][] paci=new int [rows][cols];
        	        int [][] atl=new int [rows][cols];
        	        
        	        //first row pacific
        	        for(int i=0;i<cols;i++) {
        	        	pacificAtlanticDFS(matrix,0,i,paci,Integer.MIN_VALUE);
        	        }
        	        
        	        //first col pacific
        	        for(int i=0;i<rows;i++) {
        	        	pacificAtlanticDFS(matrix,i,0,paci,Integer.MIN_VALUE);
        	        }
        	        
        	        //last row atl
        	        for(int i=0;i<cols;i++) {
        	        	pacificAtlanticDFS(matrix,rows-1,i,atl,Integer.MIN_VALUE);
        	        }
        	        
        	        //last col atl
        	        for(int i=0;i<rows;i++) {
        	        	pacificAtlanticDFS(matrix,i,cols-1,atl,Integer.MIN_VALUE);
        	        }
        	        
        	        for(int i=0;i<rows;i++) {
        	        	for(int j=0;j<cols;j++) {
        	        		if(paci[i][j]==1 && atl[i][j]==1) {
        	        			List<Integer> c=new ArrayList<Integer>();
        	        			c.add(i);c.add(j);
        	        			cord.add(c);
        	        		}
        	        	}
        	        }
        	        
        	        return cord;
        	    }
        		
        		void pacificAtlanticDFS(int[][] matrix,int row,int col,int[][] temp,int prev) {
        			if(row<0||col<0||row>matrix.length-1||col>matrix[0].length-1)
                		return ;
        			else if(prev>matrix[row][col]) return;
        			else if(temp[row][col]==1);
        			
        			temp[row][col]=1;
        			pacificAtlanticDFS(matrix, row+1, col, temp, matrix[row][col]);
        			pacificAtlanticDFS(matrix, row-1, col, temp, matrix[row][col]);
        			pacificAtlanticDFS(matrix, row, col+1, temp, matrix[row][col]);
        			pacificAtlanticDFS(matrix, row, col-1, temp, matrix[row][col]);
        		}
        		
//        		Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation 
//        		of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs
  //      		(i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be 
    //    		reconstructed from seqs and it is the org sequence.
//
//        		Example 1:
//
//        		Input:
//        		org: [1,2,3], seqs: [[1,2],[1,3]]
//
//        		Output:
//        		false
//
//        		Explanation:
//        		[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
//        		Example 2:
//
//        		Input:
//        		org: [1,2,3], seqs: [[1,2]]
//
//        		Output:
//        		false
//
//        		Explanation:
//        		The reconstructed sequence can only be [1,2].
//        		Example 3:
//
//        		Input:
//        		org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
//
//        		Output:
//        		true
//
//        		Explanation:
//        		The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
//        		Example 4:
//
//        		Input:
//        		org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
//
//        		Output:
//        		true
        		//https://www.youtube.com/watch?v=Bqhf7zPMdaU
        		public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        			
        			//verify if all elements in org are present in seqs 
        			Set<Integer> nums=new HashSet<Integer>();
        			
        			for(List<Integer> seq:seqs) {
        				for(int i=0;i<seq.size();i++) {
        					nums.add(seq.get(i));
        				}
        			}
        			
        			if (org.length != nums.size()) return false;
        			for(int i:org) {
        				if(!nums.contains(i))
        					return false;
        			}
        			
        			//graph processing
        			int n=org.length;
        			int [] out_edges=new int[n+1];
        			int [] in_degree=new int[n+1];
        			
        			for(List<Integer> seq:seqs) {
        				for(int i=1;i<seq.size();i++) {
        					out_edges[i-1]=seq.get(i);
        					in_degree[i]++;
        				}
        			}
        			
        			//BFS search to find the unique topological sort order
        			Queue< Integer> q=new LinkedList<Integer>();
        			List<Integer> order=new ArrayList<Integer>();
        			//put the node in queue for which in degree is zero.that would be the first int in org
        			for(int i=0;i<in_degree.length;i++) {
        				if(in_degree[i]==0) {
        					q.add(i);
        					break;
        				}
        			}
        			
        			while(!q.isEmpty()) {
        				int temp=q.poll();
        				order.add(temp);
        				int out_node=out_edges[temp];
        				in_degree[out_node]-=1;
        				if(in_degree[out_node]==0)
        					q.add(out_node);
        			}
        			
        			//compare whether order and org is same or not.if same return true,else false;
        			return false;//placeholder
        		}
        		
//        		There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal
//        		to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.
//
//        		Return a sorted list of the items such that:
//
//        		The items that belong to the same group are next to each other in the sorted list.
//        		There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th
//        		item in the sorted array (to the left of the i-th item).
//        		Return any solution if there is more than one solution and return an empty list if there is no solution.
//
//        		 
//
//        		Example 1:
//
//
//
//        		Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
//        		Output: [6,3,4,1,5,2,0,7]
//        		Example 2:
//
//        		Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
//        		Output: []
//        		Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
        		
        		//https://www.youtube.com/watch?v=jcRNNMgHtiY
        		public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        			List<List<Integer>> group_items=new ArrayList<List<Integer>>();//this is the list of all groups corresponding to the items inside group.
        	        int groupId=m;
        	        for(int i=0;i<n;i++) {
        	        	if(group[i]==-1) {
        	        		group[i]=groupId;
        	        		groupId++;
        	        	}
    	        		group_items.add(group[i], new ArrayList<Integer>());
        	        	group_items.get(group[i]).add(i);
        	        }
        	        
        	        ArrayList<Integer>[] item_graph = new ArrayList[n];
        	        int [] item_indegree = new int[n];
        	        
        	        //making adjacency list.u is the starting of edge and v is the ending of edge
        	        for(int v=0;v<n;v++) {
        	        	for(int u:beforeItems.get(v)) {
        	        		if(group[u]==group[v]) {
        	        			item_graph[u]=new ArrayList<Integer>();
        	        			item_graph[u].add(v);
        	        			item_indegree[v]++;
        	        		}
        	        	}
        	        }
        	        
        	        List<Integer> sorted_items=new ArrayList<Integer>();
        	        
        	        List<List<Integer>> sorted_group_items=new ArrayList<List<Integer>>();//it will store the items sorted group wise
        	        for(List<Integer> grp:group_items){//topological sort within items of same group
        	        	sorted_items=sortItemsTopologicalSort(grp, item_graph, item_indegree);
        	        
        	        if(sorted_items.size()!=grp.size())
        	        	return new int [0];

        	        sorted_group_items.add(sorted_items);
        	        }
        	        
        	        ArrayList<Integer>[] group_graph = new ArrayList[n];
        	        int [] group_indegree = new int[n];
        	        for(int v=0;v<n;v++) {//this it to decide the dependencies among groups
        	        	for(int u:beforeItems.get(v)) {
        	        		if(group[u]!=group[v]) {
        	        			group_graph[u]=new ArrayList<Integer>();
        	        			group_graph[u].add(v);
        	        			group_indegree[v]++;
        	        		}
        	        	}
        	        }
        	        
        	        Set<Integer> groups=new HashSet<Integer>();
        	        for(int i=0;i<group.length;i++)
        	        	groups.add(group[i]);
        	        //sort the groups topologically
        	        List <Integer> sorted_group=sortItemsTopologicalSort(groups, group_graph, group_indegree);
        	        	if(sorted_group.size()!=groups.size())
        	        		return new int[0];
        	        	
        	        List<Integer> ans =new ArrayList<Integer>();
        	        for(int i:sorted_group) {
        	        	ans.addAll(sorted_group_items.get(i));
        	        }
        	        
        	        return ans;
        	    }
        		
        		List<Integer> sortItemsTopologicalSort(List<Integer> nodes,ArrayList<Integer> [] graph,int [] in_degree) {
        			List<Integer> ans=new ArrayList<Integer>();
        			Queue<Integer> q=new LinkedList<Integer>();
        			for(int i=0;i<in_degree.length;i++) {
        				if(in_degree[i]==0) {
        					q.add(i);
        					break;
        				}
        			}
        			
        			while(!q.isEmpty()) {
        				int temp=q.poll();
        				ans.add(temp);
        				for(int node:graph[temp]) {
        					in_degree[node]-=1;
        					if(in_degree[node]==0)
        						q.add(node);
        				}
        			}
        			
        			return ans;
        		}
        		
//        		
//        		Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//        				Follow up: Could you implement the O(n) solution? 
//
//        				 
//
//        				Example 1:
//
//        				Input: nums = [100,4,200,1,3,2]
//        				Output: 4
//        				Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//        				Example 2:
//
//        				Input: nums = [0,3,7,2,5,8,4,6,0,1]
//        				Output: 9
//        				https://www.youtube.com/watch?v=AGb52zpOBvc
        				
        				public int longestConsecutive(int[] nums) {
        			        HashMap<Integer, Boolean> intToSeqMap=new HashMap<Integer, Boolean>();
        			        for(int i=0;i<nums.length;i++)
        			        	intToSeqMap.put(nums[i], true);
        			        
        			        for(int i=0;i<nums.length;i++) {
        			        	if(intToSeqMap.containsKey(nums[i]-1))
        			        		intToSeqMap.put(nums[i], false);
        			        }
        			        
        			        int max=0;
        			        for(int key:intToSeqMap.keySet()) {
        			        	if(intToSeqMap.get(key)==true) {
        			        		max=Math.max(max, findSeqLength(intToSeqMap,key));
        			        	}
        			        }
        			        
        			        return max;
        			    }
        				
        				int findSeqLength(HashMap<Integer, Boolean> intToSeqMap,int key) {
        					int ans=0;
        					while(intToSeqMap.containsKey(key)) {
        						ans++;
        						key++;
        					}
        					
        					return ans;
        				}
        				
//        				You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
//
//        						You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
//
//        						Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//        						Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
//
//        						 
//
//        						Example 1:
//
//        						Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//        						Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//        						Explanation: 
//        						Given: a / b = 2.0, b / c = 3.0
//        						queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//        						return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//        						Example 2:
//
//        						Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//        						Output: [3.75000,0.40000,5.00000,0.20000]
//        						Example 3:
//
//        						Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//        						Output: [0.50000,2.00000,-1.00000,-1.00000]
        				//https://www.youtube.com/watch?v=UcDZM6Ap5P4
        				
        				class Node{
        					String key;
        					double val;
							public Node(String key, double val) {
								super();
								this.key = key;
								this.val = val;
							}
        					
        				}
        				
        				public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        			        Map<String,List<Node>> graph=buildGraphCalcEquation(equations,values);
        			        double[] result =new double[queries.size()];
        			        for(int i=0;i<queries.size();i++) {
        			        	result[i]=calcEquationDFS(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<String>());
        			        }
        			        
        			        return result;
        			    }
        				
        				Map<String,List<Node>> buildGraphCalcEquation(List<List<String>> equations, double[] values){
        					Map<String,List<Node>> graph = new HashMap<String, List<Node>>();
        					for(int i=0;i<values.length;i++) {
        						String src=equations.get(i).get(0);
        						String dest=equations.get(i).get(1);
        						graph.putIfAbsent(src, new ArrayList<BFS.Node>());
        						graph.putIfAbsent(dest, new ArrayList<BFS.Node>());
        						graph.get(src).add(new Node(src, values[i]));
        						graph.get(dest).add(new Node(dest,1/values[i]));
        					}
        					
        					return graph;
        				}
        				
        				double calcEquationDFS(String s,String d,Map<String,List<Node>> graph,Set<String> visited) {
        					if(!(graph.containsKey(s) && (graph.containsKey(d))))
        						return -1.0;
        					if(s.equals(d))
        						return 1.0;
        					
        					visited.add(s);
        					for(Node ng:graph.get(s)) {
        						if(!(visited.contains(ng))) {
        							double ans=calcEquationDFS(ng.key, d, graph, visited);
        							if(ans != -1.0)
        								return ans*ng.val;
        						}
        					}
        					
        					return -1.0;
        					
        				}
//        				There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
//
//        				Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
//
//        				Example 1:
//
//        				Input: 
//        				[[1,1,0],
//        				 [1,1,0],
//        				 [0,0,1]]
//        				Output: 2
//        				Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
//        				The 2nd student himself is in a friend circle. So return 2.
//        				 
//
//        				Example 2:
//
//        				Input: 
//        				[[1,1,0],
//        				 [1,1,1],
//        				 [0,1,1]]
//        				Output: 1
//        				Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
//        				so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
        				//https://www.youtube.com/watch?v=43_8Ql7bO9s
        				
        			public int findCircleNum(int[][] M) {
        			        int ans=findCircleNumBFS(M, 0);
        			        return ans;
        		    }
        			
        			int findCircleNumBFS(int[][] adjMatrix,int ans) {
        				int v=adjMatrix.length;
        				boolean[] visited=new boolean[v];
        				for(int i=0;i<visited.length;i++) {
        					if(visited[i]==false) {
        						ans++;
        						findCircleNumBFSUtil(visited,adjMatrix,i);
        					}
        				}
        				return ans;
        			}
        			
        			void findCircleNumBFSUtil(boolean[] visited,int[][] adjMatrix,int source) {
        				Queue<Integer> q=new LinkedList<Integer>();
        				q.add(source);
        				visited[source]=true;
        				int v=adjMatrix.length;
        				while(!q.isEmpty()) {
        					int front=q.poll();
        					for(int i=0;i<v;i++) {
        						if(adjMatrix[front][i]==1 && visited[i] == false) {
        							visited[i]=true;
        							q.add(i);
        						}
        					}
        				}
        			}
//        			Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//        			Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
//        			After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
//
//        			Example 1:
//        			Input: 
//        			accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
//        			Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
//        			Explanation: 
//        			The first and third John's are the same person as they have the common email "johnsmith@mail.com".
//        			The second John and Mary are different people as none of their email addresses are used by other accounts.
//        			We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
//        			['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
//        					https://leetcode.com/problems/accounts-merge/
        			//https://leetcode.com/problems/accounts-merge/solution/
        			//https://www.youtube.com/watch?v=nFkaJYj0IKc
        			
        			public List<List<String>> accountsMerge(List<List<String>> accounts) {
        		        accountsMergeDSU dsu=new accountsMergeDSU();
        		        Map<String,String> email_to_name=new HashMap<String, String>();
        		        Map<String,Integer> email_to_id=new HashMap<String, Integer>();
        		        
        		        int index=0;
        		        
        		        for(List<String> account:accounts) {
        		        	String name=account.get(0);
        		        	String email;
        		        	for(int i=1;i<account.size();i++) {
        		        		email=account.get(i);
        		        		email_to_name.put(email, name);
        		        		if(!(email_to_id.containsKey(email))) {//this checks if the email is also pointing to some othe account
        		        			email_to_id.put(email, index);
        		        			index++;
        		        		}
        		        		dsu.union(email_to_id.get(account.get(1)), email_to_id.get(email));//grouping each email with the 1st email of that account.
        		        	}
        		        }
        		        
        		        Map<Integer,List<String>> idToEmails=new HashMap<Integer, List<String>>();
        		        for(String email:email_to_name.keySet()) {//mapping id to emails,here the parent emails id will have list of all emails including parent and other dependent ones.
        		        	int id=dsu.find(email_to_id.get(email));
        		        	if(idToEmails.get(id)==null)
        		        		idToEmails.put(id, new ArrayList<String>());
        		        	idToEmails.get(id).add(email);
        		        }
        		        
        		        for(List<String> comp:idToEmails.values()) {
        		        	Collections.sort(comp);
        		        	comp.add(0, email_to_name.get(comp.get(0)));//adding name of account holder at the start of list of emails
        		        }
        		        
        		        return new ArrayList(idToEmails.values());
        		    }
        			
        			class accountsMergeDSU{
        				int [] parent;
        				public accountsMergeDSU() {
        					this.parent=new int[1000];
        					for(int i=0;i<1000;i++)
        						parent[i]=i;
        				}
        				
        				int find(int x) {
        					if(parent[x] != x)
        						parent[x]=find(parent[x]);
        					return parent[x];
        				}
        				void union(int x,int y) {
        					parent[find(x)]=find(y);
        				}
        			}
//        			On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
//
//        			Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
//
//        			You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
//
//        			Example 1:
//
//        			Input: [[0,2],[1,3]]
//        			Output: 3
//        			Explanation:
//        			At time 0, you are in grid location (0, 0).
//        			You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
//
//        			You cannot reach point (1, 1) until time 3.
//        			When the depth of water is 3, we can swim anywhere inside the grid.
//        			Example 2:
//
//        			Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//        			Output: 16
//        			Explanation:
//        			 0  1  2  3  4
//        			24 23 22 21  5
//        			12 13 14 15 16
//        			11 17 18 19 20
//        			10  9  8  7  6
//
//        			The final route is marked in bold.
//        			We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
//        			Note:
//
//        			2 <= N <= 50.
//        			grid[i][j] is a permutation of [0, ..., N*N - 1].
        			
        			//Binary Search approach---https://www.youtube.com/watch?v=rStVWdt-OwY
        			
        			public int swimInWaterBS(int[][] grid) {
        				boolean [][] vis;
        		        int l=0;
        		        int r=50*50; //as N value can be maximum 50
        		        int n=grid.length;
        		        int m=grid[0].length;
        		        while(l<r) {
        		        	int mid=(l+r)/2;
        		        	vis=new boolean [n][m];
        		        	swimDFS(mid, 0, 0, grid, vis);
        		        	if(vis[n-1][m-1])
        		        		r=mid-1;
        		        	else
        		        		l=mid+1;
        		        }
        		        
        		        return l;
        		      
        		    }
        			
        			void swimDFS(int time,int x,int y,int[][] grid,boolean[][] visited) {
        				if(!visited[x][y] && grid[x][y]<=time) {
        					visited[x][y]=true;
        					if(x+1<grid.length) swimDFS(time, x+1, y, grid, visited);//dfs in all 4 direction
        					if(x-1>=0) swimDFS(time, x-1, y, grid, visited);
        					if(y+1<grid[0].length) swimDFS(time, x, y+1, grid, visited);
        					if(y-1>=0) swimDFS(time, x, y-1, grid, visited);
        				}
        			}
        			
//        			Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
//
//        			Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.
//
//        			 
//
//        			Example 1:
//
//        			Input: ["a==b","b!=a"]
//        			Output: false
//        			Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
//        			Example 2:
//
//        			Input: ["b==a","a==b"]
//        			Output: true
//        			Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
//        			Example 3:
//
//        			Input: ["a==b","b==c","a==c"]
//        			Output: true
//        			Example 4:
//
//        			Input: ["a==b","b!=c","c==a"]
//        			Output: false
//        			Example 5:
//
//        			Input: ["c==c","b==d","x!=z"]
//        			Output: true
//        			 
//
//        			Note:
//
//        			1 <= equations.length <= 500
//        			equations[i].length == 4
//        			equations[i][0] and equations[i][3] are lowercase letters
//        			equations[i][1] is either '=' or '!'
//        			equations[i][2] is '='
        			
        			class equationsPossibleDSU{
        				int [] parent;
        				Map<Character,Character> toParent;
        				public equationsPossibleDSU() {
        					this.toParent=new HashMap<Character, Character>();
        					
        				}
        				
        				char find(char ch) {
        					if(toParent.containsKey(ch)) {
        						if(toParent.get(ch)!=ch)
            						return find(toParent.get(ch));
        						return toParent.get(ch);
        					}
        					
        					toParent.put(ch, ch);
        					return ch;
        					
        				}
        				void union(char c1,char c2) {
        					if(find(c1)==find(c2))
        						return;
        					toParent.put((find(c1)),find(c2));
        				}
        			}
        			
        			public boolean equationsPossible(String[] equations) {
//        				Map<Character, Integer> charToId=new HashMap<Character, Integer>();
//        				int id=0;
//        		        for(String st:equations) {
//        		        	charToId.putIfAbsent(st.charAt(0), id);
//        		        	id++;
//        		        }
//        		        Map<Character, Set<Character>> charToEqual=new HashMap<Character, Set<Character>>();
//        		        Map<Character, Set<Character>> charToNotequal=new HashMap<Character, Set<Character>>();
//        		        
//        		        int n=charToId.size();
//        		        equationsPossibleDSU dsu=new equationsPossibleDSU(n);
//        		        for(String st:equations) {
//        		        	if(st.charAt(1)=='=') {
//        		        		if(charToNotequal.containsKey(st.charAt(0)) && charToNotequal.get(st.charAt(0)).contains(st.charAt(3))||
//        		        				charToNotequal.containsKey(st.charAt(3)) && charToNotequal.get(st.charAt(3)).contains(st.charAt(0)))
//        		        			return false;
//        		        		if(dsu.find(charToId.get(st.charAt(0)))==dsu.find(st.charAt(charToId.get(st.charAt(3)))))
//        		        				continue;
//        		        		else {
//        		        			dsu.union(charToId.get(st.charAt(0)),charToId.get(st.charAt(3)));
//        		        			charToEqual.putIfAbsent(st.charAt(0), new HashSet<Character>());
//        		        			charToEqual.get(st.charAt(0)).add(st.charAt(3));
//        		        			charToEqual.putIfAbsent(st.charAt(3), new HashSet<Character>());
//        		        			charToEqual.get(st.charAt(3)).add(st.charAt(0));
//        		        		}		
//        		        	}else {
//        		        		if(charToEqual.containsKey(st.charAt(0)) && charToEqual.get(st.charAt(0)).contains(st.charAt(3))||
//        		        				charToEqual.containsKey(st.charAt(3)) && charToEqual.get(st.charAt(3)).contains(st.charAt(0)))
//        		        			return false;
//        		        		charToNotequal.putIfAbsent(st.charAt(0), new HashSet<Character>());
//        		        		charToNotequal.get(st.charAt(0)).add(st.charAt(3));
//        		        		charToNotequal.putIfAbsent(st.charAt(3), new HashSet<Character>());
//        		        		charToNotequal.get(st.charAt(3)).add(st.charAt(0));
//        		        		
//        		        	}
//        		        }
//        		        return true;
        				equationsPossibleDSU dsu=new equationsPossibleDSU();
        				for(String st:equations) {
        					if(st.charAt(1)=='=') {
        						dsu.union(st.charAt(0), st.charAt(3));
        					}	
        				}
        				
        				for(String st:equations) {
        					if(st.charAt(1)=='!')
        						if(dsu.find(st.charAt(0))==dsu.find(st.charAt(3)))
        							return false;
        				}
        				
        				return true;
        		    }
        			
//        			You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
//
//        					You can swap the characters at any pair of indices in the given pairs any number of times.
//
//        					Return the lexicographically smallest string that s can be changed to after using the swaps.
//
//        					 
//
//        					Example 1:
//
//        					Input: s = "dcab", pairs = [[0,3],[1,2]]
//        					Output: "bacd"
//        					Explaination: 
//        					Swap s[0] and s[3], s = "bcad"
//        					Swap s[1] and s[2], s = "bacd"
//        					Example 2:
//
//        					Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
//        					Output: "abcd"
//        					Explaination: 
//        					Swap s[0] and s[3], s = "bcad"
//        					Swap s[0] and s[2], s = "acbd"
//        					Swap s[1] and s[2], s = "abcd"
//        					Example 3:
//
//        					Input: s = "cba", pairs = [[0,1],[1,2]]
//        					Output: "abc"
//        					Explaination: 
//        					Swap s[0] and s[1], s = "bca"
//        					Swap s[1] and s[2], s = "bac"
//        					Swap s[0] and s[1], s = "abc"
//        					 
//
//        					Constraints:
//
//        					1 <= s.length <= 10^5
//        					0 <= pairs.length <= 10^5
//        					0 <= pairs[i][0], pairs[i][1] < s.length
//        					s only contains lower case English letters.
        			//https://www.youtube.com/watch?v=cc3eUjjbwSI
       class smallestStringWithSwapsDSU {
        	int[] parent;
        	public smallestStringWithSwapsDSU(int n) {
        		this.parent=new int[n];
        		for(int i=0;i<n;i++)
        			parent[i]=i;
        	}
        	
        	int find(int x) {
        		if(parent[x]!=x)
        			parent[x]=find(parent[x]);
        		return parent[x];
        	}
        	
        	void union(int x,int y) {
        		parent[find(x)]=find(y);
        	}
       }
       public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        	int n=s.length();
        	smallestStringWithSwapsDSU dsu=new smallestStringWithSwapsDSU(n);
        	for(int i=0;i<pairs.size();i++) {
        		dsu.union(pairs.get(i).get(0), pairs.get(i).get(1));
        	}
        	
        	Map<Integer,Queue<Character>> parentToChar=new HashMap<Integer, Queue<Character>>();
        	for(int i=0;i<n;i++) {
        		parentToChar.putIfAbsent(dsu.find(i), new PriorityQueue<Character>());
        		parentToChar.get(dsu.find(i)).add(s.charAt(i));
        	}
        	
        	StringBuilder sb=new StringBuilder();
        	for(int i=0;i<n;i++) {
        		sb.append(parentToChar.get(dsu.find(i)).poll());
        	}
        	
        	return sb.toString();
       }
       
       //count number of coumponents in an undireced graph
       //edges = [[0, 1], [1, 2], [3, 4]] ,n=5
       
       class ComponentDSU{
    	   int [] parent;
    	   public ComponentDSU(int n) {
    		   this.parent=new int[n];
    		   for(int i=0;i<n;i++)
    			   parent[i]=i;
    	   }
    	   
    	   int find(int x) {
       		if(parent[x]!=x)
       			parent[x]=find(parent[x]);
       		return parent[x];
       	}
       	
       	void union(int x,int y) {
       		parent[find(x)]=find(y);
       	}
       }
       
       int countComponent(int n,int [][] edges) {
    	   int count =n;
    	   ComponentDSU dsu=new ComponentDSU(n);
    	   for(int [] edge:edges) {
    		   int s=edge[0];
    		   int d=edge[1];
    		   
    		   dsu.union(s, d);
    		   if(dsu.find(s)==dsu.find(d))
    			   count--;
    		   
    	   }
    	   
    	   return count;
       }
       
//       Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
//
//    		   An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//    		    
//
//    		   Example 1:
//
//    		   Input: grid = [
//    		     ["1","1","1","1","0"],
//    		     ["1","1","0","1","0"],
//    		     ["1","1","0","0","0"],
//    		     ["0","0","0","0","0"]
//    		   ]
//    		   Output: 1
//    		   Example 2:
//
//    		   Input: grid = [
//    		     ["1","1","0","0","0"],
//    		     ["1","1","0","0","0"],
//    		     ["0","0","1","0","0"],
//    		     ["0","0","0","1","1"]
//    		   ]
//    		   Output: 3
//    		    
//
//    		   Constraints:
//
//    		   m == grid.length
//    		   n == grid[i].length
//    		   1 <= m, n <= 300
//    		   grid[i][j] is '0' or '1'.
       
       class IslandsDSU{
    	   int [] parent;
    	   int components;
    	  
    	   public IslandsDSU(int n) {
    		   this.components=n;
    		   this.parent=new int[n];
    		   for(int i=0;i<n;i++)
    			   parent[i]=i;
    		   
    	   }

		public int getComponents() {
			return components;
		}

		public void setComponents(int components) {
			this.components = components;
		}
		int find(int x) {
       		if(parent[x]!=x)
       			parent[x]=find(parent[x]);
       		return parent[x];
       	}
       	
       	void union(int x,int y) {
       		parent[find(x)]=find(y);
       		components--;
       	}
    	   
    	   
       }
        		
       public int numIslandsDSU(char[][] grid) {
    	   int r=grid.length;
    	   int c=grid[0].length;
    	   
    	   int size=r*c;
    	   
    	   IslandsDSU dsu=new IslandsDSU(size);
    	   int comps=0;
    	   for(int i=0;i<r;i++) {
    		   for(int j=0;j<c;j++) {
    			   if(grid[i][j]==1)
    				   comps++;
    		   }
    	   }
    	   Map<List<Integer>,Integer> gridToId=new HashMap<List<Integer>, Integer>();
    	  
    	   
//    	   for(int k=0;k<size;k++) {
//    		   for(int i=0;i<r;i++) {
//    			   for(int j=0;j<c;j++) {
//    				   List<Integer> pos=new ArrayList<Integer>();
//    				   pos.add(i);
//    				   pos.add(j);
//    				   gridToId.put(pos, k);
//    			   }
//    		   }
//    	   }
    	   
    	   int [] [] dirs= {{0,1},{0,-1},{1,0},{-1,0}};
    	   
    	   for(int i=0;i<r;i++) {
    		   for(int j=0;j<c;j++) {
    			   if(grid[i][j]==0)
    				   continue;
    			   for(int[] dir:dirs) {
    				   int row=i+dir[0];
    				   int col=j+dir[1];
    				   
    				   if(row>=0 && row<r && col>=0 && col<c) {
    					   int x=i*c+j;
    					   int y=row*c+col;//these two lines are to identify each grid[i][j] uniquely with single 
    					   dsu.union(x, y);
    				   }
    			   }
    		   }
    	   }
    	   
    	   return dsu.getComponents();
       }
       
       //Kruskal minimum spanning tree algorithm
       //https://www.youtube.com/watch?v=Ub-fJ-KoBQM
       class Edge implements Comparable<Edge>{
    	   int src;
    	   int dst;
    	   int wt;
		@Override
		public int compareTo(Edge o) {
			return this.wt-o.wt;
		}
       }
       
       class KruskalDSU{
    	   int [] parent;
    	   int [] rank;
    	   
    	   public KruskalDSU(int n) {
    		   this.parent=new int [n];
    		   this.rank=new int [n];
    		   for(int i=0;i<n;i++)
    			   parent[i]=i;
    	   }
    	   
    	   int find(int x) {
    		   if(parent[x]!=x)
    			   parent[x]=find(parent[x]);
    		   return parent[x];
    	   }
    	   
    	   boolean unionByRank(int x,int y) {
    		   int px=find(x);
    		   int py=find(y);
    		   
    		   if(px==py)
    			   return false;
    		   if(rank[px]==rank[py]) {
    			   parent[px]=py;
    			   rank[py]++;
    		   }else if(rank[px]>rank[py]) {
    			   parent[py]=px;
    		   }else
    			   parent[px]=py;
    		   return true;
    	   }
       }
       
       List<Edge> kruskalsMST(List<Edge> edges,int V,int E) {
    	   List<Edge> res=new ArrayList<BFS.Edge>();
    	   PriorityQueue<Edge> edgeQ=new PriorityQueue<Edge>();
    	   for(int i=0;i<edges.size();i++) {
    		   edgeQ.add(edges.get(i));
    	   }
    	   KruskalDSU dsu=new KruskalDSU(V);
    	   int i=0,j=0;
    	   while(i<V-1 && j<E) {
    		   Edge temp=edgeQ.poll();
    		   
    		   int fromP=dsu.find(temp.src);
    		   int toP=dsu.find(temp.dst);
    		   
    		   if(!dsu.unionByRank(fromP, toP)) {
    			   ++j;//counting no of edges
    			   continue;
    		   }
    		   res.add(temp);
    		   ++i;  
    	   }
    	   
    	   return res;
       }
       
//       You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
//
//    		   The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
//
//    		   Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
//
//    		    
//
//    		   Example 1:
//    		   Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//    		   Output: 20
//    		   Explanation:
//
//    		   We can connect the points as shown above to get the minimum cost of 20.
//    		   Notice that there is a unique path between every pair of points.
//    		   https://leetcode.com/problems/min-cost-to-connect-all-points/
       
       public int minCostConnectPoints(int[][] points) {
    	   PriorityQueue<Edge> edgeQ=new PriorityQueue<Edge>();
    	   int res=0;
           for(int i=0;i<points.length;i++) {
        	   for(int j=i+1;j<points.length;j++) {
        		   Edge temp=new Edge();
            	   temp.dst=i;
            	   temp.src=j;
            	   temp.wt=(Math.abs(points[i][0]-points[i+1][0])+Math.abs(points[i][1]-points[i+1][1]));
            	   edgeQ.add(temp);
        	   }
           }
           KruskalDSU dsu=new KruskalDSU(points.length-1);
    	   int i=0,j=0;
    	   while(i<points.length-1) {
    		   Edge temp=edgeQ.poll();
    		   int fromP=dsu.find(temp.src);
    		   int toP=dsu.find(temp.dst);
    		   
    		   if(!dsu.unionByRank(fromP, toP)) 
    			   continue;
    		   
    		   res+=temp.wt;
    		   ++i;  
    	   }
    	   return res;
       }
       
//       There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
//
//       Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
//
//       Example 1:
//       Input: 
//       n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//       src = 0, dst = 2, k = 1
//       Output: 200
//       https://leetcode.com/problems/cheapest-flights-within-k-stops/
       //https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/918302/Using-Map-and-PriorityQueue
       
       class pathNode{
    	   int city;
    	   int cost;
    	   int stops;
		public pathNode(int city, int cost, int stops) {
			super();
			this.city = city;
			this.cost = cost;
			this.stops = stops;
		}  
       }
       
       public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
           if(flights.length==0 || flights==null)
        	   return -1;
           Map<Integer,List<int[]>> graph=new HashMap<Integer, List<int[]>>();
           for(int[] flight:flights) {
        	   if(!graph.containsKey(flight[0])) {
        		   graph.put(flight[0], new ArrayList<int[]>());
        	   }
        	   graph.get(flight[0]).add(new int[] {flight[1],flight[2]});
           }
           
           PriorityQueue<pathNode> pq=new PriorityQueue<BFS.pathNode>((a,b)->(a.cost-b.cost));
           pq.add(new pathNode(src, 0, -1));
           while(!pq.isEmpty()) {
        	   pathNode curr=pq.poll();
        	   if(curr.city==dst)
        		   return curr.cost;
        	   if(curr.stops<K) {
        		   List<int[]> nexts=graph.getOrDefault(curr.city,new LinkedList<int[]>());
        		   for(int[] next:nexts) {
        			   pq.add(new pathNode(next[0], next[1]+curr.city, curr.stops+1));
        		   }
        	   }
           }
           return -1;
       }
       
//       Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0, 0] and ending at [R-1, C-1].
//
//       The score of a path is the minimum value in that path. For example, the value of the path 8 → 4 → 5 → 9 is 4.
//
//       A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
//
//       Note:
//
//       1 <= R, C <= 100
//       0 <= A[i][j] <= 10^9
       //https://www.junhaow.com/lc/problems/heap/1102_path-with-maximum-minimum-value.html
       //using Priority queue
       public int maximumMinimumPathPQ(int[][] A) {
    	   if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
    		    return 0;
    		  }
    	   int row=A.length,col=A[0].length;
    	   PriorityQueue<int[]> pq=new PriorityQueue<int[]>((o1,o2)->{//max heap
    		   return A[o2[0]][o2[1]]-A[o1[0]][o1[1]];
    	   });
    	   
    	   boolean [][] visited=new boolean[row][col];
    	   int score=Integer.MAX_VALUE;
    	   int [][] dirs= {{1,0},{-1,0},{0,1},{0,-1}};
    	   pq.add(new int[]{0,0});
    	   while(!pq.isEmpty()) {
    		   int []p=pq.poll();
    		   visited[p[0]][p[1]]=true;
    		   score=Math.min(score, A[p[0]][p[1]]);
    		   if(p[0]==row-1 && p[1]==col-1)
    			   break;
    		   for(int[] dir:dirs) {
    			   int r=p[0]+dir[0];
    			   int c=p[1]+dir[1];
    			   if(r>=0 && r<=row-1 && c>=0 && c<=col-1 && visited[r][c]==false)
    				   pq.add(new int[] {r,c});
    		   }
    		   
    	   }
    	   
    	   return score;
       }
       
       //using dynaminc programming
       public int maximumMinimumPathDP(int[][] A) {
    	   int [][] dp=new int [A.length][A[0].length];
    	   int row=A.length,col=A[0].length;
    	   int score;
    	   dp[0][0]=Integer.MAX_VALUE;
    	   for(int i=1;i<row-1;i++)
    		   dp[i][0]=Math.min(dp[i-1][0],A[i][0]);
    	   
    	   for(int i=1;i<col-1;i++)
    		   dp[0][i]=Math.min(dp[0][i-1],A[0][i]);
    	   
    	   for(int i=1;i<row;i++) {
    		   for(int j=1;j<col;j++) {
    			   if(i==row-1 && j==col-1) {
    				   dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
    			   }else {
    			   int score1=Math.min(dp[i-1][j], A[i][j]);
    			   int score2=Math.min(dp[i][j-1], A[i][j]);
    			   dp[i][j]=Math.max(score1, score2);
    			   }
    		   }
    	   }
    	   
    	   return dp[row-1][col-1];
       }
       
//       There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents 
//       a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
//
//    Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between 
//    any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the
//    computers connected. If it's not possible, return -1. 
//    https://leetcode.com/problems/number-of-operations-to-make-network-connected/
       class MakeConnectedDSU{
    	   int [] parent;
    	   int components;
    	  
    	   public MakeConnectedDSU(int n) {
    		   this.components=n;
    		   this.parent=new int[n];
    		   for(int i=0;i<n;i++)
    			   parent[i]=i;
    		   
    	   }

		public int getComponents() {
			return components;
		}

		public void setComponents(int components) {
			this.components = components;
		}
		int find(int x) {
       		if(parent[x]!=x)
       			parent[x]=find(parent[x]);
       		return parent[x];
       	}
       	boolean unionByRank(int x,int y) {
 		   int px=find(x);
 		   int py=find(y);
 		   
 		   if(px==py)
 			   return false;
 		   parent[px]=py;
 		  components--;
 		   return true;
 	   }
    	   
    	   
       }
       
       public int makeConnected(int n, int[][] connections) {
           MakeConnectedDSU dsu=new MakeConnectedDSU(n);
           int noOfCycle=0;
           for(int [] connection:connections) {
        	   if(!dsu.unionByRank(connection[0], connection[1]))
        		   noOfCycle++;
           }
           if(noOfCycle>=dsu.getComponents()-1)
        	   return dsu.getComponents()-1;
           if(dsu.getComponents()==1)
        	   return 0;
           if(noOfCycle<dsu.getComponents()-1)
        	   return -1;
           return 0;
       }
       
//       On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
//
//       Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
//
//       What is the largest possible number of moves we can make?
//
//        
//
//       Example 1:
//
//       Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//       Output: 5
//       Example 2:
//
//       Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
//       Output: 3
//       Example 3:
//
//       Input: stones = [[0,0]]
//       Output: 0
       //https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
       class RemoveStonesDSU{
    	   int [] parent;
    	   int components;
    	  
    	   public RemoveStonesDSU(int n) {
    		   this.parent=new int[n];
    		   for(int i=0;i<n;i++)
    			   parent[i]=i;
    		   
    	   }
    	   int find(int x) {
          		if(parent[x]!=x)
          			parent[x]=find(parent[x]);
          		return parent[x];
          	}
          	void union(int x,int y) {
    		   int px=find(x);
    		   int py=find(y);
    		   parent[px]=py;
          	}

		}
		
       	
       public int removeStones(int[][] stones) {
           int n=stones.length;
           if(n==0||n==1)
        	   return 0;
           
           RemoveStonesDSU dsu=new RemoveStonesDSU(20000);
           for(int[] cord:stones)
        	   dsu.union(cord[0], cord[1]+10000);
           
           HashSet<Integer> seen=new HashSet<Integer>();
           for(int [] cord:stones)
        	   seen.add(dsu.find(cord[0]));
           
           return n-seen.size();
       }
//       A certain bug's home is on the x-axis at position x. Help them get there from position 0.
//
//       The bug jumps according to the following rules:
//
//       It can jump exactly a positions forward (to the right).
//       It can jump exactly b positions backward (to the left).
//       It cannot jump backward twice in a row.
//       It cannot jump to any forbidden positions.
//       The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
//
//       Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.
//
//        
//
//       Example 1:
//
//       Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
//       Output: 3
//       Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
      
       public int minimumJumps(int[] forbidden, int a, int b, int x) {
           int steps=0,furthest=x+a+b;
           Queue<Map<Integer, Integer>> q=new LinkedList<Map<Integer,Integer>>();
           q.add(new HashMap<Integer, Integer>());
           q.peek().put(0, 0);
           
           Set<Map<Integer, Integer>> seen=new HashSet<Map<Integer,Integer>>();
           for(int i:forbidden) {
        	   Map<Integer, Integer> H1=new HashMap<Integer, Integer>();
        	   Map<Integer, Integer> H2=new HashMap<Integer, Integer>();
        	   H1.put(0, i);H2.put(1, i);
        	   seen.add(H1);
        	   seen.add(H2);
        	   furthest=Math.max(furthest, i+a+b);
           }
           
           while(!q.isEmpty()) {
        	   for(int sz=q.size();sz>0;--sz) {
        		   Map<Integer,Integer> hm=q.poll();
        		   int dir=hm.keySet().stream().findFirst().get();
        		   int pos=hm.get(dir);
        		   if(pos==x)
        			   return steps;
        		   
        		   Map<Integer, Integer> forward=new HashMap<Integer, Integer>();
        		   forward.put(0,pos+a);
        		   Map<Integer, Integer> backward=new HashMap<Integer, Integer>();
        		   backward.put(1, pos-b);
        		   
        		   if(pos+a<=furthest && seen.add(forward))
        			   q.add(forward);
        		   
        		   if(dir==0 && pos-b >=0 && seen.add(backward))
        			   q.add(backward);
        	   }
        	   ++steps;
           }
           
           return -1;
       }
       
//       A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
//
//       Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
//
//       Return a list of all MHTs' root labels. You can return the answer in any order.
//
//       The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
//
//        
//
//       Example 1:
//
//
//       Input: n = 4, edges = [[1,0],[1,2],[1,3]]
//       Output: [1]
//       Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
//       https://leetcode.com/problems/minimum-height-trees/
//    	   https://www.youtube.com/watch?v=qbpP8GIZiqQ
    	   
    	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    		List<Integer> ans=new ArrayList<Integer>();
           
           if(n<2) {
        	   for(int i=0;i<n;i++)
        		   ans.add(i);
        	   return ans;
           }
           
           //making adjucency list from given edge list
           ArrayList<Set<Integer>> adj=new ArrayList<Set<Integer>>();
           for(int i=0;i<n;i++) {
        	   adj.add(new HashSet<Integer>());
           }
           
           for(int[] edge:edges) {
        	   adj.get(edge[0]).add(edge[1]);
        	   adj.get(edge[1]).add(edge[0]);
           }
           
           //gather the first layer of leaves
           ArrayList<Integer> leaves=new ArrayList<Integer>();
           for(int i=0;i<n;i++) {
        	   if(adj.get(i).size()==1)
        		   leaves.add(i);
           }
           
           int remeiningNodes=n;
           while(remeiningNodes>2) {
        	   remeiningNodes -=leaves.size();
        	   ArrayList<Integer> newLeaves = new ArrayList<>();
        	   //remove current leaves along with the edges
        	   for(Integer leaf:leaves) {
        		   // the only neighbor left for the leaf node
        		   Integer neighbour=adj.get(leaf).iterator().next();
        		// remove the edge along with the leaf node
        		   adj.get(neighbour).remove(leaf);
        		   if(adj.get(neighbour).size()==1)//identifying the new leaves
        			   newLeaves.add(neighbour);
        	   }
        	   
        	   leaves=newLeaves;
           }
           
           return leaves;
           
       }
    	
//    	Given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a lower-case character given in the string labels (i.e. The node with the number i has the label labels[i]).
//
//    	The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
//
//    	Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
//
//    	A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
//    	https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/

    	public int[] countSubTrees(int n, int[][] edges, String labels) {
            List<List<Integer>> adj=new ArrayList<List<Integer>>();
            for(int i=0;i<n;i++) {
         	   adj.add(new ArrayList<Integer>());
            }
            
            for(int[] edge:edges) {
         	   adj.get(edge[0]).add(edge[1]);
         	   adj.get(edge[1]).add(edge[0]);
            }
            int [] res=new int[n];
            countSubTreesUtil(adj, res, 0, -1, labels);
            return res;
            
        }
    	
    	int[] countSubTreesUtil(List<List<Integer>> adj,int[] res,int curr_node,int parent,String labels) {
    		int[] counts=new int[26];
    		char ch=labels.charAt(curr_node);
    		for(int v:adj.get(curr_node)) {
    			if(v==parent)
    				continue;//ignoring the parent node as its not part of subtree
    			int[] next=countSubTreesUtil(adj, res, v, curr_node, labels);
    			for(int i=0;i<26;i++)
    				counts[i] += next[i];
    		}
    		
    		counts[ch-'a']++;//incrementing the label array as we count the label of root of subtree also.so by default all counts will be min 1
    		res[curr_node]=counts[ch-'a'];
    		return counts;
    	}
    	
//    	http://lixinchengdu.github.io/algorithmbook/leetcode/minimum-knight-moves.html
//    		Minimum Knight Moves
//    		Leetcode 1142
    	
    	int minKnightMoves(int x, int y) {
    		Set<int[]> visited=new HashSet<int[]>();
    		Queue<int []> q=new LinkedList<int[]>();
    		int[] root= {0,0};
    		visited.add(root);
    		q.add(root);
    		int level=0;
    		int[][] directions = {
    		        {1, 2}, 
    		        {2, 1},
    		        {2, -1},
    		        {1, -2},
    		        {-1, -2},
    		        {-2, -1},
    		        {-2, 1},
    		        {-1, 2}
    		    };
    		while(!q.isEmpty()) {//BFS
    			int len=q.size();
    			for(int i=0;i<len;i++) {
    				int [] temp=q.poll();
    				if(x==Math.abs(temp[0]) && y==Math.abs(temp[1])) return level;
    				if(visited.contains(temp)) continue;
    				visited.add(temp);
    				for(int j=0;j<directions.length;j++) {
    					int[] pos=new int[2];
    					pos[0]=temp[0]+directions[j][0];
    					pos[1]=temp[1]+directions[j][1];
    					q.add(pos);
    				}
    			}
    			++level;
    		}
    		return -1;
    	}
//    	In an N by N square grid, each cell is either empty (0) or blocked (1).
//
//    	A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
//
//    	Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
//    	C_1 is at location (0, 0) (ie. has value grid[0][0])
//    	C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
//    	If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
//    	Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
//    			https://leetcode.com/problems/shortest-path-in-binary-matrix/
    	//https://leetcode.com/problems/shortest-path-in-binary-matrix/discuss/942276/Java-BFS-simple-87-faster
    	
    	class Cell {        
    		   
    	    int x;
    	    int y;
    	    Cell(int x, int y){            
    	        this.x = x;
    	        this.y = y;
    	    }  
    	    
    	}
    	
    	public int shortestPathBinaryMatrix(int[][] grid) {
            int rows=grid.length;
            int cols=grid[0].length;
            
            LinkedList<Cell> q=new LinkedList<BFS.Cell>();
            int [][] dirs={{1,1}, {-1,-1}, {-1,1}, {1,-1}, {1, 0}, {-1, 0}, {0, 1}, {0,-1}};
            if(grid[0][0]==0) {
            	q.offer(new Cell(0, 0));
            	grid[0][0]=1;
            }
            
            while(!q.isEmpty()) {
            	Cell cell=q.poll();
            	if(cell.x==rows-1 && cell.y==cols-1)
            		return grid[cell.x][cell.y];
            	for(int []dir:dirs) {
            		int newX=dir[0];
            		int newY=dir[1];
            		if(newX>=0 && newX<rows && newY>=0 && newY<cols) {
            			if(grid[newX][newY]==0) {
            				q.offer(new Cell(newX,newY));
            				grid[newX][newY]=grid[cell.x][cell.y]++;
            			}
            		}
            	}
            }
            
            return -1;
        }
    	
//    	Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
//
//    	Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
//
//    	Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
//
//    	 
//
//    	Example 1:
//
//    	Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//    	Output: [0,1,-1]
//    	Example 2:
//
//    	Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
//    	Output: [0,1,-1]
//    	Example 3:
//
//    	Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
//    	Output: [0,-1,-1]
//    	Example 4:
//
//    	Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//    	Output: [0,1,2]
//    	Example 5:
//
//    	Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
//    	Output: [0,1,1]
//    	 
//
//    	Constraints:
//
//    	1 <= n <= 100
//    	red_edges.length <= 400
//    	blue_edges.length <= 400
//    	red_edges[i].length == blue_edges[i].length == 2
//    	0 <= red_edges[i][j], blue_edges[i][j] < n
    	//https://leetcode.com/problems/shortest-path-with-alternating-colors/discuss/910339/java-single-BFS-O(n)-time
    	class tupple{
    		int node;
    		char inColor;
    		int dist;
			public tupple(int node, char inColor, int dist) {
				super();
				this.node = node;
				this.inColor = inColor;
				this.dist = dist;
			}
    		
    	}
    	
    	public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
            if(n<=0||red_edges==null||blue_edges==null)
            	return null;
            
            //making adj list for red and blue edges
            List<List<Integer>> red_adj=new ArrayList<List<Integer>>();
            List<List<Integer>> blue_adj=new ArrayList<List<Integer>>();
            
            for(int i=0;i<n;i++) {
            	red_adj.add(new ArrayList<Integer>());
            	blue_adj.add(new ArrayList<Integer>());
            }
            
            for(int[]red:red_edges)
            	red_adj.get(red[0]).add(red[1]);
            for(int[]blue:blue_edges)
            	blue_adj.get(blue[0]).add(blue[1]);
            Queue<tupple> q=new LinkedList<BFS.tupple>();
            q.offer(new tupple(0, 'R', 0));
            int[] output=new int[n];
            Arrays.fill(output, -1);
            output[0]=0;
            boolean[] redVisited = new boolean[n];
            boolean[] blueVisited = new boolean[n];
            redVisited[0]=blueVisited[0]=true;
            while(!q.isEmpty()) {
            	tupple t=q.poll();
            	if(output[t.node]==-1)
            		output[t.node]=t.dist;
            	
            	for(int child:red_adj.get(t.node)) {
            		if((t.node==0 || t.inColor=='B')&&redVisited[t.node]==false) {
            			q.add(new tupple(child, 'R', t.dist+1));
            			redVisited[t.node]=true;
            		}
            	}
            	
            	for(int child:blue_adj.get(t.node)) {
            		if((t.node==0 || t.inColor=='R')&&blueVisited[t.node]==false) {
            			q.add(new tupple(child, 'R', t.dist+1));
            			blueVisited[t.node]=true;
            		}
            	}
            }
            return output;
        }
    	
//    	Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
//
//    	The distance between two adjacent cells is 1.
//    	https://leetcode.com/problems/01-matrix/
    	//https://leetcode.com/problems/01-matrix/solution/
    	
    	class MatrixNode{
    		int row;
    		int column;
			public MatrixNode(int row, int column) {
				super();
				this.row = row;
				this.column = column;
			}
    	}
    	
    	public int[][] updateMatrix(int[][] matrix) {
            int r=matrix.length;
            int c=matrix[0].length;
            int [][] dirs= {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};
            int [][] result=new int[r][c];
            boolean [][] visited=new boolean[r][c];
            Queue<MatrixNode> q=new LinkedList<BFS.MatrixNode>();
            q.offer(new MatrixNode(0, 0));
            while(!q.isEmpty()) {
            	MatrixNode temp=q.poll();
            	matrixBFS(q, temp, dirs, r, c, matrix, result,visited);
            }
            return result;
        }
    	
    	void matrixBFS(Queue<MatrixNode> q,MatrixNode node,int [][] dirs,int rows,int cols,int[][] matrix,int [][] result,boolean [][] visited) {
    		int r=node.row;
    		int c=node.column;
    		if(visited[r][c])
    			return;
    		if(matrix[r][c]==0)
        		result[r][c]=0;
        	
    		for(int[] dir:dirs) {
    			int x=r+dir[0];
    			int y=c+dir[1];
    			
    			if(x>=0 && x<rows && y>=0 && y<cols && !visited[x][y]) {
    				q.add(new MatrixNode(x, y));
    				if(matrix[r][c]==0) {
    	        		result[r][c]=0;
    	        		continue;
    				}
    				if(result[r][c]!=1) {
    					if(matrix[x][y]==0)
        					result[r][c]=1;
        				else {
        					matrixBFS(q, new MatrixNode(x, y), dirs, rows, cols, matrix, result,visited);
        				}
    				}
    			}
    		}
    	}
    	
    	class Pair01 {

    		int row;
    		int col;
    		int dist;

    		public Pair01(int row, int col, int dist) {
    			this.row = row;
    			this.col = col;
    			this.dist = dist;
    		}
    		}
    	
    	public int[][] updateMatrixSol(int[][] matrix) {
    	    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    		Queue<Pair01> queue = new LinkedList<Pair01>();
    		for (int i = 0; i < matrix.length; i++) {
    			for (int j = 0; j < matrix[0].length; j++)
    	            if(matrix[i][j] == 0)
    				    queue.add(new Pair01(i, j, 0));
    		}

    		while (!queue.isEmpty()) {

    			Pair01 pair = queue.remove();
    			for (int[] d : dir) {

    				int row = pair.row + d[0];
    				int col = pair.col + d[1];

    				if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || matrix[row][col] == 0
    						|| visited[row][col])
    					continue;

    				visited[row][col] = true;
    				matrix[row][col] = pair.dist + 1;
    				queue.add(new Pair01(row, col, pair.dist + 1));
    			}
    		}

    		return matrix;
    	}
    	
//    	You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge
//    			connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
//
//    			Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success
//    					probability.
//
//    			If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
//
//    			 
//
//    			Example 1:
//
//
//
//    			Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
//    			Output: 0.25000
//    			Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
//    			Example 2:
//
//
//
//    			Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
//    			Output: 0.30000
//    			Example 3:
//
//
//
//    			Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
//    			Output: 0.00000
//    			Explanation: There is no path between 0 and 2.
    	//https://leetcode.com/problems/path-with-maximum-probability/discuss/1021629/SHORT-DIJKSTRA'S-ALGO
    	public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
    		List<List<double[]>> adj=new ArrayList<List<double[]>>();
    		for(int i=0;i<n;i++)
    			adj.add(new ArrayList<double[]>());
    		for(int i=0;i<edges.length;i++) {//making adjucency list
    			adj.get(edges[i][0]).add(new double[] {edges[i][1]+0d,succProb[i]});
    			adj.get(edges[i][1]).add(new double[] {edges[i][0]+0d,succProb[i]});
    		}
    		
    		PriorityQueue<double[]> pq=new PriorityQueue<double[]>((a,b)->b[1]>a[1]?1:-1);
    		boolean visited[]=new boolean[n];
    		double[] prob = new double[n];
    		pq.add(new double[]{start+0d,1d});
    		while(!pq.isEmpty()) {
    			double[]x=pq.poll();
    			if(x[0]==end)
    				return x[1];
    			if(visited[(int)x[0]])
    				continue;
    			visited[(int)x[0]]=true;
    			prob[(int)x[0]]=x[1];
    			for(double[] neig:adj.get((int) x[0])) {
    				double currProb=x[1]*neig[1];
    				if(!visited[(int) neig[0]]&& currProb>prob[(int) neig[0]]) {
    					pq.add(new double[] {neig[0],currProb});
    					prob[(int) neig[0]]=currProb;
    				}
    			}
    		}
    		return 0;
    	}
    	
//    	In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
//
//    	Return the maximum amount of gold you can collect under the conditions:
//
//    	Every time you are located in a cell you will collect all the gold in that cell.
//    	From your position you can walk one step to the left, right, up or down.
//    	You can't visit the same cell more than once.
//    	Never visit a cell with 0 gold.
//    	You can start and stop collecting gold from any position in the grid that has some gold.
//    	 
//
//    	Example 1:
//
//    	Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
//    	Output: 24
//    	Explanation:
//    	[[0,6,0],
//    	 [5,8,7],
//    	 [0,9,0]]
//    	Path to get the maximum gold, 9 -> 8 -> 7.
//    	Example 2:
//
//    	Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
//    	Output: 28
//    	Explanation:
//    	[[1,0,7],
//    	 [2,0,6],
//    	 [3,4,5],
//    	 [0,3,0],
//    	 [9,0,20]]
//    	Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
//    	 
//
//    	Constraints:
//
//    	1 <= grid.length, grid[i].length <= 15
//    	0 <= grid[i][j] <= 100
//    	There are at most 25 cells containing gold.
    	
    	public int getMaximumGold(int[][] grid) {
         int rows=grid.length,cols=grid[0].length,ans=0;
         boolean [][] mat=new boolean[rows][cols];
         for(int i=0;i<rows;i++) {
        	 for(int j=0;j<cols;j++) {
        		 if(grid[i][j]==0)
        			 mat[i][j]=true;
        	 }
         }
         
         for(int i=0;i<rows;i++) {
        	 for(int j=0;j<cols;j++) {
        		ans=Math.max(ans, getMaximumGoldUtil(grid, mat, i, j));
        	 }
         }
         return ans;
        }
    	
    	int getMaximumGoldUtil(int[][] grid,boolean [][] mat,int row,int col) {
    		if(row<0||row>=grid.length||col<0||col>=grid[0].length||mat[row][col]==true)
    			return 0;
    		mat[row][col]=true;
    		PriorityQueue<Integer> pq=new PriorityQueue<Integer>(Collections.reverseOrder());
    		pq.add(getMaximumGoldUtil(grid, mat, row+1, col));
    		pq.add(getMaximumGoldUtil(grid, mat, row-1, col));
    		pq.add(getMaximumGoldUtil(grid, mat, row, col+1));
    		pq.add(getMaximumGoldUtil(grid, mat, row, col-1));
    		mat[row][col]=false;
    		return grid[row][col]+pq.poll();
    	}
    	
}


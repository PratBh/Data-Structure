
package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DFS {
//	There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, 
	//you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, 
//	you can at most move N times. Find out the number of paths to move the ball out of grid boundary. 
//	The answer may be very large, return it after mod 10^9 + 7.
//
//			 
//
//			Example 1:
//
//			Input: m = 2, n = 2, N = 2, i = 0, j = 0
//			Output: 6
//			https://leetcode.com/problems/out-of-boundary-paths/
	
	int M = 1000000007;

	  public int findPaths(int m, int n, int N, int i, int j) {
	    int[][][] memo = new int[m][n][N + 1];
	    for (int[][] l : memo) for (int[] sl : l) Arrays.fill(sl, -1);
	    return findPaths(m, n, N, i, j, memo);
	  }

	  public int findPaths(int m, int n, int N, int i, int j, int[][][] memo) {
	    if (i == m || j == n || i < 0 || j < 0) return 1;
	    if (N == 0) return 0;
	    if (memo[i][j][N] >= 0) return memo[i][j][N];
	    memo[i][j][N] = (
	        (findPaths(m, n, N - 1, i - 1, j, memo) + findPaths(m, n, N - 1, i + 1, j, memo)) % M +
	        (findPaths(m, n, N - 1, i, j - 1, memo) + findPaths(m, n, N - 1, i, j + 1, memo)) % M
	    ) % M;
	    return memo[i][j][N];
	  }
	  
//	  Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one
//	  square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
//
//			  Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to
//	  represent whether you could make one square using all the matchsticks the little match girl has.
//
//			  Example 1:
//			  Input: [1,1,2,2,2]
//			  Output: true
//
//			  Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
//			  Example 2:
//			  Input: [3,3,3,3,4]
//			  Output: false
//
//			  Explanation: You cannot find a way to form a square with all the matchsticks.
//			  Note:
//			  The length sum of the given matchsticks is in the range of 0 to 10^9.
//			  The length of the given matchstick array will not exceed 15.
	  
	  public boolean makesquare(int[] nums) {
		  if(nums==null||nums.length==0)
			  return false;
		    int perimeter=0;
		    int k=4;
		    for(int i=0;i<nums.length;i++)
		    	perimeter+=nums[i];
		    if(perimeter%4 != 0) return false;
		    boolean [] visited=new boolean[nums.length]; 
		    return makesquaredfs(nums, k, perimeter/4, 0, 0, visited);
		}

		public boolean makesquaredfs(int[] nums, int k, int target, int index, int sum, boolean[] visited) {
		    if(k==0)return true;
		    if(sum>target)return false;
		    if(sum==target) return makesquaredfs(nums, k-1, target, 0, 0, visited);
		    for(int i=index;i<nums.length;i++) {
		    	if(!visited[i]) {
		    		visited[i]=true;
		    		if(makesquaredfs(nums, k, target, i+1, sum+nums[i], visited)) return true;
		    		visited[i]=false;
		    	}
		    }
		    return false;
		}
		
//		Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
//
//		Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and 
		//is invalid, but 0 is valid.
//
//		You may return the answer in any order.
//
//		 
//
//		Example 1:
//
//		Input: n = 3, k = 7
//		Output: [181,292,707,818,929]
//		Explanation: Note that 070 is not a valid number, because it has leading zeroes.
//		Example 2:
//
//		Input: n = 2, k = 1
//		Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
//		Example 3:
//
//		Input: n = 2, k = 0
//		Output: [11,22,33,44,55,66,77,88,99]
//		Example 4:
//
//		Input: n = 2, k = 1
//		Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
//		Example 5:
//
//		Input: n = 2, k = 2
//		Output: [13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]
//		 
//
//		Constraints:
//
//		2 <= n <= 9
//		0 <= k <= 9 
		//https://leetcode.com/problems/numbers-with-same-consecutive-differences/solution/
		
		public int[] numsSameConsecDiff(int n, int k) {
	        if(n==1)
	        	return new int[] {0,1,2,3,4,5,6,7,8,9};
	        List<Integer> result=new ArrayList<Integer>();
	        for(int i=1;i<10;i++)
	        	numsSameConsecDiffDFS(n-1, k, i, result);
	        return result.stream().mapToInt(i->i).toArray();
	    }
		
		void numsSameConsecDiffDFS(int n,int k,int num,List<Integer> result) {
			if(n==0) {
				result.add(num);
				return;
			}
			List<Integer> nextDigits=new ArrayList<Integer>();
			int tailDigit=num%10;
			nextDigits.add(tailDigit+k);
			if(k!=0)
				nextDigits.add(tailDigit-k);
			for(int digit:nextDigits) {
				if(digit<10 && digit>=0) {
					int newNum=num*10+digit;
					numsSameConsecDiffDFS(n-1, k, newNum, result);
				}
			}
		}
		
//		There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the 
//		next room. 
//
//		Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length. 
//		A key rooms[i][j] = v opens the room with number v.
//
//		Initially, all the rooms start locked (except for room 0). 
//
//		You can walk back and forth between rooms freely.
//
//		Return true if and only if you can enter every room.
//
//		Example 1:
//
//		Input: [[1],[2],[3],[]]
//		Output: true
//		Explanation:  
//		We start in room 0, and pick up key 1.
//		We then go to room 1, and pick up key 2.
//		We then go to room 2, and pick up key 3.
//		We then go to room 3.  Since we were able to go to every room, we return true.
//		Example 2:
//
//		Input: [[1,3],[3,0,1],[2],[0]]
//		Output: false
//		Explanation: We can't enter the room with number 2.
		
		public boolean canVisitAllRooms(List<List<Integer>> rooms) {
			boolean[]seen=new boolean[rooms.size()];
			Stack<Integer> st=new Stack<Integer>();
			st.push(0);
			while(!st.isEmpty()) {
				int node=st.pop();
				if(!seen[node]) {
					seen[node]=true;
					for(int v:rooms.get(node))
						st.push(v);
				}
			}
			
			for(boolean v:seen) {
				if(!v)
					return false;
			}
			return true;
		}
		
//		Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
//		You may assume all four edges of the grid are surrounded by water.
//
//		Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
//
//		Example 1:
//
//		[[0,0,1,0,0,0,0,1,0,0,0,0,0],
//		 [0,0,0,0,0,0,0,1,1,1,0,0,0],
//		 [0,1,1,0,1,0,0,0,0,0,0,0,0],
//		 [0,1,0,0,1,1,0,0,1,0,1,0,0],
//		 [0,1,0,0,1,1,0,0,1,1,1,0,0],
//		 [0,0,0,0,0,0,0,0,0,0,1,0,0],
//		 [0,0,0,0,0,0,0,1,1,1,0,0,0],
//		 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
//		Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
//		Example 2:
//
//		[[0,0,0,0,0,0,0,0]]
//		Given the above grid, return 0.
//		Note: The length of each dimension in the given grid does not exceed 50.
		public int maxAreaOfIsland(int[][] grid) {
	      boolean [][] seen=new boolean [grid.length][grid[0].length];
	      int maxArea=0;
	      for(int r=0;r<grid.length;r++) {
	    	  for(int c=0;c<grid[0].length;c++) {
	    		  maxArea=Math.max(maxArea, area(seen, r, c, grid));
	    	  }
	      }
	      return maxArea;
	    }
		
		int area(boolean [][] seen,int r,int c,int[][] grid) {
			if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length ||
	                seen[r][c] || grid[r][c] == 0)
				return 0;
			seen[r][c]=true;
			return 1+area(seen, r+1, c, grid)+area(seen, r-1, c, grid)+area(seen, r, c+1, grid)+area(seen, r, c-1, grid);
			
		}
		
//		There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly 
//		with city c, then city a is connected indirectly with city c.
//
//		A province is a group of directly or indirectly connected cities and no other cities outside of the group.
//
//		You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and
//		isConnected[i][j] = 0 otherwise.
//
//		Return the total number of provinces.
//
//		 
//
//		Example 1:
//
//
//		Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//		Output: 2
//		Example 2:
//
//
//		Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//		Output: 3
//		
//		https://leetcode.com/problems/number-of-provinces/
		//https://leetcode.com/problems/number-of-provinces/discuss/1103777/Java-O(N2)-time-space-O(N)-100-speed-using-input-as-graph-matrix
		
		public int findCircleNum(int[][] M) {
			boolean []visited=new boolean[M.length];
			int count=0;
			for(int i=0;i<M.length;i++) {
				if(visited[i]==false) {
					findCircleDFSUtil(M, visited, i);
					count++;
				}
			}
			return count;
		}
		
		void findCircleDFSUtil(int[][] isConnected,boolean []visited,int city) {
			if(visited[city])
				return;
			visited[city]=true;
			for(int i=0;i<isConnected.length;i++) {
				if(i==city)
					continue;
				if(isConnected[city][i]==1)
					findCircleDFSUtil(isConnected, visited, i);
			}
		}
//		A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
//
//		Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1.
//		Also, it is guaranteed that the subordination relationships have a tree structure.
//
//		The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, 
//		and they will inform their subordinates, and so on until all employees know about the urgent news.
//
//		The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes,
//				all his direct subordinates can start spreading the news).
//
//		Return the number of minutes needed to inform all the employees about the urgent news.
//
//		 
//
//		Example 1:
//
//		Input: n = 1, headID = 0, manager = [-1], informTime = [0]
//		Output: 0
//		Explanation: The head of the company is the only employee in the company.
//		Example 2:
//
//
//		Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
//		Output: 1
//		Explanation: The head of the company with id = 2 is the direct manager of all the employees in the company and needs 1 minute to inform them all.
//		The tree structure of the employees in the company is shown.
//		Example 3:
//
//
//		Input: n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1]
//		Output: 21
//		Explanation: The head has id = 6. He will inform employee with id = 5 in 1 minute.
//		The employee with id = 5 will inform the employee with id = 4 in 2 minutes.
//		The employee with id = 4 will inform the employee with id = 3 in 3 minutes.
//		The employee with id = 3 will inform the employee with id = 2 in 4 minutes.
//		The employee with id = 2 will inform the employee with id = 1 in 5 minutes.
//		The employee with id = 1 will inform the employee with id = 0 in 6 minutes.
//		Needed time = 1 + 2 + 3 + 4 + 5 + 6 = 21.
//		Example 4:
//
//		Input: n = 15, headID = 0, manager = [-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6], informTime = [1,1,1,1,1,1,1,0,0,0,0,0,0,0,0]
//		Output: 3
//		Explanation: The first minute the head will inform employees 1 and 2.
//		The second minute they will inform employees 3, 4, 5 and 6.
//		The third minute they will inform the rest of employees.
//		Example 5:
//
//		Input: n = 4, headID = 2, manager = [3,3,-1,2], informTime = [0,0,162,914]
//		Output: 1076
//		 
//
//		Constraints:
//
//		1 <= n <= 105
//		0 <= headID < n
//		manager.length == n
//		0 <= manager[i] < n
//		manager[headID] == -1
//		informTime.length == n
//		0 <= informTime[i] <= 1000
//		informTime[i] == 0 if employee i has no subordinates.
//		It is guaranteed that all the employees can be informed.
//		https://leetcode.com/problems/time-needed-to-inform-all-employees/
		//https://leetcode.com/problems/time-needed-to-inform-all-employees/discuss/1093391/JAVAorBFSorEasy-logical-code
			
		public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
	        ArrayList<ArrayList<Integer>> adjList=new ArrayList<ArrayList<Integer>>();
	        for(int i=0;i<n;i++) {
	        	adjList.add(new ArrayList<Integer>());
	        }
	        for(int i=0;i<n;i++) {
	        	if(manager[i]!=-1) {
	        		adjList.get(manager[i]).add(i);
	        	}
	        }
	        
	        Queue<Integer> q=new LinkedList<Integer>();
	        q.add(headID);
	        int time=0;
	        int []delayTime=new int[informTime.length];
	        while(!q.isEmpty()) {
	        	int mng=q.poll();
	        	for(int sub:adjList.get(mng)) {
	        		int t=informTime[mng]+delayTime[mng];
	        		delayTime[sub] +=t;
	        		time=Math.max(time, delayTime[sub]);
	        		q.add(sub);
	        	}
	        }
	        return time;
	    }
		
//		You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi]
//				indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source. Note that you can swap elements at
//				a specific pair of indices multiple times and in any order.
//
//				The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different.
//				Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).
//
//				Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.
//
//				 
//
//				Example 1:
//
//				Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
//				Output: 1
//				Explanation: source can be transformed the following way:
//				- Swap indices 0 and 1: source = [2,1,3,4]
//				- Swap indices 2 and 3: source = [2,1,4,3]
//				The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
//				Example 2:
//
//				Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
//				Output: 2
//				Explanation: There are no allowed swaps.
//				The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
//				Example 3:
//
//				Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
//				Output: 0
		//https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/discuss/1032210/Faster-than-88-UnionFind-Map-JAVA
		public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
	        minimumHammingDistanceDisjoinSet uf=new minimumHammingDistanceDisjoinSet(source.length);
	        for(int arr[]:allowedSwaps)
	        	uf.union(arr[0], arr[1]);
	        Map<Integer, Map<Integer, Integer>> groupFreq = new HashMap();
	        for(int i=0;i<source.length;i++) {
	        	int p=uf.find(i);
	        	Map<Integer,Integer> mp=groupFreq.get(p);
	        	if(mp==null) {
	        		mp=new HashMap<Integer, Integer>();
	        		groupFreq.put(p, mp);
	        	}
	        	mp.put(source[i], mp.getOrDefault(source[i],0)+1);
	        }
	        int diff=0;
	        for(int i=0;i<target.length;i++) {
	        	int p=uf.find(i);
	        	Map<Integer,Integer> mp=groupFreq.get(p);
	        	if(mp.get(target[i])==0||mp.get(target[i])==null) {
	        		diff++;
	        		continue;
	        	}
	        	mp.put(target[i],mp.get(target[i])-1);
	        }
	        return diff;
	    }
		
		public class minimumHammingDistanceDisjoinSet{
	        int[] p, rank;
	        public minimumHammingDistanceDisjoinSet(int n){
	            p = new int[n];
	            rank = new int[n];
	            for(int i=0; i<n; i++) p[i] = i;
	        }
	        
	        
	        public boolean union(int x, int y){
	            int px = find(x);
	            int py = find(y);
	            if(px == py) return false;
	            if(rank[px]  >= rank[py]){
	                p[py] = px;
	                rank[px] ++;
	            }
	            else{
	                p[px] = py;
	                rank[py]++;
	            }
	            return true;
	        }
	        
	        public int find (int x){
	            while(p[x]!=x){
	                p[x] = p[p[x]];
	                return find(p[x]);
	            }
	            return x;
	        }
	    }
		
//		Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
//				Return the answer in any order.
//
//				A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
//				 
//
//				Example 1:
//
//				Input: digits = "23"
//				Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//				Example 2:
//
//				Input: digits = ""
//				Output: []
//				Example 3:
//
//				Input: digits = "2"
//				Output: ["a","b","c"]
				 //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
		public List<String> letterCombinations(String digits) {
			ArrayList<String> al=new ArrayList<String>();
			Map<Character, String> letters = Map.of(
			        '2', "abc", '3', "def", '4', "ghi", '5', "jkl", 
			        '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
			if(digits.length()==0)
				return al;
			letterCombinationsBackTrack(al, letters, 0, new StringBuilder(), digits);
			return al;
		}
		
		void letterCombinationsBackTrack(ArrayList<String> al,Map<Character, String> letters,int index,StringBuilder path,String digits) {
			if(path.length()==digits.length()) {
				al.add(path.toString());
				return;
			}
			
			String possibleLetters=letters.get(digits.charAt(index));
			for(char letter:possibleLetters.toCharArray()) {
				path.append(letter);
				letterCombinationsBackTrack(al, letters, index+1, path, digits);
				path.deleteCharAt(path.length()-1);
			}
		}
		
//		There are a total of n courses you have to take, labeled from 0 to n-1.
//
//		Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]
//
//		Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.
//
//		You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.
//
//		Return a list of boolean, the answers to the given queries.
//
//		Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.
//
//		 
//
//		Example 1:
//
//
//		Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
//		Output: [false,true]
//		Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
//		Example 2:
//
//		Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
//		Output: [false,false]
//		Explanation: There are no prerequisites and each course is independent.
//		Example 3:
//
//
//		Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
//		Output: [true,true]
//		Example 4:
//
//		Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
//		Output: [false,true]
//		Example 5:
//
//		Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
//		Output: [true,false,true,false]
//https://leetcode.com/problems/course-schedule-iv/discuss/1088374/Java-memo-technique...99.91
		public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
	       
			LinkedList<Boolean> res=new LinkedList<Boolean>();
			boolean[][] comp=new boolean[n][n];
			
			for(int []x:prerequisites)
				comp[x[0]][x[1]]=true;
			for(int j=0;j<n;j++) {
				for(int i=0;i<n;i++) {
					if(comp[i][j]) {
						for(int k=0;k<n;k++) {
							if(comp[j][k])
								comp[i][k]=true;
						}
					}
				}
			}
			
			for(int[]x:queries) {
				if(comp[x[0]][x[1]])
					res.add(true);
				else
					res.add(false);
			}
			return res;
	    }	
		
//		Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//				 
//
//				Example 1:
//
//				Input: nums = [100,4,200,1,3,2]
//				Output: 4
//				Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
		
		public int longestConsecutive(int[] nums) {
			Set<Integer> num_set=new HashSet<Integer>();
			for(int i:nums)
				num_set.add(i);
			
			int longSeqLen=0;
			
			for(int num:nums) {
				if(!num_set.contains(num-1)) {
					int currNum=num;
					int currSeqLen=1;
					
					while(num_set.contains(currNum+1)) {
						currNum+=1;
						currSeqLen+=1;
					}
					longSeqLen=Math.max(currSeqLen, longSeqLen);
				}
			}
			return longSeqLen;
		}
		
//		There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 
//		edges that connect the center node with every other node.
//
//		You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center
//		of the given star graph.
		
		public int findCenter(int[][] edges) {
	        int n=edges.length+1,center=0;
	        Map<Integer,List<Integer>> adj=new HashMap<Integer, List<Integer>>();
	        for(int[]edge:edges) {
	        	if(!adj.containsKey(edge[0]))
	        		adj.put(edge[0], new ArrayList<Integer>());
	        	if(!adj.containsKey(edge[1]))
	        		adj.put(edge[1], new ArrayList<Integer>());
	        	adj.get(edge[0]).add(edge[1]);
	        	adj.get(edge[1]).add(edge[0]);
	        }
	       for(int i=1;i<=n;i++) {
	    	   if(adj.get(i).size()==n-1) {
	    		   center=i;
	    		   break;
	    	   }
	       }
	       return center;
		}
		
//		There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, 
//		and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
//
//				A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge 
//				between zi and zi+1 where 0 <= i <= k-1.
//
//				The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a 
//				path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where
//				0 <= i <= k-1.
//
//				Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
//
//				 
//
//				Example 1:
//
//
//				Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
//				Output: 3
//				Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
//				1) 1 --> 2 --> 5
//				2) 1 --> 2 --> 3 --> 5
//				3) 1 --> 3 --> 5
//				Example 2:
//
//
//				Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
//				Output: 1
//				Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
//				 
//
//				Constraints:
//
//				1 <= n <= 2 * 104
//				n - 1 <= edges.length <= 4 * 104
//				edges[i].length == 3
//				1 <= ui, vi <= n
//				ui != vi
//				1 <= weighti <= 105
//				There is at most one edge between any two nodes.
//				There is at least one path between any two nodes.
		//https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/discuss/1203890/Dijkstra-%2B-DFS-memoization
		
		public int countRestrictedPaths(int n, int[][] edges) {
	        int dis[]=new int [n+1];//to store the minimum path distance we get using Dijkstra from n to 1;
	        Arrays.fill(dis, Integer.MAX_VALUE);
	        
	        //make adjucency list
	        Map<Integer,List<int[]>> adjs=new HashMap<Integer, List<int[]>>();
	        for(int [] edge:edges) {
	        	if(!adjs.containsKey(edge[0]))
	        		adjs.put(edge[0], new ArrayList<int[]>());
	        	if(!adjs.containsKey(edge[1]))
	        		adjs.put(edge[1], new ArrayList<int[]>());
	        	adjs.get(edge[0]).add(new int[]{edge[1], edge[2]}); // [target, weight]
	            adjs.get(edge[1]).add(new int[]{edge[0], edge[2]}); // [target, weight]
	        }
	        
	        //applying Dijkstra
	        PriorityQueue<int[]> pq=new PriorityQueue<int[]>((a,b)->a[1]-b[1]);
	        pq.offer(new int[]{n,0});
	        dis[n]=0;
	        while(!pq.isEmpty()) {
	        	int [] cur=pq.poll();
	        	List<int[]> children=adjs.get(cur[0]);
	        	if(children==null || children.size()==0)
	        		continue;
	        	for(int[]child:children) {
	        		if(child[1]+cur[1]<dis[child[0]]) {
	        			dis[child[0]]=child[1]+cur[1];
	        			pq.offer(new int[]{child[0],dis[child[0]]});
	        		}
	        	}
	        }
	        
	        //now use DFS to calculate the count
	        return dfs(dis, 1, n, adjs, new HashMap<Integer, Integer>());
	    }
		
		private int dfs(int[] dis,int i,int n,Map<Integer,List<int[]>> adjs,Map<Integer,Integer> map) {
			if(map.containsKey(i))
				return map.get(i);//this is for DP
			if(i==n) {
				map.put(i,1);
				return 1;
			}
			int res=0;
			List<int[]> lst=adjs.get(i);
			if(lst!=null||lst.size()>0) {
				for(int [] child:lst) {
					if(dis[child[0]]<dis[i]) {
						res=(res+dfs(dis, child[0], n, adjs, map))%1000000007;
					}
				}
			}
			map.put(i, res);
			return res;
		}
		
//		You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
//
//		If isWater[i][j] == 0, cell (i, j) is a land cell.
//		If isWater[i][j] == 1, cell (i, j) is a water cell.
//		You must assign each cell a height in a way that follows these rules:
//
//		The height of each cell must be non-negative.
//		If the cell is a water cell, its height must be 0.
//		Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, 
//		east, south, or west of the latter (i.e., their sides are touching).
//		Find an assignment of heights such that the maximum height in the matrix is maximized.
//
//		Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.
//
//		 
//
//		Example 1:
//
//
//
//		Input: isWater = [[0,1],[0,0]]
//		Output: [[1,0],[2,1]]
//		Explanation: The image shows the assigned heights of each cell.
//		The blue cell is the water cell, and the green cells are the land cells.
//		Example 2:
//
//
//
//		Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
//		Output: [[1,1,0],[0,1,1],[1,2,2]]
//		Explanation: A height of 2 is the maximum possible height of any assignment.
//		Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
//		 
//
//		Constraints:
//
//		m == isWater.length
//		n == isWater[i].length
//		1 <= m, n <= 1000
//		isWater[i][j] is 0 or 1.
//		There is at least one water cell.
//https://leetcode.com/problems/map-of-highest-peak/discuss/1203460/Java-Solution-oror-BFS		
		public int[][] highestPeak(int[][] isWater) {
	        int [][] dir= {{1,0}, {0,1}, {-1,0}, {0,-1}}; // to traverse adjacent cells
	        boolean [][] visited=new boolean[isWater.length][isWater[0].length];
	        Queue<int[]> q=new LinkedList<int[]>();
	        for(int i=0;i<isWater.length;i++) {
	        	for(int j=0;j<isWater[0].length;j++) {
	        		if(isWater[i][j]==1) {
	        			visited[i][j]=true;
	        			isWater[i][j]=0;
	        			q.offer(new int[] {i,j});
	        		}
	        	}
	        }
	        
	     // start traversing by taking one by one from the queue
			// add 1 to all the adjacent cells of the current cell and mark them visited
	       while(!q.isEmpty()) {
	    	   int[] curr=q.poll();
	    	   for(int d[]:dir) {
	    		   int x=d[0]+curr[0];
	    		   int y=d[1]+curr[1];
	    		   if(x<0||x>=isWater.length||y<0||y>=isWater[0].length||visited[x][y])
	    			   continue;
	    		   isWater[x][y]=isWater[curr[0]][curr[1]]+1;
	    		   visited[x][y]=true;
	    		   q.offer(new int[] {x,y});
	    	   }
	       }
	       return isWater;
		}
		
//		Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to 
//		the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
//
//				The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
//
//				 
//
//				Example 1:
//
//
//				Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
//				Output: 2
//				Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
//				Example 2:
//
//
//				Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
//				Output: 4
//				Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
//				 
//
//				Constraints:
//
//				n == grid.length
//				n == grid[i].length
//				1 <= n <= 100
//				grid[i][j] is 0 or 1
		//https://leetcode.com/problems/as-far-from-land-as-possible/discuss/1202154/MultiSource-BFS-Easy-C%2B%2B
		public int maxDistance(int[][] grid) {
			int [][] dir= {{1,0}, {0,1}, {-1,0}, {0,-1}}; // to traverse adjacent cells
			int n=grid.length;
			int[][] vis=new int [n][n];
			Queue<int[]> q=new LinkedList<int[]>();
			for(int i=0;i<n;i++)
				Arrays.fill(vis[i], -1);
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(grid[i][j]==1) {
						vis[i][j]=0;
						q.add(new int[] {i,j});
					}
				}
			}
			int ans=-1;
			while(!q.isEmpty()) {
				int[]cur=q.poll();
				for(int d[]:dir) {
					int x=cur[0]+d[0];
					int y=cur[1]+d[1];
					if(valid(x, y, n)) {
						if(vis[x][y]==-1) {
							vis[x][y]=1+vis[cur[0]][cur[1]];
							ans=Math.max(ans,vis[x][y]);
							q.add(new int[] {x,y});
						}else if(vis[x][y]>=1+vis[cur[0]][cur[1]]) {
							vis[x][y]=1+vis[cur[0]][cur[1]];
							ans=Math.max(ans,vis[x][y]);
							q.add(new int[] {x,y});
						}
					}
				}
			}
			return ans;
	    }
		
		boolean valid(int x, int y, int n){
	        if(x<0 || y<0 || x>=n || y>=n){
	            return false;
	        }
	        return true;
	    }
				
}

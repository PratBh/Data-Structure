package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Basic {
	class Vertex {
		char label;
		boolean visited;
		public Vertex(char lab) {
			this.label=lab;
			this.visited=false;
		}
	}
	
	class Graph{
		final int maxNum = 20;
		Vertex[] vertextList;
		int[][] adjMatrix;
		int vCount;
		Stack<Integer> st;//for dfs
		Queue<Integer> q; //for bfs
		public Graph(int count) {
			vertextList = new Vertex[maxNum];
			adjMatrix = new int [maxNum][maxNum];
			vCount=0;
			st=new Stack<Integer>();
			q=new LinkedList<Integer>();
		}
		
		void addVertex(char lab) {
			vertextList[vCount++]=new Vertex(lab);
		}
		
		void addEdge(int start,int end) {
			adjMatrix[start][end]=1;
			adjMatrix[end][start]=1;
		}
		
		int getAdjUnvisitedVertex(int start) {
			for(int i=0;i<vCount;i++) {
				if(adjMatrix[start][i]==1 && !vertextList[i].visited)
					return i;
			}
			return -1;
		}
		
		void dfs() {
			vertextList[0].visited=true;
			st.push(0);
			while(!st.isEmpty()) {
				int v = getAdjUnvisitedVertex(st.peek());
				if(v==-1)
					st.pop();//backtrack
				else {
					vertextList[v].visited=true;
					st.push(v);
				}
			}
		}
		
		void bfs() {
			vertextList[0].visited=true;
			q.add(0);
			while(!q.isEmpty()) {
				int v1 = q.poll();
				while(getAdjUnvisitedVertex(v1)!=-1) {
					int v2 = getAdjUnvisitedVertex(v1);
					vertextList[v2].visited=true;
					q.add(v2);
				}
			}
		}
	}
	
	class ListNode {
		int val;
		ListNode next;

		public ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
	class AdjGraph{
		ArrayList<Integer> vertices;
		ListNode[] edges;
		int vCount;
		public AdjGraph(int count) {
			this.vCount=count;
			this.vertices=new ArrayList<Integer>();
			edges = new ListNode[count];
			for(int i=0;i<count;i++) {
				vertices.add(i);
				edges[i]=new ListNode();
			}
		}
		
		void addEdge(int start,int dest) {
			int i = vertices.get(start);
			int j=vertices.get(dest);
			if(i!=-1 || j!=-1) {
				edges[i].add(dest);
				edges[j].add(start);
			}
		}
		
		void topologicalOrder() {
			int [] inDegree = new int[vCount];
//			for(int i=0;i<vCount;i++) {
//				inDegree[i]=edges[i].size();
//			}
			Queue<Integer>q = new LinkedList<Integer>();
			for(int i=0;i<vCount;i++) {
				if(inDegree[i]==0)
					q.add(i);
			}
			
			while(!q.isEmpty()){
				int v=q.poll();
				System.out.println(v);
				for(int w:getAdj(v)) {
					inDegree[v]--;
					if(inDegree[v]==0)
						q.add(v);
				}
			}
		}
	}
}

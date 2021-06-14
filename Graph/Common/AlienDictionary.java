import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words 
//from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
//Example 1:
//Given the following words in dictionary,
//[
//  "wrt",
//  "wrf",
//  "er",
//  "ett",
//  "rftt"
//]
//The correct order is: "wertf".
//https://zhuhan0.blogspot.com/2017/06/leetcode-269-alien-dictionary.html
public class AlienDictionary {
	public String alienOrder(String[] words) {
	Map<Character,Set<Character>> graph=new HashMap<>();
	int[] inDegree=new int[26];
	buildgraph(words,graph,inDegree);
	String order = topologicalSort(graph, inDegree);
	return order.length() == graph.size() ? order : "";
	}
	
	private void buildgraph(String[] words, Map<Character, Set<Character>> graph, int[] inDegree) {
		for(String word:words) {
			for(char c:word.toCharArray()) {
				graph.put(c, new HashSet<>());
			}
		}
		
		for(int i=1;i<words.length;i++) {
			String first=words[i-1];
			String second=words[i];
			
			int length=Math.min(first.length(), second.length());
			for(int j=0;j<length;j++) {
				char parent=first.charAt(j);
				char child=second.charAt(j);
				if(parent != child) {
					if(!graph.get(parent).contains(child)) {
						graph.get(parent).add(child);
						inDegree[child-'a']++;
					}
					break;
				}
			}
		}
	}
	
	private String topologicalSort(Map<Character, Set<Character>> graph, int[] inDegree) {
		Queue<Character> q=new LinkedList<Character>();
		for(char c:graph.keySet()) {
			if(inDegree[c-'a']==0)//this means the character which is not depending on any one
				q.offer(c);
		}
		
		StringBuilder sb=new StringBuilder();
		while(!q.isEmpty()) {
			char c=q.poll();
			sb.append(c);
			for(char neighbor:graph.get(c)) {
				inDegree[neighbor-'a']--;
				if(inDegree[neighbor-'a']==0) {
					q.offer(neighbor);
				}
			}
		}
		
		return sb.toString();
	}
}
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words 
//from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
//Example 1:
//Given the following words in dictionary,
//[
//  "wrt",
//  "wrf",
//  "er",
//  "ett",
//  "rftt"
//]
//The correct order is: "wertf".
//https://zhuhan0.blogspot.com/2017/06/leetcode-269-alien-dictionary.html
public class AlienDictionary {
	public String alienOrder(String[] words) {
	Map<Character,Set<Character>> graph=new HashMap<>();
	int[] inDegree=new int[26];
	buildgraph(words,graph,inDegree);
	String order = topologicalSort(graph, inDegree);
	return order.length() == graph.size() ? order : "";
	}
	
	private void buildgraph(String[] words, Map<Character, Set<Character>> graph, int[] inDegree) {
		for(String word:words) {
			for(char c:word.toCharArray()) {
				graph.put(c, new HashSet<>());
			}
		}
		
		for(int i=1;i<words.length;i++) {
			String first=words[i-1];
			String second=words[i];
			
			int length=Math.min(first.length(), second.length());
			for(int j=0;j<length;j++) {
				char parent=first.charAt(j);
				char child=second.charAt(j);
				if(parent != child) {
					if(!graph.get(parent).contains(child)) {
						graph.get(parent).add(child);
						inDegree[child-'a']++;
					}
					break;
				}
			}
		}
	}
	
	private String topologicalSort(Map<Character, Set<Character>> graph, int[] inDegree) {
		Queue<Character> q=new LinkedList<Character>();
		for(char c:graph.keySet()) {
			if(inDegree[c-'a']==0)//this means the character which is not depending on any one
				q.offer(c);
		}
		
		StringBuilder sb=new StringBuilder();
		while(!q.isEmpty()) {
			char c=q.poll();
			sb.append(c);
			for(char neighbor:graph.get(c)) {
				inDegree[neighbor-'a']--;
				if(inDegree[neighbor-'a']==0) {
					q.offer(neighbor);
				}
			}
		}
		
		return sb.toString();
	}
}


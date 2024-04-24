package trie;

class Node{
	Node[] links = new Node[26];
	boolean flag=false;
	
	boolean containsKey(char ch) {
		return links[ch-'a']!=null;
	}
	
	Node get(char ch) {
		return links[ch-'a'];
	}
	
	void put(char ch,Node node) {
		links[ch-'a']=node;
	}
	void setEnd() {
		flag=true;
	}
	boolean isEnd() {
		return flag;
	}
};
public class Trie {
	private static Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public static void insert(String word) {
		Node node =root;
		for(int i=0;i<word.length();i++) {
			if(!node.containsKey(word.charAt(i)))
				node.put(word.charAt(i), new Node());
			node = node.get(word.charAt(i));
		}
		node.setEnd();
	}
	
	public static boolean search(String word) {
		Node node =root;
		for(int i=0;i<word.length();i++) {
			if(!node.containsKey(word.charAt(i)))
				return false;
			node = node.get(word.charAt(i));
		}
		return node.isEnd();
	}
	
	public static boolean startsWith(String word) {
		Node node =root;
		for(int i=0;i<word.length();i++) {
			if(!node.containsKey(word.charAt(i)))
				return false;
			node = node.get(word.charAt(i));
		}
		return true;
	}
}

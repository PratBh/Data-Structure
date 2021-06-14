import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
//Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
//
//Note:
//
//If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//All airports are represented by three capital letters (IATA code).
//You may assume all tickets form at least one valid itinerary.
//One must use all the tickets once and only once.
//Example 1:
//
//Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
//https://www.youtube.com/watch?v=WYqsg5dziaQ
public class ReconstructItenary {
	HashMap<String, PriorityQueue<String>> map=new HashMap<String, PriorityQueue<String>>();
	LinkedList<String> result = new LinkedList<String>();
	public static void main(String[] args) {
		ReconstructItenary rc=new ReconstructItenary();
		rc.getItenary(new String[][] {
			{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}
		});
	}
	
	public LinkedList<String> getItenary(String [][] tickets){
		for(String[] ticket:tickets) {
			if(!map.containsKey(ticket)) {
				PriorityQueue<String> q = new PriorityQueue<String>();
				map.put(ticket[0], q);
			}map.get(ticket[0]).add(ticket[1]);
		}
		Stack<String> myStack=new Stack<String>();
		myStack.push("JFK");
		while(!myStack.isEmpty()) {
			String src=myStack.peek();
			if(map.get(src)==null||map.get(src).size()==0) {
				result.add(src);
				myStack.pop();
			}else {
				String dst=map.get(src).poll();
				myStack.push(dst);
			}
		}
		Collections.reverse(result);
		//need to reverse the result.
		return result;
	}
	
}


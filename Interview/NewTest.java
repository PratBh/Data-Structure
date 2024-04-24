package Interview;

import java.util.*;

public class NewTest {

	public static void main(String[] args) {
		int [][]edges = {{5,2},{5,1},{3,1},{3,4},{3,5}};
		//System.out.println(addSome("1048575 DUP +"));
		findRedundantDirectedConnection(edges);

	}
	
	static int[] findRedundantDirectedConnection(int[][] edges) {
        Set<Integer> visited = new HashSet();
        Map<Integer,List<Integer>> graph = new HashMap();
        for(int[]arr:edges){
            if(!graph.containsKey(arr[0]))
                graph.put(arr[0],new ArrayList<Integer>());
            List<Integer> l = graph.get(arr[0]);l.add(arr[1]);
            graph.put(arr[0],l);
        }

        int[]arr = new int[2];
        Queue<Integer>q = new LinkedList();
        int start = edges[0][0];
        q.add(start);visited.add(start);
        while(!q.isEmpty()){
            int src = q.poll();
            if(graph.containsKey(src)){
                if(graph.get(src)!=null||!graph.get(src).isEmpty()){
                    for(int i:graph.get(src)){
                        if(visited.contains(i)){
                            arr[0]=src;
                            arr[1]=i;
                            return arr;
                        }
                        visited.add(i);
                        q.add(i);
                    }
                }
            }
        }
        return arr;
    }
	
	static String solution(String str) {
		PriorityQueue<Integer> single = new PriorityQueue<Integer>(Collections.reverseOrder());
		Map<Integer,Integer> mp =new HashMap<Integer, Integer>();
		for(char c:str.toCharArray()) {
			int k  = c-'0';
			mp.put(k, mp.getOrDefault(k, 0)+1);
		}
		PriorityQueue<Map.Entry<Integer, Integer>> evnFreq = new 
				PriorityQueue<Map.Entry<Integer,Integer>>((a,b)->b.getKey()-a.getKey());
		mp.entrySet().forEach(e->{
			if(e.getValue()==1)
				single.add(e.getKey());
			else if (e.getValue()%2==0) {
				evnFreq.add(e);
			}else if (e.getValue()%2!=0 &&e.getValue()>1 ) {
				Map.Entry<Integer, Integer> entry = Map.entry(e.getKey(), e.getValue()-1);
				evnFreq.add(entry);
				single.add(e.getKey());
			}
		});
		String mid = "";
		if(single.size()>0)
			mid = String.valueOf(single.poll());
		StringBuilder sb =new StringBuilder();
		while(evnFreq.size()>0) {
			int fr = evnFreq.peek().getValue()/2;
			int i = evnFreq.poll().getValue();
			while(fr>0) {
				sb.append(String.valueOf(i));
				fr--;
			}
		}
		String res = sb.toString()+mid+new StringBuilder(sb).reverse().toString();
		if(res.charAt(0)=='0') {
			String strPattern = "^0+(?!$)";
			res = res.replaceAll(strPattern, "");
	         StringBuilder nb=new StringBuilder(res);  
	         nb.reverse();  
	         res = nb.toString(); 
	         res = res.replaceAll(strPattern, "");
		}
		return res;
	}
	public String largestPalindromic(String num) {
        
	    Map<Character,Integer>map  = new HashMap();
	    for(char c: num.toCharArray()){
	        
	        map.put(c,map.getOrDefault(c,0)+1);
	        
	    }
	    PriorityQueue<Map.Entry<Character,Integer>> even = new PriorityQueue<>((a,b)->{
	        return b.getKey()-a.getKey();
	    });
	    PriorityQueue<Integer> single = new PriorityQueue<>((a,b)->b-a);
	   
	    map.entrySet().forEach(e->{
	        if(e.getValue() == 1)
	            single.offer(e.getKey()-'0');
	        else if(e.getValue()%2==0)
	        {
	            even.offer(e);
	        } 
	        else if(e.getValue()%2==1 && e.getValue()>1){
	        	Character c = e.getKey();
	        	Integer i = e.getValue()-1;
	            Map.Entry<Character,Integer> entry = Map.entry(c,i);
	            even.offer(entry);
	            single.offer(e.getKey()-'0'); 
	        }
	    });
	    String mid = "";
	    if(single.size()>0)
	      mid = Character.toString((char)(single.poll() + '0'));
	    
	    StringBuilder sb = new StringBuilder();
	    while(even.size()>0){
	        
	        int freq = even.peek().getValue();
	        char ch = even.poll().getKey();
	        freq = freq/2;
	        int temp = freq;
	        while(temp-->0)
	        {
	            sb.append(ch);
	        }
	    }
	    String ans = sb.toString() + mid + new StringBuilder(sb).reverse().toString();
	    
	    
	    if(ans.charAt(0)=='0'){
		
	         String strPattern = "^0+(?!$)";
	         ans = ans.replaceAll(strPattern, "");
	         StringBuilder nb=new StringBuilder(ans);  
	         nb.reverse();  
	         ans = nb.toString(); 
	         ans = ans.replaceAll(strPattern, "");
	    }
	   
	    return ans;
	    }
	
	
	static int addSome(String str) {
		int d = (int) (Math.pow(2, 20)-1);
		String[] splited = str.split("\\s+");
		Stack<Integer> st = new Stack<Integer>();
		for(String sr:splited) {
			if(sr.matches("-?\\d+(\\.\\d+)?")) {
				st.add(Integer.parseInt(sr));
			}else if (sr.equals("POP")) {
				if(st.size()>0)
					st.pop();
				else
					throw new NullPointerException();
			}else if (sr.equals("DUP")) {
				if(st.size()>0) {
					st.add(st.peek());
				}
				else
					throw new NullPointerException();
			}else if (sr.equals("-")) {
				if(st.size()>=2) {
					int a = st.pop();
					int b=st.pop();
					st.add(a-b);
				}
				else
					st.add(-1);
			}else if (sr.equals("+")) {
				if(st.size()>=2) {
					int a = st.pop();
					int b=st.pop();
					if(a+b > d)
						st.add(-1);
					else
					  st.add(a+b);
				}
				else
					throw new NullPointerException();
			}
		}
		return st.peek();
	}

}

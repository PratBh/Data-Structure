package LLD.Splitwise
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitWiseSimlified {
	
	public int minTransactions(int [][] transactions) {
		Map<Integer,Integer> mp = new HashMap<Integer, Integer>();
		for(int[]txn:transactions) {
			int from=txn[0];
			int to=txn[1];
			int amount = txn[2];
			mp.put(from, mp.getOrDefault(from, 0)-amount);
			mp.put(to, mp.getOrDefault(to, 0)+amount);
		}
		List<Integer> balanceList = new ArrayList<Integer>();
		for(int k:mp.values()) {
			if(k!=0)
				balanceList.add(k);
		}
		return dfs(balanceList, 0);
	}
	
	private int dfs(List<Integer> balanceList,int currIdx) {
		if(balanceList.size()==0||currIdx>=balanceList.size())
			return 0;
		if(balanceList.get(currIdx)==0)
			return dfs(balanceList, currIdx+1);
		int currVal = balanceList.get(currIdx);
		int minTxn = Integer.MAX_VALUE;
		for(int i=currIdx+1;i<balanceList.size();i++) {
			int nextVal=balanceList.get(i);
			if(currVal*nextVal<0) {
				balanceList.set(i, nextVal+currVal);
				minTxn=Math.min(minTxn, 1+dfs(balanceList, currIdx+1));
				balanceList.set(i, nextVal);
				if(currVal+nextVal==0)
					break;
			}
		}
		return minTxn;
	}

	public static void main(String[] args) {

	}

}

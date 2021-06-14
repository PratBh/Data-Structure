
package leet;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
//		List<String> ls=new ArrayList<String>();
//		ls.add("ymann");
//		ls.add("yycrj");
//		ls.add("oecij");
//		ls.add("ymcnj");
//		ls.add("yzcrj");
//		ls.add("yycij");
//		ls.add("xecij");
//		ls.add("yecij");
//		ls.add("ymanj");
//		ls.add("yzcnj");
//		ls.add("ymain");
//		int [][]grid= {{0,6,0},{5,8,7},{0,9,0}};
//		BFS prb=new BFS();
//		//prb.ladderLength("ymain", "oecij", ls);
//		prb.getMaximumGold(grid);
		List<List<Integer>> res=new ArrayList<List<Integer>>();
		for(int i=0;i<5;i++)
			res.add(new ArrayList<Integer>(5));
		res.get(4).add(0, 4);
		res.get(4).add(4, 4);
	}

}

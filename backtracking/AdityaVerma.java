package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdityaVerma {
	//find unique permutation of string
	//ip: abc
	//op: abc,acb,bac,bca,cab,cba
//	ip:aab
//	op: aab,aba,baa
	
	 String[] permute(String ip) {
		List<String> res = new ArrayList<String>();
		permuteUtil(ip, "", res);
		String[] arr = new String[res.size()];
		int k=0;
		for(String s:res)
			arr[k++]=s;
 		return arr;
	}
	 
	 void permuteUtil(String ip,String op,List<String> res) {
		 if(ip.length()==0) {
			 res.add(op);
			 return;
		 }
		 Set<Character> set = new HashSet<Character>();
		 for(int i=0;i<ip.length();i++) {
			 if(!set.contains(ip.charAt(i))){
				 set.add(ip.charAt(i));
				 String newIp=ip.substring(0,i)+ip.substring(i+1);
				 String newOp=op+ip.charAt(i);
				 permuteUtil(newIp, newOp, res);
			 }
		 }
		 
	 }
	
}

package DP;

public class Test {

	public static void main(String[] args) {
//		Kedane_Algo ka =new Kedane_Algo();
//		String[]a= {"1","01","001","0001"};
		int[]b= {3,2,6,5,0,3};
		char[][]c1 = {{'0','1'},{'1','0'}};
		char[]c2 = {'1','0'};
//		System.out.println(ka.countSubarrays(b,50));
		DP_150_75 dp = new DP_150_75();
		//System.out.println(dp.maxProfit(2,b));
		dp.maximalSquare(c1);
	}
	

}

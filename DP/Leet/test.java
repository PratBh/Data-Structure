package Leet;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Problems prb=new Problems();
		int [][]arr= {{1,2},{3,4}};
		int []nums= {1,3,5,4};
		int[] ar= {1,2,3,7};
		ArrayList<ArrayList<Integer>> tri=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> l1=new ArrayList<Integer>();
		ArrayList<Integer> l2=new ArrayList<Integer>();
		ArrayList<Integer> l3=new ArrayList<Integer>();
		ArrayList<Integer> l4=new ArrayList<Integer>();
		l1.add(2);
		l2.add(3);
		l2.add(4);
		l3.add(6);
		l3.add(5);
		l3.add(7);
		l4.add(4);
		l4.add(1);
		l4.add(8);
		l4.add(3);
		tri.add(l1);
		tri.add(l2);
		tri.add(l3);
		tri.add(l4);
//		prb.maxProfitPeakValey(arr);
//		int []cost= {10, 15, 20};
//		prb.minCostClimbingStairs(cost);
		//prb.climbStairs(3);
		//prb.maxSubArray(arr);
		//prb.rob(arr);
		//prb.countVowelStrings(1);
		//prb.maximalSquare(arr);
		//prb.orderOfLargestPlusSign(2, arr);
		//prb.canPartitionKSubsets(arr, 4);
		//prb.countBits(5);
		//prb.maxSumAfterPartitioning(arr, 1);
		//prb.stoneGameII(arr);
		//prb.minFallingPathSum(arr);
		//prb.minHeightShelves(arr, 4);
		//prb.minimumTotal(tri);
		//prb.waysToMakeFair(arr);
		//prb.numSubmat(arr);
		//prb.mctFromLeafValues(nums);
		//prb.minimumDeleteSum("delete", "leet");
		//prb.findNumberOfLIS(nums);
		//prb.checkSubarraySum(nums, 6);
		//prb.findLongestChain(arr);
		//prb.numDecodings("0");
		//prb.isMatchRegex("ab", ".*c");
		//prb.printLIS(nums);
		//prb.maximumSum(nums);
		//prb.majorityElementTwo(nums);
		//prb.removeDuplicate("abcbad");
		//prb.minSwap(ar, nums);
		//prb.removeCoveredIntervals(arr);
		prb.numSquare(13);
	}

}


package leetcode;

public class Test {

	public static void main(String[] args) {
		Problems prb=new Problems();
		int [][]grid= {{1,  5,  9},{10, 11, 13},{12, 13, 15}};
		int[]arr= {5,1,3};
		int[] b= {3,2,1,4,7};
		//prb.countNegatives(grid);
		//prb.kWeakestRows(grid, 3);
		//prb.mySqrt(3);
		//prb.smallestCommonElement(grid);
		//prb.shipWithinDays(arr, 3);
		//prb.kthSmallest(grid, 8);
		//prb.search(arr);
		//prb.minEatingSpeed(arr, 6);
		//prb.findLength(arr, b);
		//prb.minDays(arr, 2, 3);
		//prb.smallestDivisor(arr, 5);
		//prb.findMin(arr);
		//prb.findBestValue(arr, 56803);
		prb.search(arr, 3);
	}

}

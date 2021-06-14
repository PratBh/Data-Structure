package leetcode;

public class Test {

	public static void main(String[] args) {
		Problems prb=new Problems();
		int [] arr= {2,2,2,4,4,2,5,5,5,5,5,2};
		int [] num= {10, 5, 2, 6};
		//prb.numSubseq(arr, 1);
		//prb.longestOnes(arr, 4);
		//prb.maxSatisfied(arr, num, 3);
		//prb.maxVowels("", 3);
		//prb.characterReplacement("AAAA", 0);
		//prb.maxTurbulenceSize(arr);
		//prb.maxScore(arr, 3);
		//prb.checkInclusion("ab", "eidbaooo");
		//prb.longestSubarray(arr, 2);
		//prb.equalSubstring("abcd", "cdef", 3);
		//prb.minSubArrayLen(7, num);
		prb.numSubarrayProductLessThanK(num, 100);
	}

}


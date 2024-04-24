package leet75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Methods {
//	You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other,
//	append the additional letters onto the end of the merged string.
//
//	Return the merged string.
//
//	 
//
//	Example 1:
//
//	Input: word1 = "abc", word2 = "pqr"
//	Output: "apbqcr"
//	Explanation: The merged string will be merged as so:
//	word1:  a   b   c
//	word2:    p   q   r
//	merged: a p b q c r
//	Example 2:
//
//	Input: word1 = "ab", word2 = "pqrs"
//	Output: "apbqrs"
//	Explanation: Notice that as word2 is longer, "rs" is appended to the end.
//	word1:  a   b 
//	word2:    p   q   r   s
//	merged: a p b q   r   s
//	Example 3:
//
//	Input: word1 = "abcd", word2 = "pq"
//	Output: "apbqcd"
//	Explanation: Notice that as word1 is longer, "cd" is appended to the end.
//	word1:  a   b   c   d
//	word2:    p   q 
//	merged: a p b q c   d
	public String mergeAlternately(String word1, String word2) {
		int n1 = word1.length(), n2 = word2.length(), i = 0, j = 0;
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while (i < n1 && j < n2) {
			if (first) {
				sb.append(word1.charAt(i));
				i++;
			} else {
				sb.append(word2.charAt(j));
				j++;
			}
			first = !first;
		}

		if (i < n1) {
			while (i < n1) {
				sb.append(word1.charAt(i));
				i++;
			}
		}
		if (j < n2) {
			while (j < n2) {
				sb.append(word2.charAt(j));
				j++;
			}
		}
		return sb.toString();
	}

//	For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
//
//			Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
//
//			 
//
//			Example 1:
//
//			Input: str1 = "ABCABC", str2 = "ABC"
//			Output: "ABC"
//			Example 2:
//
//			Input: str1 = "ABABAB", str2 = "ABAB"
//			Output: "AB"
//			Example 3:
//
//			Input: str1 = "LEET", str2 = "CODE"
//			Output: ""
//			 
//
//			Constraints:
//
//			1 <= str1.length, str2.length <= 1000
//			str1 and str2 consist of English uppercase letters.
	// https://leetcode.com/problems/greatest-common-divisor-of-strings/solutions/3125858/the-idea-of-using-string-equality-and-gcd-time-space-complexity-elaboration/
	public String gcdOfStrings(String str1, String str2) {
		if ((str1 + str2).equals(str2 + str1)) {
			return str1.substring(0, getGcd(str1.length(), str2.length()));
		}
		return "";
	}

	private int getGcd(int a, int b) {
		if (b == 0)
			return a;
		return getGcd(b, a % b);
	}

//	There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, 
//	and an integer extraCandies, denoting the number of extra candies that you have.
//
//	Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies,
//	they will have the greatest number of candies among all the kids, or false otherwise.
//
//	Note that multiple kids can have the greatest number of candies.
//
//	 
//
//	Example 1:
//
//	Input: candies = [2,3,5,1,3], extraCandies = 3
//	Output: [true,true,true,false,true] 
//	Explanation: If you give all extraCandies to:
//	- Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
//	- Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
//	- Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
//	- Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
//	- Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
//	Example 2:
//
//	Input: candies = [4,2,1,1,2], extraCandies = 1
//	Output: [true,false,false,false,false] 
//	Explanation: There is only 1 extra candy.
//	Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
//	Example 3:
//
//	Input: candies = [12,1,12], extraCandies = 10
//	Output: [true,false,true]

	public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
		int max = Integer.MIN_VALUE;
		List<Boolean> res = new ArrayList<>();
		for (int i : candies)
			max = Math.max(i, max);
		for (int i = 0; i < candies.length; i++) {
			if (candies[i] + extraCandies >= max)
				res.add(true);
			else
				res.add(false);
		}
		return res;

	}

//	You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
//
//	Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, 
//	return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
//
//	 
//
//	Example 1:
//
//	Input: flowerbed = [1,0,0,0,1], n = 1
//	Output: true
//	Example 2:
//
//	Input: flowerbed = [1,0,0,0,1], n = 2
//	Output: false
//	 
//
//	Constraints:
//
//	1 <= flowerbed.length <= 2 * 104
//	flowerbed[i] is 0 or 1.
//	There are no two adjacent flowers in flowerbed.
//	0 <= n <= flowerbed.length

	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		int i = 0, k = flowerbed.length;
		if (n == 0)
			return true;
		if (k == 1) {
			if (flowerbed[0] != 1)
				n--;
		} else {
			while (i < k && n > 0) {
				if (flowerbed[i] != 1) {
					if (i == 0) {
						if (flowerbed[i + 1] != 1) {
							flowerbed[i] = 1;
							n--;
						}
					} else if (i == k - 1) {
						if (flowerbed[i - 1] != 1) {
							flowerbed[i] = 1;
							n--;
						}
					} else {
						if (flowerbed[i - 1] != 1 && flowerbed[i + 1] != 1) {
							flowerbed[i] = 1;
							n--;
						}
					}
				}
				i++;
			}
		}
		return (n == 0);
	}

//	Given a string s, reverse only all the vowels in the string and return it.
//
//			The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
//
//			 
//
//			Example 1:
//
//			Input: s = "hello"
//			Output: "holle"
//			Example 2:
//
//			Input: s = "leetcode"
//			Output: "leotcede"
//			 
//
//			Constraints:
//
//			1 <= s.length <= 3 * 105
//			s consist of printable ASCII characters.

	public String reverseVowels(String s) {
		if (s.length() == 1)
			return s;
		char[] chars = s.toCharArray();
		Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (!vowels.contains(s.charAt(i)) && !vowels.contains(s.charAt(j))) {
				i++;
				j--;
				continue;
			} else {
				if (vowels.contains(s.charAt(i)) && vowels.contains(s.charAt(j))) {
					if (s.charAt(i) != s.charAt(j)) {
						chars[i] = s.charAt(j);
						chars[j] = s.charAt(i);
					}
					i++;
					j--;
				} else if (vowels.contains(s.charAt(j))) {
					i++;
				} else {
					j--;
				}
			}
		}
		return String.valueOf(chars);

	}

//	Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].
//			If no such indices exists, return false.
//
//			 
//
//			Example 1:
//
//			Input: nums = [1,2,3,4,5]
//			Output: true
//			Explanation: Any triplet where i < j < k is valid.
//			Example 2:
//
//			Input: nums = [5,4,3,2,1]
//			Output: false
//			Explanation: No triplet exists.
//			Example 3:
//
//			Input: nums = [2,1,5,0,4,6]
//			Output: true
//			Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
//			 
//
//			Constraints:
//
//			1 <= nums.length <= 5 * 105
//			-231 <= nums[i] <= 231 - 1

	public boolean increasingTriplet(int[] nums) {
		if (nums.length < 3)
			return false;
		int i = Integer.MAX_VALUE, j = Integer.MAX_VALUE;
		for (int k = 0; k < nums.length; k++) {
			if (nums[k] <= i)
				i = nums[k];
			else if (nums[k] > i && nums[k] > j)
				return true;
			else if (nums[k] > i)
				j = nums[k];

		}
		return false;
	}

//	Given an array of characters chars, compress it using the following algorithm:
//
//		Begin with an empty string s. For each group of consecutive repeating characters in chars:
//
//		If the group's length is 1, append the character to s.
//		Otherwise, append the character followed by the group's length.
//		The compressed string s should not be returned separately, but instead, be stored in the input character array chars. 
//		Note that group lengths that are 10 or longer will be split into multiple characters in chars.
//
//		After you are done modifying the input array, return the new length of the array.
//
//		You must write an algorithm that uses only constant extra space.
//
//		 
//
//		Example 1:
//
//		Input: chars = ["a","a","b","b","c","c","c"]
//		Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
//		Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
//		Example 2:
//
//		Input: chars = ["a"]
//		Output: Return 1, and the first character of the input array should be: ["a"]
//		Explanation: The only group is "a", which remains uncompressed since it's a single character.
//		Example 3:
//
//		Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
//		Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
//		Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
//		 
//
//		Constraints:
//
//		1 <= chars.length <= 2000
//		chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
	// https://leetcode.com/problems/string-compression/solutions/4467773/java-c-o-n-simple-easy-solution-step-by-step-explanation/?envType=study-plan-v2&envId=leetcode-75
	public int compress(char[] chars) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length;) {
			int cnt = 1;
			char c = chars[i];
			while (i < chars.length - 1 && c == chars[i + 1]) {
				cnt++;
				i++;
			}
			sb.append(c);
			if (cnt > 1)
				sb.append(cnt);
			i++;
		}

		char[] cmp = sb.toString().toCharArray();
		for (int i = 0; i < cmp.length; i++)
			chars[i] = cmp[i];
		return cmp.length;
	}

//	Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
//
//	Note that you must do this in-place without making a copy of the array.
//
//	 
//
//	Example 1:
//
//	Input: nums = [0,1,0,3,12]
//	Output: [1,3,12,0,0]
//	Example 2:
//
//	Input: nums = [0]
//	Output: [0]
//	 
//
//	Constraints:
//
//	1 <= nums.length <= 104
//	-231 <= nums[i] <= 231 - 1

	// https://leetcode.com/problems/move-zeroes/solutions/4527308/beats-100-most-easiest-approach-two-pointer/?envType=study-plan-v2&envId=leetcode-75
	public void moveZeroes(int[] nums) {
//        int zero=0,num=0,n=nums.length;
//        while(zero<n&&num<n) {
//        	if(nums[zero]!=0)
//        		zero++;
//        	if(nums[num]==0)
//        		num++;
//        	if(zero<n&&num<n&&zero<num) {
//        		int t=nums[zero];
//        		nums[zero]=nums[num];
//        		nums[num]=t;
//        	}
//        	num++;
//        }
		int idx = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[idx] = nums[i];
				idx++;
			}
		}
		for (int i = idx; i < nums.length; i++) {
			nums[i] = 0;
		}
		return;
	}

//	You are given an integer array nums and an integer k.
//
//	In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
//
//	Return the maximum number of operations you can perform on the array.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,2,3,4], k = 5
//	Output: 2
//	Explanation: Starting with nums = [1,2,3,4]:
//	- Remove numbers 1 and 4, then nums = [2,3]
//	- Remove numbers 2 and 3, then nums = []
//	There are no more pairs that sum up to 5, hence a total of 2 operations.
//	Example 2:
//
//	Input: nums = [3,1,3,4,3], k = 6
//	Output: 1
//	Explanation: Starting with nums = [3,1,3,4,3]://1 3 3 3 4
//	- Remove the first two 3's, then nums = [1,4,3]
//	There are no more pairs that sum up to 6, hence a total of 1 operation.

	public int maxOperations(int[] nums, int k) {
		if (nums.length == 1)
			return 0;
		int op = 0, i = 0, j = nums.length - 1;
		Arrays.sort(nums);
		while (i < j) {
			if (nums[i] + nums[j] == k) {
				op++;
				i++;
				j--;
			} else if (nums[i] + nums[j] < k)
				i++;
			else
				j++;
		}
		return op;
	}

//	You are given an integer array nums consisting of n elements, and an integer k.
//
//	Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
//			Any answer with a calculation error less than 10-5 will be accepted.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,12,-5,-6,50,3], k = 4
//	Output: 12.75000
//	Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
//	Example 2:
//
//	Input: nums = [5], k = 1
//	Output: 5.00000
//	 
//
//	Constraints:
//
//	n == nums.length
//	1 <= k <= n <= 105
//	-104 <= nums[i] <= 104
	public double findMaxAverage(int[] nums, int k) {
		double res = 0, sum = 0;
		int i = 1, j = 1;
		for (int m = 0; m < k; m++)
			res += nums[k];
		while (j < nums.length) {
			sum += nums[j];
			if (j - i + 1 == k) {
				res = Math.max(res, sum);
				sum -= nums[i];
				i++;
			}
			j++;
		}
		return res / k;
	}

//	Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
//
//			Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
//
//			 
//
//			Example 1:
//
//			Input: s = "abciiidef", k = 3
//			Output: 3
//			Explanation: The substring "iii" contains 3 vowel letters.
//			Example 2:
//
//			Input: s = "aeiou", k = 2
//			Output: 2
//			Explanation: Any substring of length 2 contains 2 vowels.
//			Example 3:
//
//			Input: s = "leetcode", k = 3
//			Output: 2
//			Explanation: "lee", "eet" and "ode" contain 2 vowels.
//			 
//
//			Constraints:
//
//			1 <= s.length <= 105
//			s consists of lowercase English letters.
//			1 <= k <= s.length

	public int maxVowels(String s, int k) {
		Set<Character> set = Set.of('a', 'e', 'i', 'o', 'u');
		int i = 0, j = 0, res = Integer.MIN_VALUE, count = 0;
		while (j < s.length()) {
			if (set.contains(s.charAt(j)))
				count++;
			if (j - i + 1 == k) {
				res = Math.max(res, count);
				if (set.contains(s.charAt(i)))
					count--;
				i++;
			}
			j++;
		}
		return res;
	}

//	Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
//
//			 
//
//			Example 1:
//
//			Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
//			Output: 6
//			Explanation: [1,1,1,0,0,1,1,1,1,1,1]
//			Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
//			Example 2:
//
//			Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
//			Output: 10
//			Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//			Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
//			 
//
//			Constraints:
//
//			1 <= nums.length <= 105
//			nums[i] is either 0 or 1.
//			0 <= k <= nums.length

	public int longestOnes(int[] nums, int k) {
		int res = 0, zeros = 0, i = 0, j = 0, ones = 0;
		while (j < nums.length) {
			if (nums[j] == 1)
				ones++;
			else
				zeros++;
			if (zeros <= k) {
				res = Math.max(res, j - i + 1);
				j++;
			} else {
				while (zeros > k) {
					if (nums[i] == 1)
						ones--;
					else
						zeros--;
					i++;
				}
				j++;
			}
		}
		return res;
	}

//	Given a binary array nums, you should delete one element from it.
//
//	Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,1,0,1]
//	Output: 3
//	Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
//	Example 2:
//
//	Input: nums = [0,1,1,1,0,1,1,0,1]
//	Output: 5
//	Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
//	Example 3:
//
//	Input: nums = [1,1,1]
//	Output: 2
//	Explanation: You must delete one element.

	public int longestSubarray(int[] nums) {
		if (nums.length == 1)
			return 0;
		int ones = 0, zeros = 0, res = Integer.MIN_VALUE, len = 0, j = 0, i = 0;
		while (j < nums.length) {
			if (nums[j] == 1)
				ones++;
			else
				zeros++;
			if (zeros <= 1) {
				res = Math.max(res, ones);
				j++;
			} else {
				while (zeros > 1) {
					if (nums[i] == 1)
						ones--;
					else
						zeros--;
					i++;
				}
				j++;
			}
		}
		return (zeros == 0) ? res - 1 : res;
	}

//	There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.
//
//	You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i​​​​​​ and i + 1 for all (0 <= i < n). 
//	Return the highest altitude of a point.
//
//	 
//
//	Example 1:
//
//	Input: gain = [-5,1,5,0,-7]
//	Output: 1
//	Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
//	Example 2:
//
//	Input: gain = [-4,-3,-2,-1,4,3,2]
//	Output: 0
//	Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.
//	 
//
//	Constraints:
//
//	n == gain.length
//	1 <= n <= 100
//	-100 <= gain[i] <= 100

	public int largestAltitude(int[] gain) {
		int start = 0, max = 0;
		for (int i = 0; i < gain.length; i++) {
			start += gain[i];
			max = Math.max(max, start);
		}
		return max;
	}

//	Given an array of integers nums, calculate the pivot index of this array.
//
//	The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
//
//	If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
//
//	Return the leftmost pivot index. If no such index exists, return -1.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,7,3,6,5,6]
	//
	// 0,1,8,11,17,22
	// 27,20,17,11,6,0
//	Output: 3
//	Explanation:
//	The pivot index is 3.
//	Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
//	Right sum = nums[4] + nums[5] = 5 + 6 = 11
//	Example 2:
//
//	Input: nums = [1,2,3]
	// 0 1 3
	// 5 3 0
//	Output: -1
//	Explanation:
//	There is no index that satisfies the conditions in the problem statement.
//	Example 3:
//
//	Input: nums = [2,1,-1]
	// 0 2 3
	// 0 -1 0
//	Output: 0
//	Explanation:
//	The pivot index is 0.
//	Left sum = 0 (no elements to the left of index 0)
//	Right sum = nums[1] + nums[2] = 1 + -1 = 0
//	 
//
//	Constraints:
//
//	1 <= nums.length <= 104
//	-1000 <= nums[i] <= 1000

	public int pivotIndex(int[] nums) {
		if (nums.length == 1)
			return 0;
		int n = nums.length;
		int[] pre_sum = new int[n];
		int[] suff_sum = new int[n];
		pre_sum[0] = 0;
		pre_sum[1] = nums[0];
		suff_sum[n - 1] = 0;
		suff_sum[n - 2] = nums[n - 1];
		for (int i = 2; i < n; i++)
			pre_sum[i] = pre_sum[i - 1] + nums[i - 1];
		for (int i = n - 3; i >= 0; i--)
			suff_sum[i] = suff_sum[i + 1] + nums[i + 1];
		for (int i = 0; i < n; i++) {
			if (pre_sum[i] == suff_sum[i])
				return i;
		}
		return -1;
	}

//	Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
//
//		answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
//		answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
//		Note that the integers in the lists may be returned in any order.
//
//		 
//
//		Example 1:
//
//		Input: nums1 = [1,2,3], nums2 = [2,4,6]
//		Output: [[1,3],[4,6]]
//		Explanation:
//		For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
//		For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].
//		Example 2:
//
//		Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
//		Output: [[3],[]]
//		Explanation:
//		For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
//		Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
//		 
//
//		Constraints:
//
//		1 <= nums1.length, nums2.length <= 1000
//		-1000 <= nums1[i], nums2[i] <= 1000

	public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
		Set<Integer> s1 = new HashSet<Integer>();
		Set<Integer> s2 = new HashSet<Integer>();
		for (int n : nums1)
			s1.add(n);
		for (int n : nums2)
			s2.add(n);
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> l1 = new ArrayList<Integer>(s1);
		List<Integer> l2 = new ArrayList<Integer>(s2);
		l1.removeAll(s2);
		l2.removeAll(s1);
		res.add(l1);
		res.add(l2);
		return res;
	}

//	Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.
//
//			 
//
//			Example 1:
//
//			Input: arr = [1,2,2,1,1,3]
//			Output: true
//			Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
//			Example 2:
//
//			Input: arr = [1,2]
//			Output: false
//			Example 3:
//
//			Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
//			Output: true
//			 
//
//			Constraints:
//
//			1 <= arr.length <= 1000
//			-1000 <= arr[i] <= 1000

	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
		Set<Integer> s = new HashSet<Integer>();
		for (int n : arr)
			mp.put(n, mp.getOrDefault(n, 0) + 1);
		for (int a : mp.keySet()) {
			if (s.contains(mp.get(a)))
				return false;
			s.add(mp.get(a));
		}
		return true;
	}

//	Two strings are considered close if you can attain one from the other using the following operations:
//
//		Operation 1: Swap any two existing characters.
//		For example, abcde -> aecdb
//		Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
//		For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
//		You can use the operations on either string as many times as necessary.
//
//		Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
//
//		 
//
//		Example 1:
//
//		Input: word1 = "abc", word2 = "bca"
//		Output: true
//		Explanation: You can attain word2 from word1 in 2 operations.
//		Apply Operation 1: "abc" -> "acb"
//		Apply Operation 1: "acb" -> "bca"
//		Example 2:
//
//		Input: word1 = "a", word2 = "aa"
//		Output: false
//		Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
//		Example 3:
//
//		Input: word1 = "cabbba", word2 = "abbccc"
//		Output: true
//		Explanation: You can attain word2 from word1 in 3 operations.
//		Apply Operation 1: "cabbba" -> "caabbb"
//		Apply Operation 2: "caabbb" -> "baaccc"
//		Apply Operation 2: "baaccc" -> "abbccc"

	public boolean closeStrings(String word1, String word2) {
		if (word1.length() != word2.length())
			return false;
		int[] freq1 = new int[26];
		int[] freq2 = new int[26];

		for (int i = 0; i < word1.length(); i++)
			freq1[word1.charAt(i) - 'a']++;

		for (int i = 0; i < word2.length(); i++)
			freq2[word2.charAt(i) - 'a']++;

		for (int i = 0; i < 26; i++) {
			if ((freq1[i] > 0 && freq2[i] == 0) || (freq2[i] > 0 && freq1[i] == 0))
				return false;
		}

		Arrays.sort(freq1);
		Arrays.sort(freq2);

		for (int i = 0; i < 26; i++) {
			if (freq1[i] != freq2[i])
				return false;
		}
		return true;
	}

//	Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
//
//			A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
//
//			 
//
//			Example 1:
//
//
//			Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
//			Output: 1
//			Explanation: There is 1 equal row and column pair:
//			- (Row 2, Column 1): [2,7,7]
//			Example 2:
//
//
//			Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
//			Output: 3
//			Explanation: There are 3 equal row and column pairs:
//			- (Row 0, Column 0): [3,1,2,2]
//			- (Row 2, Column 2): [2,4,2,2]
//			- (Row 3, Column 2): [2,4,2,2]

	public int equalPairs(int[][] grid) {
		int n = grid.length, ans = 0;
		Map<Integer, List<Integer>> row = new HashMap<Integer, List<Integer>>();
		Map<Integer, List<Integer>> col = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < n; i++)
			row.put(i, new ArrayList<Integer>());
		for (int i = 0; i < n; i++)
			col.put(i, new ArrayList<Integer>());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				row.get(i).add(grid[i][j]);
				col.get(j).add(grid[i][j]);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (row.get(i).equals(col.get(j)))
					ans++;
			}
		}
		return ans;
	}

//	You are given a string s, which contains stars *.
//
//	In one operation, you can:
//
//	Choose a star in s.
//	Remove the closest non-star character to its left, as well as remove the star itself.
//	Return the string after all stars have been removed.
//
//	Note:
//
//	The input will be generated such that the operation is always possible.
//	It can be shown that the resulting string will always be unique.
//	 
//
//	Example 1:
//
//	Input: s = "leet**cod*e"
//	Output: "lecoe"
//	Explanation: Performing the removals from left to right:
//	- The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
//	- The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
//	- The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
//	There are no more stars, so we return "lecoe".
//	Example 2:
//
//	Input: s = "erase*****"
//	Output: ""
//	Explanation: The entire string is removed, so we return an empty string.

	public String removeStars(String s) {
		Stack<Character> st = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '*' && !st.isEmpty())
				st.pop();
			else
				st.push(c);
		}
		if (st.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		while (!st.isEmpty())
			sb.append(st.pop());
		return sb.reverse().toString();
	}

//	We are given an array asteroids of integers representing asteroids in a row.
//
//	For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
//	Each asteroid moves at the same speed.
//
//	Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
//	If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
//
//	 
//
//	Example 1:
//
//	Input: asteroids = [5,10,-5]
//	Output: [5,10]
//	Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
//	Example 2:
//
//	Input: asteroids = [8,-8]
//	Output: []
//	Explanation: The 8 and -8 collide exploding each other.
//	Example 3:
//
//	Input: asteroids = [10,2,-5]
//	Output: [10]
//	Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
	// https://leetcode.com/problems/asteroid-collision/solutions/4494114/easy-full-explained-beats-98-java-c-python/?envType=study-plan-v2&envId=leetcode-75
	public int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> st = new Stack<Integer>();
		for (int a : asteroids) {
			if (st.isEmpty() || a > 0)
				st.push(a);
			else {
				while (true) {
					int k = st.peek();
					if (k < 0) {
						st.push(a);
						break;
					} else if (k == (a * -1)) {
						st.pop();
						break;
					} else if (k > (a * -1))
						break;
					else {
						st.pop();
						if (st.isEmpty()) {
							st.push(a);
							break;
						}
					}
				}

			}
		}
		if (st.isEmpty())
			return new int[0];
		int[] res = new int[st.size()];
		for (int i = st.size() - 1; i >= 0; i--)
			res[i] = st.pop();
		return res;
	}

//	Given an encoded string, return its decoded string.
//
//			The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
//			Note that k is guaranteed to be a positive integer.
//
//			You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
//			Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
//			For example, there will not be input like 3a or 2[4].
//
//			The test cases are generated so that the length of the output will never exceed 105.
//
//			 
//
//			Example 1:
//
//			Input: s = "3[a]2[bc]"
//			Output: "aaabcbc"
//			Example 2:
//
//			Input: s = "3[a2[c]]"
//			Output: "accaccacc"
//			Example 3:
//
//			Input: s = "2[abc]3[cd]ef"
//			Output: "abcabccdcdcdef"
//			 
//
//			Constraints:
//
//			1 <= s.length <= 30
//			s consists of lowercase English letters, digits, and square brackets '[]'.
//			s is guaranteed to be a valid input.
//			All the integers in s are in the range [1, 300].
	// https://leetcode.com/problems/decode-string/solutions/4547257/java-c-simple-easy-solution-step-by-step-explanation/?envType=study-plan-v2&envId=leetcode-75
	public String decodeString(String s) {
		Stack<Integer> st = new Stack<Integer>();
		Stack<StringBuilder> st1 = new Stack<StringBuilder>();
		int n = 0;
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				n = n * 10 + (c - '0');
			} else if (c == '[') {
				st.push(n);
				n = 0;
				st1.push(sb);
				sb = new StringBuilder();
			} else if (c == ']') {
				int k = st.pop();
				StringBuilder temp = sb;
				sb = st1.pop();
				while (k > 0) {
					sb.append(temp);
					k--;
				}
			} else
				sb.append(c);
		}
		return sb.toString();
	}

//	You have a RecentCounter class which counts the number of recent requests within a certain time frame.
//
//	Implement the RecentCounter class:
//
//	RecentCounter() Initializes the counter with zero recent requests.
//	int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and 
//	returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, 
//	return the number of requests that have happened in the inclusive range [t - 3000, t].
//	It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
//
//	 
//
//	Example 1:
//
//	Input
//	["RecentCounter", "ping", "ping", "ping", "ping"]
//	[[], [1], [100], [3001], [3002]]
//	Output
//	[null, 1, 2, 3, 3]
//
//	Explanation
//	RecentCounter recentCounter = new RecentCounter();
//	recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
//	recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
//	recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
//	recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
//	 
//
//	Constraints:
//
//	1 <= t <= 109
//	Each test case will call ping with strictly increasing values of t.
//	At most 104 calls will be made to ping.

	class RecentCounter {
		Queue<Integer> q;

		public RecentCounter() {
			q = new LinkedList<Integer>();
		}

		public int ping(int t) {
			if (q.isEmpty()) {
				q.add(t);
				return 1;
			}
			int min = t - 3000, max = t;
			while (q.peek() < min)
				q.poll();
			return q.size();
		}
	}

//	In the world of Dota2, there are two parties: the Radiant and the Dire.
//
//	The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting 
//	for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
//
//	Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
//	Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, 
//	he can announce the victory and decide on the change in the game.
//	Given a string senate representing each senator's party belonging. 
//	The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.
//
//	The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
//
//	Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".
//
//	 
//
//	Example 1:
//
//	Input: senate = "RD"
//	Output: "Radiant"
//	Explanation: 
//	The first senator comes from Radiant and he can just ban the next senator's right in round 1. 
//	And the second senator can't exercise any rights anymore since his right has been banned. 
//	And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
//	Example 2:
//
//	Input: senate = "RDD"
//	Output: "Dire"
//	Explanation: 
//	The first senator comes from Radiant and he can just ban the next senator's right in round 1. 
//	And the second senator can't exercise any rights anymore since his right has been banned. 
//	And the third senator comes from Dire and he can ban the first senator's right in round 1. 
//	And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
	// https://leetcode.com/problems/dota2-senate/solutions/3485841/java-solution-o-n-using-single-and-double-queue/?envType=study-plan-v2&envId=leetcode-75
	public String predictPartyVictory(String senate) {
		int dcount = 0, rcount = 0, dban = 0, rban = 0;
		int n = senate.length();
		Queue<Character> q = new LinkedList<>();
		for (char c : senate.toCharArray()) {
			if (c == 'D')
				dcount++;
			else
				rcount++;
			q.offer(c);
		}
		while (dcount > 0 && rcount > 0) {
			char curr = q.poll();
			if (curr == 'D') {
				if (dban > 0) {
					dban--;
					dcount--;
				} else {
					rban++;
					q.offer(curr);
				}
			} else {
				if (rban > 0) {
					rban--;
					rcount--;
				} else {
					dban++;
					q.offer(curr);
				}
			}
		}
		return rcount > 0 ? "Radiant" : "Dire";
	}

//	You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
//
//			The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing,
//			where ⌊x⌋ denotes the largest integer less than or equal to x.
//
//			For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
//			 
//
//			Example 1:
//
//
//			Input: head = [1,3,4,7,1,2,6]
//			Output: [1,3,4,1,2,6]
//			Explanation:
//			The above figure represents the given linked list. The indices of the nodes are written below.
//			Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
//			We return the new list after removing this node. 
//			Example 2:
//
//
//			Input: head = [1,2,3,4]
//			Output: [1,2,4]
//			Explanation:
//			The above figure represents the given linked list.
//			For n = 4, node 2 with value 3 is the middle node, which is marked in red.
//			Example 3:
//
//
//			Input: head = [2,1]
//			Output: [2]
//			Explanation:
//			The above figure represents the given linked list.
//			For n = 2, node 1 with value 1 is the middle node, which is marked in red.
//			Node 0 with value 2 is the only node remaining after removing node 1.

	public class ListNode {
		int val;
		ListNode next;

		public ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public ListNode deleteMiddle(ListNode head) {
		if (head.next == null)
			return head;
		ListNode prev = null, fast = head, slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			prev = slow;
			slow = slow.next;
		}
		if (fast.next != null && fast.next.next == null) {
			prev = slow;
			slow = slow.next;
		}
		prev.next = slow.next;
		slow.next = null;
		return head;
	}

//	  	Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
//
//	  			The first node is considered odd, and the second node is even, and so on.
//
//	  			Note that the relative order inside both the even and odd groups should remain as it was in the input.
//
//	  			You must solve the problem in O(1) extra space complexity and O(n) time complexity.
//
//	  			 
//
//	  			Example 1:
//
//
//	  			Input: head = [1,2,3,4,5]
//	  			Output: [1,3,5,2,4]
//	  			Example 2:
//
//
//	  			Input: head = [2,1,3,5,6,4,7]
//	  			Output: [2,3,6,7,1,5,4]
//	  			 
//
//	  			Constraints:
//
//	  			The number of nodes in the linked list is in the range [0, 104].
//	  			-106 <= Node.val <= 106

	public ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode curr_odd = head, curr_even = head.next, enHead = curr_even;
		while (curr_odd != null && curr_even != null && curr_even.next != null && curr_even.next != null) {
			curr_odd.next = curr_even.next;
			curr_odd = curr_odd.next;
			curr_even.next = curr_odd.next;
			curr_even = curr_even.next;
		}
		curr_odd.next = enHead;
		return head;
	}

//	  	Given the head of a singly linked list, reverse the list, and return the reversed list.
//
//	  			 
//
//	  			Example 1:
//
//
//	  			Input: head = [1,2,3,4,5]
//	  			Output: [5,4,3,2,1]
//	  			Example 2:
//
//
//	  			Input: head = [1,2]
//	  			Output: [2,1]
//	  			Example 3:
//
//	  			Input: head = []
//	  			Output: []
//	  			 
//
//	  			Constraints:
//
//	  			The number of nodes in the list is the range [0, 5000].
//	  			-5000 <= Node.val <= 5000
//	  			 
//
//	  			Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode prev = null, next = head.next;
		while (head != null) {
			ListNode curr = head.next;
			head.next = prev;
			prev = head;
			head = curr;
		}
		return prev;
	}

//	  	In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
//
//	  			For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
//	  			The twin sum is defined as the sum of a node and its twin.
//
//	  			Given the head of a linked list with even length, return the maximum twin sum of the linked list.
//
//	  			 
//
//	  			Example 1:
//
//
//	  			Input: head = [5,4,2,1]
//	  			Output: 6
//	  			Explanation:
//	  			Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
//	  			There are no other nodes with twins in the linked list.
//	  			Thus, the maximum twin sum of the linked list is 6. 
//	  			Example 2:
//
//
//	  			Input: head = [4,2,2,3]
//	  			Output: 7
//	  			Explanation:
//	  			The nodes with twins present in this linked list are:
//	  			- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
//	  			- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
//	  			Thus, the maximum twin sum of the linked list is max(7, 4) = 7. 
//	  			Example 3:
//
//
//	  			Input: head = [1,100000]
//	  			Output: 100001
//	  			Explanation:
//	  			There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
//	  			 
//
//	  			Constraints:
//
//	  			The number of nodes in the list is an even integer in the range [2, 105].
//	  			1 <= Node.val <= 105
	public int pairSum(ListNode head) {
		int ans = Integer.MIN_VALUE;
		ListNode slow = head, fast = head;
		while (fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode newHead = slow.next;
		slow.next = null;
		newHead = reverseList(newHead);
		while (head != null && newHead != null) {
			ans = Math.max(ans, head.val + newHead.val);
			head = head.next;
			newHead = newHead.next;
		}
		return ans;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

//	  	Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
//
//
//
//	  	For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
//
//	  	Two binary trees are considered leaf-similar if their leaf value sequence is the same.
//
//	  	Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
//
//	  	 
//
//	  	Example 1:
//
//
//	  	Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
//	  	Output: true
//	  	Example 2:
//
//
//	  	Input: root1 = [1,2,3], root2 = [1,3,2]
//	  	Output: false

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> leafValues1 = new ArrayList<>();
		List<Integer> leafValues2 = new ArrayList<>();

		collectLeafValues(root1, leafValues1);
		collectLeafValues(root2, leafValues2);

		return leafValues1.equals(leafValues2);
	}

	private void collectLeafValues(TreeNode root, List<Integer> leafValues) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			leafValues.add(root.val);
		}
		collectLeafValues(root.left, leafValues);
		collectLeafValues(root.right, leafValues);
	}

//	    Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
//
//	    Return the number of good nodes in the binary tree.
//
//	     
//
//	    Example 1:
//
//
//
//	    Input: root = [3,1,4,3,null,1,5]
//	    Output: 4
//	    Explanation: Nodes in blue are good.
//	    Root Node (3) is always a good node.
//	    Node 4 -> (3,4) is the maximum value in the path starting from the root.
//	    Node 5 -> (3,4,5) is the maximum value in the path
//	    Node 3 -> (3,1,3) is the maximum value in the path.
//	    Example 2:
//
//
//
//	    Input: root = [3,3,null,4,2]
//	    Output: 3
//	    Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
//	    Example 3:
//
//	    Input: root = [1]
//	    Output: 1
//	    Explanation: Root is considered as good.
//	     
//
//	    Constraints:
//
//	    The number of nodes in the binary tree is in the range [1, 10^5].
//	    Each node's value is between [-10^4, 10^4].

	public int goodNodes(TreeNode root) {
		return preOrder(root, root.val);
	}

	public int preOrder(TreeNode root, int curr_max) {
		if (root == null)
			return 0;
		int count = 0;
		if (root.val >= curr_max) {
			count++;
			curr_max = root.val;
		}
		return count + preOrder(root.left, curr_max) + preOrder(root.right, curr_max);
	}

//	    Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
//
//	    		The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
//
//	    		 
//
//	    		Example 1:
//
//
//	    		Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//	    		Output: 3
//	    		Explanation: The paths that sum to 8 are shown.
//	    		Example 2:
//
//	    		Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//	    		Output: 3
//	    		 
//
//	    		Constraints:
//
//	    		The number of nodes in the tree is in the range [0, 1000].
//	    		-109 <= Node.val <= 109
//	    		-1000 <= targetSum <= 1000

	// https://leetcode.com/problems/path-sum-iii/solutions/4110803/437-path-sum-iii-simple-solution-using-dfs/?envType=study-plan-v2&envId=leetcode-75
	int targetCount = 0;

	public int pathSum(TreeNode root, int targetSum) {
		if (root == null)
			return 0;
		pathSumUtil(root, targetSum, 0);
		pathSum(root.left, targetSum);
		pathSum(root.right, targetSum);
		return targetCount;
	}

	private void pathSumUtil(TreeNode root, int targetSum, long currSum) {
		if (root == null)
			return;
		currSum += root.val;
		if (currSum == targetSum)
			targetCount++;
		pathSumUtil(root.left, targetSum, currSum);
		pathSumUtil(root.right, targetSum, currSum);

	}

//	    You are given the root of a binary tree.
//
//	    A ZigZag path for a binary tree is defined as follow:
//
//	    Choose any node in the binary tree and a direction (right or left).
//	    If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
//	    Change the direction from right to left or from left to right.
//	    Repeat the second and third steps until you can't move in the tree.
//	    Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
//
//	    Return the longest ZigZag path contained in that tree.
//
//	     
//
//	    Example 1:
//
//
//	    Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
//	    Output: 3
//	    Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
//	    Example 2:
//
//
//	    Input: root = [1,1,1,null,1,null,null,1,1,null,1]
//	    Output: 4
//	    Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
//	    Example 3:
//
//	    Input: root = [1]
//	    Output: 0
//	     
//
//	    Constraints:
//
//	    The number of nodes in the tree is in the range [1, 5 * 104].
//	    1 <= Node.val <= 100

	// https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/solutions/3435870/java-solution-with-o-n-tc/?envType=study-plan-v2&envId=leetcode-75
	public int maxLength = 0;

	public void zigZagPath(TreeNode root, String dir, int currLength) {
		if (root == null)
			return;
		maxLength = Math.max(maxLength, currLength);
		if (dir == "R") {
			zigZagPath(root.left, "L", currLength + 1);
			zigZagPath(root.right, "R", 1);
		} else {
			zigZagPath(root.right, "R", currLength + 1);
			zigZagPath(root.left, "L", 1);
		}
	}

	public int longestZigZag(TreeNode root) {
		zigZagPath(root, "L", 0);
		zigZagPath(root, "R", 0);
		return maxLength;
	}
	
//	Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
//
//	Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
//
//	 
//
//	Example 1:
//
//
//	Input: root = [1,7,0,7,-8,null,null]
//	Output: 2
//	Explanation: 
//	Level 1 sum = 1.
//	Level 2 sum = 7 + 0 = 7.
//	Level 3 sum = 7 + -8 = -1.
//	So we return the level with the maximum sum which is level 2.
//	Example 2:
//
//	Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
//	Output: 2
//	 
//
//	Constraints:
//
//	The number of nodes in the tree is in the range [1, 104].
//	-105 <= Node.val <= 105
	public int maxLevelSum(TreeNode root) {
        if(root==null)
        	return 0;
        int ans=0,res=0;
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty()) {
        	TreeNode nd = q.poll();
        	if(nd!=null) {
        		ans+=nd.val;
        		if(nd.left!=null)
        			q.offer(nd.left);
        		if(nd.right!=null)
        			q.offer(nd.right);
        	}else {
        		res=Math.max(ans, res);
        		ans=0;
        		if(!q.isEmpty())
        			q.offer(null);
        	}
        }
        return res;
    }
	
//	You are given the root of a binary search tree (BST) and an integer val.
//
//	Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
//
//	 
//
//	Example 1:
//
//
//	Input: root = [4,2,7,1,3], val = 2
//	Output: [2,1,3]
//	Example 2:
//
//
//	Input: root = [4,2,7,1,3], val = 5
//	Output: []
//	 
//
//	Constraints:
//
//	The number of nodes in the tree is in the range [1, 5000].
//	1 <= Node.val <= 107
//	root is a binary search tree.
//	1 <= val <= 107
	
	 	public TreeNode searchBST(TreeNode root, int val) {
	 		if(root==null)
	 			return null;
	        if(root.val==val)
	        	return root;
	        if(root.val>val)
	        	return searchBST(root.left, val);
	        else
	        	return searchBST(root.right, val);
	    }
	
	 	
//	 	Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
//
//	 	Basically, the deletion can be divided into two stages:
//
//	 	Search for a node to remove.
//	 	If the node is found, delete the node.
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: root = [5,3,6,2,4,null,7], key = 3
//	 	Output: [5,4,6,2,null,null,7]
//	 	Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
//	 	One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
//	 	Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
//
//	 	Example 2:
//
//	 	Input: root = [5,3,6,2,4,null,7], key = 0
//	 	Output: [5,3,6,2,4,null,7]
//	 	Explanation: The tree does not contain a node with value = 0.
//	 	Example 3:
//
//	 	Input: root = [], key = 0
//	 	Output: []
	 	
	 	public TreeNode deleteNode(TreeNode root, int key) {
	        if(root==null)
	        	return root;
	       if(key<root.val)
	    	   root.left=deleteNode(root.left, key);
	       else if(key>root.val)
	    	   root.right=deleteNode(root.right, key);
	       else {
	    	   if(root.left==null)
	    		   return root.right;
	    	   if(root.right==null)
	    		   return root.left;
	    	   root.val=findLowestValueInRightChild(root.right);
	    	   root.right=deleteNode(root.right, root.val);
	       }
	       return root;
	        
	    }
	 	private int findLowestValueInRightChild(TreeNode root) {
	 		int k=root.val;
	 		while(root.left!=null) {
	 			k=root.left.val;
	 			root=root.left;
	 		}
	 		return k;
	 	}
	 	
//	 	There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However,
//	 	you cannot enter a locked room without having its key.
//
//	 	When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks,
//	 	and you can take all of them with you to unlock the other rooms.
//
//	 	Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: rooms = [[1],[2],[3],[]]
//	 	Output: true
//	 	Explanation: 
//	 	We visit room 0 and pick up key 1.
//	 	We then visit room 1 and pick up key 2.
//	 	We then visit room 2 and pick up key 3.
//	 	We then visit room 3.
//	 	Since we were able to visit every room, we return true.
//	 	Example 2:
//
//	 	Input: rooms = [[1,3],[3,0,1],[2],[0]]
//	 	Output: false
//	 	Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
//	 	 
//
//	 	Constraints:
//
//	 	n == rooms.length
//	 	2 <= n <= 1000
//	 	0 <= rooms[i].length <= 1000
//	 	1 <= sum(rooms[i].length) <= 3000
//	 	0 <= rooms[i][j] < n
//	 	All the values of rooms[i] are unique.
	 	 public boolean canVisitAllRooms(List<List<Integer>> rooms) {
	         int roomsCount = rooms.size();
	         Set<Integer> open=new HashSet<Integer>();
	         Queue<Integer> q=new LinkedList<Integer>();
	         open.add(0);
	         q.offer(0);
	         while(!q.isEmpty()) {
	        	 int i=q.poll();
	        	 if(open.contains(i)) {
	        		 for(int n:rooms.get(i)) {
	        			 if(!open.contains(n)) {
	        				 open.add(n);
		        			 q.offer(n);
		        			 if(open.size()==roomsCount)
		        				 return true;
	        			 }
	        		 }
	        	 }
	         }
	         return open.size()==roomsCount;
	     }
	 	 
//	 	There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, 
//	 	and city b is connected directly with city c, then city a is connected indirectly with city c.
//
//	 	A province is a group of directly or indirectly connected cities and no other cities outside of the group.
//
//	 	You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
//
//	 	Return the total number of provinces.
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//	 	Output: 2
//	 	Example 2:
//
//
//	 	Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//	 	Output: 3
	 	 
	 	public int findCircleNum(int[][] isConnected) {
	       int cities = isConnected.length,provinces=0; 
	       boolean[] visited=new boolean[cities];
	       for(int i=0;i<cities;i++) {
	    	   if(!visited[i]) {
	    		   DFSUtil(isConnected,visited,i);
	    		   cities++;
	    	   }
	       }
	       return cities;
	    }
	 	
	 	private void DFSUtil(int[][] isConnected, boolean[] visited,int city) {
	 		if(visited[city])
	 			return;
	 		visited[city]=true;
	 		for(int i=0;i<isConnected.length;i++) {
	 			if(i!=city&&isConnected[i][city]==1) {
	 				DFSUtil(isConnected, visited, i);
	 			}
	 		}
	 		return;
	 	}
	 	
//	 	There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree).
//	 	Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
//
//	 	Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
//
//	 	This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
//
//	 	Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
//
//	 	It's guaranteed that each city can reach city 0 after reorder.
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]] // 0->4,1->0,2->,3->2,1,4->,5->4
//	 	Output: 3
//	 	Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
//	 	Example 2:
//
//
//	 	Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
//	 	Output: 2
//	 	Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
//	 	Example 3:
//
//	 	Input: n = 3, connections = [[1,0],[2,0]]
//	 	Output: 0
	 	
	 	
	 	public int minReorder(int n, int[][] connections) {
	 		List<List<Integer>> graph = new ArrayList<List<Integer>>();
	 		for(int i=0;i<n;i++)
	 			graph.add(new ArrayList<Integer>());
	 		for(int[]a:connections) {
	 			graph.get(a[0]).add(a[1]);
	 			graph.get(a[1]).add(-a[0]);
	 		}
	 		return findRoute(graph, 0, -1);
	        
	    }
	 	
	 	private int findRoute(List<List<Integer>> graph,int u,int parent) {
	 		int changes=0;
	 		for(int v:graph.get(u)) {
	 			if(Math.abs(v)==parent)
	 				continue;
	 			if(v>0)
	 				changes++;
	 			changes+=findRoute(graph, Math.abs(v), u);
	 		}
	 		return changes;
	 	}
	 	
//	 	You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). 
//	 	You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
//
//	 			In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze.
//	 			Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. 
//	 			The entrance does not count as an exit.
//
//	 			Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
//
//	 			 
//
//	 			Example 1:
//
//
//	 			Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
//	 			Output: 1
//	 			Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
//	 			Initially, you are at the entrance cell [1,2].
//	 			- You can reach [1,0] by moving 2 steps left.
//	 			- You can reach [0,2] by moving 1 step up.
//	 			It is impossible to reach [2,3] from the entrance.
//	 			Thus, the nearest exit is [0,2], which is 1 step away.
//	 			Example 2:
//
//
//	 			Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
//	 			Output: 2
//	 			Explanation: There is 1 exit in this maze at [1,2].
//	 			[1,0] does not count as an exit since it is the entrance cell.
//	 			Initially, you are at the entrance cell [1,0].
//	 			- You can reach [1,2] by moving 2 steps right.
//	 			Thus, the nearest exit is [1,2], which is 2 steps away.
//	 			Example 3:
//
//
//	 			Input: maze = [[".","+"]], entrance = [0,0]
//	 			Output: -1
//	 			Explanation: There are no exits in this maze.
	 	class Pair{
	 		int first;
	 		int second;
	 		int steps;
			public Pair(int first, int second, int steps) {
				super();
				this.first = first;
				this.second = second;
				this.steps = steps;
			}
	 	}
	 	
	 	public int nearestExit(char[][] maze, int[] entrance) {
	 		int n=maze.length,m=maze[0].length,x=entrance[0],y=entrance[1],ans=0;
	 		int[] rowDir= {1,-1,0,0},colDir= {0,0,1,-1};
	 		Queue<Pair> q = new LinkedList<>();
	 		q.offer(new Pair(x,y,0));
	 		while(!q.isEmpty()) {
	 			int row=q.peek().first;
	 			int col=q.peek().second;
	 			int step=q.peek().steps;
	 			maze[row][col]='+';
	 			q.poll();
	 			for(int i=0;i<4;i++) {
	 				int nrow=row+rowDir[i];
	 				int ncol=col+colDir[i];
	 				if(nrow>=0&&nrow<n&&ncol>=0&&ncol<m&& maze[nrow][ncol]=='.') {
	 					maze[nrow][ncol]='+';
	 					q.add(new Pair(nrow, ncol,step+1));
	 					if(nrow==0||nrow==n-1||ncol==0||ncol==m-1) {
	 						ans=step+1;
	 						return ans;
	 					}
	 				}
	 			}
	 		}
	 		return -1;
	 	}
	 	
	 	
//	 	You are given an m x n grid where each cell can have one of three values:
//
//	 		0 representing an empty cell,
//	 		1 representing a fresh orange, or
//	 		2 representing a rotten orange.
//	 		Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
//
//	 		Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
//
//	 		 
//
//	 		Example 1:
//
//
//	 		Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//	 		Output: 4
//	 		Example 2:
//
//	 		Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//	 		Output: -1
//	 		Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
//	 		Example 3:
//
//	 		Input: grid = [[0,2]]
//	 		Output: 0
//	 		Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
	 	public int orangesRotting(int[][] grid) {
	        int n=grid.length,m=grid[0].length,fresh=0,rotten=0;
	        Queue<int[]> q=new LinkedList<int[]>();
	        for(int i=0;i<n;i++) {
	        	for(int j=0;j<m;j++) {
	        		if(grid[i][j]==2)
	        			q.offer(new int[] {i,j});
	        		else if (grid[i][j]==1)
	        			fresh++;
	        	}
	        }
	        if(fresh==0)
	        	return 0;
	        if(q.isEmpty())
	        	return -1;
	        int minutes=-1;
	        int[][] dir = {{1, 0},{-1, 0},{0, -1},{0, 1}};
	        while(!q.isEmpty()) {
	        	int size = q.size();
	        	while(size>0) {
	        		int x=q.peek()[0],y=q.peek()[1];
	        		q.poll();
	        		for(int i=0;i<dir.length;i++) {
	        			int r=x+dir[i][0],c=y+dir[i][1];
	        			if(r>=0&&r<n&&c>=0&&c<m&&grid[r][c]==1) {
	        				fresh--;
	        				grid[r][c]=2;
	        				q.offer(new int[] {r,c});
	        			}
	        		}
	        		size--;
	        	}
	        	minutes++;
	        }
	        if(fresh==0)
	        	return minutes;
	        return -1;
	    }
	 	
//	 	You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
//
//	 	Implement the SmallestInfiniteSet class:
//
//	 	SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
//	 	int popSmallest() Removes and returns the smallest integer contained in the infinite set.
//	 	void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
//	 	 
//
//	 	Example 1:
//
//	 	Input
//	 	["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
//	 	[[], [2], [], [], [], [1], [], [], []]
//	 	Output
//	 	[null, null, 1, 2, 3, null, 1, 4, 5]
//
//	 	Explanation
//	 	SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
//	 	smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
//	 	smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
//	 	smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
//	 	smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
//	 	smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
//	 	smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
//	 	                                   // is the smallest number, and remove it from the set.
//	 	smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
//	 	smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
//	 	 
//
//	 	Constraints:
//
//	 	1 <= num <= 1000
//	 	At most 1000 calls will be made in total to popSmallest and addBack.
	 	//https://leetcode.com/problems/smallest-number-in-infinite-set/solutions/3453057/java-beats-100-easy-to-understand/?envType=study-plan-v2&envId=leetcode-75
	 	class SmallestInfiniteSet {
	 		PriorityQueue<Integer> pq;
	 		int minVal;

	 	    public SmallestInfiniteSet() {
	 	        this.minVal=1;
	 	        this.pq=new PriorityQueue<Integer>();
	 	    }
	 	    
	 	    public int popSmallest() {
	 	        if(!pq.isEmpty())
	 	        	return pq.poll();
	 	        minVal++;
	 	        return minVal-1;
	 	    }
	 	    
	 	    public void addBack(int num) {
	 	        if(minVal>num && !pq.contains(num))
	 	        	pq.offer(num);
	 	    }
	 	}
	 	
//	 	You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k.
//	 	You must choose a subsequence of indices from nums1 of length k.
//
//	 	For chosen indices i0, i1, ..., ik - 1, your score is defined as:
//
//	 	The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
//	 	It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
//	 	Return the maximum possible score.
//
//	 	A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
//	 	Output: 12
//	 	Explanation: 
//	 	The four possible subsequence scores are:
//	 	- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
//	 	- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6. 
//	 	- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12. 
//	 	- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
//	 	Therefore, we return the max score, which is 12.
//	 	Example 2:
//
//	 	Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
//	 	Output: 30
//	 	Explanation: 
//	 	Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.
	 	//https://leetcode.com/problems/maximum-subsequence-score/solutions/3558592/java-priorityqueue-easy-understanding/?envType=study-plan-v2&envId=leetcode-75
	 	public long maxScore(int[] nums1, int[] nums2, int k) {
	 		int [][]pair = new int [nums1.length][2];
	 		for(int i=0;i<nums1.length;i++) {
	 			pair[i][0]=nums2[i];
	 			pair[i][1]=nums1[i];
	 		}
	 		Arrays.sort(pair, (a,b)->b[0]-a[0]);
	 		long ans=0,sum=0;int j=0,min=Integer.MAX_VALUE;
	 		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	 		while(j<k) {
	 			sum+=pair[j][1];
	 			min=Math.min(pair[j][0], min);
	 			pq.offer(pair[j][1]);
	 			j++;
	 		}
	 		
	 		ans=min*sum;
	 		for(int i=j;i<nums1.length;i++) {
	 			min=pair[i][0];
	 			if(pq.peek()<pair[i][1]) {
	 				sum=sum+pair[i][1]-pq.poll();
	 				pq.offer(pair[i][1]);
	 				ans=Math.max(ans, sum*min);
	 			}
	 		}
	 		return ans;
	        
	    }
	 	
//	 	You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
//
//	 	You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:
//
//	 	You will run k sessions and hire exactly one worker in each session.
//	 	In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers.
//	 	Break the tie by the smallest index.
//	 	For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the 
//	 	lowest cost [3,2,7,7,1,2].
//	 	In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest
//	 	index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
//	 	If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
//	 	A worker can only be chosen once.
//	 	Return the total cost to hire exactly k workers.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
//	 	Output: 11
//	 	Explanation: We hire 3 workers in total. The total cost is initially 0.
//	 	- In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index,
//	 	which is 3. The total cost = 0 + 2 = 2.
//	 	- In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
//	 	- In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11.
//	 	Notice that the worker with index 3 was common in the first and last four workers.
//	 	The total hiring cost is 11.
//	 	Example 2:
//
//	 	Input: costs = [1,2,4,1], k = 3, candidates = 3
//	 	Output: 4
//	 	Explanation: We hire 3 workers in total. The total cost is initially 0.
//	 	- In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. 
//	 	The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last 3 workers.
//	 	- In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
//	 	- In the third hiring round there are less than three candidates. We choose the worker from the remaining workers [2,4]. The lowest cost is 2 (index 0). 
//	 	The total cost = 2 + 2 = 4.
//	 	The total hiring cost is 4.
	 	
	 	//https://leetcode.com/problems/total-cost-to-hire-k-workers/solutions/3700306/simple-heap-solution-using-two-heap/?envType=study-plan-v2&envId=leetcode-75
	 	// 2nd solution gave TLE
	 	public long totalCost(int[] costs, int k, int candidates) {
	 		
	 		Queue<Integer> pql = new PriorityQueue<>(), pqr = new PriorityQueue<>();
	 		int p=0,q=costs.length-1;
	 		for(int i=0;i<candidates&&p<=q;i++) {
	 			pql.offer(costs[p]);
	 			p++;
	 		}
	 		for(int i=0;i<candidates&&p<=q;i++) {
	 			pqr.offer(costs[q]);
	 			q--;
	 		}
	 		long total=0;
	 		while(k>0) {
	 			if(pqr.isEmpty()||(!pql.isEmpty()&& pql.peek()<=pqr.peek())) {
	 				total+=pql.poll();
	 				if(p<=q)pql.offer(costs[p++]);
	 			}else {
	 				total+=pqr.poll();
	 				if(p<=q)pqr.offer(costs[q--]);
	 			}
	 			k--;
	 		}
	 		return total;
//	        int min=Integer.MAX_VALUE,min_index=0,len=costs.length,count=k;
//	        long sum=0;
//	        PriorityQueue<Integer> pq1 =new PriorityQueue<Integer>();
//	        PriorityQueue<Integer> pq2 =new PriorityQueue<Integer>();
//	        while(count>0) {
//	        	if(len>=2*candidates) {
//	        		int idx1=0,idx2=0;
//	        		for(int i=0;i<candidates;i++) {
//	        			if(pq1.isEmpty()&& pq2.isEmpty()) {
//	        				idx1=i;
//	        				idx2=len-i-1;
//	        			}else {
//	        				if(pq1.peek()>costs[i])
//									idx1=i;
//	        				if(pq2.peek()>=costs[len-i-1])
//	        					idx2=len-i-1;
//	        			}
//	        			pq1.offer(costs[i]);
//	        			pq2.offer(costs[len-i-1]);
//	        			
//	        		}
//	        		if(pq1.peek()>pq2.peek()) {
//	        			min=pq2.peek();
//	        			min_index=idx2;
//	        		}else if(pq1.peek()<pq2.peek()) {
//	        			min=pq1.peek();
//	        			min_index=idx1;
//	        		}else {
//	        			if(idx1<idx2) {
//	        				min=pq1.peek();
//		        			min_index=idx1;
//	        			}else {
//	        				min=pq2.peek();
//		        			min_index=idx2;
//	        			}
//	        		}
//	        	}else {
//	        		for(int i=0;i<len;i++) {
//		        		pq1.offer(costs[i]);
//		        		//if(pq.size()>k) {
//		        			if(min>pq1.peek()) {
//		        				min=pq1.peek();
//		        				min_index=i;
//		        			}else if(min==pq1.peek()) {
//		        				if(min_index>i) {
//		        					min_index=i;
//		        				}
//		        			}
//		        			// pq.poll();
//		        		//}
//		        	}
//	        	}
//	        	
//	        	sum+=min;
//	        	for(int i=min_index;i<len-1;i++) {
//	        		costs[i]=costs[i+1];
//	        	}
//	        	pq1.clear();pq2.clear();
//	        	len--;
//	        	count--;
//	        	min=Integer.MAX_VALUE;min_index=0;
//	        }
//	        return sum;
	    }
	 	
	 	
//	 	We are playing the Guess Game. The game is as follows:
//
//	 		I pick a number from 1 to n. You have to guess which number I picked.
//
//	 		Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
//
//	 		You call a pre-defined API int guess(int num), which returns three possible results:
//
//	 		-1: Your guess is higher than the number I picked (i.e. num > pick).
//	 		1: Your guess is lower than the number I picked (i.e. num < pick).
//	 		0: your guess is equal to the number I picked (i.e. num == pick).
//	 		Return the number that I picked.
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: n = 10, pick = 6
//	 		Output: 6
//	 		Example 2:
//
//	 		Input: n = 1, pick = 1
//	 		Output: 1
//	 		Example 3:
//
//	 		Input: n = 2, pick = 1
//	 		Output: 1
	 	
	 	public int guessNumber(int n) {
	        int start=1,end=n;
	        while(start<=end) {
	        	int mid = start+(end-start)/2;
	        	if(guess(mid)==0)
	        		return mid;
	        	if(guess(mid)==-1)
	        		end=mid-1;
	        	else if(guess(mid)==1)
	        		start=mid+1;
	        }
	        return n;
	    }
	 	
//	 	You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell 
//	 	and potions[j] represents the strength of the jth potion.
//
//	 	You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
//
//	 	Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
//	 	Output: [4,0,3]
//	 	Explanation:
//	 	- 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
//	 	- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
//	 	- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
//	 	Thus, [4,0,3] is returned.
//	 	Example 2:
//
//	 	Input: spells = [3,1,2], potions = [8,5,8], success = 16
//	 	Output: [2,0,2]
//	 	Explanation:
//	 	- 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
//	 	- 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful. 
//	 	- 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful. 
//	 	Thus, [2,0,2] is returned.
	 	public int[] successfulPairs(int[] spells, int[] potions, long success) {
	        int n=spells.length,m=potions.length;
	        Arrays.sort(potions);
	        int []res = new int[n];
	        for(int i=0;i<n;i++) {
	        	int a=spells[i];
	        	int start=0,end=m-1;
	        	while(start<=end) {
	        		int mid=start+(end-start)/2;
	        		long pro=(long)potions[mid]*a;
	        		if(pro>=success)
	        			end=mid-1;
	        		else
	        			start=mid+1;
	        	}
	        	res[i]=m-start;
	        }
	        return res;
	    }	
	 	
//	 	Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
//
//	 	Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
//	 	If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
//
//	 	Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
//
//	 	Return the minimum integer k such that she can eat all the bananas within h hours.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: piles = [3,6,7,11], h = 8
//	 	Output: 4
//	 	Example 2:
//
//	 	Input: piles = [30,11,23,4,20], h = 5
//	 	Output: 30
//	 	Example 3:
//
//	 	Input: piles = [30,11,23,4,20], h = 6
//	 	Output: 23
	 	
	 	public int minEatingSpeed(int[] piles, int h) {
//	       Arrays.sort(piles);
//	       int start=piles[0],end=piles[piles.length-1];
	 		int start=1,end=1000000000;
	       while(start<=end) {
	    	   int mid=start+(end-start)/2;
	    	   if(canEat(piles, h, mid))
	    		   end=mid-1;
	    	   else
	    		   start=mid+1;
	       }
	       return start;
	    }
	 	
	 	private boolean canEat(int[] piles, int h,int k) {
	 		int hours=0;
	 		for(int pile:piles) {
	 			if(pile<k)
	 				hours++;
	 			else {
	 				int div=pile/k;
	 				hours+=div;
	 				if(pile%k!=0)
	 					hours++;
	 			}
	 		}
	 		return hours<=h;
	 	}
	 	
//	 	Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
//
//	 		Only numbers 1 through 9 are used.
//	 		Each number is used at most once.
//	 		Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: k = 3, n = 7
//	 		Output: [[1,2,4]]
//	 		Explanation:
//	 		1 + 2 + 4 = 7
//	 		There are no other valid combinations.
//	 		Example 2:
//
//	 		Input: k = 3, n = 9
//	 		Output: [[1,2,6],[1,3,5],[2,3,4]]
//	 		Explanation:
//	 		1 + 2 + 6 = 9
//	 		1 + 3 + 5 = 9
//	 		2 + 3 + 4 = 9
//	 		There are no other valid combinations.
//	 		Example 3:
//
//	 		Input: k = 4, n = 1
//	 		Output: []
//	 		Explanation: There are no valid combinations.
//	 		Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
//	 		 
//
//	 		Constraints:
//
//	 		2 <= k <= 9
//	 		1 <= n <= 60
	 	
	 	public List<List<Integer>> combinationSum3(int k, int n) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        combinationSum3Util(k, n, res, 0, 1, new ArrayList<Integer>());
	        return res;
	    }
	 	
	 	public void combinationSum3Util(int k,int n,List<List<Integer>> res,int curr_sum,int startInt,List<Integer> list) {
	 		if(list.size()==k) {
	 			if(curr_sum==n)
	 				res.add(new ArrayList<Integer>(list));
	 			return;
	 		}
	 		for(int i=startInt;i<=9;i++) {
	 			if(curr_sum+i<=n) {
	 				list.add(i);
	 				combinationSum3Util(k, n, res, curr_sum+i, i+1, list);
	 				list.remove(list.size()-1);
	 			}
	 		}
	 	}
	 	
//	 	The Tribonacci sequence Tn is defined as follows: 
//
//	 		T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
//
//	 		Given n, return the value of Tn.
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: n = 4
//	 		Output: 4
//	 		Explanation:
//	 		T_3 = 0 + 1 + 1 = 2
//	 		T_4 = 1 + 1 + 2 = 4
//	 		Example 2:
//
//	 		Input: n = 25
//	 		Output: 1389537
//	 		 
//
//	 		Constraints:
//
//	 		0 <= n <= 37
//	 		The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
	 	
	 	public int tribonacci(int n) {
	 		if(n==0)return 0;
	 		if(n==2||n==1) return 1;
	       int []dp=new int[n+1];
	       dp[0]=0;dp[1]=1;dp[2]=1;
	       dp[3]=dp[0]+dp[1]+dp[2];
	       if(n>3) {
	    	   for(int i=4;i<=n;i++)
	    		   dp[i]=2*dp[i-1]-dp[i-4];
	       }
	       return dp[n];
	    }
	 	
//	 	You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
//
//	 	You can either start from the step with index 0, or the step with index 1.
//
//	 	Return the minimum cost to reach the top of the floor.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: cost = [10,15,20]
//	 	Output: 15
//	 	Explanation: You will start at index 1.
//	 	- Pay 15 and climb two steps to reach the top.
//	 	The total cost is 15.
//	 	Example 2:
//
//	 	Input: cost = [1,100,1,1,1,100,1,1,100,1]
//	 	Output: 6
//	 	Explanation: You will start at index 0.
//	 	- Pay 1 and climb two steps to reach index 2.
//	 	- Pay 1 and climb two steps to reach index 4.
//	 	- Pay 1 and climb two steps to reach index 6.
//	 	- Pay 1 and climb one step to reach index 7.
//	 	- Pay 1 and climb two steps to reach index 9.
//	 	- Pay 1 and climb one step to reach the top.
//	 	The total cost is 6.
//	 	 
//
//	 	Constraints:
//
//	 	2 <= cost.length <= 1000
//	 	0 <= cost[i] <= 999
	 	
	 	public int minCostClimbingStairs(int[] cost) {
	 		Map<Integer,Integer>mp=new HashMap<Integer, Integer>();
	 		int n=cost.length;
	 		int min = Math.min(minCostClimbingStairsUtil(cost, n-1, mp),
	 				minCostClimbingStairsUtil(cost, n-2, mp));
	 		return min;
	    }
	 	
	 	public int  minCostClimbingStairsUtil(int[] cost,int n,Map<Integer,Integer>mp) {
	        if(n<0)
	        	return 0;
	        if(n==0||n==1) {
	        	mp.put(n, cost[n]);
	        	return mp.get(n);
	        }
	        if(!mp.containsKey(n)) {
	        	int min=cost[n]+Math.min(minCostClimbingStairsUtil(cost, n-1, mp),
	        			minCostClimbingStairsUtil(cost, n-2, mp));
	        	mp.put(n, min);
	        }
	        return mp.get(n);
	    }
	 	
//	 	You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only 
//	 	constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police 
//	 	if two adjacent houses were broken into on the same night.
//
//	 	Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
//
//	 	 
//
//	 	Example 1:
//
//	 	Input: nums = [1,2,3,1]
//	 	Output: 4
//	 	Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//	 	Total amount you can rob = 1 + 3 = 4.
//	 	Example 2:
//
//	 	Input: nums = [2,7,9,3,1]
//	 	Output: 12
//	 	Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//	 	Total amount you can rob = 2 + 9 + 1 = 12.
//	 	 
//
//	 	Constraints:
//
//	 	1 <= nums.length <= 100
//	 	0 <= nums[i] <= 400
	 	
	 	public int rob(int[] nums) {
	        int n=nums.length,amount=Integer.MIN_VALUE;
	        int[]dp=new int[n];
	        dp[n-1]=nums[n-1];
	        Arrays.fill(dp, -1);
	        for(int i=n-1;i>=0;i--) {
	        	int val = robUtil(dp,i,nums[i],n,nums);
	        	amount=Math.max(amount, val);
	        }
	        return amount;
	    }
	 	
	 	private int robUtil(int[]dp,int currIdx,int currAmt,int n,int[]nums) {
	 		if(currIdx==n-1) {
	 			return nums[currIdx];
	 		}
	 		if(dp[currIdx]==-1) {
	 			int k=currAmt;
	 			for(int i=currIdx+2;i<n;i++) {
	 				int amt;
	 				if(dp[i]!=-1)
	 					amt=currAmt+dp[i];
	 				else
	 				 amt = currAmt+robUtil(dp, i, nums[i], n, nums);
	 				k=Math.max(amt, k);
	 			}
	 			dp[currIdx]=k;
	 		}
	 		return dp[currIdx];
	 			
	 	}
	 	
//	 	You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
//
//
//	 	Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
//
//	 	In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent 
//	 	cells on the board such that exactly one of the tilings has both squares occupied by a tile.
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: n = 3
//	 	Output: 5
//	 	Explanation: The five different ways are show above.
//	 	Example 2:
//
//	 	Input: n = 1
//	 	Output: 1
	 	//https://www.youtube.com/watch?v=CecjOo4Zo-g
	 	static int n;
	 	  static Long[][] dp;
	 	  static long MOD = 1_000_000_007;

	 	  public int numTilings(int N) {
	 	    n = N;
	 	    dp = new Long[n + 1][1 << 2];
	 	    long ans = f(0, true, true);
	 	    return (int) ans;
	 	  }

	 	  // t1 - whether tile 1 is available
	 	  // t2 - whether tile 2 is available
	 	  static long f(int i, boolean t1, boolean t2) {
	 	    // Finished fully tiling the board.
	 	    if (i == n) {
	 	      return 1;
	 	    }
	 	    int state = makeState(t1, t2);
	 	    if (dp[i][state] != null) {
	 	      return dp[i][state];
	 	    }

	 	    // Zones that define which regions are free. For the surrounding 4 tiles:
	 	    // t1 t3
	 	    // t2 t4
	 	    boolean t3 = i + 1 < n;
	 	    boolean t4 = i + 1 < n;

	 	    long count = 0;

	 	    // Placing:
	 	    // XX
	 	    // X
	 	    if (t1 && t2 && t3) count += f(i + 1, false, true);

	 	    // Placing:
	 	    // X
	 	    // XX
	 	    if (t1 && t2 && t4) count += f(i + 1, true, false);

	 	    // Placing:
	 	    // XX
	 	    // #X
	 	    if (t1 && !t2 && t3 && t4) count += f(i + 1, false, false);

	 	    // Placing:
	 	    // #X
	 	    // XX
	 	    if (!t1 && t2 && t3 && t4) count += f(i + 1, false, false);

	 	    // Placing
	 	    // X
	 	    // X
	 	    if (t1 && t2) count += f(i + 1, true, true);

	 	    // Placing two horizontals. We don't place 2 verticals because
	 	    // that's accounted for with the single vertical tile:
	 	    // XX
	 	    // XX
	 	    if (t1 && t2 && t3 && t4) count += f(i + 1, false, false);

	 	    // Placing:
	 	    // XX
	 	    // #
	 	    if (t1 && !t2 && t3) count += f(i + 1, false, true);

	 	    // Placing:
	 	    // #
	 	    // XX
	 	    if (!t1 && t2 && t4) count += f(i + 1, true, false);

	 	    // Current column is already fully tiled, so move to next column
	 	    // #
	 	    // #
	 	    if (!t1 && !t2) count += f(i + 1, true, true);

	 	    return dp[i][state] = count % MOD;
	 	  }

	 	  static int makeState(boolean row1, boolean row2) {
	 	    if (!row1 && !row2)
	 	    	return 0;
	 	    else if (row1 && !row2)
	 	    	return 1;
	 	    else if (!row1 && row2)
	 	    	return 2;
	 	    else
	 	    	return 3;
	 	  }
	 	  
//	 	 There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). 
//	 	 The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
//
//	 	Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
//
//	 	The test cases are generated so that the answer will be less than or equal to 2 * 109.
//
//	 	 
//
//	 	Example 1:
//
//
//	 	Input: m = 3, n = 7
//	 	Output: 28
//	 	Example 2:
//
//	 	Input: m = 3, n = 2
//	 	Output: 3
//	 	Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
//	 	1. Right -> Down -> Down
//	 	2. Down -> Down -> Right
//	 	3. Down -> Right -> Down
//	 	 
//
//	 	Constraints:
//
//	 	1 <= m, n <= 100
	 	  
	 	 public int uniquePaths(int m, int n) {
	         int [][]dp=new int[n][m];
	         for(int i=0;i<n;i++) {
	        	 for(int j=0;j<m;j++) {
	        		 if(i==0||j==0)
	        			 dp[i][j]=1;
	        		 else
	        			 dp[i][j]=dp[i][j-1]+dp[i-1][j];
	        	 }
	         }
	         return dp[n-1][m-1];
	     }
}

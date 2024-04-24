package backtracking;

import java.util.*;

public class Practice {
//	Given a non-negative integer n, find all n-letter words composed by 'a' and 'b', return them in a list of strings in lexicographical order.
//
//			Input: 2
//			Output: ["aa", "ab", "ba", "bb"]
//
//			Input: 4
//			Output: ["aaaa", "aaab", "aaba", "aabb", "abaa", "abab", "abba", "abbb", "baaa", "baab", "baba", "babb", "bbaa", "bbab", "bbba", "bbbb"]
	
	List<String> letterCombination(int n) {
		List<String> res = new ArrayList<String>();
		letterCombinationUtil(n, new char[] {'a', 'b'}, new StringBuilder(""), res);
		return res;
	}
	
	void letterCombinationUtil(int n,char[]chars,StringBuilder sb,List<String> res) {
		if(sb.length()==n) {
			res.add(sb.toString());
			return;
		}
		for(int i=0;i<chars.length;i++) {
			sb.append(chars[i]);
			letterCombinationUtil(n, chars, sb,res);
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	
//	Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
//
//			A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//			 
//
//			Example 1:
//
//			Input: digits = "23"
//			Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//			Example 2:
//
//			Input: digits = ""
//			Output: []
//			Example 3:
//
//			Input: digits = "2"
//			Output: ["a","b","c"]
	public Map<Character,List<String>>digitMap = Map.of(
				'2',List.of("a","b","c"),
				'3',List.of("d","e","f"),
				'4',List.of("g","h","i"),
				'5',List.of("j","k","l"),
				'6',List.of("m","n","o"),
				'7',List.of("p","q","r","s"),
				'8',List.of("t","u","v"),
				'9',List.of("w","x","y","z")
			);
	public List<String> letterCombinations(String digits) {
		if(digits.isBlank()||digits.isEmpty())
			return new ArrayList<String>();
        int n=digits.length();
        List<List<String>> inputList = new ArrayList<List<String>>();
        for(char c:digits.toCharArray())
        	inputList.add(digitMap.get(c));
        List<String> res = new ArrayList<String>();
        letterCombinationsUtil(n, digits, res, new StringBuilder(""), inputList,0);
        return res;
    }
	
	void letterCombinationsUtil(int n,String digits,List<String> res,StringBuilder sb,List<List<String>> inputList,int index) {
		if(sb.length()==n) {
			res.add(sb.toString());
			return;
		}
		List<String> ipList = inputList.get(index);
		for(int i=0;i<ipList.size();i++) {
			sb.append(ipList.get(i));
			letterCombinationsUtil(n, digits, res, sb, inputList, index+1);
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
//	Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
//
//			You may return the answer in any order.
//
//			 
//
//			Example 1:
//
//			Input: n = 4, k = 2
//			Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
//			Explanation: There are 4 choose 2 = 6 total combinations.
//			Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
//			Example 2:
//
//			Input: n = 1, k = 1
//			Output: [[1]]
//			Explanation: There is 1 choose 1 = 1 total combination.
//			 
//
//			Constraints:
//
//			1 <= n <= 20
//			1 <= k <= n
	
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		combineUtil(res, k, n, 0, new ArrayList<Integer>(), k);
		return res;
    }
	
	void combineUtil(List<List<Integer>> res,int k,int n,int curr,List<Integer> op,int count) {
		if(op.size()==k) {
			res.add(new ArrayList<Integer>(op));
			// this is important.we can not do res.add(op) because in that case when we remove item from op,as res had same reference,even res will change
		}
		else{
			for(int i=curr+1;i<=n;i++) {
				op.add(i);
				combineUtil(res, k, n, i, op, count);
				op.remove(op.size()-1);
			}
		}
	}
	
//	Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
//
//			 
//
//			Example 1:
//
//			Input: nums = [1,2,3]
//			Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//			Example 2:
//
//			Input: nums = [0,1]
//			Output: [[0,1],[1,0]]
//			Example 3:
//
//			Input: nums = [1]
//			Output: [[1]]
//			 
//
//			Constraints:
//
//			1 <= nums.length <= 6
//			-10 <= nums[i] <= 10
//			All the integers of nums are unique.
	
	
//	this solution also works.But its slower
//	public List<List<Integer>> permute(int[] nums) {
//		List<List<Integer>> res = new ArrayList<List<Integer>>();
//		permuteUtil(res, new HashSet<Integer>(),new ArrayList<Integer>(), nums, -1,0);
//		return res;
//    }
//	void permuteUtil(List<List<Integer>> res,Set<Integer> op,List<Integer> ls,int[] nums,int curr,int idx) {
//		if(op.size()==nums.length) {
//			res.add(new ArrayList<Integer>(ls));
//			return;
//		}
//		for(int i=0;i<nums.length;i++) {
//			if(!op.contains(nums[i])) {
//				op.add(nums[i]);
//				ls.add(nums[i]);
//				permuteUtil(res, op,ls, nums, nums[i],i);
//				ls.remove(ls.size()-1);
//				op.remove(nums[i]);
//			}
//		}
	//https://leetcode.com/problems/permutations/solutions/3851089/java-1ms-solution/?envType=study-plan-v2&envId=top-interview-150
	
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		find(nums, nums.length, 0, res);
		return res;
    }
	
	void find(int[] arr, int n, int ind, List<List<Integer>> ans){
		if(ind==n) {
			List<Integer> ds = new ArrayList<Integer>();
			for(int i:arr)
				ds.add(i);
			ans.add(ds);
			return;
		}
		for(int i=ind;i<n;i++) {
			swap(arr, i, ind);
			find(arr, n, ind+1, ans);
			swap(arr, ind, i);
		}
	}
	
	void swap(int []arr,int source,int dest) {
		if(source!=dest) {
			int t=arr[source];
			arr[source]=arr[dest];
			arr[dest]=t;
		}
	}
	
//	Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target.
//	You may return the combinations in any order.
//
//			The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the 
//			frequency
//			 of at least one of the chosen numbers is different.
//
//			The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
//
//			 
//
//			Example 1:
//
//			Input: candidates = [2,3,6,7], target = 7
//			Output: [[2,2,3],[7]]
//			Explanation:
//			2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
//			7 is a candidate, and 7 = 7.
//			These are the only two combinations.
//			Example 2:
//
//			Input: candidates = [2,3,5], target = 8
//			Output: [[2,2,2,2],[2,3,3],[3,5]]
//			Example 3:
//
//			Input: candidates = [2], target = 1
//			Output: []
//			 
//
//			Constraints:
//
//			1 <= candidates.length <= 30
//			2 <= candidates[i] <= 40
//			All elements of candidates are distinct.
//			1 <= target <= 40
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		combinationSumUtil(res, candidates, target, 0, new ArrayList<Integer>(),0);
		return res;
    }
	
	void combinationSumUtil(List<List<Integer>> res,int[] candidates, int target,int currSum,List<Integer> op,int start) {
		if(currSum==target) {
			res.add(new ArrayList<Integer>(op));
			return;
		}
		for(int i=start;i<candidates.length;i++) {
			int n = candidates[i];
			if(currSum+n<=target) {
				op.add(n);
				combinationSumUtil(res, candidates, target, currSum+n, op,i);
				op.remove(op.size()-1);
			}
		}
	}
//	Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//	 
//
//	Example 1:
//
//	Input: n = 3
//	Output: ["((()))","(()())","(())()","()(())","()()()"]
//	Example 2:
//
//	Input: n = 1
//	Output: ["()"]
//	 
//
//	Constraints:
//
//	1 <= n <= 8
	
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<String>();
		generateParenthesisUtil(res, "",0,0, n);
		return res;
    }
	
	void generateParenthesisUtil(List<String> res,String op,int open,int close,int n) {
		if(op.length()==n*2) {
			res.add(op);
			return ;
		}
		if(open<n)
			generateParenthesisUtil(res, op+"(", open+1, close, n);
		if(close<open)
			generateParenthesisUtil(res, op+")", open, close+1, n);
		
	}
	
//	Given an m x n grid of characters board and a string word, return true if word exists in the grid.
//
//			The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are 
//			horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//			 
//
//			Example 1:
//
//
//			Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
//			Output: true
//			Example 2:
//
//
//			Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
//			Output: true
//			Example 3:
//
//
//			Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
//			Output: false
			 
	public boolean exist(char[][] board, String word) {
		boolean[][] visited= new boolean[board.length][board[0].length];
        boolean find = false;
        for(int x=0;x<board.length;x++) {
        	for(int y=0;y<board[0].length;y++) {
        		//visited[x][y]=true;
        		find = existUtil(board.length, board[0].length, x, y, 0, board, word,visited);
        		if(find)
        			return find;
        	}
        }
        return find;
    }
	
	boolean existUtil(int m,int n,int x,int y,int i,char[][] board, String word,boolean[][] visited) {
		if(i==word.length())
			return true;
		if(y<0||y>=n||x<0||x>=m||visited[x][y])
			return false;

		if(board[x][y]!=word.charAt(i))
			return false;
		visited[x][y]=true;
		boolean found= existUtil(m, n, x+1, y, i+1, board, word,visited)||
				existUtil(m, n, x-1, y, i+1, board, word,visited)||
				existUtil(m, n, x, y+1, i+1, board, word,visited)||
				existUtil(m, n, x, y-1, i+1, board, word,visited);
		visited[x][y]=false;
		return found;
	}
	
//	The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
//
//	Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
//
//	Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
//
//	 
//
//	Example 1:
//
//
//	Input: n = 4
//	Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//	Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
//	Example 2:
//
//	Input: n = 1
//	Output: [["Q"]]
//	 
//
//	Constraints:
//
//	1 <= n <= 9
	//https://www.youtube.com/watch?v=wGbuCyNpxIg
    public List<List<String>> solveNQueens(int n) {
    	List<List<String>> res = new ArrayList<List<String>>();
    	StringBuffer sb = new StringBuffer();
    	for(int j=0;j<n;j++) {
    		sb.append(".");
    	}
    	solveNQueensUtil(n, 0, res, new ArrayList<String>(), sb.toString(), new ArrayList<Integer>());
    	return res;
    }
    
    void solveNQueensUtil(int n,int row,List<List<String>> res,List<String>currPlaces,String currRow,List<Integer>colPlacements) {
    	if(row==n){
    		res.add(new ArrayList<String>(currPlaces));
    		return;
    	}
    	for(int column=0;column<n;column++) {
    		colPlacements.add(column);
			String newRow = currRow.substring(0,column)+"Q"+currRow.substring(column+1);
			currPlaces.add(newRow);
    		if(isValidQueenPlacement(colPlacements)) {
    			solveNQueensUtil(n, row+1, res, new ArrayList<String>(currPlaces),new String(currRow),colPlacements);
    		}
    		currPlaces.remove(currPlaces.size()-1);
    		colPlacements.remove(colPlacements.size()-1);
    	}
    }
    boolean isValidQueenPlacement(List<Integer> colPlacements) {
    	int rowId = colPlacements.size()-1;
    	for(int i=0;i<rowId;i++) {
    		int diff = Math.abs(colPlacements.get(i)-colPlacements.get(rowId));
    		if(diff==0 || // when queen is in the same column of another row
    				diff==(rowId-i)) //when queen is diagonal to another queen
    			return false;
    	}
    	return true;
    }
}

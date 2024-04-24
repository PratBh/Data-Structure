package matrix;
import java.util.*;

public class LeetCode {
//	Write a program to solve a Sudoku puzzle by filling the empty cells.
//
//	A sudoku solution must satisfy all of the following rules:
//
//	Each of the digits 1-9 must occur exactly once in each row.
//	Each of the digits 1-9 must occur exactly once in each column.
//	Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
//	The '.' character indicates empty cells.
//
//	 
//
//	Example 1:
//
//
//	Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
//	Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
//	Explanation: The input board is shown above and the only valid solution 
	
	//https://leetcode.com/problems/sudoku-solver/solutions/4034246/readable-solution-youtube-walkthrough/
	private int[][] result = new int[9][9];
    private boolean[][] modifiable = new boolean[9][9];

    public void solveSudoku(char[][] board) {
        markModifiable(board);
        recurse(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.toString(result[i][j]).charAt(0);
            }
        }
    }

    private void markModifiable(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                modifiable[i][j] = (board[i][j] == '.');
                if (!modifiable[i][j]) {
                    result[i][j] = Integer.parseInt(String.valueOf(board[i][j]));
                }
            }
        }
    }

    private boolean recurse(int idx) {
        if (idx == 81) {
            return true;
        }

        int row = idx / 9;
        int column = idx % 9;

        for (int candidate = 1; candidate < 10; candidate++) {
            if (modifiable[row][column]) {
                result[row][column] = candidate;
                if (isValid(row, column) && recurse(idx + 1)) {
                    return true;
                }
            } else {
                return recurse(idx + 1);
            }
        }
        result[row][column] = 0;
        return false;
    }

    private boolean isValid(int i, int j) {
        return isValidRow(i) && isValidColumn(j) && isValidBlock(i, j);
    }

    private boolean isValidRow(int i) {
        boolean[] seen = new boolean[10];
        for (int j = 0; j < 9; j++) {
            int value = result[i][j];
            if (seen[value] && value != 0) {
                return false;
            }
            seen[value] = true;
        }
        return true;
    }

    private boolean isValidColumn(int j) {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int value = result[i][j];
            if (seen[value] && value != 0) {
                return false;
            }
            seen[value] = true;
        }
        return true;
    }

    private boolean isValidBlock(int i, int j) {
        boolean[] seen = new boolean[10];
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
            for (int column = (j / 3) * 3; column < (j / 3) * 3 + 3; column++) {
                int value = result[row][column];
                if (seen[value] && value != 0) {
                    return false;
                }
                seen[value] = true;
           }
        }
        return true;
    }
    
//    An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
//
//    Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
//
//     
//
//    Example 1:
//
//
//    Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
//    Output: true
//    Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
//    Hence, we return true.
    
    public boolean checkValid(int[][] matrix) {
    	int n = matrix.length;
    	if(n==1) 
    		return matrix[0][0] == 1;

    	HashSet<Integer> set = new HashSet<Integer>();
    	for(int i=1;i<=n;i++)
    		set.add(i);
    	for(int i=0;i<n;i++) {
    		if(!isValidRow(i, n, set, matrix))
    			return false;
    	}
    	for(int i=0;i<n;i++) {
    		if(!isValidColumn(i, n, set, matrix))
    			return false;
    	}
    	return true;
        
    }
    
    boolean isValidRow(int i,int n,HashSet<Integer> hset,int[][] matrix) {
    	Set<Integer> set = (HashSet<Integer>) hset.clone();
    	for(int j=0;j<n;j++) {
    		if(set.contains(matrix[i][j]))
    			set.remove(matrix[i][j]);
    		else
    			return false;
    	}
    	return set.isEmpty();
    }
    
    boolean isValidColumn(int j,int n,HashSet<Integer> hset,int[][] matrix) {
    	Set<Integer> set = (HashSet<Integer>) hset.clone();
    	for(int i=0;i<n;i++) {
    		if(set.contains(matrix[i][j]))
    			set.remove(matrix[i][j]);
    		else
    			return false;
    	}
    	return set.isEmpty();
    }
    
//    Given an m x n matrix, return all elements of the matrix in spiral order.
//
//    		 
//
//    		Example 1:
//
//
//    		Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//    		Output: [1,2,3,6,9,8,7,4,5]
    //https://leetcode.com/problems/spiral-matrix/solutions/4055455/spiral-matrix-java-solution-beats-100/?envType=study-plan-v2&envId=top-interview-150
    
    public List<Integer> spiralOrder(int[][] matrix) {
		int n=matrix.length,m=matrix[0].length,round =0;
		List<Integer> res = new ArrayList<Integer>();
		int start_row=0,end_row=n-1,start_col=0,end_col=m-1;
			while(start_row<=end_row && start_col<=end_col) {
				//going right
				for(int j=start_col;j<=end_col;j++) {
					res.add(matrix[start_row][j]);
				}
				
				//going down
				for(int i=start_row+1;i<=end_row;i++) {
					res.add(matrix[i][end_col]);
				}
				
				
				//going left
                if (start_row < end_row) {
				for(int j=end_col-1;j>=start_col;j--) {
					res.add(matrix[end_row][j]);
				}
                }
				
				//going up
                if (start_col < end_col) {
				for(int i=end_row-1;i>=start_row+1;i--) {
					res.add(matrix[i][start_col]);
				}
                }
                start_col++;
				start_row++;
				end_col--;
				end_row--;
			}
			return res;
		}
    
}

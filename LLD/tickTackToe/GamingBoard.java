package LLD.tickTackToe;

import java.util.ArrayList;
import java.util.List;

public class GamingBoard {
	int size;
	PlayingPiece[][] board;
	
	public GamingBoard(int size) {
		this.size = size;
		this.board = new PlayingPiece[size][size];
	}
	
	public boolean addPiece(int row,int column,PlayingPiece piece) {
		if(board[row][column]!=null)
			return false;
		board[row][column]=piece;
		return true;
	}
	
	public List<int[]> getFreeCells(){
		List<int[]> freeCells = new ArrayList<int[]>();
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(board[i][j]==null) {
					int []cell = {i,j};
					freeCells.add(cell);
				}
			}
		}
		return freeCells;
	}
	
	public void printBoard() {
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                   System.out.print(board[i][j].type.name() + "   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();

        }

	}
}

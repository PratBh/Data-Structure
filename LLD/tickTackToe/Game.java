package LLD.tickTackToe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
	GamingBoard board;
	Deque<Player> players;
	public Game() {
		initializeGame();
	}
	
	public void initializeGame() {
		players = new LinkedList<Player>();
		PlayingPiece cross = new PlayingPieceX(PieceType.X);
		Player p1 = new Player("P1",cross);
		PlayingPiece nought = new PlayingPieceX(PieceType.O);
		Player p2 = new Player("P2",nought);
		players.add(p1);
		players.add(p2);
		board=new GamingBoard(3);
	}
	
	public String startGame() {
		boolean noWinner = true;
		while(noWinner) {
			Player playerTurn = players.removeFirst();
			board.printBoard();
			List<int[]> freeCells = board.getFreeCells();
			if(freeCells.isEmpty()) {
				noWinner = false;
				continue;
			}
			System.out.println("Player: "+playerTurn.name+" Enter position");
			Scanner inScanner = new Scanner(System.in);
			String s = inScanner.nextLine();
			String[] values = s.split(",");
			int row = Integer.valueOf(values[0]);
			int col = Integer.valueOf(values[1]);
			boolean playedSuccessfully = board.addPiece(row, col, playerTurn.piece);
			if(!playedSuccessfully) {
				players.addFirst(playerTurn);
				System.out.println("Wrong input,please try again..");
				continue;
			}
			players.addLast(playerTurn);
			boolean winner = isThereWinner(row, col, playerTurn.piece);
			if(winner)
				return playerTurn.name;
		}
		return "Tie";
	}
	
	public boolean isThereWinner(int row,int col,PlayingPiece piece) {
		boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;
        
        for(int i=0;i<board.size;i++) {
        	if(board.board[row][i]==null || board.board[row][i].type != piece.type )
        		rowMatch = false;
        }
        
        for(int i=0;i<board.size;i++) {
        	if(board.board[i][col]==null || board.board[i][col].type != piece.type )
        		columnMatch = false;
        }
        
        for(int i=0,j=0;i<board.size;i++,j++) {
        	if(board.board[i][j]==null || board.board[i][j].type != piece.type )
        		diagonalMatch = false;
        }
        
        for(int i=0,j=board.size-1;i<board.size;i++,j--) {
        	if(board.board[i][j]==null || board.board[i][j].type != piece.type )
        		antiDiagonalMatch = false;
        }
        
        return rowMatch||columnMatch||diagonalMatch||antiDiagonalMatch;
	}
}

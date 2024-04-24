package LLD.snackAndLadder;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
	Board board;
	Dice dice;
	Deque<Player> players=new LinkedList<Player>();
	Player winner;
	
	public Game() {
		initializeGame();
	}
	
	private void initializeGame() {
		board = new Board(10, 5, 5);
		dice = new Dice(1);
		winner=null;
		addPlayers();
	}
	
	private void addPlayers() {
		Player p1 = new Player("p1", 0);
		Player p2 = new Player("p2", 0);
		players.add(p1);
		players.add(p2);
	}
	
	private Player findPlayer() {
		Player pl = players.removeFirst();
		players.addLast(pl);
		return pl;
	}
	private int jumpCheck(int position) {
		if(position>board.cells.length*board.cells.length-1)
			return position;
		Cell cell = board.getCell(position);
		if(cell.jump!=null && cell.jump.start==position) {
			String jumpBy = (cell.jump.start>cell.jump.end)?"Snake":"Ladder";
			System.out.println("Jump done by: "+jumpBy);
			return cell.jump.end;
		}
		return position;
	}
	
	public void startGame() {
		while(winner==null) {
			Player turn = findPlayer();
			System.out.println(turn.id+" playing ");
			int diceNumber = dice.rollDice();
			int newPosition = turn.currentPosition+diceNumber;
			newPosition = jumpCheck(newPosition);
			turn.currentPosition=newPosition;
			if(newPosition>=board.cells.length*board.cells.length-1)
				winner=turn;
			
		}
		System.out.println("Winner: "+winner.id);
	}
}

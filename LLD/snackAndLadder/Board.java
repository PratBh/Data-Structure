package LLD.snackAndLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
	Cell[][] cells;
	public Board(int boardSize,int numberOfSnake,int numberOfLadder) {
		initializeCells(boardSize);
		addSnakesLadders(cells, numberOfSnake, numberOfLadder);
	}
	
	private void initializeCells(int boardSize) {
		cells = new Cell[boardSize][boardSize];
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				Cell obj = new Cell();
				cells[i][j]=obj;
			}
		}
	}
	
	private void addSnakesLadders(Cell[][] cells,int numberOfSnake,int numberOfLadder) {
		while(numberOfSnake>0){
			int snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
			int snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
			if(snakeTail>=snakeHead)
				continue;
			Jump snake = new Jump();
			snake.start=snakeHead;
			snake.end=snakeTail;
			Cell cell = getCell(snakeHead);
			cell.jump=snake;
			
			numberOfSnake--;
		}
		
		while(numberOfLadder>0){
			int snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
			int snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
			if(snakeTail<=snakeHead)
				continue;
			Jump snake = new Jump();
			snake.start=snakeHead;
			snake.end=snakeTail;
			Cell cell = getCell(snakeHead);
			cell.jump=snake;
			
			numberOfLadder--;
		}
	}
	
	Cell getCell(int playerPosition) {
		int row = playerPosition/cells.length;
		int col=playerPosition%cells.length;
		return cells[row][col];
	}
}

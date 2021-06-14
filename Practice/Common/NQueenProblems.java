

public class NQueenProblem {
	class Position{
		int row,col;
		public Position(int row,int col) {
			this.col=col;
			this.row=row;
		}
	}
	
	public Position[] solvedNQueen(int n) {
		Position[] positions = new Position[n];
		boolean hasSolved = solvedNQueenUtil(n,0,positions);
		if(hasSolved)
			return positions;
		else 
			return new Position[0];
	}
	
	public boolean solvedNQueenUtil(int n,int row,Position[] positions) {
		if(n==row)
			return true;
		int col;
		for(col=0;col<n;col++) {
			boolean foundSafe = true;
			for(int queen = 0;queen<n;queen++) {
				if( positions[queen].col==col || positions[queen].row-positions[queen].col==row-col||
						positions[queen].row+positions[queen].col==row+col) {
					foundSafe=false;
					break;
				}
			}
			
			if(foundSafe) {
				Position pos = new Position(row, col);
				positions[row]=pos;
				if(solvedNQueenUtil(n, row+1, positions))
					return true;
			}
		}
		return false;
	}
	

}


package sudoku.database;

import sudoku.board.IBoard;

public interface IBoardDatabase 
{
	boolean connect();
	
	IBoard fetchRandomStartingBoard();
        
        boolean saveStartingBoard(IBoard board);
}
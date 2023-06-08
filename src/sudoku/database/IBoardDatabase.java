package sudoku.database;

import sudoku.board.Board;

public interface IBoardDatabase 
{
	boolean connect();
	
	Board fetchRandomStartingBoard();
}
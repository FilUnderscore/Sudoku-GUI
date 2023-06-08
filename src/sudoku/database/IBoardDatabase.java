package sudoku.database;

import sudoku.board.Board;

public interface IBoardDatabase 
{
	boolean connect();
	
	Board fetchRandomStartingBoard();
        
        void saveStartingBoard(int board_length, int[][] board_indices);
}
package sudoku;

import sudoku.board.DefaultBoard;
import sudoku.database.IBoardDatabase;
import sudoku.database.SudokuDerbyBoardDatabase;
import sudoku.gui.start.SudokuStartController;
import sudoku.gui.start.SudokuStartView;

public final class SudokuStart 
{
	public int difficulty;
	
	public static void open()
	{
		SudokuStart start = new SudokuStart();
		SudokuStartView view = new SudokuStartView(start);
		new SudokuStartController(start, view);
	}
	
	public void start()
	{
		IBoardDatabase sudokuBoardDatabase = new SudokuDerbyBoardDatabase();
		Sudoku sudoku = new Sudoku(sudokuBoardDatabase);
		sudoku.start(difficulty);
	}
}
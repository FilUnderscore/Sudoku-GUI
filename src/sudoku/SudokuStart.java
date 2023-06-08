package sudoku;

import sudoku.board.DefaultBoard;
import sudoku.database.IBoardDatabase;
import sudoku.database.SudokuDerbyBoardDatabase;
import sudoku.gui.start.SudokuStartController;
import sudoku.gui.start.SudokuStartView;

public final class SudokuStart 
{
        private static IBoardDatabase sudokuBoardDatabase = new SudokuDerbyBoardDatabase();
	public int difficulty;
	
	public static void open()
	{
		SudokuStart start = new SudokuStart();
		SudokuStartView view = new SudokuStartView(start);
		new SudokuStartController(start, view);
	}
	
	public void start()
	{
		SudokuGame sudoku = new SudokuGame(sudokuBoardDatabase);
		sudoku.start(difficulty);
	}
        
        public void create()
        {
                SudokuCreate sudoku = new SudokuCreate(sudokuBoardDatabase);
        }
}
package sudoku;

import sudoku.gui.start.SudokuStartController;
import sudoku.gui.start.SudokuStartView;

// Sudoku - ENSE600 Assignment 1
// Project 2 Group 40
// Filip Jerkovic - 20122575
public final class Main
{
	public static void main(String[] args)
	{
		SudokuStart start = new SudokuStart();
		SudokuStartView view = new SudokuStartView(start);
		new SudokuStartController(start, view);
		
		//IBoardDatabase sudokuBoardDatabase = new SudokuDerbyBoardDatabase();
		//Sudoku sudoku = new Sudoku(sudokuBoardDatabase);
	}
}
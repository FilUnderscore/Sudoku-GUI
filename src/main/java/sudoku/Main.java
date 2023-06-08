package sudoku;

import sudoku.database.IBoardDatabase;
import sudoku.database.SudokuDerbyBoardDatabase;

// Sudoku - ENSE600 Assignment 1
// Project 2 Group 40
// Filip Jerkovic - 20122575
public final class Main
{
	public static void main(String[] args)
	{
		IBoardDatabase sudokuBoardDatabase = new SudokuDerbyBoardDatabase();
		Sudoku sudoku = new Sudoku(sudokuBoardDatabase);
	}
}
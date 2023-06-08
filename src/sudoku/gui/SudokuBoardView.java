package sudoku.gui;

import javax.swing.JFrame;

public class SudokuBoardView extends JFrame
{
	private SudokuBoardModel model;
	private SudokuBoardController controller;
	
	public SudokuBoardView(SudokuBoardModel model, SudokuBoardController controller)
	{
		this.model = model;
		this.controller = controller;
	}
}
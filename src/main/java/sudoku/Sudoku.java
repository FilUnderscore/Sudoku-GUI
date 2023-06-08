package sudoku;

import java.util.Random;

import sudoku.board.Board;
import sudoku.board.BoardValue;
import sudoku.gui.SudokuBoardController;
import sudoku.gui.SudokuBoardView;

public final class Sudoku 
{
	// Randomly generated using https://www.sudokuweb.org/
	private static final int[][] STARTING_BOARD = new int[][]
	{
		{1, 9, 0, 0, 0, 0, 0, 3, 0},
		{0, 8, 7, 3, 2, 1, 0, 9, 5},
		{4, 3, 0, 8, 0, 0, 0, 0, 2},
		{3, 1, 4, 5, 6, 0, 2, 0, 9},
		{0, 0, 8, 9, 4, 3, 7, 6, 0},
		{7, 0, 0, 1, 0, 2, 5, 4, 0},
		{0, 0, 0, 0, 0, 0, 8, 2, 7},
		{9, 0, 0, 0, 0, 8, 0, 0, 4},
		{0, 0, 0, 7, 1, 4, 9, 5, 0}
	};
			
	private static final Random RANDOM = new Random();

	private SudokuBoardView view;
	private SudokuBoardController controller;
	
	private Board board;
	
	public Sudoku()
	{
	}
	
	public void start(int boardLength, int difficultySwaps)
	{
		this.generate(boardLength, difficultySwaps);
	}
	
	private void initializeView()
	{
		// Cleanup previous view.
		if(this.view != null)
			this.view.dispose();
		
		this.view = new SudokuBoardView(this.board);
		this.controller = new SudokuBoardController(this.board, this.view);
	}
	
	private void generate(int boardLength, int difficultySwaps)
	{
		this.board = new Board(boardLength);
		
		for(int x = 0; x < boardLength; x++)
		{
			for(int y = 0; y < boardLength; y++)
			{
				int value = STARTING_BOARD[x][y];
				this.board.set(x, y, new BoardValue(value, value > 0));
			}
		}
		
		// Swap random regions (column-to-column).
		for(int n = 0; n < difficultySwaps; n++)
		{
			int randomRegionX = RANDOM.nextInt(boardLength / 3);
			int randomRegionY = RANDOM.nextInt(boardLength / 3);
			
			int randomTargetRegionX = RANDOM.nextInt(boardLength / 3);
			int randomTargetRegionY = randomRegionY;
			
			this.board.swap(randomRegionX, randomRegionY, randomTargetRegionX, randomTargetRegionY);
		}
		
		this.initializeView();
	}
}
package sudoku;

import java.util.Random;

import sudoku.board.Board;
import sudoku.board.DefaultBoard;
import sudoku.board.IBoard;
import sudoku.database.IBoardDatabase;
import sudoku.gui.game.SudokuBoardController;
import sudoku.gui.game.SudokuBoardView;

public final class Sudoku 
{
	private static final Random RANDOM = new Random();
	private final IBoardDatabase boardDatabase;

	private SudokuBoardView view;
	private SudokuBoardController controller;
	
	private IBoard board;
	
	public Sudoku(IBoardDatabase boardDatabase)
	{
		this.boardDatabase = boardDatabase;
	}
	
	public void start(int difficultySwaps)
	{
		Board board = null;
		
		if(this.boardDatabase != null && this.boardDatabase.connect())
			board = this.boardDatabase.fetchRandomStartingBoard();
		
                if(board == null) // Database may have failed to initialize or is empty.
			board = new DefaultBoard();
		
		this.start(board, difficultySwaps);
	}
	
	public void start(Board board, int difficultySwaps)
	{
		this.board = board;
	
		board.generate();
		shuffleBoard(board, difficultySwaps);
		
		this.initializeView();
	}
	
	private static void shuffleBoard(Board board, int difficultySwaps)
	{
		// Swap random regions (column-to-column).
		for(int n = 0; n < difficultySwaps; n++)
		{
			int randomRegionX = RANDOM.nextInt(board.getLength() / 3);
			int randomRegionY = RANDOM.nextInt(board.getLength() / 3);
		
			int randomTargetRegionX = RANDOM.nextInt(board.getLength() / 3);
			int randomTargetRegionY = randomRegionY;
		
			board.swap(randomRegionX, randomRegionY, randomTargetRegionX, randomTargetRegionY);
		}
	}
	
	private void initializeView()
	{
		// Cleanup previous view.
		if(this.view != null)
			this.view.dispose();
		
		this.view = new SudokuBoardView(this.board);
		this.controller = new SudokuBoardController(this.board, this.view);
	}
}
package sudoku;

import java.util.Random;

import sudoku.board.Board;
import sudoku.board.DefaultBoard;
import sudoku.board.IBoard;
import sudoku.database.IBoardDatabase;
import sudoku.gui.game.play.SudokuGameController;
import sudoku.gui.game.play.SudokuGameView;

public final class SudokuGame 
{
	private static final Random RANDOM = new Random();
	private final IBoardDatabase boardDatabase;

	private SudokuGameView view;
	private SudokuGameController controller;
	
	private IBoard board;
	
	public SudokuGame(IBoardDatabase boardDatabase)
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
		this.view = new SudokuGameView(this.board);
		this.controller = new SudokuGameController(this.board, this.view.getBoardPanel(), this.view);
	}
}
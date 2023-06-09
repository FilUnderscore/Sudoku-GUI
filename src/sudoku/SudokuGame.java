package sudoku;

import java.util.ArrayList;
import java.util.Random;

import sudoku.board.Board;
import sudoku.board.BoardValue;
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
			board = (Board) this.boardDatabase.fetchRandomStartingBoard();
		
                if(board == null) // Database may have failed to initialize or is empty.
			board = new DefaultBoard();
		
		this.start(board, difficultySwaps);
	}
	
	public void start(Board board, int difficulty)
	{
		this.board = board;
	
		board.generate();
		shuffleBoard(board, difficulty);
		
		this.initializeView();
	}
	
	private static void shuffleBoard(Board board, int difficulty)
	{
		// Swap random regions (column-to-column).
		for(int n = 0; n < difficulty; n++)
		{
			int randomRegionX = RANDOM.nextInt(board.getLength() / 3);
			int randomRegionY = RANDOM.nextInt(board.getLength() / 3);
		
			int randomTargetRegionX = RANDOM.nextInt(board.getLength() / 3);
			int randomTargetRegionY = randomRegionY;
		
			board.swap(randomRegionX, randomRegionY, randomTargetRegionX, randomTargetRegionY);
		}
                
                ArrayList<BoardHint> hints = new ArrayList<>();
                
                // Detect hints.
                for(int x = 0; x < board.getLength(); x++)
                {
                    for(int y = 0; y < board.getLength(); y++)
                    {
                        if(board.get(x, y).isGenerated())
                            hints.add(new BoardHint(x, y));
                    }
                }
                
                // Remove hints.
                int removed = 0;
                while(!hints.isEmpty() && removed++ < difficulty)
                {
                    int index = RANDOM.nextInt(hints.size());
                    BoardHint hint = hints.get(index);
                    
                    board.set(hint.x, hint.y, new BoardValue(0, false));
                    hints.remove(index);
                }
	}
        
        private static final class BoardHint
        {
            public int x, y;
            
            public BoardHint(int x, int y)
            {
                this.x = x;
                this.y = y;
            }
        }
	
	private void initializeView()
	{
		this.view = new SudokuGameView(this.board);
		this.controller = new SudokuGameController(this.board, this.view.getBoardPanel(), this.view);
	}
}
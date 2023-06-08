package sudoku;

import java.util.Random;

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

	private final int boardLength;
	private final Board board;
	
	private int currentIndex;
	
	public Sudoku(int n)
	{
		this.boardLength = n;
		this.board = new Board(this.boardLength);
	}
	
	public void start(int difficultySwaps)
	{
		this.generate(0);
	}
	
	private void generate(int difficultySwaps)
	{
		for(int x = 0; x < this.boardLength; x++)
		{
			for(int y = 0; y < this.boardLength; y++)
			{
				int value = STARTING_BOARD[x][y];
				this.board.set(x, y, value, value > 0);
			}
		}
		
		// Swap random regions (column-to-column).
		for(int n = 0; n < difficultySwaps; n++)
		{
			int randomRegionX = RANDOM.nextInt(this.boardLength / 3);
			int randomRegionY = RANDOM.nextInt(this.boardLength / 3);
			
			int randomTargetRegionX = RANDOM.nextInt(this.boardLength / 3);
			int randomTargetRegionY = randomRegionY;
			
			this.board.swap(randomRegionX, randomRegionY, randomTargetRegionX, randomTargetRegionY);
		}
	}
	
	public InputState enter(int value)
	{
		if(value <= 0 || value >= 10)
			return InputState.INVALID;
		
		IndexedPosition indexPos = new IndexedPosition(currentIndex, this.boardLength);
		this.board.set(indexPos.getX(), indexPos.getY(), value, false);
		
		return ++currentIndex < this.getTotalBoardSize() ? InputState.CONTINUE : InputState.FULL;
	}
	
	private int getTotalBoardSize()
	{
		return this.boardLength * this.boardLength;
	}
	
	public String print()
	{
		int length = this.boardLength;
		int currentX;
		int currentY;

		do
		{
			if(currentIndex >= (length * length))
				currentIndex = 0;
			
			currentX = currentIndex % length;
			currentY = currentIndex / length;
			
			if(this.board.isGenerated(currentX, currentY))
				currentIndex++;
			else
				break;
		} while(true);

		return this.board.print(currentX, currentY);
	}
	
	public boolean isSolved()
	{
		return this.board.check();
	}
}
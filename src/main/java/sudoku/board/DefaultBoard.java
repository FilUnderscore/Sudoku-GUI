package sudoku.board;

public class DefaultBoard extends Board
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
		
	public DefaultBoard()
	{
		super(STARTING_BOARD.length);
	}

	@Override
	protected int getGeneratedValueAt(int x, int y) 
	{
		return STARTING_BOARD[x][y];
	}
}
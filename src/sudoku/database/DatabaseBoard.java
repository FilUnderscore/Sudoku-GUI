package sudoku.database;

import sudoku.board.Board;

public final class DatabaseBoard extends Board
{
	private final int[][] indices;
	
	public DatabaseBoard(int n, int[][] indices)
	{
		super(n);
		this.indices = indices;
	}

	@Override
	protected int getGeneratedValueAt(int x, int y) 
	{
            return this.indices[x][y];
	}
}

package sudoku.board;



import org.junit.Assert;
import org.junit.Test;

public class BoardTest 
{
	@Test
	public void boardSetSwapTest()
	{
		Board board = new DefaultBoard();
		board.set(0, 2, new BoardValue(9, false));
		
		Assert.assertEquals("Expected board value to return 9.", 9, board.get(0, 2).getValue());
		
		board.set(3, 1, new BoardValue(4, false));
		
		board.swap(0, 0, 1, 0);
		
		Assert.assertNotEquals("Expected value to not return 4.", 4, board.get(0, 2).getValue());
		Assert.assertNotEquals("Expected value to not return 9.", 9, board.get(3, 1).getValue());
		
		Assert.assertEquals("Expected swapped board value to return 4.", 4, board.get(0, 1).getValue());
		Assert.assertEquals("Expected swapped board value to return 9.", 9, board.get(3, 2).getValue());
	}
	
	@Test
	public void boardCheckTest()
	{
		IBoard board = new DefaultBoard();
		
		// Incomplete STARTING_BOARD.
		int[][] STARTING_BOARD = new int[][]
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
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				board.set(i, j, new BoardValue(STARTING_BOARD[i][j], true));
			}
		}
		
		Assert.assertFalse("Expected board check to fail.", board.check());
		
		// Randomly filled-in STARTING_BOARD.
		STARTING_BOARD = new int[][]
		{
			{1, 9, 3, 2, 1, 3, 2, 3, 5},
			{6, 8, 7, 3, 2, 1, 3, 9, 5},
			{4, 3, 3, 8, 9, 5, 4, 6, 2},
			{3, 1, 4, 5, 6, 6, 2, 7, 9},
			{4, 5, 8, 9, 4, 3, 7, 6, 4},
			{7, 1, 5, 1, 4, 2, 5, 4, 5},
			{6, 3, 6, 2, 3, 6, 8, 2, 7},
			{9, 2, 5, 3, 2, 8, 3, 4, 4},
			{5, 4, 4, 7, 1, 4, 9, 5, 9}
		};
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				board.set(i, j, new BoardValue(STARTING_BOARD[i][j], true));
			}
		}
		
		Assert.assertFalse("Expected board check to fail.", board.check());
		
		// Fully solved STARTING_BOARD.
		STARTING_BOARD = new int[][]
		{
			{1, 9, 2, 4, 7, 5, 6, 3, 8},
			{6, 8, 7, 3, 2, 1, 4, 9, 5},
			{4, 3, 5, 8, 9, 6, 1, 7, 2},
			{3, 1, 4, 5, 6, 7, 2, 8, 9},
			{2, 5, 8, 9, 4, 3, 7, 6, 1},
			{7, 6, 9, 1, 8, 2, 5, 4, 3},
			{5, 4, 1, 6, 3, 9, 8, 2, 7},
			{9, 7, 6, 2, 5, 8, 3, 1, 4},
			{8, 2, 3, 7, 1, 4, 9, 5, 6}
		};
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				board.set(i, j, new BoardValue(STARTING_BOARD[i][j], true));
			}
		}
		
		Assert.assertTrue("Expected board check to pass.", board.check());
	}
}

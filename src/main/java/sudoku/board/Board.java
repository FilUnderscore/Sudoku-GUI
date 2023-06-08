package sudoku.board;

import java.util.HashSet;
import java.util.Set;

public final class Board implements IBoard
{
	private final int length;
	private final IBoard[][] values;
	
	public Board(int n)
	{
		this.length = n;
		this.values = new BoardRegion[n/3][n/3];
		
		// Create empty regions.
		for(int i = 0; i < n/3; i++)
		{
			for(int j = 0; j < n/3; j++)
			{
				this.values[i][j] = new BoardRegion();
			}
		}
	}
	
	@Override
	public void set(int x, int y, BoardValue value)
	{
		// Set value in region at position.
		this.getSubBoardAt(x, y).set(x%3, y%3, value);
	}
	
	public void swap(int rX, int rY, int tRX, int tRY)
	{
		// Swap two regions.
		IBoard r = this.values[rX][rY];
		IBoard tR = this.values[tRX][tRY];
		
		this.values[rX][rY] = tR;
		this.values[tRX][tRY] = r;
	}
	
	@Override
	public BoardValue get(int x, int y)
	{
		// Get value in region at position.
		return this.getSubBoardAt(x, y).get(x%3, y%3);
	}
	
	private IBoard getSubBoardAt(int x, int y)
	{
		// Get region at position.
		return this.values[x/3][y/3];
	}
	
	@Override
	public boolean check()
	{
		// Check if the board is solved.
		int[][] check = new int[this.length][this.length];
		
		for(int x = 0; x < this.length; x++)
		{
			for(int y = 0; y < this.length; y++)
			{
				check[x][y] = this.get(x, y).getValue();
			}
		}
		
		boolean valid = true;
		for(int x = 0; x < this.length; x++)
		{
			for(int y = 0; y < this.length; y++)
			{
				if(!this.check(check, x, y))
				{
					valid = false;
					break;
				}
			}
			
			if(!valid)
				break;
		}
		
		return valid;
	}
	
	private boolean check(int[][] solved, int x, int y)
	{
		int length = this.length;
		
		boolean valid = true;
		
		Set<Integer> set = new HashSet<>();
		
		// Check row.
		for(int rX = 0; rX < length; rX++)
		{
			int rN = solved[rX][y];
			
			if(set.contains(rN))
			{
				valid = false;
				break;
			}
			
			set.add(rN);
		}
		
		set.clear();
		
		if(!valid)
			return false;
		
		// Check column.
		for(int rY = 0; rY < length; rY++)
		{
			int rN = solved[x][rY];
			
			if(set.contains(rN))
			{
				valid = false;
				break;
			}
			
			set.add(rN);
		}
		
		set.clear();
		
		if(!valid)
			return false;
		
		// Check 3x3 where n is located at (x, y)
		// Should equal 1+2+...+9=45
		IBoard region = this.getSubBoardAt(x, y);
		return region.check();
	}
	
	public int getLength()
	{
		return this.length;
	}
}
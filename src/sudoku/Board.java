package sudoku;

import java.util.HashSet;
import java.util.Set;

public final class Board 
{
	private final int length;
	private final BoardRegion[][] values;
	
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
	
	public void set(int x, int y, int n, boolean generated)
	{
		// Set value in region at position.
		this.getRegion(x, y).set(x%3, y%3, n, generated);
	}
	
	public void swap(int rX, int rY, int tRX, int tRY)
	{
		// Swap two regions.
		BoardRegion r = this.values[rX][rY];
		BoardRegion tR = this.values[tRX][tRY];
		
		this.values[rX][rY] = tR;
		this.values[tRX][tRY] = r;
	}
	
	public int get(int x, int y)
	{
		// Get value in region at position.
		return this.getRegion(x, y).get(x%3, y%3).getValue();
	}
	
	public boolean isGenerated(int x, int y)
	{
		// Check if this value was pre-generated or user-inputted.
		return this.getRegion(x, y).get(x%3, y%3).isGenerated();
	}
	
	private BoardRegion getRegion(int x, int y)
	{
		// Get region at position.
		return this.values[x/3][y/3];
	}
	
	public boolean check()
	{
		// Check if the board is solved.
		int[][] check = new int[this.length][this.length];
		
		for(int x = 0; x < this.length; x++)
		{
			for(int y = 0; y < this.length; y++)
			{
				check[x][y] = this.get(x, y);
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
		BoardRegion region = this.getRegion(x, y);
		return region.validate();
	}
	
	public String print(int currentX, int currentY)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int y = 0; y < this.length; y++)
		{
			for(int x = 0; x < this.length; x++)
			{
				if(x == currentX && y == currentY)
				{
					sb.append("> " + this.get(x, y) + " < | ");
				}
				else
				{
					sb.append("  " + this.get(x, y) + "   | ");
				}
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public int getLength()
	{
		return this.length;
	}
}
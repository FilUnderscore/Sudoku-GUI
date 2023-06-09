package sudoku.board;

import java.util.HashSet;

/**
 * Representing a region of the main game board as a sub-board.
 */
public final class BoardRegion implements IBoard
{
	private static final int MAGIC_NUMBER = 45;
	private BoardValue[][] values;
	
	public BoardRegion()
	{
		this.values = new BoardValue[3][3];
		
		// Initialize values.
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
				this.values[i][j] = new BoardValue(0, false);
		}
	}
	
	@Override
	public void clear()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(!this.values[i][j].isGenerated())
				{
					// Set non-generated tiles back to zero.
					this.values[i][j] = new BoardValue(0, false);
				}
			}
		}
	}
	
	@Override
	public BoardValue get(int x, int y)
	{
		// Get value at position in array.
		return this.values[x][y];
	}
	
	@Override
	public void set(int x, int y, BoardValue value)
	{
		this.values[x][y] = value;
	}
	
	@Override
	public boolean check()
	{
            return solveCheck(false);
	}
        
        @Override
        public boolean isSolvable()
        {
           return solveCheck(true);
        }
        
        private boolean solveCheck(boolean lessThan)
        {
             // Check if all numbers summed in 3x3 region are equal to 45.
		
		int sum = 0;
		HashSet<Integer> unique = new HashSet<Integer>();
                boolean uniqueFlag = true;
                
		int length = this.values.length;
		for(int x = 0; x < length; x++)
		{
			for(int y = 0; y < length; y++)
			{
                            int value = this.get(x, y).getValue();

                            sum += this.get(x, y).getValue();

                            if(value > 0)
                            {
                                if(unique.contains(value))
                                {
                                    uniqueFlag = false;
                                    break;
                                }

                                unique.add(value);
                            }
			}
                        
                        if(!uniqueFlag)
                            break;
		}
		
		return uniqueFlag && (lessThan ? sum <= MAGIC_NUMBER : sum == MAGIC_NUMBER);
        }

	@Override
	public int getLength() 
	{
		return 3;
	}

	@Override
	public void swap(int x, int y, int tX, int tY) 
	{
		BoardValue tempValue = this.values[x][y];

		this.values[x][y] = this.values[tX][tY];
		this.values[tX][tY] = tempValue;
	}
}
package sudoku;

public final class BoardRegion 
{
	private static final int MAGIC_NUMBER = 45;
	private SudokuValue[][] values;
	
	public BoardRegion()
	{
		this.values = new SudokuValue[3][3];
		
		// Initialize values.
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
				this.values[i][j] = new SudokuValue(0, false);
		}
	}
	
	public SudokuValue get(int x, int y)
	{
		// Get value at position in array.
		return this.values[x][y];
	}
	
	public void set(int x, int y, int n, boolean generated)
	{
		// Set value at position.
		this.set(x, y, new SudokuValue(n, generated));
	}
	
	public void set(int x, int y, SudokuValue value)
	{
		this.values[x][y] = value;
	}
	
	public boolean validate()
	{
		// Check if all numbers summed in 3x3 region are equal to 45.
		
		int sum = 0;
		
		int length = this.values.length;
		for(int x = 0; x < length; x++)
		{
			for(int y = 0; y < length; y++)
			{
				sum += this.get(x, y).getValue();
			}
		}
		
		return sum == MAGIC_NUMBER;
	}
}
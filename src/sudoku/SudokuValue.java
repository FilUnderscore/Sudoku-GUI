package sudoku;

public final class SudokuValue 
{
	private final int value;
	private final boolean generated;
	
	public SudokuValue(int value, boolean generated)
	{
		this.value = value;
		this.generated = generated;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public boolean isGenerated()
	{
		return this.generated;
	}
}
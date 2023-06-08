package sudoku.board;

public final class BoardValue 
{
	private final int value;
	private final boolean generated;
	
	public BoardValue(int value, boolean generated)
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
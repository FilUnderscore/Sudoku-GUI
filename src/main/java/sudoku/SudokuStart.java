package sudoku;

public final class SudokuStart 
{
	public static enum State
	{
		NONE,
		PLAY,
		CREATE;
	}
	
	public State state;
	
	public SudokuStart()
	{
		this.state = State.NONE;
	}
}
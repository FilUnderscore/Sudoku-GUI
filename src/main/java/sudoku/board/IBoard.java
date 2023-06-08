package sudoku.board;

public interface IBoard 
{
	/**
	 * Set a value on the board.
	 */
	void set(int x, int y, BoardValue value);
	
	/**
	 * Get a value on the board.
	 */
	BoardValue get(int x, int y);
	
	/**
	 * Swap between two values on the board.
	 */
	void swap(int x, int y, int tX, int tY);
	
	/**
	 * Validate the board according to Sudoku rules.
	 */
	boolean check();
	
	/**
	 * Clear the board back to its initial state.
	 */
	void clear();
	
	/*
	 * Get the board's length.
	 */
	int getLength();
}
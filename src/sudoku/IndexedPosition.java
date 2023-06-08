package sudoku;

public final class IndexedPosition 
{
	private final int x;
	private final int y;
	private final int n;
	
	public IndexedPosition(int x, int y, int n)
	{
		this.x = x;
		this.y = y;
		this.n = n;
	}
	
	public IndexedPosition(int index, int n)
	{
		this.x = index % n;
		this.y = index / n;
		this.n = n;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getAsIndex()
	{
		return this.y * n + this.x;
	}
}
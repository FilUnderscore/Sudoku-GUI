package sudoku.board;



import org.junit.Assert;
import org.junit.Test;

public class BoardRegionTest 
{
	@Test
	public void boardRegionSetTest()
	{
		BoardRegion region = new BoardRegion();
		
		region.set(1, 2, new BoardValue(3, false));
		
		Assert.assertEquals("Expected region to have value of 3.", 3, region.get(1, 2).getValue());
		Assert.assertFalse("Expected region to not be generated.", region.get(1, 2).isGenerated());
	}
	
	@Test
	public void boardRegionCheckTest()
	{
		BoardRegion region = new BoardRegion();
		
		region.set(0, 0, new BoardValue(1, false));
		region.set(0, 1, new BoardValue(2, false));
		region.set(0, 2, new BoardValue(3, false));
		region.set(1, 0, new BoardValue(4, false));
		region.set(1, 1, new BoardValue(5, false));
		region.set(1, 2, new BoardValue(6, false));
		region.set(2, 0, new BoardValue(7, false));
		region.set(2, 1, new BoardValue(8, false));
		region.set(2, 2, new BoardValue(9, false));
		
		Assert.assertTrue("Expected region check to pass.", region.check());
		
		region.set(1, 1, new BoardValue(9, false));
		
		Assert.assertFalse("Expected region check to fail.", region.check());
	}
	
	@Test
	public void boardRegionResetTest()
	{
		BoardRegion region = new BoardRegion();
		
		region.set(0, 0, new BoardValue(4, false));
		region.set(1, 2, new BoardValue(1, true));
		region.set(2, 2, new BoardValue(8, false));
		
		region.clear();
		
		Assert.assertEquals("Expected value of 0.", 0, region.get(0, 0).getValue());
		Assert.assertEquals("Expected value of 1.", 1, region.get(1, 2).getValue());
		Assert.assertEquals("Expected value of 0.", 0, region.get(2, 2).getValue());
	}
}

package sudoku.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sudoku.board.IBoard;

public final class SudokuDerbyBoardDatabase implements IBoardDatabase
{
	private static final String URL = "jdbc:derby:SudokuDB_Ebd;create=true";
	private static final String USER_NAME = "sudoku";
	private static final String PASSWORD = "sudoku";
	
	private Connection connection;
	
	public SudokuDerbyBoardDatabase()
	{
	}
	
	@Override
	public boolean connect()
	{
		if(this.connection == null)
		{
			try
			{
				this.connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			}
			catch(SQLException e)
			{
				System.err.println("Failed to connect to database.");
			}
		}
		
		return this.connection != null;
	}
	
	@Override
	public IBoard fetchRandomStartingBoard() 
	{
		try
		{
			Statement statement = this.connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT board_length,board_indices FROM starting_boards ORDER BY RAND(); LIMIT 1");
			
			if(result.getFetchSize() == 0)
				return null;
			
			int boardLength = result.getInt("board_length");		
			int[] indices = (int[])result.getArray("board_indices").getArray();
			
			return new DatabaseBoard(boardLength, indices);
		}
		catch(SQLException e)
		{
			System.err.println("Failed to fetch random starting board from database.");
		}
		
		return null;
	}
}
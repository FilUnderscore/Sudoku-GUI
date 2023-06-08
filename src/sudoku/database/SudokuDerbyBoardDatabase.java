package sudoku.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sudoku.board.Board;

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
        public void saveStartingBoard(int board_length, int[][] board_indices)
        {
            try
            {
                try (Statement statement = this.connection.createStatement()) 
                {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS starting_boards (board_length INT, board_indices VARCHAR(1024));");
                    statement.executeUpdate("INSERT INTO starting_boards (board_length, board_indices) VALUES ('" + board_length + "','" + indices_to_string(board_length, board_indices) + "');");
                }
            }
            catch(SQLException e)
            {
                System.err.println("Failed to save starting board to database.");
            }
        }
        
        private static String indices_to_string(int board_length, int[][] board_indices)
        {
            String str = "";
            
            for(int x = 0; x < board_length; x++)
            {
                for(int y = 0; y < board_length; y++)
                {
                    str += Integer.toString(board_indices[x][y]) + ",";
                }
            }
            
            return str.substring(0, str.length() - 1);
        }
        
        private static int[][] string_to_indices(int board_length, String board_indices_str)
        {
            int[][] board_indices = new int[board_length][board_length];
            
            String[] board_indices_str_split = board_indices_str.split(",");
            
            for(int i = 0; i < board_indices_str_split.length; i++)
            {
                int value = Integer.parseInt(board_indices_str_split[i]);
                board_indices[i / board_length][i % board_length] = value;
            }
            
            return board_indices;
        }
        
	@Override
	public Board fetchRandomStartingBoard() 
	{
		try
		{
                    int boardLength;
                    int[][] indices;
                    
                    try (Statement statement = this.connection.createStatement()) 
                    {
                        ResultSet result = statement.executeQuery("SELECT board_length,board_indices FROM starting_boards ORDER BY RAND(); LIMIT 1");
                        if(result.getFetchSize() == 0)
                            return null;
                        boardLength = result.getInt("board_length");
                        indices = string_to_indices(boardLength, result.getString("board_indices"));
                    }
                        
                    return new DatabaseBoard(boardLength, indices);
		}
		catch(SQLException e)
		{
			System.err.println("Failed to fetch random starting board from database.");
		}
		
		return null;
	}
}
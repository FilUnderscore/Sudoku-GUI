package sudoku.database;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Random;

import sudoku.board.Board;
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
        public boolean saveStartingBoard(IBoard board)
        {
            int board_length = board.getLength();
            int[][] board_indices = new int[board_length][board_length];
            
            for(int x = 0; x < board_length; x++)
            {
                for(int y = 0; y < board_length; y++)
                {
                    board_indices[x][y] = board.get(x, y).getValue();
                }
            }
            
            boolean success = false;
            
            try
            {
                try (Statement statement = this.connection.createStatement()) 
                {
                     DatabaseMetaData databaseMetadata = this.connection.getMetaData();
                     ResultSet resultSet = databaseMetadata.getTables(null, null, "STARTING_BOARDS", new String[] { "TABLE" });
                    
                     if(!resultSet.next()) // Create table if not exists.
                        statement.executeUpdate("CREATE TABLE STARTING_BOARDS (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1), board_length INT NOT NULL, board_indices VARCHAR(255) NOT NULL)");
                     
                    success = statement.executeUpdate("INSERT INTO STARTING_BOARDS (board_length, board_indices) VALUES (" + board_length + ",'" + indices_to_string(board_length, board_indices) + "')") == 1;
                }
            }
            catch(SQLException e)
            {                
                System.err.println("Failed to save starting board to database.");
            }
            
            return success;
        }
        
        private static String indices_to_string(int board_length, int[][] board_indices)
        {
            String str = "";
            
            for(int x = 0; x < board_length; x++)
            {
                for(int y = 0; y < board_length; y++)
                {
                    str += Integer.toString(board_indices[x][y]);
                }
            }
            
            System.out.println(str);
            
            return str;
        }
        
        private static int[][] string_to_indices(int board_length, String board_indices_str)
        {
            int[][] board_indices = new int[board_length][board_length];
            
            for(int i = 0; i < board_indices_str.length(); i++)
            {
                int value = Integer.parseInt(Character.toString(board_indices_str.charAt(i)));
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
                    BoardEntry entry = null;
                    
                    try (Statement statement = this.connection.createStatement()) 
                    {
                        ResultSet result = statement.executeQuery("SELECT board_length, board_indices FROM STARTING_BOARDS");
                        if(result.getFetchSize() == 0)
                            return null;
                        
                        ArrayList<BoardEntry> entries = new ArrayList<BoardEntry>(); // Store all entries so we can randomize later on.
                        while(result.next())
                        {
                            boardLength = result.getInt("board_length");                            
                            indices = string_to_indices(boardLength, result.getString("board_indices"));   
                            
                            entry = new BoardEntry(boardLength, indices);
                            entries.add(entry);
                        }
                        
                        Random random = new Random();
                        entry = entries.get(random.nextInt(entries.size()));
                    }
                        
                    if(entry == null)
                        return null;
                    
                    return new DatabaseBoard(entry.board_length, entry.board_indices);
		}
		catch(SQLException e)
		{
			System.err.println("Failed to fetch random starting board from database.");
		}
		
		return null;
	}
        
        private final class BoardEntry
        {
            public int board_length;
            public int[][] board_indices;
            
            public BoardEntry(int board_length, int[][] board_indices)
            {
                this.board_length = board_length;
                this.board_indices = board_indices;
            }
        }
}
package sudoku;

import sudoku.gui.SudokuBoardController;
import sudoku.gui.SudokuBoardView;

public final class CUI extends Console
{
	private Sudoku sudoku;
	
	public CUI()
	{
		super(System.out, System.in);
	}
	
	public void start()
	{
		do
		{
			boolean quit = false;
			
			switch(this.readString("Welcome to Sudoku. Type 'i' for instructions, 's' to start, or 'e' to exit.").toLowerCase())
			{
			case "i":
				this.printInstructions();
				break;
			case "s":
				this.startGame();
				break;
			case "e":
				quit = true;
				break;
			default:
				this.write("Unrecognized command, please try again.");
				break;
			}
			
			if(quit)
				break;
		} while(true);
	}
	
	private void printInstructions()
	{
		this.write("Sudoku is a game where you must fill each 3x3 region with the numbers 1-9.");
		this.write("Each row/column on the game board cannot repeat the same numbers in that row/column.");
		this.write("And each 3x3 region cannot have repeat values of 1-9 within.");
		this.write("");
		this.write("Entering values will be done horizontally-first,");
		this.write("going to the next row after the final column of the previous row has been entered.");
		this.write("");
		this.write("If you do not manage to successfully solve the board on your first try, the game will");
		this.write("reset your position on the board, so you can continue updating your answers until you");
		this.write("get it right!");
		this.write("");
	}
	
	private void startGame()
	{
		this.sudoku = new Sudoku(9);
		this.sudoku.start(this.readInt("Please enter the difficulty you would like (number of swaps, e.g. 2): "));

		SudokuBoardView view = new SudokuBoardView(this.sudoku.getBoard());
		SudokuBoardController controller = new SudokuBoardController(this.sudoku.getBoard(), view);		
		
		boolean solved;
		
		// Game loop until solved.
		do
		{
			this.gameInput();
			
			solved = this.sudoku.isSolved();
			
			if(!solved)
			{
				this.write("Good try! Unfortunately the board remains unsolved.");
			}
		} while(!solved);
		
		if(solved)
		{
			this.write("Congratulations. You've solved the board!");
		}
	}
	
	private void gameInput()
	{
		// Game input loop.
		
		InputState state;
		while((state = this.sudoku.enter(this.readInt(this.sudoku.print()))) != null)
		{
			boolean success = false;
			
			switch(state)
			{
			case CONTINUE:
				break;
			case FULL:
				success = true;
				break;
			case INVALID:
				this.write("Invalid number entered. Please enter a number between 1 and 9.");
				break;
			}
			
			if(success)
				break;
		}
	}
}
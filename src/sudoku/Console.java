package sudoku;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class Console 
{
	private final PrintStream print;
	private final Scanner scanner;
	
	public Console(PrintStream print, InputStream in)
	{
		this.print = print;
		this.scanner = new Scanner(in);
	}
	
	public String readString(String message)
	{
		return this.readString(true, message);
	}
	
	public String readString(boolean prompt, String message)
	{
		if(prompt)
			this.write(message);

		return this.scanner.nextLine();
	}
	
	public String readString()
	{
		return this.readString(false, null);
	}
	
	public int readInt()
	{
		return this.readInt(false, null);
	}
	
	public int readInt(String message)
	{
		return this.readInt(true, message);
	}
	
	public int readInt(boolean prompt, String message)
	{
		do
		{			
			try
			{
				return Integer.parseInt(this.readString(prompt, message));
			}
			catch(NumberFormatException e)
			{
				this.write("Invalid number entered. Please try again.");
			}
		}
		while(true);
	}
	
	public void write(String str)
	{
		this.print.println(str);
	}
}
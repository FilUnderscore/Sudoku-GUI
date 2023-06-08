package sudoku.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public final class SudokuJTextField extends JTextField
{
	public SudokuJTextField()
	{
		super();
	}
	
	@Override
	protected Document createDefaultModel()
	{
		return new SudokuDocument();
	}
	
	private final class SudokuDocument extends PlainDocument
	{
		@Override
		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
		{
			if(str == null || !str.matches("[0-9]"))
				return;
			
			if((getLength() + str.length()) <= 1)
				super.insertString(offset, str, attr);
		}
	}
}
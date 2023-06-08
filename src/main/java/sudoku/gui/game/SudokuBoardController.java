package sudoku.gui.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import sudoku.SudokuStart;
import sudoku.board.BoardValue;
import sudoku.board.IBoard;

public final class SudokuBoardController 
{
	private IBoard model;
	private SudokuBoardView view;
	
	public SudokuBoardController(IBoard model, SudokuBoardView view)
	{
		this.model = model;
		this.view = view;

		view.getCheckButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SudokuBoardController.this.NotifyCheckButtonPressed(view.getTextFields());
			}
		});
		
		view.getClearButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				model.clear();
				view.update();
			}
		});
		
		view.getResetButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				view.dispose();
				SudokuStart.open();
			}
		});
		
		for(int y = 0; y < model.getLength(); y++)
		{
			for(int x = 0; x < model.getLength(); x++)
			{
				JTextField field = view.getTextFields()[x][y];
				
				if(!field.isEnabled())
					continue;

				// Initial detection of next box.
				JTextField nextField = null;
				int nextX = (x + 1) % model.getLength();
				int nextY = nextX == 0 ? y + 1 : y;
				
				if(nextY < model.getLength())
					nextField = view.getTextFields()[nextX][nextY];
				
				// Check if we still have a next box or we are at the end.
				boolean hasNext = nextField != null;
				
				// If we are not at the end, try find the next available box.
				while(nextY < model.getLength() && (nextField != null && !nextField.isEnabled()))
				{
					nextX = (nextX + 1) % model.getLength();
					nextY = nextX == 0 ? (nextY + 1) : nextY;
					nextField = view.getTextFields()[nextX][nextY];
				}
				
				// Register key listeners / set custom document for numeric-only input.
				field.addKeyListener(new TextFieldKeyListener(field, nextField, hasNext));	
				field.setDocument(new CustomDocument());
			}
		}
	}
	
	public void NotifyCheckButtonPressed(JTextField[][] textBoxes)
	{
		for(int y = 0; y < model.getLength(); y++)
		{
			for(int x = 0; x < model.getLength(); x++)
			{
				String text = textBoxes[y][x].getText();
				
				if(text.isBlank())
					continue;
				
				Integer value = Integer.valueOf(text);
				
				if(value == null)
					continue;
				
				this.model.set(x, y, new BoardValue(value.intValue(), false));
			}
		}
		
		boolean check = this.model.check();
		this.view.NotifyCheck(check);
	}
	
	private final class TextFieldKeyListener implements KeyListener
	{
		private final JTextField current;
		private final JTextField next;
		private final boolean hasNext;
		
		public TextFieldKeyListener(JTextField current, JTextField next, boolean hasNext)
		{
			this.current = current;
			this.next = next;
			this.hasNext = hasNext;
		}
		
		@Override
		public void keyTyped(KeyEvent e) 
		{
			if(this.hasNext) // Swap to the next text box that is enabled.
				this.next.requestFocusInWindow();
			else // We reached the end of the board, go back to window focus.
				this.current.getParent().requestFocus();
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{	
			// Limits boxes to only 1 character input.
			this.current.setText(null);
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
		}
	}
	
	private final class CustomDocument extends PlainDocument
	{
		public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException
		{
			if(text == null || !text.matches("[0-9]")) // Use regex to allow only numbers to be entered.
				return;
			
			super.insertString(offset, text, attr);
		}
	}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku.gui.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import sudoku.board.IBoard;

/**
 *
 * @author Filip
 */
public abstract class SudokuBoardController
{
    public SudokuBoardController(IBoard model, SudokuBoardPanel panel)
    {
        
		for(int y = 0; y < model.getLength(); y++)
		{
			for(int x = 0; x < model.getLength(); x++)
			{
				JTextField field = panel.getTextFields()[y][x];
				
				if(!field.isEnabled())
					continue;

				// Initial detection of next box.
				JTextField nextField = null;
				int nextX = (x + 1) % model.getLength();
				int nextY = nextX == 0 ? y + 1 : y;
				
				if(nextY < model.getLength())
					nextField = panel.getTextFields()[nextY][nextX];
				
				// Check if we still have a next box or we are at the end.
				boolean hasNext = nextField != null;
				
				// If we are not at the end, try find the next available box.
				while(nextY < model.getLength() && (nextField != null && !nextField.isEnabled()))
				{
					nextX = (nextX + 1) % model.getLength();
					nextY = nextX == 0 ? (nextY + 1) : nextY;
                                        
                                        if(nextY == model.getLength())
                                            break;
                                        
					nextField = panel.getTextFields()[nextY][nextX];
				}
				
				// Register key listeners / set custom document for numeric-only input.
				field.addKeyListener(new TextFieldKeyListener(field, nextField, hasNext));	
				field.setDocument(new CustomDocument());
			}
		}
    }
    
    protected final class TextFieldKeyListener implements KeyListener
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
	
	protected final class CustomDocument extends PlainDocument
	{

        public CustomDocument() {
        }
		public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException
		{
			if(text == null || !text.matches("[0-9]")) // Use regex to allow only numbers to be entered.
				return;
			
			super.insertString(offset, text, attr);
		}
	}
}

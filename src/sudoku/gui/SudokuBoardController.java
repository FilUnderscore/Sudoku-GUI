package sudoku.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import sudoku.Board;

public final class SudokuBoardController 
{
	private Board model;
	private SudokuBoardView view;
	
	public SudokuBoardController(Board model, SudokuBoardView view)
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
		
		view.getResetButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				view.ClearAllBoxes();
			}
		});
		
		for(int y = 0; y < model.getLength(); y++)
		{
			for(int x = 0; x < model.getLength(); x++)
			{
				JTextField field = view.getTextFields()[x][y];
				
				if(!field.isEnabled())
					continue;

				JTextField nextField = null;
				int nextX = (x + 1) % model.getLength();
				int nextY = nextX == 0 ? y + 1 : y;
				
				if(nextY < model.getLength() + 1)
				{
					if(nextY < model.getLength())
						nextField = view.getTextFields()[nextX][nextY];
					
					boolean hasNext = nextField != null;
					
					while(nextY < model.getLength() + 1 && (nextField != null && !nextField.isEnabled()))
					{
						if(nextY == model.getLength())
						{
							hasNext = false;
						}
						
						nextX = (nextX + 1) % model.getLength();
						nextY = nextX == 0 ? (nextY + 1) : nextY;
						nextField = view.getTextFields()[nextX][nextY];
					}
					
					field.addKeyListener(new TextFieldKeyListener(field, nextField, hasNext));	
				}
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
				
				this.model.set(x, y, value.intValue(), false);
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
			if(this.hasNext)
				this.next.requestFocusInWindow();
			else
				this.current.getParent().requestFocus();
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			
		}
	}
}
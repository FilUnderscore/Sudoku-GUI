package sudoku.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
package sudoku.gui.start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sudoku.SudokuStart;
import sudoku.SudokuStart.State;

public class SudokuStartController 
{
	private final SudokuStart model;
	
	public SudokuStartController(SudokuStart model, SudokuStartView view)
	{
		this.model = model;
		
		view.getPlayButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.state = State.PLAY;
				view.update();
			}	
		});
		
		view.getCreateButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				model.state = State.CREATE;
				view.update();
			}
		});
	}
}
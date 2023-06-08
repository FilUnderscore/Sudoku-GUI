package sudoku.gui.start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sudoku.SudokuStart;

public class SudokuStartController 
{
	private final SudokuStart model;
	
	public SudokuStartController(SudokuStart model, SudokuStartView view)
	{
		this.model = model;
		
                view.getCreateButton().addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        view.dispose();
                        model.create();
                    }
                });
                
		view.getPlayPanel().getIncButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.difficulty++;
				view.update();
			}
		});
		
		view.getPlayPanel().getDecButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(model.difficulty == 0)
					return;
				
				model.difficulty--;
				view.update();
			}
		});
		
		view.getPlayPanel().getZeroButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				model.difficulty = 0;
				view.update();
			}
		});
		
		view.getPlayPanel().getStartButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				view.dispose();
				model.start();
			}
		});
	}
}
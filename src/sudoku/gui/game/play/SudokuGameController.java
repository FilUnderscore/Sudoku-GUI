package sudoku.gui.game.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import sudoku.SudokuStart;
import sudoku.board.BoardValue;
import sudoku.board.IBoard;
import sudoku.gui.game.SudokuBoardController;
import sudoku.gui.game.SudokuBoardPanel;

public final class SudokuGameController extends SudokuBoardController
{
	private IBoard model;
	private SudokuGameView view;
	
	public SudokuGameController(IBoard model, SudokuBoardPanel panel, SudokuGameView view)
	{
            super(model, panel);
            
		this.model = model;
		this.view = view;

		view.getCheckButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SudokuGameController.this.NotifyCheckButtonPressed(view.getBoardPanel().getTextFields());
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
                
                view.getInstructionsButton().addActionListener(new ActionListener()
                {
                   @Override
                   public void actionPerformed(ActionEvent e)
                   {
                       SudokuInstructionsView instructionView = new SudokuInstructionsView();
                       new SudokuInstructionsController(instructionView);
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
				
                                if(!this.model.get(x, y).isGenerated())
                                    this.model.set(x, y, new BoardValue(value.intValue(), false));
			}
		}
		
		boolean check = this.model.check();
		this.view.NotifyCheck(check);
	}
	
	
}
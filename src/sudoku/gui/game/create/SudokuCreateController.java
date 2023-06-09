/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku.gui.game.create;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sudoku.SudokuCreate;
import sudoku.SudokuStart;
import sudoku.board.BoardValue;
import sudoku.board.IBoard;
import sudoku.gui.game.SudokuBoardController;

/**
 *
 * @author Filip
 */
public final class SudokuCreateController extends SudokuBoardController
{
    private final SudokuCreate createModel;
    private final SudokuCreateView view;
    
    public SudokuCreateController(IBoard model, SudokuCreateView view, SudokuCreate createModel)
    {
        super(model, view.getBoardPanel());
        
        this.createModel = createModel;
        this.view = view;
        
        view.getSaveButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateBoard();
                
                boolean verifyCheck = createModel.verifyBoard();
                Boolean saveCheck = null;
                
                if(verifyCheck)
                    saveCheck = createModel.saveBoard();

                view.NotifyVerifySave(verifyCheck, saveCheck);
            }
        });
        
        view.getExitButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.dispose();
                SudokuStart.open();
            }
        });
    }
    
    
    public void updateBoard()
    {
        for(int y = 0; y < model.getLength(); y++)
		{
			for(int x = 0; x < model.getLength(); x++)
			{
				String text = this.view.getBoardPanel().getTextFields()[y][x].getText();
				
				if(text.isBlank())
					continue;
				
				Integer value = Integer.valueOf(text);
				
				if(value == null)
					continue;
				
                                if(!this.model.get(x, y).isGenerated())
                                    this.model.set(x, y, new BoardValue(value.intValue(), false));
			}
		}
    }
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku.gui.game.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Filip
 */
public class SudokuInstructionsController 
{
    public SudokuInstructionsController(SudokuInstructionsView view)
    {
        view.getCloseButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.dispose();
            }
        });
    }
}
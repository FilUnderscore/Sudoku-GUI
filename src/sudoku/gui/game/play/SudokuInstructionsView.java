/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku.gui.game.play;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * @author Filip
 */
public class SudokuInstructionsView extends JFrame
{
    /**
      "Sudoku is a game where you must fill each 3x3 region with the numbers 1-9.
       Each row/column on the game board cannot repeat the same numbers in that row/column.
       And each 3x3 region cannot have repeat values of 1-9 within.

	Entering values will be done horizontally-first,
	going to the next row after the final column of the previous row has been entered."
     */
    
    private final JTextArea instructionLabel;
    private final JButton closeButton;
    
    public SudokuInstructionsView()
    {
        this.setTitle("Sudoku Instruction Manual");
        this.setSize(430, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        this.instructionLabel = new JTextArea("Sudoku is a game where you must fill\n each 3x3 region with the numbers 1-9.\n\n" +
       "Each row/column on the game board cannot\n repeat the same numbers in that row/column.\n\n" +
       "And each 3x3 region cannot have repeat values of 1-9 within.\n\n\n\n" +

	" Entering values will be done horizontally-first,\n\n" +
	" going to the next row after the final column \nof the previous row has been entered.");
        
        this.instructionLabel.setSize(400, 100);
        this.instructionLabel.setLocation(10, 0);
        this.instructionLabel.setWrapStyleWord(true);
        this.add(this.instructionLabel);
        
        this.closeButton = new JButton("Close");
        this.closeButton.setLocation(10, 105);
        this.closeButton.setSize(400, 50);
        this.add(this.closeButton);
        
        this.setVisible(true);
    }
    
    public JButton getCloseButton()
    {
        return this.closeButton;
    }
}

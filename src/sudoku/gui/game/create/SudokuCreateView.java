/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku.gui.game.create;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sudoku.board.BoardValue;
import sudoku.board.IBoard;
import sudoku.gui.game.SudokuBoardPanel;

/**
 *
 * @author Filip
 */
public final class SudokuCreateView extends JFrame
{
    private static final int HEIGHT_WIDTH = 700;
    
    private final IBoard model;
    
    private final SudokuBoardPanel boardPanel;
    private final JButton saveButton;
    private final JButton exitButton;
    
    private final JLabel saveLabel;
        
    public SudokuCreateView(IBoard model)
    {
        this.model = model;
        
        this.setTitle("Create a Sudoku");
        this.setSize(HEIGHT_WIDTH + 300, HEIGHT_WIDTH + 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        this.boardPanel = new SudokuBoardPanel(model, HEIGHT_WIDTH);
        this.boardPanel.setLocation(0, 0);
        this.add(this.boardPanel);
        
        this.saveButton = new JButton("Verify & Save");
        this.saveButton.setSize(150, 40);
        this.saveButton.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2);
        this.add(this.saveButton);
        
        this.exitButton = new JButton("Quit");
        this.exitButton.setSize(100, 40);
        this.exitButton.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2 + 60);
        this.add(this.exitButton);
        
        this.saveLabel = new JLabel("Click Save & Verify when done.");
	this.saveLabel.setSize(300, 50);
	this.saveLabel.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2 - 40);
        this.add(this.saveLabel);
        
        this.setVisible(true);
        this.update();
    }
    
    public JButton getSaveButton()
    {
        return this.saveButton;
    }
    
    public JButton getExitButton()
    {
        return this.exitButton;
    }
    
    public SudokuBoardPanel getBoardPanel()
    {
        return this.boardPanel;
    }
    
    public void NotifyVerifySave(boolean verifyCheck, Boolean saveCheck)
    {
        String text = "";
        Color color = Color.black;
        
        if(verifyCheck)
        {
            text = "Verified.";
            
            if(saveCheck == null)
            {
                text += " Failed to save.";
                color = Color.red;
            }
            else if(saveCheck != null)
            {
                boolean save = saveCheck.booleanValue();
                
                if(save)
                {
                    text += " Saved.";
                    color = Color.black;
                }
                else
                {
                    text += " Failed to save to database.";
                    color = Color.red;
                }
            }
        }
        else
        {
            text = "Verification failed, board impossible to solve.";
            color = Color.red;
        }
        
        this.saveLabel.setForeground(color);
        this.saveLabel.setText(text);
    }
    
    public void update()
    {
        this.boardPanel.update();
        this.repaint();
    }
}
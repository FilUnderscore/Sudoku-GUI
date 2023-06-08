/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku.gui.game.create;

import javax.swing.JButton;
import javax.swing.JFrame;
import sudoku.board.IBoard;
import sudoku.gui.game.SudokuBoardPanel;

/**
 *
 * @author Filip
 */
public final class SudokuCreateView extends JFrame
{
    private static final int HEIGHT_WIDTH = 700;
    
    private final SudokuBoardPanel boardPanel;
    private final JButton saveButton;
    private final JButton exitButton;
    
    public SudokuCreateView(IBoard model)
    {
        this.setTitle("Sudoku Create");
        this.setSize(HEIGHT_WIDTH + 200, HEIGHT_WIDTH + 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        this.boardPanel = new SudokuBoardPanel(model, HEIGHT_WIDTH);
        this.boardPanel.setLocation(0, 0);
        this.add(this.boardPanel);
        
        this.saveButton = new JButton("Save");
        this.saveButton.setSize(100, 40);
        this.saveButton.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2);
        this.add(this.saveButton);
        
        this.exitButton = new JButton("Quit");
        this.exitButton.setSize(100, 40);
        this.exitButton.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2 + 60);
        this.add(this.exitButton);
        
        this.setVisible(true);
        this.update();
    }
    
    public SudokuBoardPanel getBoardPanel()
    {
        return this.boardPanel;
    }
    
    public void update()
    {
        this.boardPanel.update();
        this.repaint();
    }
}
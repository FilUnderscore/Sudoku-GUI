/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku.gui.game;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sudoku.board.BoardValue;
import sudoku.board.IBoard;

/**
 *
 * @author Filip
 */
public class SudokuBoardPanel extends JPanel
{
    private static final int REGION_OFFSET = 10;
	
    private final IBoard model;	
    private final JTextField[][] textBoxes;
    
    public SudokuBoardPanel(IBoard model, int HEIGHT_WIDTH)
    {
        this.model = model;
        
        this.setSize(HEIGHT_WIDTH, HEIGHT_WIDTH);
        this.setLayout(null);
        
        HEIGHT_WIDTH -= 25;
        
        this.textBoxes = new JTextField[model.getLength()][model.getLength()];
		int offset = HEIGHT_WIDTH / model.getLength();
		
		for(int y = 0; y < model.getLength(); y++)
		{
			for(int x = 0; x < model.getLength(); x++)
			{
				int regionOffsetX = (x / 3) * REGION_OFFSET;
				int regionOffsetY = (y / 3) * REGION_OFFSET;
						
				JTextField textField = new JTextField();
				textField.setSize(offset - 5, offset - 5);
				textField.setLocation(x * offset + 5 + regionOffsetX, y * offset + regionOffsetY);
				textField.setHorizontalAlignment(JTextField.CENTER);
                                textField.setFont(textField.getFont().deriveFont(HEIGHT_WIDTH / 16.0f));
                                
				BoardValue value = model.get(x, y);
				boolean generated = value.isGenerated();
				
				if(generated)
				{
                                    textField.setEnabled(false);
                                    textField.setDisabledTextColor(Color.white);
                                    textField.setBackground(Color.darkGray);
				}
                                else
                                {
                                    textField.setEnabled(true);
                                }
				
				this.add(textField);
				this.textBoxes[y][x] = textField;
			}
		}
                
                this.repaint();
    }
    
    
	public JTextField[][] getTextFields()
	{
		return this.textBoxes;
	}
	
}
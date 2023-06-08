package sudoku.gui.game;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sudoku.board.BoardValue;
import sudoku.board.IBoard;

public final class SudokuBoardView extends JFrame
{
	private static final int HEIGHT_WIDTH = 500;
	private static final int REGION_OFFSET = 10;
	
	private final IBoard model;
	
	private final JTextField[][] textBoxes;
	private final JButton checkButton;
	private final JButton clearButton;
	private final JButton resetButton;
	
	private final JLabel winLabel;
	
	private int checkOffset;
	
	public SudokuBoardView(IBoard model)
	{
		this.model = model;
		
		this.setTitle("Sudoku");
		this.setSize(HEIGHT_WIDTH + 200, HEIGHT_WIDTH + 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.textBoxes = new JTextField[model.getLength()][model.getLength()];
		int offset = HEIGHT_WIDTH / model.getLength();
		int buttonOffset = 0;
		
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

				BoardValue value = model.get(x, y);
				boolean generated = value.isGenerated();
				
				if(generated)
				{
					textField.setEnabled(false);
					textField.setDisabledTextColor(Color.black);
				}
				
				this.add(textField);
				this.textBoxes[x][y] = textField;
				
				buttonOffset = Math.max(buttonOffset, x * offset + regionOffsetY);
				checkOffset = Math.max(checkOffset, y * offset + regionOffsetX);
			}
		}
		
		this.checkButton = new JButton("Check");
		this.checkButton.setSize(HEIGHT_WIDTH, 40);
		this.checkButton.setLocation(10, buttonOffset + 60);
		this.add(this.checkButton);
		
		this.clearButton = new JButton("Clear");
		this.clearButton.setSize(100, 40);
		this.clearButton.setLocation(checkOffset + 70, HEIGHT_WIDTH / 2);
		this.add(this.clearButton);
		
		this.resetButton = new JButton("Reset");
		this.resetButton.setSize(100, 40);
		this.resetButton.setLocation(checkOffset + 70, HEIGHT_WIDTH / 2 + 60);
		this.add(this.resetButton);
		
		this.winLabel = new JLabel();
		this.winLabel.setSize(200, 50);
		this.winLabel.setLocation(checkOffset + 70, HEIGHT_WIDTH / 2 - 40);
		this.add(this.winLabel);
		
		this.setVisible(true);
		this.update();
	}
	
	public JButton getCheckButton()
	{
		return this.checkButton;
	}
	
	public JButton getClearButton()
	{
		return this.clearButton;
	}
	
	public JButton getResetButton()
	{
		return this.resetButton;
	}
	
	public JTextField[][] getTextFields()
	{
		return this.textBoxes;
	}
	
	public void NotifyCheck(boolean success)
	{
		String text;
		
		if(success)
			text = "Congratulations, you win!";
		else
			text = "Incorrect, keep trying.";
		
		if(success)
			this.winLabel.setForeground(Color.black);
		else
			this.winLabel.setForeground(Color.red);
		
		this.winLabel.setText(text);
	}
	
	public void update()
	{
		for(int y = 0; y < model.getLength(); y++)
		{
			for(int x = 0; x < model.getLength(); x++)
			{
				JTextField textField = this.textBoxes[y][x];
				BoardValue value = this.model.get(x, y);
				
				if(value.getValue() == 0 || value == null)
					textField.setText(null);
				else
					textField.setText(Integer.toString(value.getValue()));
			}
		}	
	}
}
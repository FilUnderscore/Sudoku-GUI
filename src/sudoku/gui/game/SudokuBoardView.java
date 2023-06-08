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
	
	private final IBoard model;
	
        private final SudokuBoardPanel boardPanel;
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
		
		this.boardPanel = new SudokuBoardPanel(model, HEIGHT_WIDTH);
                this.boardPanel.setLocation(0, 0);
                this.add(this.boardPanel);
		
		this.checkButton = new JButton("Check");
		this.checkButton.setSize(HEIGHT_WIDTH, 40);
		this.checkButton.setLocation(10, HEIGHT_WIDTH - 60);
		//this.add(this.checkButton);
		
		this.clearButton = new JButton("Clear");
		this.clearButton.setSize(100, 40);
		this.clearButton.setLocation(checkOffset + 70, HEIGHT_WIDTH / 2);
		//this.add(this.clearButton);
		
		this.resetButton = new JButton("Reset");
		this.resetButton.setSize(100, 40);
		this.resetButton.setLocation(checkOffset + 70, HEIGHT_WIDTH / 2 + 60);
		//this.add(this.resetButton);
		
		this.winLabel = new JLabel();
		this.winLabel.setSize(200, 50);
		this.winLabel.setLocation(checkOffset + 70, HEIGHT_WIDTH / 2 - 40);
		//this.add(this.winLabel);
		
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
				JTextField textField = this.boardPanel.getTextFields()[y][x];
				BoardValue value = this.model.get(x, y);
				
				if(value.getValue() == 0 || value == null)
					textField.setText(null);
				else
					textField.setText(Integer.toString(value.getValue()));
			}
		}	
	}
        
        public SudokuBoardPanel getBoardPanel()
        {
            return this.boardPanel;
        }
}
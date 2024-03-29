package sudoku.gui.game.play;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sudoku.board.IBoard;
import sudoku.gui.game.SudokuBoardPanel;

public final class SudokuGameView extends JFrame
{
	private static final int HEIGHT_WIDTH = 700;
	
        private final SudokuBoardPanel boardPanel;
	private final JButton checkButton;
	private final JButton clearButton;
	private final JButton resetButton;
	
        private final JButton instructionsButton;
        private final JLabel winLabel;
	
	public SudokuGameView(IBoard model)
	{		
		this.setTitle("Sudoku");
		this.setSize(HEIGHT_WIDTH + 200, HEIGHT_WIDTH + 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.boardPanel = new SudokuBoardPanel(model, HEIGHT_WIDTH);
                this.boardPanel.setLocation(0, 0);
                this.add(this.boardPanel);
		
		this.checkButton = new JButton("Check");
		this.checkButton.setSize(HEIGHT_WIDTH, 40);
		this.checkButton.setLocation(10, HEIGHT_WIDTH);
		this.add(this.checkButton);
		
		this.clearButton = new JButton("Clear");
		this.clearButton.setSize(100, 40);
		this.clearButton.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2);
		this.add(this.clearButton);
		
		this.resetButton = new JButton("Quit");
		this.resetButton.setSize(100, 40);
		this.resetButton.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2 + 60);
		this.add(this.resetButton);
		
                this.instructionsButton = new JButton("Instructions");
                this.instructionsButton.setSize(120, 40);
                this.instructionsButton.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2 - 60);
                this.add(this.instructionsButton);
                
		this.winLabel = new JLabel();
		this.winLabel.setSize(200, 50);
		this.winLabel.setLocation(HEIGHT_WIDTH, HEIGHT_WIDTH / 2 - 100);
		this.add(this.winLabel);
		
		this.setVisible(true);
                this.update();
	}
        
        public JButton getInstructionsButton()
        {
            return this.instructionsButton;
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
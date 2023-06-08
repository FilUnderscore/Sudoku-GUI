package sudoku.gui.start;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SudokuStartPlayPanel extends JPanel
{	
	private JLabel instructionLabel;

	private JLabel difficultyNumberLabel;
	private JButton incButton;
	private JButton decButton;
	private JButton zeroButton;

	private JButton startButton;
	
	public SudokuStartPlayPanel()
	{
		this.setSize(300, 250);
		this.setLayout(null);
		
		this.instructionLabel = new JLabel("Pick a difficulty (number of tile shuffles):");
		this.instructionLabel.setSize(300, 50);
		this.instructionLabel.setLocation(30, 0);
		this.add(this.instructionLabel);
		
		this.difficultyNumberLabel = new JLabel("0");
		this.difficultyNumberLabel.setSize(50, 50);
		this.difficultyNumberLabel.setLocation(130, 50);
		this.difficultyNumberLabel.setFont(this.difficultyNumberLabel.getFont().deriveFont(24.0f));
		this.add(this.difficultyNumberLabel);
		
		this.incButton = new JButton(">");
		this.incButton.setSize(50, 50);
		this.incButton.setLocation(180, 50);
		this.add(this.incButton);
		
		this.decButton = new JButton("<");
		this.decButton.setSize(50, 50);
		this.decButton.setLocation(50, 50);
		this.add(this.decButton);	
	}
}
package sudoku.gui.start;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sudoku.SudokuStart;

public class SudokuStartPlayPanel extends JPanel
{	
	private SudokuStart model;
	private JLabel instructionLabel;

	private JLabel difficultyNumberLabel;
	private JButton incButton;
	private JButton decButton;
	private JButton zeroButton;

	private JButton startButton;
	
	public SudokuStartPlayPanel(SudokuStart model)
	{
		this.model = model;
		
		this.setSize(300, 300);
		this.setLayout(null);
		
		this.instructionLabel = new JLabel("Pick a difficulty (tile shuffles & hints removed):");
		this.instructionLabel.setSize(300, 50);
		this.instructionLabel.setLocation(10, 0);
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
		
		this.zeroButton = new JButton("0");
		this.zeroButton.setSize(50, 50);
		this.zeroButton.setLocation(115, 120);
		this.add(this.zeroButton);
		
		this.startButton = new JButton("Start");
		this.startButton.setSize(150, 50);
		this.startButton.setLocation(70, 220);
		this.add(this.startButton);
	}
	
	public JButton getIncButton()
	{
		return this.incButton;
	}
	
	public JButton getDecButton()
	{
		return this.decButton;
	}
	
	public JButton getZeroButton()
	{
		return this.zeroButton;
	}
	
	public JButton getStartButton()
	{
		return this.startButton;
	}
	
	public void update()
	{
		this.difficultyNumberLabel.setText(Integer.toString(this.model.difficulty));
		this.repaint();
	}
}
package sudoku.gui.start;

import javax.swing.JFrame;
import javax.swing.JLabel;

import sudoku.SudokuStart;

public final class SudokuStartView extends JFrame
{
	private SudokuStart model;
	private JLabel gameLabel;
	
	private SudokuStartPlayPanel playPanel;
	
	public SudokuStartView(SudokuStart model)
	{
		this.model = model;
		
		this.setTitle("Welcome to Sudoku");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 500);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.gameLabel = new JLabel("Sudoku");
		this.gameLabel.setSize(150, 50);
		this.gameLabel.setLocation(80, 0);
		this.gameLabel.setFont(this.gameLabel.getFont().deriveFont(32.0f));
		this.add(this.gameLabel);
		
		this.playPanel = new SudokuStartPlayPanel(model);
		this.playPanel.setLocation(0, 50);
		add(this.playPanel);
		
		this.setVisible(true);
	}
	
	public SudokuStartPlayPanel getPlayPanel()
	{
		return this.playPanel;
	}
	
	public void update()
	{
		this.playPanel.update();
		this.repaint();
	}
}
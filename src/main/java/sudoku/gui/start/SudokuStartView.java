package sudoku.gui.start;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sudoku.SudokuStart;

public final class SudokuStartView extends JFrame
{
	private SudokuStart model;
	private JLabel gameLabel;
	
	private JButton playButton;
	private SudokuStartPlayPanel playPanel;
	
	private JButton createButton;
	private SudokuStartCreatePanel createPanel;
	
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

		this.playButton = new JButton("Play");
		this.playButton.setSize(150, 50);
		this.playButton.setLocation(70, 70);
		this.add(this.playButton);
		
		this.playPanel = new SudokuStartPlayPanel();
		this.playPanel.setLocation(0, 200);
		
		this.createButton = new JButton("Create");
		this.createButton.setSize(150, 50);
		this.createButton.setLocation(70, 140);
		this.add(this.createButton);
		
		this.createPanel = new SudokuStartCreatePanel();
		this.createPanel.setLocation(0, 50);
		
		this.setVisible(true);
	}
	
	public JButton getPlayButton()
	{
		return this.playButton;
	}
	
	public JButton getCreateButton()
	{
		return this.createButton;
	}
	
	public SudokuStartPlayPanel getPlayPanel()
	{
		return this.playPanel;
	}
	
	public SudokuStartCreatePanel getCreatePanel()
	{
		return this.createPanel;
	}
	
	public void update()
	{
		switch(this.model.state)
		{
		case CREATE:
			this.remove(this.playPanel);
			this.playButton.setText("Play");
			this.createButton.setText("-> Create <-");
			this.add(this.createPanel);
			break;
		case PLAY:
			this.remove(this.createPanel);
			this.playButton.setText("-> Play <-");
			this.createButton.setText("Create");
			this.add(this.playPanel);
			break;
		default:
			break;
		}

		this.repaint();
	}
}
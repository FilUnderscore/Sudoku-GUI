package sudoku.gui.start;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sudoku.SudokuStart;

public final class SudokuStartView extends JFrame
{
	private SudokuStart model;
	private JLabel gameLabel;
        private JLabel playLabel;

        private JButton createButton;
        
	private SudokuStartPlayPanel playPanel;
	
	public SudokuStartView(SudokuStart model)
	{
		this.model = model;
		
		this.setTitle("Welcome to Sudoku");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 600);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.gameLabel = new JLabel("Sudoku");
		this.gameLabel.setSize(150, 50);
		this.gameLabel.setLocation(80, 0);
		this.gameLabel.setFont(this.gameLabel.getFont().deriveFont(32.0f));
		this.add(this.gameLabel);
		
                this.createButton = new JButton("Create");
                this.createButton.setSize(150, 60);
                this.createButton.setLocation(65, 80);
                this.add(this.createButton);

                this.playLabel = new JLabel("OR Play:");
                this.playLabel.setSize(150, 50);
                this.playLabel.setLocation(85, 160);
                this.playLabel.setFont(this.playLabel.getFont().deriveFont(24.0f));
                this.add(this.playLabel);
                                
		this.playPanel = new SudokuStartPlayPanel(model);
		this.playPanel.setLocation(0, 220);
		add(this.playPanel);
		
		this.setVisible(true);
	}
	
	public SudokuStartPlayPanel getPlayPanel()
	{
		return this.playPanel;
	}
        
        public JButton getCreateButton()
        {
            return this.createButton;
        }
	
	public void update()
	{
		this.playPanel.update();
		this.repaint();
	}
}
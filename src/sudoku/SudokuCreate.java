/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

import sudoku.board.Board;
import sudoku.board.IBoard;
import sudoku.database.IBoardDatabase;
import sudoku.gui.game.create.SudokuCreateController;
import sudoku.gui.game.create.SudokuCreateView;

/**
 *
 * @author Filip
 */
public final class SudokuCreate 
{
    private final IBoardDatabase boardDatabase;
    
    private SudokuCreateView view;
    private SudokuCreateController controller;
    
    private IBoard board;
    
    public SudokuCreate(IBoardDatabase boardDatabase)
    {
        this.boardDatabase = boardDatabase;
        this.initialize();
    }
    
    public void initialize()
    {
        this.board = new CreativeBoard(9);
        this.initializeView();
    }
    
    private void initializeView()
    {
        this.view = new SudokuCreateView(this.board);
        this.controller = new SudokuCreateController(this.board, this.view, this);
    }
    
    public boolean saveBoard()
    {
        return this.boardDatabase != null && this.boardDatabase.connect() && this.boardDatabase.saveStartingBoard(board);
    }
    
    public boolean verifyBoard()
    {
        return this.board.isSolvable();
    }
    
    private class CreativeBoard extends Board
    {
        public CreativeBoard(int n)
        {
            super(n);
        }

        @Override
        protected int getGeneratedValueAt(int x, int y) 
        {
            return 0;
        }
    }
}
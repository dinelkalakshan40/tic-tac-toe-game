package lk.ijse.gdse.tictactoe.service;

import java.util.Scanner;

public class HumanPlayer extends Player{
    private int selectedRow;
    private int selectedCol;

    public HumanPlayer(BoardImpl board) {
        super(board);

    }
    @Override
    public void aiPlayerMove() {
        board.updateMove(selectedRow, selectedCol, Piece.X);

    }
    @Override
    public void humanPlayerMove(int row, int col) {
        board.updateMove(row, col, Piece.X);
    }

}

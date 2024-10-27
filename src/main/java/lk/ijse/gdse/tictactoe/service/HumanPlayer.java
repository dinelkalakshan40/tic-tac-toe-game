package lk.ijse.gdse.tictactoe.service;

import java.util.Scanner;

public class HumanPlayer extends Player{
    private int selectedRow;
    private int selectedCol;
    private Scanner scanner;

    public HumanPlayer(BoardImpl board) {
        super(board);

    }
    public void setMove(int row, int col) {
        this.selectedRow = row;
        this.selectedCol = col;
    }

    @Override
    public void move() {
        board.updateMove(selectedRow, selectedCol, Piece.X);

    }
    public void move(int row, int col) {
        board.updateMove(row, col, Piece.X);
    }


}

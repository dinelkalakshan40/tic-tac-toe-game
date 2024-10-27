package lk.ijse.gdse.tictactoe.service;

public interface Board {
    void initializeBoard();
    boolean isLegalMove(int row, int col);
    void updateMove(int row, int col, Piece piece);
    Piece checkWinner();
    void printBoard();
    boolean isFull();
    Piece getPiece(int row, int col);

}

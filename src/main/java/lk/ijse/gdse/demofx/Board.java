package lk.ijse.gdse.demofx;

public interface Board {
    void initializeBoard();
    boolean isLegalMove(int row, int col);
    void updateMove(int row, int col, Piece piece);
    Piece checkWinner();
    void printBoard();
    boolean isFull();
    Piece getPiece(int row, int col);

}

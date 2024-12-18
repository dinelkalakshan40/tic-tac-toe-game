package lk.ijse.gdse.tictactoe.service;

public class BoardImpl implements Board {
    private Piece[][] pieces;



    public BoardImpl() { //constructor
        pieces = new Piece[3][3]; //Creates a 3x3 grid
        initializeBoard();
    }

    @Override
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && pieces[row][col] == Piece.EMPTY;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        pieces[row][col] = piece;
    }


    @Override
    public Winner checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) {
                return new Winner(pieces[i][0], 0, i, 1, i, 2, i); // Return winning piece and coordinates
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[0][i] != Piece.EMPTY) {
                return new Winner(pieces[0][i], i, 0, i, 1, i, 2); // Return winning piece and coordinates
            }
        }

        // Check diagonals
        if (pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) {
            return new Winner(pieces[0][0], 0, 0, 1, 1, 2, 2); // Return winning piece and coordinates
        }
        if (pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0] && pieces[0][2] != Piece.EMPTY) {
            return new Winner(pieces[0][2], 2, 0, 1, 1, 0, 2); // Return winning piece and coordinates
        }

        return new Winner(Piece.EMPTY); // No winner
    }

    @Override
    public boolean isFull() {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }

}

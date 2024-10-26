package lk.ijse.gdse.demofx.service;

public class BoardImpl implements Board {
    private Piece[][] pieces;

    public BoardImpl() {
        pieces = new Piece[3][3];
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

    public int[] findNextAvailableSpot() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return new int[]{i, j}; // Return the first available spot
                }
            }
        }
        return null; // No available spot
    }

    @Override
    public Piece checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) {
                return pieces[i][0]; // Return the winning piece
            }
            if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[0][i] != Piece.EMPTY) {
                return pieces[0][i]; // Return the winning piece
            }
        }
        // Check diagonals
        if (pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) {
            return pieces[0][0]; // Return the winning piece
        }
        if (pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0] && pieces[0][2] != Piece.EMPTY) {
            return pieces[0][2]; // Return the winning piece
        }
        return Piece.EMPTY; // No winner
    }

    @Override
    public void printBoard() {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                System.out.print(piece + " ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean isFull() {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece == Piece.EMPTY) {
                    return false; // At least one empty cell exists
                }
            }
        }
        return true; // No empty cells
    }
    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }

}

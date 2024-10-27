package lk.ijse.gdse.tictactoe.service;

public class Winner {
    public Piece winningPiece;

    public int col1;
    public int row1;
    public int col2;
    public int row2;
    public int col3;
    public int row3;

    public Piece getWinningPiece() {
        return winningPiece;
    }

    public int getRow3() {
        return row3;
    }

    public int getCol3() {
        return col3;
    }

    public int getRow2() {
        return row2;
    }

    public int getCol2() {
        return col2;
    }

    public int getRow1() {
        return row1;
    }

    public int getCol1() {
        return col1;
    }

    public void setWinningPiece(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public void setRow3(int row3) {
        this.row3 = row3;
    }

    public void setCol3(int col3) {
        this.col3 = col3;
    }

    public void setRow2(int row2) {
        this.row2 = row2;
    }

    public void setCol2(int col2) {
        this.col2 = col2;
    }

    public void setRow1(int row1) {
        this.row1 = row1;
    }

    public void setCol1(int col1) {
        this.col1 = col1;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "winningPiece=" + winningPiece +
                ", col1=" + col1 +
                ", row1=" + row1 +
                ", col2=" + col2 +
                ", row2=" + row2 +
                ", col3=" + col3 +
                ", row3=" + row3 +
                '}';
    }
    public Winner(Piece winningPiece){
        setWinningPiece(winningPiece);
        this.col1 = -1;
        this.row1 = -1;
        this.col2 = -1;
        this.row2 = -1;
        this.col3 = -1;
        this.row3 = -1;
    }
    public Winner(Piece winningPiece, int col1, int row1, int col2, int row2,int col3,int row3) {
        this.winningPiece = winningPiece;
        this.setCol1(col1);
        this.setRow2(row2);
        this.setRow1(row1);
        this.setCol2(col2);
        this.setCol3(col3);
        this.setRow3(row3);
    }
}

package lk.ijse.gdse.tictactoe.service;

public abstract class Player {
    protected BoardImpl board;

    public Player(BoardImpl board) {
        this.board = board;
    }

    public abstract void move();
}

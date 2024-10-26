package lk.ijse.gdse.demofx.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.gdse.demofx.service.AiPlayer;
import lk.ijse.gdse.demofx.service.BoardImpl;
import lk.ijse.gdse.demofx.service.HumanPlayer;
import lk.ijse.gdse.demofx.service.Piece;

public class BoardController {

    public GridPane grid;

    public Button button00;
    public Button button01;
    public Button button02;
    public Button button10;
    public Button button11;
    public Button button12;
    public Button button20;
    public Button button21;
    public Button button22;
    public Text lblPlayer;

    private Button[][] buttons;

    private BoardImpl board;
    private AiPlayer aiPlayer;
    private HumanPlayer humanPlayer;

    @FXML
    public void initialize() {
        board = new BoardImpl();
        aiPlayer = new AiPlayer(board);
        humanPlayer = new HumanPlayer(board);


        buttons = new Button[][]{
                {button00, button01, button02},
                {button10, button11, button12},
                {button20, button21, button22}
        };

        // Set button actions
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnAction(e -> handlePlayerMove(row, col));
            }
        }
    }
    private void handlePlayerMove(int row, int col) {
        if (board.isLegalMove(row, col)) {
            humanPlayer.move(row, col);
            buttons[row][col].setText("X"); // Human player's move
            checkWinner();

            // AI move
            if (!board.isFull() && board.checkWinner() == Piece.EMPTY) {
                aiPlayer.move();
                updateBoard();
                checkWinner();
            }
        }
    }

    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece == Piece.X) {
                    buttons[i][j].setText("X");
                } else if (piece == Piece.O) {
                    buttons[i][j].setText("O");
                } else {
                    buttons[i][j].setText("");
                }
            }
        }
    }

    private void checkWinner() {
        Piece winner = board.checkWinner();
        if (winner == Piece.X) {
            System.out.println("Human wins!");
            delayResetBoard();
        } else if (winner == Piece.O) {
            System.out.println("AI wins!");
            delayResetBoard();
        } else if (board.isFull()) {
            System.out.println("Not winner!");
            delayResetBoard();
        }
    }

    private void delayResetBoard() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> resetBoard()));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void resetBoard() {
        board.initializeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
    public void setPlayerName(String name){
        lblPlayer.setText(name);
    }
}

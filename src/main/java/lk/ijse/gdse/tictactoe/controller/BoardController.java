package lk.ijse.gdse.tictactoe.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.gdse.tictactoe.service.*;

public class BoardController implements BoardUI {

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
            if (!board.isFull() && board.checkWinner().getWinningPiece() == Piece.EMPTY) {
                aiPlayer.move();
                updateBoard();
                checkWinner();
            }
        }
    }
    @Override
    public void update(int col, int row, boolean isHuman) {
        Button button = buttons[row][col];
        if (isHuman) {
            button.setText("X");
            button.setStyle("-fx-text-fill: green; -fx-font-size: 40px;");
        } else {
            button.setText("O");
            button.setStyle("-fx-text-fill: blue; -fx-font-size: 40px;");
        }
    }

    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece == Piece.X) {
                    update(j, i, true); // Human piece
                } else if (piece == Piece.O) {
                    update(j, i, false); // AI piece
                } else {
                    // Clear button for empty cell
                    buttons[i][j].setText("");
                    buttons[i][j].setStyle("");
                }
            }
        }
    }


    @Override
    public void NotifyWinner() {
        checkWinner();
    }

    private void checkWinner() {
        Winner winnerResult = board.checkWinner(); // Get Winner object
        Piece winner = winnerResult.getWinningPiece();
        String message = "";

        if (winner == Piece.X) {
            message = "Congratulations! Human wins! You played well!";
        } else if (winner == Piece.O) {
            message = "Oh no! AI wins! Better luck next time!";
        } else if (board.isFull()) {
            message = "It's a draw! No winners this time, but you can try again!";
        }

        if (!message.isEmpty()) {
            showAlert(message);
            delayResetBoard(); // Call to reset the board after displaying the message
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Winner");
        alert.setContentText(message);
        alert.getButtonTypes().setAll(ButtonType.OK);

        alert.showAndWait();
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
    //set PlayerName
    public void setPlayerName(String name){
        lblPlayer.setText(name);
    }

}

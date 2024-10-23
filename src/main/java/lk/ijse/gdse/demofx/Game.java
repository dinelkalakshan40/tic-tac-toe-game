package lk.ijse.gdse.demofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    private BoardImpl board;
    private AiPlayer aiPlayer;
    private HumanPlayer humanPlayer;

    // 3x3 Button array to represent the board
    private Button[][] buttons = new Button[3][3];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new BoardImpl();
        aiPlayer = new AiPlayer(board);
        humanPlayer = new HumanPlayer(board);

        GridPane grid = new GridPane();
        Button[][] buttons = new Button[3][3];

        // Create buttons for each cell in the Tic-Tac-Toe grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(100, 100);
                final int row = i;
                final int col = j;

                // Handle button click
                buttons[i][j].setOnAction(e -> {
                    if (board.isLegalMove(row, col)) {
                        humanPlayer.move(row, col);
                        buttons[row][col].setText("X"); // Human player's move
                        Piece winner = board.checkWinner();
                        if (winner == Piece.X) {
                            buttons[row][col].setText("X"); // Ensure the human player's last move is displayed
                          //  displayWinner("Human Winner!"); // Display "Human Winner" message
                            delayResetBoard(buttons); // Delay board reset to show final move
                            System.out.println("Human Winner!");
                        } else if (!board.isFull()) {
                            // If no winner yet, AI makes its move
                            aiPlayer.move();
                            updateBoard(buttons); // Update UI after AI's move

                            // Check if the AI wins
                            winner = board.checkWinner();
                            if (winner == Piece.O) {
                                updateBoard(buttons); // Ensure the AI's last move is displayed
                                System.out.println("AI Winner!");
                                delayResetBoard(buttons); // Delay board reset to show final move
                            }
                        } else {
                            System.out.println("No Winner, It's a Draw!");
                            delayResetBoard(buttons); // Delay board reset for a draw
                        }
                    }
                });

                grid.add(buttons[i][j], j, i);
            }
        }


        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void delayResetBoard(Button[][] buttons) {
        // Create a timeline that pauses for 1 second (1000 ms) before resetting the board
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            resetBoard(buttons); // Reset the board after the delay
        }));
        timeline.setCycleCount(1); // Only run once
        timeline.play(); // Start the delay
    }

    // Handle the player's move
   /* private void handlePlayerMove(int row, int col) {
        if (board.isLegalMove(row, col)) {
            // Set the human player's move
            humanPlayer.setMove(row, col);
            humanPlayer.move(); // Place "X"
            buttons[row][col].setText("X"); // Update UI

            // Check for a winner after the human move
            Piece winner = board.checkWinner();
            if (winner != Piece.EMPTY) {
                displayWinner(winner);
                resetBoard();
                return; // Stop further processing if there's a winner
            }

            // AI makes its move
            aiPlayer.move(); // AI makes a move
            updateBoard(); // Update UI for AI move

            // Check for a winner after the AI move
            winner = board.checkWinner();
            if (winner != Piece.EMPTY) {
                displayWinner(winner);
                resetBoard();
            }
        }
    }*/

    // Update the UI to reflect AI's move
    private void updateBoard(Button[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getPiece(i, j) == Piece.X) {
                    buttons[i][j].setText("X");
                } else if (board.getPiece(i, j) == Piece.O) {
                    buttons[i][j].setText("O");
                }
            }
        }
    }

    // Display the winner
    private void displayWinner(Piece winner) {
        String message = winner == Piece.X ? "Human wins!" : "AI wins!";
        // Show message (e.g., in a dialog or console)
        System.out.println(message);
    }

    // Reset the board for a new game
    private void resetBoard(Button[][] buttons) {
        board.initializeBoard(); // Reset board state
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear button text
            }
        }
    }

}

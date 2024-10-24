package lk.ijse.gdse.demofx;

import java.util.Random;

public class AiPlayer extends Player{
    private Random random;

    public AiPlayer(BoardImpl board) {
        super(board);
        random = new Random();
    }

    @Override
    public void move() {
        // Use the Minimax algorithm to find the best move
        int[] bestMove = findBestMove();
        if (bestMove != null) {
            board.updateMove(bestMove[0], bestMove[1], Piece.O);
        }
    }

    private int[] findBestMove() {
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = null;

        // Iterate over each cell in the 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if the move is legal (the cell is empty)
                if (board.isLegalMove(i, j)) {
                    // Simulate the AI's move
                    board.updateMove(i, j, Piece.O);
                    // Evaluate this move with Minimax
                    int moveValue = minimax(0, false); // Call minimax with the next turn as minimizing player
                    // Undo the move
                    board.updateMove(i, j, Piece.EMPTY);

                    // If the value of this move is better than the best found so far, update bestMove
                    if (moveValue > bestValue) {
                        bestMove = new int[]{i, j};
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove; // Return the best move found
    }

    private int minimax(int depth, boolean isMaximizing) {
        // Check for terminal states and evaluate the board
        Piece winner = board.checkWinner();
        if (winner == Piece.O) {
            return 1; // AI wins
        } else if (winner == Piece.X) {
            return -1; // Human wins
        } else if (board.isFull()) {
            return 0; // Draw
        }

        if (isMaximizing) {
            int bestValue = Integer.MIN_VALUE;

            // Iterate through all cells to find the best move for the AI
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        // AI makes a move
                        board.updateMove(i, j, Piece.O);
                        bestValue = Math.max(bestValue, minimax(depth + 1, false)); // Recur for the minimizing player
                        // Undo move
                        board.updateMove(i, j, Piece.EMPTY);
                    }
                }
            }
            return bestValue; // Return the best value found for the maximizing player
        } else { // Minimizing player's turn
            int bestValue = Integer.MAX_VALUE;

            // Iterate through all cells to find the best move for the human
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        // Human makes a move
                        board.updateMove(i, j, Piece.X);
                        bestValue = Math.min(bestValue, minimax(depth + 1, true)); // Recur for the maximizing player
                        // Undo move
                        board.updateMove(i, j, Piece.EMPTY);
                    }
                }
            }
            return bestValue; // Return the best value found for the minimizing player
        }
    }
}

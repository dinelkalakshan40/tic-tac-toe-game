package lk.ijse.gdse.tictactoe.service;

public interface BoardUI {
   void update(int col, int row, boolean isHuman);
   void NotifyWinner();
}

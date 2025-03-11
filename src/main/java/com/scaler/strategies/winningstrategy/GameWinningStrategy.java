package com.scaler.strategies.winningstrategy;

import com.scaler.models.Board;
import com.scaler.models.Cell;
import com.scaler.models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Player player, Cell moveCell);

    void undoMove(Cell cell);
}

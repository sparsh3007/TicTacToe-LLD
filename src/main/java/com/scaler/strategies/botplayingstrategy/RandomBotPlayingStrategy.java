package com.scaler.strategies.botplayingstrategy;

import com.scaler.models.Board;
import com.scaler.models.CellState;
import com.scaler.models.Move;
import com.scaler.models.Player;

public class RandomBotPlayingStrategy implements  BotPlayingStrategy {

    @Override
    public Move decideMove(Player player, Board board) {
        //  Way 1. Iterate through the board and find all the empty cells and return the first available cell
        /*for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().size(); j++) {
                if (board.getBoard().get(i).get(j).getCellState() == CellState.EMPTY) {
                    return new Move(player, board.getBoard().get(i).get(j));
                }
            }
        }*/

        // Way 2. Keep generating random numbers until we find an empty cell
        int row, col;
        while (true) {
            row = (int) (Math.random() * board.getBoard().size());
            col = (int) (Math.random() * board.getBoard().size());
            if (board.getBoard().get(row).get(col).getCellState() == CellState.EMPTY) {
                return new Move(player, board.getBoard().get(row).get(col));
            }
        }
    }
}

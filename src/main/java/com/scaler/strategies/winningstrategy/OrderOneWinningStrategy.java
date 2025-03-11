package com.scaler.strategies.winningstrategy;

import com.scaler.models.Board;
import com.scaler.models.Cell;
import com.scaler.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements  GameWinningStrategy {
    // This strategy checks if the player has won by filling a row, column, or diagonal
    private List<HashMap<Character,Integer>> rowSymbolCount;
    private List<HashMap<Character,Integer>> colSymbolCount;
    private HashMap<Character,Integer> topLeftDiagonalSymbolCount;
    private HashMap<Character,Integer> topRightDiagonalSymbolCount;

    public OrderOneWinningStrategy(int boardSize) {
        rowSymbolCount = new ArrayList<>();
        colSymbolCount = new ArrayList<>();
        for(int i=0;i<boardSize;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
        topLeftDiagonalSymbolCount = new HashMap<>();
        topRightDiagonalSymbolCount = new HashMap<>();
    }
    @Override
    public boolean checkWinner(Board board, Player player, Cell moveCell) {
        // Check if the player has won by filling a row
        int row = moveCell.getRow();
        char symbol = player.getSymbol();
        rowSymbolCount.get(row).put(symbol, rowSymbolCount.get(row).getOrDefault(symbol, 0) + 1);
        if(rowSymbolCount.get(row).get(symbol) == board.getBoard().size()){
            return true;
        }

        // Check if the player has won by filling a column
        int col = moveCell.getCol();
        colSymbolCount.get(col).put(symbol, colSymbolCount.get(col).getOrDefault(symbol, 0) + 1);
        if(colSymbolCount.get(col).get(symbol) == board.getBoard().size()){
            return true;
        }

        // Check if the player has won by filling a diagonal
        if(row == col){
            topLeftDiagonalSymbolCount.put(symbol, topLeftDiagonalSymbolCount.getOrDefault(symbol, 0) + 1);
            if(topLeftDiagonalSymbolCount.get(symbol) == board.getBoard().size()){
                return true;
            }
        }
        if(row + col == board.getBoard().size() - 1){
            topRightDiagonalSymbolCount.put(symbol, topRightDiagonalSymbolCount.getOrDefault(symbol, 0) + 1);
            if(topRightDiagonalSymbolCount.get(symbol) == board.getBoard().size()){
                return true;
            }
        }
        return false;
    }
}

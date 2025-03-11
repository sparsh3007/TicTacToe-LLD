package com.scaler.models;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(String name, char symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move decideMove(Board board) {
        // Get the input from the player where to place the symbol
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row and column to place the symbol (0-indexed): ");
        int row = sc.nextInt();
        int col = sc.nextInt();

        return new Move(this, board.getBoard().get(row).get(col));
    }
}

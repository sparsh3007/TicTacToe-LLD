package com.scaler.controllers;

import com.scaler.models.Game;
import com.scaler.models.GameStatus;
import com.scaler.models.Player;

import java.util.List;

public class GameController {
    // This class is responsible for controlling the game flow
    // It will handle the game state, player turns, and interactions between players
    // It will also manage the winning strategy and bot playing strategy


    public Game startGame(int dimension, List<Player> players) {
        // Initialize game state
        // Set up players
        // Start the game loop
        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        }
        catch (Exception e) {
            System.out.println("Error starting game: " + e.getMessage());
            return null;
        }
    }
    public Player getWinner(Game game){
        return  game.getWinner();
    }

    public void makeMove(Game game) {
        // Validate move
        // Update game state
        // Check for win or draw
        game.makeNextMove();
    }

    public void displayBoard(Game game) {
        // Display the current state of the board
        game.displayBoard();
        // Or game.getBoard().displayBoard();
    }

    public void undoMove() {
        // Undo the last move
    }

    public GameStatus getGameStatus(Game game) {
        // Return the current game status
        return game.getGameStatus();
    }

}

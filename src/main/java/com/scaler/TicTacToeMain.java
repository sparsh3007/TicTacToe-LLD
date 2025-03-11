package com.scaler;

import com.scaler.controllers.GameController;
import com.scaler.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Please enter the dimension of the board (3 for 3x3, 4 for 4x4, etc.):");
        int dimension = scanner.nextInt();

        System.out.println("Will you be playing against a bot? (yes/no):");
        String playAgainstBot = scanner.next();

        int numberOfPlayers = dimension-1;
        int numberOfBots = playAgainstBot.equalsIgnoreCase("yes") ? 1 : 0;
        int numberOfHumans = numberOfPlayers - numberOfBots;

        List<Player> players = new ArrayList<>();
        for(int i = 0; i < numberOfHumans; i++) {
            System.out.println("Please enter the name of player " + (i + 1) + ":");
            String playerName = scanner.next();
            System.out.println("Please enter the symbol for player " + (i + 1) + ":");
            char playerSymbol = scanner.next().charAt(0);

            players.add(new Player(playerName, playerSymbol, PlayerType.HUMAN));
        }

        for(int i = 0; i < numberOfBots; i++) {
            System.out.println("Please enter the name of bot:");
            String botName = scanner.next();
            System.out.println("Please enter the symbol for bot:");
            char botSymbol = scanner.next().charAt(0);

            System.out.println("Please enter the difficulty level for bot (EASY, MEDIUM, HARD):");
            String difficultyLevel = scanner.next();
            players.add(new Bot(botName, botSymbol, DifficultyLevel.valueOf(difficultyLevel.toUpperCase())));
        }
        GameController gameController = new GameController();
        Game game = gameController.startGame(dimension, players);

        if (game != null) {
            System.out.println("Game started successfully!");
            // You can now use the game object to manage the game state
            while (game.getGameStatus() == GameStatus.IN_PROGRESS) {
                // Display the board
                System.out.println("Current board:");
                gameController.displayBoard(game);

                System.out.println("Do you want to undo the last move? (yes/no):");
                String undoMove = scanner.next();
                if (undoMove.equalsIgnoreCase("yes")) {
                    gameController.undoMove(game);
                    continue;
                }
                gameController.makeMove(game);

            }
            //Someone has won or draw
            if(gameController.getGameStatus(game) == GameStatus.ENDED){
                gameController.displayBoard(game);
                System.out.println("Winner is: "+ gameController.getWinner(game).getName() + " with symbol " + gameController.getWinner(game).getSymbol());
            }
            else {
                System.out.println("Game ended in a draw!");
            }
        } else {
            System.out.println("Failed to start the game.");
        }



    }
}
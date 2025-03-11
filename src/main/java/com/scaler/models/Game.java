package com.scaler.models;

import com.scaler.exceptions.InvalidDimensionException;
import com.scaler.exceptions.InvalidPlayerNumberOfPlayersException;
import com.scaler.exceptions.InvalidPlayerSymbolException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    GameStatus gameStatus;
    int nextPlayerIndex;
    Player winner;

    private Game(){}

    private Game(GameBuilder builder){
        this.players = builder.players;
    }
    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public  Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void displayBoard() {
        board.displayBoard();
    }

    public static class GameBuilder{
        private int dimension;
        private List<Player> players;


        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        private void isValid() throws InvalidDimensionException, InvalidPlayerNumberOfPlayersException, InvalidPlayerSymbolException {
            if(this.dimension <3){
                throw new InvalidDimensionException("Dimension should be greater than or equal to 3");
            }
            if(this.players.size()!=this.dimension-1){
                throw new InvalidPlayerNumberOfPlayersException("Number of players should be equal to dimension - 1");
            }
            HashSet<Character>  symbols = new HashSet<>();
            for(int i=0;i<players.size();i++) {
                if (symbols.contains(players.get(i).getSymbol())) {
                    throw new InvalidPlayerSymbolException("Symbols should be unique");
                }
                symbols.add(players.get(i).getSymbol());
            }
        }
        public Game build() {
            try {
                isValid();
            } catch (InvalidDimensionException | InvalidPlayerNumberOfPlayersException | InvalidPlayerSymbolException e) {
                System.out.println(e.getMessage());
            }
            Game game = new Game();
            game.board = new Board(dimension);
            game.players = players;
            game.moves = new ArrayList<>();
            game.gameStatus = GameStatus.IN_PROGRESS;
            game.nextPlayerIndex = 0;
            return game;
        }
    }
}

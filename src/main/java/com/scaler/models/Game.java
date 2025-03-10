package com.scaler.models;

import java.util.List;

public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    GameStatus gameStatus;
    int nextPlayerIndex;
    int winnerIndex;

    public Game(Board board, List<Player> players, List<Move> moves, GameStatus gameStatus, int nextPlayerIndex, int winnerIndex) {
        this.board = board;
        this.players = players;
        this.moves = moves;
        this.gameStatus = gameStatus;
        this.nextPlayerIndex = nextPlayerIndex;
        this.winnerIndex = winnerIndex;
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

    public int getWinnerIndex() {
        return winnerIndex;
    }

    public void setWinnerIndex(int winnerIndex) {
        this.winnerIndex = winnerIndex;
    }
}

package com.scaler.models;

import com.scaler.exceptions.InvalidDimensionException;
import com.scaler.exceptions.InvalidPlayerNumberOfPlayersException;
import com.scaler.exceptions.InvalidPlayerSymbolException;
import com.scaler.strategies.winningstrategy.GameWinningStrategy;
import com.scaler.strategies.winningstrategy.OrderOneWinningStrategy;

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
    GameWinningStrategy gameWinningStrategy;

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

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void displayBoard() {
        board.displayBoard();
    }

    public void makeNextMove() {
        // 1. Get the current player
        Player currentPlayer = players.get(nextPlayerIndex);

        // 2. Player should decide where to play
        System.out.println("Player " + currentPlayer.getName() + "'s turn. Please make your move.");
        Move move = currentPlayer.decideMove(this.getBoard());

        // 3. Validate the move
        // Check if the cell is within the bounds of the board
        Cell cell = move.getCell();
        if (cell.getRow() < 0 || cell.getRow() >= board.getBoard().size() ||
                cell.getCol() < 0 || cell.getCol() >= board.getBoard().get(0).size()) {
            System.out.println("Invalid move. Cell is out of bounds. Try again.");
            // Ask the player to decide again
            makeNextMove();
        }
        // Check if the cell is empty
        else if (cell.getCellState() != CellState.EMPTY) {
            System.out.println("Invalid move. Cell is already occupied. Try again.");
            // Ask the player to decide again
            makeNextMove();
        }

        // 4. If move is valid, update the board and move list
        else {
            int row = cell.getRow();
            int col = cell.getCol();
            System.out.println("Player " + currentPlayer.getName() + " made a move at (" + row + ", " + col + ")");
            board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
            board.getBoard().get(row).get(col).setPlayer(currentPlayer);
            moves.add(move);

            // 5. Check if the game is over (win or draw)
            if (gameWinningStrategy.checkWinner(board, currentPlayer, cell)) {
                System.out.println("Player " + currentPlayer.getName() + " wins!");
                gameStatus = GameStatus.ENDED;
                winner = currentPlayer;
            }
            // 6. Move to the next player
            nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
        }
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
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimension));
            return game;
        }
    }
}

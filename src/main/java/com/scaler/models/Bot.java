package com.scaler.models;

import com.scaler.strategies.botplayingstrategy.BotPlayingStrategy;
import com.scaler.strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class Bot extends  Player {
    private  DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, char symbol,DifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = new RandomBotPlayingStrategy();
    }
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    @Override
    public Move decideMove(Board board) {
        System.out.println("Bot " + this.getName()+" is deciding the move");
        return botPlayingStrategy.decideMove(this, board);
    }
}

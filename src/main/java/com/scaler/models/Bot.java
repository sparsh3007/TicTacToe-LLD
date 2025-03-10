package com.scaler.models;

public class Bot extends  Player {
    private  DifficultyLevel difficultyLevel;

    public Bot(String name, char symbol,DifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
    }
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}

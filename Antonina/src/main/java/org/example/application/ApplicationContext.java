package org.example.application;

import org.example.gamefield.GameField;

public class ApplicationContext {

    private static ApplicationContext INSTANCE;

    private GameField gameField;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (INSTANCE == null) {
            return new ApplicationContext();
        }
        return INSTANCE;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(int width, int height) {
        gameField = new GameField(width, height);
    }
}

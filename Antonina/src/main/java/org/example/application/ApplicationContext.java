package org.example.application;

import org.example.actions.AnimalSpawner;
import org.example.actions.Statistics;
import org.example.gamefield.GameField;

public class ApplicationContext {

    private static ApplicationContext INSTANCE;
    private GameField gameField;
    private Statistics statistics;
    private AnimalSpawner animalSpawner = new AnimalSpawner();

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

    public void startAnimalSpawner() {
        Thread animalSpawnerThread = new Thread(animalSpawner);
        animalSpawnerThread.start();
        try {
            animalSpawnerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.application;

import org.example.actions.AnimalSpawner;
import org.example.actions.GrassSpawner;
import org.example.actions.Statistics;
import org.example.gamefield.GameField;

import java.util.concurrent.*;

public class ApplicationContext {

    private static ApplicationContext INSTANCE;
    private GameField gameField;
    private Statistics statistics;
    private AnimalSpawner animalSpawner = new AnimalSpawner();
    private GrassSpawner grassSpawner = new GrassSpawner();

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
        gameField = GameField.getInstance();
        gameField.setDimensions(width, height);
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

    public void startGrassSpawner() {
        Thread grassSpawnerThread = new Thread(grassSpawner);
        grassSpawnerThread.start();
        try {
            grassSpawnerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

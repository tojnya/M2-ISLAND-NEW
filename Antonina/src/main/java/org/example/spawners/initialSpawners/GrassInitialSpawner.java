package org.example.spawners.initialSpawners;

import org.example.application.ApplicationContext;
import org.example.gamefield.GameField;
import org.example.organisms.grass.Grass;

import java.util.concurrent.ThreadLocalRandom;

public class GrassInitialSpawner implements InitialSpawner {
    private final GameField gameField = ApplicationContext.getInstance().getGameField();

    @Override
    public void run() {
        initialSpawn();
    }

    public void initialSpawn() {
        GameField.Cell[][] cells = gameField.getCells();
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                int grassCount = ThreadLocalRandom.current().nextInt(0, Grass.getMaxPerCell() + 1);
                cells[i][j].setGrass(grassCount);
            }
        }
        aggregate(cells);
    }

    public void aggregate(GameField.Cell[][] cells) {
        int allGrass = 0;
        for (GameField.Cell[] cell : cells) {
            for (GameField.Cell value : cell) {
                allGrass += value.getGrass();
            }
        }
        gameField.setAllGrass(allGrass);
    }
}

package org.example.spawners;

import org.example.application.ApplicationContext;
import org.example.gamefield.GameField;
import org.example.organisms.grass.Grass;

import java.util.concurrent.*;

public class GrassSpawner implements Spawner {
    private final GameField gameField = ApplicationContext.getInstance().getGameField();

    @Override
    public void run() {
        System.out.println("GrassSpawner");
        GameField.Cell[][] cells = gameField.getCells();
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                int prevCount = cells[i][j].getGrass();
                int spawnCount = ThreadLocalRandom.current().nextInt(0, Grass.getMaxPerCell() - prevCount + 1);
                cells[i][j].setGrass(prevCount + spawnCount);
                System.out.print(spawnCount + ", ");
            }
        }
    }

    @Override
    public void spawn() {
    }
}

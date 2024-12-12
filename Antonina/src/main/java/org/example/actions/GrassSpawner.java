package org.example.actions;

import org.example.entities.grass.Grass;
import org.example.gamefield.GameField;

import java.util.concurrent.ThreadLocalRandom;

public class GrassSpawner implements Runnable {
    private GameField gameField = GameField.getInstance();

    @Override
    public void run() {
        GameField.Cell[][] cells = gameField.getCells();
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                int grassCount = ThreadLocalRandom.current().nextInt(0, Grass.maxCount + 1);
                cells[i][j].setGrass(grassCount);
                System.out.print(cells[i][j].getX() + "_" + cells[i][j].getY() + ": " + cells[i][j].getGrass() + "🌿\t");
            }
            System.out.println();
        }
    }
}
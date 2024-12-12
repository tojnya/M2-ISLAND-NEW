package org.example.gamefield;

import org.example.entities.animals.Animal;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameField {
    private static GameField INSTANCE;
    private static Cell[][] cells;
    private static int width;
    private static int height;

    private GameField() {
    }

    public static GameField getInstance() {
        if (INSTANCE == null) {
            return new GameField();
        }
        return INSTANCE;
    }

    public GameField setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        initCells(width, height);
        return INSTANCE;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getCells() {
        return cells;
    }

    private void initCells(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public class Cell {
        private Map<Class<? extends Animal>, Set<? extends Animal>> animals;
        private int x;
        private int y;
        private Set<Cell> nextCells = new HashSet<>();
        private int grass;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Map<Class<? extends Animal>, Set<? extends Animal>> getAnimals() {
            return animals;
        }

        public void setAnimals(Map<Class<? extends Animal>, Set<? extends Animal>> animals) {
            this.animals = animals;
        }

        public int getGrass() {
            return grass;
        }

        public void setGrass(int grass) {
            this.grass = grass;
        }

        public Set<Cell> getNextCells() {
            if (this.x > 0) {
                nextCells.add(cells[x - 1][y]);
            }
            if (this.x < --width) {
                nextCells.add(cells[x + 1][y]);
            }
            if (this.y > 0) {
                nextCells.add(cells[x][y - 1]);
            }
            if (this.y < --height) {
                nextCells.add(cells[x][y + 1]);
            }
            return nextCells;
        }
    }
}

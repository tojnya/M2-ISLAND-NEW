package org.example.gamefield;

import org.example.entities.animals.Animal;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameField {
    private Cell[][] cells;
    private int width;
    private int height;

    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
        initGameField(width, height);
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

    private Cell[][] initGameField(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        return cells;
    }

    public class Cell {
        private Map<Class<? extends Animal>, Set<? extends Animal>> animals;
        private int x;
        private int y;
        private Set<Cell> nextCells = new HashSet<>();

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

        public Set<Cell> getNextCells() {
            if (this.x > 0) {                           // if not left border
                nextCells.add(cells[x - 1][y]);     // add the next cell on the left
            }
            if (this.x < --width) {                     // if not right border
                nextCells.add(cells[x + 1][y]);     // add the next cell on the right
            }
            if (this.y > 0) {                           // if not top border
                nextCells.add(cells[x][y - 1]);     // add the next cell on the top
            }
            if (this.y < --height) {                    // if not bottom border
                nextCells.add(cells[x][y + 1]);     // add the next cell on the bottom
            }
            return nextCells;
        }
    }
}

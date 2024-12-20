package org.example.gamefield;

import lombok.Getter;
import lombok.Setter;
import org.example.organisms.animals.Animal;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class GameField {
    private static GameField INSTANCE;
    private Cell[][] cells;
    private int width;
    private int height;
    private Map<Class<? extends Animal>, Set<Animal>> allAnimals;
    private int allGrass;

    private GameField() {
    }

    public static GameField getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameField();
        }
        return INSTANCE;
    }

    public void initWithDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = initCells(width, height);
    }

    private Cell[][] initCells(int width, int height) {
        Cell[][] cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        return cells;
    }

    @Getter
    @Setter
    public class Cell {
        private Map<Class<? extends Animal>, Set<Animal>> animals;
        private int x;
        private int y;
        private Set<Cell> nextCells = new HashSet<>();
        private int grass;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
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

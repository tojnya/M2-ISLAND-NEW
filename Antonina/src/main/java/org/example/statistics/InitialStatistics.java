package org.example.statistics;

import org.example.application.ApplicationContext;
import org.example.entities.animals.Animal;
import org.example.gamefield.GameField;

import java.util.Map;
import java.util.Set;

public class InitialStatistics implements Runnable, AbstractStatistics {
    private final GameField gameField = ApplicationContext.getInstance().getGameField();

    @Override
    public void run() {
        printStatistics();
    }

    public void printStatistics() {
        printGrass();
        printAnimals();
    }

    private void printGrass() {
        GameField.Cell[][] cells = gameField.getCells();
        System.out.println("Grass:");
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                System.out.print(cells[i][j].getGrass() + ", ");
            }
            System.out.println();
        }
    }

    private void printAnimals() {
        GameField.Cell[][] cells = gameField.getCells();
        System.out.println("Animals:");
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                Map<Class<? extends Animal>, Set<? extends Animal>> map = cells[i][j].getAnimals();
                System.out.print(cells[i][j].getX() + "_" + cells[i][j].getY() + ": ");
                for (Class<? extends Animal> animalClass : map.keySet()) {
                    System.out.print(animalClass.getSimpleName() + " - ");
                    System.out.print(map.get(animalClass).size()+ ", ");
                }
                System.out.println();
            }
        }
    }
}

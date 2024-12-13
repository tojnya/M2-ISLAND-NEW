package org.example.statistics;

import org.example.application.ApplicationContext;
import org.example.organisms.animals.Animal;
import org.example.gamefield.GameField;
import org.example.organisms.grass.Grass;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
        System.out.println("Grass");
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                System.out.print(cells[i][j].getGrass() + Grass.getEmoji() + " ");
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
                    System.out.print(getEmoji(animalClass) + " - ");
                    System.out.print(map.get(animalClass).size() + ", ");
                }
                System.out.println();
            }
        }
    }

    private String getEmoji(Class<? extends Animal> animalClass) {
        String emoji;
        try {
            Field field = animalClass.getDeclaredField("emoji");
            int mod = field.getModifiers();
            if (Modifier.isPrivate(mod)) {
                field.setAccessible(true);
            }
            emoji = (String) field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return emoji;
    }
}

package org.example.actions;

import org.example.entities.animals.Animal;
import org.example.entities.grass.Grass;
import org.example.gamefield.GameField;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalSpawner implements Runnable {
    private Set<Class<? extends Animal>> animalClasses = Animal.getInheritors();
    private Map<Class<? extends Animal>, Set<? extends Animal>> allAnimalsInCell = new HashMap();
    private GameField gameField = GameField.getInstance();

    @Override
    public void run() {
        GameField.Cell[][] cells = gameField.getCells();
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                System.out.println("Cell: " + cells[i][j].getX() + "_" + cells[i][j].getY());
                for (Class animalClass : animalClasses) {
                    allAnimalsInCell.put(animalClass, spawnAnimals(animalClass));
                }
                cells[i][j].setAnimals(allAnimalsInCell);
            }
        }
    }

    public Set<Animal> spawnAnimals(Class<? extends Animal> species) {
        Set<Animal> tAnimals = new HashSet<>();
        int animalCount;
        try {
            animalCount = ThreadLocalRandom.current().nextInt(0,
                    species.getDeclaredField("maxCount").getInt(species) + 1);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        System.out.println(species.getSimpleName() + ": " + animalCount + " instances.");

        try {
            Constructor<?> constructor = species.getConstructor();
            for (int i = 0; i < animalCount; i++) {
                Animal animal = (Animal) constructor.newInstance();
                tAnimals.add(animal);
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return tAnimals;
    }
}

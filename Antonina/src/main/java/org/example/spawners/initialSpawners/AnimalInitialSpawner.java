package org.example.spawners.initialSpawners;

import org.example.application.ApplicationContext;
import org.example.organisms.animals.Animal;
import org.example.gamefield.GameField;
import org.example.settings.Settings;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalInitialSpawner implements InitialSpawner {
    private final static Set<Class<? extends Animal>> animalClasses = Settings.getInstance().getAnimals();
    private final GameField gameField = ApplicationContext.getInstance().getGameField();

    @Override
    public void run() {
        initialSpawn();
    }

    @Override
    public void initialSpawn() {
        GameField.Cell[][] cells = gameField.getCells();
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                Map<Class<? extends Animal>, Set<Animal>> cellAnimals = new HashMap<>();
                for (Class<? extends Animal> animalClass : animalClasses) {
                    cellAnimals.put(animalClass, spawnOfClass(animalClass, i, j));
                }
                cells[i][j].setAnimals(cellAnimals);
            }
        }
        aggregate(cells);
    }

    private Set<Animal> spawnOfClass(Class<? extends Animal> species, int x, int y) {
        Set<Animal> tAnimals = new HashSet<>();

        try {
            Constructor<?> constructor = species.getConstructor();
            for (int i = 0; i < getMaxPerCellValue(species); i++) {
                Animal animal = (Animal) constructor.newInstance();
                animal.assignCoordinates(x, y);
                tAnimals.add(animal);
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return tAnimals;
    }

    public void aggregate(GameField.Cell[][] cells) {
        Map<Class<? extends Animal>, Set<Animal>> allAnimals = new HashMap<>();
        for (GameField.Cell[] cell : cells) {
            for (GameField.Cell value : cell) {
                Map<Class<? extends Animal>, Set<Animal>> animalsOnCell = value.getAnimals();

                for (Map.Entry<Class<? extends Animal>, Set<Animal>> entry : animalsOnCell.entrySet()) {
                    Class<? extends Animal> animalClass = entry.getKey();
                    Set<? extends Animal> animalSet = entry.getValue();

                    allAnimals.computeIfAbsent(animalClass, _ -> new HashSet<>()).addAll(animalSet);
                }
            }
        }
        gameField.setAllAnimals(allAnimals);
    }

    private int getMaxPerCellValue(Class<? extends Animal> species) {
        int animalCount;
        try {
            Field field = species.getDeclaredField("maxPerCell");
            int mod = field.getModifiers();
            if (Modifier.isPrivate(mod)) {
                field.setAccessible(true);
            }
            animalCount = ThreadLocalRandom.current().nextInt(0,
                    field.getInt(null) + 1);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return animalCount;
    }
}

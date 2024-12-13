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
        System.out.println("AnimalInitialSpawner");
        GameField.Cell[][] cells = gameField.getCells();
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                Map<Class<? extends Animal>, Set<? extends Animal>> cellAnimals = new HashMap<>();
                for (Class<? extends Animal> animalClass : animalClasses) {
                    cellAnimals.put(animalClass, spawnOfClass(animalClass));
                }
                cells[i][j].setAnimals(cellAnimals);
            }
        }
    }

    public Set<Animal> spawnOfClass(Class<? extends Animal> species) {
        Set<Animal> tAnimals = new HashSet<>();
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

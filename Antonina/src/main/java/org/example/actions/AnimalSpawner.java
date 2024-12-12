package org.example.actions;

import org.example.entities.animals.Animal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalSpawner implements Runnable {
    private Set<Class<? extends Animal>> animalClasses = Animal.getInheritors();
    private Map<Class<? extends Animal>, Set<Animal>> allAnimals = new HashMap();

    @Override
    public void run() {
        for (Class animalClass : animalClasses) {
            allAnimals.put(animalClass, spawnAnimals(animalClass));
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

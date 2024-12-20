package org.example.statistics;

import org.example.application.ApplicationContext;
import org.example.organisms.animals.Animal;

import java.util.Map;
import java.util.Set;

public class Statistics implements Runnable, AbstractStatistics {
    @Override
    public void run() {
        printStatistics();
    }

    @Override
    public void printStatistics() {
        printAnimals();
        printGrass();
    }

    public static void printAnimals() {
        Map<Class<? extends Animal>, Set<Animal>> allAnimals = ApplicationContext.getInstance().getGameField().getAllAnimals();
        for (Class<? extends Animal> animalClass : allAnimals.keySet()) {
            System.out.printf("%s: %d, %n", animalClass.getSimpleName(), allAnimals.get(animalClass).size());
        }
    }

    public static void printGrass() {
        System.out.printf("Grass: %d. %n", ApplicationContext.getInstance().getGameField().getAllGrass());
        System.out.println();
    }
}
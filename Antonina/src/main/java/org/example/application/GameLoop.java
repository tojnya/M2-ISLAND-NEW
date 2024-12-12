package org.example.application;

import org.example.initialSpawners.AnimalInitialSpawner;
import org.example.initialSpawners.GrassInitialSpawner;
import org.example.statistics.InitialStatistics;

public class GameLoop implements Runnable {
    private final ApplicationContext context;

    public GameLoop(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        GrassInitialSpawner grassInitialSpawner = context.getGrassSpawner();
        AnimalInitialSpawner animalInitialSpawner = context.getAnimalSpawner();
        Thread grass = new Thread(grassInitialSpawner);
        Thread animal = new Thread(animalInitialSpawner);
        grass.start();
        animal.start();
        try {
            grass.join();
            animal.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InitialStatistics initialStatistics = context.getStatistics();
        Thread initial = new Thread(initialStatistics);
        initial.start();
    }
}

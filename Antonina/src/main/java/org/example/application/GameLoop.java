package org.example.application;

import org.example.settings.Settings;
import org.example.spawners.GrassSpawner;
import org.example.spawners.initialSpawners.AnimalInitialSpawner;
import org.example.spawners.initialSpawners.GrassInitialSpawner;
import org.example.statistics.InitialStatistics;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoop implements Runnable {
    private final ApplicationContext context;

    public GameLoop(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        initGrass();
        initAnimals();
        initStats();
        spawnGrass();
        initStats();
    }

    private void initGrass(){
        GrassInitialSpawner grassInitialSpawner = context.getGrassSpawner();
        Thread initGrass = new Thread(grassInitialSpawner);
        initGrass.start();
        try {
            initGrass.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initAnimals(){
        AnimalInitialSpawner animalInitialSpawner = context.getAnimalSpawner();
        Thread initAnimal = new Thread(animalInitialSpawner);
        initAnimal.start();
        try {
            initAnimal.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initStats(){
        InitialStatistics initialStatistics = context.getStatistics();
        Thread initStats = new Thread(initialStatistics);
        initStats.start();
        try {
            initStats.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void spawnGrass(){
        GrassSpawner grassSpawner = new GrassSpawner();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(grassSpawner,
                0, Settings.getInstance().getTickDurationInMilliseconds(), TimeUnit.MILLISECONDS);

        // todo edit:
        try {
            Thread.sleep(2000);
            executorService.shutdown();
            Thread.sleep(100);
            System.out.println(executorService.isTerminated());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

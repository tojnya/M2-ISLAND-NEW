package org.example.application;

import org.example.settings.Settings;

import java.util.concurrent.*;

public class GameLoop implements Runnable {
    private final ApplicationContext context;

    public GameLoop(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        initial();
        scheduled();
    }

    private void initial() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable grassInitialSpawner = context.getGrassInitialSpawner();
        Runnable animalInitialSpawner = context.getAnimalInitialSpawner();
        executor.submit(grassInitialSpawner);
        executor.submit(animalInitialSpawner);

        executor.shutdown();
        try {
            while (!executor.awaitTermination(50, TimeUnit.MILLISECONDS)) {
                System.out.println("Waiting for initialisation to complete...");
            }
            System.out.println("Initialisation completed.");
        } catch (InterruptedException e) {
            System.err.println("Initialisation interrupted");
            executor.shutdownNow();
        }
        context.getInitialStatistics().printStatistics();
    }

    private void scheduled() {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(3);
        Runnable grassSpawner = context.getGrassSpawner();
        Runnable animalSpawner = context.getAnimalSpawner();
        Runnable statistics = context.getStatistics();

        long gameTick = Settings.getInstance().getTickDurationInMilliseconds();
        scheduler.scheduleAtFixedRate(grassSpawner, 0, gameTick, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(animalSpawner, 0, gameTick, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(statistics, 0, gameTick, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(3000);
            scheduler.shutdown();
            Thread.sleep(100);
            System.out.println("End of simulation: " + scheduler.isTerminated());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.application;

import lombok.Getter;
import org.example.initialSpawners.AnimalInitialSpawner;
import org.example.initialSpawners.GrassInitialSpawner;
import org.example.statistics.InitialStatistics;
import org.example.gamefield.GameField;

@Getter
public class ApplicationContext {

    private static ApplicationContext INSTANCE;
    private final GameField gameField = GameField.getInstance();
    private GrassInitialSpawner grassSpawner;
    private AnimalInitialSpawner animalSpawner;
    private InitialStatistics statistics;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (INSTANCE == null) {
            return new ApplicationContext();
        }
        return INSTANCE;
    }

    public void initialize() {
        this.gameField.initWithDimensions(100, 20);
        this.grassSpawner = new GrassInitialSpawner();
        this.animalSpawner = new AnimalInitialSpawner();
        this.statistics = new InitialStatistics();
    }
}

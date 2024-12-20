package org.example.application;

import lombok.Getter;
import org.example.settings.Settings;
import org.example.spawners.AnimalSpawner;
import org.example.spawners.GrassSpawner;
import org.example.spawners.initialSpawners.AnimalInitialSpawner;
import org.example.spawners.initialSpawners.GrassInitialSpawner;
import org.example.statistics.InitialStatistics;
import org.example.gamefield.GameField;
import org.example.statistics.Statistics;

@Getter
public class ApplicationContext {

    private static ApplicationContext INSTANCE;
    private final GameField gameField = GameField.getInstance();
    private final Settings settings = Settings.getInstance();
    private GrassInitialSpawner grassInitialSpawner;
    private AnimalInitialSpawner animalInitialSpawner;
    private InitialStatistics initialStatistics;
    private GrassSpawner grassSpawner;
    private AnimalSpawner animalSpawner;
    private Statistics statistics;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (INSTANCE == null) {
            return new ApplicationContext();
        }
        return INSTANCE;
    }

    public void initialize() {
        this.settings.setDefaultSettings();
        this.gameField.initWithDimensions(settings.getGameFieldWidth(), settings.getGameFieldHeight());
        this.grassInitialSpawner = new GrassInitialSpawner();
        this.animalInitialSpawner = new AnimalInitialSpawner();
        this.initialStatistics = new InitialStatistics();
        this.grassSpawner = new GrassSpawner();
        this.animalSpawner = new AnimalSpawner();
        this.statistics = new Statistics();
    }
}

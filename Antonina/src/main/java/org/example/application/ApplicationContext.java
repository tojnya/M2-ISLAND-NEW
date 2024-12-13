package org.example.application;

import lombok.Getter;
import org.example.settings.Settings;
import org.example.spawners.initialSpawners.AnimalInitialSpawner;
import org.example.spawners.initialSpawners.GrassInitialSpawner;
import org.example.statistics.InitialStatistics;
import org.example.gamefield.GameField;

@Getter
public class ApplicationContext {

    private static ApplicationContext INSTANCE;
    private final GameField gameField = GameField.getInstance();
    private final Settings settings = Settings.getInstance();
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
        this.settings.setDefaultSettings();
        this.gameField.initWithDimensions(settings.getGameFieldWidth(), settings.getGameFieldHeight());
        this.grassSpawner = new GrassInitialSpawner();
        this.animalSpawner = new AnimalInitialSpawner();
        this.statistics = new InitialStatistics();
    }
}

package org.example.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.animals.Animal;
import org.example.entities.animals.herbivore.Horse;
import org.example.entities.animals.herbivore.Rabbit;
import org.example.entities.animals.predator.Snake;
import org.example.entities.animals.predator.Wolf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Settings {
    private static Settings INSTANCE;
    private Set<Class<? extends Animal>> animals;
    private long tickDurationInMilliseconds;
    private int gameFieldWidth;
    private int gameFieldHeight;
    private double grassWeight;
    private int grassMaxPerCell;

    private Settings() {
    }

    public static Settings getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Settings();
        }
        return INSTANCE;
    }

    public void setDefaultSettings() {
        this.animals = initAnimals();
        this.tickDurationInMilliseconds = 1000;
        this.gameFieldWidth = 100;
        this.gameFieldHeight = 20;
        this.grassWeight = 1.0;
        this.grassMaxPerCell = 200;
        setDefaultAnimalSettings();
    }

    private static Set<Class<? extends Animal>> initAnimals() {
        Set<Class<? extends Animal>> set = new HashSet<>();
        // todo: get classes from directory
        set.add(Wolf.class);
        set.add(Snake.class);
        set.add(Rabbit.class);
        set.add(Horse.class);
        return set;
    }

    private void setDefaultAnimalSettings() {
        Wolf settingsWolf = new Wolf();
        Snake settingsSnake = new Snake();
        Rabbit settingsRabbit = new Rabbit();
        Horse settingsHorse = new Horse();

        settingsWolf.initSettings(new AnimalSettings(50.0, 30, 3, 8.0,
                new HashMap<>() {{
                    put(Horse.class, 0.15);
                    put(Rabbit.class, 0.6);
                }}));
        settingsSnake.initSettings(new AnimalSettings(15.0, 30, 1, 3.0,
                new HashMap<>() {{
                    put(Rabbit.class, 0.2);
                }}));
        settingsRabbit.initSettings(new AnimalSettings(2.0, 150, 2, 0.45,
                new HashMap<>()));
        settingsHorse.initSettings(new AnimalSettings(400.0, 20, 4, 60.0,
                new HashMap<>()));
    }
}

package org.example.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.organisms.animals.Animal;
import org.example.organisms.animals.herbivore.*;
import org.example.organisms.animals.omnivore.Boar;
import org.example.organisms.animals.omnivore.Duck;
import org.example.organisms.animals.omnivore.Mouse;
import org.example.organisms.animals.predator.*;
import org.example.organisms.grass.Grass;

import java.util.*;

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
    private double costOfAnimalAction;
    private double animalFullnessOnStart;

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
        this.costOfAnimalAction = 0.1;          // from 0 to 1
        this.animalFullnessOnStart = 0.75;      // from 0 to 1
        setDefaultGrassSettings();
        setDefaultAnimalSettings();
    }

    private static Set<Class<? extends Animal>> initAnimals() {
        Set<Class<? extends Animal>> set = new HashSet<>();
        // predators
        set.add(Bear.class);
        set.add(Eagle.class);
        set.add(Fox.class);
        set.add(Snake.class);
        set.add(Wolf.class);
        // herbivores
        set.add(Boar.class);
        set.add(Buffalo.class);
        set.add(Caterpillar.class);
        set.add(Deer.class);
        set.add(Duck.class);
        set.add(Goat.class);
        set.add(Horse.class);
        set.add(Mouse.class);
        set.add(Rabbit.class);
        set.add(Sheep.class);

        return set;
    }

    private void setDefaultGrassSettings() {
        Grass settingsGrass = new Grass();
        settingsGrass.initSettings(new GrassSettings("\uD83C\uDF3F", 1.0, 200));
    }

    private void setDefaultAnimalSettings() {
        // predators
        Bear settingsBear = new Bear(true);
        Eagle settingsEagle = new Eagle(true);
        Fox settingsFox = new Fox(true);
        Snake settingsSnake = new Snake(true);
        Wolf settingsWolf = new Wolf(true);
        // herbivores
        Boar settingsBoar = new Boar(true);
        Buffalo settingsBuffalo = new Buffalo(true);
        Caterpillar settingsCaterpillar = new Caterpillar(true);
        Deer settingsDeer = new Deer(true);
        Duck settingsDuck = new Duck(true);
        Goat settingsGoat = new Goat(true);
        Horse settingsHorse = new Horse(true);
        Mouse settingsMouse = new Mouse(true);
        Rabbit settingsRabbit = new Rabbit(true);
        Sheep settingsSheep = new Sheep(true);


        // predators
        settingsBear.initSettings(new AnimalSettings("\uD83D\uDC3B", 500.0, 5, 2, 80.0,
                new HashMap<>() {{
                    put(Snake.class, 0.8);
                    put(Horse.class, 0.4);
                    put(Rabbit.class, 0.8);
                    put(Deer.class, 0.8);
                    put(Mouse.class, 0.9);
                    put(Goat.class, 0.7);
                    put(Sheep.class, 0.7);
                    put(Boar.class, 0.5);
                    put(Buffalo.class, 0.2);
                    put(Duck.class, 0.1);
                }}));
        settingsEagle.initSettings(new AnimalSettings("\uD83E\uDD85", 6.0, 20, 3, 1.0,
                new HashMap<>() {{
                    put(Fox.class, 0.1);
                    put(Rabbit.class, 0.9);
                    put(Mouse.class, 0.9);
                    put(Duck.class, 0.8);
                }}));
        settingsFox.initSettings(new AnimalSettings("\uD83E\uDD8A", 8.0, 30, 2, 2.0,
                new HashMap<>() {{
                    put(Rabbit.class, 0.7);
                    put(Mouse.class, 0.9);
                    put(Duck.class, 0.6);
                    put(Caterpillar.class, 0.4);
                }}));
        settingsSnake.initSettings(new AnimalSettings("\uD83D\uDC0D", 15.0, 30, 1, 3.0,
                new HashMap<>() {{
                    put(Fox.class, 0.15);
                    put(Rabbit.class, 0.2);
                    put(Mouse.class, 0.4);
                    put(Duck.class, 0.1);
                }}));
        settingsWolf.initSettings(new AnimalSettings("\uD83D\uDC3A", 50.0, 30, 3, 8.0,
                new HashMap<>() {{
                    put(Horse.class, 0.1);
                    put(Deer.class, 0.15);
                    put(Rabbit.class, 0.6);
                    put(Mouse.class, 0.8);
                    put(Goat.class, 0.6);
                    put(Sheep.class, 0.7);
                    put(Boar.class, 0.15);
                    put(Buffalo.class, 0.1);
                    put(Duck.class, 0.4);
                }}));

        // herbivores
        settingsBoar.initSettings(new AnimalSettings("\uD83D\uDC17", 400.0, 50, 2, 50.0,
                new HashMap<>() {{
                    put(Mouse.class, 0.5);
                    put(Caterpillar.class, 0.9);
                }}));
        settingsBuffalo.initSettings(new AnimalSettings("\uD83D\uDC03", 700.0, 10, 3, 100.0,
                new HashMap<>()));
        settingsCaterpillar.initSettings(new AnimalSettings("\uD83D\uDC1B", 0.01, 1000, 0, 0,
                new HashMap<>()));
        settingsDeer.initSettings(new AnimalSettings("\uD83E\uDD8C", 300.0, 20, 4, 50.0,
                new HashMap<>()));
        settingsDuck.initSettings(new AnimalSettings("\uD83E\uDD86", 1.0, 200, 4, 0.15,
                new HashMap<>() {{
                    put(Caterpillar.class, 0.9);
                }}));
        settingsGoat.initSettings(new AnimalSettings("\uD83D\uDC10", 60.0, 140, 3, 10.0,
                new HashMap<>()));
        settingsHorse.initSettings(new AnimalSettings("\uD83D\uDC0E", 400.0, 20, 4, 60.0,
                new HashMap<>()));
        settingsMouse.initSettings(new AnimalSettings("\uD83D\uDC01", 0.05, 500, 1, 0.01,
                new HashMap<>() {{
                    put(Caterpillar.class, 0.9);
                }}));
        settingsRabbit.initSettings(new AnimalSettings("\uD83D\uDC07", 2.0, 150, 2, 0.45,
                new HashMap<>()));
        settingsSheep.initSettings(new AnimalSettings("\uD83D\uDC11", 70.0, 140, 3, 15.0,
                new HashMap<>()));
    }
}

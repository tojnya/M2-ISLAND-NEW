package org.example.organisms.animals.herbivore;

import lombok.Getter;
import org.example.organisms.Organism;
import org.example.settings.AnimalSettings;

import java.util.Map;

public class Horse extends Herbivore {
    @Getter
    private static String emoji;
    @Getter
    private static double weight;
    @Getter
    private static int maxPerCell;
    @Getter
    private static int maxSpeed;
    @Getter
    private static double maxFullness;
    @Getter
    private static Map<Class<? extends Organism>, Double> chanceOfHunt;

    @Override
    public void initSettings(AnimalSettings settings) {
        emoji = settings.getEmoji();
        weight = settings.getWeight();
        maxPerCell = settings.getMaxPerCell();
        maxSpeed = settings.getMaxSpeed();
        maxFullness = settings.getMaxFullness();
        chanceOfHunt = settings.getChanceOfHunt();
    }

    @Override
    public void move() {
    }

    @Override
    public void eat() {
    }

    @Override
    public void reproduce() {
    }

    @Override
    public void die() {
    }
}

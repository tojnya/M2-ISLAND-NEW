package org.example.entities.animals.herbivore;

import org.example.entities.animals.Animal;
import org.example.settings.AnimalSettings;

import java.util.Map;

public class Rabbit extends Herbivore {
    private static double weight;
    private static int maxPerCell;
    private static int maxSpeed;
    private static double maxFullness;
    private static Map<Class<? extends Animal>, Double> chanceOfHunt;

    @Override
    public void initSettings(AnimalSettings settings) {
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

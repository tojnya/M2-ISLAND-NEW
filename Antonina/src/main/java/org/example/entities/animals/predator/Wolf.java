package org.example.entities.animals.predator;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.animals.Animal;
import org.example.settings.AnimalSettings;

import java.util.Map;

@Getter
@Setter
public class Wolf extends Predator {
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

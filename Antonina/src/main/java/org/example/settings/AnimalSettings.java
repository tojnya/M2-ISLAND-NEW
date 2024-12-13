package org.example.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.animals.Animal;

import java.util.Map;

@Getter
@Setter
public class AnimalSettings {
    private double weight;
    private int maxPerCell;
    private int maxSpeed;
    private double maxFullness;
    private Map<Class<? extends Animal>, Double> chanceOfHunt;

    public AnimalSettings(double weight, int maxPerCell, int maxSpeed, double maxFullness,
                          Map<Class<? extends Animal>, Double> chanceOfHunt) {
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.maxSpeed = maxSpeed;
        this.maxFullness = maxFullness;
        this.chanceOfHunt = chanceOfHunt;
    }
}

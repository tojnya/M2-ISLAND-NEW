package org.example.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.organisms.animals.Animal;

import java.util.Map;

@Getter
@Setter
public class AnimalSettings {
    private String emoji;
    private double weight;
    private int maxPerCell;
    private int maxSpeed;
    private double maxFullness;
    private Map<Class<? extends Animal>, Double> chanceOfHunt;

    public AnimalSettings(String emoji, double weight, int maxPerCell, int maxSpeed, double maxFullness,
                          Map<Class<? extends Animal>, Double> chanceOfHunt) {
        this.emoji = emoji;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.maxSpeed = maxSpeed;
        this.maxFullness = maxFullness;
        this.chanceOfHunt = chanceOfHunt;
    }
}

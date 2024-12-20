package org.example.organisms.animals;

import lombok.Getter;
import lombok.Setter;
import org.example.gamefield.GameField;
import org.example.organisms.Organism;
import org.example.organisms.animals.herbivore.Herbivore;
import org.example.organisms.animals.omnivore.Omnivore;
import org.example.organisms.animals.predator.Predator;
import org.example.organisms.interfaces.*;
import org.example.settings.Settings;

import java.util.Map;

@Getter
@Setter
public abstract class Animal extends Organism implements Movable, Reproducible, Mortal, Eating,
        AnimalSettingsInterface {
    public int xCoordinate;
    public int yCoordinate;
    public double currentFullness;
    public double weight;
    public boolean isAlive;
    public Map<Class<? extends Organism>, Double> chanceOfHunt;
    public double maxFullness;

    public void assignCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    @Override
    public void eat() {
        switch (this) {
            case Predator predator -> predator.eatAnimal();
            case Herbivore herbivore -> herbivore.eatGrass();
            case Omnivore omnivore -> omnivore.eatAnything();
            default -> throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    @Override
    public void die() {
        GameField.Cell[][] cells = GameField.getInstance().getCells();
        cells[xCoordinate][yCoordinate].getAnimals().get(this.getClass()).remove(this);
    }

    @Override
    public void reproduce() {
        // reproduce();
        loseFullness();
    }

    @Override
    public void move() {
        // move
        loseFullness();
    }

    private void loseFullness() {
        this.currentFullness -= (maxFullness * Settings.getInstance().getCostOfAnimalAction());
    }
}

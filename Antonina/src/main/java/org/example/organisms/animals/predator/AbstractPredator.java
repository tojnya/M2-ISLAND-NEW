package org.example.organisms.animals.predator;

import org.example.gamefield.GameField;
import org.example.organisms.animals.Animal;
import org.example.actions.AnimalActions;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class AbstractPredator extends Animal implements Predator {

    @Override
    public void eat() {
        GameField.Cell[][] cells = GameField.getInstance().getCells();
        Map<Class<? extends Animal>, Set<Animal>> cellAnimals = cells[xCoordinate][yCoordinate].getAnimals();
        Map<Class<? extends Animal>, Set<Animal>> whomCanEatOnCell = cellAnimals.entrySet().stream()
                .filter(entry -> chanceOfHunt.containsKey(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Class<? extends Animal> randomClass = AnimalActions.selectRandomClass(whomCanEatOnCell);
        if (randomClass == null) {
            super.eat();
            return;
        }

        Animal randomPrey = AnimalActions.selectRandomAnimal(whomCanEatOnCell, randomClass);
        if (randomPrey == null) {
            super.eat();
            return;
        }

        if (ThreadLocalRandom.current().nextDouble(0.0, 1.0) > chanceOfHunt.get(randomClass)) {
            super.eat();
        } else {
            randomPrey.die();
            currentFullness += randomPrey.getWeight();
            if (currentFullness > maxFullness) {
                currentFullness = maxFullness;
            }
        }
    }
}
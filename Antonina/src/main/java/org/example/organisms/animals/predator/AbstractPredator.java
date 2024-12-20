package org.example.organisms.animals.predator;

import org.example.gamefield.GameField;
import org.example.organisms.animals.Animal;
import org.example.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class AbstractPredator extends Animal implements Predator {

    @Override
    public void eatAnimal() {
        GameField.Cell[][] cells = GameField.getInstance().getCells();
        Map<Class<? extends Animal>, Set<Animal>> cellAnimals = cells[xCoordinate][yCoordinate].getAnimals();
        Map<Class<? extends Animal>, Set<Animal>> whomCanEatOnCell = cellAnimals.entrySet().stream()
                .filter(entry -> chanceOfHunt.containsKey(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Class<? extends Animal> randomClass = selectRandomClass(whomCanEatOnCell);
        if (randomClass == null) {
            this.currentFullness -= (maxFullness * Settings.getInstance().getCostOfAnimalAction());
            return;
        }

        double realChance = chanceOfHunt.get(randomClass);

        Animal randomPrey = selectRandomAnimal(whomCanEatOnCell, randomClass);
        if (randomPrey == null) {
            this.currentFullness -= (maxFullness * Settings.getInstance().getCostOfAnimalAction());
            return;
        }

        double randomChance = ThreadLocalRandom.current().nextDouble(0.0, 1.0);

        if (randomChance < realChance) {
            randomPrey.die();
            this.currentFullness += randomPrey.getWeight();
            if (this.currentFullness > maxFullness) {
                this.currentFullness = maxFullness;
            }
        } else {
            this.currentFullness -= (maxFullness * Settings.getInstance().getCostOfAnimalAction());
        }
    }

    private Class<? extends Animal> selectRandomClass(Map<Class<? extends Animal>, Set<Animal>> map) {
        List<Class<? extends Animal>> classes = new ArrayList<>(map.keySet());
        if (classes.isEmpty()) {
            return null;
        }
        return classes.get(ThreadLocalRandom.current().nextInt(classes.size()));
    }

    private Animal selectRandomAnimal(Map<Class<? extends Animal>, Set<Animal>> map, Class<? extends Animal> aClass) {
        Set<Animal> randomSet = map.get(aClass);
        if (randomSet.isEmpty()) {
            return null;
        }
        List<Animal> randomList = new ArrayList<>(randomSet);

        return randomList.get(ThreadLocalRandom.current().nextInt(randomList.size()));
    }
}
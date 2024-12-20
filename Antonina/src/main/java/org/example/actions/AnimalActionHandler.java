package org.example.actions;

import org.example.application.ApplicationContext;
import org.example.organisms.animals.Animal;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalActionHandler implements Runnable {

    @Override
    public void run() {
        Map<Class<? extends Animal>, Set<Animal>> allAnimals = ApplicationContext.getInstance().getGameField().getAllAnimals();

        allAnimals.values().forEach(animalSet -> animalSet.forEach(animal -> {
            switch (Actions.getRandomAction()) {
                case REPRODUCE -> animal.reproduce();
                case EAT -> animal.eat();
                case MOVE -> animal.move();
            }
        }));
    }

    private enum Actions {
        EAT, MOVE, REPRODUCE;

        private static Actions getRandomAction() {
            Actions[] actions = values();
            return actions[ThreadLocalRandom.current().nextInt(0, actions.length)];
        }
    }
}

package org.example.actions;

import org.example.entities.animals.Animal;

import java.util.Map;
import java.util.Set;

public class Statistics {
    private Map<Class<? extends Animal>, Set<? extends Animal>> animals;

    public Map<Class<? extends Animal>, Set<? extends Animal>> getAnimals() {
        return animals;
    }
}

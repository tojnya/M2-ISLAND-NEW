package org.example.actions;

import org.example.organisms.animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public final class AnimalActions {

    private AnimalActions(){
    }

    public static Class<? extends Animal> selectRandomClass(Map<Class<? extends Animal>, Set<Animal>> map) {
        List<Class<? extends Animal>> classes = new ArrayList<>(map.keySet());
        if (classes.isEmpty()) {
            return null;
        }
        return classes.get(ThreadLocalRandom.current().nextInt(classes.size()));
    }

    public static Animal selectRandomAnimal(Map<Class<? extends Animal>, Set<Animal>> map, Class<? extends Animal> aClass) {
        Set<Animal> randomSet = map.get(aClass);
        if (randomSet.isEmpty()) {
            return null;
        }
        List<Animal> randomList = new ArrayList<>(randomSet);
        return randomList.get(ThreadLocalRandom.current().nextInt(randomList.size()));
    }
}

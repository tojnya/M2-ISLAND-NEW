package org.example.entities.animals;

import org.example.entities.animals.herbivore.Horse;
import org.example.entities.animals.herbivore.Rabbit;
import org.example.entities.animals.predator.Snake;
import org.example.entities.animals.predator.Wolf;
import org.example.entities.interfaces.Eating;
import org.example.entities.interfaces.Mortal;
import org.example.entities.interfaces.Movable;
import org.example.entities.interfaces.Reproducible;

import java.util.HashSet;
import java.util.Set;

public abstract class Animal implements Movable, Eating, Reproducible, Mortal {
    private static final Set<Class<? extends Animal>> inheritors = initInheritors();

    private static Set<Class<? extends Animal>> initInheritors() {
        Set<Class<? extends Animal>> set = new HashSet<>();
        set.add(Wolf.class);
        set.add(Snake.class);
        set.add(Rabbit.class);
        set.add(Horse.class);
        return set;
    }

    public static Set<Class<? extends Animal>> getInheritors() {
        return new HashSet<>(inheritors);
    }
}

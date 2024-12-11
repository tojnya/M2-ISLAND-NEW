package org.example.entities.animals.predator;

public class Wolf extends Predator {
    public static final int MAX_COUNT = 30;
    public final double WEIGHT = 50;
    public final double FULLNESS = 8;
    public final int MAX_SPEED = 3;

    @Override
    public void move() {
        System.out.println("Wolf moved.");
    }

    @Override
    public void eat() {
        System.out.println("Wolf ate.");
    }

    @Override
    public void reproduce() {
        System.out.println("Wolf reproduced.");
    }

    @Override
    public void die() {
        System.out.println("Wolf died.");
    }
}

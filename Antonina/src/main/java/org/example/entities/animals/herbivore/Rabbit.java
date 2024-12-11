package org.example.entities.animals.herbivore;

public class Rabbit extends Herbivore {

    @Override
    public void move() {
        System.out.println("Rabbit moved.");
    }

    @Override
    public void eat() {
        System.out.println("Rabbit ate.");
    }

    @Override
    public void reproduce() {
        System.out.println("Rabbit reproduced.");
    }

    @Override
    public void die() {
        System.out.println("Rabbit died.");
    }
}

package org.example.entities.grass;

import org.example.entities.interfaces.Mortal;
import org.example.entities.interfaces.Reproducible;

public class Grass implements Reproducible, Mortal {
    @Override
    public void reproduce() {
        System.out.println("Grass grew.");
    }

    @Override
    public void die() {
        System.out.println("Grass is eaten.");
    }
}

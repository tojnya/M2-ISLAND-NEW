package org.example.entities.grass;

import org.example.entities.interfaces.Mortal;
import org.example.entities.interfaces.Reproducible;

public class Grass implements Reproducible, Mortal {
    public static int maxCount = 200;
    public double weight = 1.0;

    @Override
    public void reproduce() {
        System.out.println("Grass grew.");
    }

    @Override
    public void die() {
        System.out.println("Grass is eaten.");
    }
}

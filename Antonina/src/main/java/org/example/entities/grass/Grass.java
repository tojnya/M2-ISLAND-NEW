package org.example.entities.grass;

import org.example.entities.interfaces.Mortal;
import org.example.entities.interfaces.Reproducible;
import org.example.settings.Settings;

public class Grass implements Reproducible, Mortal {
    public static int maxCount = Settings.getInstance().getGrassMaxPerCell();
    public double weight = Settings.getInstance().getGrassWeight();

    @Override
    public void reproduce() {
    }

    @Override
    public void die() {
    }
}

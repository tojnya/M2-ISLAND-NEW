package org.example.organisms.animals.herbivore;

import org.example.gamefield.GameField;
import org.example.organisms.animals.Animal;
import org.example.organisms.interfaces.Eating;
import org.example.settings.Settings;

public abstract class AbstractHerbivore extends Animal implements Eating {

    @Override
    public void eat() {
        GameField.Cell[][] cells = GameField.getInstance().getCells();
        int grass = cells[xCoordinate][yCoordinate].getGrass();
        double neededToGetFull = (getMaxFullness() - getCurrentFullness()) / Settings.getInstance().getGrassWeight();
        if (grass > 0) {
            if (neededToGetFull >= grass) {
                setCurrentFullness(getCurrentFullness() + neededToGetFull);
                cells[xCoordinate][yCoordinate].setGrass(0);
            } else if (neededToGetFull < grass) {
                setCurrentFullness(getMaxFullness());
                cells[xCoordinate][yCoordinate].setGrass((int) (grass - neededToGetFull));
            }
        } else {
            super.eat();
        }
    }
}

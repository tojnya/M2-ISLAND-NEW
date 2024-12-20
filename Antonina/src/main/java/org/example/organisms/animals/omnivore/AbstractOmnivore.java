package org.example.organisms.animals.omnivore;

import org.example.gamefield.GameField;
import org.example.organisms.animals.predator.AbstractPredator;
import org.example.settings.Settings;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractOmnivore extends AbstractPredator {
    @Override
    public void eat() {
        int eatOption = ThreadLocalRandom.current().nextInt(0,2);
        if (eatOption == 0) {
            eatGrass();
        } else {
            super.eat();
        }
    }

    private void eatGrass(){
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
            this.currentFullness -= (maxFullness * Settings.getInstance().getCostOfAnimalAction());
        }
    }
}

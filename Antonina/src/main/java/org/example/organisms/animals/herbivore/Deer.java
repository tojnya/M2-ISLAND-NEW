package org.example.organisms.animals.herbivore;

import lombok.Getter;
import lombok.Setter;
import org.example.settings.AnimalSettings;
import org.example.settings.Settings;

@Getter
@Setter
public class Deer extends AbstractHerbivore {
    @Getter
    private static AnimalSettings settings;
    @Getter
    private static String emoji;
    @Getter
    private static int maxPerCell;
    @Getter
    private static int maxSpeed;

    public Deer(boolean settings) {
    }

    public Deer() {
        this.isAlive = true;
        this.currentFullness = maxFullness * Settings.getInstance().getAnimalFullnessOnStart();
        this.weight = settings.getWeight();
        this.chanceOfHunt = settings.getChanceOfHunt();
        this.maxFullness = settings.getMaxFullness();
    }

    @Override
    public void initSettings(AnimalSettings animalSettings) {
        settings = animalSettings;
        emoji = settings.getEmoji();
        maxPerCell = settings.getMaxPerCell();
        maxSpeed = settings.getMaxSpeed();
    }
}

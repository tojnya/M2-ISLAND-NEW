package org.example.organisms.animals.predator;

import lombok.Getter;
import lombok.Setter;
import org.example.settings.AnimalSettings;
import org.example.settings.Settings;

@Getter
@Setter
public class Fox extends AbstractPredator {
    @Getter
    private static AnimalSettings settings;
    @Getter
    private static String emoji;
    @Getter
    private static int maxPerCell;
    @Getter
    private static int maxSpeed;

    public Fox() {
        this.isAlive = true;
        this.currentFullness = maxFullness * Settings.getInstance().getAnimalFullnessOnStart();
        this.weight = settings.getWeight();
        this.chanceOfHunt = settings.getChanceOfHunt();
    }

    @Override
    public void initSettings(AnimalSettings animalSettings) {
        settings = animalSettings;
        emoji = settings.getEmoji();
        maxPerCell = settings.getMaxPerCell();
        maxSpeed = settings.getMaxSpeed();
        maxFullness = settings.getMaxFullness();
    }
}

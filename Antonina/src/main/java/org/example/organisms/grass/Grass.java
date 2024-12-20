package org.example.organisms.grass;

import lombok.Getter;
import org.example.organisms.Organism;
import org.example.organisms.interfaces.GrassSettingsInterface;
import org.example.organisms.interfaces.Mortal;
import org.example.settings.GrassSettings;

public class Grass extends Organism implements Mortal, GrassSettingsInterface {
    @Getter
    private static String emoji;
    @Getter
    private static int maxPerCell;
    @Getter
    private static double weight;

    @Override
    public void initSettings(GrassSettings settings) {
        emoji = settings.getEmoji();
        maxPerCell = settings.getMaxPerCell();
        weight = settings.getWeight();
    }

    @Override
    public void die() {
    }
}

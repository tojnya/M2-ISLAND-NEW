package org.example.settings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrassSettings {
    private String emoji;
    private double weight;
    private int maxPerCell;

    public GrassSettings(String emoji, double weight, int maxPerCell) {
        this.emoji = emoji;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
    }
}

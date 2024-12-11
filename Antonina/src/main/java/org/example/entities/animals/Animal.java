package org.example.entities.animals;

import org.example.entities.interfaces.Eating;
import org.example.entities.interfaces.Mortal;
import org.example.entities.interfaces.Movable;
import org.example.entities.interfaces.Reproducible;

public abstract class Animal implements Movable, Eating, Reproducible, Mortal {
}

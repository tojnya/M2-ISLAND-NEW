package org.example.spawners.initialSpawners;

import org.example.gamefield.GameField;

public interface InitialSpawner extends Runnable {
    void initialSpawn();

    void aggregate(GameField.Cell[][] cells);
}

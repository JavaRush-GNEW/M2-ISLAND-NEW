package org.ua.com.javarush.gnew.model.Animals.Intarfaces;

import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandMap;

public interface LifeCycle {
    void move(IslandMap island, Cell currentCell);
    void eat(Cell currentCell);
    void reproduce(Cell currentCell);
}

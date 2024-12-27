package org.ua.com.javarush.gnew.model.Animals.Intarfaces;

import org.ua.com.javarush.gnew.Island.Cell;

import java.util.Map;

public interface Organism extends LifeCycle{
    int getMAX_CELL_COUNT();
    int getMAX_STEPS_COUNT();
    Map<Class<? extends Organism>, Integer> getEatProbability();
    void isAnimalAlive(Cell cell);
}

package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import lombok.Data;
import lombok.Getter;
import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandMap;

@Getter
public class Horse extends Herbivores{
    private final int WEIGHT = 400;
    private final int MAX_CELL_COUNT = 20;
    private final int MAX_STEPS_COUNT = 4;
    private final int FOOD_KG_REQUIRED = 60;


    @Override
    public int getMaxCountPerCell() {
        return MAX_CELL_COUNT;
    }

    @Override
    public void move(IslandMap island, Cell currentCell) {

    }

    @Override
    public void eat(Cell currentCell) {

    }

    @Override
    public void reproduce(Cell currentCell) {

    }
}

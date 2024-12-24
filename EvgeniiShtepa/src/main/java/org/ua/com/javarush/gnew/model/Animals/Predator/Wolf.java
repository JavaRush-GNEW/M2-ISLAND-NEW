package org.ua.com.javarush.gnew.model.Animals.Predator;

import lombok.Data;
import lombok.Getter;
import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandMap;

@Getter
public class Wolf extends Predator {
    private final int MAX_CELL_COUNT = 30;
    private final int MAX_STEPS_COUNT = 3;
    private final int WEIGHT = 50;
    private final int FOOD_KG_REQUIRED = 8;




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

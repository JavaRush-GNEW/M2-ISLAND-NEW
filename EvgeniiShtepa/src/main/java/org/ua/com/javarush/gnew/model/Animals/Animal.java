package org.ua.com.javarush.gnew.model.Animals;

import lombok.Getter;
import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandMap;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public abstract class Animal implements Organism {
    private final int MAX_CELL_COUNT;
    private final int MAX_STEPS_COUNT;
    private final int WEIGHT;
    private final int FOOD_KG_REQUIRED;


    protected Animal(int maxCellCount, int maxStepsCount, int weight, int foodKgRequired) {
        MAX_CELL_COUNT = maxCellCount;
        MAX_STEPS_COUNT = maxStepsCount;
        WEIGHT = weight;
        FOOD_KG_REQUIRED = foodKgRequired;
    }

    public void move(IslandMap island, Cell currentCell) {
        int cellX = currentCell.getX();
        int cellY = currentCell.getY();
        int direction = ThreadLocalRandom.current().nextInt(4); // 0: вверх, 1: вниз, 2: влево, 3: вправо
        int newX = cellX;
        int newY = cellY;

        if (direction == 0) {
            newY++;
        } else if (direction == 1) {
            newY--;
        } else if (direction == 2) {
            newX--;
        } else {
            newX++;
        }

        Cell[][] cells = island.getCells();
        if ((newX >= 0 && newX < island.getWidth() && newY >= 0 && newY < island.getHeight())) {// ширина || висота
            Cell newCell = cells[newY][newX];
            currentCell.removeAnimal(this);
            newCell.addAnimal(this);
        }


    }


    public void eat(Cell currentCell) {

    }

    public void reproduce(Cell currentCell) {

    }

}

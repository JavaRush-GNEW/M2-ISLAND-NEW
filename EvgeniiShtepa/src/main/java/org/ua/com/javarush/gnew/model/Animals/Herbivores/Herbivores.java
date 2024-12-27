package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.model.Animals.Animal;


public abstract class Herbivores extends Animal {

    public Herbivores(int maxCellCount, int maxStepsCount, int weight, int foodKgRequired) {
        super(maxCellCount, maxStepsCount, weight, foodKgRequired);
    }

    @Override
    public void eat(Cell currentCell) {
        if (currentCell.getGrassAmount() > 0 && this.getSatiety() < this.getFOOD_KG_REQUIRED()) {
            currentCell.setGrassAmount(currentCell.getGrassAmount() - 1);
            this.setSatiety(getSatiety() + 1);

        }
    }
}

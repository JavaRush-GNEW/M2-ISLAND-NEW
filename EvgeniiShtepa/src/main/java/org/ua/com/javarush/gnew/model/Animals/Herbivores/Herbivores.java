package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.model.Animals.Animal;

public abstract class Herbivores extends Animal {

    public Herbivores(int maxCellCount, int maxStepsCount, int weight, int foodKgRequired) {
        super(maxCellCount, maxStepsCount, weight, foodKgRequired);
    }

    @Override
    public void eat(Cell currentCell) {

    }
}

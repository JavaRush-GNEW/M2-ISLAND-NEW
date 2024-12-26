package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.model.Animals.Animal;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.Map;

public abstract class Predator extends Animal {

    public Predator(int maxCellCount, int maxStepsCount, int weight, int foodKgRequired) {
        super(maxCellCount, maxStepsCount, weight, foodKgRequired);
    }

    @Override
    public void eat(Cell currentCell) {
        Map<Class<? extends Organism>, Integer> eatProbability = Wolf.getEAT_PROBABILITY();


    }
}

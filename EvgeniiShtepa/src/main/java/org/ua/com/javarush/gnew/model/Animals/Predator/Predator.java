package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.model.Animals.Animal;

public abstract class Predator extends Animal {

    public Predator(int maxCellCount, int maxStepsCount, int weight, int foodKgRequired) {
        super(maxCellCount, maxStepsCount, weight, foodKgRequired);
    }
}

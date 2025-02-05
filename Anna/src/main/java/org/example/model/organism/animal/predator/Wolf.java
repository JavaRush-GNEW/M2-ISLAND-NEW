package org.example.model.organism.animal.predator;

import org.example.model.organism.animal.herbivore.Horse;

public class Wolf extends Predator {
    private static final double WOLF_WEIGHT = 50;
    private static final double WOLF_FOOD_NEEDED = 8;
    private static final int WOLF_SPEED = 3;
    private static final int MAX_POPULATION = 30;

    public Wolf() {
        super(WOLF_WEIGHT, WOLF_FOOD_NEEDED, WOLF_SPEED, MAX_POPULATION);
        eatingProbabilities.put(Horse.class, 10);
    }


    @Override
    public Wolf createOffspring() {
        return new Wolf();
    }
}

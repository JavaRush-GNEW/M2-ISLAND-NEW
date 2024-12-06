package org.example.model.organism.animal.herbivore;

public class Horse extends Herbivore {
    private static final double HORSE_WEIGHT = 400;
    private static final double HORSE_FOOD_NEEDED = 60;
    private static final int HORSE_SPEED = 4;
    private static final int MAX_POPULATION = 20;


    public Horse() {
        super(HORSE_WEIGHT, HORSE_FOOD_NEEDED, HORSE_SPEED, MAX_POPULATION);
    }

    @Override
    public Horse createOffspring() {
        return new Horse();
    }
}

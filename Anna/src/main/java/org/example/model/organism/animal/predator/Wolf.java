package org.example.model.organism.animal.predator;

public class Wolf extends Predator {
    private static final double WOLF_WEIGHT = 50;
    private static final double WOLF_FOOD_NEEDED = 8;
    private static final int WOLF_SPEED = 3;
    private static final int MAX_QUANTITY_AT_CELL = 30;

    public Wolf() {
        super(WOLF_WEIGHT, WOLF_FOOD_NEEDED, WOLF_SPEED, MAX_QUANTITY_AT_CELL);
    }


    @Override
    public Wolf createOffspring() {
        return new Wolf();
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "isAlive=" + isAlive +
                ", health=" + health +
                ", weight=" + weight +
                ", foodNeeded=" + foodNeeded +
                ", speed=" + speed +
                ", maxPopulation=" + maxPopulation +
                '}';
    }
}

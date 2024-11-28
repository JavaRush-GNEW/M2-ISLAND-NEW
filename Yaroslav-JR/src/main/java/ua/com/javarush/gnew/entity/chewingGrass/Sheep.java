package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Sheep extends ChewingGrass {

    private static final double MAX_WEIGHT = 85.0;
    private static final double INITIAL_WEIGHT = 70.0;
    private static final int MOVE_DISTANCE = 3;

    public Sheep() {
        super(140, INITIAL_WEIGHT);
    }

    @Override
    protected int getMoveDistance() {
        return MOVE_DISTANCE;
    }

    @Override
    public void checkSatiation() {
        if (getWeight() >= MAX_WEIGHT) {
            isSatiated = true;
        } else {
            isSatiated = false;
        }
    }
}

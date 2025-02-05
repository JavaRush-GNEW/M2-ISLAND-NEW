package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Buffalo extends ChewingGrass{

    private static final double MAX_WEIGHT = 800.0;
    private static final double INITIAL_WEIGHT = 700.0;
    private static final int MOVE_DISTANCE = 3;

    public Buffalo() {
        super(10, INITIAL_WEIGHT);
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

package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Boar extends ChewingGrass{

    private static final double MAX_WEIGHT = 450.0;
    private static final double INITIAL_WEIGHT = 400.0;
    private static final int MOVE_DISTANCE = 2;

    public Boar() {
        super(50, INITIAL_WEIGHT);
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

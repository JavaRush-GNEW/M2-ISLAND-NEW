package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends ChewingGrass{


    private static final double INITIAL_WEIGHT = 0.1;


    public Caterpillar() {
        super(1000, INITIAL_WEIGHT);
    }


}

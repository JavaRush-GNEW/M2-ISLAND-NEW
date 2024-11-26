package ua.com.javarush.gnew.entity.plant;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Grass extends Organism {
    private static final double INITIAL_WEIGHT = 1.0;
    public Grass() {
        super(200, INITIAL_WEIGHT);
    }
}

package entities.plants;

import entities.Organism;

public class Plant implements Organism {
    public final static double WEIGHT = 0.1; // Вага рослини



    public void reproduce() {

    }

    @Override
    public boolean isAlive() {
        return true;
    }
}

package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends ChewingGrass {

    private static final double MAX_WEIGHT = 2.45;
    private static final double INITIAL_WEIGHT = 2.0;
    private static final int MOVE_DISTANCE = 2;

    public Rabbit() {
        super(150, INITIAL_WEIGHT);
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

    @Override
    public void eat(Cell cell) {
        Set<Organism> grassSet = cell.getResidents().get(Grass.class);
        if (grassSet == null || grassSet.isEmpty()) return;

        Iterator<Organism> iterator = cell.getResidents().get(Grass.class).iterator();

        while (iterator.hasNext()) {
            Organism prey = iterator.next();
            prey.die();
            iterator.remove();
            this.setWeight(this.getWeight() + prey.getWeight() / 10);
            break;
        }
    }
}

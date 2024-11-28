package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends ChewingGrass{

    private static final double MAX_WEIGHT = 1.15;
    private static final double INITIAL_WEIGHT = 1.0;
    private static final int MOVE_DISTANCE = 4;

    public Duck() {
        super(200, INITIAL_WEIGHT);
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

        Set<Organism> caterpillarSet = cell.getResidents().get(Caterpillar.class);
        if (caterpillarSet == null || caterpillarSet.isEmpty()) return;

        Iterator<Organism> grassIterator = cell.getResidents().get(Grass.class).iterator();
        Iterator<Organism> caterpillarIterator = cell.getResidents().get(Caterpillar.class).iterator();

        boolean hasEaten = false;

        while (grassIterator.hasNext() && !hasEaten) {
            Organism prey = grassIterator.next();
            prey.die();
            grassIterator.remove();
            this.setWeight(this.getWeight() + prey.getWeight() / 10);
            hasEaten = true;
        }

        while (caterpillarIterator.hasNext() && !hasEaten) {
            Organism prey = caterpillarIterator.next();
            if (ThreadLocalRandom.current().nextInt(100) < 90) {
                prey.die();
                caterpillarIterator.remove();
                this.setWeight(this.getWeight() + prey.getWeight() / 10);
                hasEaten = true;
            }
        }
    }
}

package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends ChewingGrass{


    private static final double INITIAL_WEIGHT = 0.1;


    public Caterpillar() {
        super(1000, INITIAL_WEIGHT);
    }


    @Override
    protected int getMoveDistance() {
        return 0;
    }

    @Override
    public void checkSatiation(){
        isSatiated = false;
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
            this.setWeight(this.getWeight() + prey.getWeight() / 50);
            break;
        }
    }
}

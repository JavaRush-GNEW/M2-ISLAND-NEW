package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class ChewingGrass extends Animal {


    public ChewingGrass(int maxCellResidents, double initialWeight) {
        super(maxCellResidents, initialWeight);
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
            this.setWeight(this.getWeight() + prey.getWeight());
            break;
        }
    }


}

package ua.com.javarush.gnew.entity.meatEaters;

import ua.com.javarush.gnew.Main;
import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.chewingGrass.*;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends MeatEaters {

    private static final double MAX_WEIGHT = 58.0;
    private static final double INITIAL_WEIGHT = 50.0;
    private static final int MOVE_DISTANCE = 3;

    public Wolf() {
        super(30, INITIAL_WEIGHT);
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

        Map<Class<? extends Organism>, Integer> preyTypes = Map.of(
                Sheep.class, 70,
                Mouse.class, 80,
                Rabbit.class, 60,
                Deer.class, 15,
                Horse.class, 10,
                Goat.class, 60,
                Boar.class, 15,
                Buffalo.class, 10,
                Duck.class, 40
        );


        for (Map.Entry<Class<? extends Organism>, Integer> entry : preyTypes.entrySet()) {
            Class<? extends Organism> preyClass = entry.getKey();
            int chanceToEat = entry.getValue();

            Set<Organism> preySet = cell.getResidents().get(preyClass);
            if (preySet == null || preySet.isEmpty()) continue;

            Iterator<Organism> iterator = preySet.iterator();

            while (iterator.hasNext()) {
                Organism prey = iterator.next();


                if (ThreadLocalRandom.current().nextInt(100) < chanceToEat) {
                    prey.die();
                    iterator.remove();
                    this.setWeight(this.getWeight() + prey.getWeight() / 2);
                    return;
                }
            }
        }
    }
}

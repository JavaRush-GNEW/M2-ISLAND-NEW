package ua.com.javarush.gnew.entity.meatEaters;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.chewingGrass.*;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends MeatEaters{
    private static final double MAX_WEIGHT = 10.0;
    public static final double INITIAL_WEIGHT = 8.0;
    private static final int MOVE_DISTANCE = 2;

    public Fox() {
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

                Mouse.class, 90,
                Rabbit.class, 70,
                Caterpillar.class, 40,
                Duck.class, 60
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
                    this.setWeight(this.getWeight() + prey.getWeight());
                    return;
                }
            }
        }
    }
}

package ua.com.javarush.gnew.entity.meatEaters;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.chewingGrass.*;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends MeatEaters{
    private static final double MAX_WEIGHT = 10.0;
    private static final double INITIAL_WEIGHT = 8.0;
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

        Set<Organism> preySet = cell.getResidents().get(ChewingGrass.class);
        if (preySet == null || preySet.isEmpty()) {
            return;
        }

        Iterator<Organism> iterator = preySet.iterator();

        while (iterator.hasNext()) {
            Organism prey = iterator.next();

            int chanceToEat = 0;

            if (prey instanceof Rabbit) {
                chanceToEat = 70;
            }else if (prey instanceof Mouse) {
                chanceToEat = 90;
            }else if (prey instanceof Caterpillar) {
                chanceToEat = 40;
            } else if (prey instanceof Duck) {
                chanceToEat = 60;
            }

            if (ThreadLocalRandom.current().nextInt(100) < chanceToEat) {

                prey.die();
                iterator.remove();
                this.setWeight(this.getWeight() + prey.getWeight() / 2);
                break;
            }

        }
    }
}

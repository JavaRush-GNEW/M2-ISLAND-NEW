package ua.com.javarush.gnew.entity;

import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism {



    public boolean isSatiated;

    public Animal(int maxCellResidents, double initialWeight) {
        super(maxCellResidents, initialWeight);
    }

    protected abstract int getMoveDistance();


    public boolean isSatiated() {
        return isSatiated;
    }

    public void reproduce(Cell currentCell) {
        this.checkSatiation();
        int currentCount = currentCell.getResidents().get(this.getClass()).size();
        if (isSatiated && this.getMaxCellResidents() > currentCount) {
            try {
                Organism offspring = this.getClass().getDeclaredConstructor().newInstance();
                    currentCell.add(offspring);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void eat(Cell cell){

    }

    public void move(Cell currentCell, Island island, int currentX, int currentY) {
            int moveDistance = getMoveDistance();

            int deltaX = ThreadLocalRandom.current().nextInt(-moveDistance, moveDistance + 1);
            int deltaY = ThreadLocalRandom.current().nextInt(-moveDistance, moveDistance + 1);

            int newX = Math.max(0, Math.min(currentX + deltaX, island.getWidth() - 1));
            int newY = Math.max(0, Math.min(currentY + deltaY, island.getHeight() - 1));

                if (island.getField()[newX][newY].add(this)) {

                        Iterator<Organism> iterator = currentCell.getResidents().get(this.getClass()).iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().equals(this)) {
                                iterator.remove();
                                break;
                            }
                        }

                }

    }

}

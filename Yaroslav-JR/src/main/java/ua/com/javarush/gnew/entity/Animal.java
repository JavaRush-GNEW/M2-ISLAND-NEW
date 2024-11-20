package ua.com.javarush.gnew.entity;

import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Random;

public abstract class Animal extends Organism {
    protected static int lifePower = 50;

    public static int getLifePower() {
        return lifePower;
    }

    public  void move(Cell currentCell, Island island, int x, int y) {
        lifePower -= 5;
        if (lifePower <= 0) {
            currentCell.removeAnimal(this);
            return;
        }
        Random random = new Random();
        int deltaX = random.nextInt(4) - 2;
        int deltaY = random.nextInt(4) - 2;
        int newX = Math.max(0, Math.min(island.getField().length - 1, x + deltaX));
        int newY = Math.max(0, Math.min(island.getField()[0].length - 1, y + deltaY));
        currentCell.removeAnimal(this);
        Cell newCell = island.getField()[newX][newY];
        newCell.addAnimal(this);
    }


}
